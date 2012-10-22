package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.Controller;

import model.Game;

public class RunGame implements Observer {
	
	public static void main(String[] args) {
		Controller c = new Controller(new RunGame());
	}
	
	public RunGame() {}

	@Override
	public void update(Observable o, Object m) {
		Game g = (Game) o;
		System.out.println(g.toString());
		if (g.isFinished()) {
			System.out.println("FINISHED");
		} else {
			char current_player = g.getCurrentPlayer();
			System.out.println(current_player + "'s turn");
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			int j = s.nextInt();
			try {
				g.makeMove(i, j);
			} catch(Exception e) {
				System.out.println("ILLEGAL MOVE");
				this.update(o, null);
			}
		}
		// print board
	}

}
