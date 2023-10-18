package com.example.WeatherForeCast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.WeatherForeCast.controller.WebForecastController;
import com.example.WeatherForeCast.entities.FeelsLike;
import com.example.WeatherForeCast.entities.FinalResponse;
import com.example.WeatherForeCast.service.WeatherForecastService;

@SpringBootTest
class WeatherForeCastApplicationTests {

	@Test
	void contextLoads() {
	}
	@Mock
    private WeatherForecastService service; // Assuming you have a WeatherService

    @InjectMocks
//	@Mock
    private WebForecastController controller;

    @Test
    void testGetWeatherForeCast() {
        // Arrange
        String city = "Khurja";
        String zip = "203131";
        String country = "IN";

        // Mock the combineResponse method
        FinalResponse mockResponse = new FinalResponse();
        mockResponse.setIsDayTime(true);
        mockResponse.setWeatherText("Sunny");
        mockResponse.setHasPrecipitation(false);
        mockResponse.setPrecipitationType("null");
        mockResponse.setVisibility(10000);
        mockResponse.setHumidity(18.0);
        mockResponse.setSunrise((long) 1696293708);
        mockResponse.setSunset((long) 1696336390);
        mockResponse.setFeels_like(new FeelsLike(32.47, "C"));
        
        
        when(service.combineResponse(city, zip, country)).thenReturn(mockResponse);

        // Act
        ResponseEntity<FinalResponse> responseEntity = controller.getWeatherForeCast(city, zip+","+country);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

}
