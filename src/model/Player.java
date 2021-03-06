package model;

public class Player {

	private Strategy strategy;

	public Player(Strategy init_strategy) {
		this.strategy = init_strategy;
	}

	/**
	 * Change the strategy that this player employs
	 * 
	 * @param s
	 *            is an instance of a class implementing Strategy
	 */
	public void setStrategy(Strategy s) {
		this.strategy = s;
	}

	/**
	 * Given the context of a game, use the current strategy to determine the
	 * next move
	 * 
	 * @param g
	 *            the game to respond to
	 * @return (i, j) coordinates of chosen move
	 */
	public int[] getMove(Game g) {
		return strategy.getMove(g, g.getCurrentPlayer());
	}

}
