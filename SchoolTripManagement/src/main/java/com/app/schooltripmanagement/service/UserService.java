package com.app.schooltripmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.User;

public interface UserService extends UserDetailsService {
	public User registerUser(User user);
	public boolean checkIfUserExists(String username);
	public Optional<User> findUserByUsername(String username);
	public List<Approval> approvalsListOfLoggedInUser();
	public UserDetails loadUserByUsername(String username);

}
