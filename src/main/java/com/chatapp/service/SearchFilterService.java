package com.chatapp.service;

import com.chatapp.domain.SearchFilter;
import com.chatapp.repository.SearchFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "searchFilterCache")
public class SearchFilterService {

   private final  SearchFilterRepository searchFilterRepository;

    @Autowired
    public SearchFilterService(SearchFilterRepository searchFilterRepository ){

        this.searchFilterRepository=searchFilterRepository;
    }

    @Caching(evict = {@CacheEvict(value = "searchFilterCache", allEntries = true)},
            put = {@CachePut(value = "searchFilterCache", key = "#root.methodName")})
    public void createSearchFilter(String filterName) {

        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setFilterName(filterName);
        searchFilterRepository.save(searchFilter);
    }
}
