/**
 * Simulates a computer throw. Randomly selects a number from 1 to 5, and plays
 * it. Acts as a base class for the AI Thinker() class.
 * 
 * @author Pranav Shrestha ps2958
 *
 */
public class Thrower {
	public int play() {
		int i;
		do {
			i = (int) (Math.random() * 5 + 1);
		} while (i < 1 || i > 5);
		return i;
	}
}