package com.chatapp.dto;

public class CategoryDTO {

	private String name;
	private String categoryIcon;
	private String categoryDefaultName;
	private String lang;

	public CategoryDTO(String name, String categoryIcon, String categoryDefaultName, String lang) {
		super();
		this.name = name;
		this.categoryIcon = categoryIcon;
		this.categoryDefaultName = categoryDefaultName;
		this.lang = lang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryDefaultName() {
		return categoryDefaultName;
	}

	public void setCategoryDefaultName(String categoryDefaultName) {
		this.categoryDefaultName = categoryDefaultName;
	}

	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
