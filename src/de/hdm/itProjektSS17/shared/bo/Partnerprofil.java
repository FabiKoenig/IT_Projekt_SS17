package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;
/**
 * Realisierung eines Partnerprofils. Ein Partnerprofil besitzt ein Erstellungsdatum 
 * und ein �nderungsdatum.
 * 
 * @author Tom Alender
 * @version 1.0
 */
public class Partnerprofil extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	   * Datum der Erstellung des Partnerprofils
	   */
	private Date erstellungsdatum = new Date();
	
	/**
	   * Datum der �nderung des Partnerprofils
	   */
	private Date aenderungdatum = null;
	
	//private int OragnisationsID = 0;
	//private int AusschreibungsID = 0;
	
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
	 * 
	 * @return aenderungdatum
	 */
	public Date getAenderungdatum() {
		return aenderungdatum;
	}
	
	/**
	 * 
	 * @param aenderungdatum
	 */
	public void setAenderungdatum(Date aenderungdatum) {
		this.aenderungdatum = aenderungdatum;
	}
	
	
	

}
