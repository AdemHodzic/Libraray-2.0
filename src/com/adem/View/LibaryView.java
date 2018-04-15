package com.adem.View;

import com.adem.Controller.Controller;
import com.adem.Entities.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LibaryView {
	
	private static int WIDTH=768;
	private static int HEIGHT=432;
	
	
	public static Scene display(User user) {
		Pane layout = new GridPane();
		Label label = new Label("Login successful, hello " + user.getUserName() + "!");
		Button takeBookButton = new Button("Take a Book");
		Button returnBookButton = new Button("Return a Book");
		Button donateBookButton = new Button("Donate a Book");
		Button logoutButton = new Button("Log Out");
		Button userDetailsButton = new Button("User Details");
		
		
		
		takeBookButton.setOnAction(e -> takeBook(user));
		returnBookButton.setOnAction(e -> returnBook(user));
		donateBookButton.setOnAction(e -> donateBook());
		logoutButton.setOnAction(e -> logout());
		userDetailsButton.setOnAction(e -> userDetails(user));
		
		label.setId("Label");
		
		GridPane.setConstraints(label,0,0);
		GridPane.setConstraints(takeBookButton,0,1);
		GridPane.setConstraints(returnBookButton,1,1);
		GridPane.setConstraints(donateBookButton,0,2);
		GridPane.setConstraints(logoutButton,1,2);
		GridPane.setConstraints(userDetailsButton,0,3);
		
		
		
		layout.getChildren().addAll(label,takeBookButton,returnBookButton,donateBookButton,logoutButton,userDetailsButton);
		layout.setId("InnerLayout");
		layout.setPadding(new Insets(10,10,10,10));
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().add(layout);
		finalLayout.setId("LibraryLayout");
		Scene scene = new Scene(finalLayout, WIDTH,HEIGHT);
		LibaryView view = new LibaryView();
		scene.getStylesheets().add(view.getClass().getResource("library.css").toExternalForm());
		return scene;
	}


	public static void takeBook(User user) {
		//System.out.println("Book taken");
		Controller.takeBook(user);
	}
	
	private static void returnBook(User user) {
		//System.out.println("Book returned");
		Controller.returnBook(user);
		
	}
	private static void donateBook() {
		//System.out.println("Book donated");
		Controller.donateBook();
	}
	private static void logout() {
		Controller.logout();
	}
	
	public static void userDetails(User user) {
		Controller.userDetails(user);
	}
}
