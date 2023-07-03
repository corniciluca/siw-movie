package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Artist {
	public static final String UPLOAD_FOLDER="./images_upload/artists/";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String surname;
	
	@NotNull
	private LocalDate dateOfBirth; 
	
	private LocalDate deathDate;
	
	private String url_of_picture;
	
	@OneToMany(mappedBy = "director",cascade = CascadeType.REMOVE)
	private Set<Movie> moviesDirected;
	
	@ManyToMany(mappedBy = "actors",cascade = CascadeType.REMOVE)
	private Set<Movie> movies;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUrl_of_picture() {
		return url_of_picture;
	}
	public void setUrl_of_picture(String url_of_picture) {
		this.url_of_picture = url_of_picture;
	}
	public Set<Movie> getMoviesDirected() {
		return moviesDirected;
	}
	public void setMoviesDirected(Set<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public LocalDate getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(LocalDate deathDate) {
		this.deathDate = deathDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
	
}
