package com.centime.exception;

public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String exception) {

		super(exception);
		System.out.println("----------------------");
	}
}
