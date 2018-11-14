package com.adriankubala.uni.cookbook.dish.model;

import java.util.Objects;

public class DishDto {

	private Long id;

	private String name;

	private String recipe;

	public DishDto(Long id, String name, String recipe) {
		this.id = id;
		this.name = name;
		this.recipe = recipe;
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

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DishDto dishDto = (DishDto) o;
		return Objects.equals(id, dishDto.id) &&
			Objects.equals(name, dishDto.name) &&
			Objects.equals(recipe, dishDto.recipe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, recipe);
	}
}
