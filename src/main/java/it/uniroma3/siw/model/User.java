package it.uniroma3.siw.model;

import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Table(name = "users")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	@Length(max=64)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String name;
	
	@NotBlank
	@Length(max=64)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String surname;
	
	@NotBlank
	@Email
	private String email;
	
	@OneToMany(mappedBy = "reviewer",cascade = CascadeType.REMOVE)
	private Set<Review> reviews;
	
	
	
	
	public User() {
	}
	
	public User(String name,String surname,String Email) {
		this();
		setEmail(Email);
		setName(name);
		setSurname(surname);
	}
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
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
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

}
