package test;

import static org.junit.Assert.*;
import model.Game;

import org.junit.Test;

public class GameTest {

	@Test
	public void testGetCurrentPlayer() {
		Game g = new Game();
		g.makeMove(new int[] { 0, 0 });

		assertEquals(g.getCurrentPlayer(), 'O');
		g.makeMove(1, 1);
		assertEquals(g.getCurrentPlayer(), 'X');
	}

	@Test
	public void testIsFinishedAndGetWinner() {
		Game g = new Game();

		g.makeMove(0, 0);
		g.makeMove(0, 1);
		g.makeMove(0, 2);
		g.makeMove(1, 1);
		g.makeMove(1, 0);
		g.makeMove(1, 2);
		g.makeMove(2, 1);
		g.makeMove(2, 0);
		g.makeMove(2, 2);
		assertTrue(g.isFinished());
		assertEquals(g.getWinner(), '\0');

		g = new Game();
		g.makeMove(0, 0);
		g.makeMove(1, 0);
		g.makeMove(0, 1);
		g.makeMove(2, 0);
		g.makeMove(0, 2);
		assertTrue(g.isFinished());
		assertEquals(g.getWinner(), 'X');
		
		g = new Game();
		g.makeMove(0, 0);
		g.makeMove(1, 0);
		g.makeMove(0, 1);
		g.makeMove(1, 1);
		g.makeMove(2, 0);
		g.makeMove(1, 2);
		assertTrue(g.isFinished());
		assertEquals(g.getWinner(), 'O');
	}

	@Test
	public void testOccupied() {
		Game g = new Game();
		g.makeMove(0, 0);
		assertTrue(g.isOccupied(0, 0));
		assertFalse(g.isOccupied(1, 1));
		assertEquals(g.occupiedBy(0, 0), 'X');
		g.makeMove(1, 1);
		assertEquals(g.occupiedBy(1, 1), 'O');
		assertEquals(g.occupiedBy(2, 2), '\0');
	}
	
	@Test
	public void testToMatrix() {
		Game g = new Game();
		char[][] m = g.toMatrix();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				assertEquals(m[i][j], '\0');
		g.makeMove(0, 0);
		g.makeMove(1, 1);
		m = g.toMatrix();
		assertEquals(m[0][0], 'X');
		assertEquals(m[1][1], 'O');
	}
	
	@Test
	public void testToString() {
		Game g = new Game();
		String s = g.toString();
		String s2 = "---\n---\n---";
		assertEquals(s, s2);
		g.makeMove(0, 0);
		g.makeMove(1, 1);
		s = g.toString();
		s2 = "X--\n-O-\n---";
		assertEquals(s, s2);
	}
}
