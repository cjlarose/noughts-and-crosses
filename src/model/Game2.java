package model;

import java.util.LinkedList;
import java.util.Observable;

public class Game2 extends Observable {
	
	private char currentPlayer = 'X';
	
	private static final int[] winning_cases = new int[] {448, 56, 7, 292, 146, 73, 273, 84};
	
	private int [] remaining_moves_array = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256};
	private LinkedList<Integer> remaining_moves = new LinkedList<Integer>();
	private static final int COMPLETE = 511;
	private int player1_moves;
	private int player2_moves;
	
	private boolean finished = false;
	private char winner;

	public Game2() { 
		for (int i : remaining_moves_array) {
			remaining_moves.add(i);
		}
	}
	
	public boolean makeMove(int i, int j) {
		if (this.finished || j < 0 || j > 2 || i < 0 || i > 2) 
			return false;
		
		int index = i * 3 + j;
		int move = 1 << index;
		
		int all_moves = player1_moves | player2_moves;
		if ((all_moves | move) == all_moves)
			return false;
		
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
	
	public char isOccupied(int i, int j) {
		
		return '\0';
	}
	
	public char getWinner() {
		return winner;
	}

}
