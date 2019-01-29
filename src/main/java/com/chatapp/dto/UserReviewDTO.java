package com.chatapp.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserReviewDTO {

	String raterName;
	String review;
	Integer ratingValue;
	public String getRaterName() {
		return raterName;
	}
	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Integer getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}
	public UserReviewDTO(String raterName, String review, Integer ratingValue) {
		super();
		this.raterName = raterName;
		this.review = review;
		this.ratingValue = ratingValue;
	}
	
}
