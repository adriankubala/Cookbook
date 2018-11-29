package com.adriankubala.uni.cookbook.dish.presentation;

import com.adriankubala.uni.cookbook.dish.model.AddDishDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static com.adriankubala.uni.cookbook.dish.DishFixtures.*;
import static com.adriankubala.uni.cookbook.dish.presentation.DishRestController.DISHES_TERM_PARAM_NAME;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class DishRestControllerTest {

	private static final String BASE_URL = "/dishes/";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	void getAllDishes() throws Exception {
		mockMvc.perform(get(BASE_URL))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(EXISTING_DISHES_SIZE)));
	}

	@Test
	void getDishesByTerm() throws Exception {
		mockMvc.perform(get(BASE_URL)
			.param(DISHES_TERM_PARAM_NAME, DISH_TERM_PARAM_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(DISH_BY_TERM_SIZE)));
	}

	@Test
	void getDish() throws Exception {
		mockMvc.perform(get(BASE_URL + EXISTING_DISH_ID))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.id", is(EXISTING_DISH_ID.intValue())))
			.andExpect(jsonPath("$.ingredients", hasSize(EXISTING_DISH_INGREDIENTS_SIZE)));
	}

	@Test
	void getNonExistingDish() throws Exception {
		mockMvc.perform(get(BASE_URL + NON_EXISTING_DISH_ID))
			.andExpect(status().isNotFound());
	}

	@Test
	void addDish() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(getAddDishDto())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is(DISH_NAME)))
			.andExpect(jsonPath("$.picture", is(DISH_JPEG_PICTURE)))
			.andExpect(jsonPath("$.recipe", is(DISH_RECIPE)))
			.andExpect(jsonPath("$.ingredients", hasSize(DISH_INGREDIENT_IDS.size())))
			.andExpect(jsonPath("$.ingredients[0].id", is(DISH_INGREDIENT_IDS.get(0).intValue())))
			.andExpect(jsonPath("$.ingredients[1].id", is(DISH_INGREDIENT_IDS.get(1).intValue())));
	}

	@Test
	void addDishWithEmptyIngredientIds() throws Exception {
		AddDishDto dto = getAddDishDto();
		dto.setIngredientIds(Collections.emptyList());

		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(dto)))
			.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void deleteDish() throws Exception {
		mockMvc.perform(delete(BASE_URL + EXISTING_DISH_ID))
			.andExpect(status().isNoContent());
	}

	@Test
	void deleteNonExistingDish() throws Exception {
		mockMvc.perform(delete(BASE_URL + NON_EXISTING_DISH_ID))
			.andExpect(status().isNotFound());
	}
}
