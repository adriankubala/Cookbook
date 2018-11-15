package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
