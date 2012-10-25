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

}
