package com.chatapp.dto;

import java.util.List;

public class ProductDTO {

	
	private String name;
	private String description;
	private String price;
	private String quantity;
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
	
	
}