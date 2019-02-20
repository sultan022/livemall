package com.chatapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryResponse {
    private String name;
    private String categoryIcon;
    private String categoryDefaultName;
    private String lang;

}


