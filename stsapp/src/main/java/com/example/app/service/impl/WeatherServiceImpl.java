package com.example.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.app.entity.Weather;
import com.example.app.entity.WeatherMain;
import com.example.app.entity.WeatherSys;
import com.example.app.service.WeatherService;
import com.example.app.util.WeatherConst;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class WeatherServiceImpl implements WeatherService {

	
	
	@Override
	public Weather getWeather(String id, String api) {
		
		 Logger logger = LoggerFactory.getLogger(Controller.class);
		 
		// URL to open weather api
		String webRequest = WeatherConst.URL + 
							"?id=" + id + 
							"&appid=" + api;
		
		logger.debug(webRequest);
		
		// Rest Template to get Object
		RestTemplate restTemplate = new RestTemplate();
		// Get JSON response from webRequest
		String response = restTemplate.getForObject(webRequest, String.class);

		// Create JSON object and Array
		JsonObject data = new Gson().fromJson(response, JsonObject.class);
		JsonArray weatherArray = data.get("weather").getAsJsonArray();
		
		// Create classes for weather, weatherMain, weatherSys
		Weather weatherPojo = new Weather();
		WeatherMain weatherPojoMain = new WeatherMain();
		WeatherSys weatherPojoSys = new WeatherSys();
		
		// Set everything to a class
		weatherPojo.setMain(weatherPojoMain);
		weatherPojo.setSys(weatherPojoSys);
		
		// Search in JSON array the elements "main", "description", "icon"
		for(JsonElement element : weatherArray){
	        JsonObject weatherObj = element.getAsJsonObject();

	        weatherPojo.setDescription(weatherObj.get("description").getAsString());
	        weatherPojo.setIcon(weatherObj.get("icon").getAsString());
	    }

		// Get weather Main in JSON and set to weatherPojoMain
		JsonObject weatherJsonMain = data.get("main").getAsJsonObject();
		weatherPojoMain.setTemp(weatherJsonMain.get("temp").getAsDouble());
		weatherPojoMain.setTemp(convertToCelsius(weatherPojoMain.getTemp()));
		// Max temp
		weatherPojoMain.setTemp_max(weatherJsonMain.get("temp_max").getAsDouble());
		weatherPojoMain.setTemp_max(convertToCelsius(weatherPojoMain.getTemp_max()));
		// Min temp
		weatherPojoMain.setTemp_min(weatherJsonMain.get("temp_min").getAsDouble());
		weatherPojoMain.setTemp_min(convertToCelsius(weatherPojoMain.getTemp_min()));
		
		weatherPojoMain.setPressure(weatherJsonMain.get("pressure").getAsInt());
		weatherPojoMain.setHumidity(weatherJsonMain.get("humidity").getAsInt());
		
		// Get time of sunset and sunrise
		JsonObject weatherJsonSys = data.get("sys").getAsJsonObject();
		weatherPojoSys.setSunrise(weatherJsonSys.get("sunrise").getAsString());
		weatherPojoSys.setSunrise(convertToTime(weatherPojoSys.getSunrise()));
		
		weatherPojoSys.setSunset(weatherJsonSys.get("sunset").getAsString());
		weatherPojoSys.setSunset(convertToTime(weatherPojoSys.getSunset()));
		
		return weatherPojo;

	}
	
	public double convertToCelsius(double temp_K) {
		return Math.round((temp_K - 273.15F ) * 100.0) / 100.0;
	}

	public String convertToTime(String time_unix) {
		long time = Long.parseLong(time_unix);
		Date date = new Date(time * 1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		String date_final = sdf.format(date);
		
		return date_final;
	}
}
