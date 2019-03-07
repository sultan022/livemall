package com.chatapp.controller;

import com.chatapp.dto.CategoryDTOForSearch;
import com.chatapp.dto.CityDTO;
import com.chatapp.dto.CountryDTO;
import com.chatapp.dto.SearchFilerDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.SearchService;
import com.chatapp.util.CustomException;
import com.chatapp.util.DeferredResults;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/search")
public class SearchController {


    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "Get all cities")
    @GetMapping("/city")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getAllCities(@Valid @RequestHeader(value = "channel") String channel,
                                                             @Valid @RequestParam("lang") String lang) throws CustomException, Exception {

        CustomResponse<CityDTO> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setData(searchService.getAllCities(lang));
                customResponse.setMessage("");
                customResponse.setCallStatus("true");
                customResponse.setResultCode("00");
            } catch (CustomException e) {
                e.printStackTrace();
                throw new CustomException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("An error has occurred");
            }

            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }));

    }

    @ApiOperation(value = "Get all countries ")
    @GetMapping("/country")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getAllCountries(@Valid @RequestHeader(value = "channel") String channel,
                                                              @Valid @RequestParam("lang") String lang) throws CustomException, Exception {

        CustomResponse<CountryDTO> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setData(searchService.getAllCountries(lang));
                customResponse.setMessage("");
                customResponse.setCallStatus("true");
                customResponse.setResultCode("00");
            } catch (CustomException e) {
                e.printStackTrace();
                throw new CustomException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("An error has occurred");
            }

            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }));

    }

    @ApiOperation(value = "Get all category names for search filter ")
    @GetMapping("/categorynames")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getAllCategories(@Valid @RequestHeader(value = "channel") String channel,
                                                                 @Valid @RequestParam("lang") String lang) throws CustomException, Exception {

        CustomResponse<CategoryDTOForSearch> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setData(searchService.getAllCategoryNames(lang));
                customResponse.setMessage("");
                customResponse.setCallStatus("true");
                customResponse.setResultCode("00");
            } catch (CustomException e) {
                e.printStackTrace();
                throw new CustomException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("An error has occurred");
            }

            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }));

    }


    @ApiOperation(value = "Get Filters to Search users")
    @GetMapping("/filters")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getFilters(@Valid @RequestHeader(value = "channel") String channel,
                                                                  @Valid @RequestParam("lang") String lang) throws CustomException, Exception {

        CustomResponse<SearchFilerDTO> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setData(searchService.getSearchFilters(lang));
                customResponse.setMessage("");
                customResponse.setCallStatus("true");
                customResponse.setResultCode("00");
            } catch (CustomException e) {
                e.printStackTrace();
                throw new CustomException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("An error has occurred");
            }

            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }));

    }



}
