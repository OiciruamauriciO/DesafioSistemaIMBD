package cl.desafio.sistema.imbd.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.desafio.sistema.imbd.main.model.Rating;

@Transactional
@Repository("RatingRepository")
public interface RatingRepository extends CrudRepository<Rating, Long>{
	
	public List<Rating> findAll();	
	public Rating findRatingById(Long id);	

}
