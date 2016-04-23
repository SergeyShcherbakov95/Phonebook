package shcherbakov.sergey.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
import shcherbakov.sergey.dao.user.UserDaoXml;
import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
@TestPropertySource("file:./src/test/resources/xml.properties")
public class UserDaoXmlTest {
	private Marshaller marshaller;
	
	private Unmarshaller unmarshaller;
	
	@Value("${testPathToFile}")
	private String pathToFile;
	
	@Before
	public void deleteUsers() throws JAXBException, IOException{
		JAXBContext context = JAXBContext.newInstance(Users.class, User.class, Contact.class);
		this.marshaller = context.createMarshaller();
		this.unmarshaller = context.createUnmarshaller();
		
		File file = new File(pathToFile);
		if(file.exists())
			file.delete();

        Users users = new Users();
        
        file.getParentFile().mkdirs();
        file.createNewFile();
		this.marshaller.marshal(users, new File(pathToFile));
	}
	
	@Test(expected = DataException.class)
	public void addUser_userExist() throws SQLException{
		UserDaoXml userDao = new UserDaoXml();
		userDao.setMarshaller(marshaller);
		userDao.setUnmarshaller(unmarshaller);
		XmlProperties xmlProp = Mockito.mock(XmlProperties.class);
		userDao.setXmlProperties(xmlProp);
	
		Mockito.when(xmlProp.getPathXml()).thenReturn(pathToFile, pathToFile, pathToFile, pathToFile);
		
		User user = new User("testLogin", "testPassword", "testFullName");
		
		userDao.addUser(user);
		userDao.addUser(user);
	}
	
	@Test
	public void addUser_positive() throws SQLException{
		UserDaoXml userDao = new UserDaoXml();
		userDao.setMarshaller(marshaller);
		userDao.setUnmarshaller(unmarshaller);
		XmlProperties xmlProp = Mockito.mock(XmlProperties.class);
		userDao.setXmlProperties(xmlProp);
	
		Mockito.when(xmlProp.getPathXml()).thenReturn(pathToFile, pathToFile, pathToFile);
		
		User user = new User("testLogin", "testPassword", "testFullName");
		
		userDao.addUser(user);

		user = userDao.getUser("testLogin");
		
		assertTrue(user.getLogin().equals("testLogin"));
		assertTrue(user.getUserPassword().equals("testPassword"));
		assertTrue(user.getFullName().equals("testFullName"));
	}
	
	@Test
	public void getUser_userNotExist() throws SQLException{
		UserDaoXml userDao = new UserDaoXml();
		userDao.setMarshaller(marshaller);
		userDao.setUnmarshaller(unmarshaller);
		XmlProperties xmlProp = Mockito.mock(XmlProperties.class);
		userDao.setXmlProperties(xmlProp);
	
		Mockito.when(xmlProp.getPathXml()).thenReturn(pathToFile);
		
		User user = userDao.getUser("testLogin");
		
		assertNull(user);
	}
}
