package com.adriankubala.uni.cookbook.exception;

public class EntityAlreadyExistsException extends CookbookException {

	public EntityAlreadyExistsException() {
		super();
	}

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	protected EntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
