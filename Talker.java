import java.util.Scanner;

/**
 * Used for all input, output and logging functions. Also includes gameStrings
 * for parsing output. Contains methods for input/output, and variables (int[])
 * for logging past moves. Can be extended to include file support for inputs
 * and outputs.
 * 
 * @author Pranav Shrestha ps2958
 *
 */
public class Talker {
	private static Scanner inptr = new Scanner(System.in);

	private String[] moveNames = { "Scissor", "Paper", "Rock", "Lizard", "Spock" };
	private String[] winNames = { "cuts", "covers", "crushes", "poisons", "smashes", "decapitates", "disproves", "eats",
			"vaporizes" };

	private int[] log;
	private int logSize = 0;

	/**
	 * @param numRounds
	 *            -Initializes log with an initial size
	 */
	Talker(int numRounds) {
		log = new int[numRounds];
	}

	/**
	 * Method for getting user-input, including basic input-validation
	 * 
	 * @return move - user input move as an int (1-5) or exit(0)
	 */
	public int userInput() {
		int value;
		String input;

		while (true) {
			System.out.print("Enter your choice r/p/s/l/k/z: ");
			input = inptr.nextLine();
			if (input.length() != 1)
				continue;

			value = "zsprlkZSPRLK".indexOf(input.charAt(0));
			if (value > -1)
				break;
		}
		return value % 6;
	}

	/**
	 * prints out the string specified. Meant to be extended for file output
	 * 
	 * @param s
	 *            - String to output
	 */
	public void log(String s) {
		System.out.println(s);
	}

	/**
	 * Adds the human's move to the game log, and calls output function for
	 * displaying game state (moves chosen by both players).
	 * 
	 * @param move1
	 *            -Human's move. To be added to the game log
	 * @param move2
	 *            -Computer's move.
	 */
	public void log(int move1, int move2) {
		if (logSize == log.length)
			expand();

		log[logSize] = move1;
		logSize++;

		log("You played " + moveNames[move1 - 1] + ". The computer played " + moveNames[move2 - 1]);
	}

	/**
	 * Formats the game round, explaining how move1 wins.For instance, Rock
	 * "crushes" Scissor
	 * 
	 * @param move1
	 * @param move2
	 * @param verb
	 */
	public void log(int move1, int move2, int verb) {
		log(moveNames[move1 - 1] + " " + winNames[verb - 1] + " " + moveNames[move2 - 1]);
	}

	/**
	 * Getter method for the logSize;
	 * 
	 * @return logSize - Number of human moves in history
	 */
	public int getLogSize() {
		return logSize;
	}

	/**
	 * Getter method for the log
	 * 
	 * @param i
	 *            - index of log to access
	 * @return the (i)th move played by the human
	 */
	public int getLog(int i) {
		return (i < 0) ? log[logSize + i] : log[i];
	}

	/**
	 * Increases the size of the log when needed (like an ArrayList)
	 */
	public void expand() {
		int[] tmp = new int[(int) (log.length * 1.5)];
		for (int i = 0; i < logSize; i++)
			tmp[i] = log[i];
		log = tmp;
	}
}
