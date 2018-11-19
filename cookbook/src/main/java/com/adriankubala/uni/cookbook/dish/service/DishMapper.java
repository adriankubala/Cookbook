package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.repository.entity.Dish;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;

import java.util.List;
import java.util.stream.Collectors;

final class DishMapper {

	private DishMapper() {
	}

	static List<DishDto> toDishDtos(List<Dish> entities) {
		return entities.stream()
			.map(DishMapper::toDishDto)
			.collect(Collectors.toList());
	}

	static DishDto toDishDto(Dish entity) {
		return new DishDto(entity.getId(), entity.getName(), entity.getPicture(), entity.getRecipe(), mapIngredientsToDtos(entity));
	}

	private static List<IngredientDto> mapIngredientsToDtos(Dish entity) {
		return entity.getIngredients().stream()
			.map(ingredient -> new IngredientDto(ingredient.getId(), ingredient.getName()))
			.collect(Collectors.toList());
	}
}
