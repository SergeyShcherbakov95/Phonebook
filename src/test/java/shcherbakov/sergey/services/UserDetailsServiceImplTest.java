package shcherbakov.sergey.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.dao.user.UserDaoDatabase;
import shcherbakov.sergey.services.user.UserDetailsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
public class UserDetailsServiceImplTest {
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_userNull(){
		UserDetailsServiceImpl userService = new UserDetailsServiceImpl();
		UserDaoDatabase userDao = Mockito.mock(UserDaoDatabase.class);
		
		Mockito.when(userDao.getUser("test")).thenReturn(null);
		
		userService.setUserDao(userDao);
		
		userService.loadUserByUsername("test");
	}
}
