package com.adem.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ErrorView {

	public static void display(String message) {
		ErrorView view = new ErrorView();
		Stage window = new Stage();

		Label label = new Label(message);
		GridPane.setConstraints(label, 0, 0);
		
		Button btn = new Button("OK");
		GridPane.setConstraints(btn,0, 1);
		btn.setOnAction(e -> window.close());
		
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
