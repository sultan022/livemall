package com.chatapp.repository;

import com.chatapp.domain.Generics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericsRepository extends CrudRepository<Generics, Integer> {



    @Query(value = "select * from live_mall_generics where identifier=:identifier and lang=:lang", nativeQuery = true)
    Generics getUserRules(@Param("lang")String lang, @Param("identifier") String identifier);
}
