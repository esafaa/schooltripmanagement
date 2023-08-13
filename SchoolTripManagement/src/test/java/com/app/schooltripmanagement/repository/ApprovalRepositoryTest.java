package com.app.schooltripmanagement.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.schooltripmanagement.model.Approval;


/* Testing positive scenario--> finding Approval by ID  (Approval exists in the database)
 * Testing negative scenario --> finding Approval by ID (Approval doesn't exist in the database)
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApprovalRepositoryTest {

    @Autowired
    ApprovalRepository approvalRepository;
	

	  
	 @Test
    void findByIdNativeWhenApprovalExist() {
    	Optional<Approval>  approval = approvalRepository.findByIdNative(5L);
    	  assertThat(approval).isPresent();
    }
	 
	 @Test
	    void findByIdNativeApprovalDoesNotExist() {
	    	Optional<Approval>  approval = approvalRepository.findByIdNative(20L);
	    	  assertThat(approval).isNotPresent();
	    }
}






/*
 * @ExtendWith(MockitoExtension.class)
 * 
 * @DataJpaTest
 * 
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 * public class ApprovalRepositoryTest {
 * 
 * @Mock private ApprovalRepository approvalRepository;
 * 
 * @InjectMocks private ApprovalService approvalService;
 * 
 * @Test public void testFindByIdNative() { Long approvalId = 1L; Approval
 * approval = new Approval(); approval.setId(1L); approval.setStatus(true); //
 * Set properties for the approval object if needed
 * when(approvalRepository.findByIdNative(approvalId)).thenReturn(Optional.of(
 * approval));
 * 
 * // Replace the following code with the actual code that calls findByIdNative
 * in service class Optional<Approval> result =
 * approvalService.findApprovalById(approvalId);
 * assertEquals(Optional.of(approval), result); } }
 * 
 */