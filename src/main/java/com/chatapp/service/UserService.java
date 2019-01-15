package com.chatapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatapp.domain.Product;
import com.chatapp.domain.UserData;
import com.chatapp.domain.UserFollower;
import com.chatapp.dto.AddFollower;
import com.chatapp.dto.ImageDTO;
import com.chatapp.dto.ProductDTO;
import com.chatapp.dto.RateUserDTO;
import com.chatapp.dto.UserDtoWithProducts;
import com.chatapp.dto.UserLogin;
import com.chatapp.repository.UserFollowerRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserFollowerRepository userFollowerRepository;

	public void signup(@Valid UserData userData) throws CustomException {

		UtilBase64Image.createDirectory(userData.getEmail());
		userData.setProfilePic(" ");
		userRepository.save(userData);

	}

	public UserData login(@Valid UserLogin userLogin) throws CustomException {

		UserData userDataFromDB = userRepository.findUserByEmailAndPassword(userLogin.getUsername(),
				userLogin.getPassword());
		if (userDataFromDB != null) {
			userDataFromDB.setPassword("");
			userDataFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userDataFromDB.getProfilePic()));
		}

		else
			throw new CustomException("Email or Password Does Not Match");

		return userDataFromDB;

	}

	public void editAccountInfo(@Valid UserData userData, String email) throws CustomException {

		Integer id = userRepository.findUserIdbyEmail(email);
		if (id == null)
			throw new CustomException("User Does Not Exists!");

		String userPictureOriginalString = "";
		if (userData.getProfilePic() != null && userData.getProfilePic().length() > 1) {
			userPictureOriginalString = userData.getProfilePic();
			String picPath = UtilBase64Image.saveBase64StringAsImage(userData.getProfilePic(), email);
			userData.setProfilePic(picPath);
		}
		userData.setId(id);
		userData.setEmail(email);
		userRepository.save(userData);

		userData.setProfilePic(userPictureOriginalString);

	}

	public UserData getAccountInfo(String email) throws CustomException {

		UserData userDataFromDB = userRepository.findUserbyEmail(email);
		userDataFromDB.setProfilePic(UtilBase64Image.getImageFromDirectory(userDataFromDB.getProfilePic()));
		return userDataFromDB;

	}

	@Transactional(readOnly = true)
	public UserDtoWithProducts getUserAndProductsDetails(String userEmail) throws CustomException, Exception {

		UserData userData = userRepository.findByEmailOptional(userEmail)
				.orElseThrow(() -> new CustomException("User Not Found"));
		
		userData.setProfilePic(UtilBase64Image.getImageFromDirectory(userData.getProfilePic()));
		userData.getProductCollection().forEach(product -> {

			product.setProductMainImage(UtilBase64Image.getImageFromDirectory(product.getProductMainImage()));
//			product.getImageCollection().forEach(image -> {
//
//				image.setImageString(UtilBase64Image.getImageFromDirectory(image.getImagePath()));
//			});
		});

		
		
		userData.setFollowerCount(userRepository.getFollowerCount(userData.getId()));
		userData.setFollowingCount(userRepository.getFollowingCount(userData.getId()));
		UserDtoWithProducts userDtoWithProducts = new UserDtoWithProducts();

		mapUserEntityToUserDto(userDtoWithProducts, userData);

		return userDtoWithProducts;

	}

	private void mapUserEntityToUserDto(UserDtoWithProducts userDtoWithProducts, UserData userData) {

		List<ProductDTO> productDTOs = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		List<ImageDTO> imageDTOs = new ArrayList<>();
		ImageDTO imageDTO = new ImageDTO();

		userDtoWithProducts.setCategory(userData.getCategory());
		userDtoWithProducts.setCity(userData.getCity());
		userDtoWithProducts.setCountry(userData.getCountry());
		userDtoWithProducts.setEmail(userData.getEmail());
		userDtoWithProducts.setFollowerCount(userData.getFollowerCount());
		userDtoWithProducts.setFollowingCount(userData.getFollowingCount());
		userDtoWithProducts.setFullName(userData.getFullName());
		userDtoWithProducts.setNickname(userData.getNickname());
		userDtoWithProducts.setPhoneNumber(userData.getPhoneNumber());
		userDtoWithProducts.setProfilePic(userData.getProfilePic());
		userDtoWithProducts.setStoreName(userData.getStoreName());
		userDtoWithProducts.setUserType(userData.getUserType());
		userDtoWithProducts.setFollowerCount(userData.getFollowerCount());
		userDtoWithProducts.setFollowingCount(userData.getFollowingCount());
		// convert products

		for (Product product : userData.getProductCollection()) {

			productDTO.setDescription(product.getDescription());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductMainImage(product.getProductMainImage());
			productDTO.setQuantity(product.getQuantity());

			productDTOs.add(productDTO);
			productDTO = new ProductDTO();

		}

		userDtoWithProducts.setProductCollection(productDTOs);

	}

	public void addFollower(AddFollower addFollower) throws CustomException {

		Integer followerId, followingId;

		followerId = userRepository.findUserIdbyEmail(addFollower.getSourceUserEmail());
		if (followerId == null)
			throw new CustomException("user does not exist " + addFollower.getSourceUserEmail());

		followingId = userRepository.findUserIdbyEmail(addFollower.getTargetUSerEmail());

		if (followingId == null)
			throw new CustomException("user does not exist " + addFollower.getTargetUSerEmail());
		// update user1 following

		UserFollower userFollower = new UserFollower();
		userFollower.setUserId(followingId);
		userFollower.setFollowerId(followerId);

		userFollowerRepository.save(userFollower);

	}

	public void rateUser(RateUserDTO rateUserDto) {

	
	}

}
