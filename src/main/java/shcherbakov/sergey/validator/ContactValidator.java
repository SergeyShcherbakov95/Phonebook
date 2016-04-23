package shcherbakov.sergey.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component("contactValidator")
public class ContactValidator {
	
	public boolean checkSurname(String surname){
		Pattern pattern = Pattern.compile(ValidatorValues.checkContactSurname);
		Matcher matcher = pattern.matcher(surname);
		return matcher.matches();
	}
	
	public boolean checkName(String Name){
		Pattern pattern = Pattern.compile(ValidatorValues.checkContactName);
		Matcher matcher = pattern.matcher(Name);
		return matcher.matches();
	}
	
	public boolean checkPatronymic(String patronymic){
		Pattern pattern = Pattern.compile(ValidatorValues.checkContactPatronymic);
		Matcher matcher = pattern.matcher(patronymic);
		return matcher.matches();
	}
	
	public boolean checkPhone(String phone){
		Pattern pattern = Pattern.compile(ValidatorValues.checkContactPhone);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	public boolean checkEmail(String email){
		Pattern pattern = Pattern.compile(ValidatorValues.checkContactEmail);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
