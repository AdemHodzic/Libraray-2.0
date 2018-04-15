package application;
	

import com.adem.Controller.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	
	private static Stage window;
	@Override
	public void start(Stage stage) {

		try {
			window = stage;
 			Scene scene = Controller.start();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.setTitle("Library");
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateScene(Scene scene) {
		window.setScene(scene);
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}
