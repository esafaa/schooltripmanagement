package com.app.schooltripmanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.schooltripmanagement.model.File;
import com.app.schooltripmanagement.repository.FileRepository;
import com.app.schooltripmanagement.serviceImpl.FileServiceImpl;



@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileServiceImpl fileService;

    @Test
    public void testGetFileByIdWhenFileExists() {
        Long fileId = 1L;
        File file = new File();
        when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));

        File result = fileService.getFileById(fileId);

        assertEquals(file, result);
    }

    @Test
    public void testGetFileByIdWhenFileDoesNotExist() {
        Long fileId = 1L;
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> fileService.getFileById(fileId));
    }

    
}