package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.*;

public class TestGame {
	
	@Test
	public void testConstructor() {
		Game g = new Game();
		assertFalse(g.isFinished());
		assertEquals('\0', g.getWinner());
		assertEquals('\0', g.occupiedBy(0,0));
		assertFalse(g.isOccupied(0,0));
		
		char[][] board = new char[3][3];
		char[][] gameBoard = g.toMatrix();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				assertEquals(board[i][j], gameBoard[i][j]);
			}
		}
	}
	
	@Test
	public void testAfterMoves() {
		Game g = new Game();
		g.makeMove(new int [] {0,0});
		g.makeMove(new int [] {0, 1});
		g.makeMove(new int [] {1, 0});
		g.makeMove(new int [] {1, 1});
		g.makeMove(new int [] {2, 0});
		System.out.println(g.toString());
		
		assertEquals('X', g.getWinner());
		assertTrue(g.isFinished());
		assertEquals('X', g.occupiedBy(0,0));
		assertTrue(g.isOccupied(0,0));
		assertEquals('O', g.occupiedBy(0,1));
		assertTrue(g.isOccupied(0, 1));
		
		System.out.println();
		
		g = new Game();
		g.makeMove(new int [] {0,0});
		g.makeMove(new int [] {0, 1});
		g.makeMove(new int [] {1, 0});
		g.makeMove(new int [] {1, 1});
		g.makeMove(new int [] {0, 2});
		g.makeMove(new int [] {2, 1});
		System.out.println(g.toString());
		
		assertEquals('O', g.getWinner());
		assertTrue(g.isFinished());
	}
}
