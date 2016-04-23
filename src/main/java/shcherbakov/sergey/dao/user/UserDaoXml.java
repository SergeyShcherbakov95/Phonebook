package shcherbakov.sergey.dao.user;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;

import shcherbakov.sergey.config.XmlProperties;
import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

public class UserDaoXml implements UserDao{
	@Autowired
	private XmlProperties xmlProperties;
	
	public void setXmlProperties(XmlProperties xmlProperties){
		this.xmlProperties = xmlProperties;
	}
	
	@Autowired
	private Marshaller marshaller;
	
	public void setMarshaller(Marshaller marshaller){
		this.marshaller = marshaller;
	}
	
	@Autowired
	private Unmarshaller unmarshaller;
	
	public void setUnmarshaller(Unmarshaller unmarshaller){
		this.unmarshaller = unmarshaller;
	}
	
	@Override
	public void addUser(User user) {
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			boolean isUserExist = false;
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(user.getLogin().equals(userTemp.getLogin()))
						isUserExist = true;
			}
				
			if(!isUserExist){
				users.addUser(user);
				marshaller.marshal(users, new File(xmlProperties.getPathXml()));
			} else
				throw new DataException("Login is used");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(String login) {
		User user = null;
		
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin())){
						String userLogin = userTemp.getLogin();
						String password = userTemp.getUserPassword();
						String fullName = userTemp.getFullName();

						user = new User(userLogin, password, fullName);
					}
	
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
