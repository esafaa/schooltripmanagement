package com.app.schooltripmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/* home page controller */

@Controller
public class IndexController {

    // This controller handles requests for the root URL ("/") and returns the "index" view.
    @GetMapping("/")
    public String index() {
        return "index"; // Return the name of the view to be displayed.
    }

}
