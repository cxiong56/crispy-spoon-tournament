package application;

public class Match {
	protected Team team1;
	protected Team team2;
	protected int matchNum;

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
		if (team1.getScore() > team2.getScore()) {
			return team1;
		} else if (team1.getScore() == team2.getScore()) {
			// add random num generator

			return team1;
		} else {
			return team2;
		}

	}

	/**
	 * inputs scores into teams
	 * 
	 * @param team1s
	 * @param team2s
	 */
	public void setScores(int team1s, int team2s) {
		team1.setScore(team1s);
		team2.setScore(team2s);
	}

}
