package entites.model.repository;

import java.util.ArrayList;
import java.util.List;

import entites.model.Book;

public class UserBookBorrowed_Repository {
	private List<Book> borrowedList = new ArrayList<>();

	public UserBookBorrowed_Repository() {

	}

	public void addListBorrowed(Book book) {
		borrowedList.add(book);
	}

	public List<Book> getBorrowed() {
		return borrowedList;
	}
}
