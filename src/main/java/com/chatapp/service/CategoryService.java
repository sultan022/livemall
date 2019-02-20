package com.chatapp.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.chatapp.dto.response.CategoryResponse;
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

    public CategoryDTO getCategories(String lang, String channel) {

        List<CategoryResponse> categories = categoryRepository.findCategoriesByLang(lang).
                stream().
                filter(entity -> entity != null)
                .map(mapper -> mapCategoryToCategoryDTO(mapper, channel)).collect(Collectors.toList());

        if (categories.isEmpty())
            throw new CustomException("No Categories Exists");

        CategoryDTO categoryDTO = new CategoryDTO(categories);
        return categoryDTO;

    }

    public Category createCategoryFromCategoryDTO(AddCategoryDTO addCategoryDTO) {

        return modelMapper.map(addCategoryDTO, Category.class);


    }

    public Category saveCategory(Category category) {


        Optional<Category> categoryFromDB = categoryRepository.checkCategoryExistsByDefaultName(category.getCategoryDefaultName(),
                category.getLang());

        if (categoryFromDB.isPresent())
            throw new CustomException("Category Already Exists by name" + category.getCategoryDefaultName() +" and lang " + category.getLang());


        String iconPathIos = UtilBase64Image.saveCategoryIconInDirectory(category.getCategoryIosIcon(),
                category.getCategoryDefaultName(), "ios");

        String iconPathAndroid = UtilBase64Image.saveCategoryIconInDirectory(category.getCategoryAndroidIcon(),
                category.getCategoryDefaultName(), "android");

        if (iconPathIos != null && iconPathAndroid != null) {
            category.setCategoryAndroidIcon(iconPathAndroid);
            category.setCategoryIosIcon(iconPathIos);
        } else
            throw new CustomException("Exception while creating image");

        categoryRepository.save(category);

        return category;


    }

    public CategoryResponse mapCategoryToCategoryDTO(Category category, String channel) {
        String categoryIcon = "";

        if (channel.equalsIgnoreCase("ios"))
            categoryIcon = UtilBase64Image.getImageFromDirectory(category.getCategoryIosIcon());
        else if (channel.equalsIgnoreCase("android"))
            categoryIcon = UtilBase64Image.getImageFromDirectory(category.getCategoryAndroidIcon());


        return new CategoryResponse(category.getCategoryName(), categoryIcon, category.getCategoryDefaultName(),
                category.getLang());
    }

}
