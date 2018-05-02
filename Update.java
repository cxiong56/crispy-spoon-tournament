package application;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Update {

	public static Node drawGUI() {
		GridPane root = new GridPane();
		int leftCol = 0;
		int rightCol = HeapBracket.numRounds() * 2 + 1;
		int padding = 0;
		for (int curRound = HeapBracket
				.numRounds(); curRound > 0; curRound--, leftCol++, rightCol--, padding += HeapBracket
						.numInRound(curRound) / 4) {
			List<Match> round = HeapBracket.getRound(curRound);
			int left = 0;
			int right = round.size() / 2;
			for (; left < right; left++)
				root.add(makeMatch(round.get(left)), leftCol, left + padding);
			for (; right < round.size(); right++)
				root.add(makeMatch(round.get(right)), rightCol, right - left + padding);
		}
		return root;
	}

	private static Node makeMatch(Match m) {
		if (m != null)
			return makeMatch(m.getTeam1(), m.getTeam2(), m);
		return makeMatch("", "", m);
	}

	private static Node makeMatch(Team team1, Team team2, Match m) {
		return makeMatch(team1 != null ? team1.getName() : "", team2 != null ? team2.getName() : "", m);
	}

	private static Node makeMatch(String t1, String t2, Match m) {
		VBox match = new VBox();
		Button addScore = new Button("Add Score" + m.getNum());

		addScore.setOnAction(e -> {
			// need to be able to get match number here to change the scores
			boolean result = finalScoreEdit(m);
			if (result == true)// final score recorded
				addScore.setDisable(true);
		});
		match.getChildren().addAll(makeTeam(t1, m.getScore1()), makeTeam(t2, m.getScore2()));
		if (t1 != "" || t2 != "") {
			match.getChildren().add(addScore);
		} else {
			match.getChildren().add(new Label("                        "));// to
																			// give
																			// the
																			// empty
																			// bracket
																			// a
																			// body
		}
		match.setSpacing(10);
		match.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return match;
	}

	private static Node makeTeam(String teamName, int score) {
		HBox team = new HBox();
		Label scores = new Label("   ");
		if (teamName != "") {
			scores.setText("\t\t(" + score + ")");
		}

		team.getChildren().addAll(new Label(teamName), scores);
		return team;
	}

	private static boolean answer1; // cant seem to find a simpler solution to
									// this

	protected static boolean finalScoreEdit(Match m) {

		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Match " + m.getNum());
		stage.setMinWidth(300);
		stage.setMinHeight(250);
		GridPane trueLayout = new GridPane();
		trueLayout.setPadding(new Insets(10, 10, 10, 10));
		trueLayout.setVgap(8);
		trueLayout.setHgap(10);
		if (m.getTeam1() == null || m.getTeam2() == null) {// no match
			Label error = new Label("A Match is not set yet.\nPlease try again later.");
			Button close = new Button("Close");

			close.setOnAction(e -> stage.close());

			VBox layout = new VBox(10);
			layout.getChildren().addAll(error, close);
			layout.setAlignment(Pos.CENTER);
			GridPane.setConstraints(layout, 0, 0);
			trueLayout.getChildren().add(layout);

		} else {
			// TeamA
			Label teamA = new Label(m.getTeam1().getName());
			TextField score1 = new TextField("0");
			GridPane.setConstraints(teamA, 0, 0);
			GridPane.setConstraints(score1, 1, 0);

			// TeamB
			Label teamB = new Label(m.getTeam2().getName());
			TextField score2 = new TextField("0");
			GridPane.setConstraints(teamB, 0, 1);
			GridPane.setConstraints(score2, 1, 1);

			// Confirm Button
			Button confirm = new Button("Final Score");
			confirm.setOnAction(e -> {
				if ((score1.getText() == null || score1.getText().isEmpty())
						|| (score2.getText() == null || score2.getText().isEmpty())) {
					errorAlert("Error", "Please fill in the scores for both teams");
				}

				int s1 = 0, s2 = 0;
				try {
					s1 = Integer.parseInt(score1.getText());
					s2 = Integer.parseInt(score2.getText());
					if (s1 < 0 || s2 < 0) {
						throw new NumberFormatException();
					}

					boolean results = confirmScore(m, s1, s2);
					if (results == true) {
						m.setScore1(s1);
						m.setScore2(s2);
						stage.close();
					}
					answer1 = results;
				} catch (NumberFormatException error) {
					errorAlert(error.getMessage(), "Please enter positive Integers only.");
				}
				//handle ties
				int e1 = 0, e2 = 0;
				try {
					e1 = Integer.parseInt(score1.getText());
					e2 = Integer.parseInt(score2.getText());
					if (e1 == e2) {
						throw new NumberFormatException();
					}

					boolean results = confirmScore(m, s1, s2);
					if (results == true) {
						m.setScore1(s1);
						m.setScore2(s2);
						stage.close();
					}
					answer1 = results;
				} catch (NumberFormatException error) {
					errorAlert(error.getMessage(), "Ties not allowed. Please enter vaild scores.");
				}
				
				m.setScore1(s1);
				m.setScore2(s2);
				Main.update();
			});

			GridPane.setConstraints(confirm, 1, 2);

			trueLayout.getChildren().addAll(teamA, score1, teamB, score2, confirm);
		}
		Scene scene = new Scene(trueLayout);
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> answer1 = false);// For when user close
														// through "x" button
		stage.showAndWait();

		return answer1;
	}

	private static boolean answer2;

	private static boolean confirmScore(Match m, int s1, int s2) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Confirm Final Score");
		stage.setMinWidth(300);
		stage.setMinHeight(250);
		BorderPane trueLayout = new BorderPane();

		String matchInfo = m.getTeam1().getName() + "\t\t(" + s1 + ")\n" + m.getTeam2().getName() + "\t\t(" + s2 + ")";
		Label label = new Label(matchInfo);
		Label label2 = new Label("Are you sure?");
		Label label3 = new Label("Scores entered are final and immutable.");

		// yes no button

		Button yes = new Button("Yes");
		Button no = new Button("No");

		yes.setOnAction(e -> {
			answer2 = true;
			stage.close();
		});
		no.setOnAction(e -> {
			answer2 = false;
			stage.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, label2, label3);
		layout.setAlignment(Pos.CENTER);
		HBox yesNO = new HBox(10);
		yesNO.setAlignment(Pos.TOP_CENTER);
		yesNO.getChildren().addAll(yes, no);
		trueLayout.setCenter(layout);
		trueLayout.setBottom(yesNO);

		Scene scene = new Scene(trueLayout);
		stage.setScene(scene);
		stage.setOnCloseRequest(e -> answer2 = false);
		stage.showAndWait();
		return answer2;
	}

	private static void errorAlert(String title, String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);// set it so that user
														// only interacts with
														// this window
		window.setTitle(title);
		window.setMinWidth(250);

		Label label = new Label(message);
		Button close = new Button("Close window");
		close.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 300, 100);
		window.setScene(scene);
		java.awt.Toolkit.getDefaultToolkit().beep();// alert sound
		window.showAndWait();// display and wait until the window is closed
	}
}
