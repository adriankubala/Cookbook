package com.adriankubala.uni.cookbook.ingredient.presentation;

import com.adriankubala.uni.cookbook.ingredient.model.IngredientNameDto;
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

import static com.adriankubala.uni.cookbook.ingredient.IngredientFixtures.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class IngredientRestControllerTest {

	private static final String BASE_URL = "/ingredients/";

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
	void getAllIngredients() throws Exception {
		mockMvc.perform(get(BASE_URL))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(EXISTING_INGREDIENTS_SIZE)));
	}

	@Test
	void addIngredient() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto(NON_EXISTING_INGREDIENT_NAME))))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is(NON_EXISTING_INGREDIENT_NAME)));
	}

	@Test
	void addIngredientWithWhitespaces() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto(INGREDIENT_NAME_WITH_WHITESPACES))))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is(NON_EXISTING_INGREDIENT_NAME)));
	}

	@Test
	void addIngredientWithNullName() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto())))
			.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void addIngredientWithExistingName() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto(EXISTING_INGREDIENT_NAME))))
			.andExpect(status().isConflict());
	}

	@Test
	void addIngredientWithBlankName() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto(" "))))
			.andExpect(status().isUnprocessableEntity());
	}

	@Test
	void addIngredientWithNonAlphabeticChars() throws Exception {
		mockMvc.perform(post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsBytes(new IngredientNameDto(NON_EXISTING_INGREDIENT_NAME + "!"))))
			.andExpect(status().isUnprocessableEntity());
	}
}
