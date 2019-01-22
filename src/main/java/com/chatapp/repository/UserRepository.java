package com.chatapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatapp.domain.UserData;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserData, Integer> {

	@Query(value = "select* from user_data where email=:username and password=:password", nativeQuery = true)
	UserData findUserByEmailAndPassword(@Param("username") String username, @Param("password") String password);

	@Query(value = "select id from user_data where email=:email", nativeQuery = true)
	Integer findUserIdbyEmail(@Param("email") String email);

	@Query(value = "select * from user_data where email=:email", nativeQuery = true)
	UserData findUserbyEmail(@Param("email") String email);

	@Query(value = "select * from user_data where email=:email", nativeQuery = true)
	Optional<UserData> findByEmailOptional(@Param("email") String email);

	@Query(value = "select count(follower_id) from user_follower where user_id=:userId", nativeQuery = true)
	Integer getFollowerCount(@Param("userId") Integer userId );

	@Query(value = "select count(user_id) from user_follower where follower_id=:userId", nativeQuery = true)
	Integer getFollowingCount(@Param("userId") Integer userId);

}
