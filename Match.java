package application;

public class Match {
	private Team team1;
	private int score1;
	private Team team2;
	private int score2;
	private int matchNum;

	/**
	 * Creates a new match between two teams
	 * 
	 * @param team1
	 * @param team2
	 * @param num
	 */
	public Match(Team team1, Team team2, int num) {
		this.team1 = team1;
		this.team2 = team2;
		this.matchNum = num;
	}
	
	public Match(String team1, String team2, int num) {
		this.team1 = new Team(team1);
		this.team2 = new Team(team2);
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
			return team1;
	}
	
	public String toString() {
		return team1 + " vs. " + team2;
	}

}
