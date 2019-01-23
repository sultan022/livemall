/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author faheem.sultan
 */
@Data @AllArgsConstructor @NoArgsConstructor
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

	@Column(name = "nick_name")
	private String nickname;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String fullName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	@Email
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "email", unique = true)
	@NotNull(message = "email required!")
	private String email;
	@Basic(optional = false)
	@Size(min = 1, max = 200)
	@Column(name = "password")
	@NotNull(message = "password required!")
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
	@Column(name = "user_type")
	@NotNull(message = "user type required!")
	private UserType userType;

	@Column(name = "profile_pic_path")
	private String profilePic;

	@Column(name = "follower_count")
	private Integer followerCount;
	
	@Column(name = "following_count")
	private Integer followingCount;

	@Column(name="avg_rating")
	private Double avgRating;
	@Column(name="rating_count")
	private Integer ratingCount;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn (name = "id")
	private Collection<Product> productCollection;


}

	