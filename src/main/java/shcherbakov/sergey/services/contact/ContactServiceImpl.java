package shcherbakov.sergey.services.contact;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shcherbakov.sergey.dao.contact.ContactDao;
import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.validator.ContactValidator;

@Component("contactService")
public class ContactServiceImpl implements ContactService{
	@Autowired
	public ContactDao contactDao;
	
	public void setContactDao(ContactDao contactDao){
		this.contactDao = contactDao;
	}
	
	@Autowired
	public ContactValidator contactValidator;
	
	public void setContactValidator(ContactValidator contactValidator){
		this.contactValidator = contactValidator;
	}
	
	@Transactional
	@Override
	public List<Contact> listContact(String login) {
		return this.contactDao.listContact(login);
	}

	@Transactional
	@Override
	public void addContact(Contact contact, String login) {
		if(!this.contactValidator.checkSurname(contact.getSurname()))
			throw new ValidatorException("Wrong surname");
		
		if(!this.contactValidator.checkName(contact.getName()))
			throw new ValidatorException("Wrong name");
		
		if(!this.contactValidator.checkPatronymic(contact.getPatronymic()))
			throw new ValidatorException("Wrong patronymic");
		
		if(!this.contactValidator.checkPhone(contact.getMobilePhone()))
			throw new ValidatorException("Wrong mobile phone");
		
		if(contact.getHomePhone() != "" && !this.contactValidator.checkPhone(contact.getHomePhone()))
			throw new ValidatorException("Wrong home phone");
		
		if(contact.getEmail() != "" && !this.contactValidator.checkEmail(contact.getEmail()))
			throw new ValidatorException("Wrong email");
		
		this.contactDao.addContact(contact, login);
	}

	@Transactional
	@Override
	public void editContact(Contact contact) {
		if(!this.contactValidator.checkSurname(contact.getSurname()))
			throw new ValidatorException("Wrong surname");
		
		if(!this.contactValidator.checkName(contact.getName()))
			throw new ValidatorException("Wrong name");
		
		if(!this.contactValidator.checkPatronymic(contact.getPatronymic()))
			throw new ValidatorException("Wrong patronymic");
		
		if(!this.contactValidator.checkPhone(contact.getMobilePhone()))
			throw new ValidatorException("Wrong mobile phone");
		
		if(contact.getHomePhone() != "" && !this.contactValidator.checkPhone(contact.getHomePhone()))
			throw new ValidatorException("Wrong home phone");
		
		if(contact.getEmail() != "" && !this.contactValidator.checkEmail(contact.getEmail()))
			throw new ValidatorException("Wrong email");
		
		this.contactDao.editContact(contact);		
	}

	@Transactional
	@Override
	public Contact getContact(String idContact, String login) {
		return this.contactDao.getContact(idContact, login);
	}

	@Transactional
	@Override
	public void deleteContact(String idContact, String login) {
		this.contactDao.deleteContact(idContact, login);
	}

	@Transactional
	@Override
	public List<Contact> listContactSearch(String surname, String name, String phone, String login) {
		return this.contactDao.listContactSearch(surname, name, phone, login);
	}

}
