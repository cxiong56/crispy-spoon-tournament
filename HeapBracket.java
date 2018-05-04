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

	private static Match[] matches;
	private static int numMatches;
	private static int numRounds;
	static List<Team> teams;

	public static void setupHeapBracket(File inputFile) throws IOException {
		teams = new ArrayList<Team>();
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		int temp = 1;
		while (inputReader.ready()) {
			String in = inputReader.readLine();

			if (in != null)
				teams.add(new Team(in, temp));
			temp++;
		}
		inputReader.close();
		
		if(teams.size() == 0) {
			return;
		}
		
		numMatches = teams.size() - 1;
		numRounds = (int) (Math.log(teams.size()) / Math.log(2));
		matches = new Match[numMatches];

		int roundStart = (int) (Math.pow(2, numRounds - 1)) - 1;
		for (int i = 0; i < roundStart; i++) {
			matches[i] = new Match(null, null, i);
		}
		for (int a = 0; a < teams.size() / 2; a++) {
			matches[roundStart + a] = new Match(teams.get(a), teams.get(findOpponent(a)), roundStart + a);
		}
	}
	
	private static int findOpponent(int i) {
		return teams.size() - i - 1;
	}

	public static Team getTeam(String name) {
		// compare name give to names of the teams in the array
		for (int i = 0; i < teams.size(); i++) {
			if (name.equals(teams.get(i).getName())) {
				return teams.get(i);
			} else {
				// loop
			}
		}
		return null;

	}

	public static List<Match> getRound(int roundNum) {
		List<Match> round = new ArrayList<Match>();
		int numInRound = numInRound(roundNum);
		int roundStart = numInRound - 1;
		for (int i = 0; i < numInRound; i++)
			round.add(matches[roundStart + i]);
		return round;
	}

	public static int numRounds() {
		return numRounds;
	}

	public static int numInRound(int round) {
		return (int) (Math.pow(2, round - 1));
	}

	public static void update() {
		for (int index = matches.length - 1; index >= 0; index--) {
			Match l = null, r = null;
			try {
				l = matches[getLeft(index)];
				r = matches[getRight(index)];
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
			if (l.getWinner() != null)
				matches[index].setTeam1(l.getWinner());
			else
				matches[index].setTeam1(null);
			if (r.getWinner() != null)
				matches[index].setTeam2(r.getWinner());
			else
				matches[index].setTeam2(null);
		}
//		for (int curRound = numRounds - 1; curRound >= 0; curRound--) {
//			int roundStart = numInRound(curRound) - 1;
//			for (int curMatch = 0; curMatch < numInRound(curRound); curMatch++) {
//				int index = roundStart + curMatch;
//				if (matches[getLeft(index)] != null && matches[getLeft(index)].getWinner() != null)
//					matches[index].setTeam1(matches[getLeft(index)].getWinner());
//				if (matches[getRight(index)] != null && matches[getRight(index)].getWinner() != null)
//					matches[index].setTeam2(matches[getRight(index)].getWinner());
//			}
//		}
	}

	private static int getLeft(int index) {
		return (index + 1) * 2 - 1;
	}

	private static int getRight(int index) {
		return getLeft(index) + 1;
	}

	public static int getMatchNumber(String team) {
		int match = 0;
		for (int i = 1; i < matches.length; i++) {
			if (matches[i].getTeam1().getName() == (team) || matches[i].getTeam2().getName() == (team)) {
				match = i;
				System.out.println(match);
			}

		}
		return match;
	}
	
	public static Team getWinner() {
		if (matches != null && matches.length > 0 && matches[0] != null)
			return matches[0].getWinner();
		return null;
	}

	public static Team getSecondPlace() {
		if (matches != null && matches.length > 0 && matches[0] != null)
			return matches[0].getLoser();
		return null;
	}
	
	public static Team getThirdPlace() {
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
