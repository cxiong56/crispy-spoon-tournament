package application;

public class Update {

	/**
	 * A constructor that will apply the users interactions to the HeapBracket,
	 * matches, and teams on updated scores
	 */
	public Update(Match match, int score1, int score2) {

	}

	/**
	 * Using the match object, record final score of the match and stores them into
	 * match object within the heap bracket
	 * 
	 * @param round
	 * @param game
	 * @param t1
	 * @param t2
	 */
	public void storeFinalScore(int round, int game, int t1, int t2) {

	}

	/**
	 * Goes through the matches that needs updating and calls update helper, moving
	 * the winner into the next bracket
	 * 
	 * @param heapBracket
	 */
	public void updateBracket(HeapBracket heapBracket) {

	}

	/**
	 * Compares the score, add an indicator for the match winner in the GUI and
	 * returns the winner
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	public Match updateWinHelper(Match m1, Match m2) {
		return m2;

	}
}
