package it.uniroma3.siw.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.utils.FileUploadUtil;
import jakarta.transaction.Transactional;

@Service
public class ArtistService {
	@Autowired ArtistRepository artistRepository;
	
	@Transactional
	public Iterable<Artist> getAllArtists() {
		return artistRepository.findAll();
	}
	@Transactional
	public Artist getArtist(Long idArtist) {
		return artistRepository.findById(idArtist).get();
	}
	@Transactional
	public Artist save(Artist artist) {
		return artistRepository.save(artist);
	}
	@Transactional
	public Iterable<Artist> getActorsNotInTheMovie(Long idMovie) {
		return artistRepository.findActorsNotInMovie(idMovie);
	}
	@Transactional
	public void deleteArtist(Long idArtist) {
		Artist artist = artistRepository.findById(idArtist).get();
		artistRepository.delete(artist);
	}
	
	public Artist updateArtistFields(Artist artist,Artist newArtist) {
		
		updateArtistField(artist, newArtist,"name");
		updateArtistField(artist, newArtist,"surname");
		updateArtistField(artist, newArtist,"deathDate");
		updateArtistField(artist, newArtist,"dateOfBirth");
		
		return artist;
	}
	
	private void updateArtistField(Artist artist,Artist newArtist,String nameField) {
		try {
			Class<?> typeClass = Artist.class.getDeclaredField(nameField).getType();
			Method setter = Artist.class.getMethod("set"+StringUtils.capitalize(nameField), typeClass);
		    Method getter = Artist.class.getMethod("get"+StringUtils.capitalize(nameField));
		    
		    if (getter.invoke(newArtist) != null)
				setter.invoke(artist, getter.invoke(newArtist));
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public boolean isAlreadyPresent(Artist artist) {
		return artistRepository.existsByNameAndSurname(artist.getName(),artist.getSurname());
	}
	
	public String getName(Artist artist) {
		return artist.getName();
	}
	
	public LocalDate getDateOfBirth(Artist artist) {
		return artist.getDateOfBirth();
	}
	
	public LocalDate getDateOfDeath(Artist artist) {
		return artist.getDeathDate();
	}
	public String getSurname(Artist artist) {
		return artist.getSurname();
	}
	
	@Transactional
	public void uploadImagesForArtist(Artist artist,MultipartFile image) throws IOException{
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		FileUploadUtil.saveFile(Artist.UPLOAD_FOLDER, filename, image);
		artist.setUrl_of_picture(filename);
	}

}
