package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;

public interface ReviewRepository extends CrudRepository<Review, Long>{

	public boolean existsByReviewerAndMovie(User reviewer, Movie movie);

	public Optional<Review> findByReviewerAndMovie(User reviewer, Movie movie);

}
