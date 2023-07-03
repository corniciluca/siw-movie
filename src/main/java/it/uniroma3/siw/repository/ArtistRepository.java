package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist,Long>{
	
	@Query(value = "SELECT * FROM artist as a WHERE a.id "
			+ " NOT IN (SELECT actors_id FROM movie_actors "
			+ " WHERE movie_actors.movies_id = :idMovie) ", nativeQuery = true)
	public Iterable<Artist> findActorsNotInMovie(@Param("idMovie") Long idMovie);

	public boolean existsByNameAndSurname(String name, String surname); 
}
