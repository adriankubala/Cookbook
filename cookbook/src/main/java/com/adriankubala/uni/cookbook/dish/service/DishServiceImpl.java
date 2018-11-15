package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.repository.DishRepository;
import com.adriankubala.uni.cookbook.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DishServiceImpl implements DishService {

	private final DishRepository dishRepository;

	DishServiceImpl(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	@Override
	public List<DishDto> getAllDishes() {
		return DishMapper.toDishDtos(dishRepository.findAll());
	}

	@Override
	public DishDto getDish(Long dishId) {
		return dishRepository.findById(dishId)
			.map(DishMapper::toDishDto)
			.orElseThrow(EntityNotFoundException::new);
	}
}
