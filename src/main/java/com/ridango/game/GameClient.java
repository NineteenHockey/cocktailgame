package com.ridango.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.ridango.game.spring.jpa.h2.repository.*;
import java.util.List;
import java.util.Scanner;

@Component
public class GameClient {
	static final String apiURL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
	Scanner sc = new Scanner (System.in);
	private final RestTemplate rt; 
	private final GameDataRepository repository;
	CocktailGame game;
	
	@Autowired
	public GameClient(RestTemplate restTemplate, GameDataRepository repository ) {
		this.rt = restTemplate;
		this.repository = repository;
	}
	
	
	public Drink getNewDrink() {
		Drink ret = null;
		DrinkResponse drinkResponse = rt.getForObject(apiURL, DrinkResponse.class);
		if (drinkResponse!=null && drinkResponse.drinks()!=null) {
			List<Drink> drinks = drinkResponse.drinks();
			if(!drinks.isEmpty()) 
				ret =  drinks.get(0);
		}
		return ret;
	}
	
	public void startGame(){
		game = new CocktailGame();
		int previousHighScore = repository.getHighScore().orElse(0);
		CocktailGame.highScore = previousHighScore;
		while (!game.enough) {
			Drink d = getNewDrink();
			if (d!=null)
				game.playNewRound(d);
		}
		if (CocktailGame.highScore>previousHighScore)
			repository.setHighScore(CocktailGame.highScore);
	}

}
