package com.chatapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.UserFollower;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowerRepository extends CrudRepository<UserFollower, Integer>{

}
