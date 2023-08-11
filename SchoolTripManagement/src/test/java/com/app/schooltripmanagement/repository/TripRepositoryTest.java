package com.app.schooltripmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.schooltripmanagement.model.Trip;

/* Testing positive scenario--> finding Trip by ID  (Trip exists in the database)
 * Testing negative scenario --> finding Trip by ID (Trip doesn't exist in the database)
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TripRepositoryTest {

	@Autowired
	TripRepository TripRepository;

	@Test
	void testFindTripByIdWhenTripExist() {
		Optional<Trip> trip = TripRepository.findByIdNative(2L);
		assertThat(trip).isPresent();
	}
	
	@Test
	void testFindTripByIdWhenTripDoesNotExist() {
		Optional<Trip> trip = TripRepository.findByIdNative(8L);
		assertThat(trip).isNotPresent();
	}
}
