package com.adriankubala.uni.cookbook.ingredient.repository;

import com.adriankubala.uni.cookbook.exception.EntityNotFoundException;
import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientRepositoryFacade {

	private final IngredientRepository ingredientRepository;

	public IngredientRepositoryFacade(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<Ingredient> findAllById(Collection<Long> ids) {
		List<Ingredient> entities = ingredientRepository.findAllById(ids);
		checkIfFoundAllIds(ids, entities);
		return entities;
	}

	private void checkIfFoundAllIds(Collection<Long> ids, List<Ingredient> entities) {
		if (ids.size() != entities.size()) {
			throw new EntityNotFoundException(String.format("Ingredients with the given IDs: %s could not be found.", findNotFoundIds(ids, entities)));
		}
	}

	private List<Long> findNotFoundIds(Collection<Long> ids, List<Ingredient> entities) {
		return ids.stream()
			.filter(id -> noneEntityMatchesId(entities, id))
			.collect(Collectors.toList());
	}

	private boolean noneEntityMatchesId(List<Ingredient> ids, Long entityId) {
		return ids.stream()
			.noneMatch(entity -> entity.getId().equals(entityId));
	}

}
