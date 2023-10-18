package com.example.WeatherForeCast.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalResponse {
//	public FinalResponse(WeatherInfoByKey result1, WeatherInfoByLatLong result2) {
//		// TODO Auto-generated constructor stub
//		
//	}
	private String WeatherText;
	private boolean IsDayTime;
	private Temperature temperature;
	private FeelsLike feels_like;
	private boolean hasPrecipitation;
	private String precipitationType;
	private Wind wind;
	private Integer visibility;
	private Double humidity;
	private Long sunrise;
	private Long sunset;
	
	public FinalResponse(WeatherInfoByKey result1, WeatherInfoByLatLong result2) {
		// TODO Auto-generated constructor stub
		this.WeatherText=result1.getWeatherText();
		this.IsDayTime=result1.isIsDayTime();
		this.temperature=result1.getTemperature();
		this.hasPrecipitation=result1.isHasPrecipitation();
		this.precipitationType=result1.getPrecipitationType();
		this.wind=result2.getWind();
		this.visibility=result2.getVisibility();
		this.humidity=result2.getHumidity();
		this.feels_like=result2.getFeels_like();
		this.sunrise=result2.getSunrise();
		this.sunset=result2.getSunset();
	}
//	private WeatherInfoByLatLong weatherInfoByLatLong;
//	private WeatherInfoByKey weatherInfoByKey;

}
