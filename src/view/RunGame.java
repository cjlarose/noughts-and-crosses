package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import model.Game;
import model.HumanPlayer;
import model.Player;

public class RunGame implements Observer {
	
	private Game game;
	private Player player1;
	private Player player2;
	
	public static void main(String[] args) {
		RunGame rg = new RunGame();
	}
	
	public RunGame() {
		this.player1 = new HumanPlayer();
		this.player2 = new HumanPlayer();
		this.game = new Game(player1, player2);
		this.game.addObserver(this);
		this.update(this.game, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(this.game.toString());
		if (this.game.isFinished()) {
			System.out.println("FINISHED");
		} else {
			Player current_player = this.game.getCurrentPlayer();
			System.out.println(String.format("%s's turn", current_player == player1 ? "X" : "O"));
			Scanner s = new Scanner(System.in);
			int n = s.nextInt();
			int m = s.nextInt();
			this.game.makeMove(current_player, n, m);
		}
		// print board
	}

}
