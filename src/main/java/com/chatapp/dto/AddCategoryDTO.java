package com.chatapp.dto;

import javax.validation.constraints.NotNull;

public class AddCategoryDTO {

	@NotNull
	private String categoryName;
	@NotNull
	private String categoryIcon;
	@NotNull
	private String categoryDefaultName;
	@NotNull
	private String lang;

	public AddCategoryDTO(@NotNull String categoryName, @NotNull String categoryIcon,
			@NotNull String categoryDefaultName, @NotNull String lang) {
		this.categoryName = categoryName;
		this.categoryIcon = categoryIcon;
		this.categoryDefaultName = categoryDefaultName;
		this.lang = lang;
	}
	
	public AddCategoryDTO(){
		
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
