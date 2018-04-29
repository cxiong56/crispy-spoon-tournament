package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeapBracket {
	
	private Match[] matches;
	private int numMatches;
	private int numRounds;
	
	public HeapBracket(File inputFile) throws IOException {
		List<String> teams = new ArrayList<String>();
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		while(inputReader.ready()) {
			String in = inputReader.readLine();
			if (in != null)
				teams.add(in);
		}
		inputReader.close();
		
		numMatches = teams.size() - 1;
		numRounds = (int) (Math.log(teams.size()) / Math.log(2));
		matches = new Match[numMatches];
		
		int roundStart = (int) (Math.pow(2, numRounds - 1)) - 1;
		for (int a = 0, b = teams.size() - 1; a < b; a++, b--) {
			matches[roundStart + a] = new Match(teams.get(a), teams.get(b), 0);
		}
		Iterator<Match> r = getRound(3);
		System.out.println(r);
	}
	
	public Iterator<Match> getRound(int roundNum) {
		List<Match> round = new ArrayList<Match>();
		int numInRound = (int) (Math.pow(2, roundNum - 1));
		int roundStart = numInRound - 1;
		for (int i = 0; i < numInRound; i++)
			round.add(matches[roundStart + i]);
		return round.iterator();
	}
	
//
//	protected int numTeams = 0;
//	protected int numMatches;
//	protected Team[] bracketArray;
//	protected int numRounds;
//
//	public HeapBracket(File inputFile) {
//		if (!buildBracket(inputFile))
//			throw new IllegalArgumentException();
//	}
//
//	/**
//	 * Generates the correct number of matches based on the number of teams and
//	 * places them in the first round based on their seed value
//	 * 
//	 * @param inputFile
//	 * @return
//	 */
//	public boolean buildBracket(File inputFile) {
//		BufferedReader br;
//		List<String> teamList = new ArrayList<String>();
//		String input;
//
//		try {
//			br = new BufferedReader(new FileReader(inputFile));
//		} catch (FileNotFoundException e) {
//			return false;
//		}
//
//		try {
//			while (br.ready()) {
//				input = br.readLine();
//				if (input != null)
//					teamList.add(input);
//			}
//			br.close();
//		} catch (IOException e) {
//			return false;
//		}
//
//		numTeams = teamList.size();
//		bracketArray = new Team[(numTeams * 2) - 1];
//
//		numRounds = (int) (Math.log(numTeams) / Math.log(2));
//		numMatches = numTeams - 1;
//
//		int indexA = 0;
//		int indexB = numTeams - 1;
//
//		for (int i = (numTeams - 1); i < bracketArray.length; i += 2) {
//			bracketArray[i] = new Team(teamList.get(indexA));
//			if (numTeams > 1) {
//				bracketArray[i + 1] = new Team(teamList.get(indexB));
//			}
//			indexA++;
//			indexB--;
//		}
//		return true;
//	}
//
//	/**
//	 * Returns the match object associated with a given ordered pair. The first
//	 * value refers to the round number (0 for first round, 1 for second, etc.)
//	 * and the second refers to a specific match in that round
//	 * 
//	 * @param round
//	 * @param game
//	 * @return
//	 */
//	public Match getMatch(int round, int game) {
//		int correctedRound = numRounds - round;
//		int correctedGame = game - 1;
//		int index;
//		if (correctedGame >= (int) Math.pow(2, correctedRound)) {
//			return null;
//		}
//		index = (int) Math.pow(2, correctedRound) - 1 + correctedGame;
//
//		Match tempMatch = new Match(bracketArray[(index * 2) + 1], bracketArray[(index * 2) + 2]);
//		return tempMatch;
//
//	}
//
//	/**
//	 * Returns the left child of the specified match (the children being the
//	 * matches determining the players in this match)
//	 * 
//	 * @param round
//	 * @param game
//	 * @return
//	 */
//	public Match getLeft(int round, int game) {
//		int correctedRound = numRounds - round;
//		int correctedGame = game - 1;
//		int index;
//		if (correctedGame >= (int) Math.pow(2, correctedRound)) {
//			return null;
//		}
//		index = 2 * ((int) Math.pow(2, correctedRound) - 1 + correctedGame) + 1;
//
//		Match tempMatch = new Match(bracketArray[(index * 2) + 1], bracketArray[(index * 2) + 2]);
//		return tempMatch;
//
//	}
//
//	/**
//	 * Returns the right child of the specified match (the children being the
//	 * matches determining the players in this match)
//	 * 
//	 * @param round
//	 * @param game
//	 * @return
//	 */
//	public Match getRight(int round, int game) {
//		int correctedRound = numRounds - round;
//		int correctedGame = game - 1;
//		int index;
//		if (correctedGame >= (int) Math.pow(2, correctedRound)) {
//			return null;
//		}
//		index = 2 * ((int) Math.pow(2, correctedRound) - 1 + correctedGame) + 2;
//
//		Match tempMatch = new Match(bracketArray[(index * 2) + 1], bracketArray[(index * 2) + 2]);
//		return tempMatch;
//	}
//
//	/**
//	 * Returns the parent of the specified match (the parent being the match the
//	 * winner of this match will play next)
//	 * 
//	 * @param round
//	 * @param game
//	 * @return
//	 */
//	public Match getParent(int round, int game) {
//		int correctedRound = numRounds - round;
//		int correctedGame = game - 1;
//		int index;
//		if (correctedGame >= (int) Math.pow(2, correctedRound)) {
//			return null;
//		}
//		index = (((int) Math.pow(2, correctedRound) - 1 + correctedGame) - 1) / 2;
//		if (index < 0)
//			return null;
//		Match tempMatch = new Match(bracketArray[(index * 2) + 1], bracketArray[(index * 2) + 2]);
//		return tempMatch;
//	}
//
//	/**
//	 * Returns an iterable containing every match object in the bracket
//	 * 
//	 * @return
//	 */
//	public Iterable<Match> getAllMatches() {
//		List<Match> matches = new ArrayList<Match>();
//		for (int i = 0; i < numRounds; i++) {
//			for (int j = 0; j < (int) Math.pow(2, i); j++) {
//				matches.add(getMatch(i, j));
//			}
//		}
//		return matches;
//
//	}
//
//	public Iterable<Match> getRound(int round) {
//		List<Match> roundList = new ArrayList<Match>();
//		for (int j = 0; j < (int) Math.pow(2, round); j++) {
//			roundList.add(getMatch(round, j));
//		}
//		return roundList;
//	}
}
