package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private MainGUI view;
	private Game game;
	
	public static void main(String[] args) {
		// this will be the _only_ main method of the project
		new Controller();
	}
	
	public Controller() {
		this.view.addStrategyChangedListener(new strategyChangedListener());
		//this.game = new Game();
	}
	
	public class strategyChangedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evt) {
			Strategy s = view.getSelectedStrategy();
			// set the ai's player's strategy to s
		}
		
	}
}