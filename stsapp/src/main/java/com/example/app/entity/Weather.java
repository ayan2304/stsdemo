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
@JsonPropertyOrder({ "id", "main", "description", "icon" })

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Weather {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("main")
	private WeatherMain main;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("icon")
	private String icon;
	
	@JsonProperty("sys")
	private WeatherSys sys;
	
}
