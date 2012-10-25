package test;

import static org.junit.Assert.assertTrue;
import model.BeginnerStrategy;
import model.Game;
import model.IntermediateStrategy;
import model.Player;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player p = new Player(new BeginnerStrategy());
		p.setStrategy(new IntermediateStrategy());
		int[] move = p.getMove(new Game());
		assertTrue(move[0] >= 0 && move[0] <= 2);
		assertTrue(move[1] >= 0 && move[1] <= 2);
	}
}
