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

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;

	/**
     * First scene to draw up when javafx launches
     */
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(Update.drawGUI());
		primaryStage.setScene(new Scene(scroll));
		primaryStage.setTitle("Tournament Bracket");
		primaryStage.show();
		
		primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
	}
	
	/**
	 * update() updates the heapBracket, then draws up a new GUI
	 * then sets it to the new and updated scene
	 */
	static void update() {
		HeapBracket.update();
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(Update.drawGUI());
		stage.setScene(new Scene(scroll));
		stage.setTitle("Tournament Bracket");
		stage.show();
		stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
	}

	public static void main(String[] args) throws IOException {
		// read input file
		File inputFile = new File(args[0]);
		HeapBracket.setupHeapBracket(inputFile);

		launch(args);

	}
}

