package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddCategoryDTO {

	@NotNull
	private String categoryName;
	@NotNull
	private String categoryIosIcon;
	@NotNull
	private String categoryAndroidIcon;
	@NotNull
	private String categoryDefaultName;
	@NotNull
	private String lang;



}
