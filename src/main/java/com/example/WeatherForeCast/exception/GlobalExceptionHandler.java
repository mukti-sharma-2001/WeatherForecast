package com.example.WeatherForeCast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiNotAvailableException.class)
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ApiNotAvailableException ex){
		String message=ex.getMessage();
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
}
