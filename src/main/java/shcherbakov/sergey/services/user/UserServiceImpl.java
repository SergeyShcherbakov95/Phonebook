package shcherbakov.sergey.services.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import shcherbakov.sergey.dao.user.UserDao;
import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.validator.UserValidator;

@Component("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	public UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Autowired
	public UserValidator userValidator;
	
	public void setUserValidator(UserValidator userValidator){
		this.userValidator = userValidator;
	}

	@Transactional
	@Override
	public void addUser(User user) {
		if(!this.userValidator.checkLogin(user.getLogin()))
			throw new ValidatorException("Wrong login");
		
		if(!this.userValidator.checkPassword(user.getUserPassword()))
			throw new ValidatorException("Wrong password");
		
		if(!this.userValidator.checkFullName(user.getFullName()))
			throw new ValidatorException("Wrong full name");
		
		userDao.addUser(user);
	}

}
