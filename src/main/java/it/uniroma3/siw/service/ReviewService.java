package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;
import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	@Autowired private ReviewRepository reviewRepository;

	@Transactional
	public Review save(Review review) {
		return reviewRepository.save(review);
	}
	@Transactional
	public Review getReview(User reviewer, Movie movie) {
		return reviewRepository.findByReviewerAndMovie(reviewer,movie).orElse(null);
	}
	
	@Transactional
	public void delete(Review review) {
		reviewRepository.delete(review);	
	}
	
	public void setReviewerOfReview(Review review,User reviewer) {
		review.setReviewer(reviewer);
	}
	
	public void setMovieReview(Review review,Movie movie) {
		review.setMovie(movie);
	}
	
}
