package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Data @AllArgsConstructor @NoArgsConstructor
public class RateUserDTO {

	@Email
	@NotNull
	private String sourceUserEmail;
	@Email
	@NotNull
	private String targetUserEmail;
	@NotNull
	private Integer rating;
	@NotNull
	private String review;





}
