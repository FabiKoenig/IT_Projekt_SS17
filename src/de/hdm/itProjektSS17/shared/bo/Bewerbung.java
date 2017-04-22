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

	
	
	
	
	
	
}
