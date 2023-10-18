package com.example.WeatherForeCast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WeatherForeCast.entities.FinalResponse;
import com.example.WeatherForeCast.entities.WeatherInfoByKey;
import com.example.WeatherForeCast.entities.WeatherInfoByLatLong;

@Service
public class WeatherForecastService {
	@Autowired
	private AccuWeatherApiService service1;
	@Autowired
	private OpenWeatherApiService service2;
	public FinalResponse combineResponse(String cityName,String zipCode, String countryId) {
		List<FinalResponse> res=new ArrayList<>();
		System.out.println(service1.getWeatherInfoUsingKey(cityName));
		System.out.println(service2.getInfo(zipCode, countryId));
//		ExecutorService executor = Executors.newFixedThreadPool(1);
//		CompletableFuture<WeatherInfoByKey> future1 = CompletableFuture.supplyAsync(() -> service1.getWeatherInfoUsingKey(cityName));
//	    CompletableFuture<WeatherInfoByLatLong> future2 = CompletableFuture.supplyAsync(() -> service2.getInfo(zipCode, countryId));
//	    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);
//	    try {
//			combinedFuture.get();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	    System.out.println("futures: "+combinedFuture);
		CompletableFuture<WeatherInfoByKey> future1 = CompletableFuture.supplyAsync(() -> service1.getWeatherInfoUsingKey(cityName));
		CompletableFuture<WeatherInfoByLatLong> future2 = CompletableFuture.supplyAsync(() -> service2.getInfo(zipCode, countryId));

		CompletableFuture<List<FinalResponse>> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
		    // Create FinalResponse based on result1 and result2
		    FinalResponse finalResponse = new FinalResponse(result1, result2);

		    // Convert to a list
//		    System.out.println(List.of(finalResponse));
		    return List.of(finalResponse);
		});

		try {
		    res = combinedFuture.get();
		    System.out.println("Final Responses: " + res);
		    return res.get(0);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;

	    
//	    List<FinalResponse> res=Stream.of(future1,future2).map(CompletableFuture::join).flatMap(List::stream).collect(Collectors.toList());
//	    List<PaymentDto> combinedTransactions = Stream.of(future1, future2, future3)
//                .map(CompletableFuture::join)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//		List<PaymentDto> success=combinedTransactions.stream().filter(p->p.getStatus().trim().toUpperCase().equals(AppConstants.SUCCESS))
//				.collect(Collectors.toList());
//		List<PaymentDto> pending=combinedTransactions.stream().filter(p->p.getStatus().trim().toUpperCase().equals(AppConstants.PENDING))
//				.collect(Collectors.toList());
//		List<PaymentDto> failure=combinedTransactions.stream().filter(p->p.getStatus().trim().toUpperCase().equals(AppConstants.FAILURE))
//				.collect(Collectors.toList());
//        
	}
}
