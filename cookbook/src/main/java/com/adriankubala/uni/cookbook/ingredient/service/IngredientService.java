package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.model.Name;

import java.util.List;

public interface IngredientService {

	List<IngredientDto> getAllIngredients();

	IngredientDto addIngredient(Name name);
}
