/**
 * A basic AI class, that searches for patterns within the human's throws to
 * determine a probabilistically higher winning move for next turn. In condition
 * of sufficient data (n>5), it checks for same throw pattern in the log (e.g.
 * if the last 4 throws have been thrown in the same order earlier). If found,
 * it gets the next move played. Under multiple matches, it chooses the move
 * that's most likely to win.
 * 
 * <p>
 * This is most effective against forms of strategy. Since strategy such as
 * Repeaters and Rotators easily form patterns, this method can easily and
 * consistently beat them. Strategies such as the Reflector and Reinvestor as
 * also prone to falling into patterns, hence being prone to lose.
 * 
 * <p>
 * In the worst case scenario, no pattern can be found and it resorts to a
 * random move, which has equal chances of winning and losing.
 * 
 * <p>
 * Effectiveness of this class can be checked by comparing final win% against
 * previous runs.
 * 
 * @author Pranav Shrestha ps2958
 *
 */
public class Thinker extends Thrower {
	Talker logger;
	/**
	 * preData is the array of moves that will beat a particular move. e.g.
	 * Scissor(1) will beat {Paper(2) and Lizard(4)}
	 */

	int[][] preData = { { 2, 4 }, { 3, 0 }, { 4, 1 }, { 0, 2 }, { 1, 3 } };

	/**
	 * 
	 * @param myTalk
	 *            - Reference to logger class for access to game logs
	 */
	Thinker(Talker myTalk) {
		logger = myTalk;
	}

	/**
	 * Overrides the Thrower play(), to implement the AI module. If the AI can't
	 * find a pattern, then it'll resort to the random() method of determining
	 * next move.
	 */
	public int play() {
		int j = getNextMove();
		if (j == 0)
			return super.play();
		else {
			return j;
		}
	}

	/**
	 * <ol>
	 * <li>If logSize <5, resort to random method since patterns aren't likely
	 * to be found in small data
	 * <li>Checks if the last 4 moves have been repeated in the past.
	 * <li>If not found, then checks for last 3 moves, then 2 moves and so on.
	 * <li>If found, plays the counter for the move played after the pattern.
	 * <li>If multiple found, determines best counter across different moves
	 * played, by adding up winningMoves. In case of tie, randomly chooses one.
	 * </ol>
	 * 
	 * @return nextMove -If == 0, pattern wasn't found (resort to random move).
	 */
	public int getNextMove() {
		int cap = logger.getLogSize();
		if (cap <= 5)
			return 0;

		int[] first = new int[5];
		boolean found = false;
		int numMoves = 4;

		while (numMoves > 0 && !found) {
			int j = 0;
			while (j + numMoves < cap) {

				boolean flag = true;
				for (int i = 0; i < numMoves && flag; i++) {
					if (logger.getLog(i + j) != logger.getLog(cap - numMoves + i))
						flag = false;
				}

				if (flag) {
					int nextMove = logger.getLog(j + numMoves) - 1;
					first[preData[nextMove][0]]++;
					first[preData[nextMove][1]]++;
					found = true;
				}
				j++;
			}
			numMoves--;
		}

		if (!found)
			return 0;

		int value = 0;
		int max = 0;
		for (int i = 0; i < 5; i++) {
			if (first[i] > max) {
				max = first[i];
				value = i + 1;
			} else if (first[i] == max) {
				if (Math.round(Math.random()) == 0)
					value = i + 1;
			}
		}
		return value;
	}
}
