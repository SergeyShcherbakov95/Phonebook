package shcherbakov.sergey.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import shcherbakov.sergey.PhonebookApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class ContactValidatorTest {
	@Test
	public void checkSurname_positive(){
		String surname1 = "fsds34fh!g";
		String surname2 = "aedd";
		
		ContactValidator cv = new ContactValidator();
		
		assertTrue(cv.checkSurname(surname1));
		assertTrue(cv.checkSurname(surname2));
	}
	
	@Test
	public void checkSurname_negative(){
		String surname1 = "aed";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkSurname(surname1));
	}
	
	@Test
	public void checkName_positive(){
		String name1 = "fsds34fh!g";
		String name2 = "aedd";
		
		ContactValidator cv = new ContactValidator();
		
		assertTrue(cv.checkName(name1));
		assertTrue(cv.checkName(name2));
	}
	
	@Test
	public void checkName_negative(){
		String name1 = "aed";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkName(name1));
	}
	
	@Test
	public void checkPatronymic_positive(){
		String patronymic1 = "fsds34fh!g";
		String patronymic2 = "aedd";
		
		ContactValidator cv = new ContactValidator();
		
		assertTrue(cv.checkPatronymic(patronymic1));
		assertTrue(cv.checkPatronymic(patronymic2));
	}
	
	@Test
	public void checkPatronymic_negative(){
		String patronymic1 = "aed";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkPatronymic(patronymic1));
	}
	
	@Test
	public void checkPhone_positive(){
		String phone1 = "+380(07)0123456";
		String phone2 = "+380(97)9678010";
		
		ContactValidator cv = new ContactValidator();
		
		assertTrue(cv.checkPhone(phone1));
		assertTrue(cv.checkPhone(phone2));
	}
	
	@Test
	public void checkPhone_negative(){
		String phone1 = "380(08)1234567";
		String phone2 = "+80(08)1234567";
		String phone3 = "+30(08)1234567";
		String phone4 = "+38(08)1234567";
		String phone5 = "+381(08)1234567";
		String phone6 = "+300(08)1234567";
		String phone7 = "+080(08)1234567";
		String phone8 = "-380(08)1234567";
		String phone9 = "+308(08)1234567";
		String phone10 = "+380(081)1234567";
		String phone11 = "+380(0)1234567";
		String phone12 = "+380(08)11234567";
		String phone13 = "+380(08)112345";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkPhone(phone1));
		assertFalse(cv.checkPhone(phone2));
		assertFalse(cv.checkPhone(phone3));
		assertFalse(cv.checkPhone(phone4));
		assertFalse(cv.checkPhone(phone5));
		assertFalse(cv.checkPhone(phone6));
		assertFalse(cv.checkPhone(phone7));
		assertFalse(cv.checkPhone(phone8));
		assertFalse(cv.checkPhone(phone9));
		assertFalse(cv.checkPhone(phone10));
		assertFalse(cv.checkPhone(phone11));
		assertFalse(cv.checkPhone(phone12));
		assertFalse(cv.checkPhone(phone13));
	}
	
	@Test
	public void checkEmail_positive(){
		String email1 = "Sfg.r465gd@gserb.re";
		String email2 = "+srEgddytt_Rg55@gmail.com";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkPhone(email1));
		assertFalse(cv.checkPhone(email2));
	}
	
	@Test
	public void checkEmail_negative(){
		String email1 = "@grgr.re";
		String email2 = "ferfr@gttgt.";
		String email3 = "wefwef@.fdf";
		String email4 = "reefr@dfvdf";
		String email5 = "EDrgrrgrrf.er";
		
		ContactValidator cv = new ContactValidator();
		
		assertFalse(cv.checkPhone(email1));
		assertFalse(cv.checkPhone(email2));
		assertFalse(cv.checkPhone(email3));
		assertFalse(cv.checkPhone(email4));
		assertFalse(cv.checkPhone(email5));
	}
}
