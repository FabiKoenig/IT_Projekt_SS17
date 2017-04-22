package de.hdm.thies.bankProjektSS17.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 * @author Fabian
 *
 */

public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tite des Reports.
	 */
	private String titel = "";
	
	/**
	 * Erstellungsdatum des Reports.
	 */
	private Date erstellungsdatum = null;
	
	
	/**
	 * Kopfdaten eines Reports.
	 */
	private Paragraph header = null;

	
	
	
	/**
	 * Auslesen des Report Titels.
	 * @return den Report Titel.
	 */
	
	public String getTitel() {
		return titel;
	}

	/**
	 * Setzen des Report Titels.
	 * @param titel
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * Auslesen des Erstellungsdatums.
	 * @return
	 */
	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}

	
	/**
	 * Setzen des Erstellungsdatums.
	 * @param erstellungsdatum
	 */
	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	/**
	 * Auslesen der Kopfdaten.
	 * @return das Paragraph Objekt, dass die Kopfdaten beeinhaltet.
	 */
	public Paragraph getHeader() {
		return header;
	}

	/**
	 * Setzen der Kopfdaten.
	 * @param header
	 */
	public void setHeader(Paragraph header) {
		this.header = header;
	}
	
}
