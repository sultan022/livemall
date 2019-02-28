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
	public <T> DeferredResult<ResponseEntity<?>> getCategories(@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel) {

		CustomResponse<CategoryDTO> customResponse = new CustomResponse<>();

		return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

			try {
				//customResponse.setData(categoryService.getCategories(lang, channel).toString());
				customResponse.setData(categoryService.getCategories(lang, channel));
				customResponse.setMessage("");
				customResponse.setCallStatus("true");
				customResponse.setResultCode("00");
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
	public <T> DeferredResult<ResponseEntity<?>> addCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel) {

		CustomResponse<AddCategoryDTO> customResponse = new CustomResponse<>();

		return DeferredResults
				.from(CompletableFuture.completedFuture(categoryService.createCategoryFromCategoryDTO(addCategoryDTO))
						.thenApply(category -> categoryService.saveCategory(category)).thenApply(createdCategory -> {

							customResponse.setData(addCategoryDTO);
							customResponse.setMessage("");
							customResponse.setCallStatus("true");
							customResponse.setResultCode("00");

							return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
						}));

	}


	@ApiOperation(value = "Add a new Category")
	@DeleteMapping("/{categoryDefaultName}")
	@ResponseStatus(HttpStatus.OK)
	public <T> DeferredResult<ResponseEntity<?>> deleteCategory(@Valid @RequestParam("lang") String lang,
															 @Valid @RequestHeader("categoryDefaultName") String categoryDefaultName,
															 @Valid @RequestHeader(value="channel") String channel) {

		CustomResponse<?> customResponse = new CustomResponse<>();

		return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

			try {

				categoryService.deleteCategory(categoryDefaultName);
				customResponse.setMessage("");
				customResponse.setCallStatus("true");
				customResponse.setResultCode("00");
			} catch (CustomException e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			}

			return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
		}));


	}


}
