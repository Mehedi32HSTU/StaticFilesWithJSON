package com.spring.rest.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.config.FileUploadDirUtil;
import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.DemoFilesName;
import com.spring.rest.model.FileDetails;
import com.spring.rest.model.LeaveRequest;
import com.spring.rest.repository.LeaveRequestRepository;
import com.spring.rest.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	
	public Logger Log = LoggerFactory.getLogger(this.getClass());
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	LeaveRequestRepository leaveRequestRepository;
	
//	public  Log = LoggerFactory.getLogger(this.getClass());
	@Override
	public ResponseEntity<?> saveLeaveRequest(String leaveReq, List<MultipartFile> files)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		try {
			Log.info("saveLeaveRequest service has called.");
			LeaveRequest leaveRequest = new LeaveRequest();
			try {
				leaveRequest = objectMapper.readValue(leaveReq, LeaveRequest.class);
//				leaveRequest.setActive(true);
				Log.info("leaveRequest service has called.");
//				Log.info(leaveRequest.toString());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to parse the JSON data");
			} 
			String attatchment =null;
			List<FileDetails> fileDetails=new ArrayList<>();
			FileDetails fileDetail;
			try {
				String uploadDir= "src/main/resources/static/file/";
				for(MultipartFile file : files) {
					DemoFilesName demoFileName=FileUploadDirUtil.saveFileName(leaveRequest.getEmployeeId(), uploadDir, leaveRequest.getRequestType(), file);
					attatchment=demoFileName.getFileName();
//					Log.info(attatchment);
//					Log.info(demoFileName.getFilePath());
//					Log.info(String.valueOf(demoFileName.getFileSize()));
//					FileDetails fileDetail=new FileDetails()
					fileDetail=new FileDetails(demoFileName.getFileName(), demoFileName.getFileType(), demoFileName.getFileSize(), uploadDir+demoFileName.getFilePath());
					fileDetails.add(fileDetail);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			leaveRequest.setFileDetails(fileDetails);
			leaveRequest.setActive(true);
			leaveRequest.setStatus(false);
			Log.info("leaveRequest object service created.");
			Log.info(leaveRequest.toString());
			
			try {
				leaveRequestRepository.save(leaveRequest);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save Leave Request.");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Leave Request Not recieved, try again letter.");
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Leave request recieved, please wait till your request is approved.");
	}

	

}
