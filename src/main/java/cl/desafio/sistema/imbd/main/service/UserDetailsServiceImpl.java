package cl.desafio.sistema.imbd.main.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.desafio.sistema.imbd.main.model.Role;
import cl.desafio.sistema.imbd.main.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserService userServiceInUserDetailsServiceImpl;
	
	/*
	@Autowired
	private UserRepository userRepositoryInUserDetailsServiceImpl;	
	*/
	
	@Transactional
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.info("::: USERDETAILSSERVICEIMPL ::: in loadUserByUsername :::");
		
		User user = userServiceInUserDetailsServiceImpl.findUserByEmail(username);
		logger.info("::: USERDETAILSSERVICEIMPL ::: in loadUserByUsername ::: userServiceInUserDetailsServiceImpl.findUserByEmail(username) ::: " + user.getUsername() + " :::");
		/*
		List<User> userbyrepositorylist = (List<User>) userRepositoryInUserDetailsServiceImpl.findAll();
		logger.info("::: USERDETAILSSERVICEIMPL ::: in loadUserByUsername ::: userbyrepositorylist.get(0).getUsername() ::: " + userbyrepositorylist.get(0).getUsername() + " :::");
		*/
		
		if(user == null) {
			logger.info("::: USERDETAILSSERVICEIMPL ::: Usuario no encontrado :::");
			throw new UsernameNotFoundException("::: Usuario no encontrado :::");
		}else {
			logger.info("::: USERDETAILSSERVICEIMPL ::: in loadUserByUsername ::: user.getEmail() ::: " + user.getEmail() + " :::");
			logger.info("::: USERDETAILSSERVICEIMPL ::: in loadUserByUsername ::: user.getPassword() ::: " + user.getPassword() + " :::");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(user));
	}
	
	private List<GrantedAuthority> getAuthorities(User user){
		
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		
		logger.info("::: USERDETAILSSERVICEIMPL ::: in getAuthorities :::");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role: user.getRoles()) {
			
			logger.info("::: USERDETAILSSERVICEIMPL ::: in getAuthorities ::: role ::: " + role.getRole_name() + " ::: " + role.getId());
			
			//GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRole_name());
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole_name());
			authorities.add(grantedAuthority);
		}
		
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		logger.info(" ");		
		
		return authorities;		
	}
	
	
}
