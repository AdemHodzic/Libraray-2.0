package com.adem.Controller;

import java.io.File;
import java.util.List;

import com.adem.Database.Database;
import com.adem.Entities.Book;
import com.adem.Entities.User;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PopupViewController {
	
	private static String musicFile = "images/click.wav";     
	private static Media sound = new Media(new File(musicFile).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	private static final int WIDTH=768;
	private static final int HEIGHT=288;

	private static Database database = Database.getDatabase();
	private static PopupViewController pvc;
	
	public static void userDetails(User user) {
		pvc = new PopupViewController();
		Stage window = new Stage();

		Pane layout = new GridPane();
		
		Label usernameLabel = new Label("Username: " + user.getUserName());
		Label userNumberLabel = new Label("User Number: " + user.getUserNumber());
		Label booksTakenLabel = new Label("Books you've taken: " + user.getUserBooksTaken());
		Button btn = new Button("OK");
		
		GridPane.setConstraints(usernameLabel, 0, 0);
		GridPane.setConstraints(userNumberLabel, 0, 1);
		GridPane.setConstraints(booksTakenLabel, 0, 2);
		GridPane.setConstraints(btn,0,3);
		
		btn.setOnAction(e -> {
			mediaPlayer.play();
			window.close();	
		});
		
		layout.getChildren().addAll(usernameLabel,userNumberLabel,booksTakenLabel,btn);
		layout.setId("Layout");
		layout.setPadding(new Insets(10,10,10,10));
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().add(layout);
		finalLayout.setId("FinalLayout");
		
		Scene scene = new Scene(finalLayout, WIDTH,HEIGHT);
		scene.getStylesheets().add(pvc.getClass().getResource("details.css").toExternalForm());
		
		window.setScene(scene);
		window.setTitle(user.getUserName() + " Details!");
		window.show();
	}
	
	
	
	public static void takeBook(User user) {
		pvc = new PopupViewController();
		List<Book> bookList = database.readAllBooks();
		Label booknameLabel = new Label("Enter the name of book you want to take: ");
		TextField input = new TextField();
		Button btn = new Button("TAKE");
		VBox labels = new VBox();
		for(Book book : bookList) {
			String status = "";
			if(book.getBookStatus())
				status = "Taken";
			else
				status = "Not Taken";
			Label temp = new Label(book.getBookNumber() + " - " + book.getBookName() + " - " + status);
			labels.getChildren().add(temp);
		}
		
		GridPane.setConstraints(booknameLabel,0,0);
		GridPane.setConstraints(input,1,0);
		GridPane.setConstraints(btn,2,0);
		GridPane.setConstraints(labels,0,1);
		
		btn.setOnAction(e -> {
			mediaPlayer.play();
			database.takeBook(user, input.getText());
		});
		
		Pane layout = new GridPane();
		layout.getChildren().addAll(booknameLabel, input, labels,btn);
		layout.setId("Layout");
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().addAll(layout);
		finalLayout.setId("FinalLayout");
		
		Scene scene = new Scene(finalLayout,WIDTH,HEIGHT);
		scene.getStylesheets().add(pvc.getClass().getResource("take.css").toExternalForm());
		
		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Take-a-Book");
		window.show();
	}



	public static void returnBook(User user) {
		pvc = new PopupViewController();
		List<Book> list = database.readAllBooks();
		
		Label label = new Label("Enter the name of book you want to return: ");
		TextField input = new TextField();
		Button btn = new Button("RETURN");
		
		VBox books = new VBox();
		
		for(Book book:list) {
			String status = "";
			if(book.getBookStatus())
				status = "Taken";
			else
				status = "Not Taken";
			
			Label temp = new Label(book.getBookNumber() + " - " + book.getBookName() + " - " + status);
			books.getChildren().add(temp);
		}
		
		GridPane.setConstraints(label,0,0);
		GridPane.setConstraints(input,1,0);
		GridPane.setConstraints(btn,2,0);
		GridPane.setConstraints(books,0,1);
		
		btn.setOnAction(e -> {
			mediaPlayer.play();
			database.returnBook(user, input.getText());
		});
		
		Pane layout = new GridPane();
		layout.getChildren().addAll(label,input,btn,books);
		layout.setId("Layout");
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().addAll(layout);
		finalLayout.setId("FinalLayout");
		
		Scene scene = new Scene(finalLayout,WIDTH,HEIGHT);
		scene.getStylesheets().add(pvc.getClass().getResource("return.css").toExternalForm());
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Return-a-book");
		stage.show();
	}



	public static void donateBook() {
		pvc = new PopupViewController();
		Label label = new Label("Enter the name of book you want to donate: ");
		TextField input = new TextField();
		Button btn = new Button("DONATE");

		GridPane.setConstraints(label,0,0);
		GridPane.setConstraints(input,1,0);
		GridPane.setConstraints(btn,2,0);
		
		btn.setOnAction(e -> {
			mediaPlayer.play();
			database.addBook(input.getText());
		});
		
		Pane layout = new GridPane();
		layout.getChildren().addAll(label,input,btn);
		layout.setId("Layout");
		
		Pane finalLayout = new GridPane();
		finalLayout.getChildren().addAll(layout);
		finalLayout.setId("FinalLayout");
		
		
		Scene scene = new Scene(finalLayout,WIDTH,HEIGHT);
		scene.getStylesheets().add(pvc.getClass().getResource("donate.css").toExternalForm());
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Donate-a-book");
		stage.show();
	}
	
}
