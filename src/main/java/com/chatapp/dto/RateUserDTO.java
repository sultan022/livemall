package com.chatapp.dto;

import javax.validation.constraints.NotNull;

public class RateUserDTO {

	@NotNull
	private String sourceUserEmail;
	@NotNull
	private String targetUserEmail;
	@NotNull
	private Integer rating;

	public RateUserDTO(@NotNull String sourceUserEmail, @NotNull String targetUserEmail, @NotNull Integer rating) {
		super();
		this.sourceUserEmail = sourceUserEmail;
		this.targetUserEmail = targetUserEmail;
		this.rating = rating;
	}

	public String getSourceUserEmail() {
		return sourceUserEmail;
	}

	public void setSourceUserEmail(String sourceUserEmail) {
		this.sourceUserEmail = sourceUserEmail;
	}

	public String getTargetUserEmail() {
		return targetUserEmail;
	}

	public void setTargetUserEmail(String targetUserEmail) {
		this.targetUserEmail = targetUserEmail;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
