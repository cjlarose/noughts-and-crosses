package model;

public class BeginnerStrategy implements Strategy {

	@Override
	public int[] getMove(Game g) {
		for(int i = 0; i < 9; i++) {
			if(!g.isOccupied(i/3, i%3))
				return new int[] {i/3, i%3};
		}
		
		return new int[] {0,0};
	}

}
