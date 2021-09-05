package com.gcp.recruitRight.Impls;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class SessionManagement {
	
	private static HashMap<String,String> sessions = new HashMap<String, String>();
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Logger log = LoggerFactory.getLogger(SessionManagement.class);
	
	public void addSession(String userId, String sessionId) throws Exception
	{
		log.info("Entering SessionManagement.addSession()");
		try {
			sessions.put(userId, sessionId);
			String sql = "INSERT into SESSION(userId,sessionId) values(?,?)";
			jdbcTemplate.update(sql,userId,sessionId);
			log.info("Exiting SessionManagement.addSession()");
		} 
		catch(Exception e) {
			throw new Exception("Error creating Session");
		}
	}
	
	
	public void removeSession(String userId) throws Exception
	{
		log.info("Entering SessionManagement.removeSession()");
		try {
			sessions.remove(userId);
			String sql = "DELETE from SESSION where userId=?";
			jdbcTemplate.update(sql,userId);
			log.info("Exiting SessionManagement.removeSession()");
		} 
		catch(Exception e) {
			throw new Exception("Session unavailable");
		}
	}
	
	public void updateSession(String userId,String sessionId) throws Exception
	{
		log.info("Entering SessionManagement.updateSession()");
		try {
			sessions.put(userId,sessionId);
			String sql = "UPDATE SESSION SET sessionId=? where userId=?";
			jdbcTemplate.update(sql,sessionId,userId);
			log.info("Exiting SessionManagement.updateSession()");
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
