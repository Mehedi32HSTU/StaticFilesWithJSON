package com.spring.rest.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.rest.exception.ResourceNotFoundException;

public interface LeaveRequestService {
	ResponseEntity<?> saveLeaveRequest( String leaveReq, List<MultipartFile> file )
			throws ResourceNotFoundException;

}
