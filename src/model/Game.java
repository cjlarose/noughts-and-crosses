package model;

import java.util.Observable;

public class Game extends Observable {
	
	private Player player1;
	private Player player2;
	private final int[] winning_cases;
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
		
	}
	
	public void makeMove(Player player, int i, int j) {
		if (this.finished || j < 0 || j > 2 || i < 0 || i > 2)
			throw new IllegalArgumentException();
		
		int index = i * 3 + j;
		int move = 1 << index;
		
		int all_moves = player1_moves | player2_moves;
		if ((all_moves ^ move) == 0)
			throw new IllegalArgumentException();
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
	
	public boolean isFinished() {
		return this.finished;
	}
	
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
	
	public Player getCurrentPlayer() {
		return this.current_player;
	}
	
	public boolean isOccupied(int i, int j) {
		int index = i * 3 + j;
		int all_moves = this.player1_moves | this.player2_moves;
		
		all_moves >>= index;
		return (all_moves & 1) == 1;
	}
	
	public int getPlayer1Moves() {
		return this.player1_moves;
	}
	
	public int getPlayer2Moves() {
		return this.player2_moves;
	}
	
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
		for (int i = 0; i < 9; i++) {
			int y = i / 3;
			int x = i % 3;
			if ((tmp1 & 1) == 1)
				matrix[y][x] = this.player1;
			else if ((tmp2 & 1) == 1)
				matrix[y][x] = this.player2;
			tmp1 >>= 1;
			tmp2 >>= 1;
		}
		
		return matrix;
	}
	
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