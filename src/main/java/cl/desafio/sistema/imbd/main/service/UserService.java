package cl.desafio.sistema.imbd.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.desafio.sistema.imbd.main.model.Role;
import cl.desafio.sistema.imbd.main.model.User;
import cl.desafio.sistema.imbd.main.repository.RatingRepository;
import cl.desafio.sistema.imbd.main.repository.RoleRepository;
import cl.desafio.sistema.imbd.main.repository.UserRepository;
import lombok.Data;

@Data
@Service("UserService")
public class UserService {	
	
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepositoryInUserService;
	
	@Autowired
	private RoleRepository roleRepositoryInUserService;
	
	@Autowired
	private RatingRepository ratingRepositoryInUserService;
	
	@Primary
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncodeInUserService() {
        return new BCryptPasswordEncoder();
    }
			
	public void saveUserRole(User user) {	
		
		logger.info("::: USERSERVICE ::: in saveUserRole :::");
		
		if(user.getUsername().length()>=3) {
			
			if(user.getEmail().length()>=5) {
				
		        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		        Matcher mather = pattern.matcher(user.getEmail());

		        if (mather.find() == true) {
		        	
		        	logger.info("::: USERSERVICE ::: in saveUserRole ::: correo ingresado es valido");

					if(user.getPassword().equalsIgnoreCase(user.getPasswordconfirmation())) {
						
						User temporaluser = findUserByEmail(user.getEmail());		
						
						if(temporaluser.getEmail()==null) {
							
							logger.info("::: USERSERVICE ::: in saveUserRole ::: temporaluser ::: " + temporaluser.getEmail() + " :::");
							user.setPassword(bCryptPasswordEncodeInUserService().encode(user.getPassword()));
							user.setPasswordconfirmation(user.getPassword());
							List<Role> listarolesusuario = new ArrayList<Role>();
							Role roledeusuario = new Role();
							roledeusuario.setRole_name("USER");
							roledeusuario.setId((long)1);
							roleRepositoryInUserService.save(roledeusuario);
							listarolesusuario.add(roledeusuario);
							user.setRoles(listarolesusuario);
							
							logger.info("::: USERSERVICE ::: in saveUserRole ::: " + user.getUsername() + " - " + user.getEmail() + " - " + user.getPassword() + " - " + user.getPasswordconfirmation() + " :::");

							userRepositoryInUserService.save(user);			
						}else {
							logger.info("::: USERSERVICE ::: in saveUserRole ::: correo electrónico ya está almacenado en la base de datos, no es posible agregar al usuario con ese correo electrónico.");
						}				
						
					}else {
						logger.info("::: USERSERVICE ::: in saveUserRole ::: las contraseñas no coinciden, intente nuevamente :::");
					}
		        	
		        } else {
		        	logger.info("::: USERSERVICE ::: in saveUserRole ::: correo ingresado es invalido :::");
		        }
				
			}else {
				logger.info("::: USERSERVICE ::: in saveUserRole ::: nombre de usuario no es mayor igual a 5 de largo, intente nuevamente con un correo electrónico mas largo :::");
			}
			
		}else {
			logger.info("::: USERSERVICE ::: in saveUserRole ::: nombre de usuario no es mayor igual a 3 de largo, intente nuevamente con un nombre mas largo :::");
		}
		
	}
	
	public void saveAdminRole(User user) {
		
		logger.info("::: USERSERVICE ::: in saveAdminRole :::");
		
		if(user.getUsername().length()>=3) {
			
			if(user.getEmail().length()>=5) {
				
		        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		        Matcher mather = pattern.matcher(user.getEmail());

		        if (mather.find() == true) {
		        	
		        	logger.info("::: USERSERVICE ::: in saveAdminRole ::: correo ingresado es valido");

					if(user.getPassword().equalsIgnoreCase(user.getPasswordconfirmation())) {
						
						User temporaluser = findUserByEmail(user.getEmail());		
						
						if(temporaluser.getEmail()==null) {
							
							logger.info("::: USERSERVICE ::: in saveAdminRole ::: temporaluser ::: " + temporaluser.getEmail() + " :::");
							user.setPassword(bCryptPasswordEncodeInUserService().encode(user.getPassword()));
							user.setPasswordconfirmation(user.getPassword());
							List<Role> listarolesusuario = new ArrayList<Role>();
							Role roledeusuario = new Role();
							roledeusuario.setRole_name("ADMIN");
							roledeusuario.setId((long)2);
							roleRepositoryInUserService.save(roledeusuario);
							listarolesusuario.add(roledeusuario);
							user.setRoles(listarolesusuario);
							
							logger.info("::: USERSERVICE ::: in saveAdminRole ::: " + user.getUsername() + " - " + user.getEmail() + " - " + user.getPassword() + " - " + user.getPasswordconfirmation() + " :::");

							userRepositoryInUserService.save(user);			
						}else {
							logger.info("::: USERSERVICE ::: in saveAdminRole ::: correo electrónico ya está almacenado en la base de datos, no es posible agregar al usuario con ese correo electrónico :::");
						}					
						
					}else {
						logger.info("::: USERSERVICE ::: in saveAdminRole ::: las contraseñas no coinciden, intente nuevamente :::");
					}
		        	
		        } else {
		        	logger.info("::: USERSERVICE ::: in saveAdminRole ::: correo ingresado es invalido :::");
		        }
				
			}else {
				logger.info("::: USERSERVICE ::: in saveAdminRole ::: nombre de usuario no es mayor igual a 5 de largo, intente nuevamente con un correo electrónico mas largo :::");
			}
			
		}else {
			logger.info("::: USERSERVICE ::: in saveAdminRole ::: nombre de usuario no es mayor igual a 3 de largo, intente nuevamente con un nombre mas largo :::");
		}
		
	}
	
	public User findUserByEmail(String email) {	
		
		logger.info("::: USERSERVICE ::: in findUserByEmail :::");
		logger.info("::: USERSERVICE ::: in findUserByEmail ::: String email ::: " + email + " :::");
		
		List<User> listalocaldeusuarios = (List<User>) userRepositoryInUserService.findAll();
		logger.info("::: USERSERVICE ::: in findUserByEmail ::: listalocaldeusuarios.size() ::: " + listalocaldeusuarios.size() + " :::");
		User user = new User();
		
		boolean usuarioencontrado = false;
		int contadordeusuariosrecorridos = 0;
		
		for(User usuario: listalocaldeusuarios) {
			
			logger.info("::: USERSERVICE ::: in findUserByEmail ::: " + usuario.getUsername() + " - " + usuario.getEmail() + " :::");
			
			if(usuario.getEmail().equalsIgnoreCase(email)) {
				
				user = auxFindUser(usuario);
				usuarioencontrado=true;
				logger.info("::: USERSERVICE ::: findUserByEmail ::: usuario ya existe con ese correo electrónico en la base de datos, búsqueda sin resultados :::");		
				break;
			}else {
				usuarioencontrado=false;
				//logger.info("::: USERSERVICE ::: findUserByEmail ::: no hay usuario con ese correo electrónico en la base de datos, búsqueda sin resultados :::");
			}
			
			contadordeusuariosrecorridos+=1;
		}
		
		if(usuarioencontrado==false && contadordeusuariosrecorridos==listalocaldeusuarios.size()) {
			logger.info("::: USERSERVICE [1]::: findUserByEmail ::: no hay usuario con ese correo electrónico en la base de datos :::");
		}
		
		return user;			
	}
	
	public User auxFindUser(User usuario) {
		
		Optional<User> optionalUser = userRepositoryInUserService.findById(usuario.getId());
		
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			return null;
		}
	}
	
	public User findByUserName(String username) {	
		
		logger.info("::: USERSERVICE ::: in findByUserName :::");
		
		List<User> listalocaldeusuarios = (List<User>) userRepositoryInUserService.findAll();
		User user = new User();
		
		for(User usuario: listalocaldeusuarios) {
			
			if(usuario.getUsername().equalsIgnoreCase(username)) {
				
				user = auxFindUser(usuario);
				logger.info("::: USERSERVICE ::: findUserByEmail ::: user ::: " + user.getUsername() + " ::: username (string) ::: " + username);
				
				break;
			}
		}
		
		return user;
	}
	
	public Optional<User> findUserById(Long id) {
		logger.info("::: USERSERVICE ::: in findUserById :::");
		logger.info("::: USERSERVICE ::: findUserById - " + userRepositoryInUserService.findById(id) + ":::");
		return userRepositoryInUserService.findById(id);
	}
	
	public List<User> findAllUsers(){
		logger.info("::: USERSERVICE ::: in findAllUsers :::");
		return (List<User>) userRepositoryInUserService.findAll();
	}



}
