package shcherbakov.sergey.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.dao.user.UserDaoDatabase;
import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.services.user.UserServiceImpl;
import shcherbakov.sergey.validator.UserValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class UserServiceImplTest {

	@Test(expected = ValidatorException.class)
	public void addUser_wrongLogin(){
		UserServiceImpl userService = new UserServiceImpl();
		UserValidator userValidator = Mockito.mock(UserValidator.class);
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		User user = new User("test", "test", "test");
		
		Mockito.when(userValidator.checkLogin("test")).thenReturn(false);
		Mockito.when(userValidator.checkPassword("test")).thenReturn(true);
		Mockito.when(userValidator.checkFullName("test")).thenReturn(true);
		
		Mockito.doNothing().when(userDao).addUser(user);
		
		userService.setUserDao(userDao);
		userService.setUserValidator(userValidator);
		
		userService.addUser(user);
	}
	
	@Test(expected = ValidatorException.class)
	public void addUser_wrongPassword(){
		UserServiceImpl userService = new UserServiceImpl();
		UserValidator userValidator = Mockito.mock(UserValidator.class);
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		User user = new User("test", "test", "test");
		
		Mockito.when(userValidator.checkLogin("test")).thenReturn(true);
		Mockito.when(userValidator.checkPassword("test")).thenReturn(false);
		Mockito.when(userValidator.checkFullName("test")).thenReturn(true);
		
		Mockito.doNothing().when(userDao).addUser(user);
		
		userService.setUserDao(userDao);
		userService.setUserValidator(userValidator);
		
		userService.addUser(user);
	}
	
	@Test(expected = ValidatorException.class)
	public void addUser_wrongFullName(){
		UserServiceImpl userService = new UserServiceImpl();
		UserValidator userValidator = Mockito.mock(UserValidator.class);
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		User user = new User("test", "test", "test");
		
		Mockito.when(userValidator.checkLogin("test")).thenReturn(true);
		Mockito.when(userValidator.checkPassword("test")).thenReturn(true);
		Mockito.when(userValidator.checkFullName("test")).thenReturn(false);
		
		Mockito.doNothing().when(userDao).addUser(user);
		
		userService.setUserDao(userDao);
		userService.setUserValidator(userValidator);
		
		userService.addUser(user);
	}
	
	@Test(expected = DataException.class)
	public void addUser_userExist(){
		UserServiceImpl userService = new UserServiceImpl();
		UserValidator userValidator = Mockito.mock(UserValidator.class);
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		User user = new User("test", "test", "test");
		
		Mockito.when(userValidator.checkLogin("test")).thenReturn(true);
		Mockito.when(userValidator.checkPassword("test")).thenReturn(true);
		Mockito.when(userValidator.checkFullName("test")).thenReturn(true);
		
		Mockito.doThrow(DataException.class).when(userDao).addUser(user);
		
		userService.setUserDao(userDao);
		userService.setUserValidator(userValidator);
		
		userService.addUser(user);
	}
	
	@Test
	public void addUser_positive(){
		UserServiceImpl userService = new UserServiceImpl();
		UserValidator userValidator = Mockito.mock(UserValidator.class);
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		User user = new User("test", "test", "test");
		
		Mockito.when(userValidator.checkLogin("test")).thenReturn(true);
		Mockito.when(userValidator.checkPassword("test")).thenReturn(true);
		Mockito.when(userValidator.checkFullName("test")).thenReturn(true);
		
		Mockito.doNothing().when(userDao).addUser(user);
		
		userService.setUserDao(userDao);
		userService.setUserValidator(userValidator);
		
		userService.addUser(user);
	}
}
