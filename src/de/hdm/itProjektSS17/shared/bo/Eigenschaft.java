package de.hdm.itProjektSS17.shared.bo;

/**
 * Realisierung einer Eigenschaftenbeschreibung. Eiene Eigenschaft einen Namen und einen Wert.
 * Eine Eigenschaft zeichnet sich daraus aus, dass sie einem Partnerprofil untergeordnet ist. 
 * 
 * @author Michael Geiselmann
 * @version 1.0
 *
 */
public class Eigenschaft extends BusinessObject {
	
	/**
	 * Name der Eigenschaft
	 */
	private String name = "";
	/**
	 * Wert der Eigenschaft
	 */
	private String wert = "";
	/**
	 * Fremdschl�sselbeziehung zum jeweiligen Partnerprofil.
	 */
	private int partnerprofilId = 0;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Auslesen des Eigenschaftennamens.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setzen des Eigenschaftensnamens.
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 * Auslesen des Eigenschaftenwerts.
	 *  @return wert
	 */
	public String getWert() {
		return wert;
	}
	
	/**
	 * Setzen des Eigenschaftenwerts.
	 * @param wert
	 */
	public void setWert(String wert) {
		this.wert = wert;
	}
	
	/**
	 * Auslesen des Fremdschl�ssels des dazugeh�rigen Partnerprofils.
	 * @return partnerprofil
	 */
	public int getPartnerprofilId() {
		return partnerprofilId;
	}
	
	/**
	 * Setzen des Fremdschl�ssels des dazugeh�rigen Partnerprofils
	 * @param partnerprofilId
	 */
	public void setPartnerprofilId(int partnerprofilId) {
		this.partnerprofilId = partnerprofilId;
	}
	
	/**
	 * Erzeugen einer textuellen Darstellung der jeweiligen Eigenschaft.
	 */
	public String toString(){
		return super.toString() + " " + this.name + ": " + this.wert;
	}
}
