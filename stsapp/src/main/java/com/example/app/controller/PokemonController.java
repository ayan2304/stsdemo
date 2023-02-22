package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.app.entity.Pokemon;
import com.example.app.entity.PokemonResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/form")

public class PokemonController {

	@GetMapping("/pokeapi/download")
	@ResponseBody
	public List<Pokemon> enviarPokemons(Model model) {

		RestTemplate restTemplate = new RestTemplate();

		String url = "https://pokeapi.co/api/v2/pokemon?limit=50";

		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// JSON
		String pokemonsJSON = response.getBody();

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		// Convert to POJO
		PokemonResults results = gson.fromJson(pokemonsJSON, PokemonResults.class);

		List<Pokemon> pokemons = new ArrayList<>();

		// Lista de pokemons
		for (int i = 0; i < results.getResults().size(); i++) {
			Pokemon pokemonNew = new Pokemon();
			pokemonNew.setId(i + 1);
			pokemonNew.setName(results.getResults().get(i).getName());
			pokemonNew.setUrl(results.getResults().get(i).getUrl());
			pokemons.add(pokemonNew);
		}

		return pokemons;
	}

	@GetMapping("/pokeapi")
	public String showPokemons(Model model) {

		return "pokeAPI";
	}

}
