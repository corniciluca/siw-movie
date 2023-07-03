package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;

@Component
public class UpdateArtistValidator implements Validator{
	
	@Autowired
	private ArtistService artistService;

	@Override
	public void validate(Object o, Errors errors) {
		Artist artist = (Artist)o;
		if( artistService.getName(artist) != null && artistService.getSurname(artist) != null) {
			if(artistService.getDateOfDeath(artist) != null) {
				if(artistService.getDateOfBirth(artist).compareTo(artistService.getDateOfDeath(artist)) >= 0)
					errors.reject("artist.dates.inconsistency");
				if(artistService.getDateOfDeath(artist).getYear() - artistService.getDateOfBirth(artist).getYear() < 18)
					errors.reject("artist.year.inconsistency");
			}
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Artist.class.equals(aClass);
	}
}
