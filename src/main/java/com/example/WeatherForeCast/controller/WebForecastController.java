package com.example.WeatherForeCast.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherForeCast.entities.FinalResponse;
import com.example.WeatherForeCast.entities.WeatherInfoByKey;
import com.example.WeatherForeCast.entities.WeatherInfoByLatLong;
import com.example.WeatherForeCast.service.AccuWeatherApiService;
import com.example.WeatherForeCast.service.OpenWeatherApiService;
import com.example.WeatherForeCast.service.WeatherForecastService;
import com.example.WeatherForeCast.util.RequestConvertor;

@RestController
@RequestMapping
public class WebForecastController {
	@Autowired
	private AccuWeatherApiService service1;
	@Autowired
	private OpenWeatherApiService service2;
	@Autowired
	private WeatherForecastService service3;
	@Autowired
	private RequestConvertor convertor;
	@GetMapping("/{cityName}")
	public ResponseEntity<WeatherInfoByKey> callApi(@PathVariable String cityName){
		return new ResponseEntity<>(service1.getWeatherInfoUsingKey(cityName),HttpStatus.OK);
	}	
	
	@GetMapping("/api/{zip}/{countryCode}")
	public ResponseEntity<WeatherInfoByLatLong> callApi2(@PathVariable String zip,@PathVariable String countryCode) {
		System.out.println("in control");
		return new ResponseEntity<>(service2.getInfo(zip, countryCode),HttpStatus.OK);
	}
	@GetMapping("/weather")
	public ResponseEntity<FinalResponse> getWeatherForeCast(	@RequestParam(value = "city", required = true) String city,
									            @RequestParam(value = "zip", required = true) String zip)
//									            ,@RequestParam(value = "country", required = true) String country) 
	{
//	public ResponseEntity<FinalResponse> getWeatherForeCast(@PathVariable String city,@PathVariable String zip,@PathVariable String country) 
//	{
//		try {
		String[] zipCountry=convertor.getZipCountrySep(zip);
		 return new ResponseEntity<>(service3.combineResponse(city, zipCountry[0], zipCountry[1]),HttpStatus.OK);
//		}
	}

}
