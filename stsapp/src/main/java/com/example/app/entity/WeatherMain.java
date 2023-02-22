package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"temp",
"temp_min",
"temp_max",
"pressure",
"humidity"
})

@Data
@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMain {

	@JsonProperty("temp")
	private double temp;
	
	@JsonProperty("temp_min")
	private double temp_min;

	@JsonProperty("temp_max")
	private double temp_max;
	
	@JsonProperty("pressure")
	private int pressure;
	
	@JsonProperty("humidity")
	private int humidity;
	
}
