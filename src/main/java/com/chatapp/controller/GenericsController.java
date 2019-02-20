package com.chatapp.controller;


import com.chatapp.dto.CategoryDTOForSearch;
import com.chatapp.dto.GenericsDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.GenericsService;
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
@RequestMapping("/generics")
public class GenericsController {


    @Autowired
    private GenericsService genericsService;

    @ApiOperation(value = "Get user rules")
    @GetMapping("/userrules")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getUserRules(@Valid @RequestHeader(value = "channel") String channel,
                                                                  @Valid @RequestParam("lang") String lang) throws CustomException, Exception {

        CustomResponse<GenericsDTO> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setData(genericsService.getUserRules(lang,"userrules"));
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
