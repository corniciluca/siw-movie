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

import it.uniroma3.siw.controller.validator.ArtistValidator;
import it.uniroma3.siw.controller.validator.UpdateArtistValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;
import jakarta.validation.Valid;


@Controller
public class ArtistController {
	
	@Autowired private ArtistService artistService;
	@Autowired private ArtistValidator artistValidator;
	@Autowired private UpdateArtistValidator updateArtistValidator;
	@Autowired private MovieService movieService;
	
	@GetMapping("/artists")
	public String artists(Model model) {
		model.addAttribute("artists",artistService.getAllArtists());
		model.addAttribute("artist",new Artist());
		return "artists.html";
	}
	
	@PostMapping("/updateArtist/{ida}")
	public String updateArtist(@ModelAttribute("ida") Long ida,@Valid @ModelAttribute("artist") Artist newArtist, BindingResult artistBindingResult,Model model)  {
		
		Artist artistInDB = artistService.getArtist(ida);
		
		updateArtistValidator.validate(newArtist, artistBindingResult);
		if(!artistBindingResult.hasErrors()) {
			artistService.updateArtistFields(artistInDB, newArtist);
			artistService.save(artistInDB);
			return "redirect:/artists";
		}
		
		model.addAttribute("artists",artistService.getAllArtists());
		model.addAttribute("artist",newArtist);
		return "artists.html";
	}
	
	@PostMapping("/updateArtistImage/{ida}")
	public String updateArtistImage( @ModelAttribute("ida") Long ida,@RequestParam("image") MultipartFile image,Model model) throws IOException {
		
		Artist artist = artistService.getArtist(ida);
		
		
		artistService.uploadImagesForArtist(artist, image);
		
		artistService.save(artist);
		
		return "redirect:/artists";
	}
	
	@PostMapping("admin/newArtist")
	public String newArtist(@Valid @ModelAttribute("artist") Artist artist, BindingResult artistBindingResult,@RequestParam("image") MultipartFile image,Model model) throws IOException {
		artistValidator.validate(artist,artistBindingResult);
		updateArtistValidator.validate(artist,artistBindingResult);
		if(!artistBindingResult.hasErrors()) {
			artistService.uploadImagesForArtist(artist, image);
			artistService.save(artist);
			return "redirect:/admin/manageSite";
		}
		
		model.addAttribute("movies", this.movieService.getAllMovies());
		model.addAttribute("artists",this.artistService.getAllArtists());
		model.addAttribute("movie", new Movie());
		model.addAttribute("artist", artist);
		return "/admin/manageSite.html";
	}
	
	@GetMapping("admin/deleteArtist/{id}")
	public String deleteArtist(@PathVariable("id") Long id, Model model) {
		
		
		artistService.deleteArtist(id);
		return "redirect:/admin/manageSite";
	}
}
