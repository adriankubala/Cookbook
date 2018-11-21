package com.adriankubala.uni.cookbook.ingredient.repository.entity;

import com.adriankubala.uni.cookbook.exception.EntityValidationException;
import com.adriankubala.uni.cookbook.ingredient.model.Name;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {

	private static final int MAX_NAME_LENGTH = 254;

	private static final String SEQUENCE_NAME = "ingredients_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Column(unique = true, updatable = false)
	private Long id;

	private String name;

	public void setName(Name name) {
		validate(name);
		this.name = trim(name);
	}

	private void validate(Name name) {
		checkIfNameIsNotNull(name);
		checkIfNameIsNotBlank(name);
		checkIfNameIsNotTooLong(name);
		checkIfNameDoesNotContainIllegalChars(name);
	}

	private void checkIfNameIsNotNull(Name name) {
		if (name.getValue() == null) {
			throw new EntityValidationException("Ingredient name may not be null.");
		}
	}

	private void checkIfNameIsNotBlank(Name name) {
		if (trim(name).isEmpty()) {
			throw new EntityValidationException("Ingredient name may not be blank.");
		}
	}

	private void checkIfNameIsNotTooLong(Name name) {
		if (name.getValue().length() > MAX_NAME_LENGTH) {
			throw new EntityValidationException("Ingredient name is too long.");
		}
	}

	private void checkIfNameDoesNotContainIllegalChars(Name name) {
		if (nameContainsIllegalChars(name)) {
			throw new EntityValidationException("Ingredient may not contain illegal characters.");
		}
	}

	private boolean nameContainsIllegalChars(Name name) {
		return name.getValue().chars().anyMatch(this::isCharIllegal);
	}

	private boolean isCharIllegal(int charCode) {
		return !(Character.isDigit(charCode) || Character.isLetter(charCode) || Character.isWhitespace(charCode));
	}

	private String trim(Name name) {
		return name.getValue().trim();
	}

	public Long getId() {
		return id;
	}

	public Name getName() {
		return new Name(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ingredient that = (Ingredient) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
