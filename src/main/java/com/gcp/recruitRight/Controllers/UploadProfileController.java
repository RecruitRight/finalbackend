package com.gcp.recruitRight.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.UploadProfileImpl;
import com.gcp.recruitRight.Requests.UploadProfileRequest;
import com.gcp.recruitRight.response.BaseResponse;


@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UploadProfileController {

	@Autowired
	UploadProfileImpl uploadProfileImpl;
	
	Logger log = LoggerFactory.getLogger(UploadProfileController.class);
	
	@PostMapping("/uploadProfile")
	public ResponseEntity<BaseResponse> uploadProfile(@RequestBody UploadProfileRequest uploadProfileRequest){
		log.info("Entering UploadProfileControlelr.uploadProfile()");
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			baseResponse.setBooleanMsg(uploadProfileImpl.uploadProfile(uploadProfileRequest));
		} catch (Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		log.info("Exiting UploadProfileControlelr.uploadProfile()");
		return ResponseEntity.ok(baseResponse);
	}
}
