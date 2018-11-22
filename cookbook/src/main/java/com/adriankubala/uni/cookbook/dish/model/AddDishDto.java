package com.adriankubala.uni.cookbook.dish.model;

import java.util.List;
import java.util.Objects;

public class AddDishDto {

	private String name;
	private String picture;
	private String recipe;
	private List<Long> ingredientIds;

	public AddDishDto() {
	}

	public AddDishDto(String name, String picture, String recipe, List<Long> ingredientIds) {
		this.name = name;
		this.picture = picture;
		this.recipe = recipe;
		this.ingredientIds = ingredientIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public List<Long> getIngredientIds() {
		return ingredientIds;
	}

	public void setIngredientIds(List<Long> ingredientIds) {
		this.ingredientIds = ingredientIds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AddDishDto that = (AddDishDto) o;
		return Objects.equals(name, that.name) &&
			Objects.equals(picture, that.picture) &&
			Objects.equals(recipe, that.recipe) &&
			Objects.equals(ingredientIds, that.ingredientIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, picture, recipe, ingredientIds);
	}
}
