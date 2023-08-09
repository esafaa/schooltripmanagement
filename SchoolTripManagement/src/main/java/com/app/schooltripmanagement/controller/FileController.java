package com.app.schooltripmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.schooltripmanagement.model.File;
import com.app.schooltripmanagement.serviceImpl.FileServiceImpl;

/* Controller handling HTTP requests related to file attachment for each trip
 * get the file to view a specific file, delete file */


@Controller
public class FileController {

    private final FileServiceImpl fileService;

    @Autowired
    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/viewFile/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        // Retrieve the file by its ID
        File file = fileService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build(); // Return a "not found" response if the file doesn't exist
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(getMediaType(file.getFileType())); // Set the content type based on the file type
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(file.getFileName()).build()); // Set the content disposition to inline
        return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK); // Return the file data along with headers
    }

    // Determine and return the appropriate media type based on the file type
    private MediaType getMediaType(String fileType) {
        if (fileType != null) {
            String lowerCaseFileType = fileType.toLowerCase();
            if (lowerCaseFileType.contains("image")) {
                return MediaType.parseMediaType(fileType); // For images
            } else if (lowerCaseFileType.endsWith("pdf")) {
                return MediaType.APPLICATION_PDF; // For PDF files
            } else if (lowerCaseFileType.endsWith("docx") || lowerCaseFileType.endsWith("doc")) {
                return MediaType.APPLICATION_OCTET_STREAM; // For Word documents
            } else if (lowerCaseFileType.endsWith("xlsx") || lowerCaseFileType.endsWith("xls")) {
                return MediaType.APPLICATION_OCTET_STREAM; // For Excel files (you can adjust this based on your application's behavior)
            } else if (lowerCaseFileType.endsWith("pptx") || lowerCaseFileType.endsWith("ppt")) {
                return MediaType.APPLICATION_OCTET_STREAM; // For PowerPoint files (you can adjust this based on your application's behavior)
            }
        }
        return MediaType.APPLICATION_OCTET_STREAM; // Default to binary data if the file type is unknown
    }

    @GetMapping("/deleteFile/{fileId}/{tripId}")
    public String deleteFile(@PathVariable(name = "fileId") Long fileId, @PathVariable(name = "tripId") Long tripId) {
        // Delete the file by its ID and redirect to the trip's file list
        fileService.deleteFile(fileId);
        return "redirect:/tripFiles/" + tripId;
    }
}
