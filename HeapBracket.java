/////////////////////////////////////////////////////////////////////////////
//Semester:         CS400 Spring 2018
//PROJECT:          cs400_Team Project Milestone 3
//FILES:            Main.java
//                  HeapBracket.java
//                  Match.java
//                  Team.java
//                  Update.java
//
//Team:             Brock Wroblewski
//                  Khai Bin Woon
//                  Amber Westlund
//                  Henry Wieland
//                  Chaly Xiong
//
//Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
//
/////////////////////////////////////////////////////////////////////////////

package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeapBracket {

	// Heap type array of matches
	private static Match[] matches;
	// Total number of matches
	private static int numMatches;
	// Total number of rounds
	private static int numRounds;
	// List of the teams in the tournament
	static List<Team> teams;

	/**
	 * Initializes the bracket based on an input file
	 * 
	 * @param inputFile
	 * @throws IOException
	 *             - If there's problems with reading the file
	 */
	public static void setupHeapBracket(File inputFile) throws IOException {
		teams = new ArrayList<Team>();

		// Read teams from inputFile
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		int ranking = 1;
		while (inputReader.ready()) {
			String in = inputReader.readLine();
			if (in != null)
				teams.add(new Team(in, ranking));
			ranking++;
		}
		inputReader.close();

		if (teams.size() == 0) {
			return;
		}

		numMatches = teams.size() - 1;
		numRounds = (int) (Math.log(teams.size()) / Math.log(2));
		matches = new Match[numMatches];

		int firstRoundStart = (int) (Math.pow(2, numRounds - 1)) - 1;
		// Initialize the 2nd through final rounds with empty matches
		for (int i = 0; i < firstRoundStart; i++) {
			matches[i] = new Match(null, null, i);
		}
		// Initialize the 1st round based on proper tournament seeding
		Iterator<Integer> seeding = seedBracket(new ArrayList<Integer>()).iterator();
		for (int i = 0; i < teams.size() / 2; i++) {
			Match m = new Match(teams.get(seeding.next()), teams.get(seeding.next()), firstRoundStart + i);
			matches[firstRoundStart + i] = m;
		}
	}

	/**
	 * A recursive helper method for seeding the bracket. Returns an Iterable
	 * containing pairs of zero indexed Integers denoting two teams to play each
	 * other in the first match. e.g. 1 team: {0} 2 teams: {0, 1} 4 teams: {0,
	 * 3, 1, 2} 8 teams: {0, 7, 3, 4, 1, 6, 2, 5} etc.
	 */
	private static Iterable<Integer> seedBracket(ArrayList<Integer> prev) {
		ArrayList<Integer> next = new ArrayList<Integer>();
		if (prev.isEmpty())
			next.add(0);
		else
			for (int i : prev) {
				next.add(i);
				next.add(findOpponent(i, prev.size() * 2));
			}
		if (next.size() == teams.size())
			return next;
		else
			return seedBracket(next);
	}

	/**
	 * A helper method for seedBracket() Finds the index of the team the team at
	 * index i would play in a tournament with size teams.
	 */
	private static int findOpponent(int i, int size) {
		return size - i - 1;
	}

	/**
	 * Returns a list of the matches in a given round based on that round's
	 * number.
	 */
	public static List<Match> getRound(int roundNum) {
		List<Match> round = new ArrayList<Match>();
		int numInRound = numInRound(roundNum);
		int roundStart = numInRound - 1;
		for (int i = 0; i < numInRound; i++)
			round.add(matches[roundStart + i]);
		return round;
	}

	/**
	 * Accessor method for the number of rounds
	 */
	public static int numRounds() {
		return numRounds;
	}

	/**
	 * Returns the number of matches in a given round
	 */
	public static int numInRound(int round) {
		return (int) (Math.pow(2, round - 1));
	}

	/**
	 * Updates the state of the HeapBracket based on any won matches.
	 */
	public static void update() {
		for (int index = matches.length - 1; index >= 0; index--) {
			Match left = null, right = null;
			try {
				left = matches[getLeft(index)];
				right = matches[getRight(index)];
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
			if (left.getWinner() != null)
				matches[index].setTeam1(left.getWinner());
			else
				matches[index].setTeam1(null);
			if (right.getWinner() != null)
				matches[index].setTeam2(right.getWinner());
			else
				matches[index].setTeam2(null);
		}
	}

	/**
	 * Returns the index of the match that determines team1 of the match at the
	 * given index
	 */
	private static int getLeft(int index) {
		return (index + 1) * 2 - 1;
	}

	/**
	 * Returns the index of the match that determines team2 of the match at the
	 * given index
	 */
	private static int getRight(int index) {
		return getLeft(index) + 1;
	}

	/**
	 * Returns the team that won the tournament
	 */
	public static Team getWinner() {
		if (matches != null && matches.length > 0 && matches[0] != null)
			return matches[0].getWinner();
		return null;
	}

	/**
	 * Returns the team that got second place in the tournament
	 */
	public static Team getSecondPlace() {
		if (matches != null && matches.length > 0 && matches[0] != null)
			return matches[0].getLoser();
		return null;
	}

	/**
	 * Returns the team that got third place in the tournament.
	 */
	public static Team getThirdPlace() {
		// Create array of the semifinal teams and their scores in the semifinal
		// matches
		Team[] teams = new Team[4];
		int[] scores = new int[4];
		if (matches == null || matches.length < 4)
			return null;
		if (!matches[1].isFinal() || !matches[2].isFinal())
			return null;
		if (matches[1] != null) {
			Match m = matches[1];
			teams[0] = m.getTeam1();
			scores[0] = m.getScore1();
			teams[1] = m.getTeam2();
			scores[1] = m.getScore2();
		}
		if (matches[2] != null) {
			Match m = matches[2];
			teams[2] = m.getTeam1();
			scores[2] = m.getScore1();
			teams[3] = m.getTeam2();
			scores[3] = m.getScore2();
		}
		// Return the team that had the highest score in a semifinal
		Team third = null;
		int maxScore = 0;
		for (int i = 0; i < teams.length; i++) {
			if (scores[i] > maxScore) {
				maxScore = scores[i];
				third = teams[i];
			}
		}
		return third;
	}
}
