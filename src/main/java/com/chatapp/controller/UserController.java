package com.chatapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.domain.UserData;
import com.chatapp.dto.AddFollower;
import com.chatapp.dto.UserDtoWithProducts;
import com.chatapp.dto.UserLogin;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.UserService;
import com.chatapp.util.CustomException;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/rest/user")
public class UserController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "Register a new user in the system")
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> signup(@Valid @RequestBody UserData userData,
			@Valid @RequestHeader(value = "lang") String lang) throws CustomException {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		// userData.setName(userData.getFirstName()+" "+userData.getLastName());

		userService.signup(userData);
		customResponse.setData(userData);
		customResponse.setMessage("User Created");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Validate User")
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin,
			@Valid @RequestHeader(value = "lang") String lang) throws CustomException {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		customResponse.setData(userService.login(userLogin));
		customResponse.setMessage("User Found");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit User's account info")
	@PutMapping("/accountinfo/{email}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> editAccountInfo(@Valid @RequestBody UserData userData, 
			@PathVariable String email,
			@Valid @RequestHeader(value = "lang") String lang)
			throws CustomException {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		userService.editAccountInfo(userData, email);

		customResponse.setData(userData);
		customResponse.setMessage("User Updated");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Get a User and List of Products By User Email")
	@GetMapping("/getuserandproducts")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> fetchUsertoView(@Valid @RequestHeader(value = "userEmail") String userEmail,
			@Valid @RequestHeader(value = "lang") String lang)
			throws CustomException, Exception {

		CustomResponse<UserDtoWithProducts> customResponse = new CustomResponse<>();

		customResponse.setData(userService.getUserAndProductsDetails(userEmail));
		customResponse.setMessage("User Found");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "add a new Follower for a particular user")
	@PostMapping("/addfollower")
	@ResponseStatus(HttpStatus.OK)
	public <T> ResponseEntity<?> addFollower(@Valid @RequestBody AddFollower addFollower,
			@Valid @RequestHeader(value = "lang") String lang)
			throws CustomException, Exception {

		CustomResponse<T> customResponse = new CustomResponse<>();

		userService.addFollower(addFollower);
		customResponse.setMessage("Follower Added");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

}
