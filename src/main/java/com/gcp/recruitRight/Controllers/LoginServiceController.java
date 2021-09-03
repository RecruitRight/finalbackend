package com.gcp.recruitRight.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.LoginServiceImpl;
import com.gcp.recruitRight.Requests.LoginServiceRequest;
import com.gcp.recruitRight.response.BaseResponse;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class LoginServiceController {
	
	@Autowired
	private LoginServiceImpl loginServiceImpl; 

	
	@PostMapping("/signup")
	public ResponseEntity<BaseResponse> signup(@RequestBody LoginServiceRequest loginServiceRequest) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			baseResponse.setBooleanMsg(loginServiceImpl.signup(loginServiceRequest));
		} catch(Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(baseResponse);
	}
	
	@GetMapping("/login")
	public ResponseEntity<BaseResponse> login(@RequestBody LoginServiceRequest loginServiceRequest) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String sessionId = loginServiceImpl.login(loginServiceRequest);
			if(sessionId!=null) {
				baseResponse.setBooleanMsg(true);
				baseResponse.setSessionId(sessionId);
				baseResponse.setUser(loginServiceImpl.fetchUserById(loginServiceRequest));
			}
			else
				baseResponse.setBooleanMsg(false); 
		}
		catch(Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false); 
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<BaseResponse> logout(@RequestBody LoginServiceRequest loginServiceRequest){
		BaseResponse baseResponse = new BaseResponse();
		try {
			if(loginServiceImpl.logout(loginServiceRequest))
				baseResponse.setBooleanMsg(true);
			else
				baseResponse.setBooleanMsg(false);
		} catch (Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		return ResponseEntity.ok(baseResponse);
	}
	
}
