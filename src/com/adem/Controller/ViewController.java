package com.adem.Controller;


import com.adem.Entities.User;
import com.adem.View.ErrorView;
import com.adem.View.LibaryView;
import com.adem.View.LoginView;

import javafx.scene.Scene;

public class ViewController {
	
	
	public static Scene initialzeStartupView() {
		return LoginView.initialzeStartupView();
	}
	
	public static Scene loginSuccess(User user) {
		return LibaryView.display(user);
	}
	
	public static void error(String message) {
		ErrorView.display(message);
	}

	public static void userDetails(User user) {
		PopupViewController.userDetails(user);
	}
	
	public static void takeBook(User user) {
		PopupViewController.takeBook(user);
	}

	public static void returnBook(User user) {
		PopupViewController.returnBook(user);
	}

	public static void donateBook() {
		PopupViewController.donateBook();
	}
}
