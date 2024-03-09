package entites.model.service;

import java.util.Scanner;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;
import entites.model.DigitalBook;
import entites.model.repository.DigitalBook_Repository;
import entites.model.validates.AServiceValidatesBooksDigitalOrBoth;

public class ServiceBookDigital extends AServiceValidatesBooksDigitalOrBoth{
	public void isDigitalBook(String title, String author, String isbn, String state, String type, DigitalBook_Repository dbR, Scanner sc) {
		int x = 0;
		String format = null;
		Double sizeBook = 0.0;
		System.out.print("Quantidade de paginas do livro: ");
		while(!sc.hasNextInt()) {
			System.out.print("Digite um numero inteiro valido: ");
			sc.next();
		}
		int qtdPages = sc.nextInt();
		sc.nextLine();
		System.out.print("Qual a empresa responsavel pela publicação: ");
		String responsibleCompany = sc.nextLine();
		while(x == 0){
			System.out.print("Qual o tamanho do arquivo (não pode ter mais de 1GB): ");
			sizeBook = sc.nextDouble();
			if(validateSize(sizeBook) != sizeBook) {
				System.out.println();
			}
			x++;
		}
		x = decreaseVaribleX(x);
	while(x == 0) {
		System.out.print("Qual o formato do arquivo (não aceitamos outros formatos além de PDF): ");
		format = sc.nextLine().trim().toLowerCase();
		
		if(!validateFormat(format).equals(format)) {
			sc.nextLine();
			System.out.println(validateFormat(format));
		}else {
			x++;
		}
		
		}
		
		
		DigitalBook b = new DigitalBook(title, author, isbn, true, NewUsed.valueOf(type), DigitalOrPhysical.DIGITAL, qtdPages, responsibleCompany, sizeBook, format);
		dbR.addList(b);
		
		}
}
