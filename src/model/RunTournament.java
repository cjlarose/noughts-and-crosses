package model;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class RunTournament implements Observer {
	
	int p1_wins = 0;
	int p2_wins = 0;
	int ties = 0;
	Player beginner_player;
	Player intermediate_player;

	public static void main(String[] args) {
		RunTournament rt = new RunTournament();
		
		rt.beginner_player = new AIPlayer(new BeginnerStrategy());
		rt.intermediate_player = new AIPlayer(new IntermediateStrategy());
		
		
		System.out.println("Result of playing 1000 games when beginner goes first:");
		rt.playTournament(rt.beginner_player, rt.intermediate_player, 1000);
		
		System.out.println();
		
		System.out.println("Result of playing 1000 games when intermediate goes first:");
		rt.playTournament(rt.intermediate_player, rt.beginner_player, 1000);
	}
	
	private void playTournament(Player player1, Player player2, int numGames) {

		for (int i = 0; i < numGames; i++) {
			Game g = new Game(player1, player2);
			g.addObserver(this);
			this.update(g, null);
		}

		System.out.println(String.format(
				"Beginner: %s\nTies: %s\nIntermediate: %s", p1_wins, p2_wins,
				ties));
	}

	@Override
	public void update(Observable game, Object arg) {
		if (((Game) game).isFinished()) {
			System.out.println("finished game");
		} else {
			Player current_player = ((Game) game).getCurrentPlayer();
			System.out.println(String.format("%s's turn", current_player == beginner_player ? "Beginner" : "Intermediate"));
			int[] move = ((AIPlayer) current_player).getMove((Game) game);
			((Game) game).makeMove(current_player, move[0], move[1]);
		}
	}

}
