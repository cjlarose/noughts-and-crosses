package model;

public class BeginnerStrategy implements Strategy {

	@Override
	public int[] getMove(Game g, Player p) {
		return new int[] {0,0};
	}

}
