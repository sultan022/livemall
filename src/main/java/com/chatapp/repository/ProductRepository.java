package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatapp.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	
	@Query(value="select * from product where user_id =:userId ", nativeQuery=true)
	List<Product> findProductByUserId(@Param ("userId") Integer userId);

	@Query(value="select * from product where name=:name", nativeQuery=true)
	Product findProductByName(@Param("name") String name);
	
	
	// just to handle a method in product service
	@Query(value="select * from product where name=:name", nativeQuery=true)
	List<Product> findProductListByName(@Param("name") String name);

//	@Query(value="select id from product where name=:name", nativeQuery=true)
//	Integer findProductIdByName(String productName);

}
