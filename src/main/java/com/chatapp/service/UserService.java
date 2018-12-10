package com.chatapp.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.domain.UserData;
import com.chatapp.dto.UserLogin;
import com.chatapp.repository.UserRepository;
import com.chatapp.response.CustomResponse;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// @Autowired
	// CustomResponse customResponse;

	public void signup(@Valid UserData userData, CustomResponse customResponse) throws CustomException {
		try {
			userRepository.save(userData);
			customResponse.setCustomMessage("user created");

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	public UserData login(@Valid UserLogin userLogin) throws CustomException {
		try {

			return userRepository.findUserByEmailAndPassword(userLogin.getUsername(), userLogin.getPassword());
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	public void editAccountInfo(@Valid UserData userData, CustomResponse customResponse, String email) throws CustomException {

		try {
			Integer id = userRepository.findUserIdbyEmail(email);
			if (id == null)
				throw new CustomException("user does not exists!");
			String picPath = UtilBase64Image.saveBase64StringAsImage(userData.getProfilePic(), email);
			userData.setProfilePic(picPath);
			userData.setId(id);
			userData.setEmail(email);
			userRepository.save(userData);
			customResponse.setCustomMessage("Account Info updated");

		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	public UserData getAccountInfo(String email) throws CustomException {

		try {

			UserData userDataFromDB = userRepository.findUserbyEmail(email);
			userDataFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userDataFromDB.getProfilePic()));
			return userDataFromDB;

		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

}
