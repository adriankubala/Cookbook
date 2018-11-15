package com.adriankubala.uni.cookbook.dish.presentation;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dishes")
class DishRestController {

	private static final String DISH_ID_PATH_VAR = "dishId";

	private final DishService dishService;

	DishRestController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping
	public List<DishDto> getAllDishes() {
		return dishService.getAllDishes();
	}

	@GetMapping("{" + DISH_ID_PATH_VAR + "}")
	public DishDto getDish(@PathVariable(DISH_ID_PATH_VAR) Long dishId) {
		return dishService.getDish(dishId);
	}
}
