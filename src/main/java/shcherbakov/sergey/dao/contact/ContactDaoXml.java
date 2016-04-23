package shcherbakov.sergey.dao.contact;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;

import shcherbakov.sergey.config.XmlProperties;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.model.Users;

public class ContactDaoXml implements ContactDao{
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
	public List<Contact> listContact(String login) {
		List<Contact> contacts = new LinkedList<>();
		
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin())){
						contacts = userTemp.getContacts();
						break;
					}
			}
				
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public void addContact(Contact contact, String login) {
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			User user = null;
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin()))
						user = userTemp;
			}
			
			if(user == null)
				return;
			
			int maxId = 0;
			if(user.getContacts() != null){
				for(Contact contactTemp : user.getContacts()){
					int curId = Integer.valueOf(contactTemp.getIdContact());
					if(curId > maxId)
						maxId = curId;
				}
			}
			
			contact.setIdContact("" + (maxId + 1));

			user.addContact(contact);
			
			marshaller.marshal(users, new File(xmlProperties.getPathXml()));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editContact(Contact contact) {
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(userTemp.getContacts() != null)
						for(Contact contactTemp : userTemp.getContacts())
							if(contactTemp.getIdContact().equals(contact.getIdContact())){
								contactTemp.setSurname(contact.getSurname());
								contactTemp.setName(contact.getName());
								contactTemp.setPatronymic(contact.getPatronymic());
								contactTemp.setMobilePhone(contact.getMobilePhone());
								contactTemp.setHomePhone(contact.getHomePhone());
								contactTemp.setAddress(contact.getAddress());
								contactTemp.setEmail(contact.getEmail());
							}
			}
			
			marshaller.marshal(users, new File(xmlProperties.getPathXml()));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Contact getContact(String idContact, String login) {
		Contact contact = null;
		
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin()))
						if(userTemp.getContacts() != null)
							for(Contact contactTemp : userTemp.getContacts())
								if(contactTemp.getIdContact().equals(idContact))
									contact = contactTemp;
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contact;
	}

	@Override
	public void deleteContact(String idContact, String login) {
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin()))
						if(userTemp.getContacts() != null)
							for(Contact contact : userTemp.getContacts())
								if(idContact.equals(contact.getIdContact())){
										userTemp.getContacts().remove(contact);
										break;
								}
			}
			
			marshaller.marshal(users, new File(xmlProperties.getPathXml()));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Contact> listContactSearch(String surname, String name, String phone, String login) {
		List<Contact> contacts = new LinkedList<>();
		List<Contact> contactsTemp = new LinkedList<>();
		
		try {
			Users users = (Users)unmarshaller.unmarshal(new File(xmlProperties.getPathXml()));
			
			if(users.getUsers() != null){
				for(User userTemp : users.getUsers())
					if(login.equals(userTemp.getLogin())){
						contactsTemp = userTemp.getContacts();
						break;
					}
			}
			
			Pattern surnamePattern = Pattern.compile("^[\\W\\w]*" + surname + "[\\W\\w]*$");
			Pattern namePattern = Pattern.compile("^[\\W\\w]*" + name + "[\\W\\w]*$");
			Pattern phonePattern = Pattern.compile("^[\\W\\w]*" + phone + "[\\W\\w]*$");
			
			for(Contact contact : contactsTemp){
				Matcher surnameMatcher = surnamePattern.matcher(contact.getSurname());
				Matcher nameMatcher = namePattern.matcher(contact.getName());
				Matcher mobilePhoneMatcher = phonePattern.matcher(contact.getMobilePhone());
				Matcher homePhoneMatcher = phonePattern.matcher(contact.getHomePhone());
				
				boolean isSurnameCorrect = surname.equals("")?true:surnameMatcher.matches();
				boolean isNameCorrect = name.equals("")?true:nameMatcher.matches();
				boolean isMobilePhoneCorrect = phone.equals("")?true:mobilePhoneMatcher.matches();
				boolean isHomePhoneCorrect = phone.equals("")?true:homePhoneMatcher.matches();
				
				if(isSurnameCorrect && isNameCorrect && (isMobilePhoneCorrect || isHomePhoneCorrect)){
					contacts.add(contact);
				}
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contacts;
	}

}
