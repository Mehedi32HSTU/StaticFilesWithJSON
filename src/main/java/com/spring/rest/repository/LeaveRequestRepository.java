package com.spring.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>  {
//	Table Name is leave_request_table
	
//	@Query("SELECT * FROM leave_request ORDER BY leaveRequest.sending_date DESC")
//	List<LeaveRequest> findAllOrderBySendingDateDesc();
	
//	List of all requests which sender employees are active
	@Query(value="Select leave_request_table.* from leave_request_table where leave_request_table.is_active = ?1", nativeQuery = true)
	List<LeaveRequest> findAllActiveEmployeeLeaveRequest(boolean isActive);
	
//	Single employee by given id if the employee is active
	@Query(value="Select leave_request_table.* from leave_request_table where leave_request_table.employee_id= ?1 and leave_request_table.is_active = ?2", nativeQuery = true)
	List<LeaveRequest> findLeaveRequestsByEmployeeId(Long employeeId, boolean isActive);
	
	

}
