package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	
	Scene scene1, scene2;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Label label1 = new Label("Welcome to the Tournament Bracket Maker!");
			HBox box = new HBox();
			box.getChildren().add(label1);
			
			Button btn = new Button("Next");
			btn.setOnAction(e -> primaryStage.setScene(scene2));
			VBox right = new VBox();
			right.getChildren().add(btn);
			
			BorderPane root = new BorderPane();
			root.setTop(box);
			root.setRight(right);
			scene1 = new Scene(root,1000,1000);
			
			Label label2 = new Label("Much Wow");
			HBox box2 = new HBox();
			box2.getChildren().add(label2);
			
			Button btn2 = new Button("Back");
			btn2.setOnAction(e -> primaryStage.setScene(scene1));
			VBox right2 = new VBox();
			right2.getChildren().add(btn2);
			
			
			ListView bracketName = new ListView();
			Label labelBracket = new Label("Added Bracket Name");
			HBox leftFirst = new HBox();
			leftFirst.getChildren().addAll(labelBracket, bracketName);
			
			ListView teamNames = new ListView();
			Label labelTeam = new Label("Added Team Names");
			HBox leftSecond = new HBox();
			leftSecond.getChildren().addAll(labelTeam, teamNames);
			
			VBox left2 = new VBox();
			left2.getChildren().addAll(leftFirst, leftSecond);
			
			BorderPane root2 = new BorderPane();
			root2.setTop(box2);
			root2.setRight(btn2);
			root2.setLeft(left2);
			scene2 = new Scene(root2, 1000, 1000);
			
			primaryStage.setScene(scene1);
			primaryStage.setTitle("Tournament Bracket");	
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
