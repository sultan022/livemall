package com.chatapp.controller;

import com.chatapp.dto.AddCategoryDTO;
import com.chatapp.dto.CategoryDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.CategoryService;
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
@RequestMapping("/rest/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "Get the List of Categories available")
    @GetMapping("/getcategories")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> getCategories(@RequestParam("lang") String lang) {

        CustomResponse<CategoryDTO> customResponse = new CustomResponse<>();


        return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

            try {
                customResponse.setArrayData(categoryService.getCategories(lang));
                customResponse.setMessage("Success");
                customResponse.setResponseCode(HttpStatus.OK);
            } catch (CustomException e) {
                e.printStackTrace();
                throw new CustomException(e.getMessage());
            }

            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }));

    }

    @ApiOperation(value = "Add a new Category")
    @PostMapping("/addcategory")
    @ResponseStatus(HttpStatus.OK)
    public <T> DeferredResult<ResponseEntity<?>> addCategory(@RequestParam("lang") String lang,
                                                             @Valid @RequestBody AddCategoryDTO addCategoryDTO) {

        CustomResponse<AddCategoryDTO> customResponse = new CustomResponse<>();

        return DeferredResults.from(CompletableFuture.completedFuture(categoryService.createCategoryFromCategoryDTO(addCategoryDTO))
                .thenApply(category -> categoryService.saveCategory(category))
                .thenApply(createdCategory -> {

                    customResponse.setData(addCategoryDTO);
                    customResponse.setMessage("Success");
                    customResponse.setResponseCode(HttpStatus.OK);

                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                }));

    }

}
