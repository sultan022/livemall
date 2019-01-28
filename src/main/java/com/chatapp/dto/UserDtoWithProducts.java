package com.chatapp.dto;

import java.util.Collection;

import com.chatapp.domain.UserType;


public class UserDtoWithProducts {

	
	
	private String nickname;
	public UserDtoWithProducts() {
		super();
	}
	
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
	public UserDtoWithProducts(String nickname, String fullName, String phoneNumber, String email, String city,
			String country, String storeName, String category, UserType userType, String profilePic,
			Integer followerCount, Integer productCount, Integer followingCount, Double avgRating, Integer ratingCount,
			Collection<ProductDTO> productCollection) {
		super();
		this.nickname = nickname;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.country = country;
		this.storeName = storeName;
		this.category = category;
		this.userType = userType;
		this.profilePic = profilePic;
		this.followerCount = followerCount;
		this.productCount = productCount;
		this.followingCount = followingCount;
		this.avgRating = avgRating;
		this.ratingCount = ratingCount;
		this.productCollection = productCollection;
	}

	private Integer followingCount;
	private Double avgRating;
	private Integer ratingCount;
	private Collection<ProductDTO> productCollection;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public Integer getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
	public Integer getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public Integer getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Collection<ProductDTO> getProductCollection() {
		return productCollection;
	}
	public void setProductCollection(Collection<ProductDTO> productCollection) {
		this.productCollection = productCollection;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	
	
}
