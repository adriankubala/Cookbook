package com.adriankubala.uni.cookbook.ingredient;

public final class IngredientFixtures {

	public static final int EXISTING_INGREDIENTS_SIZE = 10;

	public static final String EXISTING_INGREDIENT_NAME = "Eggs";
	public static final String NON_EXISTING_INGREDIENT_NAME = "1 butter";
	public static final String INGREDIENT_NAME_WITH_WHITESPACES = " " + NON_EXISTING_INGREDIENT_NAME + " ";
	public static final String INGREDIENT_NAME_WITH_NON_ALPHABETIC_CHAR = NON_EXISTING_INGREDIENT_NAME + "!";

	private IngredientFixtures() {
	}
}
