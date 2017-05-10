package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject{


	private static final long serialVersionUID = 1L;

	private String bezeichnung = "";
	
	private Date bewerbungsfrist = null;
	
	private String ausschreibungstext = "";
	
	public enum Ausschreibungsstatus {besetzt, abgebrochen, laufend };
	
	private Ausschreibungsstatus status = null;
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
	
	
	
	public String getAusschreibungstext() {
		return ausschreibungstext;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
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
	 * @return Gibt den Fremdschl�ssel projektId zur�ck.
	 */
	public int getProjektId() {
		return projektId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel projektId.
	 */
	public void setProjektId(int projektId) {
		this.projektId = projektId;
	}

	/**
	 * @return Gibt den Fremdschl�ssel partnerprofilId zur�ck.
	 */
	public int getPartnerprofilId() {
		return partnerprofilId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel partnerprofilId.
	 */
	public void setPartnerprofilId(int partnerprofilId) {
		this.partnerprofilId = partnerprofilId;
	}

	/**
	 * @return Gibt den Fremdschl�ssel partnerprofilId zur�ck.
	 */
	public int getAusschreibenderId() {
		return ausschreibenderId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel ausschreibenderId.
	 */
	public void setAusschreibenderId(int ausschreibenderId) {
		this.ausschreibenderId = ausschreibenderId;
	}

	/**
	 * @return Gibt den Ausschreibungsstatus als enum Ausschreibungsstatus zur�ck
	 */
	public Ausschreibungsstatus getStatus() {
		return status;
	}

	/**
	 * @param Setzt den Ausschreibungsstatus anhand eines enum Ausschreibungsstatus
	 */
	public void setStatus(Ausschreibungsstatus status) {
		this.status = status;
	}
	
	


	
	
	
	
	
	
}
