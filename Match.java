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
 * @author Henry
 *
 */
public class Match {
	private Team team1;
	private int score1;
	private Team team2;
	private int score2;
	private int matchNum;
	private boolean finalized = false;

	/**
	 * Creates a new match between two teams with the match num
	 * 
	 * @param team1
	 * @param team2
	 * @param num - what match this is
	 */
	public Match(Team team1, Team team2, int num) {
		this.team1 = team1;
		this.team2 = team2;
		this.matchNum = num;
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
	 * @return - returns team1
	 */
	public Team getTeam1() {
		return team1;
	}
	
	/**
	 * Sets team1 to team t
	 * @param t - provided team
	 */
	public void setTeam1(Team t) {
		team1 = t;
	}
	
	/**
	 * Sets score1 to score s
	 * @param s
	 */
	public void setScore1(int s) {
	    score1 = s;
	}
	
	/**
	 * @return - returns the score for team 1
	 */
	public int getScore1() {
	    return score1;
	}
	
	/**
	 * @return - returns team2
	 */
	public Team getTeam2() {
		return team2;
	}
	
	/**
	 * Sets team2 to team t
	 * @param t - provided team
	 */
	public void setTeam2(Team t) {
		team2 = t;
	}
	
	/**
	 * Sets score2 to score s
	 * @param s
	 */
	public void setScore2(int s) {
        score2 = s;
    }
	
	/**
	 * Sets score2 to score s
	 * @param s
	 */
	public int getScore2() {
	    return score2;
	}
	
	/**
	 * 
	 * @return - the match number of this match
	 */
	public int getNum() {
	    return matchNum;
	}

	/**
	 * Compares the scores of the two teams if finalized and returns the team with
	 * the highest score as the winner. IF the score is the same randomly chooses a
	 * winner.
	 * 
	 * @return
	 */
	public Team getWinner() {
		if (score1 > score2)
			return team1;
		else if (score2 > score1)
			return team2;
		else
			return isFinal() ? team1 : null;//handle ties
	}
	
	public Team getLoser() {
		if (score1 < score2)
			return team1;
		else if (score2 < score1)
			return team2;
		else
			return isFinal() ? team2: null;//handle ties
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
	 * @return
	 */
	public boolean isFinal() {
	    return finalized;
	}


}
