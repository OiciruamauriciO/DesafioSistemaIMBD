package cl.desafio.sistema.imbd.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.desafio.sistema.imbd.main.model.Rating;
import cl.desafio.sistema.imbd.main.repository.RatingRepository;

@Service("RatingService")
public class RatingService {

	@Autowired
	private RatingRepository ratingRepositoryInRatingService;
	
	public List<Rating> findAllRatings(){
		return (List<Rating>) ratingRepositoryInRatingService.findAll();
	}
	
	public Rating findRatingById(Long id) {
		
		Optional<Rating> optionalRating = ratingRepositoryInRatingService.findById(id);
		
		if(optionalRating.isPresent()) {
			return (Rating) optionalRating.get();
		}else {
			return null;	
		}
		
	}
	
	public Rating saveRating(Rating rating) {
		
		Rating localrating = new Rating();
		localrating = rating;
		
		if(rating!=null) {
			ratingRepositoryInRatingService.save(rating);
		}
		
		return localrating;
	}
}
