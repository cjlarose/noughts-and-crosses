package model;

import java.util.LinkedList;
import java.util.Random;

public class IntermediateStrategy implements Strategy {

	private int[] almost_wins = { 384, 192, 320, 288, 260, 36, 48, 40, 24, 144,
			130, 18, 6, 5, 3, 72, 65, 9, 272, 257, 17, 80, 68, 20 };
	private int[] wins = { 448, 56, 7, 292, 146, 73, 273, 84 };

	@Override
	public int[] getMove(Game g, Player p) {
		int computer_moves = 0;
		int player_moves = 0;
		int temp_one = 1;
		Player[][] board = g.toMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int index = i * 3 + j;
				if (board[i][j] != null) {
					if (board[i][j].equals(p))
						computer_moves |= temp_one << index;
					else
						player_moves |= temp_one << index;
				}
			}
		}
		// gar. This be a pain in the ass.
		for (int i : almost_wins) {
			if ((computer_moves & i) == i) {
				for (int j : wins) {
					if (((j | i) & j) == j) {
						int loc = j ^ i;
						int index = 0;
						while ((loc >>= 1) != 0)
							index++;
						if (!g.isOccupied(index / 3, index % 3))
							return new int[] { index / 3, index % 3 };
					}
				}
			}

			if ((player_moves & i) == i) {
				for (int j : wins) {
					if (((j | i) & j) == j) {
						int loc = j ^ i;
						int index = 0;
						while ((loc >>= 1) != 0)
							index++;
						if (!g.isOccupied(index / 3, index % 3))
							return new int[] { index / 3, index % 3 };
					}
				}
			}
		}
		LinkedList<Integer> remaining_moves = g.getRemainingMoves();
		Random rand = new Random();
		int loc;
		int index;
		do {
			int i = rand.nextInt(remaining_moves.size());
			loc = remaining_moves.get(i);
			index = 0;
			while ((loc >>= 1) != 0)
				index++;
		} while (g.isOccupied(index / 3, index % 3));
		return new int[] { index / 3, index % 3 };
	}

}
