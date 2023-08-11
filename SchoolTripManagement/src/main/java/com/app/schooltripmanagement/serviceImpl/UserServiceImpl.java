package com.app.schooltripmanagement.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.User;
import com.app.schooltripmanagement.repository.UserRepository;
import com.app.schooltripmanagement.service.UserService;
import com.app.schooltripmanagement.util.SpringSecurityUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	
	 @Autowired 
	 BCryptPasswordEncoder passwordEncoder;
	  
	 
	// Service method to register a new user
	public User registerUser(User user) {
		log.info("User: {}", user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// Service method to check if a user with a given username already exists
	public boolean checkIfUserExists(String username) {
		Optional<User> user = userRepository.findUserByUsername(username);
		return user.isPresent();
	}

	// Service method to find a user by their username
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	// Service method to retrieve a list of approvals for the currently logged-in
	// user
	public List<Approval> approvalsListOfLoggedInUser() {
		String username = SpringSecurityUtil.getCurrentUsername();
		log.info("Current user: {}", username);
		Optional<User> user = userRepository.findUserByUsername(username);
		if (user.isPresent()) {
			return user.get().getApproval();
		} else {
			throw new RuntimeException("There is no user against this username: " + username);
		}
	}

	// Method required by Spring Security to load user details by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByUsername(username);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
					user.get().getPassword(), getAuthority(user.get()));
		} else {
			throw new RuntimeException("User doesn't exist");
		}
	}

	// Helper method to create user authorities for Spring Security
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}
}