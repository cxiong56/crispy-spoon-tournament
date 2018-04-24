package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeapBracket {

	protected int numTeams = 0;
	protected int numMatches;
	protected Team[] bracketArray;
	/**
	 * Generates the correct number of matches based on the number of teams and
	 * places them in the first round based on their seed value
	 * 
	 * @param inputFile
	 * @return
	 */
	public boolean buildBracket(File inputFile) {
		BufferedReader br;
		List<String> teamList = new ArrayList<String>();
		String input;
		
		try {
			br = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		try {
			while (br.ready()) {
				input = br.readLine();
				if(input != null) teamList.add(input);
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		
		numTeams = teamList.size();
		bracketArray = new Team[(numTeams * 2) - 1];
		int indexA = 0;
		int indexB = numTeams - 1;
		
		for( int i = (numTeams - 1); i < bracketArray.length; i+=2) {
			bracketArray[i] = new Team(teamList.get(indexA));
			if( numTeams > 1) {
				bracketArray[i + 1] = new Team(teamList.get(indexB));
			}
			indexA++;
			indexB--;
		}
		return true;
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
