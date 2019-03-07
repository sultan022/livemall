package com.chatapp.controller;

import com.chatapp.domain.UserData;
import com.chatapp.dto.CreateSearchFilterDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.SearchFilterService;
import com.chatapp.util.CustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/searchfilter")
public class SearchFilterController {

    private final SearchFilterService searchFilterService;

    @Autowired
    public SearchFilterController(SearchFilterService searchFilterService){
        this.searchFilterService= searchFilterService;
    }


    @ApiOperation(value = "Create a new Search Filter")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> signup(@Valid @RequestBody CreateSearchFilterDTO filter,
                                    @Valid @RequestParam("lang") String lang,
                                    @Valid @RequestHeader(value = "channel") String channel)
            throws CustomException {

        CustomResponse<CreateSearchFilterDTO> customResponse = new CustomResponse<>();

        searchFilterService.createSearchFilter(filter.getFilterName());
        customResponse.setData(filter);
        customResponse.setMessage("");
        customResponse.setCallStatus("true");
        customResponse.setResultCode("00");

        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

    }
}
