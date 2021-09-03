package com.gcp.recruitRight.models;

import java.io.ByteArrayInputStream;

public class UserProfile {
	
	private String userId;
	private String name;
	private String contact;
	private ByteArrayInputStream resume;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public ByteArrayInputStream getResume() {
		return resume;
	}
	public void setResume(ByteArrayInputStream resume) {
		this.resume = resume;
	}
	
	
	
}
