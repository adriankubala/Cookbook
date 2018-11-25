package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.AddDishDto;
import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.exception.EntityNotFoundException;
import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.adriankubala.uni.cookbook.dish.DishFixtures.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class DishServiceTest {

	@Autowired
	private DishService dishService;

	@Test
	void getAllDishes() {
		List<DishDto> dtos = dishService.getAllDishes();

		assertNotNull(dtos);
		assertEquals(EXISTING_DISHES_SIZE, dtos.size());
	}

	@Test
	void getDish() {
		DishDto dto = dishService.getDish(EXISTING_DISH_ID);

		assertNotNull(dto);
		assertEquals(EXISTING_DISH_ID, dto.getId());
		assertEquals(EXISTING_DISH_INGREDIENTS_SIZE, dto.getIngredients().size());
	}

	@Test
	void getNonExistingDish() {
		assertThrows(EntityNotFoundException.class, () -> dishService.getDish(NON_EXISTING_DISH_ID));
	}

	@Test
	void addDish() {
		DishDto dto = dishService.addDish(getAddDishDto());

		assertNotNull(dto);
		assertEquals(DISH_NAME, dto.getName());
		assertEquals(DISH_JPEG_PICTURE, dto.getPicture());
		assertEquals(DISH_RECIPE, dto.getRecipe());
		assertEquals(DISH_INGREDIENT_IDS, getIngredientIds(dto));
	}

	@Test
	void addDishWithNullIngredientIds() {
		AddDishDto dto = getAddDishDto();
		dto.setIngredientIds(null);

		assertThrows(EntityValidationException.class, () -> dishService.addDish(dto));
	}

	@Test
	void addDishWithEmptyIngredientIds() {
		AddDishDto dto = getAddDishDto();
		dto.setIngredientIds(Collections.emptyList());

		assertThrows(EntityValidationException.class, () -> dishService.addDish(dto));
	}

	@Test
	void addDishWithNonExistingIngredientId() {
		AddDishDto dto = getAddDishDto();
		dto.setIngredientIds(Arrays.asList(1L, 345L));

		assertThrows(EntityNotFoundException.class, () -> dishService.addDish(dto));
	}

	private List<Long> getIngredientIds(DishDto dishDto) {
		return dishDto.getIngredients().stream()
			.map(IngredientDto::getId)
			.collect(Collectors.toList());
	}
}
