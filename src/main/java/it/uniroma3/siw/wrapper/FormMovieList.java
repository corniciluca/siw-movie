package it.uniroma3.siw.wrapper;

import java.util.ArrayList;
import java.util.List;

public class FormMovieList {
	List<Long>  idArtisti;
	
	public FormMovieList() {
		idArtisti = new ArrayList<>();
	}

	public List<Long> getIdArtisti() {
		return idArtisti;
	}

	public void setIdArtisti(List<Long> idArtisti) {
		this.idArtisti = idArtisti;
	}
}
