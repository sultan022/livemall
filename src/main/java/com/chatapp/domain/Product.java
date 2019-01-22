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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author faheem.sultan
 */
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

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
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "price")
	private String price;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "quantity")
	private String quantity;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "description")
	private String description;
	@JsonProperty("image")
	@OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
	private Collection<Image> imageCollection;
//	@JsonIgnore
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	@ManyToOne(optional = false)
//	private UserData userId;


	@Column(name="user_id")
	private Integer userId;
	public Product() {
	}




}
