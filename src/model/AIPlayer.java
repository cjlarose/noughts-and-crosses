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
	
}
