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
 * This object is used to store basic team info
 * such as name, seed, and if its score is finalized
 * @author Henry
 *
 */
public class Team {
	private String name;
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
	
	/**
	 * 
	 * @return the see seed postion of the team
	 */
	public int getSeed() {
		return seed;
	}
	
	/**
	 * Returns the result of getName() which is the team name
	 */
	public String toString() {
		return getName();
	}
}
