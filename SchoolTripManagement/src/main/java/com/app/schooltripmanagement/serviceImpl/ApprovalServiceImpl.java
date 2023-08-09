package com.app.schooltripmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.repository.ApprovalRepository;

import java.util.Optional;


@Service
public class ApprovalServiceImpl {

    @Autowired
    ApprovalRepository approvalRepository;

    // Service method to delete an approval by ID

    public void deleteApproval(Long id) {
        Optional<Approval> approval = approvalRepository.findByIdNative(id);
        if (approval.isPresent()) {
            approvalRepository.delete(approval.get());
        } else {
            throw new RuntimeException("There is no approval against this ID: " + id);
        }
    }


    
 // Service method to find an approval by ID
    public Optional<Approval> findApprovalById(Long id) {
        Optional<Approval> approval = approvalRepository.findByIdNative(id);
        if (approval.isPresent()) {
            return approval;
        } else {
            throw new RuntimeException("There is no approval against this ID: " + id);
        }
    }
}
