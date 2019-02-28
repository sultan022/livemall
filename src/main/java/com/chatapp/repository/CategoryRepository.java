package com.chatapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatapp.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query(value = "select * from category where lang=:lang", nativeQuery = true)
	List<Category> findCategoriesByLang(@Param("lang") String lang);

	@Query(value = "select * from category where category_default_name=:category_default_name and lang=:lang", nativeQuery = true)
	Optional<Category> checkCategoryExistsByDefaultName(@Param("category_default_name") String categoryDefaultName,
			@Param("lang") String lang);

	@Query(value = "select distinct(category_name) from category where lang=:lang", nativeQuery = true)
    Set<String> getCategoryNames(@Param("lang") String lang);

	@Query(value = "select * from category where category_default_name=:categoryDefaultName", nativeQuery = true)
    Category findCategoryByDefaultName(@Param("categoryDefaultName") String categoryDefaultName);
}
