package shcherbakov.sergey.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class UsersTest {
	@Test
	public void addUser_usersListNull(){
		Users users = new Users();
		
		users.setUsers(null);
		
		User user = new User();
		users.addUser(user);
		
		assertTrue(users.getUsers().size() == 1);
	}
	
	@Test
	public void addUser_usersListNotNull(){
		Users users = new Users();
		
		users.setUsers(null);
		
		User user1 = new User();
		User user2 = new User();
		users.addUser(user1);
		users.addUser(user2);
		
		assertTrue(users.getUsers().size() == 2);
	}
}
