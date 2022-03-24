package cl.desafio.sistema.imbd.main.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import cl.desafio.sistema.imbd.main.model.Rating;
import cl.desafio.sistema.imbd.main.model.Show;
import cl.desafio.sistema.imbd.main.model.User;
import cl.desafio.sistema.imbd.main.service.RatingService;
import cl.desafio.sistema.imbd.main.service.ShowService;
import cl.desafio.sistema.imbd.main.service.UserService;
import lombok.Data;

@Data
@Controller
@RequestMapping("/shows")
public class ShowController {
	
	private final static Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	private boolean inShowId=false;

	@Autowired
	private ShowService showServiceInShowController;
	
	@Autowired
	private UserService userServiceInShowController;
	
	@Autowired
	private RatingService ratingServiceInShowController;
	
	@PostMapping(value="/{id}/add")
	public String addRating(@Valid @ModelAttribute("addRating") Rating rating, BindingResult result, @PathVariable("id") Long id, Principal principal) {
		
		logger.info("::: IN SHOWCONTROLLER ::: addRating :::");
		
		if(result.hasErrors()) {
			
			return "redirect:/shows/" + id;
		
		}else {
			
			String email = principal.getName();			
			User currentUser = userServiceInShowController.findUserByEmail(email);			
			Show currentShow = showServiceInShowController.findById(id);	
			rating.setUser(currentUser);				
			ratingServiceInShowController.saveRating(rating);			
			currentShow.setRatings(ratingServiceInShowController.findAllRatings());	
			showServiceInShowController.updateShow(currentShow);
			
			return "redirect:/shows/" + id;
		}
		
	}
	
	@GetMapping(value="/{id}")
	public String getShow(@PathVariable("id") Long id, Model model, Principal principal) {
		
		logger.info("::: IN SHOWCONTROLLER ::: getShow :::");
		
		Show show = showServiceInShowController.findById(id);
		
		User creatorShow = (User) show.getCreatorShow();
		String email = principal.getName();
		User currentUser = userServiceInShowController.findUserByEmail(email);	
		List<Rating> showRatings = show.getRatings();
		Rating newRating = new Rating();
		
		inShowId = true;
		this.setInShowId(inShowId);
		
		model.addAttribute("addRating", newRating);
		model.addAttribute("showRatings", showRatings);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("show", show);
		model.addAttribute("creatorShow", creatorShow);		
		model.addAttribute("inShowId", this.isInShowId());
				
		return "show";
	}
	
	@GetMapping("")
	public String homePage(Principal principal, Model model) {
		
		logger.info("::: IN SHOWCONTROLLER ::: homePage :::");
		
		String email = principal.getName();
		User currentUser = userServiceInShowController.findUserByEmail(email);	
		List<Show> allShows = showServiceInShowController.findAllShows();
		model.addAttribute("allShows", allShows);
		model.addAttribute("currentUser", currentUser);
		this.inShowId=false;
		this.setInShowId(this.inShowId);
		model.addAttribute("inShowId",this.isInShowId());
		
		return "home";
	}
	
	@GetMapping("/new")
	public String newShow(Model model) {
		
		this.inShowId=false;
		this.setInShowId(this.inShowId);
		
		Show newShow = new Show();
		model.addAttribute("newShow", newShow);
		model.addAttribute("inShowId", this.isInShowId());
		
		return "new";
	}
	
	@PostMapping(value="/create")
	public String createShow(@ModelAttribute("newShow") @Valid Show show, BindingResult result, Principal principal) { 
		
		logger.info("::: IN SHOWCONTROLLER ::: createShow :::");

		if(result.hasErrors()) {
			logger.info("::: IN SHOWCONTROLLER ::: createShow ::: result.hasErrors() ::: " + result.hasErrors() + " :::");
			return "new";
		}else {
			logger.info("::: IN SHOWCONTROLLER ::: createShow ::: NOT result.hasErrors() :::");
			String email = principal.getName();
			User creatorShow = userServiceInShowController.findUserByEmail(email);
			show.setCreatorShow(creatorShow);
			//show.setId(showServiceInShowController.initIdNewShow(show));
			show.setRatings(new ArrayList<Rating>());

			showServiceInShowController.saveShow(show);
			
			return "redirect:/shows";
			
		}
	}
	
	@GetMapping(value="/{id}/edit")
	public String editShow(@PathVariable("id") Long id, Model model, @ModelAttribute("errors") String errors) {
		
		logger.info("::: IN SHOWCONTROLLER ::: editShow :::");
		
		this.inShowId=false;
		this.setInShowId(this.inShowId);
		
		Show editShow = showServiceInShowController.findById(id);
		model.addAttribute("editShow", editShow);
		model.addAttribute("inShowId", this.isInShowId());
		
		return "edit";
	}
	
	
	@PostMapping(value="/{id}/edit")
	public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("editShow") Show editedShow, BindingResult result, Model model, Principal principal, HttpSession session) {
		
		logger.info("::: IN SHOWCONTROLLER ::: updateShow :::");
		
		String email = principal.getName();
		logger.info("::: IN SHOWCONTROLLER ::: updateShow ::: email ::: " + email + " :::");
		Show show = showServiceInShowController.findById(id);
		logger.info("::: IN SHOWCONTROLLER ::: updateShow ::: show ::: " + show.getShowtitle() + "-" + show.getNetwork() + " :::");
		User creatorShow = userServiceInShowController.findUserByEmail(email);
		logger.info("::: IN SHOWCONTROLLER ::: updateShow ::: creatorShow ::: " + creatorShow.getUsername() + " :::");


		if(result.hasErrors()) {
			logger.info("::: IN SHOWCONTROLLER ::: updateShow ::: result.hasErrors() ::: " + result.hasErrors() + " :::");
			session.setAttribute("id", show.getId());
			return "redirect:/shows/createError";
		}else {
			editedShow.setCreatorShow(creatorShow);
			showServiceInShowController.updateShow(editedShow);
			return "redirect:/shows/";
		}
		
	}
	
	@GetMapping(value="/{id}/delete")
	public String deleteShow(@PathVariable("id") Long id) {
		
		logger.info("::: IN SHOWCONTROLLER ::: deleteShow :::");
		
		Show showlocal = showServiceInShowController.findById(id);
		
		if(showlocal!=null) {
			showServiceInShowController.deleteShow(showlocal);
		}
		
		return "redirect:/shows/";
	}
	
}
