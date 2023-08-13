package com.app.schooltripmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.schooltripmanagement.model.User;
import com.app.schooltripmanagement.repository.UserRepository;
import com.app.schooltripmanagement.serviceImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
        testUser.setRole("ROLE_PARENT");
    }

    @Test
    public void testRegisterUser() {
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(testUser)).thenReturn(testUser);

        User result = userService.registerUser(testUser);

        assertEquals(testUser, result);
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    public void testCheckIfUserExistsWhenUserExists() {
        String username = "testUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(testUser));

        boolean result = userService.checkIfUserExists(username);

        assertTrue(result);
    }

    @Test
    public void testCheckIfUserExistsWhenUserDoesNotExist() {
        String username = "nonExistentUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());

        boolean result = userService.checkIfUserExists(username);

        assertFalse(result);
    }

    @Test
    public void testFindUserByUsernameWhenUserExists() {
        String username = "testUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.findUserByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
    }

    @Test
    public void testFindUserByUsernameWhenUserDoesNotExist() {
        String username = "nonExistentUser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());

        Optional<User> result = userService.findUserByUsername(username);

        assertFalse(result.isPresent());
    }
}