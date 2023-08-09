package com.app.schooltripmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.schooltripmanagement.model.Trip;

import java.util.Optional;

/*Spring Data JPA repository interface that extends the JpaRepository interface
*/

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

	
	/*
	 * custom native SQL query to retrieve data from trips table based on the id
	 * field
	 */    
	
	@Query(value = "SELECT * FROM trips WHERE id = ?1", nativeQuery = true)
	
	/*
	 * Optional is used to indicate that the result might not exist in the database.
	 */    
    Optional<Trip> findByIdNative(Long id);
}
