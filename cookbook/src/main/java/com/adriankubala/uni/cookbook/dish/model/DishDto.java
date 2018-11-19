package com.adriankubala.uni.cookbook.dish.model;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;

import java.util.List;
import java.util.Objects;

public class DishDto {

	private Long id;
	private String name;
	private String picture;
	private String recipe;
	private List<IngredientDto> ingredients;

	public DishDto(Long id, String name, String picture, String recipe, List<IngredientDto> ingredients) {
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.recipe = recipe;
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DishDto dishDto = (DishDto) o;
		return Objects.equals(id, dishDto.id) &&
			Objects.equals(name, dishDto.name) &&
			Objects.equals(picture, dishDto.picture) &&
			Objects.equals(recipe, dishDto.recipe) &&
			Objects.equals(ingredients, dishDto.ingredients);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, picture, recipe, ingredients);
	}
}
