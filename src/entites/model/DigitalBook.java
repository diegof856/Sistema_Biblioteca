package entites.model;

import entites.enums.DigitalOrPhysical;
import entites.enums.NewUsed;

public class DigitalBook extends Book {
	private Double size;
	private String format;

	public DigitalBook(String titles, String author, String isbn, boolean status, NewUsed responseUsedorNot,
			DigitalOrPhysical digitalOrPhysical, Integer sizePages, String publishingCompany, Double size,
			String format) {
		super(titles, author, isbn, status, responseUsedorNot, digitalOrPhysical, sizePages, publishingCompany);
		this.size = size;
		this.format = format;
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

}
