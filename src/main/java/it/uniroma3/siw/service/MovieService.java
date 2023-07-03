package it.uniroma3.siw.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.MovieRepository;
import it.uniroma3.siw.utils.FileUploadUtil;
import jakarta.transaction.Transactional;

@Service
public class MovieService {
	@Autowired MovieRepository movieRepository;
	
	@Transactional
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}
	@Transactional
	public Movie getMovie(Long idMovie) {
		return movieRepository.findById(idMovie).get();
	}
	@Transactional
	public boolean isMovieAlreadyPresent(String title,int year) {
		return movieRepository.existsByTitleAndYear(title,year);
	}
	@Transactional
	public void deleteMovie(Long idMovie) {
		Movie movie = movieRepository.findById(idMovie).get();
		movieRepository.delete(movie);
	}
	@Transactional
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	@Transactional
	public Iterable<Movie> getMoviesByYear(Integer year) {
		return movieRepository.findByYear(year);
	}
	
	public int getYearMovie(Movie movie) {
		return movie.getYear();
	}
	
	public Set<Artist> getActorsInMovie(Movie movie) {
		return movie.getActors();
	}
	
	public String getTitleMovie(Movie movie) {
		return movie.getTitle();
	}
	
	public LocalDate getDateMovie(Movie movie) {
		return movie.getDate();
	}
	
	public void addImagesToMovie(Movie movie,String filename) {
		movie.getUrlImages().add(filename);
	}
	
	public void setDirectorMovie(Movie movie,Artist director) {
		movie.setDirector(director);
	}
	
	public void addActorToMovie(Movie movie,Artist actor) {
		movie.getActors().add(actor);
	}
	
	public void removeActorFromMovie(Movie movie,Artist actor) {
		movie.getActors().remove(actor);
	}
	
	public void deleteAllImagesFromMovie(Movie movie) {
		movie.getUrlImages().clear();
	}
	
	public void addReviewToMovie(Movie movie,Review review) {
		movie.getReviews().add(review);
	}
	
	@Transactional
	public void uploadImagesForMovie(Movie movie,MultipartFile image) throws IOException {
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		FileUploadUtil.saveFile(Movie.UPLOAD_FOLDER, filename, image);
		this.addImagesToMovie(movie,filename);
	}
	
	@Transactional
	public void uploadImagesForMovie(Movie movie,MultipartFile[] images) throws IOException {
		for(MultipartFile image : images) {
			uploadImagesForMovie(movie, image);
		}
	}


}
