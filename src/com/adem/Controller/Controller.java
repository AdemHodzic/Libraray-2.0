package com.adem.Controller;

import com.adem.Entities.User;

import application.Main;
import javafx.scene.Scene;

public class Controller {
	
	public static Scene start() {
		return ViewController.initialzeStartupView();
	}
	
	public static void loginSuccess(User user) {
		Main.updateScene(ViewController.loginSuccess(user));
	}
	
	public static void error(String errorMessage) {
		ViewController.error(errorMessage);
	}
	
	public static void logout() {
		Main.updateScene(start());
	}
	
	public static void userDetails(User user) {
		ViewController.userDetails(user);
	}

	public static void takeBook(User user) {
		ViewController.takeBook(user);
	}

	public static void returnBook(User user) {
		ViewController.returnBook(user);
	}

	public static void donateBook() {
		ViewController.donateBook();
	}

	
}
