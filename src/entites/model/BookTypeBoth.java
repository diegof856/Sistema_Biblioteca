package entites.model;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;

public class BookTypeBoth extends Book {
	// perguntas do tipo digital
	private Double size;
	private String format;

	// perguntas do tipo fisico
	private String covertype;
	private boolean signatureauthor;

	public BookTypeBoth(String titles, String author, String isbn, boolean status, NewUsed responseUsedorNot,
			DigitalOrPhysical digitalOrPhysical, Integer sizePages, String publishingCompany, Double size,
			String format, String covertype, boolean signatureauthor) {
		super(titles, author, isbn, status, responseUsedorNot, digitalOrPhysical, sizePages, publishingCompany);
		this.size = size;
		this.format = format;
		this.covertype = covertype;
		this.signatureauthor = signatureauthor;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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

}
