package com.app.schooltripmanagement.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/* retrieve the username of the currently authenticated user*/
public class SpringSecurityUtil {

    // Utility method to get the current username of the authenticated user
    public static String getCurrentUsername() {
        // Get the current authentication information from the Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if the user is not authenticated or not logged in
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // Return null indicating no authenticated user
        }

        // Retrieve the principal object from the authentication
        Object principal = authentication.getPrincipal();

        // Check if the principal is an instance of UserDetails (common in Spring Security)
        if (principal instanceof UserDetails) {
            // Return the username from UserDetails
            return ((UserDetails) principal).getUsername();
        } else {
            // Return the principal object as a string (usually a username or identifier)
            return principal.toString();
        }
    }
}