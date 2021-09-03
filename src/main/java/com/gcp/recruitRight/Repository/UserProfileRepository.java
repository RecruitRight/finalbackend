package com.gcp.recruitRight.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcp.recruitRight.models.Requirement;
import com.gcp.recruitRight.models.User;

@Repository
public class UserProfileRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public User fetchUser(String userId)
	{
		String sql = "SELECT * FROM USER where userId=?";
		List<User> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class),userId);
		for(User user:users)
			return user;
		return null;
	}
	
	public int updateUser(String userId, String firstName, String lastName, String contact)
	{
		String sql = "UPDATE USER SET firstName = ?, lastName = ?, contact = ? where userId = ?";
		return jdbcTemplate.update(sql,firstName, lastName, contact, userId);
	}
	
	public List<User> fetchAllUsers()
	{
		String sql = "SELECT * FROM USER";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		return users;
	}
	
	public List<Requirement> fetchRequirementById(String userId)
	{
		String sql = "SELECT * FROM REQUIREMENTS where userId=?";
		List<Requirement> requirements = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Requirement.class),userId);
		return requirements;
	}
	
	public List<Requirement> fetchAllRequirements()
	{
		String sql = "SELECT * FROM REQUIREMENTS";
		List<Requirement> requirements = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Requirement.class));
		return requirements;
	}
}
