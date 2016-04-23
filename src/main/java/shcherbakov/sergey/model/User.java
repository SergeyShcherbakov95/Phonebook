package shcherbakov.sergey.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "user" )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"login", "userPassword", "fullName", "contacts"})
public class User {
	private String login;
	@XmlElement( name = "password" )
	private String userPassword;
	private String fullName;
	@XmlElement( name = "contacts" )
	private List<Contact> contacts;
	
	public User(){
		//NOP
	}
	
	public User(String login, String userPassword, String fullName) {
		super();
		this.login = login;
		this.userPassword = userPassword;
		this.fullName = fullName;
	}
	
	public String getLogin() {
		return login;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getFullName() {
		return fullName;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		if(contacts == null)
			contacts = new ArrayList<>();
		this.contacts.add(contact);
	}
}
