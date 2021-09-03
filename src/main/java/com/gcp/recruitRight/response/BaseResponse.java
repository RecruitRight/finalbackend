package com.gcp.recruitRight.response;

import java.util.List;

import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;

public class BaseResponse {
	
	private User user;
	private Boolean booleanMsg;
	private String exceptionMessage;
	private String sessionId;
	private Requirement requirement;
	private List<User> users;
	private List<Requirement> requirements;
	

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getBooleanMsg() {
		return booleanMsg;
	}
	public void setBooleanMsg(Boolean booleanMsg) {
		this.booleanMsg = booleanMsg;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Requirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
}
