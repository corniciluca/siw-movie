package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.ReviewValidator;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;


@Controller
public class ReviewController {
	@Autowired private ReviewService reviewService;
	@Autowired private MovieService movieService;
	@Autowired private UserService userService;
	@Autowired private ReviewValidator reviewValidator;

	@GetMapping("/deleteReview/{idm}/{idu}")
	public String deleteReview(@PathVariable("idm") Long idm,@PathVariable("idu") Long idu, Model model) {
		Movie movie = movieService.getMovie(idm);
		User reviewer = userService.getUser(idu);
		Review review = reviewService.getReview(reviewer,movie);
		reviewService.delete(review);
		
		return "redirect:/movies/{idm}";
	}
	
	@PostMapping("/addReviewToMovie/{idm}/{fullname}")
	public String addReviewToMovie(@Valid @ModelAttribute("review") Review review, BindingResult bindingResultReview,@PathVariable("idm") Long idm,@PathVariable("fullname") String fullname,Model model) {
		
		String[] strings = fullname.split("_");
		String name = strings[0];
		String surname =  strings[1];
		
		Movie movie = this.movieService.getMovie(idm);
		User user = this.userService.getUser(name,surname);
		reviewService.setMovieReview(review, movie);
		reviewService.setReviewerOfReview(review, user);
		
		reviewValidator.validate(review, bindingResultReview);
		
		if(!bindingResultReview.hasErrors()) {
			reviewService.save(review);
			movieService.addReviewToMovie(movie, review);
			movieService.save(movie);
			return "redirect:/movies/{idm}";
		}
		
		model.addAttribute("movie", movie);
		return "movie.html";
		
	}
}
