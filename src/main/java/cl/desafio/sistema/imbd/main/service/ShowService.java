package cl.desafio.sistema.imbd.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.desafio.sistema.imbd.main.model.Show;
import cl.desafio.sistema.imbd.main.repository.ShowRepository;

import java.util.List;
import java.util.Optional;

@Service("ShowService")	
public class ShowService {
	
	private final static Logger logger = LoggerFactory.getLogger(ShowService.class);

	@Autowired
	private ShowRepository showRepositoryInShowService;
	
	public Show findById(Long id) {
		
		logger.info("::: SHOWSERVICE ::: in findById :::");
		logger.info("::: SHOWSERVICE ::: in findById ::: Long id::: " + id + " :::");

		Optional<Show> optionalShow = showRepositoryInShowService.findById(id);
		
		if(optionalShow.isPresent()) {
			return optionalShow.get();
		}else {
			return null;
		}
	}
	
	public List<Show> findAllShows(){
		return (List<Show>) showRepositoryInShowService.findAll();
	}
	
	public void saveShow(Show show) {
		
		logger.info("::: SHOWSERVICE ::: in findAllShows :::");
		logger.info("::: SHOWSERVICE ::: in findAllShows ::: show id ::: " + show.getId() + " :::");

		showRepositoryInShowService.save(show);
	}
	
	public void updateShow(Show show) {
		
		logger.info("::: SHOWSERVICE ::: in updateShow :::");
		logger.info("::: SHOWSERVICE ::: in updateShow ::: show id ::: " + show.getId() + " :::");
		
		showRepositoryInShowService.save(show);
	}
	
	public void deleteShow(Show show) {		
		showRepositoryInShowService.delete(show);
	}
	
	/*método custom para inicializar un show nuevo que se inserta en el controlador ShowController*/
	public long initIdNewShow(Show show) {
		List<Show> showlist = (List<Show>) showRepositoryInShowService.findAll();
		long id = 0;
		for(Show auxshow: showlist) {
			logger.info("::: SHOWSERVICE ::: in initShow ::: auxshow.getId() ::: " + auxshow.getId() + " :::");
			id = auxshow.getId();
		}
		//id+=1;
		logger.info("::: SHOWSERVICE ::: in initShow ::: id ::: " + id + " :::");
		
		return id;		
	}

}
