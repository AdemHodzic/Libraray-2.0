package com.adem.View;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ErrorView {

	private static String musicFile = "images/click.wav";     
	private static Media sound = new Media(new File(musicFile).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	
	public static void display(String message) {
		ErrorView view = new ErrorView();
		Stage window = new Stage();

		Label label = new Label(message);
		GridPane.setConstraints(label, 0, 0);
		
		Button btn = new Button("OK");
		GridPane.setConstraints(btn,0, 1);
		btn.setOnAction(e -> {
			mediaPlayer.play();
			window.close();
		});
		
		Pane layout = new GridPane();
		layout.getChildren().addAll(label,btn);
		layout.setId("Layout");
		layout.setPadding(new Insets(20,20,20,20));
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().add(layout);
		finalLayout.setId("FinalLayout");
		
		Scene scene = new Scene(finalLayout,350,200);
		scene.getStylesheets().add(view.getClass().getResource("error.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

}
