package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired UserRepository userRepository;
	
	@Transactional
	public User getUser(Long idUser){
		return userRepository.findById(idUser).orElseGet(null);
	}
	
	@Transactional
	public User getUser(String name,String surname){
		return userRepository.findByNameAndSurname(name,surname).orElse(null);
	}
	
	@Transactional
	public User saveUser(User user){
		return userRepository.save(user);
	}

	public boolean existByNameAndSurname(String name, String surname) {
		return userRepository.existsByNameAndSurname(name,surname);
	}

	public String getNameOfUser(User user) {
		return user.getName();
	}

	public String getSurnameOfUser(User user) {
		return user.getSurname();
	}
	
	public void setNameOfUser(User user,String name) {
		user.setName(name);
	}

	public void setSurnameOfUser(User user,String surname) {
		user.setSurname(surname);
	}

	
	
}
