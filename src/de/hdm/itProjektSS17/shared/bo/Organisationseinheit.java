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
	
	
	
	
	/**
	 * 
	 * @return strasse
	 */
	
	public String getStrasse() {
		return strasse;
	}
	
	/**
	 * 
	 * @param strasse
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getHausnummer() {
		return hausnummer;
	}
	
	/**
	 * 
	 * @param hausnummer
	 */
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	/**
	 * 
	 * @return plz 
	 */
	
	public int getPlz() {
		return plz;
	}
	
	/**
	 * 
	 * @param plz
	 */
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getOrt() {
		return Ort;
	}
	
	/**
	 * 
	 * @param ort
	 */
	public void setOrt(String ort) {
		Ort = ort;
	}
	/**
	 * @return partnerprofilId - Gibt den Fremdschl�ssel partnerprofilId zur�ck.
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
