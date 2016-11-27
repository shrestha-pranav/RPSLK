/**
 * Parses the command-line arguments and instantiates a Game class. Various
 * options have been included for command-line arguments, which must be followed
 * strictly for the program to work. Incorrect use of command-line will prompt a
 * display of correct usage and a system exit
 * 
 * <p>
 * The Game class instantiates two players, as per user specification (for
 * instance, Thrower(Computer without AI) vs simulated Human (Repeater), while
 * the Talker class deals with all the input, output and logging of data.
 * 
 * @author Pranav Shrestha ps2958
 *
 */
public class Runner {
	/**
	 * Parses command-line arguments and instantiates a Game as specified
	 * 
	 * <p>
	 * The various options include:
	 * <li>Human vs computer(random)
	 * <li>Human vs computer(AI)
	 * <li>Simulated Human vs computer(random)
	 * <li>Simulated Human vs computer(AI)
	 * 
	 * <p>
	 * The number of rounds must be specified if a simulated human is played
	 * 
	 * @param args
	 *            - Command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Pranav Shrestha ps2958\n");
				
		Game myGame = null;

		// Parsing command-line arguments and initializing the game
		if (args.length == 0)
			myGame = new Game();

		else if (args.length == 1)
			myGame = Boolean.parseBoolean(args[0]) ? new Game(true) : new Game();

		else if (args.length == 2 || args.length == 3) {
			String player = args[0].trim().toUpperCase();
			if (!(player.equals("ROTATOR") || player.equals("REPEATER")))
				exit();

			int numRounds = Integer.parseInt(args[1]);
			if (numRounds < 1)
				exit();

			if (args.length == 3 && Boolean.parseBoolean(args[2]))
				myGame = new Game(player, numRounds, true);
			else
				myGame = new Game(player, numRounds, false);
		} else
			exit();

		System.out.println("Welcome to RPSLK!");
		System.out.println("R = Rock, P = Paper, S = Scissor, L = Lizard, K = Spock\n");

		// Run the game, with specified options
		myGame.run();

		// End game, with exit code 1
		System.out.println("Thank you for playing!");
		System.exit(1);
	}

	/**
	 * Alerts users to the correct usage of command-line arguments. It is
	 * executed if command-line arguments don't match specifications.
	 * 
	 * <p>
	 * Exits the program with an error code -1
	 */
	private static void exit() {
		System.out.println("Correct usage:");
		System.out.println("java ./Runner				Start a new game Human v Computer without AI");
		System.out.println("java ./Runner true			Start a new game Human v Computer with AI");
		System.out.println("java ./Runner [p] [n]		Simulate n rounds Player p v Computer without AI");
		System.out.println("java ./Runner [p] [n] true	Simulate n rounds Player v Computer with AI");
		System.out.println("\n	[n] = number of rounds >0 \n	[p] = Rotator|Repeater");
		System.exit(-1);
	}
}