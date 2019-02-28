package com.chatapp.service;

import com.chatapp.dto.CategoryDTOForSearch;
import com.chatapp.dto.CityDTO;
import com.chatapp.dto.CountryDTO;
import com.chatapp.repository.CategoryRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class SearchService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
}
