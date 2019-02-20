package com.chatapp.dto;

import com.chatapp.response.UserReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDTO {

	private List<UserReviewResponse> userReviewResponses;







	
}
