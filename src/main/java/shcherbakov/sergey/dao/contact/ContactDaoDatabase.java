package shcherbakov.sergey.dao.contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.query.ContactQueries;
import shcherbakov.sergey.query.UserQueries;

@Repository
public class ContactDaoDatabase implements ContactDao{
	@Autowired
	public DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Contact> listContact(String login) {
		List<Contact> contacts = new LinkedList<>();
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(ContactQueries.listContacts)){
			
			statement.setString(1, login);
			
			ResultSet resSet = statement.executeQuery();

			while(resSet.next()){
				String idContact = resSet.getString("idContact");
				String surname = resSet.getString("surname");
				String name = resSet.getString("name");
				String patronymic = resSet.getString("patronymic");
				String mobilePhone = resSet.getString("mobilePhone");
				String homePhone = resSet.getString("homePhone");
				String address = resSet.getString("address");
				String email = resSet.getString("email");

				Contact contact = new Contact(idContact, surname, name, patronymic, mobilePhone, homePhone, address, email);
				
				contacts.add(contact);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return contacts;
	}

	@Override
	public void addContact(Contact contact, String login) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statementGetUser = connection.prepareStatement(UserQueries.getUser);
				PreparedStatement statementAddContact = connection.prepareStatement(ContactQueries.addContact)){
					
			//Get idUser by login
			String idUser = "";
			statementGetUser.setString(1, login);
			
			ResultSet resSet = statementGetUser.executeQuery();

			if(resSet.next())
				idUser = resSet.getString("idUser");
			
			//Insert new Contact
			statementAddContact.setString(1, contact.getSurname());
			statementAddContact.setString(2, contact.getName());
			statementAddContact.setString(3, contact.getPatronymic());
			statementAddContact.setString(4, contact.getMobilePhone());
			statementAddContact.setString(5, contact.getHomePhone());
			statementAddContact.setString(6, contact.getAddress());
			statementAddContact.setString(7, contact.getEmail());
			statementAddContact.setString(8, idUser);
			
			statementAddContact.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void editContact(Contact contact) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(ContactQueries.editContact)){
					
			statement.setString(1, contact.getSurname());
			statement.setString(2, contact.getName());
			statement.setString(3, contact.getPatronymic());
			statement.setString(4, contact.getMobilePhone());
			statement.setString(5, contact.getHomePhone());
			statement.setString(6, contact.getAddress());
			statement.setString(7, contact.getEmail());
			statement.setString(8, contact.getIdContact());
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Contact getContact(String idContact, String login) {
		Contact contact = null;
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(ContactQueries.getContact)){
					
			statement.setString(1, idContact);
			statement.setString(2, login);
			
			ResultSet resSet = statement.executeQuery();
			
			if(resSet.next()){
				String surname = resSet.getString("surname");
				String name = resSet.getString("name");
				String patronymic = resSet.getString("patronymic");
				String mobilePhone = resSet.getString("mobilePhone");
				String homePhone = resSet.getString("homePhone");
				String address = resSet.getString("address");
				String email = resSet.getString("email");
				
				contact = new Contact(idContact, surname, name, patronymic, mobilePhone, homePhone, address, email);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return contact;
	}

	@Override
	public void deleteContact(String idContact, String login) {
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(ContactQueries.deleteContact)){
					
			statement.setString(1, idContact);
			statement.setString(2, login);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Contact> listContactSearch(String surnameSearch, String nameSearch, String phoneSearch, String login) {
		List<Contact> contacts = new LinkedList<>();
		
		String query = ContactQueries.listContacts;
		
		if(surnameSearch != "" || nameSearch != "" || phoneSearch != ""){
			query += " AND (";
			if(surnameSearch != "")
				query += "surname LIKE '%" + surnameSearch + "%' AND ";
			if(nameSearch != "")
				query += "name LIKE '%" + nameSearch + "%' AND ";
			if(phoneSearch != ""){
				query += "(mobilePhone LIKE '%" + phoneSearch + "%' OR homePhone LIKE '%" + phoneSearch + "%' ) AND ";
			}
			query += "1=1)";
		}
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, login);
			
			ResultSet resSet = statement.executeQuery();
			
			while(resSet.next()){
				String idContact = resSet.getString("idContact");
				String surname = resSet.getString("surname");
				String name = resSet.getString("name");
				String patronymic = resSet.getString("patronymic");
				String mobilePhone = resSet.getString("mobilePhone");
				String homePhone = resSet.getString("homePhone");
				String address = resSet.getString("address");
				String email = resSet.getString("email");

				Contact contact = new Contact(idContact, surname, name, patronymic, mobilePhone, homePhone, address, email);
				
				contacts.add(contact);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return contacts;
	}

}
