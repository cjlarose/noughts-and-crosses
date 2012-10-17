package model;

public class IntermediateStrategy implements Strategy {
	
	private int [] almost_wins = {384, 192, 320, 288, 260, 36, 
			48, 40, 24, 144, 130, 18, 6, 5, 3, 72, 65, 9, 272, 
			257, 17, 80, 68, 20};
	
	public IntermediateStrategy () {
		
	}
	@Override
	public int[] getMove(Game g, Player p) {
		return new int[] {0,0};
	}

}
