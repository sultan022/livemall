package com.chatapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSearchResponse {

    private String fullName;
    private String email;
    private String profilePic;
}
