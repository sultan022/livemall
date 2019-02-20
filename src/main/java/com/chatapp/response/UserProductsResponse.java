package com.chatapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProductsResponse {

    private String name;
    private String productMainImage;
    private String price;
}

