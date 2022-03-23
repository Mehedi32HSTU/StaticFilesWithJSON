package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.LeaveRequest;
import com.spring.rest.repository.LeaveRequestRepository;
import com.spring.rest.service.LeaveRequestService;

@RestController
@RequestMapping("/leave")
public class LeaveRequestController {
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	
	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@GetMapping("/all-requests")
	public ResponseEntity<?> allLeaveRequest()
	{
		// need to write custom query for isActive is true. 
		List<LeaveRequest> leaveRequest = leaveRequestRepository.findAllActiveEmployeeLeaveRequest(true);
		if(leaveRequest.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("No Leave Requests Found::");
		
		return ResponseEntity.ok().body(leaveRequest);
	}
	@GetMapping("/{Id}")
	public ResponseEntity<?> leaveRequestByEmployeeId(@PathVariable(value="Id") Long employeeId) throws ResourceNotFoundException	{	
		
		List<LeaveRequest> leaveRequest =  leaveRequestRepository.findLeaveRequestsByEmployeeId(employeeId , true);
		
		if(leaveRequest.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body("Leave Request not found by Id : : "+ employeeId);
		
		return ResponseEntity.ok().body(leaveRequest);
	}
	@PostMapping(value="/send-request", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> sendLeaveRequest(
			@RequestPart(value="request", required = true) String request,
			@RequestPart(value = "file", required = false) List<MultipartFile> files)
	throws ResourceNotFoundException {
		return leaveRequestService.saveLeaveRequest(request, files);
	}
	

}
