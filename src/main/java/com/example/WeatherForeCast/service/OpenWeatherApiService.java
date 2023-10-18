package com.example.WeatherForeCast.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WeatherForeCast.entities.FeelsLike;
import com.example.WeatherForeCast.entities.WeatherInfoByLatLong;
import com.example.WeatherForeCast.entities.Wind;
import com.example.WeatherForeCast.util.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class OpenWeatherApiService {
	@Autowired
	private WebClient.Builder  webClientBuilder;
	public List<Double> getLatLong(String zipCode,String countryCode) {
		List<Double> latLong=new ArrayList<>();
		System.out.println("starting get");
		return webClientBuilder.build()
				.get()
				.uri(AppConstants.url3+"?zip="+zipCode+","+countryCode+"&appid="+AppConstants.apiKey2)
				.retrieve()
				.bodyToMono(String.class)
                .map(jsonResponse -> {
                    JsonNode rootNode = null;
					try {
						System.out.println("in try");
						rootNode = new ObjectMapper().readTree(jsonResponse);
//						System.out.println(rootNode.get("lat"));
//						System.out.println(rootNode.get("long"));
	                    Double lat = rootNode.get("lat").asDouble();
	                   Double longitude=rootNode.get("lon").asDouble();
	                   System.out.println(lat+"-----"+longitude);
	                    latLong.add(lat);
	                    latLong.add(longitude);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   
					
                    return latLong;
                })
				.block();
	}
	public WeatherInfoByLatLong getInfo(String zipCode,String countryCode) {
		System.out.println("in lat, lan service");
		List<Double> latLong=getLatLong(zipCode,countryCode);
		Double lat=latLong.get(0);
		Double longitude=latLong.get(1);
		WeatherInfoByLatLong info=new WeatherInfoByLatLong();
		return webClientBuilder.build()
			.get()
			.uri(AppConstants.url4+"?lat="+lat+"&lon="+longitude+"&appid="+AppConstants.apiKey2)
			.retrieve()
			.bodyToMono(String.class)
	        .map(jsonResponse -> {
	            JsonNode rootNode = null;
				try {
					
						rootNode = new ObjectMapper().readTree(jsonResponse);
						System.out.println(rootNode);
					   System.out.println("in wind");
					   Double speed=rootNode.get("wind").get("speed").asDouble();
					   System.out.println(speed);
					   System.out.println("in deg");
					   Integer deg= rootNode.get("wind").get("deg").asInt();
					   System.out.println(deg);
					   System.out.println("in gust");
					   Double gust= rootNode.get("wind").get("gust").asDouble();
					   System.out.println(gust);
					   System.out.println("in co");
					   Wind wind=new Wind(speed,deg,gust);
			           info.setWind(wind);
			           System.out.println("in visib");
			           info.setVisibility(rootNode.get("visibility").asInt());
			           System.out.println("in feel");
			           info.setFeels_like(new FeelsLike(KelvinToCelcius(rootNode.get("main").get("feels_like").asDouble()),"C"));
					   System.out.println("in humid");
			           info.setHumidity(rootNode.get("main").get("humidity").asDouble());
			           System.out.println("in rise");
					   info.setSunrise(rootNode.get("sys").get("sunrise").asLong());
					   System.out.println("in set");
					   info.setSunset(rootNode.get("sys").get("sunset").asLong());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   System.out.println(info);	           
	           return info;
	        })
			.block();
		
	}
	public Double KelvinToCelcius(Double temp) {
		String T=String.format("%.2f", temp-273.15);
		Double Temp=Double.parseDouble(T);
		return Temp;
	}
}
