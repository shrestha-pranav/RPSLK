/**
 * Creates the players, and runs the game as specified by Runner. Can be
 * instantiated in different ways - depending on user-specifications.
 * <p>
 * Contains game data, including wins, draws, losses, player/computer moves and
 * data for determining wins/losses.
 * <p>
 * Uses the Talker class to log data, including move history.
 * 
 * @author Pranav Shrestha ps2958
 */
public class Game {
	/**
	 * Declares/Initializes vital variables/objects for the game, specifying
	 * number of rounds to play, the players, the logger object, wins, draws,
	 * losses, and winChecking gameData array.
	 */
	private int numRounds = -1;
	private Human player1;
	private Thrower player2;
	private Talker myTalk;

	private int win, draw, loss;
	private int[][] gameData = { { 0, 1, -1, 6, -1 }, { -1, 0, 2, -1, 7 }, { 3, -1, 0, 3, -1 }, { -1, 8, -1, 0, 4 },
			{ 5, -1, 9, -1, 0 } };

	/**
	 * Default constructor: Creates a game with a human user and a computer
	 * without AI with an estimated 50 rounds (which may be increased with need)
	 */
	Game() {
		myTalk = new Talker(50);
		player1 = new Human(0);
		player2 = new Thrower();
	}

	/**
	 * Creates a game with a human user and a computer with/without AI
	 * 
	 * @param ai
	 *            - boolean value[True = AI, False = no AI]
	 */
	Game(boolean ai) {
		myTalk = new Talker(50);
		player1 = new Human(0);
		player2 = (ai) ? new Thinker(myTalk) : new Thrower();
	}

	/**
	 * Creates a game between a simulated human and computer(random or AI)
	 * 
	 * @param player
	 *            - String : specifies human player as Repeater or Rotator
	 * @param numRounds
	 *            - int: Number of rounds to simulate
	 * @param ai
	 *            - boolean : value[True = AI, False = no AI]
	 */
	Game(String player, int numRounds, boolean ai) {
		this.numRounds = numRounds;
		myTalk = new Talker(numRounds);

		player1 = (player.equals("REPEATER")) ? new Human(1) : new Human(2);
		player2 = (ai) ? new Thinker(myTalk) : new Thrower();
	}

	/**
	 * Runs the game, including getting human move, getting computer move,
	 * checking for win/loss/draw and calling a logging function. If a number of
	 * rounds is specified (in case of simulated humans), the game runs for the
	 * specified number of rounds.
	 */
	public void run() {
		while (true) {
			int move1 = player1.play(myTalk);
			if (move1 == 0)
				break;

			int move2 = player2.play();

			myTalk.log(move1, move2);

			if (gameData[move1 - 1][move2 - 1] == 0) {
				myTalk.log("It's a draw");
				draw++;
			} else if (gameData[move1 - 1][move2 - 1] == -1) {
				myTalk.log(move2, move1, gameData[move2 - 1][move1 - 1]);
				myTalk.log("You lose!");
				loss++;
			} else {
				myTalk.log(move1, move2, gameData[move1 - 1][move2 - 1]);
				myTalk.log("You win!");
				win++;
			}

			numRounds--;
			if (numRounds == 0)
				break;
			System.out.print("\n");
		}
		endGame();
	}

	/**
	 * Displays valuable information about the game played after the game ends,
	 * including:
	 * <ul>
	 * <li>Total wins, losses and draws
	 * <li>Win% (= numberOfHumanWins/totalWins*100)
	 * <li>Draw% (= numberOfDraws/totalGames*100)
	 * </ul>
	 */
	public void endGame() {
		int totalGames = win + draw + loss;
		int totalWins = win + loss;
		myTalk.log("\nTotal games : " + totalGames);
		myTalk.log("Human:" + win + " Computer:" + loss + " Draws:" + draw);

		if (totalWins != 0)
			myTalk.log("Human win % : " + (100 * win / totalWins) + "%");
		if (totalGames != 0)
			myTalk.log("Draw % : " + (100 * draw / totalGames) + "%");
	}
}
