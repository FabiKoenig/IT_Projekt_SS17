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
	 *
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
	 * Fremdschl�sselbeziehung zum jeweiligen Projekt.
	 */
	private int projektId = 0;
	/**
	 * Fremdschl�sselbeziehung zur jeweiligen Organisationseinheit.
	 */
	private int beteiligterId = 0;
	/**
	 * Fremdschl�sselbeziehung zur jeweiligen Bewertung.
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
	 * Auslesen des Fremdschl�ssels des dazugeh�rigen Projekts.
	 * @return der Fremdschl�ssel
	 */
	public int getProjektId() {
		return projektId;
	}
	
	/**
	 * Setzen des Fremdschl�ssels des dazugeh�rigen Projekts.
	 * @param der Fremdschl�ssel
	 */
	public void setProjektId(int projektId) {
		this.projektId = projektId;
	}
	
	/**
	 * Auslesen des Fremdschl�ssels der dazugeh�rigen Organisationseinheit.
	 * @return der Fremdschl�ssel
	 */
	public int getBeteiligterId() {
		return beteiligterId;
	}
	
	/**
	 * Setzen des Fremdschl�ssels der dazugeh�rigen Organisationseinheit.
	 * @param der Fremdschl�ssel
	 */
	public void setBeteiligterId(int beteiligterId) {
		this.beteiligterId = beteiligterId;
	}
	
	/**
	 * Auslesen des Fremdschl�ssels der dazugeh�rigen Bewertung.
	 * @return der Fremdschl�ssel
	 */
	public int getBewertungId() {
		return bewertungId;
	}
	
	/**
	 * Setzen des Fremdschl�ssels der dazugeh�rigen Bewertung.
	 * @param der Fremdschl�ssel
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
