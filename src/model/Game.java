package model;

import java.util.LinkedList;
import java.util.Observable;

public class Game extends Observable {
	
	private Player player1;
	private Player player2;
	private final int[] winning_cases;
	private int [] remaining_moves_array;
	private LinkedList<Integer> remaining_moves;
	private static final int COMPLETE = 511;
	private int player1_moves;
	private int player2_moves;
	private Player winner;
	private Player current_player;
	private boolean finished = false;
	
	public Game(Player player1, Player player2) {
		// encode each possible way to win as a bit array
		this.winning_cases = new int[] {448, 56, 7, 292, 146, 73, 273, 84};
		this.player1 = player1;
		this.player2 = player2;
		this.current_player = player1;
		this.remaining_moves = new LinkedList<Integer>();
		this.remaining_moves_array = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256};
		
		for (int i : remaining_moves_array) {
			remaining_moves.add(i);
		}
	}
	
	public void start() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Mark a move by a player on the game board
	 * @param player is the player who made the move. Precondition: that player
	 * is either player1 or player2 that the game was initialized with
	 * @param i the row coordinate to mark
	 * @param j the column coordinate to mark
	 */
	public void makeMove(Player player, int i, int j) {
		if (this.finished || j < 0 || j > 2 || i < 0 || i > 2) {
			System.out.println(i + " " + j);
			throw new IllegalArgumentException();
		}
		
		int index = i * 3 + j;
		int move = 1 << index;
		
		int all_moves = player1_moves | player2_moves;
		if ((all_moves ^ move) == 0)
			throw new IllegalArgumentException();
		remaining_moves.remove((Integer)move);
		if (player == player1) {
			player1_moves |= move;
			this.current_player = this.player2;
		}
		else if (player == player2) {
			player2_moves |= move;
			this.current_player = this.player1;
		}
		
		this.checkFinished();
		this.setChanged();
		this.notifyObservers(null);
	}
	
	public LinkedList<Integer> getRemainingMoves() {
		return remaining_moves;
	}

	/**
	 * Returns whether or not the game is finished
	 * @return boolean: true if game is finished, false otherwise
	 */

	public boolean isFinished() {
		return this.finished;
	}
	
    /**
     * Check if the game is finished, change this.finished appropriately
     */
	public void checkFinished() {
		if ((player1_moves | player2_moves) == Game.COMPLETE)
			this.finished = true;
		for (int i: this.winning_cases) {
			if ((player1_moves & i) == i) {
				this.winner = this.player1;
				this.finished = true;
			}
			else if ((player2_moves & i) == i) {
				this.winner = this.player2;
				this.finished = true;
			}
		}
	}
	
    /**
     * Returns the player who's turn it is
     * @return Player the current player
     */
	public Player getCurrentPlayer() {
		return this.current_player;
	}
	
    /**
     * Checks if an (i, j)-entry is occupied by a move
     * @param i row coordinate, 0 <= i <= 2
     * @param j column coordinate, 0 <= j <= 2
     * @return true if the space is occupied, false otherwise
     */
	public boolean isOccupied(int i, int j) {
		int index = i * 3 + j;
		int all_moves = this.player1_moves | this.player2_moves;
		
		all_moves >>= index;
		return (all_moves & 1) == 1;
	}
	
    /**
     * Get the winner of this game (assuming the game is finished)
     * @return the winner of the game, null if tied
     */
	public Player getWinner() {
		return this.winner;
	}
	
	/**
	 * @return a 3x3 matrix representing all moves played so far. Contains
	 * pointers to player objects where appropriate, nulls for blank
	 * spots
	 */
	public Player[][] toMatrix() {
		int tmp1 = this.player1_moves;
		int tmp2 = this.player2_moves;
		
		Player[][] matrix = new Player[3][3];
		for (int n = 0; n < 9; n++) {
			int i = n / 3;
			int j = n % 3;
			if ((tmp1 & 1) == 1)
				matrix[i][j] = this.player1;
			else if ((tmp2 & 1) == 1)
				matrix[i][j] = this.player2;
			tmp1 >>= 1;
			tmp2 >>= 1;
		}
		
		return matrix;
	}
	
    /**
     * Give a string representation of the board
     * @return the board as a string of X's and O's
     */
	public String toString() {
		String r = "";
		Player[][] a = this.toMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a[i][j] == this.player1)
					r += "X";
				else if (a[i][j] == this.player2)
					r += "O";
				else
					r += "-";
			}
			r += "\n";
		}
		return r;
	}

}
