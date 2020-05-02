package com.kubaokleja.springapp.learnenglishmaster.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.service.UserService;
import com.kubaokleja.springapp.learnenglishmaster.user.GameUser;



@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@GetMapping("/registration")
	public String showMyRegistrationPage(Model theModel) {
		
		theModel.addAttribute("gameUser", new GameUser());
		
		return "registration";
	}
	@PostMapping("/registration")
	public String processRegistration(
			@Valid @ModelAttribute("gameUser") GameUser theGameUser, 
			BindingResult theBindingResult, 
			Model theModel) {
	
	String userName = theGameUser.getUsername();
	logger.info("Processing registration form for: " + userName);
	
	// form validation
	 if (theBindingResult.hasErrors()){
		 return "registration";
        }

	// check the database if user already exists
    User existing = userService.findByUsername(userName);
    if (existing != null){
    	theModel.addAttribute("gameUser", new GameUser());
		theModel.addAttribute("registrationError", "Username already exists.");

		logger.warning("User name already exists.");
    	return "registration";
    }
    
    // create user account        						
    userService.save(theGameUser);
    theModel.addAttribute("successMessage", "User registered successfully");
    logger.info("Successfully created user: " + userName);
    
    return "registration";		
}
	
}

