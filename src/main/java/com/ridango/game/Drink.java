package com.ridango.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Drink(int idDrink, String strDrink, String strCategory, String strAlcoholic,
		String strGlass, String strInstructions, String strDrinkThumb) {
}
