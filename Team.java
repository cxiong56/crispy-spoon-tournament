package application;

public class Team {
	protected String name;
	protected int score;

	/**
	 * @param name
	 * @param score
	 */
	public Team(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * @param name
	 */
	public Team(String name) {
		this.name = name;
		this.score = 0;
	}

	/**
	 * Sets the team's current score
	 * 
	 * @param score
	 */
	public void setScore(int score) {

	}

	/**
	 * Returns the teams current score
	 * 
	 * @return
	 */
	public int getScore() {
		return 0;

	}

	/**
	 * Changes the name
	 * 
	 * @param score
	 */
	public void setName(int score) {

	}

	/**
	 * Returns the team name
	 * 
	 * @return
	 */
	public String getName() {
		return null;

	}
}
