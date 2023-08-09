package com.app.schooltripmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.schooltripmanagement.model.User;



public class UserServiceTest {

  

  

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
        

     
    }

    @Test
    public void testCheckIfUserExistsWhenUserExists() {
        String username = "testUser";
      
    }

    @Test
    public void testCheckIfUserExistsWhenUserDoesNotExist() {
        String username = "nonExistentUser";
  

    }

    @Test
    public void testFindUserByUsernameWhenUserExists() {
        String username = "testUser";

    }

    @Test
    public void testFindUserByUsernameWhenUserDoesNotExist() {
        String username = "nonExistentUser";
       

    }
}

