package model;

public class BeginnerStrategy implements Strategy {

	@Override
	/**
	 * The beginner strategy is just to choose the next unoccupied space from the top left.
	 * @return a 2-int array [i,j] with the location of the move
	 */
	public int[] getMove(Game g, Player p) {
		for (int i = 0; i < 9; i++) {
			if (!g.isOccupied(i / 3, i % 3))
				return new int[] { i / 3, i % 3 };
		}

		return new int[] { 0, 0 };

	}
}
