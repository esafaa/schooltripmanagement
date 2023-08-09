package com.app.schooltripmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.schooltripmanagement.model.Approval;

import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
	/*
	 * custom SQL query using native SQL
	 * retrieve an Approval based on the id.
	 */   
	
	@Query(value = "SELECT * FROM approvals WHERE id = ?1", nativeQuery = true)
	
	/*
	 * Optional is used to indicate that the result might not exist in the database.
	 */    
	
	Optional<Approval> findByIdNative(Long id);
}
