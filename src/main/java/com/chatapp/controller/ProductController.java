package com.chatapp.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import com.chatapp.dto.UserDtoWithProducts;
import com.chatapp.dto.UserProductsForMenu;
import com.chatapp.util.DeferredResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.domain.Product;
import com.chatapp.dto.ProductDTO;
import com.chatapp.response.CustomResponse;
import com.chatapp.service.ProductService;
import com.chatapp.util.CustomException;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/rest/product")

public class ProductController {

	@Autowired
	ProductService productService;


	@ApiOperation(value = "Add a new product against a user")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO,
			@RequestHeader(value = "userEmail") String userEmail,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel)
			throws CustomException {

		CustomResponse<ProductDTO> customResponse = new CustomResponse();

		Product product = productService.createProductFromProductDTOForNewProduct(productDTO);

		productService.saveProduct(product, userEmail);
		customResponse.setData(productDTO);
		customResponse.setMessage("");
		customResponse.setCallStatus("true");
		customResponse.setResultCode("00");

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Get a Product and its images using product id and user email")
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getProduct(@Valid @RequestHeader(value = "targetUserEmail") String userEmail,
			@Valid @RequestHeader(value = "productName") String productName,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel)
			throws CustomException {

		CustomResponse<List<ProductDTO>> customResponse = new CustomResponse<>();

		customResponse.setData(productService.getProducts(userEmail, productName));
		customResponse.setMessage("");
		customResponse.setCallStatus("true");
		customResponse.setResultCode("00");

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "delete a product and its images from the system")
	@DeleteMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteProduct(@RequestHeader(value = "targetUserEmail") String userEmail,
			@RequestHeader(value = "productName") String productName,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel)
			throws CustomException {

		CustomResponse<?> customResponse = new CustomResponse();

		productService.delProduct(productName, userEmail);
		customResponse.setMessage("");
		customResponse.setCallStatus("true");
		customResponse.setResultCode("00");

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Update a product and its images")
	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateProduct(@RequestHeader(value = "targetUserEmail") String userEmail,
			@RequestHeader(value = "productName") String productName, 
			@Valid @RequestBody ProductDTO productDTO,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value="channel") String channel) throws CustomException {

		CustomResponse<ProductDTO> customResponse = new CustomResponse();

		productService.delProduct(productName, userEmail);
		Product product = productService.createProductFromProductDTO(productDTO);
		productService.saveProduct(product, userEmail);

		customResponse.setData(productDTO);
		customResponse.setMessage("");
		customResponse.setCallStatus("true");
		customResponse.setResultCode("00");

		return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
	}


	@ApiOperation(value = "Get list of products for user's menu section by email")
	@GetMapping("/getproductsformenu")
	@ResponseStatus(HttpStatus.OK)
	public <T> DeferredResult<ResponseEntity<?>> getUserProductsForMenu(
			@Valid @RequestHeader(value = "userEmail") String userEmail,
			@Valid @RequestParam("page") Integer page,
			@Valid @RequestParam("lang") String lang,
			@Valid @RequestHeader(value = "channel") String channel) throws CustomException, Exception {

		CustomResponse<UserProductsForMenu> customResponse = new CustomResponse<>();

		return DeferredResults.from(CompletableFuture.supplyAsync(() -> {

			try {
				customResponse.setData(productService.getUserProductsForMenu(userEmail, page));
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
