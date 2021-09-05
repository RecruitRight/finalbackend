package com.gcp.recruitRight.Impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.UserProfileRepository;
import com.gcp.recruitRight.Requests.UserProfileRequest;
import com.gcp.recruitRight.models.Feedback;
import com.gcp.recruitRight.models.ProfileScore;
import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;
import com.gcp.recruitRight.models.UserProfile;
import com.gcp.recruitRight.response.UserProfileResponse;
import com.gcp.recruitRight.response.UserProfileStatus;

@Service
public class UserProfileImpl {
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	Logger log = LoggerFactory.getLogger(UserProfileImpl.class);
	
	public User fetchUserDetails(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchUserDetails()");
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
		{	
			User u= userProfileRepository.fetchUser(SessionManagement.getUserId(userProfileRequest.getSessionId()));
			log.info("Exiting UserProfileImpl.fetchUserDetails()");
			return u;
		} 
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchUserDetails()");
		return null;
	}
	
	public boolean editUserDetails(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.editUserDetails()");
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
		{
			int status = userProfileRepository.updateUser(
												SessionManagement.getUserId(userProfileRequest.getSessionId()),
												userProfileRequest.getFirstName(),
												userProfileRequest.getLastName(),
												userProfileRequest.getContact()
												);
			log.info("Exiting UserProfileImpl.editUserDetails()");
			if(status > 0)
				return true;
			return false;
		}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.editUserDetails()");
		return false;
	}
	
	public List<User> fetchAllUsers(UserProfileRequest userProfileRequest) throws Exception
	{	
		log.info("Entering UserProfileImpl.fetchAllUsers()");
		try
		{
			if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
			{
				List<User> users = userProfileRepository.fetchAllUsers();
				log.info("Exiting UserProfileImpl.ftechAllUsers()");
				return users;
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchAllUsers()");
		return null;
	}
	
	public List<Requirement> fetchRequirementById(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchRequirementById()");
		try
		{
			String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
			if(loginServiceImpl.validate(userId,userProfileRequest.getSessionId()))
			{
				List<Requirement> requirements = userProfileRepository.fetchRequirementById(userId);
				log.info("Exiting UserProfileImpl.fetchRequirementById()");
				return requirements;
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchRequirementById()");
		return null;
	}
		
		
	public List<Requirement> fetchAllRequirements(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchAllrequirements()");
		try
		{
			if(loginServiceImpl.validate(SessionManagement.getUserId(userProfileRequest.getSessionId()),userProfileRequest.getSessionId()))
			{
				List<Requirement> requirements = userProfileRepository.fetchAllRequirements();
				log.info("Exiting UserProfileImpl.fetchAllRequirements()");
				return requirements;
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchAllRequirements()");
		return null;
	}
	
	public List<UserProfileStatus> fetchUserProfileScores(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchUserProfileScores()");
		ArrayList<UserProfileStatus> userProfileStatusList = new ArrayList<UserProfileStatus>();
		try
		{
			String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
			if(loginServiceImpl.validate(userId,userProfileRequest.getSessionId()))
			{
				List<ProfileScore> profileScores = userProfileRepository.fetchProfileScoresById(userId);
				for(ProfileScore profileScore : profileScores)
				{
					UserProfileStatus ups = new UserProfileStatus();
					ups.setProfileScore(profileScore.getProfileScore());
					ups.setStatus(profileScore.getStatus());
					List<Requirement> requirements = userProfileRepository.fetchRequirementByReqId(profileScore.getReqId());
					for(Requirement req: requirements)
					{
						ups.setIsu(req.getIsu());
						ups.setSubIsu(req.getSubIsu());
						ups.setProjectName(req.getProjectName());
						ups.setJobRole(req.getJobRole());
						ups.setJobRoleType(req.getJobRoleType());
					}
					userProfileStatusList.add(ups);
				}
				log.info("Exiting UserProfileImpl.fetchUserProfileScores()");
				return userProfileStatusList;
			}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.getchUserProfileScores()");
		return null;
	}
	
	
	public List<Feedback> fetchInProgressProfilesForPOC(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchInProgressProfilesForPOC()");
		String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
		try {
		if(loginServiceImpl.validate(userId, userProfileRequest.getSessionId()))
		{
			List<Feedback> feedbackList = userProfileRepository.fetchInProgressProfilesForPOC(userId);
			log.info("Exiting UserProfileImpl.fetchInProgressProfilesForPOC()");
			return feedbackList;
		}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchInProgressProfilesForPOC()");
		return null;
	}
	
	
	public List<UserProfileResponse> fetchUserProfilesForRMG(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.fetchUserProfilesForRMG()");
		
		ArrayList<UserProfileResponse> userProfileResponse = new ArrayList<UserProfileResponse>();
		
		String uploader = SessionManagement.getUserId(userProfileRequest.getSessionId());
		try {
		if(loginServiceImpl.validate(uploader, userProfileRequest.getSessionId()))
		{
			List<UserProfile> userProfiles =  userProfileRepository.fetchUserProfilesForRMG(uploader);
			
			for(UserProfile userProfile:userProfiles)
			{
				UserProfileResponse upr = new UserProfileResponse();
				
				upr.setUserId(userProfile.getUserId());
				upr.setName(userProfile.getName());
				upr.setContact(userProfile.getContact());
				upr.setStatus(userProfile.getStatus());
				if(userProfile.getStatus().equals("Selected"))
				{
					Requirement req = userProfileRepository.fetchRequirementForSelectedUser(userProfile.getUserId());
					upr.setReqId(req.getReqId());
					upr.setProjectName(req.getProjectName());
				}
				else
				{
					upr.setProjectName("Unallocated");
				}
				userProfileResponse.add(upr);
			}
			log.info("Exiting UserProfileImpl.fetchUserProfilesForRMG()");
			return userProfileResponse;
		}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.fetchUserProfilesForRMG()");
		return null;
	}
	
	public void sendEmail(String toMailId, String OTP)
	{
		log.info("Entering UserProfileImpl.sendEmail()");
		try {
        	
        	String User="recruitright.team@gmail.com";
            String Pass="recruitright";
            String Subject="Verification Code to Reset Password";
            String mssg = "Hello,\nVerification Code to Reset your Password is: "+OTP;     
            
            Properties props = new Properties();
    		props.put("mail.smtp.host", "smtp.gmail.com");
    		props.put("mail.smtp.port", "587");		
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.smtp.starttls.enable", "true");
    		Session session = Session.getInstance
    				(
    					props,new javax.mail.Authenticator()
    					{
    						protected PasswordAuthentication getPasswordAuthentication() 
    						{
    						return new PasswordAuthentication(User,Pass);
    						}
    					}
    				); 
    			MimeMessage message = new MimeMessage(session);
    			message.setFrom(new InternetAddress(User));
    			message.addRecipient(Message.RecipientType.TO,new InternetAddress(toMailId));
    			message.setSubject(Subject);
    			MimeBodyPart messagePart = new MimeBodyPart();
    			messagePart.setText(mssg);
    			Multipart multipart = new MimeMultipart();
    			multipart.addBodyPart(messagePart);
    			message.setContent(multipart);
    			Transport.send(message); 
    			log.info("Exiting UserProfileImpl.sendEmail()");
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	public boolean sendVerificationCode(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.sendVerificationCode()");
		String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
		try 
		{	
			if(loginServiceImpl.validate(userId, userProfileRequest.getSessionId()))
			{
				Random random = new Random();
				String OTP = String.format("%04d", random.nextInt(10000));
				PasswordManagement.addOtp(userId, OTP);
				sendEmail(userId, OTP);
				log.info("Exiting UserProfileImpl.sendVerificationCode()");
			}
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public boolean changePassword(UserProfileRequest userProfileRequest) throws Exception
	{
		log.info("Entering UserProfileImpl.changePassword()");
		String userId = SessionManagement.getUserId(userProfileRequest.getSessionId());
		int flag=0;
		try 
		{	
			if(loginServiceImpl.validate(userId, userProfileRequest.getSessionId()))
			{
				if(userProfileRequest.getVerificationCode().equals(PasswordManagement.getOtp(userId)))
					flag = userProfileRepository.updateUserPassword(userId,userProfileRequest.getPassword());
				else
					throw new Exception("Enter valid Verification code");
			}
			if(flag>0)
			{
				log.info("Exiting UserProfileImpl.changePassword()");
				return true;
			}
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		log.info("Exiting UserProfileImpl.changePassword()");
		return false;
	}	

}
