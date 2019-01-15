package com.chatapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.UserFollower;

public interface UserFollowerRepository extends CrudRepository<UserFollower, Integer>{

}
