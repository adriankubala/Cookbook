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
		checkIfIngredientNameIsNotBlank(trimmedName);
		checkIfIngredientNameIsAlphabeticOnly(trimmedName);
		checkIfIngredientExistsByName(trimmedName);
		return IngredientMapper.toIngredientDto(ingredientRepository.save(new Ingredient(trimmedName)));
	}

	private void checkIfIngredientNameIsNotNull(IngredientNameDto ingredientNameDto) {
		if (ingredientNameDto.getName() == null) {
			throw new EntityValidationException();
		}
	}

	private String trimIngredientName(IngredientNameDto ingredientNameDto) {
		return ingredientNameDto.getName().trim();
	}

	private void checkIfIngredientNameIsNotBlank(String name) {
		if (name.isEmpty()) {
			throw new EntityValidationException();
		}
	}

	private void checkIfIngredientNameIsAlphabeticOnly(String name) {
		if (allCharsAreAlphabetic(name)) {
			throw new EntityValidationException();
		}
	}

	private boolean allCharsAreAlphabetic(String name) {
		return !name.chars().allMatch(Character::isLetter);
	}

	private void checkIfIngredientExistsByName(String name) {
		if (ingredientRepository.existsByName(name)) {
			throw new EntityAlreadyExistsException();
		}
	}
}
