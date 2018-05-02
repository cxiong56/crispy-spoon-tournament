package application;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Update {
	
	public static Node drawGUI(HeapBracket heapBracket) {
		 GridPane root = new GridPane();
         int leftCol = 0;
         int rightCol = heapBracket.numRounds() * 2 + 1;
         int padding = 0;
         for (int curRound = heapBracket.numRounds(); curRound > 0; curRound--, leftCol++, rightCol--, padding += heapBracket.numInRound(curRound) / 4) {
             List<Match> round = heapBracket.getRound(curRound);
             int left = 0;
             int right = round.size() / 2;
             for ( ; left < right; left++)
                 root.add(makeMatch(round.get(left)), leftCol, left + padding);
             for ( ; right < round.size(); right++)
                 root.add(makeMatch(round.get(right)), rightCol, right - left + padding);
         }
         return root;
	}
	
	private static Node makeMatch(Match m) {
        if (m != null)
            return makeMatch(m.getTeam1(), m.getTeam2(),m);
        return makeMatch("", "",m);
    }

    private static Node makeMatch(Team team1, Team team2, Match m) {
        return makeMatch(team1 != null ? team1.getName() : "", team2 != null ? team2.getName() : "",m);
    }

    private static Node makeMatch(String t1, String t2, Match m) {
        VBox match = new VBox();
        Button addScore = new Button("Add Score");

        addScore.setOnAction( e -> {
            //need to be able to get match number here to change the scores
            boolean result = Main.finalScoreEdit(m);
            if (result == true)//final score recorded
                addScore.setDisable(true);
        });
        match.getChildren().addAll(makeTeam(t1), makeTeam(t2));
        if (t1 != "" || t2 != "") {
            match.getChildren().add(addScore);
        } else {
            match.getChildren().add(new Label("                        "));//to give the empty bracket a body
        }
        match.setSpacing(10);
        match.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
                        + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        return match;
    }
    
	private static Node makeTeam(String string) {
		HBox team = new HBox();
		Label scores = new Label("   ");
		if (string != "") {
			scores.setText("\t\t(0)");
		}

		team.getChildren().addAll(new Label(string), scores);
		return team;
	}

	/**
	 * A constructor that will apply the users interactions to the HeapBracket,
	 * matches, and teams on updated scores
	 */
	public Update(Match match, int score1, int score2) {

	}

	/**
	 * Using the match object, record final score of the match and stores them into
	 * match object within the heap bracket
	 * 
	 * @param round
	 * @param game
	 * @param t1
	 * @param t2
	 */
	public void storeFinalScore(int round, int game, int t1, int t2) {

	}

	/**
	 * Goes through the matches that needs updating and calls update helper, moving
	 * the winner into the next bracket
	 * 
	 * @param heapBracket
	 */
	public void updateBracket(HeapBracket heapBracket) {

	}

	/**
	 * Compares the score, add an indicator for the match winner in the GUI and
	 * returns the winner
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	public Match updateWinHelper(Match m1, Match m2) {
		return m2;

	}
}
