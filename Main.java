
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
			int teamNum = 16;
			int max = (int) (Math.log(teamNum) / Math.log(2)) * 2;
			int min = 0;
			for (int tn = teamNum; tn >= 2; min++, max--, offset += tn / 4, tn /= 2) {
				for (int i = 0; i < tn; i++) {
					root.add(makeTeam("LTeam" + i), min, i + offset);
					root.add(makeTeam("RTeam" + i), max, i + offset);
				}
			}
			root.add(makeTeam("Champ"), min, teamNum / 2 - 1);
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Tournament Bracket");
			primaryStage.show();

			// BorderPane root = new BorderPane();
			// for (int teamNum = 2; teamNum <= 8; teamNum *= 2) {
			// VBox right = new VBox();
			// for (int i = 0; i < teamNum; i++)
			// right.getChildren().add(makeTeam("RTeam" + i));
			// VBox left = new VBox();
			// for (int i = 0; i < teamNum; i++)
			// left.getChildren().add(makeTeam("LTeam" + i));
			// root.setRight(right);
			// root.setLeft(left);
			// BorderPane newRoot = new BorderPane();
			// newRoot.setCenter(root);
			// root = newRoot;
			// }

			// primaryStage.setScene(scene1);
			// primaryStage.setTitle("Tournament Bracket");
			// primaryStage.show();
			// BorderPane root = new BorderPane();
			// Scene scene = new Scene(root,600,600,Color.BLACK);
			// //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// //scene.
			// //scene.
			// primaryStage.setTitle("Bracket");
			// primaryStage.setScene(scene);
			// primaryStage.show();
			// //create controls
			// BorderPane paneOuterMost = new BorderPane();
			//
			// Label header = new Label ("Welcome to Your Tournament Bracket!");
			// Rectangle r = new Rectangle(20,20,250,250);
			// r.setFill(Color.BLUE);
			// //root.getChildren().add(r);
			// Label teamName = new Label("Team1");
			// Label teamName2 = new Label("Team2");
			//
			// TextField enterScores = new TextField();
			// enterScores.setPromptText("Final Score");
			// TextField enterScores2 = new TextField();
			// enterScores2.setPromptText("Final Score");
			//
			// HBox teamScore1 = new HBox();
			// teamScore1.getChildren().addAll(teamName, enterScores);
			// HBox teamScore2 = new HBox();
			// teamScore2.getChildren().addAll(teamName2, enterScores2);
			//
			// Button submitButton = new Button("Submit");
			// Button submitButton2 = new Button("Submit");
			//
			// VBox matchBox = new VBox();
			// matchBox.getChildren().addAll(teamScore1, submitButton);
			// VBox matchBox2 = new VBox();
			// matchBox2.getChildren().addAll(teamScore2, submitButton2);
			//
			// //paneOuterMost.getChildren().addAll(matchBox, header);
			// paneOuterMost.setTop(header);
			// paneOuterMost.setLeft(matchBox);
			// //center
			// paneOuterMost.setRight(matchBox2);
			// //bottom
			//
			// //set properties on controls
			// //add functionality on controls
			// //add controls to panels
			// //root.getChildren().addAll(paneOuterMost);
			//// root.setLeft(r);
			//// root.setRight(r);
			// //root.setTop(header);
			// root.setCenter(paneOuterMost);

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
