package com.chatapp.service;

import com.chatapp.dto.CategoryDTOForSearch;
import com.chatapp.dto.CityDTO;
import com.chatapp.dto.CountryDTO;
import com.chatapp.dto.SearchFilerDTO;
import com.chatapp.repository.CategoryRepository;
import com.chatapp.repository.SearchFilterRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "searchFilterCache")
public class SearchService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SearchFilterRepository searchFilterRepository;

    public CityDTO getAllCities(String lang) {


        return new CityDTO(userRepository.getCities(lang));


    }

    public CountryDTO getAllCountries(String lang) {


        return new CountryDTO(userRepository.getCountries(lang));
    }

    public CategoryDTOForSearch getAllCategoryNames(String lang) {
        return new CategoryDTOForSearch(categoryRepository.getCategoryNames(lang)
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList()));
    }

    @Cacheable(value = "searchFilterCache", key = "#root.methodName", unless = "#name != null and #name.toUpperCase().startsWith('TEST')")
    public SearchFilerDTO getSearchFilters(String lang) {

        List<String> filters = new ArrayList<>();

        searchFilterRepository.findAllFilters().forEach(element -> {

            filters.add(element);
        });

        return new SearchFilerDTO(filters);
    }

}
