package com.gcp.recruitRight.Requests;

import java.util.List;

public class UploadProfileRequest {
	
	private List<String> userProfiles;
	private String sessionId;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<String> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<String> userProfiles) {
		this.userProfiles= userProfiles;
	}
	
	
	

}
