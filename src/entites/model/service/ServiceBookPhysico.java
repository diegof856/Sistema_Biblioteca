package entites.model.service;

import java.util.Scanner;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;
import entites.model.BookPhysico;
import entites.model.repository.BookPhysico_Repository;
import entites.model.validates.AServiceValidatesBooksDigitalOrBoth;

public class ServiceBookPhysico extends AServiceValidatesBooksDigitalOrBoth {
	private ServiceBookBoth sBB = new ServiceBookBoth();  
	public void isBookPhysico(String title, String author, String isbn, String state, String type, BookPhysico_Repository bpR, Scanner sc) {
		char signature;
		boolean signatureExists;
		System.out.print("Quantidade de paginas do livro: ");
		while(!sc.hasNextInt()) {
			System.out.println("Digite um numero inteiro valido: ");
			sc.next();
		}
		int qtdPages = sc.nextInt();
		sc.nextLine();
		System.out.print("Qual a empresa responsavel pela publicação: ");
		String responsibleCompany = sc.nextLine();
		System.out.print("Qual o tipo da capa do livro: ");
		String typeCover = sc.nextLine();
		
		do {
			System.out.print("No livro tem alguma assinatura ou autografo do auto(s/n): ");
			signature = Character.toLowerCase(sc.nextLine().charAt(0));
		}while(!sBB.validateChar(signature));
		
		if(signature == 's') {
			signatureExists = true;
		}else {
			signatureExists = false;
		}
		
		
		
		BookPhysico b = new BookPhysico(title, author, isbn, true, NewUsed.valueOf(type), DigitalOrPhysical.FISICO, qtdPages, responsibleCompany, typeCover, signatureExists);
		bpR.addList(b);
		}
	}
	

