package com.adriankubala.uni.cookbook.exception;

public class CookbookException extends RuntimeException {

	public CookbookException() {
		super();
	}

	public CookbookException(String message) {
		super(message);
	}

	public CookbookException(String message, Throwable cause) {
		super(message, cause);
	}

	public CookbookException(Throwable cause) {
		super(cause);
	}

	protected CookbookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
