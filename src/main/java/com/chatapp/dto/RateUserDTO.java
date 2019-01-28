package com.chatapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
public class RateUserDTO {

	public RateUserDTO() {
		super();
	}
	public RateUserDTO(@Email @NotNull String sourceUserEmail, @Email @NotNull String targetUserEmail,
			@NotNull Integer rating, @NotNull String review) {
		super();
		this.sourceUserEmail = sourceUserEmail;
		this.targetUserEmail = targetUserEmail;
		this.rating = rating;
		this.review = review;
	}
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
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}





}
