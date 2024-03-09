package entites.model.Interface;

import java.util.Scanner;

import entites.model.repository.BookPhysico_Repository;
import entites.model.repository.BookTypeBoth_Repository;
import entites.model.repository.DigitalBook_Repository;

public interface I_Books {
	boolean seeList(Scanner sc);

	boolean registerBook(Scanner sc, DigitalBook_Repository dbR, BookPhysico_Repository bpR,
			BookTypeBoth_Repository bbR);

	boolean removeBook(Scanner sc);

	int selectBooks(int number, Scanner sc);

	boolean returnBook(Scanner sc);
}
