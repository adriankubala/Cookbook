package com.adriankubala.uni.cookbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityValidationException extends CookbookException {

	public EntityValidationException() {
		super();
	}

	public EntityValidationException(String message) {
		super(message);
	}

	public EntityValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityValidationException(Throwable cause) {
		super(cause);
	}

	protected EntityValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
