package application;

import java.io.File;

public class HeapBracket {

	/**
	 * Generates the correct number of matches based on the number of teams and
	 * places them in the first round based on their seed value
	 * 
	 * @param inputFile
	 * @return
	 */
	public boolean buildBracket(File inputFile) {
		return false;

	}

	/**
	 * Returns the match object associated with a given ordered pair. The first
	 * value refers to the round number (0 for first round, 1 for second, etc.) and
	 * the second refers to a specific match in that round
	 * 
	 * @param round
	 * @param game
	 * @return
	 */
	public Match getMatch(int round, int game) {
		return null;

	}

	/**
	 * Returns the left child of the specified match (the children being the matches
	 * determining the players in this match)
	 * 
	 * @param round
	 * @param game
	 * @return
	 */
	public Match getLeft(int round, int game) {
		return null;

	}

	/**
	 * Returns the right child of the specified match (the children being the
	 * matches determining the players in this match)
	 * 
	 * @param round
	 * @param game
	 * @return
	 */
	public Match getRight(int round, int game) {
		return null;

	}

	/**
	 * Returns the parent of the specified match (the parent being the match the
	 * winner of this match will play next)
	 * 
	 * @param round
	 * @param game
	 * @return
	 */
	public Match getParent(int round, int game) {
		return null;

	}

	/**
	 * Returns an iterable containing every match object in the bracket
	 * 
	 * @return
	 */
	public Iterable<Match> getAllMatches() {
		return null;

	}
}
