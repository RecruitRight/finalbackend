package com.gcp.recruitRight.Requests;

import java.util.List;

public class UploadProfileRequest {
	
	private List<String> userProfiles;

	public List<String> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<String> userProfiles) {
		this.userProfiles= userProfiles;
	}

}
