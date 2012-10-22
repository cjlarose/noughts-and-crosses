package model;

import java.util.Random;

public class BeginnerStrategy implements Strategy {

	/**
	 * The beginner strategy is to pick a random square and then move right
	 * until an unoccupied square is found.
	 * 
	 * @return a 2-int array [i,j] with the location of the move
	 */
	@Override
	public int[] getMove(Game g, Player p) {
		Random r = new Random();
		int i = r.nextInt(9);
		while (g.isOccupied(i / 3, i % 3))
			i = (i + 1) % 9;
		return new int[] {i / 3, i % 3};
	}
}
