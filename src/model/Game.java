package model;

import java.util.Observable;

public class Game extends Observable {
	
	private Player player1;
	private Player player2;
	
	public Game(Player player1, Player player2) {
		
	}
	
	public void markPlace(Player player, int x, int y) {
		
	}
	
	private boolean isFinished() {
		return false;
	}

}