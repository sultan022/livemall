package com.chatapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query(value = "select * from category where lang=:lang", nativeQuery = true)
	List<Category> findCategoriesByLang(@Param("lang") String lang);

	@Query(value = "select * from category where category_default_name=:category_default_name and category_name=:category_name and lang=:lang", nativeQuery = true)
	Optional<Category> checkCategoryExistsByDefaultName(@Param("category_default_name") String categoryDefaultName,
			@Param("category_name") String categoryName,
			@Param("lang") String lang);

}
