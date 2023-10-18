package com.example.WeatherForeCast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WeatherForeCast.entities.Temperature;
import com.example.WeatherForeCast.entities.WeatherInfoByKey;
import com.example.WeatherForeCast.exception.ApiNotAvailableException;
import com.example.WeatherForeCast.util.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccuWeatherApiService {
	@Autowired
	private WebClient.Builder  webClientBuilder;
	public String getKeyValueFromAccuweather(String cityName){
		
		 return webClientBuilder.build()
							.get()
							.uri(AppConstants.url1+"?q="+cityName+"&apikey="+AppConstants.apiKey1)
							.retrieve()
							.bodyToMono(String.class)
			                .map(jsonResponse -> {
			                    JsonNode rootNode = null;
								try {
									rootNode = new ObjectMapper().readTree(jsonResponse);
								} catch (JsonProcessingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			                    // Extract values from the JSON structure
			                    JsonNode resultsNode = rootNode.get(0);
			                    String key = resultsNode.get("Key").asText();
			                    return key;
			                })
							.block();	  	
	}
	public WeatherInfoByKey getWeatherInfoUsingKey(String cityName) {
		System.out.println("in key service");
		String key=getKeyValueFromAccuweather(cityName);
		return webClientBuilder.build()
				.get()
				.uri(AppConstants.url2+key+"?apikey="+AppConstants.apiKey1)
				.retrieve()
				.bodyToMono(String.class)
                .map(jsonResponse -> {
                    JsonNode rootNode = null;
					try {
						rootNode = new ObjectMapper().readTree(jsonResponse);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					} catch(ApiNotAvailableException) {
//						new ApiNotAvailableException(ex.get)
//					}
                    // Extract values from the JSON structure
					
                    JsonNode resultsNode = rootNode.get(0);
                    Temperature temp=new Temperature(resultsNode.get("Temperature").get("Metric").get("Value").asDouble(),resultsNode.get("Temperature").get("Metric").get("Unit").asText());
                    
                    WeatherInfoByKey info=new WeatherInfoByKey(resultsNode.get("WeatherText").asText(),resultsNode.get("IsDayTime").asBoolean(), temp, resultsNode.get("HasPrecipitation").asBoolean(), resultsNode.get("PrecipitationType").asText());
                    
                    return info;
                })
				.block();
	}
}
