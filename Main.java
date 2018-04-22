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
						BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,600,Color.BLACK);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//scene.
			//scene.
			primaryStage.setTitle("Bracket");
			primaryStage.setScene(scene);
			primaryStage.show();
			//create controls
			BorderPane paneOuterMost = new BorderPane();
			
			Label header = new Label ("Welcome to Your Tournament Bracket!");
			Rectangle r = new Rectangle(20,20,250,250);
		       r.setFill(Color.BLUE);
		       //root.getChildren().add(r);
		    Label teamName = new Label("Team1");
		    Label teamName2 = new Label("Team2");
		    
		    TextField enterScores = new TextField();
		    		enterScores.setPromptText("Final Score");
		    	TextField enterScores2 = new TextField();
		    		enterScores2.setPromptText("Final Score");
		    		
		    HBox teamScore1 = new HBox();
		    		teamScore1.getChildren().addAll(teamName, enterScores);
		    HBox teamScore2 = new HBox();
		    		teamScore2.getChildren().addAll(teamName2, enterScores2);
		    		
		    	Button submitButton = new Button("Submit");
		    	Button submitButton2 = new Button("Submit");
		    	
		    VBox matchBox = new VBox();
		    		matchBox.getChildren().addAll(teamScore1, submitButton);
		    	VBox matchBox2 = new VBox();
		    		matchBox2.getChildren().addAll(teamScore2, submitButton2);
		    		
		    	//paneOuterMost.getChildren().addAll(matchBox, header);
		    	paneOuterMost.setTop(header);
		    paneOuterMost.setLeft(matchBox);
		    //center
		    paneOuterMost.setRight(matchBox2);
		    //bottom
		   
			//set properties on controls
			//add functionality on controls
			//add controls to panels
		    //root.getChildren().addAll(paneOuterMost);
//		    root.setLeft(r);
//			root.setRight(r);
			//root.setTop(header);
			root.setCenter(paneOuterMost);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
