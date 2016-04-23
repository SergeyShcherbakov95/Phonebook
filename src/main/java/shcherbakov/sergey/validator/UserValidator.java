package shcherbakov.sergey.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("userValidator")
public class UserValidator {
	
	public boolean checkLogin(String login){
		Pattern pattern = Pattern.compile(ValidatorValues.checkUserLogin);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}

	public boolean checkPassword(String password){
		Pattern pattern = Pattern.compile(ValidatorValues.checkUserPassword);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public boolean checkFullName(String fullName){
		Pattern pattern = Pattern.compile(ValidatorValues.checkUserFullName);
		Matcher matcher = pattern.matcher(fullName);
		return matcher.matches();
	}
}
