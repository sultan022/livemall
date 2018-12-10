package com.chatapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
	@ResponseStatus(HttpStatus.CREATED)
	public CustomResponse signup(@Valid @RequestBody UserData userData) throws CustomException {

		CustomResponse customResponse = new CustomResponse();

		if (userData.getUserType() != null){
			
			userData.setName(userData.getFirstName()+" "+userData.getLastName());
			userService.signup(userData, customResponse);
		}
			

		return customResponse;

	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public UserData login(@Valid @RequestBody UserLogin userLogin) throws CustomException {

			return userService.login(userLogin);

	}
	

	@PutMapping("/accountinfo/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomResponse editAccountInfo(@Valid @RequestBody UserData userLogin, @PathVariable String email ) throws CustomException {

		CustomResponse customResponse = new CustomResponse();
		 userService.editAccountInfo(userLogin, customResponse, email);
		 return customResponse;

	}
	
	@GetMapping("/accountinfo/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public UserData getAccountInfo(@PathVariable String email) throws CustomException {

		return userService.getAccountInfo(email);
		

	}
	
	

}
