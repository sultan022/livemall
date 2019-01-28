package com.chatapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_review_rating")

public class UserReviewRating {

	public UserReviewRating() {
		super();
	}

	public UserReviewRating(Integer userId, Integer raterId, String raterName, Integer ratingValue, String review) {
		super();
		this.userId = userId;
		this.raterId = raterId;
		this.raterName = raterName;
		this.ratingValue = ratingValue;
		this.review = review;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "rater_id")
	private Integer raterId;

	@Column(name = "rater_name")
	private String raterName;

	@Column(name = "rating_value")
	private Integer ratingValue;

	@Column(name = "review")
	private String review;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRaterId() {
		return raterId;
	}

	public void setRaterId(Integer raterId) {
		this.raterId = raterId;
	}

	public Integer getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRaterName() {
		return raterName;
	}

	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}

}
