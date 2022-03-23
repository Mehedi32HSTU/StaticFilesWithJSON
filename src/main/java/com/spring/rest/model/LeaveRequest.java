package com.spring.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LeaveRequestTable")
public class LeaveRequest {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private long employeeId;
	private String requestType;
//	private FileDetails attachedFiles;
	@OneToMany(cascade= CascadeType.ALL)
	List<FileDetails> fileDetails = new ArrayList<>();
	private boolean status; 
	private boolean isActive; 
	
	public LeaveRequest() {
		// TODO Auto-generated constructor stub
	}
	public LeaveRequest(long employeeId, String requestType) {
		this.employeeId = employeeId;
		this.requestType = requestType;
//		this.attachedFiles = attachedFiles;
		this.status = false;
		this.isActive=true;
	}
	public long getId() {
		return Id;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
//	public FileDetails getAttachedFiles() {
//		return attachedFiles;
//	}
//	public void setAttachedFiles(FileDetails attachedFiles) {
//		this.attachedFiles = attachedFiles;
//	}
	public List<FileDetails> getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(List<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "LeaveRequest [Id=" + Id + ", employeeId=" + employeeId + ", requestType=" + requestType
				+ ", fileDetails=" + fileDetails.toString() + ", status=" + status + ", isActive=" + isActive + "]";
	}
	
	
}
