package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CredentialsService {
	@Autowired 
	private CredentialsRepository credentialsRepository;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Credentials getCredentials(Long idCredentials) {
		return credentialsRepository.findById(idCredentials).orElse(null);
	}
	
	@Transactional
	public Credentials getCredentials(String username){
		return credentialsRepository.findByUsername(username).orElse(null);
	}
	
	@Transactional
	public Credentials saveCredentials(Credentials credentials){
		credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
	}
	
	public User getUserAssociatedToCredentials(Credentials credentials) {
		return credentials.getUser();
	}
	
	public String getUsernameOfCredentials(Credentials credentials) {
		return credentials.getUsername();
	}
	
	public void setUsernameOfCredentials(Credentials credentials,String username) {
		credentials.setUsername(username);
	}

	public void setUserOfCredentials(Credentials credentials,User user){
		credentials.setUser(user);
	}
}
