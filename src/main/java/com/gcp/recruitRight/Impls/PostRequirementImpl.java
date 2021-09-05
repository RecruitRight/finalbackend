package com.gcp.recruitRight.Impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.PostRequirementRepository;
import com.gcp.recruitRight.Requests.PostRequirementRequest;

@Service
public class PostRequirementImpl {
	
	@Autowired
	PostRequirementRepository postRequirementRepository;
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	Logger log = LoggerFactory.getLogger(PostRequirementImpl.class);
	
	public Boolean postRequirement(PostRequirementRequest postRequirementRequest) throws Exception{
		try {
		log.info("Entering PoastRequirementImpl.postRequirement()");
		if(loginServiceImpl.validate(SessionManagement.getUserId(postRequirementRequest.getSessionId()),postRequirementRequest.getSessionId()))
		{
			int status = postRequirementRepository.postRequirement(postRequirementRequest);
			log.info("Exiting PoastRequirementImpl.postRequirement()");
			if(status==1)
				return true;
			return false;
		}
		else
			throw new Exception("Invalid session");
		} catch(Exception e) {
			log.error("Exception occured in postRequirementImpl.postRequirement()");
			throw new Exception("Invalid session");
		}
	}

}
