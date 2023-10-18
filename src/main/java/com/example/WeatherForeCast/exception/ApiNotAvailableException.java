package com.example.WeatherForeCast.exception;

import lombok.Getter;

@Getter
public class ApiNotAvailableException extends RuntimeException{
	private String message;

	public ApiNotAvailableException(String exception) {
		super();
		this.message = message;
	}
	
}
