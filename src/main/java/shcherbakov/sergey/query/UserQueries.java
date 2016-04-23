package shcherbakov.sergey.query;

final public class UserQueries {
	private UserQueries(){
		
	}
	
	public static String addUser = "INSERT INTO Users(login, password, fullName) VALUES(?, ?, ?)";
	public static String getUser = "SELECT * FROM Users WHERE Users.login = ?";
}
