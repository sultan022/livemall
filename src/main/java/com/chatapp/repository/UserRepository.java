package com.chatapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatapp.domain.UserData;

public interface UserRepository extends CrudRepository<UserData, Integer> {

	
	@Query(value="select* from user_data where email=:username and password=:password",nativeQuery=true )
	UserData findUserByEmailAndPassword(@Param("username") String username, @Param("password") String password);

	@Query(value="select id from user_data where email=:email",nativeQuery=true )
	Integer findUserIdbyEmail(@Param("email") String email);

	@Query(value="select * from user_data where email=:email",nativeQuery=true )
	UserData findUserbyEmail(@Param("email")String email);

}
