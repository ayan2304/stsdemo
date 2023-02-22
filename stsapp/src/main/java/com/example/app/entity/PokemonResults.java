package com.example.app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResults {
	private int count;

	private String next;

	private String previous;

	private List<Pokemon> results = new ArrayList<Pokemon>();
}
