package com.stuff.stuffapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stuff.stuffapp.domain.User;

@Component
public class NewUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if (user.getName().isEmpty()) {
			errors.rejectValue("name", "validation.error.addnewuser.name.empty");
		}
		if (user.getPassword().length()<6) {
			errors.rejectValue("password", "validation.error.addnewuser.password.length");
		}
		if (user.getFirstname().isEmpty()) {
			errors.rejectValue("firstname", "validation.error.addnewuser.firstname.empty");
		}
		if (user.getLastname().isEmpty()) {
			errors.rejectValue("lastname", "validation.error.addnewuser.lastname.empty");
		}
		
	}

}
