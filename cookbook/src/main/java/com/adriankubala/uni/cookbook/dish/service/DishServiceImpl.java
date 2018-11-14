package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

	private DishRepository dishRepository;

	public DishServiceImpl(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	@Override
	public List<DishDto> getAllDishes() {
		return DishMapper.toDishDtos(dishRepository.findAll());
	}
}
