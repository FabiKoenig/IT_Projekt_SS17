package de.hdm.thies.bankProjektSS17.shared.report;

import java.util.Date;

public class SimpleReport extends Report{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String titel = "";
	
	private Date erstellungsdatum = null;
	
	private Paragraph header = null;

	
	
	
	
	
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	public Paragraph getHeader() {
		return header;
	}

	public void setHeader(Paragraph header) {
		this.header = header;
	}
	
}
