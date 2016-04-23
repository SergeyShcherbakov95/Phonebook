package shcherbakov.sergey.dao.contact;

import java.util.List;

import shcherbakov.sergey.model.Contact;

public interface ContactDao {
	public List<Contact> listContact(String login);
	
	public void addContact(Contact contact, String login);
	
	public void editContact(Contact contact);
	
	public Contact getContact(String idContact, String login);
	
	public void deleteContact(String idContact, String login);
	
	public List<Contact> listContactSearch(String surname, String name, String phone, String login);
}
