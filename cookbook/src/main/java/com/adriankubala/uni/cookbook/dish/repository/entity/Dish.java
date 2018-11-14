package com.adriankubala.uni.cookbook.dish.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishes_seq")
	@SequenceGenerator(name = "dishes_seq", sequenceName = "dishes_seq", allocationSize = 50)
	@Column(unique = true, updatable = false)
	private Long id;

	private String name;

	private String recipe;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dish dish = (Dish) o;
		return Objects.equals(name, dish.name) &&
			Objects.equals(recipe, dish.recipe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, recipe);
	}
}
