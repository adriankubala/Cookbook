package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.exception.EntityAlreadyExistsException;
import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientNameDto;
import com.adriankubala.uni.cookbook.ingredient.repository.IngredientRepository;
import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;

	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public List<IngredientDto> getAllIngredients() {
		return IngredientMapper.toIngredientDtos(ingredientRepository.findAll());
	}

	@Override
	public IngredientDto addIngredient(IngredientNameDto ingredientNameDto) {
		checkIfIngredientNameIsNotNull(ingredientNameDto);
		String trimmedName = trimIngredientName(ingredientNameDto);
		validateIngredientName(trimmedName);
		return IngredientMapper.toIngredientDto(saveIngredient(trimmedName));
	}

	private void checkIfIngredientNameIsNotNull(IngredientNameDto ingredientNameDto) {
		if (ingredientNameDto.getName() == null) {
			throw new EntityValidationException("Ingredient name may not be null.");
		}
	}

	private String trimIngredientName(IngredientNameDto ingredientNameDto) {
		return ingredientNameDto.getName().trim();
	}

	private void validateIngredientName(String name) {
		checkIfIngredientNameIsNotBlank(name);
		checkIfIngredientNameIsAlphabeticOnly(name);
		checkIfIngredientDoesNotExistByName(name);
	}

	private void checkIfIngredientNameIsNotBlank(String name) {
		if (name.isEmpty()) {
			throw new EntityValidationException("Ingredient name may not be blank.");
		}
	}

	private void checkIfIngredientNameIsAlphabeticOnly(String name) {
		if (allCharsAreAlphabetic(name)) {
			throw new EntityValidationException("Ingredient may contain only alphabetic characters.");
		}
	}

	private boolean allCharsAreAlphabetic(String name) {
		return !name.chars().allMatch(Character::isLetter);
	}

	private void checkIfIngredientDoesNotExistByName(String name) {
		if (ingredientRepository.existsByName(name)) {
			throw new EntityAlreadyExistsException("Ingredient with this name already exists.");
		}
	}

	private Ingredient saveIngredient(String name) {
		return ingredientRepository.save(new Ingredient(name));
	}
}
