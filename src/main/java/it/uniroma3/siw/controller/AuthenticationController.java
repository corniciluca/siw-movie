package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;

    @Autowired
	private UserService userService;
	
    @Autowired
    private  UserValidator userValidator;
    
    @Autowired
    private  CredentialsValidator credentialsValidator;
    
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUser.html";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "formLogin.html";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
        return "index.html";
	}
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        return "index.html";
    }

	@PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("user") User user,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,Model model) {
		
		userService.setNameOfUser(user,userService.getNameOfUser(user).toLowerCase());
		userService.setSurnameOfUser(user,userService.getSurnameOfUser(user).toLowerCase());
		credentialsService.setUsernameOfCredentials(credentials,credentialsService.getUsernameOfCredentials(credentials).toLowerCase());
		
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(user);
            credentialsService.setUserOfCredentials(credentials,user);
            credentialsService.saveCredentials(credentials);
            return "redirect:/login";
        }
        
        model.addAttribute("user", user);
		model.addAttribute("credentials", credentials);
		return "formRegisterUser.html";
    }
}