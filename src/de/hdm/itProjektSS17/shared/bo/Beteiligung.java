package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

/**
 * Realisierung einer Beteiligungsbeschreibung. Eine Beteiligung besitzt einen Umfang an Personentage,
 * ein Startdatum und ein Enddatum. Eine Beteiligung zeichnet sich daraus, dass eine Organisationseinheit
 * durch eine positive Bewertung zu einem Projekt zugewiesen wird.   
 * 
 * @author Michael Geiselmann
 * @version 1.0
 *
 */
public class Beteiligung extends BusinessObject {

	/**
	 * Umfang der Tage, an dem eine Organisationseinheit an einem Projekt beteiligt ist.
	 */
	private int umfang = 0;
	/**
	 * Datum, an dem die Beteiligung beginnt.
	 */
	private Date startDatum = null;
	/**
	 * Datum, an dem die Beteiligung endet.
	 */
	private Date endDatum = null;
	/**
	 * Fremdschlüsselbeziehung zum jeweiligen Projekt.
	 */
	private int projektId = 0;
	/**
	 * Fremdschlüsselbeziehung zur jeweiligen Organisationseinheit.
	 */
	private int beteiligterId = 0;
	/**
	 * Fremdschlüsselbeziehung zur jeweiligen Bewertung.
	 */
	private int bewertungId = 0;
	
	private static final long serialVersionUID = 1L;	
	
	/**
	 * Auslesen des Beteiligungsumfangs.
	 * @return Umfang
	 */
	public int getUmfang() {
		return umfang;
	}
	
	/**
	 * Setzen des Beteiligungsumfangs.
	 * @param umfang
	 */
	public void setUmfang(int umfang) {
		this.umfang = umfang;
	}
	
	/**
	 * Auslesen des Startdatums.
	 * @return Startdatum
	 */
	public Date getStartDatum() {
		return startDatum;
	}
	
	/**
	 * Setzen des Startdatums.
	 * @param startDatum
	 */
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}
	
	/**
	 * Auslesen des Enddatums.
	 * @return Enddatum
	 */
	public Date getEndDatum() {
		return endDatum;
	}
	
	/**
	 * Setzen des Enddatums.
	 * @param Enddatum
	 */
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}
	
	/**
	 * Auslesen des Fremdschlüssels des dazugehörigen Projekts.
	 * @return der Fremdschlüssel
	 */
	public int getProjektId() {
		return projektId;
	}
	
	/**
	 * Setzen des Fremdschlüssels des dazugehörigen Projekts.
	 * @param der Fremdschlüssel
	 */
	public void setProjektId(int projektId) {
		this.projektId = projektId;
	}
	
	/**
	 * Auslesen des Fremdschlüssels der dazugehörigen Organisationseinheit.
	 * @return der Fremdschlüssel
	 */
	public int getBeteiligterId() {
		return beteiligterId;
	}
	
	/**
	 * Setzen des Fremdschlüssels der dazugehörigen Organisationseinheit.
	 * @param der Fremdschlüssel
	 */
	public void setBeteiligterId(int beteiligterId) {
		this.beteiligterId = beteiligterId;
	}
	
	/**
	 * Auslesen des Fremdschlüssels der dazugehörigen Bewertung.
	 * @return der Fremdschlüssel
	 */
	public int getBewertungId() {
		return bewertungId;
	}
	
	/**
	 * Setzen des Fremdschlüssels der dazugehörigen Bewertung.
	 * @param der Fremdschlüssel
	 */
	public void setBewertungId(int bewertungId) {
		this.bewertungId = bewertungId;
	}

	/**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Beteiligung.
	 */
	public String toString(){
		
		return super.toString() + "Beteiligung zwischen " + beteiligterId + " und " + projektId; 
	}
}
