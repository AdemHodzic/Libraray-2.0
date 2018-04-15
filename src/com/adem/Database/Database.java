package com.adem.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.adem.Controller.Controller;
import com.adem.Controller.ViewController;
import com.adem.Entities.Book;
import com.adem.Entities.User;


public class Database {

	private static Database db;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private static final String CONN_STRING_USERS =
			"jdbc:h2:~/test";
	
	private Database() {
		
	}
	
	public  static List<User> readAllUsers(){
		List<User> list = new ArrayList<>();
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD);
				Statement statement = conn.createStatement();
				ResultSet query = statement.executeQuery("SELECT * FROM users");
				){
			System.out.println("Connection gotted");

			while(query.next()) {
				String userName = query.getString("USERNAME");
				String password = query.getString("PASSWORD");
				int userNumber = query.getInt("USERNUMBER");
				int userBooksTaken = query.getInt("USERBOOKSTAKEN");
				String books = query.getString("BOOKS");
				
				User user = new User();
				user.setUserName(userName);
				user.setUserNumber(userNumber);
				user.setUserBooksTaken(userBooksTaken);
				user.setPassword(password);
				user.setBooks(books);
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public  List<Book> readAllBooks(){
		List<Book> list = new ArrayList<>();
		try(
				Connection connection = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet query = statement.executeQuery("SELECT * FROM books");

				) {

			while(query.next()) {
				String userName = query.getString("BOOKNAME");
				int userNumber = query.getInt("BOOKNUMBER");
				boolean userBooksTaken = query.getBoolean("BOOKSTATUS");
				Book book = new Book(userName, userNumber, (userBooksTaken));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static void addUser(String username, String password) {
		String sql = "INSERT into USERS (userid, username, password, usernumber, userbookstaken,books) VALUES (?,?,?,?,?,?)";
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			int numberOfUsers = readAllUsers().size()+1;
			stmt.setString(1, Integer.toString(numberOfUsers));
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, Integer.toString(numberOfUsers));
			stmt.setString(5, "0");
			stmt.setString(6, "");
			
			int success = stmt.executeUpdate();
			if(success!=1) {
				Controller.error("Failed to register");
			}else {
				User user = new User(username, password, numberOfUsers, 0);
				Controller.loginSuccess(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addBook(String bookName) {
		List<Book> list = readAllBooks();
		for(Book book:list) {
			if(book.getBookName().equals(bookName)) {
				ViewController.error("That book already exists.");
				return;
			}
		}
		int bookNumber = list.size()+1;
		String sql = "INSERT into books(ID,BOOKNAME,BOOKNUMBER,BOOKSTATUS) VALUES (?,?,?,?)";
		
		try(
				Connection conn = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, bookNumber);
			stmt.setString(2, bookName);
			stmt.setInt(3, bookNumber);
			stmt.setBoolean(4, false);
			
			int success = stmt.executeUpdate();
			if(success==1) {
				ViewController.error("Book addedd succesfully");
			}else {
				ViewController.error("Failed to add the book");
			}
		}catch(Exception e) {
			ViewController.error("Error: " + e);
		}
		
	}
	
	private void updateUserBooksTaken(User user) {
		String sql = "update users set USERBOOKSTAKEN=?,BOOKS=? where USERNAME=? ";
		try(
				Connection conn = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, user.getUserBooksTaken());
			stmt.setString(2, user.getBooks());
			stmt.setString(3, user.getUserName());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void takeBook(User user, String bookName) {
		if(user.getUserBooksTaken() > 2) {
			ViewController.error("You currently have too many books!");
			return;
		}
		List<Book> list = new ArrayList<>();
		list.addAll(readAllBooks());
		for(Book book : list) {
			if(book.getBookName().equals(bookName) && !book.getBookStatus()) {
				String toWrite = user.getBooks()+(Integer.toString(book.getBookNumber()));
				user.setBooks(toWrite);
				user.setUserBooksTaken(user.getUserBooksTaken()+1);
				updateUserBooksTaken(user);
				changeBooksStatus(book);
				ViewController.error("Book has been taken.");
				break;
			}else if(book.getBookName().equals(bookName) && !book.getBookStatus()) {
				ViewController.error("Book already taken.");
				break;
			}
		}
	}

	public static Database getDatabase() {
		if(db == null) {
			db = new Database();
		}
		return db;
	}

	private void changeBooksStatus(Book book) {
		String sql = "update books set BOOKSTATUS=? where BOOKNAME=? ";
		try(
				Connection conn = DriverManager.getConnection(CONN_STRING_USERS, USERNAME, PASSWORD); 
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setBoolean(1, !(book.getBookStatus()));
			stmt.setString(2, book.getBookName());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void returnBook(User user, String text) {
		List<Book> list = readAllBooks();
		for(Book book:list) {
			String brojKnjige = Integer.toString(book.getBookNumber());
			if(book.getBookName().equals(text) && user.getBooks().contains(brojKnjige)) {
				user.setBooks(user.getBooks().replaceFirst(brojKnjige, ""));
				user.setUserBooksTaken(user.getUserBooksTaken()-1);
				updateUserBooksTaken(user);
				changeBooksStatus(book);
				ViewController.error("Book has been returned");
				return;
			}
		}
		ViewController.error("Wrong input");
		return;
	}

	
	
}
