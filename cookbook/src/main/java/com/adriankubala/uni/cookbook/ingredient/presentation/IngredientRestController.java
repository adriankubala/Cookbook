package com.adriankubala.uni.cookbook.ingredient.presentation;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ingredients")
class IngredientRestController {

	private final IngredientService ingredientService;

	public IngredientRestController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public List<IngredientDto> getAllIngredients() {
		return ingredientService.getAllIngredients();
	}
}
