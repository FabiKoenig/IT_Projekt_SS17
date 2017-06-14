package de.hdm.itProjektSS17.shared.bo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Projekt extends BusinessObject{
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * Startdatum des Projekt.
	   */
	private Date startdatum = null; 
	
	/**
	   * Enddatum des Projekt
	   */
	private Date enddatum = null;
	
	/**
	   * Name des Projekt
	   */
	private String name;
	
	/**
	   * Beschreibung des Projekt
	   */
	private String beschreibung;
	
	/**
	 * Realisierung der Beziehung zu einem Projektmarktplatz durch einen Fremdschl�ssel.
	 */
	private int projektmarktplatzId = 0;
	
	/**
	 * Realisierung der Beziehung zu einer Person durch einen Fremdschl�ssel.
	 */
	private int projektleiterId = 0;
	
	/**
	 * 
	 * @return startdatum
	 */
	public Date getStartdatum(){
		return startdatum;
	}
	
	/**
	 * 
	 * @param startdatum
	 */
	public void setStartdatum(Date startdatum){
		this.startdatum = startdatum;
	}
	
	 /**
	  * 
	  * @return
	  */
	public Date getEnddatum() {
		return enddatum;
	}
	
	/**
	 * 
	 * @param enddatum
	 */
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * 
	 * @param beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return projektmarktplatzId - Gibt den Fremdschl�ssel projektmarktplatzId zur�ck.
	 */
	public int getProjektmarktplatzId() {
		return projektmarktplatzId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel projektmarktplatzId.
	 */
	public void setProjektmarktplatzId(int projektmarktplatzId) {
		this.projektmarktplatzId = projektmarktplatzId;
	}

	/**
	 * @return projektleiterId - Gibt den Fremdschl�ssel projektleiterId zur�ck.
	 */
	public int getProjektleiterId() {
		return projektleiterId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel projektleiterId.
	 */
	public void setProjektleiterId(int projektleiterId) {
		this.projektleiterId = projektleiterId;
	}
	
	
}
