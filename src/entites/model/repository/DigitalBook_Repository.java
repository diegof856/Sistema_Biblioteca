package entites.model.repository;

import java.util.ArrayList;
import java.util.List;

import entites.model.DigitalBook;

public class DigitalBook_Repository {
	 private List<DigitalBook> digitalList = new ArrayList<>();
	 
	 public DigitalBook_Repository() {
		 
	 }
	 
	 public void addList(DigitalBook digitalBook) {
		 digitalList.add(digitalBook);
		}

	public List<DigitalBook> getDigitalList() {
		return digitalList;
	}
	public void removeList(DigitalBook selectedBook) {
		digitalList.remove(selectedBook);
	}
	
	 
	 
}
