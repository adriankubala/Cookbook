package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.repository.entity.Dish;

import java.util.List;
import java.util.stream.Collectors;

public final class DishMapper {

	private DishMapper() {
	}

	public static List<DishDto> toDishDtos(List<Dish> entities) {
		return entities.stream()
			.map(entity -> new DishDto(entity.getId(), entity.getName(), entity.getRecipe()))
			.collect(Collectors.toList());
	}
}
