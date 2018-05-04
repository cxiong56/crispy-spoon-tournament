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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;

	/**
     * First scene to draw up when javafx launches
     */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
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
		primaryStage.setScene(new Scene(scroll));
		primaryStage.setTitle("Tournament Bracket");
		primaryStage.show();
		primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
	}

	public static void main(String[] args) throws IOException {
		// read input file
		Scanner in = new Scanner(System.in);
		System.out.println("What is the filename? ");
		String input = in.nextLine();
		File inputFile = new File(input);
		HeapBracket.setupHeapBracket(inputFile);

		launch(args);

	}
}

