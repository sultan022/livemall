package com.chatapp.dto;

import lombok.AllArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
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
