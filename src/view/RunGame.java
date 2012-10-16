package view;

import java.util.Observable;
import java.util.Observer;

import model.Game;
import model.HumanPlayer;
import model.Player;

public class RunGame implements Observer {
	
	private Game g;
	private Player player1;
	private Player player2;
	
	public static void main(String[] args) {
		RunGame rg = new RunGame();
	}
	
	public RunGame() {
		this.player1 = new HumanPlayer();
		this.player2 = new HumanPlayer();
		this.g = new Game(player1, player2);
		g.addObserver(this);
		this.update(g, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(g.toString());
		// print board
	}

}
