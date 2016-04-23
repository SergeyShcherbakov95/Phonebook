package shcherbakov.sergey.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.query.UserQueries;

@Repository
public class UserDaoDatabase implements UserDao{
	@Autowired
	public DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public void addUser(User user) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UserQueries.addUser);
				PreparedStatement statementGetUser = connection.prepareStatement(UserQueries.getUser)){
			
			statementGetUser.setString(1, user.getLogin());
			
			if(statementGetUser.executeQuery().next())
				throw new DataException("Login is used");
			System.out.println(user.getLogin() + user.getUserPassword());
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getUserPassword());
			statement.setString(3, user.getFullName());
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String login) {
		User user = null;
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UserQueries.getUser)){
			
			statement.setString(1, login);
			
			ResultSet resSet = statement.executeQuery();

			if(resSet.next()){
				String userLogin = resSet.getString("login");
				String password = resSet.getString("password");
				String fullName = resSet.getString("fullName");

				user = new User(userLogin, password, fullName);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return user;
	}
}
