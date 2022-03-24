package cl.desafio.sistema.imbd.main.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cl.desafio.sistema.imbd.main.model.User;
import cl.desafio.sistema.imbd.main.service.UserService;

@Component("UserValidator")
public class UserValidator implements Validator{
	
	private final static Logger logger = LoggerFactory.getLogger(UserValidator.class);

	@Autowired
	public UserService userServiceInValidator; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
				
		logger.info("::: USERVALIDATOR ::: in validate :::");
		logger.info("::: USERVALIDATOR ::: in validate ::: Object object ::: " + object + " :::");
		logger.info("::: USERVALIDATOR ::: in validate ::: Errors errors ::: " + errors + " :::");

		User user = (User) object;
		logger.info("::: USERVALIDATOR ::: in validate ::: User user = (User) object;::: " + user.getUsername() + " :::");
		
		if(!user.getPasswordconfirmation().equals(user.getPassword())) {
			logger.info("::: USERVALIDATOR ::: in validate ::: contraseña y confirmación de contraseña no son iguales :::");
			errors.rejectValue("passwordconfirmation", "Match");
		}		
		
		User userCheck = userServiceInValidator.findUserByEmail(user.getEmail());
		logger.info("::: USERVALIDATOR ::: in validate ::: User userCheck ::: " + userCheck.getUsername() + " :::");
		
		if(userCheck!=null) {
			errors.rejectValue("email", "Found");
		}
		
		String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getEmail());
		
		if(!matcher.matches()) {
			errors.rejectValue("email", "Match");
		}		
		
	}
}
