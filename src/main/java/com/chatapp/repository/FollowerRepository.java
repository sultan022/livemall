package com.chatapp.repository;

import com.chatapp.domain.Follower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Integer> {

    @Query(value = "select id,name,profile_pic_path from user_data where id IN :followerIds", nativeQuery = true)
    List<Follower> findFollowerDetailsByIds(@Param("followerIds") List<Integer> followerIds);


}
