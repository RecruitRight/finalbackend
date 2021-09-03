package com.gcp.recruitRight.Impls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class SessionManagement {
	
	private static HashMap<String,String> sessions = new HashMap<String, String>();
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void addSession(String userId, String sessionId) throws Exception
	{
		try {
			sessions.put(userId, sessionId);
			String sql = "INSERT into SESSION(userId,sessionId) values(?,?)";
			jdbcTemplate.update(sql,userId,sessionId);
		} 
		catch(Exception e) {
			throw new Exception("Error creating Session");
		}
	}
	
	
	public void removeSession(String userId) throws Exception
	{
		try {
			sessions.remove(userId);
			String sql = "DELETE from SESSION where userId=?";
			jdbcTemplate.update(sql,userId);
		} 
		catch(Exception e) {
			throw new Exception("Session unavailable");
		}
	}
	
	public void updateSession(String userId,String sessionId) throws Exception
	{
		try {
			sessions.put(userId,sessionId);
			String sql = "UPDATE SESSION SET sessionId=? where userId=?";
			jdbcTemplate.update(sql,sessionId,userId);
		}
		catch(Exception e) {
			throw new Exception("Can't update session");
		}
	}
	
	public static String getSessionId(String userId)
	{
		return sessions.get(userId);
	}
	
	public static String getUserId(String sessionId)
	{
		String array[] = sessionId.split("#");
		return array[0];
	}

}
