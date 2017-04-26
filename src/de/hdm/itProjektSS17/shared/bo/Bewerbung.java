package de.hdm.itProjektSS17.shared.bo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bewerbung extends BusinessObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * Erstellungsdatum der Bewerbung
	   */
	private GregorianCalendar erstellungsdatum;
	
	/**
	   * Bewerbungstext der Bewerbung
	   */
	private String bewerbungstext;
	
	/**
	 * Realisierung der Beziehung zu einer Ausschreibung durch einen Fremdschlüssel
	 */
	private int ausschreibungId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Organisationseinheit durch einen Fremdschlüssel
	 */
	private int organisationseinheitId = 0;

	
	/**
	   * Auslesen des Erstellungsdatum
	   */
	public GregorianCalendar getErstellungsdatum() {
		return erstellungsdatum;
	}
	
	/**
	   * Setzen des Erstellungsdatum
	   */
	public void setErstellungsdatum(GregorianCalendar erstellungsdatum) {
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
	 * @return Gibt den Fremdschlüssel ausschreibungId zurück.
	 */
	public int getAusschreibungId() {
		return ausschreibungId;
	}

	/**
	 * @param Setzt den Fremdschlüssel ausschreibungId.
	 */
	public void setAusschreibungId(int ausschreibungId) {
		this.ausschreibungId = ausschreibungId;
	}

	/**
	 * @return Gibt den Fremdschlüssel organisationseinheitId zurück.
	 */
	public int getOrganisationseinheitId() {
		return organisationseinheitId;
	}

	/**
	 * @param Setzt den Fremdschlüssel organisationseinheitId.
	 */
	public void setOrganisationseinheitId(int organisationseinheitId) {
		this.organisationseinheitId = organisationseinheitId;
	}

	
	
	
	
	
	
	
}
