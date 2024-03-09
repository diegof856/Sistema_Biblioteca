package application;

import java.util.Scanner;

import entites.model.Interface.I_Books;
import entites.model.Interface.I_User;
import entites.model.repository.BookPhysico_Repository;
import entites.model.repository.BookTypeBoth_Repository;
import entites.model.repository.DigitalBook_Repository;
import entites.model.repository.UserBookBorrowed_Repository;
import entites.model.repository.User_Repository;
import entites.model.service.ServiceBookBoth;
import entites.model.service.ServiceUser;

public class Program_Library {

	
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			I_User user = new ServiceUser();
			DigitalBook_Repository dbR = new DigitalBook_Repository();
			BookPhysico_Repository dpR = new BookPhysico_Repository();
			BookTypeBoth_Repository bbR = new BookTypeBoth_Repository();
			UserBookBorrowed_Repository ubbRop = new UserBookBorrowed_Repository();
			I_Books book = new ServiceBookBoth(dbR, dpR, bbR, ubbRop);
			User_Repository userR = new User_Repository();
			try {
				user.register(sc, userR);

				boolean shouldContinue = true;

				do {
					switch (user.options(sc, userR)) {
					case 1:
						shouldContinue = book.seeList(sc);
						break;
					case 2:
						shouldContinue = book.removeBook(sc);
						break;
					case 3:
						shouldContinue = book.registerBook(sc, dbR, dpR, bbR);
						break;
					case 4:
						shouldContinue = book.returnBook(sc);
						break;
					case 5:
						return;

					}
				} while (shouldContinue);

			}catch(Exception e) {
				System.out.println("ERRO: "+ e.getMessage());
			}finally {
				sc.close();
			}
			
			
			
		}

	

}
