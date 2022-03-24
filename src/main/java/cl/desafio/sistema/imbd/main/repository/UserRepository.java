package cl.desafio.sistema.imbd.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.desafio.sistema.imbd.main.model.User;

@Transactional
@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long>{
}
