package com.example.WeatherForeCast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		WebClient.Builder builder=WebClient.builder();
		return builder;
	}
}
