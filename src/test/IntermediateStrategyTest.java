package test;

import static org.junit.Assert.assertTrue;
import model.Game;
import model.IntermediateStrategy;

import org.junit.Test;

public class IntermediateStrategyTest {

	@Test
	public void testIntermediateStrategy() {
		Game g = new Game();
		IntermediateStrategy s = new IntermediateStrategy();
		
		int[] move = s.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
		
		g.makeMove(0,0);
		move = s.getMove(g,'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
		g.makeMove(1,0);
		g.makeMove(0,1);
		move = s.getMove(g,'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
		g.makeMove(1,1);
		move = s.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
		
		g = new Game();
		g.makeMove(0, 0);
		g.makeMove(0, 1);
		g.makeMove(0, 2);
		g.makeMove(1, 1);
		g.makeMove(1, 0);
		g.makeMove(1, 2);
		g.makeMove(2, 1);
		g.makeMove(2, 0);
		move = s.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
	}
	
}
