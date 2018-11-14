package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class DishServiceTest {

	@Autowired
	private DishService dishService;

	@Test
	public void getAllDishes() {
		List<DishDto> dtos = dishService.getAllDishes();

		assertNotNull(dtos);
		assertEquals(3, dtos.size());
	}
}
