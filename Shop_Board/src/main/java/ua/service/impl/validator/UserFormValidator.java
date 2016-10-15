package ua.service.impl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.UserForm;
import ua.service.UserService;

public class UserFormValidator implements Validator{
	
	private final UserService userService;
	private static final Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
	private static final Pattern t = Pattern.compile("^[0-9\\-]*$");
	public UserFormValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm form = (UserForm) target;
		if(form.getId()==0)if(userService.findByLogin(form.getLogin())!=null){
			errors.rejectValue("login", "", "Login already exists");
		}
		Matcher mailFormat = p.matcher(form.getMail());
		Matcher phoneFormat = t.matcher(form.getTelephoneNumber());
		if(!mailFormat.matches()){
			errors.rejectValue("mail", "", "Bad mail format!");
		}
		if(!phoneFormat.matches()){
			errors.rejectValue("telephoneNumber","", "Bad phone format");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "", "Can`t be empty");
	}
}
