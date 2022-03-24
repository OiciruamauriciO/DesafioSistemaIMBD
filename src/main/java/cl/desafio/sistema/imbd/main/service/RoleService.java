package cl.desafio.sistema.imbd.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.desafio.sistema.imbd.main.model.Role;
import cl.desafio.sistema.imbd.main.repository.RoleRepository;

@Service("RoleService")
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepositoryInRoleService;
	
	public List<Role> findAll() {
		return roleRepositoryInRoleService.findAll();
	}

	public Optional<Role> findByRole_name(Role role) {
		
		Optional<Role> roleencontrado = roleRepositoryInRoleService.findById(role.getId());
				
		return roleencontrado;
	}	
}
