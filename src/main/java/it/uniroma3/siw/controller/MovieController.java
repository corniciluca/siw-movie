package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.MovieValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.wrapper.FormMovieList;
import jakarta.validation.Valid;

@Controller
public class MovieController {
	@Autowired private MovieService movieService;
	@Autowired private ArtistService artistService;
	@Autowired private MovieValidator movieValidator;

	@PostMapping("admin/newMovie")
	public String newMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult,  @RequestParam("files") MultipartFile[] images,Model model) throws IOException {
		
		this.movieValidator.validate(movie, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			movieService.uploadImagesForMovie(movie, images);
			this.movieService.save(movie);
			return "redirect:/admin/manageSite";
		}
		
		model.addAttribute("movies", this.movieService.getAllMovies());
		model.addAttribute("artists",this.artistService.getAllArtists());
		model.addAttribute("movie", movie);
		model.addAttribute("artist", new Artist());
		return "admin/manageSite.html";
	}
	
	@GetMapping("/setDirector/{idm}/{idd}")
	public String setDirector(@PathVariable("idm") Long idm,@PathVariable("idd") Long idd,Model model) {
		Movie movie = movieService.getMovie(idm);
		Artist director = artistService.getArtist(idd);
		
		
		movieService.setDirectorMovie(movie,director);
		movieService.save(movie);
		
		return "redirect:/movies/{idm}";
	}
	
	@GetMapping("/updateActorsOfMovie/{id}")
	public String updateActorsOfMovie(@PathVariable("id") Long id,Model model) {
		Movie movie = movieService.getMovie(id);
		
		model.addAttribute("movie", movie);
		model.addAttribute("actorsInTheMovie", movieService.getActorsInMovie(movie));
		model.addAttribute("actorsNotInTheMovie", artistService.getActorsNotInTheMovie(id));
		return "actorsToAdd.html";
	}
	
	
	@PostMapping("/updateMovieImages/{idm}")
	public String updateMovieImages(@RequestParam("files") MultipartFile[] images,@PathVariable("idm") Long idm, Model model) throws IOException {
		
		Movie movie = this.movieService.getMovie(idm);
		
		movieService.deleteAllImagesFromMovie(movie);
		
		movieService.uploadImagesForMovie(movie, images);
		
		movieService.save(movie);
		
		return "redirect:/movies/{idm}";
	}
	
	
	@PostMapping("/addActorsToMovie/{idm}")
	public String addActorsToMovie(@ModelAttribute("actorsToRemoveOrAdd")  FormMovieList formIdArtisti,@PathVariable("idm") Long idm, Model model) {
		
		Movie movie = this.movieService.getMovie(idm);
		
		
		Artist artist;
		for (Long id : formIdArtisti.getIdArtisti()) {
			artist = artistService.getArtist(id);
			movieService.addActorToMovie(movie, artist);
		}
		
		movieService.save(movie);
		
		return "redirect:/movies/{idm}";
	}
	

	
	@PostMapping("/removeActorsFromMovie/{idm}")
	public String removeActorsFromMovie(@ModelAttribute("actorsToRemoveOrAdd")  FormMovieList formIdArtisti ,@PathVariable("idm") Long idm, Model model) {
		Movie movie = this.movieService.getMovie(idm);
		
		
		Artist artist;
		for (Long id : formIdArtisti.getIdArtisti()) {
			artist = artistService.getArtist(id);
			movieService.removeActorFromMovie(movie, artist);
		}
		
		movieService.save(movie);
		
		return "redirect:/movies/{idm}";
	}

	@GetMapping("/movies/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		
		
		model.addAttribute("movie", this.movieService.getMovie(id));
		model.addAttribute("artists",this.artistService.getAllArtists());
		model.addAttribute("actorsNotInTheMovie", artistService.getActorsNotInTheMovie(id));
		model.addAttribute("actorsToRemoveOrAdd",new FormMovieList());
		model.addAttribute("review",new Review());
		
		return "movie.html";
	}
	

	@GetMapping("/movies")
	public String showMovies(Model model) {
		model.addAttribute("movies", this.movieService.getAllMovies());
		return "movies.html";
	}
	
	@GetMapping("admin/deleteMovie/{id}")
	public String deleteMovie(@PathVariable("id") Long id, Model model) {
		
		
		movieService.deleteMovie(id);
		
		return "redirect:/admin/manageSite";
	}

}
