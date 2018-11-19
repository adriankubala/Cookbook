package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.adriankubala.uni.cookbook.ingredient.IngredientFixtures.EXISTING_INGREDIENTS_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
