package com.chatapp.controller;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.chatapp.domain.UserData;
import com.chatapp.dto.AddFollower;
import com.chatapp.dto.RateUserDTO;
import com.chatapp.dto.UserDtoWithProducts;
import com.chatapp.dto.UserLogin;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.UserService;
import com.chatapp.util.CustomException;
import com.chatapp.util.DeferredResults;

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
			@Valid @RequestParam("lang") String lang) throws CustomException {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		userService.signup(userData);
		customResponse.setData(userData);
		customResponse.setMessage("Success");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Validate User")
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin,
			@Valid @RequestParam("lang") String lang) throws CustomException, Exception {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		customResponse.setData(userService.login(userLogin));
		customResponse.setMessage("Success");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit User's account info")
	@PutMapping("/accountinfo/{email}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> editAccountInfo(@Valid @RequestBody UserData userData, @PathVariable String email,
			@Valid @RequestParam("lang") String lang) throws CustomException {

		CustomResponse<UserData> customResponse = new CustomResponse<>();

		userService.editAccountInfo(userData, email);

		customResponse.setData(userData);
		customResponse.setMessage("Success");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Get a User and List of Products By User Email")
	@GetMapping("/getuserandproducts")
	@ResponseStatus(HttpStatus.OK)
	public <T> DeferredResult<ResponseEntity<?>> fetchUsertoView(
			@Valid @RequestHeader(value = "userEmail") String userEmail,
			@Valid @RequestParam("lang") String lang) throws CustomException, Exception {

		CustomResponse<UserDtoWithProducts> customResponse = new CustomResponse<>();

		userService.getUserAndProductDetilasWithPagination(userEmail,1,2);

		return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

			try {
				customResponse.setData(userService.getUserAndProductsDetails(userEmail));
				customResponse.setMessage("Success");
				customResponse.setResponseCode(HttpStatus.OK);
			} catch (CustomException e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException("Exception");
			}

			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		}));

	}

	@ApiOperation(value = "add a new Follower for a particular user")
	@PostMapping("/addfollower")
	@ResponseStatus(HttpStatus.OK)
	public <T> ResponseEntity<?> addFollower(@Valid @RequestBody AddFollower addFollower,
			@Valid @RequestParam("lang") String lang) throws CustomException, Exception {

		CustomResponse<T> customResponse = new CustomResponse<>();

		userService.addFollower(addFollower);
		customResponse.setMessage("Success");
		customResponse.setMessageForUser("Successfully Followed");
		customResponse.setResponseCode(HttpStatus.OK);

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "provide rating for a user")
	@PostMapping("/providereviewandrating")
	@ResponseStatus(HttpStatus.OK)
	public <T> DeferredResult<ResponseEntity<?>> addRating(@Valid @RequestBody RateUserDTO rateUserDto,
			@Valid @RequestParam("lang") String lang) throws CustomException, Exception {

		CustomResponse<T> customResponse = new CustomResponse<>();

		return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

			try {
				userService.rateUser(rateUserDto);
				customResponse.setMessage("Success");
				customResponse.setResponseCode(HttpStatus.OK);
			} catch (CustomException e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException("Exception");
			}

			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		}));

	}
	
	

}
