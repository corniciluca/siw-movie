package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;

@Controller
public class ManageController {
	
	@Autowired private MovieService movieService;
	@Autowired private ArtistService artistService;
	
	@GetMapping("admin/manageSite")
	public String manageSite(Model model) {
		model.addAttribute("movies", this.movieService.getAllMovies());
		model.addAttribute("artists",this.artistService.getAllArtists());
		model.addAttribute("movie", new Movie());
		model.addAttribute("artist", new Artist());
		return "admin/manageSite.html";
	}
	
}
