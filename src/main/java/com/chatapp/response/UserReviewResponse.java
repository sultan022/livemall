package com.chatapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewResponse {

    String raterName;
    String review;
    Integer ratingValue;
}
