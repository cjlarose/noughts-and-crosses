package tests;

import static org.junit.Assert.*;

import org.junit.*;
import model.*;

public class TestStrategy {
	@Test
	public void testBeginner() {
		Strategy s = new BeginnerStrategy();
		Game g = new Game();
		int [] move;
		
		move = s.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] < 3);
		assertTrue(move[1] >= 0 && move[1] < 3);
		g.makeMove(move);
		move = s.getMove(g, 'X');
		g.makeMove(move);
		move = s.getMove(g, 'X');
		g.makeMove(move);
		move = s.getMove(g, 'X');
	}
	
	@Test
	public void testIntermediate() {
		Strategy s = new IntermediateStrategy();
		Game g = new Game();
		int [] move;
		
		move = s.getMove(g, 'X');
		assertTrue(move[0] >= 0 && move[0] < 3);
		assertTrue(move[1] >= 0 && move[1] < 3);
		
		g = new Game();
		g.makeMove(new int [] {0, 0});
		g.makeMove(new int [] {0, 1});
		g.makeMove(new int [] {1, 0});
		System.out.println(g.toString() + "\n");
		
		move = s.getMove(g, 'O');
		assertEquals(2, move[0]);
		assertEquals(0, move[1]);
		g.makeMove(s.getMove(g, 'O'));
		assertFalse(g.isFinished());
		
		g = new Game();
		g.makeMove(new int [] {0, 0});
		g.makeMove(new int [] {0, 1});
		g.makeMove(new int [] {0, 2});
		g.makeMove(new int [] {1, 1});
		g.makeMove(new int [] {2, 0});
		System.out.println(g.toString()  + "\n");
		
		move = s.getMove(g, 'O');
		assertEquals(2, move[0]);
		assertEquals(1, move[1]);
		g.makeMove(move);
		System.out.println(g.toString());
		assertTrue(g.isFinished());
	}
}
