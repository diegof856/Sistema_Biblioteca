package entites.model;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;

public abstract class Book {
	private String titles;
	private String author;
	private String isbn;
	private boolean status;
	private Integer sizePages;
	private String publishingCompany;

	private NewUsed responseUsedorNot;
	private DigitalOrPhysical digitalOrPhysical;

	public Book(String titles, String author, String isbn, boolean status, NewUsed responseUsedorNot,
			DigitalOrPhysical digitalOrPhysical, Integer sizePages, String publishingCompany) {

		this.titles = titles;
		this.author = author;
		this.isbn = isbn;
		this.status = status;
		this.responseUsedorNot = responseUsedorNot;
		this.digitalOrPhysical = digitalOrPhysical;
		this.sizePages = sizePages;
		this.publishingCompany = publishingCompany;

	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public NewUsed getResponseUsedorNot() {
		return responseUsedorNot;
	}

	public void setResponseUsedorNot(NewUsed responseUsedorNot) {
		this.responseUsedorNot = responseUsedorNot;
	}

	public DigitalOrPhysical getDigitalOrPhysical() {
		return digitalOrPhysical;
	}

	public void setDigitalOrPhysical(DigitalOrPhysical digitalOrPhysical) {
		this.digitalOrPhysical = digitalOrPhysical;
	}

	public Integer getSizePages() {
		return sizePages;
	}

	public void setSizePages(Integer sizePages) {
		this.sizePages = sizePages;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

}
