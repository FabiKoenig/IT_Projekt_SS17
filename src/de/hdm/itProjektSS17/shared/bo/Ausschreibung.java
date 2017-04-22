package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject{


	private static final long serialVersionUID = 1L;

	private String bezeichung = "";
	
	private Date bewerbungsfrist = null;
	
	private String ausschreibungstext = "";
	
	/**
	 * Realisierung der Beziehung zu einem Projekt
	 */
	
	private Projekt projekt;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil
	 */
	
	private Partnerprofil partnerprofil;
	
	/**
	 * Realisierung der Beziehung zu einem Ausschreibendem
	 */
	
	private Organisationseinheit ausschreibender;
	
	
	
	public String getAusschreibungstext() {
		return ausschreibungstext;
	}

	public String getBezeichung() {
		return bezeichung;
	}

	public void setBezeichung(String bezeichung) {
		this.bezeichung = bezeichung;
	}

	public Date getBewerbungsfrist() {
		return bewerbungsfrist;
	}

	public void setBewerbungsfrist(Date bewerbungsfrist) {
		this.bewerbungsfrist = bewerbungsfrist;
	}

	public void setAusschreibungstext(String ausschreibungstext) {
		this.ausschreibungstext = ausschreibungstext;
	}
	
	
	
	
}
