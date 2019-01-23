package com.chatapp.repository;

import com.chatapp.domain.UserReviewRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRatingRepository extends CrudRepository<UserReviewRating, Integer> {
}
