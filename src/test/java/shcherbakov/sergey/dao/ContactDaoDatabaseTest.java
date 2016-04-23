package shcherbakov.sergey.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.dao.contact.ContactDaoDatabase;
import shcherbakov.sergey.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
@TestPropertySource("file:./src/test/resources/database.properties")
public class ContactDaoDatabaseTest {
	@Value("${testHost}")
	public String host;
	@Value("${testPort}")
	public String port;
	@Value("${testDbName}")
	public String dbName;
	@Value("${testLogin}")
	public String login;
	@Value("${testPassword}")
	public String password;
	
	private DataSource dataSourceDatabase;
	
	@Before
	public void deleteUsers(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String con = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        dataSource.setUrl(con);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        
        this.dataSourceDatabase = dataSource;
		
		try(Connection connection = dataSourceDatabase.getConnection();
				PreparedStatement statementDeleteUsers = connection.prepareStatement("DELETE FROM Users");
				PreparedStatement statementAddUser = connection.prepareStatement("INSERT INTO Users VALUES(1, 'testLogin', 'testPassword', 'testFullName')");
						PreparedStatement statementDeleteContacts = connection.prepareStatement("DELETE FROM Contacts")){
			statementDeleteUsers.executeUpdate();
			statementAddUser.executeUpdate();
			statementDeleteContacts.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void listContact_EmptyList(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.isEmpty());
	}
	
	@Test
	public void listContact_NotEmptyList(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
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
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		assertTrue(contacts.get(0).getName().equals("testName"));
		assertTrue(contacts.get(0).getPatronymic().equals("testPatronymic"));
		assertTrue(contacts.get(0).getMobilePhone().equals("testMobilePhone"));
		assertTrue(contacts.get(0).getHomePhone().equals("testHomePhone"));
		assertTrue(contacts.get(0).getAddress().equals("testAddress"));
		assertTrue(contacts.get(0).getEmail().equals(""));
	}
	
	@Test
	public void editContact_positive(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		assertTrue(contacts.get(0).getName().equals("testName"));
		assertTrue(contacts.get(0).getPatronymic().equals("testPatronymic"));
		assertTrue(contacts.get(0).getMobilePhone().equals("testMobilePhone"));
		assertTrue(contacts.get(0).getHomePhone().equals("testHomePhone"));
		assertTrue(contacts.get(0).getAddress().equals("testAddress"));
		assertTrue(contacts.get(0).getEmail().equals(""));
		
		Contact newContact = new Contact(contacts.get(0).getIdContact(), "newSurname", "newName", "newPatronymic", "newMobilePhone", "newHomePhone", "", "new");
		
		contactDao.editContact(newContact);
		
		List<Contact> editContacts = contactDao.listContact("testLogin");
		
		assertTrue(editContacts.get(0).getSurname().equals("newSurname"));
		assertTrue(editContacts.get(0).getName().equals("newName"));
		assertTrue(editContacts.get(0).getPatronymic().equals("newPatronymic"));
		assertTrue(editContacts.get(0).getMobilePhone().equals("newMobilePhone"));
		assertTrue(editContacts.get(0).getHomePhone().equals("newHomePhone"));
		assertTrue(editContacts.get(0).getAddress().equals(""));
		assertTrue(editContacts.get(0).getEmail().equals("new"));
	}
	
	@Test
	public void getContact_UserExist(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		assertTrue(contacts.get(0).getName().equals("testName"));
		assertTrue(contacts.get(0).getPatronymic().equals("testPatronymic"));
		assertTrue(contacts.get(0).getMobilePhone().equals("testMobilePhone"));
		assertTrue(contacts.get(0).getHomePhone().equals("testHomePhone"));
		assertTrue(contacts.get(0).getAddress().equals("testAddress"));
		assertTrue(contacts.get(0).getEmail().equals(""));
	}
	
	@Test
	public void addContact_UserNotExist(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "errorLogin");
		
		Contact newContact = contactDao.getContact("1", "errorLogin");
		
		assertNull(newContact);
	}
	
	@Test
	public void addContact_WrongId(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("1", "testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		Contact newContact = contactDao.getContact("2", "testLogin");
		
		assertNull(newContact);
	}
	
	@Test
	public void deleteContact_positive(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
		Contact contact = new Contact("testSurname", "testName", "testPatronymic", "testMobilePhone", "testHomePhone", "testAddress", "");
		
		contactDao.addContact(contact, "testLogin");
		
		List<Contact> contacts = contactDao.listContact("testLogin");
		
		assertTrue(contacts.get(0).getSurname().equals("testSurname"));
		assertTrue(contacts.get(0).getName().equals("testName"));
		assertTrue(contacts.get(0).getPatronymic().equals("testPatronymic"));
		assertTrue(contacts.get(0).getMobilePhone().equals("testMobilePhone"));
		assertTrue(contacts.get(0).getHomePhone().equals("testHomePhone"));
		assertTrue(contacts.get(0).getAddress().equals("testAddress"));
		assertTrue(contacts.get(0).getEmail().equals(""));
		
		contactDao.deleteContact(contacts.get(0).getIdContact(), "testLogin");
		
		List<Contact> newContacts = contactDao.listContact("testLogin");
		
		assertTrue(newContacts == null || newContacts.isEmpty());
	}
	
	@Test
	public void listContactSearch_bySurname(){
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
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
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
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
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
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
		ContactDaoDatabase contactDao = new ContactDaoDatabase();
		contactDao.setDataSource(dataSourceDatabase);
		
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
