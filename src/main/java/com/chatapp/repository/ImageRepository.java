package com.chatapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}
