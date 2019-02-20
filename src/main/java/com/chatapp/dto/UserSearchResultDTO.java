package com.chatapp.dto;

import com.chatapp.response.UserSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSearchResultDTO {


    private List<UserSearchResponse> userSearchResponses;

}
