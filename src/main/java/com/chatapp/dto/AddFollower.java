package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFollower {


	@NotNull
	private String sourceUserEmail;
	@NotNull
	private String targetUserEmail;
	

	
	
}
