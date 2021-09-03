package com.gcp.recruitRight.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcp.recruitRight.models.UserProfile;

@Repository
public class UploadProfileRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<UserProfile> findUserProfiles(String userId){
		String sql = "SELECT * FROM USERPROFILES where userId = ?";
		List<UserProfile> userProfiles = jdbcTemplate.query(sql,new BeanPropertyRowMapper(UserProfile.class),userId);
		return userProfiles;
	}
	
	public int updateUserProfiles(String userId,String pdf) throws Exception {
		try {
			File inp_file = new File(pdf);
			FileInputStream input = new FileInputStream(inp_file);
			String sql = "UPDATE USERPROFILES SET resume=? where userId=?";
			return jdbcTemplate.update(sql,input,userId);	
		} 
		catch (FileNotFoundException e) {
			throw new Exception("File Not Found");
		}
	}
	public int insertIntoUserProfiles(String userId, String name, String contact, String pdf) throws Exception
	{
		int status = 0;
		try {
			File inp_file = new File(pdf);
			FileInputStream input = new FileInputStream(inp_file);
			String sql = "INSERT into USERPROFILES(userId,name,contact,resume) values(?,?,?,?)";
			status = jdbcTemplate.update(sql,userId,name,contact,input);
		}
		catch(Exception e){
			throw new Exception("File Not Found");
		}
		return status;
	}

}
