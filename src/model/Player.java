package model;

public interface Player {

	/**
	 * Change the strategy that this player employs
	 * 
	 * @param s
	 *            is an instance of a class implementing Strategy
	 */
	public void setStrategy(Strategy s);

	/**
	 * Given the context of a game, use the current strategy to determine the
	 * next move
	 * 
	 * @param g
	 *            the game to respond to
	 * @return (i, j) coordinates of chosen move
	 */
	public int[] getMove(Game g);

}
