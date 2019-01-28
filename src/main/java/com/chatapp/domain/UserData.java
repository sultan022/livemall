/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author faheem.sultan
 */

@Entity
@Table(name = "user_data", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "phone_number" }))
public class UserData implements Serializable {

	public UserData() {
	}

	public UserData(String nickname, @NotNull String fullName, @NotNull String phoneNumber,
			@Email @NotNull(message = "email required!") String email,
			@NotNull(message = "password required!") String password, String city, String country,
			@NotNull String storeName, @NotNull String category,
			@NotNull(message = "user type required!") UserType userType, String profilePic, Integer followerCount,
			Integer followingCount, Integer productCount, Double avgRating, Double ratingCount, Double ratingSum,
			Collection<Product> productCollection) {
		super();
		this.nickname = nickname;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
		this.storeName = storeName;
		this.category = category;
		this.userType = userType;
		this.profilePic = profilePic;
		this.followerCount = followerCount;
		this.followingCount = followingCount;
		ProductCount = productCount;
		this.avgRating = avgRating;
		this.ratingCount = ratingCount;
		this.ratingSum = ratingSum;
		this.productCollection = productCollection;
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@JsonIgnore
	private Integer id;

	@Column(name = "nick_name")
	private String nickname;

	@Basic(optional = false)
	@NotNull
	@Column(name = "name")
	private String fullName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	@Email
	@Basic(optional = false)
	@Column(name = "email", unique = true)
	@NotNull(message = "email required!")
	private String email;
	@Basic(optional = false)
	@Column(name = "password")
	@NotNull(message = "password required!")
	private String password;
	@Basic(optional = false)
	@Column(name = "city")
	private String city;
	@Basic(optional = false)
	@Column(name = "country")
	private String country;
	@Basic(optional = false)
	@NotNull
	@Column(name = "store_name")
	private String storeName;
	@Basic(optional = false)
	@NotNull
	@Column(name = "category")
	private String category;
	@Basic(optional = false)
	@Column(name = "user_type")
	@NotNull(message = "user type required!")
	private UserType userType;

	@Column(name = "profile_pic_path")
	private String profilePic;

	@Column(name = "follower_count")
	private Integer followerCount;
	
	@Column(name = "following_count")
	private Integer followingCount;

	@Column(name = "product_count")
	private Integer ProductCount;
	
	@Column(name="avg_rating")
	private Double avgRating;
	@JsonIgnore
	@Column(name="rating_count")
	private Double ratingCount;
	@JsonIgnore
	@Column(name="rating_sum")
	private Double ratingSum;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn (name = "id")
	@JsonIgnore
	private Collection<Product> productCollection;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	public Collection<Product> getProductCollection() {
		return productCollection;
	}

	public void setProductCollection(Collection<Product> productCollection) {
		this.productCollection = productCollection;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductCount() {
		return ProductCount;
	}

	public void setProductCount(Integer productCount) {
		ProductCount = productCount;
	}

	public Double getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Double ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Double getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(Double ratingSum) {
		this.ratingSum = ratingSum;
	}



}

	