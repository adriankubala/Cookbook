package com.adriankubala.uni.cookbook.ingredient.presentation;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientNameDto;
import com.adriankubala.uni.cookbook.ingredient.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public ResponseEntity<IngredientDto> addIngredient(@RequestBody IngredientNameDto ingredientNameDto) {
		return new ResponseEntity<>(ingredientService.addIngredient(ingredientNameDto), HttpStatus.CREATED);
	}
}
