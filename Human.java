/**
 * The class for giving all human throws, including user input and simulated
 * human throws. Determines if the player is a human, a simulated Repeater or a
 * simulated Rotator, and proceeds accordingly.
 * 
 * @author Pranav Shrestha ps2958
 *
 */
public class Human {
	private int playStyle = 0;

	/**
	 * Defines which Human is to be played
	 * 
	 * @param style
	 *            - int determines if player is human, Repeater or Rotator
	 */
	Human(int style) {
		playStyle = style;
	}

	/**
	 * Runs a throw of the RPSLK game
	 * 
	 * @param t
	 *            -Reference to the logger class, for getting data for next
	 *            throws of SimulatedHumans
	 * @return playerMove -Returns the move (int)(1-5) of the "human" player
	 */
	public int play(Talker t) {
		if (playStyle == 0)
			return t.userInput();
		else if (playStyle == 1)
			return (t.getLogSize() == 0) ? (int) (Math.random() * 5 + 1) : t.getLog(-1);
		else {
			if (t.getLogSize() == 0)
				return (int) (Math.random() * 5 + 1);
			else {
				int tmp = (t.getLog(-1) + 1) % 5;
				return (tmp == 0) ? 5 : tmp;
			}
		}
	}
}
