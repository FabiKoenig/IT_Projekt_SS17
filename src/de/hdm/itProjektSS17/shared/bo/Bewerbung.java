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
	   * Erstellungsdatum der Bewerbung welches zur Erstellung von jedem Bewerbung-Objekt das akutelle 
	   * Datum durch den Null-Argument-Konstruktor der Klasse Date setzt
	   */
	private Date erstellungsdatum=new Date();
	
	/**
	   * Bewerbungstext der Bewerbung
	   */
	private String bewerbungstext;
	
	/**
	 * Realisierung der Beziehung zu einer Ausschreibung durch einen Fremdschl�ssel
	 */
	private int ausschreibungId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Organisationseinheit durch einen Fremdschl�ssel
	 */
	private int organisationseinheitId = 0;
	
	public enum Bewerbungsstatus { laufend, angenommen, abgelehnt };
	
	private Bewerbungsstatus status = Bewerbungsstatus.laufend;
	
	
	public void setStatus(Bewerbungsstatus status) {
		this.status = status;
	}
	
	public Bewerbungsstatus getStatus() {
		return status;
	}
	
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

	/**
	 * @return Gibt den Fremdschl�ssel organisationseinheitId zur�ck.
	 */
	public int getOrganisationseinheitId() {
		return organisationseinheitId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel organisationseinheitId.
	 */
	public void setOrganisationseinheitId(int organisationseinheitId) {
		this.organisationseinheitId = organisationseinheitId;
	}

	
	
	
	
	
	
	
}
