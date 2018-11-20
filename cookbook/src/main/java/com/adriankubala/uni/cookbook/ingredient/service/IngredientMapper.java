package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

final class IngredientMapper {

	private IngredientMapper() {
	}

	static List<IngredientDto> toIngredientDtos(List<Ingredient> entities) {
		return entities.stream()
			.map(IngredientMapper::toIngredientDto)
			.collect(Collectors.toList());
	}

	static IngredientDto toIngredientDto(Ingredient entity) {
		return new IngredientDto(entity.getId(), entity.getName());
	}
}
