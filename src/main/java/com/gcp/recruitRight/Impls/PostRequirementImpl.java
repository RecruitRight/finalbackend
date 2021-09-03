package com.gcp.recruitRight.Impls;

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
	
	public Boolean postRequirement(PostRequirementRequest postRequirementRequest) throws Exception{
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(postRequirementRequest.getSessionId()),postRequirementRequest.getSessionId()))
		{
			int status = postRequirementRepository.postRequirement(postRequirementRequest);
			if(status==1)
				return true;
			return false;
		}
		else
			throw new Exception("Invalid session");
		} catch(Exception e) {
			throw new Exception("Invalid session");
		}
	}

}
