package it.uniroma3.siw.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomErrorController implements ErrorController  {

    @GetMapping("/error")
    public String handleErrorGet() {
        return "errorPage.html";
    }
    
    @PostMapping("/error")
    public String handleErrorPost() {
        return "errorPage.html";
    }
    
    public String getErrorPath() {
		return "/error";
    }

}
