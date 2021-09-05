package com.gcp.recruitRight.Impls;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.LoginServiceRepository;
import com.gcp.recruitRight.Requests.LoginServiceRequest;
import com.gcp.recruitRight.models.User;

@Service
public class LoginServiceImpl {
	
	@Autowired
	private LoginServiceRepository loginServiceRepository;
	@Autowired
	private SessionManagement sessionManagement;
	
	Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	public boolean signup(LoginServiceRequest loginServiceRequest) throws Exception
	{
		log.info("Entering LoginServiceImpl.signup()");
		List<User> users = loginServiceRepository.findUsers(loginServiceRequest);
		if(users.size()!=0) {
			for(User u : users)
			{
				if(u.getUserId().equals(loginServiceRequest.getUserId()))
					throw new Exception("Email already Registered....Please SignIn to continue");
			}	
		}
		int status = loginServiceRepository.insertIntoUser(loginServiceRequest);
		if(status == 1)
		{
			log.info("Exiting LoginServiceImpl.signup()");
 			return true;
		}
		log.info("Exiting LoginServiceImpl.signup()");
 		return false;
	}
	
	public String login(LoginServiceRequest loginServiceRequest) throws Exception
	{
		log.info("Entering LoginServiceImpl.login()");
		if(loginServiceRepository.verifyUser(loginServiceRequest))
		{
			String sId = loginServiceRepository.fetchSessionId(loginServiceRequest.getUserId());
			if(sId!=null)
			{
				Date dt = new Date();
				String sessionId = loginServiceRequest.getUserId()+"#"+dt.getTime();
				sessionManagement.updateSession(loginServiceRequest.getUserId(),sessionId);
				log.info("Exiting LoginServiceImpl.login()");
				return sessionId;
			}
			else
			{
				Date dt = new Date();
				String sessionId = loginServiceRequest.getUserId()+"#"+dt.getTime();
				sessionManagement.addSession(loginServiceRequest.getUserId(),sessionId);
				log.info("Exiting LoginServiceImpl.login()");
				return sessionId;
			}
		}
		throw new Exception("Can't login");
	}
	
	public User fetchUserById(LoginServiceRequest loginServiceRequest) {
		log.info("Entering LoginServiceImpl.fetchUserById()");
		User user = loginServiceRepository.fetchUserById(loginServiceRequest);
		log.info("Exiting LoginServiceImpl.fetchUserById()");
		return user;
		
	}
	
	public boolean logout(LoginServiceRequest loginServiceRequest) throws Exception
	{
		log.info("Entering LoginServiceImpl.logout()");
		String userId = SessionManagement.getUserId(loginServiceRequest.getSessionId());
		try {
		if(validate(userId,loginServiceRequest.getSessionId())) {
			sessionManagement.removeSession(userId);
			log.info("Exiting LoginServiceImpl.logout()");
			return true;
		}
		} catch(Exception e) {
			throw new Exception("Invalid session");
		}
		return false;
	}
	
	public boolean validate(String userId, String sessionId) throws Exception
	{
		log.info("Entering LoginServiceImpl.validate()");
		String sId = loginServiceRepository.fetchSessionId(userId);
		//if(SessionManagement.getSessionId(userId).equals(sessionId)) {
		if(sId.equals(sessionId)) {
			log.info("Exiting LoginServiceImpl.validate()");
			return true;
		}
		else
		{
			log.error("Invalid Session");
			throw new Exception("Invalid session...");
		}
			
	}
}
