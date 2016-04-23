package shcherbakov.sergey.dao.user;

import shcherbakov.sergey.model.User;

public interface UserDao {
	public void addUser(User user);
	
	public User getUser(String login);
}
