package entites.model.repository;

import java.util.ArrayList;
import java.util.List;

import entites.model.BookTypeBoth;

public class BookTypeBoth_Repository {
	
	private List<BookTypeBoth> bothList = new ArrayList<>();
	
	public BookTypeBoth_Repository() {
		
	}
	 public void addList(BookTypeBoth bookTypeBoth) {
		 bothList.add(bookTypeBoth);
		}
	public List<BookTypeBoth> getBothList() {
		return bothList;
	}
	public void remove(BookTypeBoth selectedBook) {
		bothList.remove(selectedBook);
		
	}
	
	 
}
