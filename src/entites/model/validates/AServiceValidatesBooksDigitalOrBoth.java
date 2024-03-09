package entites.model.validates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;
import entites.model.Book;
import entites.model.BookPhysico;
import entites.model.BookTypeBoth;
import entites.model.DigitalBook;
import entites.model.User;
import entites.model.repository.BookPhysico_Repository;
import entites.model.repository.BookTypeBoth_Repository;
import entites.model.repository.DigitalBook_Repository;
import entites.model.repository.UserBookBorrowed_Repository;
import entites.model.repository.User_Repository;

public abstract class AServiceValidatesBooksDigitalOrBoth {
	public String validadeBorrowed(int number) {
		if(number >= 3) {
			return "O MAXIMO DE LIVROS QUE VOÇÊ PODE TER EMPRESTADOS SÃO "+ number +" VOÇÊ NâO PODE MAIS ADICIONAR LIVROS A SUA LISTA A MENOS QUE VOÇÊ DEVOLVAR ALGUM(S)";
			
		}else if(number > 1 && number < 3) {
			return "O MAXIMO DE LIVROS QUE VOÇÊ PODE TER EMPRESTADOS SÃO 3 VOÇÊ ATUALMENTE TEM "+ number+ " VOÇÊ SO PODE TER MAIS UM";
			
		}else {
			return "Quantidade de livros emprestados atualmente: " + number;
			
		}
		
	}
	
	public String validateIsbn(String isbn) {
		if(isbn == null || isbn.length() <= 13 || isbn.isEmpty() ) {
			return "Esse "+ isbn + " não é valido ele não pode estar em branco e não ter menos do que 13 caracteres";
		}
		return isbn;
	}

	public String validateFormat(String format) {
		if(format.equals("pdf")) {
			return format;
		}
		return "Não aceitamos este";
	}

	public Double validateSize(Double size) {
		if(size > 0 && size < 1000) {
			return size;
		}
		System.out.printf("O tamanho esta maior do que suportamos %.2f%n",size);
		return null;
	}
	public int decreaseVaribleX(int x) {
		if(x > 0) {
			return x-1;
		}else {
			return x;
		}
		
	}

	public Book searchForBook(int responseUser, int numberCount, List<DigitalBook> repositoryD,
	        List<BookPhysico> repositoryP, List<BookTypeBoth> repositoryB) {
	    if (responseUser >= 1 && responseUser <= numberCount) {
	        // Se a opção selecionada estiver dentro do intervalo válido
	    	
	        int bookIndex = responseUser - 1; // O índice do livro na lista é a opção selecionada menos um
	        if (bookIndex < repositoryD.size()) {
	            // Se o índice estiver dentro da lista de livros digitais
	            return repositoryD.get(bookIndex);
	        } else if (bookIndex < repositoryD.size() + repositoryP.size()) {
	            // Se o índice estiver dentro da lista de livros físicos
	            bookIndex -= repositoryD.size();
	            return repositoryP.get(bookIndex);
	        } else if (bookIndex < repositoryD.size() + repositoryP.size() + repositoryB.size()) {
	            // Se o índice estiver dentro da lista de ambos os tipos de livros
	            bookIndex -= repositoryD.size() + repositoryP.size();
	            return repositoryB.get(bookIndex);
	        }
	    }
	    return null;
	}
	public void putBooks(DigitalBook_Repository repositoryD, BookPhysico_Repository repositoryP, BookTypeBoth_Repository repositoryB, UserBookBorrowed_Repository repositoryBorrowed) {
		BookTypeBoth b1 = new BookTypeBoth("Das coisas nascem coisas", "Bruno Munari", "978-8580632293", true,
				NewUsed.NOVO, DigitalOrPhysical.AMBOS, 400, "Martins Fontes", 200.0, "PDF", "Capa comum", false);
		BookPhysico b2 = new BookPhysico("A Guerra dos Tronos : As Crônicas de Gelo e Fogo, volume 1",
				"George R. R. Martin", "978-8556510785", false, NewUsed.USADO, DigitalOrPhysical.FISICO, 600, "Suma",
				"Capa comun", false);
		DigitalBook b3 = new DigitalBook("O Segredo Do Violinista", "Eva Furnari", "978-8516067090", true,
				NewUsed.USADO, DigitalOrPhysical.DIGITAL, 126, "Moderna", 85.6, "PDF");
		DigitalBook b4 = new DigitalBook("Rapunzel ", "Rosana Rios", "978-8566470963", false, NewUsed.USADO,
				DigitalOrPhysical.DIGITAL, 48, "Edelbra", 55.5, "PDF");
		BookTypeBoth b5 = new BookTypeBoth("Branca de Neve e os Sete Anões", "Lily Murray", "978-6556090696", true,
				NewUsed.NOVO, DigitalOrPhysical.AMBOS, 80, "Universo dos Livros", 68.5, "PDF", "Capa Dura", true);

		
		repositoryD.addList(b3);
		repositoryD.addList(b4);
		
		repositoryP.addList(b2);
		
		repositoryB.addList(b1);
		repositoryB.addList(b5);
		
		// Adiciona os livros com isStatus() falso ao repositoryBorrowed
	    for (DigitalBook digitalBook : repositoryD.getDigitalList()) {
	        if (!digitalBook.isStatus()) {
	            repositoryBorrowed.addListBorrowed(digitalBook);
	        }
	    }

	    for (BookPhysico bookPhysico : repositoryP.getPhysicoList()) {
	        if (!bookPhysico.isStatus()) {
	            repositoryBorrowed.addListBorrowed(bookPhysico);
	        }
	    }

	    for (BookTypeBoth bookTypeBoth : repositoryB.getBothList()) {
	        if (!bookTypeBoth.isStatus()) {
	            repositoryBorrowed.addListBorrowed(bookTypeBoth);
	        }
	    }

	}
	public void showUserWaitingList() {
		User user = new User("Felipe Amorim", 3636);
		User user2 = new User("Maria", 8708);
		User_Repository userRepo = new User_Repository();
		userRepo.addlistWait(user);
		userRepo.addlistWait(user2);
		for(User us : userRepo.getUserListWait()) {
			System.out.println(us.getName());
		} 
		
		
	}
	public void downloadBook(Book selectedBook, Scanner sc) {
		System.out.println("Esse livro so possui a versão digital por isso voçê tem que colocar aqui o endereço na sua maquina para baixa-lo (F:\\Meus Documentos\\Área de Trabalho): ");
		String sourceFileStr = sc.nextLine();
		File sourceFile = new File(sourceFileStr);
		boolean sucess = new File(sourceFile + "\\Pasta").mkdir();
		if(sucess) {
			System.out.println("Pasta criada com sucesso!!");
		}
		String targetFileStr = sourceFileStr + "\\Pasta\\Summary.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
			bw.write("Titulo do livro: "+selectedBook.getTitles() + "\n");
			bw.write("Autor(a): "+ selectedBook.getAuthor()+ " ,ISBN: "+selectedBook.getIsbn() +" , Editoria: "+ selectedBook.getPublishingCompany() + "\n");
			bw.write("Tipo: "+ selectedBook.getDigitalOrPhysical()+ " Quantidade de paginas: "+selectedBook.getSizePages()+"\n");
			bw.write("\n Obrigado Pela Visita!!!!");
		}catch(IOException e) {
			System.out.println("Erro: "+ e.getMessage());
		}
		
	}
	public void typeBookPhysico(Book selectedBook,Scanner sc) {
		System.out.print("Escreva o nome da sua cidade: ");
		String city = sc.nextLine();
		System.out.print("Escreva o nome da sua rua: ");
		String road = sc.nextLine();
		System.out.print("Escreva o numero da sua Casa ou apartamento: ");
		while(!sc.hasNextInt()) {
			System.out.print("Digite um numero valido...");
			sc.next();
		}
		int numberHouse = sc.nextInt();
		sc.nextLine();
		System.out.println("Como isso é uma simulação escreva um endereço da sua maquina para imprimir os dados para a entrega (F:\\Meus Documentos\\Área de Trabalho): ");
		String sourceFileStr = sc.nextLine();
		File sourceFile = new File(sourceFileStr);
		boolean sucess = new File(sourceFile + "\\Pasta").mkdir();
		if(sucess) {
			System.out.println("Pasta criada com sucesso!!");
		}
		String targetFileStr = sourceFileStr + "\\Pasta\\Summary.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
			bw.write("Titulo do livro: "+selectedBook.getTitles() + "\n");
			bw.write("Autor(a): "+ selectedBook.getAuthor()+ " ,ISBN: "+selectedBook.getIsbn() +" , Editoria: "+ selectedBook.getPublishingCompany() + "\n");
			bw.write("Tipo: "+ selectedBook.getDigitalOrPhysical()+ "Quantidade de paginas: "+selectedBook.getSizePages()+"\n");
			bw.write("Nome da cidade: "+ city+" \n"+ "Nome da rua: "+ road+ "\n"+ "Numero da casa: "+ numberHouse);
			bw.write("\n Obrigado Pela Visita!!!!");
		}catch(IOException e) {
			System.out.println("Erro: "+ e.getMessage());
		}
	}
		
	public void typeBookBoth(Book selectedBook, Scanner sc, DigitalBook_Repository repositoryD, BookTypeBoth_Repository repositoryB, BookPhysico_Repository repositoryP) {
	    while (selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.AMBOS) {
	        System.out.print("Você tem que escolher qual das 2 versões (DIGITAL/FISICO): ");
	        String choice = sc.nextLine().toUpperCase();
	        if (choice.equals("DIGITAL") || choice.equals("FISICO")) {
	            selectedBook.setDigitalOrPhysical(DigitalOrPhysical.valueOf(choice));
	        } else {
	            System.out.println("Opção inválida. Escolha entre DIGITAL e FISICA.");
	        }
	    }

	    if (selectedBook instanceof BookTypeBoth) {
	        repositoryB.remove((BookTypeBoth) selectedBook);
	        if (selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.DIGITAL) {
	        	repositoryP.addList(new BookPhysico(selectedBook.getTitles(), selectedBook.getAuthor(), selectedBook.getIsbn(), true, NewUsed.USADO, DigitalOrPhysical.FISICO, selectedBook.getSizePages(), selectedBook.getPublishingCompany(), "Normal", false));
	        } else if (selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.FISICO) {
	            repositoryD.addList(new DigitalBook(selectedBook.getTitles(), selectedBook.getAuthor(), selectedBook.getIsbn(), true, selectedBook.getResponseUsedorNot(), selectedBook.getDigitalOrPhysical(), selectedBook.getSizePages(), selectedBook.getPublishingCompany(), 100.0, "PDf"));

	        }
	    }

	    if (selectedBook.getDigitalOrPhysical() == DigitalOrPhysical.FISICO) {
	        typeBookPhysico(selectedBook, sc);
	    } else {
	        downloadBook(selectedBook, sc);
	    }
	}




	
}
