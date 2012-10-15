package model;

public class RunTournament {

	public static void main(String[] args) {
		Player beginner_player = new AIPlayer(new BeginnerStrategy());
		Player intermediate_player = new AIPlayer(new IntermediateStrategy());

		Tournament t = new Tournament(beginner_player, intermediate_player);
		for (int i = 0; i < 1000; i++)
			t.playGame();

		int p1_wins = t.getWins(beginner_player);
		int p2_wins = t.getWins(intermediate_player);
		int ties = t.getWins(null);

		System.out
				.println("Result of playing 1000 games when beginner goes first:");
		System.out.println(String.format(
				"Beginner: %s\nTies: %s\nIntermediate: %s", p1_wins, p2_wins,
				ties));
	}

}
