package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main - Main class driver application
 * 
 * @author Andrew P. Albanese
 * 
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductInfoGUI.fxml"));
			Parent root = (Parent)loader.load();
			ProductComparisonController controller = loader.getController();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        controller.initialize();
			
	        primaryStage.setTitle("Wal Mart Product Search");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		launch(args);
	}
}
