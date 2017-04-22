package de.hdm.itProjektSS17.shared.bo;

public class Organisationseinheit extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String straÃŸe;
	
	private String hausnummer;
	
	private int plz;
	
	private String Ort;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil durch einen Fremdschlüssel
	 */
	private int partnerprofilId = 0;
	
	/**
	 * Realisierung der Beziehung zu einem Projektmarktplatz durch einen Fremdschlüssel
	 */
	private int projektmarktplatzId=0;
	
	
	
	
	
	
	public String getStraÃŸe() {
		return straÃŸe;
	}
	public void setStraÃŸe(String straÃŸe) {
		this.straÃŸe = straÃŸe;
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
	 * @return Gibt den Fremdschlüssel projektmartktplatz Id zurück.
	 */
	public int getProjektmarktplatzId() {
		return projektmarktplatzId;
	}
	/**
	 * @param Setzt den Fremdschlüssel projektmarktplatzId.
	 */
	public void setProjektmarktplatzId(int projektmarktplatzId) {
		this.projektmarktplatzId = projektmarktplatzId;
	}
	
	
	
}
