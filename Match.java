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

/**
 * Contains the details of a match/game between two teams.
 */
public class Match {
	private Team team1;
	private int score1;
	private Team team2;
	private int score2;
	private int matchNum;
	private boolean finalized = false;

	/**
	 * Creates a new match between two teams with the given match number
	 * 
	 * @param team1
	 * @param team2
	 * @param matchNum
	 */
	public Match(Team team1, Team team2, int matchNum) {
		this.team1 = team1;
		this.team2 = team2;
		this.matchNum = matchNum;
	}

	/**
	 * Creates a new match between two teams
	 * 
	 * @param team1
	 * @param team2
	 */
	public Match(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.matchNum = 0;
	}
	
	/**
	 * Accessor for team1
	 */
	public Team getTeam1() {
		return team1;
	}
	
	/**
	 * Mutator for team1
	 */
	public void setTeam1(Team t) {
		team1 = t;
	}

	/**
	 * Accessor for score1
	 */
	public int getScore1() {
	    return score1;
	}
	
	/**
	 * Mutator for score1
	 */
	public void setScore1(int s) {
	    score1 = s;
	}
	
	/**
	 * Accessor for team2
	 */
	public Team getTeam2() {
		return team2;
	}
	
	/**
	 * Mutator for team2
	 */
	public void setTeam2(Team t) {
		team2 = t;
	}

	/**
	 * Accessor for score2
	 */
	public int getScore2() {
	    return score2;
	}
	
	/**
	 * Mutator for score2
	 */
	public void setScore2(int s) {
	    score1 = s;
	}
	
	/**
	 * Accessor for match number
	 */
	public int getNum() {
	    return matchNum;
	}

	/**
	 * Returns the winner of this match
	 * Will default to team1 in a tie
	 */
	public Team getWinner() {
		if (score1 > score2)
			return team1;
		else if (score2 > score1)
			return team2;
		else
			return isFinal() ? team1 : null;
	}
	
	/**
	 * Returns the team that lost this match
	 * Will default to team2 in a tie
	 */
	public Team getLoser() {
		if (score1 < score2)
			return team1;
		else if (score2 < score1)
			return team2;
		else
			return isFinal() ? team2: null;
	}
	
	/**
	 * Returns a string representation of this match
	 */
	public String toString() {
		return team1.getName() + " vs. " + team2.getName() ;
	}
	
	/**
	 * Finalizes the score
	 */
	public void finalize() {
	    finalized = true;
	}
	
	/**
	 * Checks if the score has been finalized
	 */
	public boolean isFinal() {
	    return finalized;
	}


}
