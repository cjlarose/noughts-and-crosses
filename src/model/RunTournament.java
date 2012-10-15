package model;

public class RunTournament {

	public static void main(String[] args) {
		Player beginner_player = new AIPlayer(new BeginnerStrategy());
		Player intermediate_player = new AIPlayer(new IntermediateStrategy());
		
		System.out.println("Result of playing 1000 games when beginner goes first:");
		RunTournament.playTournament(beginner_player, intermediate_player, 1000);
		
		System.out.println();
		
		System.out.println("Result of playing 1000 games when intermediate goes first:");
		RunTournament.playTournament(intermediate_player, beginner_player, 1000);
	}
	
	private static void playTournament(Player player1, Player player2, int numGames) {
		Tournament t = new Tournament(player1, player2);

		for (int i = 0; i < numGames; i++)
			t.playGame();

		int p1_wins = t.getWins(player2);
		int p2_wins = t.getWins(player2);
		int ties = t.getTies();

		System.out.println(String.format(
				"Beginner: %s\nTies: %s\nIntermediate: %s", p1_wins, p2_wins,
				ties));
	}

}
