package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private UserService userService;
	
	class UserData{
		private String name;
		private String surname;

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
	}
	
	
	@ModelAttribute("userData")
    public UserData getUserNameAndSurname() {
		UserData userData = new UserData();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
        	if(authentication instanceof OAuth2AuthenticationToken) {
        		
        		String name = ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("given_name");
        		String surname = ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("family_name");

        		userData.setName(name);
				userData.setSurname(surname);
        	}else {
        		
        		UserDetails userDetails = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        		
        		User userAssociated = credentialsService.getUserAssociatedToCredentials(credentials);
				userData.setName(userService.getNameOfUser(userAssociated));
        		userData.setSurname(userService.getSurnameOfUser(userAssociated));
        	}
        }
        return userData;
    }
	
	
}
