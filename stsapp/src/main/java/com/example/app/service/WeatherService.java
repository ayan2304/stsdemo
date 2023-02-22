package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.entity.Weather;

@Service
public interface WeatherService {
	
	public Weather getWeather(String id, String api);
	
}
