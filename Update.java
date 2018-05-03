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
		int count = 1;
		Label title = new Label();
		title.setText("Welcome to Your Bracket");
		title.setTextFill(Color.web("#0076a3"));
		title.setStyle("-fx-font-weight: bold");
		root.add(title, 0, 0);
		for (int curRound = HeapBracket
				.numRounds(); curRound > 0; curRound--, leftCol++, rightCol--, padding += HeapBracket
						.numInRound(curRound) / 4) {
			Label label1 = new Label();
			Label label2 = new Label();
			if(curRound == 1) {
				label2.setText("Final Game"); //Championship Game
				root.add(label2, rightCol, 5);
			}
			else {
				label1.setText("Round "+count);
				label2.setText("Round "+count);
				root.add(label1, leftCol, 5);
				root.add(label2, rightCol, 5);
			}
			List<Match> round = HeapBracket.getRound(curRound);
			int left = 0;
			int right = round.size() / 2;
			
			//root.add(child, columnIndex, rowIndex);
			for (; left < right; left++)
				root.add(makeMatch(round.get(left)), leftCol, left + padding +10);
			for (; right < round.size(); right++)
				root.add(makeMatch(round.get(right)), rightCol, right - left + padding +10);
			count++;
		}
		return root;
	}

	private static Node makeMatch(Match m) {
		if (m != null)
			return makeMatch(m.getTeam1(), m.getTeam2(), m);
		return makeMatch(null, null, m);
	}

	private static Node makeMatch(Team t1, Team t2, Match m) {
		VBox match = new VBox();
		Button addScore = new Button("Add Score");

		addScore.setOnAction(e -> {
			// need to be able to get match number here to change the scores
			boolean result = finalScoreEdit(m);
			if (result == true)// final score recorded
				addScore.setDisable(true);
		});
		match.getChildren().addAll(makeTeam(t1, m.getScore1()), makeTeam(t2, m.getScore2()));
		if (t1 != null && t2 != null) {//button only appears if there is at least a team
			match.getChildren().add(addScore);
		} else {
		    //to give the empty bracket a body
			match.getChildren().add(new Label("                        "));
		}
		
		addScore.setDisable(m.isFinal());
		match.setSpacing(10);
		match.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return match;
	}

	private static Node makeTeam(Team team, int score) {
		if (team == null)
			return new HBox();
		HBox teamBox = new HBox();
		Label scores = new Label("   ");
		if (team.getName() != "") {
			scores.setText("\t\t(" + score + ")");
		}

		teamBox.getChildren().addAll(new Label(team.getSeed() + ". " + team.getName()), scores);
		return teamBox;
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
					}else if (s1 == s2) {
					    throw new IllegalArgumentException();
					}

					boolean results = confirmScore(m, s1, s2);
					if (results == true) {
						m.setScore1(s1);
						m.setScore2(s2);
						m.finalize();
		                Main.update();
						stage.close();
					}
					answer1 = results;
				} catch (NumberFormatException error) {
					errorAlert("NumberFormatException", "Please enter positive Integers only.");
				} catch (IllegalArgumentException error2) {
				    errorAlert("IllegalArgumentException", "Ties not allowed. Please enter valid scores.");
				}
				
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
