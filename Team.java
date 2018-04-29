package application;

public class Team {
	protected String name;
	/**
	 * @param name
	 * @param score
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
	 
	 public String toString() {
		 return getName();
	 }
}
