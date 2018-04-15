package com.adem.Entities;

public class User {
	private String userName;
	private String password;
	private int userNumber;
	private int userBooksTaken;
	private String books;
	
	public User() {};
	
	
	
	public User(String userName, String password, int userNumber, int userBooksTaken) {
		super();
		this.userName = userName;
		this.password = password;
		this.userNumber = userNumber;
		this.userBooksTaken = userBooksTaken;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getUserBooksTaken() {
		return userBooksTaken;
	}
	public void setUserBooksTaken(int userBooksTaken) {
		this.userBooksTaken = userBooksTaken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getBooks() {
		return books;
	}



	public void setBooks(String books) {
		this.books = books;
	}
	
	
}
