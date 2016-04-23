package shcherbakov.sergey.model;

import static org.junit.Assert.assertFalse;
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
public class ContactTest {
	@Test
	public void equals_reflexivity(){
		Contact contact = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertTrue(contact.equals(contact));		
	}
	
	@Test
	public void equals_symmetry(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertTrue(contact1.equals(contact2));
		assertTrue(contact2.equals(contact1));
	}
	
	@Test
	public void equals_transitivity(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact3 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertTrue(contact1.equals(contact2));
		assertTrue(contact2.equals(contact3));
		assertTrue(contact1.equals(contact3));
	}
	
	@Test
	public void equals_secondElementNull(){
		Contact contact = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact.equals(null));		
	}
	
	@Test
	public void equals_differentClasses(){
		Contact contact = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		String string = "";
		
		assertFalse(contact.equals(string));		
	}
	
	@Test
	public void equals_differentId(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "2", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentSurname(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname1", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentName(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name1", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentPatronymic(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic1", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentMobilePhone(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)0976545", "+380(97)1234567", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentHomePhone(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)0976545", "address", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentAddress(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address1", "qqqqq@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
	
	@Test
	public void equals_differentEmail_negative(){
		Contact contact1 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "qqqqq@sdf.as");
		Contact contact2 = new Contact( "1", "surname", "name", "patronymic", "+380(97)1234567", "+380(97)1234567", "address", "aaaaaa@sdf.as");
		
		assertFalse(contact1.equals(contact2));		
	}
}
