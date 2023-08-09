package com.app.schooltripmanagement.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.schooltripmanagement.model.File;
import com.app.schooltripmanagement.repository.FileRepository;

import java.util.Optional;

@Service
public class FileServiceImpl {

    @Autowired
    private FileRepository fileRepository;

    // Service method to retrieve a file by its ID
    public File getFileById(Long id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            return file.get();
        } else {
            throw new RuntimeException("File not found ID: " + id);
        }
    }

    // Service method to delete a file by its ID
    public void deleteFile(Long id) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            fileRepository.delete(file.get());
        } else {
            throw new RuntimeException("File can't be deleted");
        }
    }
}
