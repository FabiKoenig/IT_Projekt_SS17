package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Bewertung extends BusinessObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stellungnahme = "";
	
	private double wert = 0.0;
	
	private Date erstellungsdatum = new Date();

	/**
	 * 
	 * @return erstellungsdatum
	 */
	
	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}
	
	/**
	 * 
	 * @param erstellungsdatum
	 */
	
	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	/**
	 * Realisierung der Beziehung zu einer Bewerbung durch einen Fremdschl�ssel
	 */
	private int bewerbungId = 0;
	
	/**
	 * 
	 * @return stellungnahme
	 */
	
	public String getStellungnahme() {
		return stellungnahme;
	}

	/**
	 * 
	 * @param stellungnahme
	 */
	
	public void setStellungnahme(String stellungnahme) {
		this.stellungnahme = stellungnahme;
	}
	
	/**
	 * 
	 * @return wert
	 */
	
	public double getWert() {
		return wert;
	}

	/**
	 * 
	 * @param wert
	 */
	
	public void setWert(double wert) {
		this.wert = wert;
	}

	/**
	 * @return Gibt den Fremdschl�ssel bewerbungId zur�ck. 
	 */
	public int getBewerbungId() {
		return bewerbungId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel bewerbungId.
	 */
	public void setBewerbungId(int bewerbungId) {
		this.bewerbungId = bewerbungId;
	}
	
	

	
	
}
