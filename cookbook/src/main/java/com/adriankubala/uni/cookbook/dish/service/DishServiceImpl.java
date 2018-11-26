package com.adriankubala.uni.cookbook.dish.service;

import com.adriankubala.uni.cookbook.dish.model.AddDishDto;
import com.adriankubala.uni.cookbook.dish.model.DishDto;
import com.adriankubala.uni.cookbook.dish.repository.DishRepository;
import com.adriankubala.uni.cookbook.dish.repository.entity.Dish;
import com.adriankubala.uni.cookbook.exception.EntityNotFoundException;
import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.repository.IngredientRepositoryFacade;
import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DishServiceImpl implements DishService {

	private final DishRepository dishRepository;
	private final IngredientRepositoryFacade ingredientRepositoryFacade;

	DishServiceImpl(DishRepository dishRepository, IngredientRepositoryFacade ingredientRepositoryFacade) {
		this.dishRepository = dishRepository;
		this.ingredientRepositoryFacade = ingredientRepositoryFacade;
	}

	@Override
	public List<DishDto> getAllDishes() {
		return DishMapper.toDishDtos(dishRepository.findAll());
	}

	@Override
	public DishDto getDish(Long dishId) {
		return dishRepository.findById(dishId)
			.map(DishMapper::toDishDto)
			.orElseThrow(() -> new EntityNotFoundException("Dish with the given ID could not be found."));
	}

	@Override
	public DishDto addDish(AddDishDto addDishDto) {
		Dish entity = new Dish();
		entity.setName(addDishDto.getName());
		entity.setPicture(addDishDto.getPicture());
		entity.setRecipe(addDishDto.getRecipe());
		checkIngredientIds(addDishDto);
		entity.setIngredients(findAllIngredientsById(addDishDto));
		return DishMapper.toDishDto(saveEntity(entity));
	}

	private void checkIngredientIds(AddDishDto addDishDto) {
		checkIfIngredientIdsIsNotNull(addDishDto);
		checkIfIngredientIdsIsNotEmpty(addDishDto);
	}

	private void checkIfIngredientIdsIsNotNull(AddDishDto addDishDto) {
		if (addDishDto.getIngredientIds() == null) {
			throw new EntityValidationException("Dish's ingredientsIds may not be null.");
		}
	}

	private void checkIfIngredientIdsIsNotEmpty(AddDishDto addDishDto) {
		if (addDishDto.getIngredientIds().isEmpty()) {
			throw new EntityValidationException("Dish's ingredientsIds may not be empty.");
		}
	}

	private List<Ingredient> findAllIngredientsById(AddDishDto addDishDto) {
		return ingredientRepositoryFacade.findAllById(addDishDto.getIngredientIds());
	}

	private Dish saveEntity(Dish entity) {
		return dishRepository.save(entity);
	}
}
