package de.hdm.itProjektSS17.shared.bo;

import java.sql.Date;

public class Projekt extends BusinessObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * Startdatum des Projekt.
	   */
	private Date startdatum; 
	
	/**
	   * Enddatum des Projekt
	   */
	private Date enddatum;
	
	/**
	   * Name des Projekt
	   */
	private String name;
	
	/**
	   * Beschreibung des Projekt
	   */
	private String beschreibung;
	
	/**
	 * Realisierung der Beziehung zu einem Projektmarktplatz durch einen Fremdschlüssel.
	 */
	private int projektmarktplatzId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Person durch einen Fremdschlüssel.
	 */
	private int projektleiterId = 0;
	
	 /**
	   * Auslesen des Startdatum.
	   */
	public Date getStartdatum(){
		return startdatum;
	}
	
	/**
	   * Setzen des Startdatum.
	   */
	public void setStartdatum(Date startdatum){
		this.startdatum = startdatum;
	}
	
	 /**
	   * Auslesen des Enddatum.
	   */
	public Date getEnddatum() {
		return enddatum;
	}
	
	/**
	   * Setzen des Enddatum.
	   */
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	 /**
	   * Auslesen des Namen.
	   */
	public String getName() {
		return name;
	}
	
	/**
	   * Setzen des Namen.
	   */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	   * Auslesen der Beschreibung.
	   */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	   * Setzen der Beschreibung.
	   */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return Gibt den Fremdschlüssel projektmarktplatzId zurück.
	 */
	public int getProjektmarktplatzId() {
		return projektmarktplatzId;
	}

	/**
	 * @param Setzt den Fremdschlüssel projektmarktplatzId.
	 */
	public void setProjektmarktplatzId(int projektmarktplatzId) {
		this.projektmarktplatzId = projektmarktplatzId;
	}

	/**
	 * @return Gibt den Fremdschlüssel projektleiterId zurück.
	 */
	public int getProjektleiterId() {
		return projektleiterId;
	}

	/**
	 * @param Setzt den Fremdschlüssel projektleiterId.
	 */
	public void setProjektleiterId(int projektleiterId) {
		this.projektleiterId = projektleiterId;
	}
	
	
}
