package com.app.schooltripmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/* Controller that manages user authentication and failed login attempt */


@Controller
public class LoginController {

    // Controller for handling GET requests to "/login" URL
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("error", false); // Add an "error" attribute to the model with value "false"
        return "login"; // Return the name of the "login" view template
    }

    // Controller for handling GET requests to "/login-error" URL
    @GetMapping("/login-error")
    public String loginError(Model model) { // Model interface provides a way for the controller to send data to the view
        model.addAttribute("error", true); // Add an "error" attribute to the model with value "true"
        return "login"; // Return the name of the "login" view template
    }

}
