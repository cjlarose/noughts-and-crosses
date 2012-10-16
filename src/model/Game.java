package model;

import java.util.Observable;

public class Game extends Observable {
	
	private Player player1;
	private Player player2;
	private final int[] winning_cases;
	private static final int COMPLETE = 511;
	private int player1_moves;
	private int player2_moves;
	private Player winner;
	
	public Game(Player player1, Player player2) {
		// encode each possible way to win as a bit array
		this.winning_cases = new int[] {448, 56, 7, 292, 146, 73, 273, 84};
	}
	
	public void makeMove(Player player, int x, int y) {
		
	}
	
	private boolean isFinished() {
		if ((player1_moves | player2_moves) == Game.COMPLETE)
			return true;
		for (int i: this.winning_cases) {
			if ((player1_moves ^ i) == i) {
				this.winner = this.player1;
				return true;
			}
			else if ((player2_moves ^ i) == i) {
				this.winner = this.player2;
				return true;
			}
		}
		return false;
	}

}