package com.chatapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatapp.domain.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

}
