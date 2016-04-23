package shcherbakov.sergey.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.dao.contact.ContactDaoDatabase;
import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.services.contact.ContactServiceImpl;
import shcherbakov.sergey.validator.ContactValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class ContactServiceImplTest {
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongSurname(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(false);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongName(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(false);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongPatronymic(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(false);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongMobilePhone(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(false, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongHomePhone(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, false);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void addContact_wrongEmail(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(false);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test
	public void addContact_positive(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).addContact(contact,  "login");
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.addContact(contact, "login");
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongSurname(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(false);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongName(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(false);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongPatronymic(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(false);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongMobilePhone(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(false, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongHomePhone(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, false);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test(expected = ValidatorException.class)
	public void editContact_wrongEmail(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(false);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
	
	@Test
	public void editContact_positive(){
		ContactServiceImpl contactService = new ContactServiceImpl();
		ContactValidator contactValidator = Mockito.mock(ContactValidator.class);
		ContactDaoDatabase contactDao = Mockito.mock(ContactDaoDatabase.class);
		
		Contact contact = new Contact("test", "test", "test", "test", "test", "test", "test");
		
		Mockito.when(contactValidator.checkSurname("test")).thenReturn(true);
		Mockito.when(contactValidator.checkName("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPatronymic("test")).thenReturn(true);
		Mockito.when(contactValidator.checkPhone("test")).thenReturn(true, true);
		Mockito.when(contactValidator.checkEmail("test")).thenReturn(true);
		
		Mockito.doNothing().when(contactDao).editContact(contact);
		
		contactService.setContactDao(contactDao);
		contactService.setContactValidator(contactValidator);
		
		contactService.editContact(contact);
	}
}
