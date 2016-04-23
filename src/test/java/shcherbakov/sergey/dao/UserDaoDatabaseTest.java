package shcherbakov.sergey.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shcherbakov.sergey.PhonebookApplication;
import shcherbakov.sergey.dao.user.UserDaoDatabase;
import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.query.UserQueries;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhonebookApplication.class)
@DirtiesContext
@TestPropertySource("file:./src/test/resources/database.properties")
public class UserDaoDatabaseTest {
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
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Users")){
			statement.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(expected = DataException.class)
	public void addUser_userExist() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		DataSource dataSource = Mockito.mock(DataSource.class);
		userDao.setDataSource(dataSource);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement statementAddUser = Mockito.mock(PreparedStatement.class);
		PreparedStatement statementGetUser = Mockito.mock(PreparedStatement.class);
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(UserQueries.addUser)).thenReturn(statementAddUser);
		Mockito.when(connection.prepareStatement(UserQueries.getUser)).thenReturn(statementGetUser);
		Mockito.when(statementGetUser.executeQuery()).thenReturn(resultSet);
		Mockito.when(resultSet.next()).thenReturn(true);
		Mockito.when(statementAddUser.executeUpdate()).thenReturn(1);
		
		User user = new User("test", "test", "test");
		
		userDao.addUser(user);
	}
	
	@Test
	public void addUser_positive() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		DataSource dataSource = Mockito.mock(DataSource.class);
		userDao.setDataSource(dataSource);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement statementAddUser = Mockito.mock(PreparedStatement.class);
		PreparedStatement statementGetUser = Mockito.mock(PreparedStatement.class);
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(UserQueries.addUser)).thenReturn(statementAddUser);
		Mockito.when(connection.prepareStatement(UserQueries.getUser)).thenReturn(statementGetUser);
		Mockito.when(statementGetUser.executeQuery()).thenReturn(resultSet);
		Mockito.when(resultSet.next()).thenReturn(false);
		Mockito.when(statementAddUser.executeUpdate()).thenReturn(1);
		
		User user = new User("test", "test", "test");
		
		userDao.addUser(user);
	}
	
	@Test
	public void addUser_positive_withoutMock() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		userDao.setDataSource(dataSourceDatabase);
		
		User user = new User("testLogin", "testPassword", "testFullName");
		
		userDao.addUser(user);
		
		user = userDao.getUser("testLogin");
		
		assertTrue(user.getLogin().equals("testLogin"));
		assertTrue(user.getUserPassword().equals("testPassword"));
		assertTrue(user.getFullName().equals("testFullName"));
	}
	
	@Test(expected = DataException.class)
	public void addUser_userExist_withoutMock() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		userDao.setDataSource(dataSourceDatabase);
		
		User user = new User("testLogin", "testPassword", "testFullName");
		
		userDao.addUser(user);
		userDao.addUser(user);
	}
	
	@Test
	public void getUser_userExist() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		DataSource dataSource = Mockito.mock(DataSource.class);
		userDao.setDataSource(dataSource);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement statementGetUser = Mockito.mock(PreparedStatement.class);
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(UserQueries.getUser)).thenReturn(statementGetUser);
		Mockito.when(statementGetUser.executeQuery()).thenReturn(resultSet);
		Mockito.when(resultSet.next()).thenReturn(true);
		Mockito.when(resultSet.getString("login")).thenReturn("testLogin");
		Mockito.when(resultSet.getString("password")).thenReturn("testPassword");
		Mockito.when(resultSet.getString("fullName")).thenReturn("testFullName");
				
		User user = userDao.getUser("login");
		
		assertTrue(user.getLogin().equals("testLogin"));
		assertTrue(user.getUserPassword().equals("testPassword"));
		assertTrue(user.getFullName().equals("testFullName"));
	}
	
	@Test
	public void getUser_userNotExist() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		DataSource dataSource = Mockito.mock(DataSource.class);
		userDao.setDataSource(dataSource);
		Connection connection = Mockito.mock(Connection.class);
		PreparedStatement statementGetUser = Mockito.mock(PreparedStatement.class);
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareStatement(UserQueries.getUser)).thenReturn(statementGetUser);
		Mockito.when(statementGetUser.executeQuery()).thenReturn(resultSet);
		Mockito.when(resultSet.next()).thenReturn(false);
		Mockito.when(resultSet.getString("login")).thenReturn("testLogin");
		Mockito.when(resultSet.getString("password")).thenReturn("testPassword");
		Mockito.when(resultSet.getString("fullName")).thenReturn("testFullName");
				
		User user = userDao.getUser("login");
		
		assertNull(user);
	}
	
	@Test
	public void getUser_userNotExist_WithoutMock() throws SQLException{
		UserDaoDatabase userDao = new UserDaoDatabase();
		userDao.setDataSource(dataSourceDatabase);
		
		User user = userDao.getUser("login");
		
		assertNull(user);
	}
}
