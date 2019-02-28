package com.chatapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.chatapp.dto.UserProductsForMenu;
import com.chatapp.response.UserProductsResponse;
import org.modelmapper.ModelMapper;
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
import com.chatapp.util.CustomException;
import com.chatapp.util.UtilBase64Image;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Product saveProduct(@Valid Product product, String userEmail) throws CustomException {

        UserData userData = userRepository.findUserbyEmail(userEmail);
        if (userData == null)
            throw new CustomException("User does not Exists");

        UtilBase64Image.createDirectory(userEmail);

        product.setUserId(userData.getId());
        product.setProductMainImage(UtilBase64Image.saveBase64StringAsMainImageForProduct(product.getProductMainImage(), product.getName(), "mainpic", userEmail));
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

        return product;

    }

    public List<ProductDTO> getProducts(String userEmail, String productName) throws CustomException {

        List<Product> productsFromDB = new ArrayList<>();
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();

        ImageDTO imageDTO = new ImageDTO();

        Integer userId = userRepository.findUserIdbyEmail(userEmail);

        if (userId != null) {

            productsFromDB = productRepository.findProductListByName(productName);

            if (productsFromDB.size() < 1)
                throw new CustomException("Product Not Found");

            for (int i = 0; i < productsFromDB.size(); i++) {

                if (productsFromDB.get(i).getImageCollection() != null) {

                    List<Image> currentImageList = (List<Image>) productsFromDB.get(i).getImageCollection();
                    List<ImageDTO> imageDTOs = new ArrayList<>();

                    for (int j = 0; j < currentImageList.size(); j++) {
                        // currentImageList.get(j).setImageString(
                        // UtilBase64Image.getImageFromDirectory(currentImageList.get(j).getImagePath()));

                        // image dto

                        imageDTO.setImageString(
                                UtilBase64Image.getImageFromDirectory(currentImageList.get(j).getImagePath()));
                        if (imageDTO.getImageString() != null)
                            imageDTOs.add(imageDTO);

                        imageDTO = new ImageDTO();

                    }
                    //	productDTO.setId(productsFromDB.get(i).getId());
                    productDTO.setDescription(productsFromDB.get(i).getDescription());
                    productDTO.setName(productsFromDB.get(i).getName());
                    productDTO.setPrice(productsFromDB.get(i).getPrice());
                    productDTO.setQuantity(productsFromDB.get(i).getQuantity());
                    productDTO.setProductMainImage(UtilBase64Image.getImageFromDirectory(productsFromDB.get(i).getProductMainImage()));
                    productDTO.setImageDTOList(imageDTOs);


                    productDTOs.add(productDTO);
                    productDTO = new ProductDTO();
                    // productsFromDB.get(i).setImageCollection(currentImageList);

                }

            }
        } else
            throw new CustomException("User Does not Exist");

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
        product.setProductMainImage(productDTO.getProductMainImage());
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


    public Product createProductFromProductDTOForNewProduct(@Valid ProductDTO productDTO) {

        Product product = new Product();
        Image image = new Image();
        List<Image> images = new ArrayList<>();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setProductMainImage(productDTO.getProductMainImage());
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

    @Transactional
    public void delProduct(String productName, String userEmail) throws CustomException {

        Product product = productRepository.findProductByName(productName);
        Integer userID = userRepository.findUserIdbyEmail(userEmail);
        if (product == null)
            throw new CustomException("Product Does not Exists");
        if (userID == null)
            throw new CustomException("User Does not Exists");

        removeProductFromDir(productName, userEmail, product);

        productRepository.delete(product);

    }

    private void removeProductFromDir(String name, String userEmail, Product product) throws CustomException {

        product.getImageCollection().forEach(image -> {

            UtilBase64Image.removeFile(image.getImagePath());

        });

        UtilBase64Image.removeFile(userEmail + "/" + product.getName());

    }

    public void createProductDTOFromProduct(Product product, @Valid ProductDTO productDTO) {
        List<ImageDTO> imageDTOs = new ArrayList<>();
        ImageDTO imageDTO = new ImageDTO();

        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setProductMainImage(product.getProductMainImage());

        List<Image> images = (List<Image>) product.getImageCollection();

        for (int i = 0; i < images.size(); i++) {

            imageDTO.setImageString(images.get(i).getImageString());
            imageDTOs.add(imageDTO);
            imageDTO = new ImageDTO();

        }

        productDTO.setImageDTOList(imageDTOs);

    }

    public String testMethod(String string) {

        System.out.println("in test method " + Thread.currentThread().getName());


        //return Optional.empty();/
        return "";
    }

    public UserProductsForMenu getUserProductsForMenu(String userEmail, Integer page) {

        page = page * 5 - 5;
        if (page < 0)
            throw new CustomException("Incorrect Page Number");

        Integer userId = userRepository.findUserIdbyEmail(userEmail);
        if (userId == null)
            throw new CustomException("User does not Exists");


        List<Product> products = productRepository.findProductByUserIdPagination(userId, page, 5)
                .stream()
                .collect(Collectors.toList());
        List<UserProductsResponse> userProductsResponses = new ArrayList<>();

        if (products != null)
            products.forEach(product -> {
                product.setProductMainImage(UtilBase64Image.getImageFromDirectory(product.getProductMainImage()));

                userProductsResponses.add(modelMapper.map(product, UserProductsResponse.class));

            });

        return new UserProductsForMenu(userProductsResponses);

    }
}
