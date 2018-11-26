package com.adriankubala.uni.cookbook.dish.repository.entity;

import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.repository.entity.Ingredient;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@Table(name = "dishes")
public class Dish {

	static final int MAX_NAME_LENGTH = 254;
	private static final Pattern PICTURE_REGEXP_PATTERN = Pattern.compile("^data:image\\/(jpeg|png);base64,(?:[A-Za-z0-9+\\/]{4})*(?:[A-Za-z0-9+\\/]{2}==|[A-Za-z0-9+\\/]{3}=)?$");

	private static final String SEQUENCE_NAME = "dishes_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Column(unique = true, updatable = false)
	private Long id;

	private String name;
	private String picture;
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
		validateName(name);
		this.name = name.trim();
	}

	private void validateName(String name) {
		checkIfNameIsNotNull(name);
		checkIfNameIsNotBlank(name);
		checkIfNameIsNotTooLong(name);
		checkIfNameDoesNotContainIllegalCharacters(name);
	}

	private void checkIfNameIsNotNull(String name) {
		if (name == null) {
			throw new EntityValidationException("Dish name may not be null.");
		}
	}

	private void checkIfNameIsNotBlank(String name) {
		if (name.trim().isEmpty()) {
			throw new EntityValidationException("Dish name may not be blank.");
		}
	}

	private void checkIfNameIsNotTooLong(String name) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new EntityValidationException(String.format("Dish name may not exceed %s characters.", MAX_NAME_LENGTH));
		}
	}

	private void checkIfNameDoesNotContainIllegalCharacters(String name) {
		if (nameContainsIllegalChars(name)) {
			throw new EntityValidationException("Dish may contain letters and hyphens only.");
		}
	}

	private boolean nameContainsIllegalChars(String name) {
		return name.chars().anyMatch(this::isCharIllegal);
	}

	private boolean isCharIllegal(int charCode) {
		return !(Character.isLetter(charCode) || Character.isWhitespace(charCode) || isCharHyphen(charCode));
	}

	private boolean isCharHyphen(int charCode) {
		return charCode == 45;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		validatePicture(picture);
		this.picture = picture;
	}

	private void validatePicture(String picture) {
		checkIfPictureIsNotNull(picture);
		checkIfPictureIsValid(picture);
	}

	private void checkIfPictureIsNotNull(String picture) {
		if (picture == null) {
			throw new EntityValidationException("Dish picture may not be null.");
		}
	}

	private void checkIfPictureIsValid(String picture) {
		if (pictureIsNotValid(picture)) {
			throw new EntityValidationException("Given dish picture is not valid. It must be base64 value with data scheme of type jpeg/png.");
		}
	}

	private boolean pictureIsNotValid(String picture) {
		return !PICTURE_REGEXP_PATTERN.matcher(picture).matches();
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		validateRecipe(recipe);
		this.recipe = recipe;
	}

	private void validateRecipe(String recipe) {
		checkIfRecipeIsNotNull(recipe);
		checkIfRecipeIsNotBlank(recipe);
	}

	private void checkIfRecipeIsNotNull(String recipe) {
		if (recipe == null) {
			throw new EntityValidationException("Dish recipe may not be null.");
		}
	}

	private void checkIfRecipeIsNotBlank(String recipe) {
		if (recipe.trim().isEmpty()) {
			throw new EntityValidationException("Dish recipe may not be blank.");
		}
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
			Objects.equals(picture, dish.picture) &&
			Objects.equals(recipe, dish.recipe) &&
			Objects.equals(ingredients, dish.ingredients);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, picture, recipe, ingredients);
	}
}
