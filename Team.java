package application;

public class Team {
	private String name;
	private int score;
	private int seed;

	/**
	 * @param name
	 * @param score
	 */
	public Team(String name, int seed, int score) {
		this.name = name;
		this.score = score;
		//makeTeam(name);
	}

	/**
	 * @param name
	 */
	public Team(String name, int seed) {
		this.name = name;
		this.seed = seed;
		this.score = 0;
	}
	/**
	 * Sets the team's original seed
	 * 
	 * @param seed
	 */
	public void setSeed(int seed){
		this.seed = seed;
	}
	
	/**
	 * Returns the teams seed
	 * 
	 * @return seed
	 */
	public int getSeed(){
		return seed;
	}
	/**
	 * Sets the team's current score
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns the teams current score
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;

	}

	/**
	 * Changes the name
	 * 
	 * @param score
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;

	}
}
