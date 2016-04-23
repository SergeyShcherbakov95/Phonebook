package shcherbakov.sergey.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class UserTest {
	@Test
	public void addContact_contactsListNull(){
		User user = new User();
		
		user.setContacts(null);
		
		Contact contact = new Contact();
		user.addContact(contact);
		
		assertTrue(user.getContacts().size() == 1);
	}
	
	@Test
	public void addContact_contactsListNotNull(){
		User user = new User();
		
		user.setContacts(null);
		
		Contact contact1 = new Contact();
		Contact contact2 = new Contact();
		user.addContact(contact1);
		user.addContact(contact2);
		
		assertTrue(user.getContacts().size() == 2);
	}
}
