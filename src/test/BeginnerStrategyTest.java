package test;

import static org.junit.Assert.*;
import model.BeginnerStrategy;
import model.Game;

import org.junit.Test;

public class BeginnerStrategyTest {

	@Test
	public void testGetMove() {
		Game g = new Game();
		BeginnerStrategy b = new BeginnerStrategy();
		int[] move = b.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);

		// Move in every space except 2,2
		g.makeMove(0, 0);
		g.makeMove(0, 1);
		g.makeMove(0, 2);
		g.makeMove(1, 1);
		g.makeMove(1, 0);
		g.makeMove(1, 2);
		g.makeMove(2, 1);
		g.makeMove(2, 0);

		// This should get coverage of the while loop with probability 8/9
		move = b.getMove(g, 'O');
		assertTrue(move[0] == 2 && move[1] == 2);
	}

}
