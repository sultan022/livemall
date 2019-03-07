package com.chatapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.UserFollower;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowerRepository extends CrudRepository<UserFollower, Integer>{

    @Query(value = "select follower_id from user_follower where user_id=:id limit :start,:end", nativeQuery = true)
    Optional<List<Integer>> findFollowersByUserId(@Param("id") Integer id,@Param("start") Integer start, @Param("end") Integer end);

    @Query(value = "select user_id from user_follower where follower_id=:id limit :start,:end", nativeQuery = true)
    Optional<List<Integer>> findFollowingsByUserId(@Param("id") Integer id,@Param("start") Integer start, @Param("end") Integer end);
}
