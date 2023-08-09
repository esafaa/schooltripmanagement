package com.app.schooltripmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
    // Inject the userDetailsService bean for user authentication

	 @Qualifier("userService")
	    @Autowired
	    UserDetailsService userDetailsService;

	 
	    // Create and configure the BCrypt password encoder bean

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF protection is disabled here.
        http.csrf().disable();
        
        // Configuring the authorization rules for HTTP requests.
        http.authorizeRequests(configurer ->
                configurer
                        // Any request starting with "/resources/" or "/static/"or .... will be permitted without authentication.
                        .requestMatchers("/resources/**", "/static/**","/register","/css/**","/images/**").permitAll()
                        // Any other request should be authenticated.
                        .anyRequest().authenticated()
        );
        
        // Configure form-based login.
        http.formLogin()
                .loginPage("/login") // The login page URL.
                .loginProcessingUrl("/login") // The URL where the login form is submitted.
                .permitAll(); // Permit all users to access the login page.
        
    	http.sessionManagement()
  	  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
  	 .invalidSessionUrl("/login"); 
        return http.build(); // Build and return the SecurityFilterChain.
    
   
	
	}
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        // Configure authentication to use userDetailsService and the password encoder

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder()); }
	

}
