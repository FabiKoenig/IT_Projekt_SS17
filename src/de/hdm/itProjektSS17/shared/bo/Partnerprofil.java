package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Partnerprofil extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	/**
	   * Datum der Erstellung des Partnerprofils
	   */
	private Date erstellungsdatum = null;
	/**
	   * Datum der Änderung des Partnerprofils
	   */
	private Date aenderungdatum = null;
	
	//private int OragnisationsID = 0;
	//private int AusschreibungsID = 0;
	
	/**
	   * Auslesen des Erstellungsdatums
	   */
	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}
	/**
	   * Setzen des Erstellungsdatums
	   */
	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}
	/**
	   * Auslesen des Aenderungsdatums
	   */
	public Date getAenderungdatum() {
		return aenderungdatum;
	}
	/**
	   * Setzen des Aenderungsdatums
	   */
	public void setAenderungdatum(Date aenderungdatum) {
		this.aenderungdatum = aenderungdatum;
	}
	
	
	

}
