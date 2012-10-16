package model;

public class IntermediateStrategy implements Strategy {

	@Override
	public int[] getMove(Game g, Player p) {
		return new int[] {0,0};
	}

}
