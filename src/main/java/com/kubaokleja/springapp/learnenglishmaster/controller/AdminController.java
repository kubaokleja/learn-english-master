package com.kubaokleja.springapp.learnenglishmaster.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kubaokleja.springapp.learnenglishmaster.entity.User;
import com.kubaokleja.springapp.learnenglishmaster.service.UserService;
import com.kubaokleja.springapp.learnenglishmaster.user.GameUser;

@Controller
@RequestMapping("/admin")
public class AdminController {


	private UserService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public AdminController(UserService userService) {
		this.userService = userService;

	}

	@GetMapping("/showFormForAdd")
	public String showMyRegistrationPage(Model theModel) {
		
		theModel.addAttribute("gameUser", new GameUser());
		
		return "user-form";
	}
	
	@PostMapping("/showFormForAdd")
	public String processRegistration(
			@Valid @ModelAttribute("gameUser") GameUser theGameUser, 
			BindingResult theBindingResult, 
			Model theModel) {
	
	String userName = theGameUser.getUsername();
	logger.info("Processing registration form for: " + userName);
	
	// form validation
	 if (theBindingResult.hasErrors()){
		 return "user-form";
        }

	// check the database if user already exists
    User existing = userService.findByUsername(userName);
    if (existing != null){
    	theModel.addAttribute("gameUser", new GameUser());
		theModel.addAttribute("registrationError", "User name already exists.");

		logger.warning("User name already exists.");
    	return "user-form";
    }
    
    // create user account        						
    userService.save(theGameUser);
    theModel.addAttribute("successMessage", "User registered successfully");
    logger.info("Successfully created user: " + userName);
    
    return "user-form";		
	}	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId) {
		//delete the user
		userService.deleteById(theId);
		
		return "redirect:/admin";
	}
	// add mapping for "/list"

	@GetMapping("")
	public String listUsers(Model theModel) {
		
		List<User> theUsers = userService.findAll();
		// add to the spring model
		theModel.addAttribute("users", theUsers);
		return "admin-panel";
	}
	@GetMapping("/search")
	public String searchCustomersVolTwo(@RequestParam("theSearchName") String theSearchName,
			Model model) {


		List<User> theUsers = userService.searchUsername(theSearchName);
				
		// add the customers to the model
		model.addAttribute("users", theUsers);

		return "admin-panel";		
	}
}
