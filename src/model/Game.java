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
	
	public Game(Player player1, Player player2) {
		// encode each possible way to win as a bit array
		this.winning_cases = new int[] {448, 56, 7, 292, 146, 73, 273, 84};
		this.player1 = player1;
		this.player2 = player2;
		this.current_player = player1;
	}
	
	public void makeMove(Player player, int x, int y) {
		
	}
	
	private boolean isFinished() {
		if ((player1_moves | player2_moves) == Game.COMPLETE)
			return true;
		for (int i: this.winning_cases) {
			if ((player1_moves ^ i) == i) {
				this.winner = this.player1;
				return true;
			}
			else if ((player2_moves ^ i) == i) {
				this.winner = this.player2;
				return true;
			}
		}
		return false;
	}
	
	private Player getCurrentPlayer() {
		return this.current_player;
	}
	
	/*
	 * @return a 3x3 matrix representing all moves played so far. Contains
	 * pointers to player objects where appropriate, nulls for blank
	 * spots
	 */
	private Player[][] toMatrix() {
		int tmp1 = this.player1_moves;
		int tmp2 = this.player2_moves;
		
		Player[][] matrix = new Player[3][3];
		for (int i = 0; i < 9; i++) {
			int y = 3 - i / 3;
			int x = 3 - i % 3;
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
		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 3; m++) {
				if (a[m][n] == this.player1)
					r += "X";
				else if (a[m][n] == this.player2)
					r += "O";
				else
					r += "-";
			}
			r += "\n";
		}
		return r;
	}

}