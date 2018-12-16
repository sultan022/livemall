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
	public CustomResponse addProduct(@Valid @RequestBody ProductDTO productDTO,
			@RequestHeader(value = "user_email") String userEmail) throws CustomException {

		CustomResponse customResponse = new CustomResponse();

		Product product = productService.createProductFromProductDTO(productDTO);
		productService.saveProduct(product, userEmail, customResponse);
		return customResponse;

	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getProduct(@RequestHeader(value = "user_email") String userEmail) throws CustomException {

		return productService.getProducts(userEmail);

	}

	@DeleteMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public CustomResponse deleteProduct(@RequestHeader(value = "user_email") String userEmail,
			@RequestHeader(value = "product_name") String name) throws CustomException {

		CustomResponse customResponse = new CustomResponse();

		productService.delProduct(name, customResponse, userEmail);

		return customResponse;
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO updateProduct(@RequestHeader(value = "user_email") String userEmail,
			@RequestHeader(value = "product_name") String productName, @Valid @RequestBody ProductDTO productDTO)
			throws CustomException {

		CustomResponse customResponse = new CustomResponse();

		productService.delProduct(productName, customResponse, userEmail);
		
		Product product = productService.createProductFromProductDTO(productDTO);
		productService.saveProduct(product, userEmail, customResponse);
		//productService.createProductDTOFromProduct(product, productDTO);
		return productDTO;
	}

	

}
