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

	public void signup(@Valid UserData userData) throws CustomException {
		try {
			UtilBase64Image.createDirectory(userData.getEmail());
			userData.setProfilePic(" ");
			userRepository.save(userData);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	public UserData login(@Valid UserLogin userLogin) throws CustomException {
		try {

			UserData userDataFromDB = userRepository.findUserByEmailAndPassword(userLogin.getUsername(), userLogin.getPassword());
			if(userDataFromDB==null)
				throw new CustomException("Email or Password Does Not Match");
				
			
			return userDataFromDB;
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	public void editAccountInfo(@Valid UserData userData, String email) throws CustomException {

		try {
			Integer id = userRepository.findUserIdbyEmail(email);
			if (id == null)
				throw new CustomException("User Does Not Exists!");
			
			if(userData.getProfilePic()!=null && userData.getProfilePic().length()>1) {
				String picPath = UtilBase64Image.saveBase64StringAsImage(userData.getProfilePic(), email);
				userData.setProfilePic(picPath);
			}
			userData.setId(id);
			userData.setEmail(email);
			userRepository.save(userData);

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
