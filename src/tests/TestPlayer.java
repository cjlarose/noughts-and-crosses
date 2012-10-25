package tests;

import static org.junit.Assert.*;
import model.*;

import org.junit.Test;

public class TestPlayer {
	
	@Test
	public void testGetSetAndConstructorAI() {
		Strategy s = new BeginnerStrategy();
		AIPlayer AI = new AIPlayer(s);
		assertEquals(s, AI.getStrategy());
		Strategy s1 = new IntermediateStrategy();
		AI.setStrategy(s1);
		assertEquals(s1, AI.getStrategy());
	}
	
	@Test
	public void testGetMove() {
		Game g = new Game();
		AIPlayer AI = new AIPlayer(new BeginnerStrategy());
		AI.getMove(g);
		assertFalse(g.isFinished());
	}
} 
