package com.spring.rest.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.model.DemoFilesName;

public class FileUploadDirUtil {
	public static DemoFilesName saveFileName(Long employeeId, String uploadDir, String requestType, MultipartFile file) throws IOException{
		Path uploadPath=Paths.get(uploadDir);
		String newFileName=null;
		DemoFilesName demoFileName=new DemoFilesName();
		if(!Files.exists(uploadPath))
		{
			try {				
				Files.createDirectories(uploadPath);
			} catch (Exception e) {
				
			}
		}
		try {
			String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			newFileName=System.currentTimeMillis()+"empId"+employeeId+requestType+fileExtension;
			Path filePath=uploadPath.resolve(newFileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING );
			demoFileName.setFileName(newFileName);
			demoFileName.setFileType(fileExtension);
			demoFileName.setFilePath(newFileName);
			demoFileName.setFileSize(file.getSize());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return demoFileName;
	}

}
