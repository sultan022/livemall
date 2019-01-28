/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author faheem.sultan
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	public Product() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "product_pic")
	private String productMainImage;

	@Basic(optional = false)
	@NotNull

	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@NotNull

	@Column(name = "price")
	private String price;
	@Basic(optional = false)
	@NotNull

	@Column(name = "quantity")
	private String quantity;
	@Basic(optional = false)
	@NotNull

	@Column(name = "description")
	private String description;
	public Product(String productMainImage, @NotNull String name, @NotNull String price, @NotNull String quantity,
			@NotNull String description, Collection<Image> imageCollection, Integer userId) {
		super();
		this.productMainImage = productMainImage;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.imageCollection = imageCollection;
		this.userId = userId;
	}

	@JsonProperty("image")
	@OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
	private Collection<Image> imageCollection;
	// @JsonIgnore
	// @JoinColumn(name = "user_id", referencedColumnName = "id")
	// @ManyToOne(optional = false)
	// private UserData userId;

	@Column(name = "user_id")
	private Integer userId;

	public String getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Image> getImageCollection() {
		return imageCollection;
	}

	public void setImageCollection(Collection<Image> imageCollection) {
		this.imageCollection = imageCollection;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
