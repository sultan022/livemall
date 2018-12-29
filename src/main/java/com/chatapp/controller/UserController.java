package com.chatapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.domain.UserData;
import com.chatapp.dto.UserLogin;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.UserService;
import com.chatapp.util.CustomException;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse<UserData> signup(@Valid @RequestBody UserData userData)  {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

			userData.setName(userData.getFirstName()+" "+userData.getLastName());
			try {
				userService.signup(userData);
				customResponse.setData(userData);
				customResponse.setMessage("User Created");
				customResponse.setResponseCode(HttpStatus.OK);
			} catch (CustomException e) {
				e.printStackTrace();
				customResponse.setMessage(e.getMessage());
				customResponse.setResponseCode(HttpStatus.BAD_REQUEST);
				
			}
			
		return customResponse;

	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse<UserData> login(@Valid @RequestBody UserLogin userLogin) {

		CustomResponse<UserData> customResponse = new CustomResponse<>();
		
		try {
			
			customResponse.setData(userService.login(userLogin));
			customResponse.setMessage("User Found");
			customResponse.setResponseCode(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);
			
		}
		
		return customResponse;
		
		

	}
	

	@PutMapping("/accountinfo/{email}")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse<UserData> editAccountInfo(@Valid @RequestBody UserData userData, @PathVariable String email ) {

		CustomResponse<UserData> customResponse = new CustomResponse<>();
		
		 try {
			userService.editAccountInfo(userData,email);
			
			customResponse.setData(userData);
			customResponse.setMessage("User Updated");
			customResponse.setResponseCode(HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			
			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);
			
			
		}
		 return customResponse;

	}
	
	
	
	

}
