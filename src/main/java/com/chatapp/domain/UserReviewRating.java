package com.chatapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_review_rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "rater_id")
    private Integer raterId;

    @Column(name = "rating_value")
    private Integer ratingValue;

    @Column(name = "review")
    private String review;


}
