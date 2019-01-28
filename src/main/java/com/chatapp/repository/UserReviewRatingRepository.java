package com.chatapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chatapp.domain.UserReviewRating;

@Repository
public interface UserReviewRatingRepository extends CrudRepository<UserReviewRating, Integer> {

	@Query(value="select * from user_review_rating where user_id=:userId limit :start,:end", nativeQuery=true)
	Optional<List<UserReviewRating>> findReviewsByUserId(@Param("userId") Integer userId,@Param("start") Integer start, @Param("end") Integer end);
}
