package com.image.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageUtil {
	private final String uploadDir = "uploads";
	
	public FileStorageUtil() throws IOException {
		Files.createDirectories(Paths.get(uploadDir));
	}
	
	public String saveFile(MultipartFile file) throws IOException {
		String extension = getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID() + "." + extension;
		Path filePath = Paths.get(uploadDir, fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		return filePath.toString();
	}
	
	private String getExtension(String fileName) {
		int index = fileName.lastIndexOf('.');
		return index > 0 ? fileName.substring(index + 1) : "";
	}
}