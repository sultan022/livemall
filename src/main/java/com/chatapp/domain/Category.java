package com.chatapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "category_default_name")
	private String categoryDefaultName;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "category_icon")
	private String categoryIcon;

	@Column(name = "lang")
	private String lang;

	private Category() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category(String categoryDefaultName, String categoryName, String categoryIcon, String lang) {
		this.categoryDefaultName = categoryDefaultName;
		this.categoryName = categoryName;
		this.categoryIcon = categoryIcon;
		this.lang = lang;
	}

	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public String getCategoryDefaultName() {
		return categoryDefaultName;
	}

	public void setCategoryDefaultName(String categoryDefaultName) {
		this.categoryDefaultName = categoryDefaultName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
