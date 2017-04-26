package de.hdm.itProjektSS17.shared.bo;

public class Organisationseinheit extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String straße;
	
	private String hausnummer;
	
	private int plz;
	
	private String Ort;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil durch einen Fremdschl�ssel
	 */
	private int partnerprofilId = 0;
	
	/**
	 * Realisierung der Beziehung zu einem Projektmarktplatz durch einen Fremdschl�ssel
	 */
	private int projektmarktplatzId=0;
	
	
	
	
	
	
	public String getStrasse() {
		return straße;
	}
	public void setStrasse(String straße) {
		this.straße = straße;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return Ort;
	}
	public void setOrt(String ort) {
		Ort = ort;
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
	 * @return Gibt den Fremdschl�ssel projektmartktplatz Id zur�ck.
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
	
	
	
}
