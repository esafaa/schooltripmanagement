package com.app.schooltripmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.repository.ApprovalRepository;
import com.app.schooltripmanagement.serviceImpl.ApprovalServiceImpl;




@ExtendWith(MockitoExtension.class)
public class ApprovalServiceTest {

    @Mock
    private ApprovalRepository approvalRepository;

    @InjectMocks
    private ApprovalServiceImpl approvalService;

    @Test
    public void testDeleteApprovalWhenApprovalExists() {
        Long approvalId = 1L;
        Approval approval = new Approval();
        when(approvalRepository.findByIdNative(approvalId)).thenReturn(Optional.of(approval));

        approvalService.deleteApproval(approvalId);

        verify(approvalRepository, times(1)).delete(approval);
    }

    @Test
    public void testDeleteApprovalWhenApprovalDoesNotExist() {
        Long approvalId = 1L;
        when(approvalRepository.findByIdNative(approvalId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> approvalService.deleteApproval(approvalId));
        verify(approvalRepository, times(0)).delete(any());
    }

    @Test
    public void testFindApprovalByIdWhenApprovalExists() {
        Long approvalId = 1L;
        Approval approval = new Approval();
        when(approvalRepository.findByIdNative(approvalId)).thenReturn(Optional.of(approval));

        Optional<Approval> result = approvalService.findApprovalById(approvalId);

        assertTrue(result.isPresent());
        assertEquals(approval, result.get());
    }

    @Test
    public void testFindApprovalByIdWhenApprovalDoesNotExist() {
        Long approvalId = 1L;
        when(approvalRepository.findByIdNative(approvalId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> approvalService.findApprovalById(approvalId));
    }
}

