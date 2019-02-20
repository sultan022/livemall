package com.chatapp.service;

import com.chatapp.dto.GenericsDTO;
import com.chatapp.repository.GenericsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericsService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GenericsRepository genericsRepository;

    public GenericsDTO getUserRules(String lang, String identifier) {

        return modelMapper.map(genericsRepository.getUserRules(lang,identifier), GenericsDTO.class );
    }
}
