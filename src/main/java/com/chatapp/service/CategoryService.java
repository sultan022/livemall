package com.chatapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.dto.CategoryDTO;
import com.chatapp.repository.CategoryRepository;
import com.chatapp.util.CustomException;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryDTO> getCategories(String lang) {

		if (lang.equals("en")) {

			return categoryRepository.findEnglishCategories()
					.stream()
					.filter(entity-> entity!=null && entity.length()>0)
					.map(mapper -> new CategoryDTO(mapper))
					.collect(Collectors.toList());

		}

		else
			throw new CustomException("langauge is not applicable");

	}

}
