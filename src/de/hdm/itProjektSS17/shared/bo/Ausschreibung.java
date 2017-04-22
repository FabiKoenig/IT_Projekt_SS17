package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject{


	private static final long serialVersionUID = 1L;

	private String bezeichung = "";
	
	private Date bewerbungsfrist = null;
	
	private String ausschreibungstext = "";
	
	/**
	 * Realisierung der Beziehung zu einem Projekt durch einen Fremdschlüssel
	 */
	
	private int projektId = 0;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil durch einen Fremdschlüssel
	 */
	
	private int partnerprofilId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Organisationseinheit durch einen Fremdschlüssel
	 */
	
	private int ausschreibenderId = 0;
	
	
	
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

	/**
	 * @return Gibt den Fremdschlüssel projektId zurück.
	 */
	public int getProjektId() {
		return projektId;
	}

	/**
	 * @param Setzt den Fremdschlüssel projektId.
	 */
	public void setProjektId(int projektId) {
		this.projektId = projektId;
	}

	/**
	 * @return Gibt den Fremdschlüssel partnerprofilId zurück.
	 */
	public int getPartnerprofilId() {
		return partnerprofilId;
	}

	/**
	 * @param Setzt den Fremdschlüssel partnerprofilId.
	 */
	public void setPartnerprofilId(int partnerprofilId) {
		this.partnerprofilId = partnerprofilId;
	}

	/**
	 * @return Gibt den Fremdschlüssel partnerprofilId zurück.
	 */
	public int getAusschreibenderId() {
		return ausschreibenderId;
	}

	/**
	 * @param Setzt den Fremdschlüssel ausschreibenderId.
	 */
	public void setAusschreibenderId(int ausschreibenderId) {
		this.ausschreibenderId = ausschreibenderId;
	}


	
	
	
	
	
	
}
