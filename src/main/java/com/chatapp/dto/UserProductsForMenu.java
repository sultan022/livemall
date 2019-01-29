package com.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserProductsForMenu {

    private String name;
    private String productMainImage;
    private String price;
}
