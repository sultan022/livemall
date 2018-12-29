/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author faheem.sultan
 */
@Entity
@Table(name = "user_data", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "phone_number" }))
public class UserData implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@JsonIgnore
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "last_name")
	private String lastName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "nick_name")
	private String nickName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String name;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "email", unique = true)
	@NotNull(message = "email required!")
	private String email;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "password")
	@NotNull(message = "password required!")
	@JsonIgnore
	private String password;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "city")
	private String city;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "country")
	private String country;
	
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "store_name")
	private String storeName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "category")
	private String category;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "user_type")
	@NotNull(message = "user type required!")
	private String userType;
	
	
	@Column(name = "profile_pic_path")
	private String profilePic;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userId")
	private Collection<Product> productCollection;
	

	public UserData() {
	}

	public UserData(Integer id) {
		this.id = id;
	}

	public UserData(Integer id, String firstName, String lastName, String nickName, String phoneNumber, String email,
			String password, String city,String country, String storeName, String category, String userType, String name, String profilePic) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country= country;
		this.storeName = storeName;
		this.category = category;
		this.userType = userType;
		this.name= name;
		this.profilePic= profilePic;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	@NotNull
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
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

	public Collection<Product> getProductCollection() {
		return productCollection;
	}

	public void setProductCollection(Collection<Product> productCollection) {
		this.productCollection = productCollection;
	}

}
