package de.hdm.itProjektSS17.shared.bo;

public class Organisationseinheit extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strasse;
	
	private String hausnummer;
	
	private int plz;
	
	private String Ort;
	
	/**
	 * Realisierung der Beziehung zu einem Partnerprofil durch einen Fremdschl�ssel
	 */
	private Integer partnerprofilId = 0;
	
	
	
	
	
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
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
	public Integer getPartnerprofilId() {
		return partnerprofilId;
	}
	/**
	 * @param Setzt den Fremdschl�ssel partnerprofilId.
	 */
	public void setPartnerprofilId(Integer partnerprofilId) {
		
		if(partnerprofilId==0){
			this.partnerprofilId=null;
		}else{
			this.partnerprofilId = partnerprofilId;	
	}
	
	}
}
