package com.adriankubala.uni.cookbook.dish.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.adriankubala.uni.cookbook.dish.DishFixtures.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class DishRestControllerTest {

	private static final String BASE_URL = "/dishes/";

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
}
