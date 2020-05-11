/*
 * CSCV335 
 * Final Capstone
 * 
 * Erik Ibarra Hurtado
 * Samuel Bryant
 * Leonardo Loureiro
 * 
 * Sunday, May 10, 2020
 * 
 * Main.java
 * 
 * This is the starting point of the game.
 * RulesView is displayed first and then loads the GameView when a button is clicked.
 * 
 * */
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("RulesView.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void stop() {
		System.out.println("stop has been called");
		System.exit(0);
	}
	
}
