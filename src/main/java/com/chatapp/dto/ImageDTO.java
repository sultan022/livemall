package com.chatapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImageDTO {

	
	private String imageString;
	@JsonIgnore
	private String imagePath;

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
