package com.app.schooltripmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.schooltripmanagement.serviceImpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	// Inject the userDetailsService bean for user authentication
	@Bean
	// authentication
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	// Define a BCryptPasswordEncoder bean for password encoding
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	// Configure the AuthenticationProvider for user authentication

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService()); // set the custom user details service
		authenticationProvider.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
		return authenticationProvider;
	}

	// Configuring the authorization rules for HTTP requests.

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable)// CSRF protection is disabled here.
				.authorizeHttpRequests(auth->{auth.requestMatchers("/resources/**", "/static/**", "/register", "/css/**", "/images/**")// Any request starting with "/resources/" or "/static/"or .... will be
					// permitted without authentication.
						.permitAll()
						.anyRequest().authenticated();});// Any other request should be authenticated.

		// Configure form-based login.
		http.formLogin().loginPage("/login") // The login page URL.
				.loginProcessingUrl("/login") // The URL where the login form is submitted.
				.permitAll(); // Permit all users to access the login page.
		// Configure session management.
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login"));
		return http.build(); // Build and return the SecurityFilterChain.

	}

}
