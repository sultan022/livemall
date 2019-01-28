package com.chatapp.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ProductDTO {

	@NotNull
	private String name;
	private String description;
	@NotNull
	private String price;
	@NotNull
	private String quantity;

	@NotNull
	private String productMainImage;

	public ProductDTO() {
		super();
	}

	public ProductDTO(@NotNull String name, String description, @NotNull String price, @NotNull String quantity,
			@NotNull String productMainImage, List<ImageDTO> imageDTOList) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.productMainImage = productMainImage;
		this.imageDTOList = imageDTOList;
	}

	private List<ImageDTO> imageDTOList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public List<ImageDTO> getImageDTOList() {
		return imageDTOList;
	}

	public void setImageDTOList(List<ImageDTO> imageDTOList) {
		this.imageDTOList = imageDTOList;
	}

	public String getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}


}
