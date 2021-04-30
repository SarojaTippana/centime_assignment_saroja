package com.centime.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exception {

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> exception(ConstraintViolationException ex) {
		// LOGGER.error("Invalid Input Exception: ", ex.getMessage());
		return new ResponseEntity<Object>("not  a valdi input value ....", HttpStatus.BAD_REQUEST);

	}
}
