package model;

public class AIPlayer implements Player {

	private Strategy strategy; 
	
	public AIPlayer(Strategy s) {
		this.strategy = s;
	}
}
