package model;

public interface Strategy {

	/**
	 * Return the next move for the given game and player.
	 * @param g the current game
	 * @param player the player who will move next
	 * @return a 2-int array corresponding to [i,j]
	 */
	public int[] getMove(Game g, char player);
	
}
