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
@WebAppConfiguration
@DirtiesContext
public class UserValidatorTest {
	@Test
	public void checkLogin_positive(){
		String login1 = "werewfsdsfd";
		String login2 = "aed";
		
		UserValidator uv = new UserValidator();
		
		assertTrue(uv.checkLogin(login1));
		assertTrue(uv.checkLogin(login2));
	}
	
	@Test
	public void checkLogin_negative(){
		String login1 = "we";
		String login2 = "a!#$%R##@F%";
		String login3 = "asdfлsg";
		
		UserValidator uv = new UserValidator();
		
		assertFalse(uv.checkLogin(login1));
		assertFalse(uv.checkLogin(login2));
		assertFalse(uv.checkLogin(login3));
	}
	
	@Test
	public void checkPassword_positive(){
		String password1 = "q1w@$%vssr_-";
		String password2 = "ae35a";
		
		UserValidator uv = new UserValidator();
		
		assertTrue(uv.checkPassword(password1));
		assertTrue(uv.checkPassword(password2));
	}
	
	@Test
	public void checkPassword_negative(){
		String password1 = "efth";
		
		UserValidator uv = new UserValidator();
		
		assertFalse(uv.checkPassword(password1));
	}
	
	@Test
	public void checkFullName_positive(){
		String fullName1 = "q1w@$%vssr_-";
		String fullName2 = "aedhu";
		
		UserValidator uv = new UserValidator();
		
		assertTrue(uv.checkFullName(fullName1));
		assertTrue(uv.checkFullName(fullName2));
	}
	
	@Test
	public void checkFullName_negative(){
		String fullName1 = "qwer";
		
		UserValidator uv = new UserValidator();
		
		assertFalse(uv.checkFullName(fullName1));
	}
}
