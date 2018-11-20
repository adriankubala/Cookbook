package com.adriankubala.uni.cookbook.ingredient.repository;

import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
