package com.adem.test.Database;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.adem.Database.Database;
import com.adem.Entities.Book;

class DatabaseTest {

	@Test
	public void bookListLoadedFromDatabase() {
		Database db = Database.getDatabase();
		ArrayList<Book> list = (ArrayList<Book>)db.readAllBooks();
		assertNotNull(list);
	}
}
