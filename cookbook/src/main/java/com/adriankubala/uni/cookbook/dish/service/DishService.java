package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.model.AddDishDto;

import java.util.List;

public interface DishService {

	List<DishDto> getAllDishes();

	DishDto getDish(Long dishId);

	DishDto addDish(AddDishDto addDishDto);
}
