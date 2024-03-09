package entites.model.repository;

import java.util.ArrayList;
import java.util.List;

import entites.model.BookPhysico;

public class BookPhysico_Repository {

	private List<BookPhysico> physicoList = new ArrayList<>();

	public BookPhysico_Repository() {
		
	}
	public void addList(BookPhysico bookPhysico) {
		 physicoList.add(bookPhysico);
		}
	public List<BookPhysico> getPhysicoList() {
		return physicoList;
	}
	public void removeList(BookPhysico selectedBook) {
		physicoList.remove(selectedBook);
	}
	
}
