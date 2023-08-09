package com.app.schooltripmanagement.service;

import java.util.Optional;

import com.app.schooltripmanagement.model.Approval;

public interface ApprovalService  {
	public void deleteApproval(Long id);
	public Optional<Approval> findApprovalById(Long id);
}
