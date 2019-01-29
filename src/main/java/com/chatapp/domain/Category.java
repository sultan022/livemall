package com.chatapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "category_default_name")
	private String categoryDefaultName;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "category_ios_icon")
	private String categoryIosIcon;

	@Column(name = "category_android_icon")
	private String categoryAndroidIcon;

	@Column(name = "lang")
	private String lang;



}
