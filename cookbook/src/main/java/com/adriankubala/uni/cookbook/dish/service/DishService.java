package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;

import java.util.List;

public interface DishService {

	List<DishDto> getAllDishes();

	DishDto getDish(Long dishId);
}
