package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.exception.EntityAlreadyExistsException;
import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientNameDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.adriankubala.uni.cookbook.ingredient.IngredientFixtures.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class IngredientServiceTest {

	@Autowired
	private IngredientService ingredientService;

	@Test
	void getAllIngredients() {
		List<IngredientDto> dtos = ingredientService.getAllIngredients();

		assertNotNull(dtos);
		assertEquals(EXISTING_INGREDIENTS_SIZE, dtos.size());
	}

	@Test
	void addIngredient() {
		IngredientDto dto = ingredientService.addIngredient(new IngredientNameDto(NON_EXISTING_INGREDIENT_NAME));

		assertNotNull(dto);
		assertEquals(NON_EXISTING_INGREDIENT_NAME, dto.getName());
	}

	@Test
	void addIngredientWithWhitespaces() {
		IngredientDto dto = ingredientService.addIngredient(new IngredientNameDto(INGREDIENT_NAME_WITH_WHITESPACES));

		assertNotNull(dto);
		assertEquals(NON_EXISTING_INGREDIENT_NAME, dto.getName());
	}

	@Test
	void addIngredientWithNullName() {
		assertThrows(EntityValidationException.class, () -> ingredientService.addIngredient(new IngredientNameDto()));
	}

	@Test
	void addIngredientWithExistingName() {
		assertThrows(EntityAlreadyExistsException.class, () -> ingredientService.addIngredient(new IngredientNameDto(EXISTING_INGREDIENT_NAME)));
	}

	@Test
	void addIngredientWithBlankName() {
		assertThrows(EntityValidationException.class, () -> ingredientService.addIngredient(new IngredientNameDto(" ")));
	}

	@Test
	void addIngredientWithNonAlphabeticChars() {
		assertThrows(EntityValidationException.class, () -> ingredientService.addIngredient(new IngredientNameDto(INGREDIENT_NAME_WITH_NON_ALPHABETIC_CHAR)));
	}
}
