package entites.model;

import java.util.Scanner;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;

public class BookPhysico extends Book {

	private String covertype;
	private boolean signatureauthor;

	public BookPhysico(String titles, String author, String isbn, boolean status, NewUsed responseUsedorNot,
			DigitalOrPhysical digitalOrPhysical, Integer sizePages, String publishingCompany, String covertype,
			boolean signatureauthor) {
		super(titles, author, isbn, status, responseUsedorNot, digitalOrPhysical, sizePages, publishingCompany);
		this.covertype = covertype;
		this.signatureauthor = signatureauthor;
	}

	public String getCovertype() {
		return covertype;
	}

	public void setCovertype(String covertype) {
		this.covertype = covertype;
	}

	public boolean isSignatureauthor() {
		return signatureauthor;
	}

	public void setSignatureauthor(boolean signatureauthor) {
		this.signatureauthor = signatureauthor;
	}

	public void checkNewOrUsed() {
		Scanner sc = new Scanner(System.in);
		System.out.print("O livro é usado ou novo (n/u): ");
		char response = sc.nextLine().charAt(0);
		if (response == 'n') {
			System.out.println("Okay, será adicionado como novo");
		}
		System.out.println("Okay, será adicionado como usado (ESTEJA CIENTE QUE O LIVRO USADO VALERA MENOS!!!)");

		sc.close();
	}

}
