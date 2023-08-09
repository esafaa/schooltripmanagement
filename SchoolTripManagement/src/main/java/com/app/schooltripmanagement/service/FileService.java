package com.app.schooltripmanagement.service;

import com.app.schooltripmanagement.model.File;

public interface FileService {
	public File getFileById(Long id);
	public void deleteFile(Long id);
}
