package com.example.WeatherForeCast.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoByKey {
	private String WeatherText;
	private boolean IsDayTime;
	private Temperature temperature;
	private boolean hasPrecipitation;
	private String precipitationType;
	
}
