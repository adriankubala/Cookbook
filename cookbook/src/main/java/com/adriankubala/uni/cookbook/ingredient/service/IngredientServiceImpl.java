package com.adriankubala.uni.cookbook.ingredient.service;

import com.adriankubala.uni.cookbook.exception.EntityAlreadyExistsException;
import com.adriankubala.uni.cookbook.ingredient.model.IngredientDto;
import com.adriankubala.uni.cookbook.ingredient.model.Name;
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
	public IngredientDto addIngredient(Name name) {
		Ingredient entity = new Ingredient();
		entity.setName(name);
		checkIfIngredientDoesNotExistByName(name);
		return IngredientMapper.toIngredientDto(saveIngredient(entity));
	}

	private void checkIfIngredientDoesNotExistByName(Name name) {
		if (ingredientRepository.existsByName(name.getValue())) {
			throw new EntityAlreadyExistsException("Ingredient with this name already exists.");
		}
	}

	private Ingredient saveIngredient(Ingredient entity) {
		return ingredientRepository.save(entity);
	}
}
