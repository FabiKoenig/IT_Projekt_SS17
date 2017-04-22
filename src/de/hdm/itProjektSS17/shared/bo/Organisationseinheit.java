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
	
	
	
	
	
	
	public String getStraße() {
		return straße;
	}
	public void setStraße(String straße) {
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
	
}
