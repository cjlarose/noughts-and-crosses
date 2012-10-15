package model;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

	private List<Game> games;
	private int[] scores; // three tuple container the number of wins
	private Player player1;
	private Player player2;
	
	public Tournament(Player player1, Player player2) {
		this.games = new ArrayList<Game>();
	}
	
	/*
	 * @param Player A player object.  If null, give number of ties
	 * @return int the number of wins for that player
	 */
	public int getWins(Player player) {
		return 0;
	}
	
	public Game playGame() {
		Game g = new Game(this.player1, this.player2);
		this.games.add(g);
		return g;
	}
}
