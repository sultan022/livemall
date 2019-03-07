package com.chatapp.repository;

import com.chatapp.domain.SearchFilter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SearchFilterRepository extends CrudRepository<SearchFilter,Integer> {


    @Query(value = "select filter_name from search_filter", nativeQuery = true)
      Set<String> findAllFilters();

}
