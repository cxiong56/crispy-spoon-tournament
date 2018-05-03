package application;

public class Team {
	private String name;
	private int score;
	private int seed;
	private boolean finalized; 

	/**
	 * @param name
	 * @param score
	 */
	public Team(String name, int seed) {
		this.name = name;
		this.seed = seed;
	}

	/**
	 * @param name
	 */
	public Team(String name) {
		this.name = name;
		this.score = 0;
	}

	/**
	 * Returns the team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Finalizes the score so it can be compared
	 * 
	 * @return
	 */
	public void finalize() {
		finalized = true;
	}

	/**
	 * Used to communicate that the score has been set and finalized
	 * 
	 * @return
	 */
	public boolean isFinal() {
		return finalized;
	}

	public int getSeed() {
		return seed;
	}
	
	public String toString() {
		return getName();
	}
}
