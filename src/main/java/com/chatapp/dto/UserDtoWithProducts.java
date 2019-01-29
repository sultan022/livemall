package com.chatapp.dto;

import java.util.Collection;

import com.chatapp.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDtoWithProducts {

	
	
	private String nickname;
	
	private String fullName;
	private String phoneNumber;
	private String email;
	private String city;
	private String country;
	private String storeName;
	private String category;
	private UserType userType;
	private String profilePic;
	private Integer followerCount;
	private Integer productCount;


	private Integer followingCount;
	private Double avgRating;
	private Integer ratingCount;
	private Collection<ProductDTO> productCollection;


	
	
}
