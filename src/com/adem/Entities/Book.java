package com.adem.Entities;

public class Book {
	private String bookName;
	private int bookNumber;
	private boolean bookStatus;  //True if is taken
	
	public Book() {}
	public Book(String bookName, int bookNumber, boolean bookStatus) {
		super();
		this.bookName = bookName;
		this.bookNumber = bookNumber;
		this.bookStatus = bookStatus;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public boolean getBookStatus() {
		return bookStatus;
	}

	public void changeStatus() {
		this.bookStatus = (this.bookStatus == true)? false:true;
	}
	

}
