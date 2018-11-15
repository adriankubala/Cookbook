package com.adriankubala.uni.cookbook.dish.repository.entity;

import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

	private static final String SEQUENCE_NAME = "dishes_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Column(unique = true, updatable = false)
	private Long id;

	private String name;

	private String recipe;

	@ManyToMany
	@JoinTable(
		name = "dishes_ingredients",
		joinColumns = {@JoinColumn(name = "dish_id")},
		inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
	)
	private List<Ingredient> ingredients;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dish dish = (Dish) o;
		return Objects.equals(name, dish.name) &&
			Objects.equals(recipe, dish.recipe) &&
			Objects.equals(ingredients, dish.ingredients);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, recipe, ingredients);
	}
}
