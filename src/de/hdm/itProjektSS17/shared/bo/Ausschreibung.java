package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject{


	private static final long serialVersionUID = 1L;

	private String bezeichnung = "";
	
	private Date bewerbungsfrist = null;
	
	private String ausschreibungstext = "";
	
	public enum Ausschreibungsstatus {besetzt, abgebrochen, laufend };
	
	private Ausschreibungsstatus status = Ausschreibungsstatus.laufend;
	/**
	 * Realisierung der Beziehung zu einem Projekt durch einen Fremdschl�ssel
	 */
	
	private int projektId = 0;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil durch einen Fremdschl�ssel
	 */
	
	private int partnerprofilId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Organisationseinheit durch einen Fremdschl�ssel
	 */
	
	private int ausschreibenderId = 0;
	
	/**
	 * 
	 * @return ausschreibungstext
	 */
	
	public String getAusschreibungstext() {
		return ausschreibungstext;
	}
	/**
	 * 
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}
	/**
	 * 
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * 
	 * @return bewerbungsfrist
	 */
	public Date getBewerbungsfrist() {
		return bewerbungsfrist;
	}
	
	/**
	 * 
	 * @param bewerbungsfrist
	 */
	public void setBewerbungsfrist(Date bewerbungsfrist) {
		this.bewerbungsfrist = bewerbungsfrist;
	}

	/**
	 * 
	 * @param ausschreibungstext
	 */
	
	public void setAusschreibungstext(String ausschreibungstext) {
		this.ausschreibungstext = ausschreibungstext;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getProjektId() {
		return projektId;
	}

	/**
	 * 
	 * @param projektId
	 */
	public void setProjektId(int projektId) {
		this.projektId = projektId;
	}

	/**
	 * 
	 * @return partnerprofilId
	 */
	public int getPartnerprofilId() {
		return partnerprofilId;
	}

	
	/**
	 * 
	 * @param partnerprofilId
	 */
	public void setPartnerprofilId(int partnerprofilId) {
		this.partnerprofilId = partnerprofilId;
	}

	/**
	 * 
	 * @return ausschreibenderId
	 */	
	public int getAusschreibenderId() {
		return ausschreibenderId;
	}

	/**
	 * 
	 * @param ausschreibenderId
	 */
	public void setAusschreibenderId(int ausschreibenderId) {
		this.ausschreibenderId = ausschreibenderId;
	}

	
	/**
	 * @return status
	 */
	
	public Ausschreibungsstatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */	
	public void setStatus(Ausschreibungsstatus status) {
		this.status = status;
	}
	
	


	
	
	
	
	
	
}
