package model;

import java.util.Observable;
import java.util.Observer;

public class RunTournament implements Observer {

	int beginner_wins = 0;
	int intermediate_wins = 0;
	int ties = 0;
	Player beginner_player;
	char beginner_player_char;
	Player intermediate_player;
	char intermediate_player_char;
	public static final int num_games = 1000;

	/**
	 * Run two tournaments between two players with different strategies. In the
	 * former, beginner player goes first every time. In the latter, the
	 * intermediate player goes first every time.
	 * 
	 * @param args
	 *            (not used)
	 */
	public static void main(String[] args) {
		RunTournament rt = new RunTournament();

		rt.beginner_player = new Player(new BeginnerStrategy());
		rt.intermediate_player = new Player(new IntermediateStrategy());

		System.out.println("Result of playing " + num_games
				+ " games when beginner goes first:");
		rt.beginner_player_char = 'X';
		rt.intermediate_player_char = 'O';
		rt.playTournament(rt.beginner_player, rt.intermediate_player);

		System.out.println();

		System.out.println("Result of playing " + num_games
				+ " games when intermediate goes first:");
		rt.beginner_player_char = 'O';
		rt.intermediate_player_char = 'X';
		rt.playTournament(rt.intermediate_player, rt.beginner_player);
	}

	/**
	 * Play a games between two players a number of times equal to num_games
	 * 
	 * @param player1
	 *            player to go first
	 * @param player2
	 *            player to go second
	 */
	private void playTournament(Player player1, Player player2) {
		for (int i = 0; i < num_games; i++) {
			Game g = new Game();
			g.addObserver(this);
			this.update(g, null);
		}

		System.out.println(String.format(
				"    Beginner: %s\n        Ties: %s\nIntermediate: %s",
				beginner_wins, ties, intermediate_wins));

		this.beginner_wins = 0;
		this.intermediate_wins = 0;
		this.ties = 0;
	}

	/**
	 * When a game changes, have the appropriate player make the next move. If
	 * the game is finished, increment the appropriate win count
	 * 
	 * @param game
	 *            the game to observer
	 * @param arg
	 *            not used
	 */
	@Override
	public void update(Observable game, Object arg) {
		if (((Game) game).isFinished()) {
			char winner = ((Game) game).getWinner();
			// System.out.println(game.toString());
			if (winner == '\0')
				this.ties++;
			else if (winner == beginner_player_char)
				this.beginner_wins++;
			else if (winner == intermediate_player_char)
				this.intermediate_wins++;
		} else {
			char current_player_char = ((Game) game).getCurrentPlayer();
			int[] move;
			if (current_player_char == beginner_player_char)
				move = beginner_player.getMove((Game) game);
			else
				move = intermediate_player.getMove((Game) game);
			// System.out.println(game.toString());
			((Game) game).makeMove(move[0], move[1]);
		}
	}

}
