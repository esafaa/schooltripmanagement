package com.app.schooltripmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.schooltripmanagement.model.Approval;

/* Testing positive scenario--> finding approval of the trip by ID  (approval exists in the database)
 * Testing negative scenario --> finding approval of the trip by ID (approval doesn't exist in the database)
 */


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApprovalRepositoryTest {

	@Autowired
	ApprovalRepository approvalRepository;

	@Test
	void testFindApprovalByIdWhenApprovalExists() {
		Optional<Approval> approval = approvalRepository.findByIdNative(5L);
		assertThat(approval).isPresent();
	}

	@Test
	void testFindApprovalByIdWhenApprovalDoesNotExists() {
		Optional<Approval> approval = approvalRepository.findByIdNative(10L);
		assertThat(approval).isNotPresent();
	}
}
