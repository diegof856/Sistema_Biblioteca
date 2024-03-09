package entites.model.service;

import java.util.List;
import java.util.Scanner;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;
import entites.model.Book;
import entites.model.BookPhysico;
import entites.model.BookTypeBoth;
import entites.model.DigitalBook;
import entites.model.Interface.I_Books;
import entites.model.Interface.I_ClassValidateChar;
import entites.model.repository.BookPhysico_Repository;
import entites.model.repository.BookTypeBoth_Repository;
import entites.model.repository.DigitalBook_Repository;
import entites.model.repository.UserBookBorrowed_Repository;
import entites.model.validates.AServiceValidatesBooksDigitalOrBoth;

public class ServiceBookBoth extends AServiceValidatesBooksDigitalOrBoth implements I_ClassValidateChar, I_Books {
		
		private DigitalBook_Repository repositoryD;
	    private BookPhysico_Repository repositoryP;
	    private BookTypeBoth_Repository repositoryB;
	    private UserBookBorrowed_Repository repositoryBorrowed;
	 
	    public ServiceBookBoth(DigitalBook_Repository repositoryD, BookPhysico_Repository repositoryP, BookTypeBoth_Repository repositoryB, UserBookBorrowed_Repository repositoryBorrowed) {
	        this.repositoryD = repositoryD;
	        this.repositoryP = repositoryP;
	        this.repositoryB = repositoryB;
	        this.repositoryBorrowed = repositoryBorrowed;
	        putBooks(repositoryD, repositoryP, repositoryB, repositoryBorrowed);
	    }
	    public ServiceBookBoth() {
	    	
	    }
	public boolean validateChar(char item) {
		if (item != 's' && item != 'n') {
			return false;
		}
		return true;
	}

	// mostra a lista de livros disponiveis
	@Override
	public boolean seeList(Scanner sc) {
		int x = 0;
		while(x==0) {
			char response;
			int count = 1;
			System.out.println("Essas são as nossas opções de livros atuais:");
			for (DigitalBook repoD : repositoryD.getDigitalList()) {
				System.out.println(count + " - " + repoD.getTitles() + " Autor(a): " + repoD.getAuthor() + " ISBN: "
						+ repoD.getIsbn() + " Formato: " + repoD.getDigitalOrPhysical());
				count++;
			}
			for (BookPhysico repoP : repositoryP.getPhysicoList()) {
				System.out.println(count + " - " + repoP.getTitles() + " Autor(a): " + repoP.getAuthor() + " ISBN: "
						+ repoP.getIsbn() + " Formato: " + repoP.getDigitalOrPhysical());
				count++;
			}
			for (BookTypeBoth repoB : repositoryB.getBothList()) {
				System.out.println(count + " - " + repoB.getTitles() + " Autor(a): " + repoB.getAuthor() + " ISBN: "
						+ repoB.getIsbn() + " Formato: " + repoB.getDigitalOrPhysical());
				count++;
			}
			System.out.println(count + " - Voltar para o menu anterior");
			 
				  int selectedOption =  selectBooks(count, sc);
				  while(selectedOption > count) {
					  System.out.println("Opção inválida. Por favor, selecione uma opção válida: ");
					  selectedOption =  selectBooks(count, sc);
				  }
			
			
			if(selectedOption == count) {
				return true;
			}
			
			        // Se a opção selecionada estiver dentro do intervalo válido...
			      
					Book selectedBook = searchForBook(selectedOption, count, repositoryD.getDigitalList(), repositoryP.getPhysicoList(), repositoryB.getBothList());
					
			        	if (selectedBook != null) {
				            // Se um livro válido foi selecionado
				            System.out.println("Livro selecionado:");
				            System.out.println("Título: " + selectedBook.getTitles());
				            System.out.println("Autor: " + selectedBook.getAuthor());
				            System.out.println("ISBN: " + selectedBook.getIsbn());
				            System.out.println("Formato: " + selectedBook.getDigitalOrPhysical());
				            System.out.println("Editora: " + selectedBook.getPublishingCompany());
				            System.out.println("Quantidade de paginas: " + selectedBook.getSizePages());
				           
				            // Adicione outras informações que você deseja mostrar
				        } else {
				            System.out.println("Opção inválida selecionada.");
				        }
			        	do{
			        		System.out.println();
			    			System.out.print("Gostou deste livro gostaria de pedi-lo emprestado: ");
			    		 response = Character.toLowerCase(sc.nextLine().charAt(0));
			    			
			    		}while(!validateChar(response));
			        	System.out.println();
			        	System.out.println();
			        	
			        	if(response == 's') {
			        		 while (x == 0) {
			        		        if (selectedBook.isStatus()) {
			        		            if (repositoryBorrowed.getBorrowed().size() < 3 && selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.DIGITAL) {

			        		            	downloadBook(selectedBook,sc);
			        		                repositoryBorrowed.addListBorrowed(selectedBook);
			        		                x++;
			        		            } else if(repositoryBorrowed.getBorrowed().size() < 3 && selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.FISICO){
			        		            	typeBookPhysico(selectedBook,sc);
			        		            	repositoryBorrowed.addListBorrowed(selectedBook);
			        		                x++;
			        		            }if(repositoryBorrowed.getBorrowed().size() < 3 && selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.AMBOS) {
			        		            	typeBookBoth(selectedBook, sc,  repositoryD, repositoryB, repositoryP);
			        		            	x++;
			        		            }
			        		            else{
			        		                System.out.println("VOCÊ NÃO PODE ADICIONAR MAIS LIVROS À SUA LISTA");
			        		                x++;
			        		            }
			        		        } else {
			        		            System.out.println("Este livro está indisponível no momento.");
			        		            System.out.println("Ele possui uma lista de espera. Aqui estão algumas pessoas que estão na espera:");
			        		            showUserWaitingList();
			        		            break; // Encerra o loop se o livro estiver indisponível
			        		        }
			        		    }
			        		   x = decreaseVaribleX(x);
			        		    System.out.println(validadeBorrowed(repositoryBorrowed.getBorrowed().size()));
			        		    System.out.println();
			        		
			        	}
			        	
			        
			        	
		}
				
		    

		    return false;
		
	}

	@Override
	public boolean registerBook(Scanner sc, DigitalBook_Repository dbR, BookPhysico_Repository bpR, BookTypeBoth_Repository bbR) {
		ServiceBookDigital digitalService = new ServiceBookDigital();
		ServiceBookPhysico physicoService = new ServiceBookPhysico();
		
		
		System.out.print("Qual é o nome do titulo do livro: ");
		String title = sc.nextLine();
		System.out.print("Qual é o nome do autor do livro: ");
		String author = sc.nextLine();
		System.out.print("Qual é o ISBN do livro: ");
		String isbn = sc.nextLine();
		System.out.print("Qual é o estado do livro(NOVO/USADO): ");
		String state = sc.nextLine().trim().toUpperCase();
		System.out.print("Qual é o tipo do livro(DIGITAL/FISCO/AMBOS): ");
		String type = sc.nextLine().trim().toUpperCase();
		switch(type) {
		case "DIGITAL":
			digitalService.isDigitalBook(title, author, isbn, type, state,dbR,sc);
			break;
		case "FISICO":
			physicoService.isBookPhysico(title, author, isbn, type , state, bpR,sc);
			break;
		case "AMBOS":
			isBookBoth(title, author, isbn, type, state, bbR, sc);
			break;
			
		}
		return true;

	}

	@Override
	public boolean removeBook(Scanner sc) {
		int count = 1;
		char response;
		System.out.println("Essas são as nossas opções de livros atuais:");
		for (DigitalBook repoD : repositoryD.getDigitalList()) {
			System.out.println(count + " - " + repoD.getTitles() + " Autor: " + repoD.getAuthor() + " ISBN: "
					+ repoD.getIsbn() + " Formato: " + repoD.getDigitalOrPhysical());
			count++;
		}
		for (BookPhysico repoP : repositoryP.getPhysicoList()) {
			System.out.println(count + " - " + repoP.getTitles() + " Autor: " + repoP.getAuthor() + " ISBN: "
					+ repoP.getIsbn() + " Formato: " + repoP.getDigitalOrPhysical());
			count++;
		}
		for (BookTypeBoth repoB : repositoryB.getBothList()) {
			System.out.println(count + " - " + repoB.getTitles() + " Autor: " + repoB.getAuthor() + " ISBN: "
					+ repoB.getIsbn() + " Formato: " + repoB.getDigitalOrPhysical());
			count++;
		}
		System.out.println(count + " - Voltar para o menu anterior");
		 
			  int selectedOption =  selectBooks(count, sc);
			  while(selectedOption > count) {
				  System.out.println("Opção inválida. Por favor, selecione uma opção válida: ");
				  selectedOption =  selectBooks(count, sc);
			  }
		
		
		if(selectedOption == count) {
			return true;
		}
		Book selectedBook = searchForBook(selectedOption, count, repositoryD.getDigitalList(), repositoryP.getPhysicoList(), repositoryB.getBothList());
		if (selectedBook != null) {
            // Se um livro válido foi selecionado
            System.out.println("Livro selecionado:");
            System.out.println("Título: " + selectedBook.getTitles());
            System.out.println("Autor: " + selectedBook.getAuthor());
            System.out.println("ISBN: " + selectedBook.getIsbn());
            System.out.println("Formato: " + selectedBook.getDigitalOrPhysical());
            System.out.println("Editora: " + selectedBook.getPublishingCompany());
            System.out.println("Quantidade de paginas: " + selectedBook.getSizePages());
           
            // Adicione outras informações que você deseja mostrar
        } else {
            System.out.println("Opção inválida selecionada.");
        }
		do{
			System.out.print("Deseja remover da estante este livro: ");
		 response = Character.toLowerCase(sc.nextLine().charAt(0));
			
		}while(!validateChar(response));
		
		if(response == 's') {
			if (selectedBook instanceof DigitalBook) {
		        repositoryD.removeList((DigitalBook) selectedBook);
		    } else if (selectedBook instanceof BookPhysico) {
		        repositoryP.removeList((BookPhysico) selectedBook);
		    } else if (selectedBook instanceof BookTypeBoth) {
		        repositoryB.remove((BookTypeBoth) selectedBook);
		    }
		    System.out.println("Livro removido com sucesso da lista.");
		    System.out.println();
		    return true;
		}else {
			 System.out.println("Retornando ao menu...");
			 System.out.println();
			 return true;
		}
		

	}
	
	
	public void isBookBoth(String title, String author, String isbn, String type, String state, BookTypeBoth_Repository bbR,Scanner sc){
		
		char signature;
		boolean signatureExists;
		System.out.print("Quantidade de paginas do livro: ");
		while(!sc.hasNextInt()) {
			System.out.println("Digite um numero inteiro valido: ");
			sc.next();
		}
		int qtdPages = sc.nextInt();
		sc.nextLine();
		System.out.print("Qual o tipo da capa do livro: ");
		String typeCover = sc.nextLine();
		
		do {
			System.out.print("No livro tem alguma assinatura ou autografo do auto(s/n): ");
			signature = Character.toLowerCase(sc.nextLine().charAt(0));
		}while(!validateChar(signature));
		
		if(signature == 's') {
			signatureExists = true;
		}else {
			signatureExists = false;
		}
		
		//perguntas do tipo digital 
		int x = 0;
		String format = null;
		Double sizeBook = 0.0;

		System.out.print("Qual a empresa responsavel pela publicação: ");
		String responsibleCompany1 = sc.nextLine();
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
	BookTypeBoth b1 = new BookTypeBoth(title, author, isbn, true, NewUsed.valueOf(state), DigitalOrPhysical.AMBOS, qtdPages, responsibleCompany1, sizeBook, format, typeCover, signatureExists);
	bbR.addList(b1);
	}

	@Override
	public int selectBooks(int number, Scanner sc) {
	
		System.out.printf("%nDigite a opção que voçê mais gostou na lista de livros: ");
		  int valueListBook;
		while(true) {
	        if(sc.hasNextInt()) {
	            valueListBook = sc.nextInt();
	            sc.nextLine();
	            break;
	            }else {
	            	System.out.print("Digite um número inteiro: ");
	                sc.next();
	            	}
	            }
		 
		return valueListBook;

	}

	@Override
	public boolean returnBook(Scanner sc) {
		int x = 0;
		char response = 0;
		
		
		List<DigitalBook> digitalBooks = repositoryD.getDigitalList();
		List<BookPhysico> bookPhysicos = repositoryP.getPhysicoList();
		List<BookTypeBoth> bookTypeBoths = repositoryB.getBothList();
		int count = 0;
		//procurar nas listas
		for(DigitalBook book : digitalBooks) {
			if(!book.isStatus()) {
				System.out.println("Titulo: "+book.getTitles()+" Autor(a): "+book.getAuthor() +" ISBN: "+ book.getIsbn());
				repositoryBorrowed.addListBorrowed(book);
				count++;
				
				}
		}
		for(BookPhysico bp : bookPhysicos ) {
			if(!bp.isStatus()) {
				System.out.println("Titulo: "+bp.getTitles()+" Autor(a): "+bp.getAuthor() +" ISBN: "+ bp.getIsbn());
				repositoryBorrowed.addListBorrowed(bp);
				count++;
			}
		}
		for(BookTypeBoth bb : bookTypeBoths) {
			if(!bb.isStatus()) {
				System.out.println("Titulo: "+bb.getTitles() +" Autor(a): "+bb.getAuthor() +" ISBN: "+ bb.getIsbn());
				repositoryBorrowed.addListBorrowed(bb);
				count++;
			}
		}
		String msg = validadeBorrowed(count);
		System.out.println(msg);
		
			while(x== 0) {System.out.print("Deseja devolver algum livro (s/n): ");
			response = Character.toLowerCase(sc.nextLine().charAt(0));
			if(validateChar(response)) {
				x++;
			}
			}
		if(response == 's') {
			System.out.print("Digite aqui o ISBN do livro correspondente:");
			String isbn = sc.nextLine();
			while(!validateIsbn(isbn).equals(isbn)) {
				System.out.println(validateIsbn(isbn));	
				System.out.print("Digite o isbn novamente:");
				 isbn = sc.nextLine();
				 }
			for(Book b : repositoryBorrowed.getBorrowed()) {
				if(b.getIsbn().equals(isbn)) {
					b.setStatus(true);
				}
			}
			
			}
			
           
		
		return true;
	}
	

}
