package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.service.impl.WeatherServiceImpl;
import com.example.app.util.WeatherConst;

@Controller
public class WeatherController {
	
	@Autowired
	WeatherServiceImpl weatServ;
	
	@GetMapping("/weather")
	public String weathershow(Model model) {
		model.addAttribute("weather",weatServ.getWeather(WeatherConst.CITY_ID, WeatherConst.API_KEY));
		return "weather";
	}
	
}
