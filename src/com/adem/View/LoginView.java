package com.adem.View;

import java.util.List;

import com.adem.Controller.Controller;
import com.adem.Database.Database;
import com.adem.Entities.User;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LoginView {
	
	private static int WIDTH=768;
	private static int HEIGHT=432;
	
	
	public static Scene initialzeStartupView() {
		LoginView view = new LoginView();
		Pane layout = new GridPane();
		Label libaryHeading = new Label("Libary 2.0");
		GridPane.setConstraints(libaryHeading, 0,0);
		Label usernameLabel = new Label("Username: ");
		Label passwordLabel = new Label("Password: ");
		GridPane.setConstraints(usernameLabel, 0,1);
		GridPane.setConstraints(passwordLabel, 0,2);

		TextField usernameInput = new TextField();
		PasswordField passwordInput = new PasswordField();
		GridPane.setConstraints(usernameInput, 1,1);
		GridPane.setConstraints(passwordInput, 1,2);
		
		Button loginButton = new Button("Login");
		Button registerButton = new Button("Register");
		GridPane.setConstraints(loginButton, 0, 3);
		GridPane.setConstraints(registerButton, 1, 3);
		
		loginButton.setOnAction(e -> login(usernameInput.getText(), passwordInput.getText()));
		registerButton.setOnAction(e -> register(usernameInput.getText(), passwordInput.getText()));
		
		usernameInput.setPromptText("username");
		passwordInput.setPromptText("password");
		
		layout.getChildren().addAll(libaryHeading, usernameLabel, passwordLabel, usernameInput, passwordInput, loginButton, registerButton);
		layout.setId("InnerLoginLayout");
		layout.setPadding(new Insets(12,10,12,10));
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().add(layout);
		finalLayout.setId("LoginLayout");
		finalLayout.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				loginButton.fire();
				e.consume();
			}
		});
		
		Scene scene = new Scene(finalLayout, WIDTH,HEIGHT);
		scene.getStylesheets().add(view.getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
	private static void login(String username, String password) {
		List<User> list = Database.readAllUsers();
		for(User user : list) {
			if(user.getUserName().equals(username) && user.getPassword().equals(password)){
				Controller.loginSuccess(user);
				return;
				}
		}
		Controller.error("Fail");
	}
	
	private static void register(String username, String password) {
		List<User> list = Database.readAllUsers();
		for(User user:list) {
			if(user.getUserName().equals(username)) {
				Controller.error("Username already exists!");
				return;
			}
		}
		Database.addUser(username, password);
	}
	

	
}
