package cl.desafio.sistema.imbd.main.controller;

import java.security.Principal;

//import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.desafio.sistema.imbd.main.model.User;
import cl.desafio.sistema.imbd.main.service.UserService;
import cl.desafio.sistema.imbd.main.validator.UserValidator;

@Controller("UserController")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserValidator userValidatorInUserController;
		
	@Autowired
	private UserService userServiceInUserController;	
		
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout, Model model, 
			@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		logger.info("::: IN USERCONTROLLER ::: login :::");
		if(error != null) {
			model.addAttribute("errorMessage", "Inténtelo nuevamente, credenciales incorrectas");
			logger.info("::: IN USERCONTROLLER ::: errorMessage -> Inténtelo nuevamente, credenciales incorrectas ::: ");
			
		}
		
		if(logout != null) {
			model.addAttribute("logoutMessage", "Ingreso exitoso");
			logger.info("::: IN USERCONTROLLER ::: Ingreso exitoso :::");
		}
		
		return "login";
	}
	
	@GetMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
		
		logger.info("::: IN USERCONTROLLER ::: registerForm - GET :::");
		logger.info("::: IN USERCONTROLLER ::: registerForm - GET ::: @Valid @ModelAttribute(\"user\") User user [1] ::: " + user.getUsername() + " :::");
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registration(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		userValidatorInUserController.validate(user, result);
		
		if(result.hasErrors()) {
			logger.info("::: IN USERCONTROLLER ::: result.hasErrors() ::: " + result.hasErrors() + " :::");
			userServiceInUserController.saveUserRole(user);
			return "redirect:/login";
		}else {
			logger.info("::: IN USERCONTROLLER ::: usuario guardado en base de datos -> redirigido al login, intente ingresar con correo y contraseña del nuevo usuario :::");
			return "registration";
		}
	}
	
	@RequestMapping(value= {"/", "/home"})
	public String home(Principal principal) {
		
		logger.info("::: IN USERCONTROLLER ::: home :::");
		
		return "redirect:/shows";
	}
	
}
