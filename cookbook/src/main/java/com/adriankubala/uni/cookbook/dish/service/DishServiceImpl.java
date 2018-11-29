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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
class DishServiceImpl implements DishService {

	private static final String ENTITY_NOT_FOUND_EXCEPTION_MSG = "Dish with the given ID could not be found.";

	private final DishRepository dishRepository;
	private final IngredientRepositoryFacade ingredientRepositoryFacade;

	DishServiceImpl(DishRepository dishRepository, IngredientRepositoryFacade ingredientRepositoryFacade) {
		this.dishRepository = dishRepository;
		this.ingredientRepositoryFacade = ingredientRepositoryFacade;
	}

	@Override
	public List<DishDto> getAllDishes() {
		return DishMapper.toDishDtos(findAllEntities());
	}

	private List<Dish> findAllEntities() {
		return dishRepository.findAll();
	}

	@Override
	public List<DishDto> getDishesByTerm(String term) {
		return DishMapper.toDishDtos(findEntitiesBy(term));
	}

	private List<Dish> findEntitiesBy(String term) {
		return findAllEntities().stream()
			.filter(entity -> isEntityFoundInTerm(entity, term))
			.collect(Collectors.toList());
	}

	private boolean isEntityFoundInTerm(Dish entity, String term) {
		return getRelevantEntityNames(entity).stream()
			.anyMatch(name -> isAnyNameFoundInTerm(name, term));
	}

	private List<String> getRelevantEntityNames(Dish entity) {
		List<String> names = entity.getIngredientNames();
		names.add(entity.getName());
		return names;
	}

	private boolean isAnyNameFoundInTerm(String name, String term) {
		return splitTerm(term).stream()
			.anyMatch(termPart -> doesNameContainsTermPartIgnoreCase(name, termPart));
	}

	private List<String> splitTerm(String term) {
		return Arrays.asList(term.trim().split("\\s+"));
	}

	private boolean doesNameContainsTermPartIgnoreCase(String name, String termPart) {
		return name.toUpperCase().contains(termPart.toUpperCase());
	}

	@Override
	public DishDto getDish(Long dishId) {
		return dishRepository.findById(dishId)
			.map(DishMapper::toDishDto)
			.orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MSG));
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

	@Override
	public void deleteDish(Long dishId) {
		checkIfEntityExistsBy(dishId);
		dishRepository.deleteById(dishId);
	}

	private void checkIfEntityExistsBy(Long dishId) {
		if (!dishRepository.existsById(dishId)) {
			throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION_MSG);
		}
	}
}
