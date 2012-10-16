package model;

public class AIPlayer implements Player {

	private Strategy strategy; 
	
	public AIPlayer(Strategy init_strategy) {
		this.strategy = init_strategy;
	}
	
	public void setStrategy(Strategy s) {
		this.strategy = s;
	}
	
	/*
	 * @return (i, j) coordinates of chosen move
	 */
	public int[] getMove(Game g) {
		return this.strategy.getMove(g, this);
	}
	
}
