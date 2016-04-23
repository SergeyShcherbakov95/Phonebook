package shcherbakov.sergey.query;

final public class ContactQueries {
	private ContactQueries(){
		//NOP
	}
	
	public static String listContacts = "SELECT * FROM Contacts, Users WHERE Users.login = ? AND Contacts.idUser = Users.idUser";
	
	public static String addContact = "INSERT INTO Contacts(surname, name, patronymic, mobilePhone, homePhone, address, email, idUser) " +
											"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static String editContact = "UPDATE Contacts "
										+ "SET surname = ?, name = ?, patronymic = ?, mobilePhone = ?, homePhone = ?, address = ?, email = ?"
										+ "WHERE idContact = ?";
	
	public static String getContact = "SELECT * FROM Contacts, Users WHERE idContact = ? AND Contacts.idUser = Users.idUser AND Users.login = ?";

	public static String deleteContact = "DELETE Contacts FROM Contacts, Users WHERE idContact = ? AND Contacts.idUser = Users.idUser AND Users.login = ?";
}
