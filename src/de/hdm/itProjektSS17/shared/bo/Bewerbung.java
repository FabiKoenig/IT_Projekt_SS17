package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Bewerbung extends BusinessObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * Erstellungsdatum der Bewerbung
	   */
	private  Date erstellungsdatum;
	
	/**
	   * Bewerbungstext der Bewerbung
	   */
	private String bewerbungstext;
	
	/**
	 * Realisierung der Beziehung zu einer Ausschreibung durch einen Fremdschl�ssel
	 */
	private int ausschreibungId = 0;
	
	/**
	   * Auslesen des Erstellungsdatum
	   */
	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}
	
	/**
	   * Setzen des Erstellungsdatum
	   */
	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}
	
	/**
	   * Auslesen des Bewerbungstext
	   */
	public String getBewerbungstext() {
		return bewerbungstext;
	}
	
	/**
	   * Setzen des Bewerbungstext
	   */
	public void setBewerbungstext(String bewerbungstext) {
		this.bewerbungstext = bewerbungstext;
	}

	/**
	 * @return Gibt den Fremdschl�ssel ausschreibungId zur�ck.
	 */
	public int getAusschreibungId() {
		return ausschreibungId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel ausschreibungId.
	 */
	public void setAusschreibungId(int ausschreibungId) {
		this.ausschreibungId = ausschreibungId;
	}

	
	
	
	
	
	
}
