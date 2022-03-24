package cl.desafio.sistema.imbd.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.desafio.sistema.imbd.main.model.Role;

@Transactional
@Repository("RoleRepository")
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	public List<Role> findAll();
	public Optional<Role> findById(Long role_id);
}
