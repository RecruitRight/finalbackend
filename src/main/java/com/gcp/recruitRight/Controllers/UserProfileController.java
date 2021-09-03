package com.gcp.recruitRight.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.UserProfileImpl;
import com.gcp.recruitRight.Requests.UserProfileRequest;
import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;
import com.gcp.recruitRight.response.BaseResponse;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserProfileController {
	
	@Autowired
	UserProfileImpl userProfileImpl;
	
	@GetMapping("/user/getDetails")
	public ResponseEntity<BaseResponse> getUserById(@RequestBody UserProfileRequest userProfileRequest)
	{
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = userProfileImpl.fetchUserDetails(userProfileRequest);
			baseResponse.setUser(user);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	@PostMapping("/user/editDetails")
	public ResponseEntity<BaseResponse> editUser(@RequestBody UserProfileRequest userProfileRequest)
	{
		BaseResponse baseResponse = new BaseResponse();
		try {
			boolean msg = userProfileImpl.editUserDetails(userProfileRequest);
			baseResponse.setBooleanMsg(msg);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/userList")
	public ResponseEntity<BaseResponse> getUsers(@RequestBody UserProfileRequest userProfileRequest)
	{
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<User> users = userProfileImpl.fetchAllUsers(userProfileRequest);
			baseResponse.setUsers(users);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/user/requirementList")
	public ResponseEntity<BaseResponse> getRequirementById(@RequestBody UserProfileRequest userProfileRequest)
	{
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<Requirement> requirements = userProfileImpl.fetchRequirementById(userProfileRequest);
			baseResponse.setRequirements(requirements);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	
	@GetMapping("/requirementList")
	public ResponseEntity<BaseResponse> getRequirements(@RequestBody UserProfileRequest userProfileRequest)
	{
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<Requirement> requirements = userProfileImpl.fetchAllRequirements(userProfileRequest);
			baseResponse.setRequirements(requirements);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	
	
	

	
	
}
