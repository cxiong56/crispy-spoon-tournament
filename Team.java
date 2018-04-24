package application;

public class Team {
	protected String name;
	protected int score;
	protected boolean finalized; //idk if we want this but it could be used to see if 
				    //the score is saved vs initialized to 0;
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
		this.score = score;
	}

	/**
	 * Returns the teams current score
	 * 
	 * @return
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
	  * @return
	  */
	 public boolean isFinal() {
		 return finalized;
	 }
}
