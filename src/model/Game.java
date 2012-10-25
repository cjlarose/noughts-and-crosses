package model;

import java.util.Observable;

public class Game extends Observable {

	private char currentPlayer = 'X';

	private static final int[] winning_cases = new int[] { 448, 56, 7, 292,
			146, 73, 273, 84 };
	private static final int COMPLETE = 511;
	private int player1_moves;
	private int player2_moves;

	private boolean finished = false;
	private char winner = '\0';

	/**
	 * A Game is a playable game of Noughts & Crosses, AKA Tic-Tac-Toe.
	 * Internally, the game is represented as two bit vectors, since my
	 * teammates are masochists. Using bit logic, we can decide which numbers
	 * represent winning games, complete games, and individual player's moves.
	 */
	public Game() {
		setChanged();
		// This will NOT call any Observer's update methods, since you can't add
		// an Observer until you have initialized the game. Call
		// Game.notifyObservers() from wherever you instantiate it
	}

	/**
	 * Move at the specified spot.
	 * 
	 * @throws IllegalArgumentException
	 *             if the game is finished, the spot is out of bounds, or the
	 *             spot is occupied
	 * @param i
	 *            the row coordinate
	 * @param j
	 *            the column coordinate
	 */
	public void makeMove(int i, int j) {
		if (this.finished || j < 0 || j > 2 || i < 0 || i > 2)
			throw new IllegalArgumentException();

		int index = i * 3 + j;
		int move = 1 << index;

		int all_moves = player1_moves | player2_moves;
		if ((all_moves | move) == all_moves) {
			System.out.println("Illegal Move: " + i + " " + j);
			System.out.println(this.toString());
			throw new IllegalArgumentException();
		}

		if (currentPlayer == 'X') {
			player1_moves |= move;
			currentPlayer = 'O';
		} else if (currentPlayer == 'O') {
			player2_moves |= move;
			currentPlayer = 'X';
		}

		String result = "";
		if (isFinished())
			result = winner + " wins";
		setChanged();
		notifyObservers(result);
	}

	/**
	 * Move at the specified spot.
	 * 
	 * @throws IllegalArgumentException
	 *             if the game is finished, the spot is out of bounds, or the
	 *             spot is occupied
	 * @param move
	 *            a 2-int array corresponding to [i,j]
	 */
	public void makeMove(int[] move) {
		makeMove(move[0], move[1]);
	}

	/**
	 * Get the char of the player who will be moving next.
	 * 
	 * @return 'X', 'O', or '\0' if the game is finished
	 */
	public char getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Check if the game is complete.
	 * 
	 * @return true if the game is finished, false otherwise
	 */
	public boolean isFinished() {
		if ((player1_moves | player2_moves) == COMPLETE) {
			finished = true;
			currentPlayer = '\0';
		}
		for (int i : winning_cases) {
			if ((player1_moves & i) == i) {
				winner = 'X';
				finished = true;
			} else if ((player2_moves & i) == i) {
				winner = 'O';
				finished = true;
			}
		}
		return finished;
	}

	/**
	 * Returns the player who occupies a space
	 * 
	 * @param i
	 *            the row coordinate
	 * @param j
	 *            the column coordinate
	 * @return the character that occupies the given spot
	 */
	public char occupiedBy(int i, int j) {
		int index = i * 3 + j;
		int moves = player1_moves >> index;
		if ((moves & 1) == 1)
			return 'X';

		moves = player2_moves >> index;
		if ((moves & 1) == 1)
			return 'O';

		return '\0';
	}

	/**
	 * Tests to see if a space is occupied
	 * 
	 * @param i
	 *            the row coordinate
	 * @param j
	 *            the column coordinate
	 * @return true if the given spot is occupied, false otherwise
	 */
	public boolean isOccupied(int i, int j) {
		int index = i * 3 + j;
		int all_moves = this.player1_moves | this.player2_moves;

		all_moves >>= index;
		return (all_moves & 1) == 1;
	}

	/**
	 * Get a 2D character matrix of the current game
	 * 
	 * @return a 2D char matrix of 'X', 'O', and '\0'
	 */
	public char[][] toMatrix() {
		int tmp1 = this.player1_moves;
		int tmp2 = this.player2_moves;

		char[][] matrix = new char[3][3];
		for (int n = 0; n < 9; n++) {
			int i = n / 3;
			int j = n % 3;
			if ((tmp1 & 1) == 1)
				matrix[i][j] = 'X';
			else if ((tmp2 & 1) == 1)
				matrix[i][j] = 'O';
			tmp1 >>= 1;
			tmp2 >>= 1;
		}

		return matrix;
	}

	/**
	 * A 3-line string representation of the current game.
	 * 
	 * @return a String consisting of 'X', 'O', and '-' with newlines every 3
	 *         chars
	 */
	public String toString() {
		String r = "";
		char[][] a = toMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a[i][j] == '\0')
					r += "-";
				else
					r += a[i][j];
			}
			r += "\n";
		}
		return r.substring(0, r.length() - 1);
	}

	/**
	 * Return the character that has won the game.
	 * 
	 * @return 'X', 'O', or '\0' if there is no winner
	 */
	public char getWinner() {
		return winner;
	}

}
