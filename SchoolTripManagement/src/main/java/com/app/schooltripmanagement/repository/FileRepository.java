package com.app.schooltripmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	
	@Query(value = "SELECT * FROM trips WHERE id = ?1", nativeQuery = true)
	
	/*
	 * Optional is used to indicate that the result might not exist in the database.
	 */    
	
	Optional<Approval> findByIdNative(Long id);
}

