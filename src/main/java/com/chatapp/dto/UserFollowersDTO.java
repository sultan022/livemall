package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data @AllArgsConstructor @NoArgsConstructor
public class UserFollowersDTO {

    private Set<UserFollowerDTO> followers;
}
