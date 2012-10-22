package controller;

import view.*;
import model.*;

/**
 * This class is responsible for the coordination between views and the model.
 * In other words, the controller is responsible for the business logic of the
 * view. For instance, the view is responsible for reporting the coordinates
 * of the cell a user chooses, but the Controller is responsible for passing
 * that move onto the Game. The view, in essence, should never directly send 
 * messages to the Game.
 */
public class Controller {

	private GameView view;
	private Game game;
	private Player player1;
	private Player player2;
	
	/**
	 * Running this class uses the default MainGUI
	 */
	public static void main(String[] args) {
		new Controller(new MainGUI());
	}
	
	/**
	 * The controller should be view-implementation agnostic, so its constructor
	 * allows any such implementation.
	 * @param view
	 */
	public Controller(GameView view) {
		this.view = view;
		this.view.setGlobalListener(new GUIListener());
		((MainGUI)view).setController(this);
	}
	
	public void makeMove(Player p, int i, int j) throws IllegalArgumentException {
		game.makeMove(p, i, j);
	}
	
	public class GUIListener {
		public void playerMoved(Player p, int i, int j) {
			game.makeMove(p, i, j);
		}
		
		public void squareClicked(Player p, int i, int j) throws IllegalArgumentException {
			if(game.isOccupied(i,j))
				throw new IllegalArgumentException();
			else
				game.makeMove(p, i, j);
		}
		
		public void strategyChanged(Strategy s) {
			
		}
		
		public void playersChosen(Player p1, Player p2) {
			player1 = p1;
			player2 = p2;
			game = new Game(player1, player2);
			game.addObserver(view);
			game.start();
			((MainGUI) view).setGame(game);
		}
		
	}
	
}
