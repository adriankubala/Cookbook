package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.AddDishDto;
import com.adriankubala.uni.cookbook.dish.model.DishDto;

import java.util.List;

public interface DishService {

	List<DishDto> getAllDishes();

	List<DishDto> getDishesByTerm(String term);

	DishDto getDish(Long dishId);

	DishDto addDish(AddDishDto addDishDto);
}
