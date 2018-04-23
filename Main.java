
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

	Scene scene1, scene2;

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			int offset = 0;
			int teamNum = 8;
			int max = (int) (Math.log(teamNum) / Math.log(2)) * 2;
			int min = 0;
			for (int tn = teamNum; tn >= 2; min++, max--, offset += tn / 4, tn /= 2) {
				for (int i = 0; i < tn; i++) {
					root.add(makeMatch("ALTeam" + i, "BLTeam" + i), min, i + offset);
					root.add(makeMatch("ARTeam" + i, "BRTeam" + i), max, i + offset);
				}
			}
			root.add(makeMatch("ChampA", "ChampB"), min, teamNum / 2 - 1);
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Tournament Bracket");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Node makeTeam(String string) {
		VBox team = new VBox();
		TextField enterScores = new TextField();
		enterScores.setPromptText("Final Score");
		team.getChildren().addAll(new Label(string), enterScores);
		return team;
	}

	private Node makeMatch(String t1, String t2) {
		VBox match = new VBox();
		match.getChildren().addAll(makeTeam(t1), makeTeam(t2));
		match.setSpacing(10);
		match.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return match;
	}

	private int[] scoreInput() {// the input parameter could be match object
		Stage stage = new Stage();
		stage.setTitle("Enter Score or TeamA vs TeamB");
		int[] score = new int[2];

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		// TeamA
		Label teamA = new Label("insert TeamA String");
		TextField score1 = new TextField("0");
		GridPane.setConstraints(teamA, 0, 0);
		GridPane.setConstraints(score1, 1, 0);

		// TeamB
		Label teamB = new Label("insert TeamB String");
		TextField score2 = new TextField("0");
		GridPane.setConstraints(teamB, 0, 1);
		GridPane.setConstraints(score2, 1, 1);

		// Confirm Button
		Button confirm = new Button("Add");
		confirm.setOnAction(e -> {
			System.out.println("get text, parse, throw errors, and store in array");
			stage.close();
		});
		GridPane.setConstraints(confirm, 1, 2);

		grid.getChildren().addAll(teamA, score1, teamB, score2, confirm);

		Scene scene = new Scene(grid, 300, 200);
		stage.setScene(scene);
		stage.show();

		return score;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
