package com.chatapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.domain.Image;
import com.chatapp.domain.Product;
import com.chatapp.domain.UserData;
import com.chatapp.dto.ImageDTO;
import com.chatapp.dto.ProductDTO;
import com.chatapp.repository.ImageRepository;
import com.chatapp.repository.ProductRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.response.CustomResponse;
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;

@Service
public class ProductService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ImageRepository imageRepository;

	public Product saveProduct(@Valid Product product, String userEmail, CustomResponse customResponse)
			throws CustomException {

		try {

			UserData userData = userRepository.findUserbyEmail(userEmail);
			if (userData == null)
				throw new CustomException("User does not Exists");

			product.setUserId(userData);
			product = productRepository.save(product);

			if (product.getImageCollection() != null) {

				List<Image> tempList = (List<Image>) product.getImageCollection();

				for (int i = 0; i < tempList.size(); i++) {

					String picPath = UtilBase64Image.saveBase64StringAsImageForProduct(tempList.get(i).getImageString(),
							product.getName(), i, userEmail);
					tempList.get(i).setImagePath(picPath);
					// tempList.get(i).setProductId(productId);
					tempList.get(i).setImageString(" ");
					tempList.get(i).setProductId(product);
				}

				product.setImageCollection(tempList);
			}

			imageRepository.saveAll(product.getImageCollection());

			customResponse.setCustomMessage("Product Added Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return product;

	}

	public List<ProductDTO> getProducts(String userEmail) throws CustomException {

		List<Product> productsFromDB = new ArrayList<>();
		List<ProductDTO> productDTOs = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		List<ImageDTO> imageDTOs = new ArrayList<>();
		ImageDTO imageDTO = new ImageDTO();

		try {

			Integer userId = userRepository.findUserIdbyEmail(userEmail);

			if (userId != null) {

				productsFromDB = productRepository.findProductByUserId(userId);

				for (int i = 0; i < productsFromDB.size(); i++) {

					if (productsFromDB.get(i).getImageCollection() != null) {

						List<Image> currentImageList = (List<Image>) productsFromDB.get(i).getImageCollection();

						for (int j = 0; j < currentImageList.size(); j++) {
							// currentImageList.get(j).setImageString(
							// UtilBase64Image.getImageFromDirectory(currentImageList.get(j).getImagePath()));

							// image dto

							imageDTO.setImageString(
									UtilBase64Image.getImageFromDirectory(currentImageList.get(j).getImagePath()));
							imageDTOs.add(imageDTO);
							imageDTO = new ImageDTO();

						}
						productDTO.setDescription(productsFromDB.get(i).getDescription());
						productDTO.setName(productsFromDB.get(i).getName());
						productDTO.setPrice(productsFromDB.get(i).getPrice());
						productDTO.setQuantity(productsFromDB.get(i).getQuantity());
						productDTO.setImageDTOList(imageDTOs);

						productDTOs.add(productDTO);
						productDTO = new ProductDTO();
						// productsFromDB.get(i).setImageCollection(currentImageList);

					}

				}
			}

			else
				throw new CustomException("User Does not Exist");

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return productDTOs;
	}

	public Product createProductFromProductDTO(@Valid ProductDTO productDTO) {

		Product product = new Product();
		Image image = new Image();
		List<Image> images = new ArrayList<>();
		product.setDescription(productDTO.getDescription());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		// product.setImageCollection((Collection)
		// productDTO.getImageDTOList());

		if (productDTO.getImageDTOList() != null)
			for (int i = 0; i < productDTO.getImageDTOList().size(); i++) {

				image.setImageString(productDTO.getImageDTOList().get(i).getImageString());
				images.add(image);
				image = new Image();
			}

		product.setImageCollection(images);

		return product;
	}

	public void delProduct(String productName, CustomResponse customResponse, String userEmail) throws CustomException {

		try {

			Product product = productRepository.findProductByName(productName);
			Integer userID = userRepository.findUserIdbyEmail(userEmail);
			if (product == null)
				throw new CustomException("Product Does not Exists");
			if (userID == null)
				throw new CustomException("User Does not Exists");

			removeProductFromDir(productName, userEmail, product);

			productRepository.delete(product);

			customResponse.setCustomMessage("deleted");

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	private void removeProductFromDir(String name, String userEmail, Product product) throws CustomException {
		try {

			product.getImageCollection().forEach(image -> {

				UtilBase64Image.removeFile(image.getImagePath());

			});

			UtilBase64Image.removeFile(userEmail + "/" + product.getName());

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

	}

	public void createProductDTOFromProduct(Product product, @Valid ProductDTO productDTO) {
		List<ImageDTO> imageDTOs = new ArrayList<>();
		ImageDTO imageDTO = new ImageDTO();

		productDTO.setDescription(product.getDescription());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(product.getQuantity());

		List<Image> images = (List<Image>) product.getImageCollection();

		for (int i = 0; i < images.size(); i++) {

			imageDTO.setImageString(images.get(i).getImageString());
			imageDTOs.add(imageDTO);
			imageDTO = new ImageDTO();

		}

		productDTO.setImageDTOList(imageDTOs);

	}

}
