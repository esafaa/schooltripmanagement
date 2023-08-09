package com.app.schooltripmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.schooltripmanagement.model.User;
import com.app.schooltripmanagement.serviceImpl.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/* Controller that handles user registration functionality 
 * display registration form, submit registration form after validating the username and password using @valid
 * check if the user name already exists based on the username. If the user already exists, an error message is added to the BindingResult */

@Controller
@Slf4j
public class RegisterController {

	@Autowired
	UserServiceImpl userService;

	// Controller method to display the registration form
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User()); // Add an empty User object to the model
		return "register"; // Return the name of the "register" view template
	}

	// Controller method to process the submitted registration form
	@PostMapping("/register")
	public String processRegistrationForm(@ModelAttribute(name = "user") @Valid User user, BindingResult result,
			Model model) {
		// Check if the user already exists
		boolean ifUserExists = userService.checkIfUserExists(user.getUsername());
		if (ifUserExists) {
			log.error("There is already a user registered with this username. Try with other username");
			result.rejectValue("username", null, "There is already a user registered with this username.");
		}

		// Check for form validation errors
		if (result.hasErrors()) {
			model.addAttribute("user", user); // Add the submitted User object to the model
			return "register"; // Return to the registration form if there are errors
		}

		// Perform user registration logic
		userService.registerUser(user);

		return "redirect:/login"; // Redirect to the login page after successful registration
	}
}