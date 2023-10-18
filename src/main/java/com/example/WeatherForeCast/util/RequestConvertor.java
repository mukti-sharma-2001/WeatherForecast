package com.example.WeatherForeCast.util;


import org.springframework.stereotype.Component;

@Component
public class RequestConvertor {
	 public String[] getZipCountrySep(String str){
		String[] words=str.split(",");
		return words;
				
	}
}
