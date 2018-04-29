package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

	// debug = "on";
	private static HeapBracket heapBracket;
	private static Iterable<Match> allMatches;
	Scene scene1, scene2;

	@Override
	public void start(Stage primaryStage) {
		try {
			ScrollPane scroll = new ScrollPane();

			GridPane root = new GridPane();
			int leftCol = 0;
			int rightCol = heapBracket.numRounds() + 1;
			for (int curRound = heapBracket.numRounds(); curRound > 0; curRound--, leftCol++, rightCol--) {
				int i = 0;
				for (Match m : heapBracket.getRound(curRound)) {
					root.add(makeMatch(m), leftCol, i);
					i++;
				}
			}
//			int offset = 0;
//			int teamNum = 0;//heapBracket.numTeams;
//			int max = (int) (Math.log(teamNum) / Math.log(2)) * 2;
//			int min = 0;
//			int round = 1;
//			for (int tn = teamNum; tn >= 2; min++, max--, offset += tn / 4, tn /= 2, round++) {
				
				
//				for (int i = (tn - 1); i > heapBracket.bracketArray.length; i++) {
//					String team1 = heapBracket.bracketArray[i].getName();
//					String team2 = heapBracket.bracketArray[i + 1].getName();
//					root.add(makeMatch(team1, team2), min, i + offset);
//					String team3 = heapBracket.bracketArray[i].getName();
//					String team4 = heapBracket.bracketArray[i + 1].getName();
//					root.add(makeMatch(team3, team4), max, i + offset);
//				}

				// primaryStage.setTitle("Tentative Tournament Bracket");
				// BorderPane root = new BorderPane();
				//
				// int count = 0;
				// for (int teamNum = 2; teamNum <= 8; teamNum *= 2) {
				// VBox oneColR = new VBox();
				// VBox oneColL = new VBox();
				//
				// int countR = 0;
				// int countL = 0;
				// for (int i = 0; i < teamNum / 2; i++) {
				// final int match = i+1;
				// final int teami = count;
				// Button buttonR = new Button("Score Entry");
				// buttonR.setOnAction(e -> scoreInput(match,"RTeam " +
				// teami,"RTeam " + teami + 1));
				// VBox right = new VBox();
				// HBox rightH = new HBox();
				// // rightH.getBorder().getInsets().
				// rightH.setSpacing(10);
				// rightH.setStyle("-fx-padding: 10;" + "-fx-border-style: solid
				// inside;" + "-fx-border-width: 5;"
				// + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" +
				// "-fx-border-color: blue;");
				//
				// right.getChildren().addAll(makeTeam("RTeam " + countR),
				// makeTeam("RTeam " + (countR + 1)), buttonR);
				// rightH.getChildren().add(right);
				// countR = countR +2;
				// oneColR.getChildren().add(rightH);
				// }
				// // right.getChildren().add(makeTeam("RTeam" + i));
				// // rightH.getChildren().add(right);
				//
				// for (int i = 0; i < teamNum / 2; i++) {
				// HBox leftH = new HBox();
				// VBox left = new VBox();
				//
				// final int match = i+1;
				// final int teami = count;
				// Button buttonL = new Button("Score Entry");
				// buttonL.setOnAction(e -> scoreInput(match,"LTeam " +
				// teami,"LTeam " + teami + 1));
				//
				// leftH.setSpacing(10);
				// leftH.setStyle("-fx-padding: 10;" + "-fx-border-style: solid
				// inside;" + "-fx-border-width: 5;"
				// + "-fx-border-insets: 5;" + "-fx-border-radius: 5;" +
				// "-fx-border-color: red;");
				// left.getChildren().addAll(makeTeam("LTeam " + countL),
				// makeTeam("LTeam " + (countL+1)), buttonL);
				// leftH.getChildren().add(left);
				// countL = countL +2;
				// oneColL.getChildren().add(leftH);
				// }
				//
				// root.setRight(oneColR);
				// root.setLeft(oneColL);
				//
				// BorderPane newRoot = new BorderPane();
				// newRoot.setCenter(root);
				// root = newRoot;
			
			//root.add(makeMatch("ChampA", "ChampB"), min, teamNum / 2 - 1);
			scroll.setContent(root);
			primaryStage.setScene(new Scene(scroll));
			primaryStage.setHeight(800);
			primaryStage.setWidth(1250);
			primaryStage.setTitle("Tournament Bracket");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Node makeTeam(String string) {
		VBox team = new VBox();
		TextField enterScores = new TextField();
		enterScores.setPromptText("Final Score");
		team.getChildren().addAll(new Label(string), enterScores);
		return team;
	}
	
	private Node makeMatch(Match m) {
		if (m != null)
			return makeMatch(m.team1(), m.team2());
		return makeMatch("", "");
	}

	private Node makeMatch(Team team1, Team team2) {
		return makeMatch(team1.toString(), team2.toString());
	}

	protected Node makeMatch(String t1, String t2) {
		VBox match = new VBox();
		Button addScore = new Button("Edit Score");
		addScore.setOnAction(e -> scoreInput(0, t1, t2));
		match.getChildren().addAll(makeTeam(t1), makeTeam(t2), addScore);
		match.setSpacing(10);
		match.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return match;
	}

	private int[] scoreInput(int i, String team1, String team2) {// the input
																	// parameter
																	// could be
																	// match
																	// object
		Stage stage = new Stage();
		stage.setTitle("Match " + i + ": " + team1 + " vs. " + team2);
		int[] score = new int[2];

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		// TeamA
		Label teamA = new Label(team1);
		TextField score1 = new TextField("0");
		GridPane.setConstraints(teamA, 0, 0);
		GridPane.setConstraints(score1, 1, 0);

		// TeamB
		Label teamB = new Label(team2);
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

	public static void main(String[] args) throws IOException {
		// read input file
		Scanner in = new Scanner(System.in);
		System.out.println("What is the filename? ");
		String input = in.nextLine();
		File inputFile = new File(input);
		heapBracket = new HeapBracket(inputFile);
		//Team[] teamArray = heapBracket.bracketArray;
		// add all of the matches and team to the bracket
		//allMatches = heapBracket.getAllMatches();

		launch(args);

		// launch(args);
	}
}
