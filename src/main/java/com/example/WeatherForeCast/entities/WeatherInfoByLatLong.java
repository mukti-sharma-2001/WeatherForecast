package com.example.WeatherForeCast.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoByLatLong {
	private Wind wind;
	private Integer visibility;
	private Double humidity;
	private Long sunrise;
	private Long sunset;
	private FeelsLike feels_like;
	
}	