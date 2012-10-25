package model;

import java.util.LinkedList;
import java.util.Random;

public class IntermediateStrategy implements Strategy {

	private final int[] almost_wins = { 384, 192, 320, 288, 260, 36, 48, 40,
			24, 144, 130, 18, 6, 5, 3, 72, 65, 9, 272, 257, 17, 80, 68, 20 };
	private final int[] wins = { 448, 56, 7, 292, 146, 73, 273, 84 };
	private final LinkedList<Integer> single_moves;
	
	/**
	 * An intermediate strategy that will block an opponent from winning and
	 * will attempt to win itself.
	 */
	public IntermediateStrategy() {
		single_moves = new LinkedList<Integer>();
		for (int i = 0; i < 9; i++) {
			single_moves.add((int) Math.pow(2, i));
		}
	}

	/**
	 * @param g
	 *            is the game to which the move will be added
	 * @param p
	 *            The current player - the character that is going to move next.
	 */
	@Override
	public int[] getMove(Game g, char player) {
		int computer_moves = 0;
		int player_moves = 0;
		int temp_one = 1;
		//Building the lists of computer and player moves, as the original method
		//cannot differentiate
		char[][] board = g.toMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int index = i * 3 + j;
				if (board[i][j] != '\0') {
					if (board[i][j] == player)
						computer_moves |= temp_one << index;
					else
						player_moves |= temp_one << index;
				}
			}
		}
		//Checks for an almost win situation, and responds accordingly.
		//First: if the almost win situation is in the favor of the computer,
		//this will return a move that will win the game. 
		for (int i : almost_wins) {
			if ((computer_moves & i) == i) {
				for (int j : wins) {
					int loc = j ^ i;
					if (single_moves.contains(loc)) {
						 int index = 0;
						 while (loc != 1) {
							 loc >>= 1;
							index++;
						 }
						 if (!g.isOccupied(index / 3, index % 3)) {
							 // System.out.println(index);
							 return new int[] { index / 3, index % 3 };
						 }
					}
				}
			}
			//Second: if the almost win situation is in favor of the other player,
			//this will return a move that will block the win (blocks only the first occurrence of
			//one)
			if ((player_moves & i) == i) {
				for (int j : wins) {
					int loc = j ^ i;
					if (single_moves.contains(loc)) {
						 int index = 0;
						 while (loc != 1) {
							 loc >>= 1;
							index++;
						 }
						 if (!g.isOccupied(index / 3, index % 3)) {
							 // System.out.println(index);
							 return new int[] { index / 3, index % 3 };
						 }
					}
				}
			}
		}
		
		//Finally: if there is no almost win situation, returns a random valid move. 
		Random r = new Random();
		int i = r.nextInt(9);
		while (g.isOccupied(i / 3, i % 3))
			i = (i + 1) % 9;
		return new int[] {i / 3, i % 3};

	}

}
