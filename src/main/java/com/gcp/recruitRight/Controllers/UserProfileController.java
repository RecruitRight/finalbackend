package com.gcp.recruitRight.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.UserProfileImpl;
import com.gcp.recruitRight.Requests.UserProfileRequest;
import com.gcp.recruitRight.models.Feedback;
import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;
import com.gcp.recruitRight.response.BaseResponse;
import com.gcp.recruitRight.response.UserProfileResponse;
import com.gcp.recruitRight.response.UserProfileStatus;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserProfileController {
	
	Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	UserProfileImpl userProfileImpl;
	
	@GetMapping("/user/getDetails")
	public ResponseEntity<BaseResponse> getUserById(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getUserById()");
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
		log.info("Exiting UserProfileController.getUserById()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@PostMapping("/user/editDetails")
	public ResponseEntity<BaseResponse> editUser(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.editUser()");
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
		log.info("Exiting UserProfileController.editUser()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/userList")
	public ResponseEntity<BaseResponse> getUsers(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getUsers()");
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
		log.info("Exiting UserProfileController.getUsers()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/poc/requirementList")
	public ResponseEntity<BaseResponse> getRequirementById(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getRequirementById()");
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
		log.info("Exiting UserProfileController.getRequirementById()");
		return ResponseEntity.ok(baseResponse);
	}
	
	
	@GetMapping("/requirementList")
	public ResponseEntity<BaseResponse> getRequirements(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getRequirements()");
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
		log.info("Exiting UserProfileController.getRequirements()");
		return ResponseEntity.ok(baseResponse);
	}
	
	
	@GetMapping("/user/profileStatus")
	public ResponseEntity<BaseResponse> getUserProfileStatus(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getUserProfileStatus()");
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<UserProfileStatus> userProfileStatusList = userProfileImpl.fetchUserProfileScores(userProfileRequest);
			baseResponse.setUserProfileStatusList(userProfileStatusList);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UserProfileController.getUserProfileStatus()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/poc/requirement/eligibleProfiles")
	public ResponseEntity<BaseResponse> getInProgressProfileStatusForPOC(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getInProgressProfileStatusForPOC()");
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<Feedback> feedbackList = userProfileImpl.fetchInProgressProfilesForPOC(userProfileRequest);
			baseResponse.setFeedbackList(feedbackList);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UserProfileController.getInProgressProfileStatusForPOC()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/rmg/uploadedProfiles")
	public ResponseEntity<BaseResponse> getUserProfilesForRMG(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.getUserProfilesForRMG()");
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<UserProfileResponse> userProfileResponse = userProfileImpl.fetchUserProfilesForRMG(userProfileRequest);
			baseResponse.setUserProfileResponse(userProfileResponse);
			baseResponse.setBooleanMsg(true);
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UserProfileController.getUserProfilesForRMG()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/forgotPassword")
	public ResponseEntity<BaseResponse> sendVerificationCode(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.sendVerificationCode()");
		BaseResponse baseResponse = new BaseResponse();
		try {
			baseResponse.setBooleanMsg(userProfileImpl.sendVerificationCode(userProfileRequest));
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UserProfileController.sendVerificationCode()");
		return ResponseEntity.ok(baseResponse);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<BaseResponse> changePassword(@RequestBody UserProfileRequest userProfileRequest)
	{
		log.info("Entering UserProfileController.resetPassword()");
		BaseResponse baseResponse = new BaseResponse();
		try {
			baseResponse.setBooleanMsg(userProfileImpl.changePassword(userProfileRequest));
		}
		catch(Exception e)
		{
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UserProfileController.resetPassword()");
		return ResponseEntity.ok(baseResponse);
	}

}
