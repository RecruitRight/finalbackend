package com.gcp.recruitRight.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.UserProfileRepository;
import com.gcp.recruitRight.Requests.UserProfileRequest;
import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;

@Service
public class UserProfileImpl {
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	public User fetchUserDetails(UserProfileRequest userProfileRequest) throws Exception
	{
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
				return userProfileRepository.fetchUser(SessionManagement.getUserId(userProfileRequest.getSessionId()));
		} 
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public boolean editUserDetails(UserProfileRequest userProfileRequest) throws Exception
	{
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
		{
			int status = userProfileRepository.updateUser(
												SessionManagement.getUserId(userProfileRequest.getSessionId()),
												userProfileRequest.getFirstName(),
												userProfileRequest.getLastName(),
												userProfileRequest.getContact()
												);
			if(status > 0)
				return true;
			return false;
		}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return false;
	}
	
	public List<User> fetchAllUsers(UserProfileRequest userProfileRequest) throws Exception
	{
		try
		{
			if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
				return userProfileRepository.fetchAllUsers();
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	public List<Requirement> fetchRequirementById(UserProfileRequest userProfileRequest) throws Exception
	{
		try
		{
			String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
			if(loginServiceImpl.validate(userId,userProfileRequest.getSessionId()))
				return userProfileRepository.fetchRequirementById(userId);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
		
		
	public List<Requirement> fetchAllRequirements(UserProfileRequest userProfileRequest) throws Exception
	{
		try
		{
			if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
				return userProfileRepository.fetchAllRequirements();
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}
	

	

}
