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
 * such as name and seed position
 */
public class Team {
	private String name;
	private int seed;

	/**
	 * Constructs a new Team object
	 * 
	 * @param name - the name of the team
	 * @param seed - the seed position of the team
	 */
	public Team(String name, int seed) {
		this.name = name;
		this.seed = seed;
	}

	/**
	 * Returns the team name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the seed position of the team
	 */
	public int getSeed() {
		return seed;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
