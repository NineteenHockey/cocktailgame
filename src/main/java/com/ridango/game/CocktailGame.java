package com.ridango.game;

import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;


public class CocktailGame {
	static final int MAX_ATTEMPTS = 5;
	static int highScore;
	static Scanner scanner = new Scanner(System.in);
	static Random rand = new Random();
	int attempts;
	int score;
	private byte hintLevel; //Which hints to show?
	boolean enough = false; //Do you want to play another game?
	Drink drink=null;
	HashSet<Integer> usedDrinks=new HashSet<Integer>();
	ArrayList<Integer> hiddenLetters = new ArrayList<Integer>(); //Numbers of letters that are hidden in
																	//the drink's name
	
	CocktailGame(){
		attempts = MAX_ATTEMPTS;
		score = 0;
	}
	
	void resetGame(){
		attempts = MAX_ATTEMPTS;
		score = 0;
		usedDrinks.clear();
		hiddenLetters.clear();
	}
	
	void playNewRound(Drink d){ //Stage of the game from showing new drink until player guesses it or loses
								//all his attempts
		String answer;
		int drinkNameLength;
		if (!usedDrinks.contains(d.idDrink())) {
			usedDrinks.add(d.idDrink());
			drink = d;
			drinkNameLength = d.strDrink().length();
			hintLevel=0;
			for (int i = 0; i<drinkNameLength; i++)
				if (d.strDrink().charAt(i)!=' ')
				hiddenLetters.add(i);
			
			boolean answerOK = false;
			while(!answerOK && attempts>0) {
				printStatus();
				answer = scanner.nextLine();
				if (d.strDrink().toUpperCase().equals(answer.toUpperCase())) {
					answerOK=true;
					score += attempts;
					hintLevel = 0;
					System.out.println("!!!!CONGRATULATIONS, YOU HAVE GOT IT RIGHT!!!!!");
				} else {
					attempts--;
					if (attempts>0) {
						hintLevel++;
						revealLetters(revealedLettersNumber(drinkNameLength));
					}
				} if (attempts==0) {
					System.out.println("~~~~SORRY, YOU HAVE NO MORE ATTEMPTS LEFT~~~~");
					System.out.println("~~~~THE NAME OF THE COCKTAIL WAS: "+ d.strDrink()+"~~~~");
					System.out.println("~~~~YOU HAVE EARNED "+score+" POINTS~~~~");
					if (score>highScore) {
						highScore = score;
						System.out.println("~~~~THIS IS THE NEW RECORD!~~~~~~");
						}
					System.out.println("?????WOULD YOU LIKE TO PLAY AGAIN? TYPE 'N' IF YOU WANT TO LEAVE???");
					answer = scanner.nextLine();
					if (answer.toUpperCase().startsWith("N"))
						enough = true;
				}
				
			}
		}
		if (!enough && attempts == 0)
			resetGame();
	}
	
	void revealLetters(int number) { //Removing some letters from hiddenLetters list
		int len = hiddenLetters.size();
		for (int i = 0; i<number; i++) {
			int ind = rand.nextInt(len);
			hiddenLetters.remove(ind);
			len--;
		}
	}
	
	int revealedLettersNumber(int length) { //Function counting how much letters to reveal after each guess
		int ret = length / MAX_ATTEMPTS;
		int mod = length % MAX_ATTEMPTS;
		if ((mod*2)>=MAX_ATTEMPTS)
			ret++;
		return ret;
	}
	
	
	String drinkNameToGuess() { //Creating a string with partly hidden drink name to show in console.
		char space = '|';
		String drinkName = drink.strDrink();
		StringBuffer b = new StringBuffer();
		for (int i = 0; i<drinkName.length(); i++) {
			char sym = drinkName.charAt(i);
			if (sym == ' ')
				b.append(space);
			else if (!hiddenLetters.contains(i))
				b.append(drink.strDrink().charAt(i)+" ");
			else
				b.append(""
						+ "_ ");
		}
		return b.toString();
	}
	
	//start round with new Drink
	void printStatus() {//Printing all the necessary information to player after each guess
		if (usedDrinks.size()==1 && attempts == MAX_ATTEMPTS)
			System.out.println("+++++++++++++++THE NEW GAME BEGINS+++++++++");
		System.out.println("===================YOU HAVE "+attempts+" ATTEMPTS LEFT! ==========");
		System.out.println(drinkNameToGuess());
		showHints();
		System.out.print("****High Score: "+ highScore);
		System.out.println(" ||| Your Score: " + score+"*****");
	}
	
	void showHints() {//Adding hints about the cocktail depending on hint level
		//Alcoholic
		//Category
		//Glass
		//Picture
		System.out.println("Description: "+drink.strInstructions());
		switch(hintLevel) {
		case 4:
			System.out.println("Look here!: " + drink.strDrinkThumb());
		case 3:
			System.out.println("Category: " + drink.strCategory());
		case 2:
			System.out.println("Glass: " + drink.strGlass());
		case 1:
			System.out.println("Alcoholic: "+drink.strAlcoholic());
			
			
		}
	}
	
}
