package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movie {
	public static final String UPLOAD_FOLDER="./images_upload/movies/";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Length(max=64)
    private String title;
    
    @NotNull
    @Min(1900)
    @Max(2023)
	private Integer year;
    
    @NotNull
    private LocalDate date;
    
 
	private Set<String> urlImages;
	
	@OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
	private Set<Review> reviews;
	
	@ManyToOne
	private Artist director;
	
	@ManyToMany
	private Set<Artist> actors;
	
	public Movie() 
	{
		urlImages = new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Set<Review> getNews() {
		return reviews;
	}

	public void setNews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Artist getDirector() {
		return director;
	}

	public void setDirector(Artist director) {
		this.director = director;
	}

	public Set<Artist> getActors() {
		return actors;
	}

	public void setActors(Set<Artist> actors) {
		this.actors = actors;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<String> getUrlImages() {
		return urlImages;
	}

	public void setUrlImages(Set<String> urlImages) {
		this.urlImages = urlImages;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(title, other.title) && Objects.equals(year, other.year);
	}
}