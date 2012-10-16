package model;

import java.awt.event.ActionListener;

public class AIPlayer implements Player {

	private Strategy strategy; 
	
	public AIPlayer(Strategy init_strategy) {
		this.strategy = init_strategy;
	}
	
	public void setStrategy(Strategy s) {
		this.strategy = s;
	}
	
	/*
	 * @return (x,y) coordinates of chosen move
	 */
	public int[] getMove(Game g) {
		return new int[] {0, 0};
	}
	
}
