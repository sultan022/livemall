package com.chatapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.domain.Product;
import com.chatapp.domain.UserData;
import com.chatapp.dto.ProductDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.ProductService;
import com.chatapp.util.CustomException;

@RestController
@RequestMapping("/rest/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomResponse<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
			@RequestHeader(value = "userEmail") String userEmail) {

		CustomResponse<ProductDTO> customResponse = new CustomResponse();

		Product product = productService.createProductFromProductDTO(productDTO);
		try {

			productService.saveProduct(product, userEmail);
			customResponse.setData(productDTO);
			customResponse.setMessage("Product Added Successfully");
			customResponse.setResponseCode(HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();

			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			customResponse.setMessage("Exception");
			customResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customResponse;

	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse<List<ProductDTO>> getProduct(@RequestHeader(value = "userEmail") String userEmail) {

		CustomResponse<List<ProductDTO>> customResponse = new CustomResponse<>();
		try {
			customResponse.setData(productService.getProducts(userEmail));
			customResponse.setMessage("");
			customResponse.setResponseCode(HttpStatus.OK);

		} catch (CustomException e) {
			e.printStackTrace();

			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			customResponse.setMessage("Exception");
			customResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return customResponse;

	}

	@DeleteMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public <T> CustomResponse<T> deleteProduct(@RequestHeader(value = "userEmail") String userEmail,
			@RequestHeader(value = "product_name") String name) {

		CustomResponse<T> customResponse = new CustomResponse();

		try {
			productService.delProduct(name, userEmail);
			customResponse.setMessage("product deleted");
			customResponse.setResponseCode(HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();

			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			customResponse.setMessage("Exception");
			customResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return customResponse;
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse<ProductDTO> updateProduct(@RequestHeader(value = "userEmail") String userEmail,
			@RequestHeader(value = "product_name") String productName, @Valid @RequestBody ProductDTO productDTO) {

		CustomResponse<ProductDTO> customResponse = new CustomResponse();

		try {

			productService.delProduct(productName, userEmail);
			Product product = productService.createProductFromProductDTO(productDTO);
			productService.saveProduct(product, userEmail);

			customResponse.setData(productDTO);
			customResponse.setMessage("Product Updated Succesfully");
			customResponse.setResponseCode(HttpStatus.OK);

		} catch (CustomException e) {
			e.printStackTrace();

			customResponse.setMessage(e.getMessage());
			customResponse.setResponseCode(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			customResponse.setMessage("Exception");
			customResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return customResponse;
	}

}
