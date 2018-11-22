package com.adriankubala.uni.cookbook.dish.repository.entity;

import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import liquibase.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adriankubala.uni.cookbook.dish.DishFixtures.*;
import static com.adriankubala.uni.cookbook.dish.repository.entity.Dish.MAX_NAME_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DishTest {

	private Dish dish;

	@BeforeEach
	void setUp() {
		dish = new Dish();
	}

	@Test
	void setName() {
		dish.setName(DISH_NAME);

		assertEquals(DISH_NAME, dish.getName());
	}

	@Test
	void setNameWithWhitespaces() {
		dish.setName(" " + DISH_NAME + " ");

		assertEquals(DISH_NAME, dish.getName());
	}

	@Test
	void setNullName() {
		assertThrows(EntityValidationException.class, () -> dish.setName(null));
	}

	@Test
	void setBlankName() {
		assertThrows(EntityValidationException.class, () -> dish.setName(" "));
	}

	@Test
	void setTooLongName() {
		assertThrows(EntityValidationException.class, () -> dish.setName(StringUtils.repeat("a", MAX_NAME_LENGTH + 1)));
	}

	@Test
	void setNameWithSpecialChar() {
		assertThrows(EntityValidationException.class, () -> dish.setName(DISH_NAME + "!"));
	}

	@Test
	void setNameWithNumber() {
		assertThrows(EntityValidationException.class, () -> dish.setName(DISH_NAME + "3"));
	}

	@Test
	void setJpegPicture() {
		dish.setPicture(DISH_JPEG_PICTURE);

		assertEquals(DISH_JPEG_PICTURE, dish.getPicture());
	}

	@Test
	void setPngPicture() {
		dish.setPicture(DISH_PNG_PICTURE);

		assertEquals(DISH_PNG_PICTURE, dish.getPicture());
	}

	@Test
	void setNullPicture() {
		assertThrows(EntityValidationException.class, () -> dish.setPicture(null));
	}

	@Test
	void setBlankPicture() {
		assertThrows(EntityValidationException.class, () -> dish.setPicture(" "));
	}

	@Test
	void setPictureWithPlainText() {
		assertThrows(EntityValidationException.class, () -> dish.setPicture(DISH_NAME));
	}

	@Test
	void setPictureWithoutDataScheme() {
		int i = DISH_JPEG_PICTURE.indexOf(",") + 1;
		String picture = DISH_JPEG_PICTURE.substring(i);

		assertThrows(EntityValidationException.class, () -> dish.setPicture(picture));
	}

	@Test
	void setRecipe() {
		dish.setRecipe(DISH_RECIPE);

		assertEquals(DISH_RECIPE, dish.getRecipe());
	}

	@Test
	void setNullRecipe() {
		assertThrows(EntityValidationException.class, () -> dish.setRecipe(null));
	}

	@Test
	void setBlankRecipe() {
		assertThrows(EntityValidationException.class, () -> dish.setRecipe(" "));
	}
}
