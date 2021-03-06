package com.chatapp.dto;

import com.chatapp.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {

	private List<CategoryResponse> categories;



}
