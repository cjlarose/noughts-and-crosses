package view;

import java.util.Observable;
import java.util.Scanner;

import controller.Controller;
import controller.Controller.GUIListener;

import model.Game;
import model.HumanPlayer;
import model.Player;

public class RunGame implements GameView {
	
	private Player player1;
	private Player player2;
	private GUIListener listener;
	
	public static void main(String[] args) {
		Controller c = new Controller(new RunGame());
		
	}
	
	@Override
	public void setGlobalListener(GUIListener l) {
		this.listener = l;
		
		this.player1 = new HumanPlayer();
		this.player2 = new HumanPlayer();
		this.listener.playersChosen(this.player1, this.player2);
	}

	@Override
	public void update(Observable o, Object m) {
		Game g = (Game) o;
		Game.GameMove move = (Game.GameMove) m;
		System.out.println(g.toString());
		if (g.isFinished()) {
			System.out.println("FINISHED");
		} else {
			Player current_player = g.getCurrentPlayer();
			System.out.println(String.format("%c's turn", current_player == player1 ? 'X' : 'O'));
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			int j = s.nextInt();
			try {
				// TODO: Remove call to make move
				g.makeMove(current_player, i, j);
			} catch(Exception e) {
				System.out.println("ILLEGAL MOVE, MUTHAFUKKA");
				this.update(o, null);
			}
		}
		// print board
	}

}
