package com.app.schooltripmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.schooltripmanagement.model.User;

/* Testing positive scenario--> finding approval of the user by ID  (user exists in the database)
 * Testing negative scenario --> finding approval of the user by ID (user doesn't exist in the database)
 */


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	
	
	@Autowired
	UserRepository UserRepository;

	@Test
	public void testFindUserByUsernameWhenUserExists() {
		Optional<User> user = UserRepository.findUserByUsername("teacher@gmail.com");
		assertThat(user).isPresent();
	}

	@Test
	public void testFindUserByUsernameWhenUserDoesNotExist() {
		Optional<User> user = UserRepository.findUserByUsername("teacher1@gmail.com");
		assertThat(user).isNotPresent();
	}
}
