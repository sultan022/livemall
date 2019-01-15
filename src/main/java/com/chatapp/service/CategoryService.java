package com.chatapp.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.domain.Category;
import com.chatapp.dto.AddCategoryDTO;
import com.chatapp.dto.CategoryDTO;
import com.chatapp.repository.CategoryRepository;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<CategoryDTO> getCategories(String lang) {

		 
		
		List<CategoryDTO> categories = categoryRepository.findCategoriesByLang(lang).
				stream().
				filter(entity -> entity != null)
				.map(mapper -> mapCategoryToCategoryDTO(mapper)).collect(Collectors.toList());
		
		if(categories.isEmpty())
			throw new CustomException("No Categories Exists");
		
		return categories;

	}

	public Category createCategoryFromCategoryDTO(AddCategoryDTO addCategoryDTO) {

			return modelMapper.map(addCategoryDTO, Category.class);
	

	}

	public Category saveCategory(Category category) {

		

			Optional<Category> categoryFromDB = categoryRepository.checkCategoryExistsByDefaultName(category.getCategoryDefaultName(),
					category.getCategoryName(),
					category.getLang());
			
			if(categoryFromDB.isPresent())
				throw new CustomException("Category Already Exists by "+category.getCategoryDefaultName()+" name");
			
			String iconPath = UtilBase64Image.saveCategoryIconInDirectory(category.getCategoryIcon(),
					category.getCategoryDefaultName());

			if (iconPath != null) {
				category.setCategoryIcon(iconPath);
			} else
				throw new CustomException("Exception while creating image");

			categoryRepository.save(category);

			return category;
		

	}

	public CategoryDTO mapCategoryToCategoryDTO(Category category) {

		String categoryIcon = UtilBase64Image.getImageFromDirectory(category.getCategoryIcon());

		return new CategoryDTO(category.getCategoryName(), categoryIcon, category.getCategoryDefaultName(),
				category.getLang());
	}

}
