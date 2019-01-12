package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query(value = "select category_name_eng from category", nativeQuery = true)
	List<String> findEnglishCategories();

}
