package shcherbakov.sergey.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.config.XmlProperties;
import shcherbakov.sergey.dao.contact.ContactDaoDatabase;
import shcherbakov.sergey.dao.contact.ContactDaoXml;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
@TestPropertySource("file:./src/test/resources/xml.properties")
public class ContactDaoXmlTest {
	
	private Marshaller marshaller;
	
	private Unmarshaller unmarshaller;
	
	private XmlProperties xmlProp = new XmlProperties();
	
	@Value("${testPathToFile}")
	private String pathToFile;
	
	@Before
	public void deleteUsers() throws JAXBException, IOException{
		xmlProp.setPathXml(pathToFile);
		JAXBContext context = JAXBContext.newInstance(Users.class, User.class, Contact.class);
		this.marshaller = context.createMarshaller();
		this.unmarshaller = context.createUnmarshaller();
		
		File file = new File(pathToFile);
		if(file.exists())
			file.delete();

        Users users = new Users();
        User user = new User("testLogin", "testPassword", "testFullName");
        
        users.addUser(user);
        
        file.getParentFile().mkdirs();
        file.createNewFile();
		this.marshaller.marshal(users, new File(pathToFile));
	}
	
	@Test
	public void listContact_UserExistEmptyList(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertNull(contacts);
	}
	
	@Test
	public void listContact_NotEmptyList(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
				
		contactDao.addContact(contact, "testLogin");
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.size() == 1);
		assertTrue(contact.getSurname().equals(contacts.get(0).getSurname()));
		assertTrue(contact.getName().equals(contacts.get(0).getName()));
		assertTrue(contact.getPatronymic().equals(contacts.get(0).getPatronymic()));
		assertTrue(contact.getMobilePhone().equals(contacts.get(0).getMobilePhone()));
		assertTrue(contact.getHomePhone().equals(contacts.get(0).getHomePhone()));
		assertTrue(contact.getAddress().equals(contacts.get(0).getAddress()));
		assertTrue(contact.getEmail().equals(contacts.get(0).getEmail()));
	}
	
	@Test
	public void addContact_UserExist(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		Contact newContact = contactDao.getContact("1", "testLogin");
		
		assertTrue(contact.equals(newContact));
	}
	
	@Test
	public void editContact_positive(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		assertTrue(contact.equals(contactDao.getContact("1", "testLogin")));
		
		Contact newContact = new Contact("1", "newSurname", "newName", "newPatronymic", "newMobilePhone", "newHomePhone", "", "new");
		
		contactDao.editContact(newContact);
		
		assertTrue(newContact.equals(contactDao.getContact("1", "testLogin")));
	}
	
	@Test
	public void getContact_UserExist(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		Contact newContact = contactDao.getContact("1", "testLogin");
		
		assertTrue(contact.equals(newContact));
	}
	
	@Test
	public void addContact_UserNotExist(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "errorLogin");
		
		Contact newContact = contactDao.getContact("1", "errorLogin");
		
		assertNull(newContact);
	}
	
	@Test
	public void addContact_WrongId(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		Contact newContact = contactDao.getContact("2", "testLogin");
		
		assertNull(newContact);
	}
	
	@Test
	public void deleteContact_positive(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		Contact newContact = contactDao.getContact("1", "testLogin");
		
		assertTrue(newContact.equals(contact));
		
		contactDao.deleteContact("1", "testLogin");
		
		newContact = contactDao.getContact("1", "testLogin");
		
		assertNull(newContact);
	}
	
	@Test
	public void listContactSearch_bySurname(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact1 = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		Contact contact2 = new Contact("qwerty", "qwerty", "qwerty", "qwerty", "zxcvbqwe", "qwerty", "");
		Contact contact3 = new Contact("zxcvbqwe", "zxcvbqwe", "zxcvbqwe", "qwerty", "zxcvbqwe", "zxcvbqwe", "");
		
		contactDao.addContact(contact1, "testLogin");
		contactDao.addContact(contact2, "testLogin");
		contactDao.addContact(contact3, "testLogin");
		
		List<Contact> contacts = contactDao.listContactSearch("qw", "", "", "testLogin");
		
		assertTrue(contacts.size() == 2);
		assertTrue(contacts.get(0).getSurname().equals("qwerty"));
		assertTrue(contacts.get(1).getSurname().equals("zxcvbqwe"));
		
		contacts = contactDao.listContactSearch("test", "", "", "testLogin");
		
		assertTrue(contacts.size() == 1);
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		
		contacts = contactDao.listContactSearch("", "", "", "testLogin");
		
		assertTrue(contacts.size() == 3);
	}
	
	@Test
	public void listContactSearch_byName(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact1 = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		Contact contact2 = new Contact("qwerty", "qwerty", "qwerty", "qwerty", "zxcvbqwe", "qwerty", "");
		Contact contact3 = new Contact("zxcvbqwe", "zxcvbqwe", "zxcvbqwe", "qwerty", "zxcvbqwe", "zxcvbqwe", "");
		
		contactDao.addContact(contact1, "testLogin");
		contactDao.addContact(contact2, "testLogin");
		contactDao.addContact(contact3, "testLogin");
		
		List<Contact> contacts = contactDao.listContactSearch("", "qw", "", "testLogin");
		
		assertTrue(contacts.size() == 2);
		assertTrue(contacts.get(0).getSurname().equals("qwerty"));
		assertTrue(contacts.get(1).getSurname().equals("zxcvbqwe"));
		
		contacts = contactDao.listContactSearch("", "test", "", "testLogin");
		
		assertTrue(contacts.size() == 1);
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		
		contacts = contactDao.listContactSearch("", "", "", "testLogin");
		
		assertTrue(contacts.size() == 3);
	}
	
	@Test
	public void listContactSearch_byPhone(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact1 = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "", "testAddress", "");
		Contact contact2 = new Contact("qwerty", "qwerty", "qwerty", "qwerty", "zxcvbqwe", "qwerty", "");
		Contact contact3 = new Contact("zxcvbqwe", "zxcvbqwe", "zxcvbqwe", "qwerty", "zxcvbqwe", "zxcvbqwe", "");
		
		contactDao.addContact(contact1, "testLogin");
		contactDao.addContact(contact2, "testLogin");
		contactDao.addContact(contact3, "testLogin");
		
		List<Contact> contacts = contactDao.listContactSearch("", "", "cv", "testLogin");
		
		assertTrue(contacts.size() == 2);
		assertTrue(contacts.get(0).getSurname().equals("qwerty"));
		assertTrue(contacts.get(1).getSurname().equals("zxcvbqwe"));
		
		contacts = contactDao.listContactSearch("", "", "test", "testLogin");
		
		assertTrue(contacts.size() == 1);
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		
		contacts = contactDao.listContactSearch("", "", "", "testLogin");
		
		assertTrue(contacts.size() == 3);
	}
	
	@Test
	public void listContactSearch_byAll(){
		ContactDaoXml contactDao = new ContactDaoXml();
		contactDao.setMarshaller(marshaller);
		contactDao.setUnmarshaller(unmarshaller);
		contactDao.setXmlProperties(xmlProp);
		
		Contact contact1 = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		Contact contact2 = new Contact("qwerty", "qwerty", "qwerty", "qwerty", "zxcvbqwe", "qwerty", "");
		Contact contact3 = new Contact("zxcvbqwe", "zxcvbqwe", "zxcvbqwe", "qwerty", "zxcvbqwe", "zxcvbqwe", "");
		
		contactDao.addContact(contact1, "testLogin");
		contactDao.addContact(contact2, "testLogin");
		contactDao.addContact(contact3, "testLogin");
		
		List<Contact> contacts = contactDao.listContactSearch("qw", "qw", "xc", "testLogin");
		
		assertTrue(contacts.size() == 2);
		assertTrue(contacts.get(0).getSurname().equals("qwerty"));
		assertTrue(contacts.get(1).getSurname().equals("zxcvbqwe"));
		
		contacts = contactDao.listContactSearch("test", "Name", "Phone", "testLogin");
		
		assertTrue(contacts.size() == 1);
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
	}
}
