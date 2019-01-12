package com.chatapp.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.chatapp.domain.Category;
import com.chatapp.dto.CategoryDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.CategoryService;
import com.chatapp.util.CustomException;
import com.chatapp.util.DeferredResults;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/category")
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;

	@ApiOperation(value = "Get List of Categories available")
	@GetMapping("/getcategories")
	@ResponseStatus(HttpStatus.OK)
	public <T> DeferredResult<ResponseEntity<?>> getCategories(@RequestParam("lang") String lang) {
		
		CustomResponse<CategoryDTO> customResponse = new CustomResponse<>();
		
		return DeferredResults.from(CompletableFuture.supplyAsync(()->{
			
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
	
}
