package model;

import java.util.LinkedList;
import java.util.Observable;

public class Game extends Observable {
	
	private char currentPlayer = 'X';
	
	private static final int[] winning_cases = new int[] {448, 56, 7, 292, 146, 73, 273, 84};
	
	private int [] remaining_moves_array = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256};
	private LinkedList<Integer> remaining_moves = new LinkedList<Integer>();
	private static final int COMPLETE = 511;
	private int player1_moves;
	private int player2_moves;
	
	private boolean finished = false;
	private char winner = '\0';

	public Game() { 
		for (int i : remaining_moves_array) {
			remaining_moves.add(i);
		}
		setChanged();
	}
	
	public boolean makeMove(int i, int j) {
		if (this.finished || j < 0 || j > 2 || i < 0 || i > 2) 
			throw new IllegalArgumentException();
		
		int index = i * 3 + j;
		int move = 1 << index;
		
		int all_moves = player1_moves | player2_moves;
		if ((all_moves | move) == all_moves)
			throw new IllegalArgumentException();
		
		if (currentPlayer == 'X') {
			player1_moves |= move;
			currentPlayer = 'O';
		}
		else if (currentPlayer == 'O') {
			player2_moves |= move;
			currentPlayer = 'X';
		}
		
		isFinished();
		setChanged();
		notifyObservers();
		return true;
	}
	
	public char getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isFinished() {
		if ((player1_moves | player2_moves) == COMPLETE)
			finished = true;
		for (int i: winning_cases) {
			if ((player1_moves & i) == i) {
				winner = 'X';
				finished = true;
			}
			else if ((player2_moves & i) == i) {
				winner = 'O';
				finished = true;
			}
		}
		return finished;
	}
	
	public char occupied(int i, int j) {
		int index = i * 3 + j;
		int moves = player1_moves >> index;
		if((moves & 1) == 1)
			return 'X';
		
		moves = player2_moves >> index;
		if((moves & 1) == 1)
			return 'O';
		
		return '\0';
	}
	
	public boolean isOccupied(int i, int j) {
		int index = i * 3 + j;
		int all_moves = this.player1_moves | this.player2_moves;
		
		all_moves >>= index;
		return (all_moves & 1) == 1;
	}
	
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
	
	public String toString() {
		String r = "";
		char[][] a = toMatrix();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a[i][j] == 'X')
					r += "X";
				else if (a[i][j] == 'O')
					r += "O";
				else
					r += "-";
			}
			r += "\n";
		}
		return r;
	}
	
	public char getWinner() {
		return winner;
	}

}
