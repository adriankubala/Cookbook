package com.adriankubala.uni.cookbook.dish.presentation;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dishes")
public class DishRestController {

	private final DishService dishService;

	public DishRestController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping
	public List<DishDto> getAllDishes() {
		return dishService.getAllDishes();
	}
}
