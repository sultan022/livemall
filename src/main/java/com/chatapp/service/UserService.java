package com.chatapp.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.domain.Image;
import com.chatapp.domain.Product;
import com.chatapp.domain.UserData;
import com.chatapp.dto.AddFollower;
import com.chatapp.dto.ImageDTO;
import com.chatapp.dto.ProductDTO;
import com.chatapp.dto.UserDtoWithProducts;
import com.chatapp.dto.UserLogin;
import com.chatapp.repository.UserRepository;
import com.chatapp.response.CustomResponse;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	ModelMapper modelMapper;

	// @Autowired
	// CustomResponse customResponse;

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
			product.getImageCollection().forEach(image -> {

				image.setImageString(UtilBase64Image.getImageFromDirectory(image.getImagePath()));
			});
		});

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

		// convert products

		for (Product product : userData.getProductCollection()) {

			productDTO.setDescription(product.getDescription());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductMainImage(product.getProductMainImage());
			productDTO.setQuantity(product.getQuantity());
			// imageDTOs.clear();
			// imageDTOs = new ArrayList<>();
			//
			// for(Image image : product.getImageCollection()){
			//
			// imageDTO.setImagePath(image.getImagePath());
			// imageDTO.setImageString(image.getImageString());
			// imageDTOs.add(imageDTO.getImageString()!=null?imageDTO:null);
			// imageDTO = new ImageDTO();
			// }
			//
			// productDTO.setImageDTOList(imageDTOs!=null?imageDTOs:null);
			productDTOs.add(productDTO);
			productDTO = new ProductDTO();

		}

		userDtoWithProducts.setProductCollection(productDTOs);

	}

	public void addFollower(AddFollower addFollower) throws CustomException {

		List<String> userEmails = Arrays.asList(addFollower.getSourceUserEmail(), addFollower.getTargetUSerEmail());

		List<String> usersFromDB = userRepository.checkExistsByEmailReturnIds(userEmails);

		if (usersFromDB.size() == 0)
			throw new CustomException("Users Do not exist");

		if (usersFromDB.size() == 2) {

			// perform logic here

		} else {

			List<String> usersNotAvailable = userEmails.stream()
					.filter(list1Obj -> !usersFromDB.stream()
							.collect(Collectors.toSet())
							.contains(list1Obj))
					.collect(Collectors.toList());
			
			throw new CustomException(usersNotAvailable.toString()+" Not Available in System");

		}

		

	}


}
