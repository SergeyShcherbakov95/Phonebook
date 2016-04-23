package shcherbakov.sergey.validator;

final public class ValidatorValues {
	private ValidatorValues(){
		
	}
	
	//User values
	public static String checkUserLogin = "^[A-Za-z]{3,}$";
	public static String checkUserPassword = "^[\\w\\W]{5,}$";
	public static String checkUserFullName = "^[\\w\\W]{5,}$";
	
	//Contact values
	public static String checkContactSurname = "^[\\w\\W]{4,}$";
	public static String checkContactName = "^[\\w\\W]{4,}$";
	public static String checkContactPatronymic = "^[\\w\\W]{4,}$";
	public static String checkContactPhone = "^\\+380\\(\\d{2}\\)\\d{7}$";
	public static String checkContactEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
