package de.hdm.itProjektSS17.shared.bo;

public class Bewertung extends BusinessObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stellungsnahme = "";
	
	private double bewerbungswert = 0.0;

	/**
	 * Realisierung der Beziehung zu einer Bewerbung durch einen Fremdschl�ssel
	 */
	private int bewerbungId = 0;
	
	public String getStellungsnahme() {
		return stellungsnahme;
	}

	public void setStellungsnahme(String stellungsnahme) {
		this.stellungsnahme = stellungsnahme;
	}

	public double getBewerbungswert() {
		return bewerbungswert;
	}

	public void setBewerbungswert(double bewerbungswert) {
		this.bewerbungswert = bewerbungswert;
	}

	/**
	 * @return Gibt den Fremdschl�ssel bewerbungId zur�ck. 
	 */
	public int getBewerbungId() {
		return bewerbungId;
	}

	/**
	 * @param Setzt den Fremdschl�ssel bewerbungId.
	 */
	public void setBewerbungId(int bewerbungId) {
		this.bewerbungId = bewerbungId;
	}
	
	

	
	
}
