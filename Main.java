
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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
			int max = 7;
			int min = 0;
			int offset = 0;
			for (int teamNum = 8; teamNum >= 2; teamNum /= 2) {
				for (int i = 0; i < teamNum; i++) {
					root.add(makeTeam("RTeam" + i), max, i + offset);
					root.add(makeTeam("LTeam" + i), min, i + offset);
				}
				min++;
				max--;
				offset += teamNum / 4;
			}
			
//			BorderPane root = new BorderPane();
//			for (int teamNum = 2; teamNum <= 8; teamNum *= 2) {
//				VBox right = new VBox();
//				for (int i = 0; i < teamNum; i++)
//					right.getChildren().add(makeTeam("RTeam" + i));
//				VBox left = new VBox();
//				for (int i = 0; i < teamNum; i++)
//					left.getChildren().add(makeTeam("LTeam" + i));
//				root.setRight(right);
//				root.setLeft(left);
//				BorderPane newRoot = new BorderPane();
//				newRoot.setCenter(root);
//				root = newRoot;
//			}
			

//			root.setCenter(new BorderPane());
//			root.setRight(new Label("Sup"));
//			root.setLeft(new Label("BRos"));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
			
//			primaryStage.setScene(scene1);
//			primaryStage.setTitle("Tournament Bracket");	
//			primaryStage.show();
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,600,600,Color.BLACK);
//			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			//scene.
//			//scene.
//			primaryStage.setTitle("Bracket");
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			//create controls
//			BorderPane paneOuterMost = new BorderPane();
//			
//			Label header = new Label ("Welcome to Your Tournament Bracket!");
//			Rectangle r = new Rectangle(20,20,250,250);
//		       r.setFill(Color.BLUE);
//		       //root.getChildren().add(r);
//		    Label teamName = new Label("Team1");
//		    Label teamName2 = new Label("Team2");
//		    
//		    TextField enterScores = new TextField();
//		    		enterScores.setPromptText("Final Score");
//		    	TextField enterScores2 = new TextField();
//		    		enterScores2.setPromptText("Final Score");
//		    		
//		    HBox teamScore1 = new HBox();
//		    		teamScore1.getChildren().addAll(teamName, enterScores);
//		    HBox teamScore2 = new HBox();
//		    		teamScore2.getChildren().addAll(teamName2, enterScores2);
//		    		
//		    	Button submitButton = new Button("Submit");
//		    	Button submitButton2 = new Button("Submit");
//		    	
//		    VBox matchBox = new VBox();
//		    		matchBox.getChildren().addAll(teamScore1, submitButton);
//		    	VBox matchBox2 = new VBox();
//		    		matchBox2.getChildren().addAll(teamScore2, submitButton2);
//		    		
//		    	//paneOuterMost.getChildren().addAll(matchBox, header);
//		    	paneOuterMost.setTop(header);
//		    paneOuterMost.setLeft(matchBox);
//		    //center
//		    paneOuterMost.setRight(matchBox2);
//		    //bottom
//		   
//			//set properties on controls
//			//add functionality on controls
//			//add controls to panels
//		    //root.getChildren().addAll(paneOuterMost);
////		    root.setLeft(r);
////			root.setRight(r);
//			//root.setTop(header);
//			root.setCenter(paneOuterMost);
			
		} catch(Exception e) {
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

	public static void main(String[] args) {
		launch(args);
	}
}
