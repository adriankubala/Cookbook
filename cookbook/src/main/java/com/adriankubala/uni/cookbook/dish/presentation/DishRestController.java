package com.adriankubala.uni.cookbook.dish.presentation;

import com.adriankubala.uni.cookbook.dish.model.AddDishDto;
import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dishes")
class DishRestController {

	static final String DISHES_TERM_PARAM_NAME = "term";
	private static final String DISH_ID_PATH_VAR = "dishId";
	private static final String DISH_ID_PATH_VAR_MAPPING = "{" + DISH_ID_PATH_VAR + "}";

	private final DishService dishService;

	DishRestController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping
	public List<DishDto> getAllDishes() {
		return dishService.getAllDishes();
	}

	@GetMapping(params = DISHES_TERM_PARAM_NAME)
	public List<DishDto> searchDishes(@RequestParam(DISHES_TERM_PARAM_NAME) String term) {
		return dishService.getDishesByTerm(term);
	}

	@GetMapping(DISH_ID_PATH_VAR_MAPPING)
	public DishDto getDish(@PathVariable(DISH_ID_PATH_VAR) Long dishId) {
		return dishService.getDish(dishId);
	}

	@PostMapping
	public ResponseEntity<DishDto> addDish(@RequestBody AddDishDto addDishDto) {
		return new ResponseEntity<>(dishService.addDish(addDishDto), HttpStatus.CREATED);
	}

	@DeleteMapping(DISH_ID_PATH_VAR_MAPPING)
	public ResponseEntity deleteDish(@PathVariable(DISH_ID_PATH_VAR) Long dishId) {
		dishService.deleteDish(dishId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
