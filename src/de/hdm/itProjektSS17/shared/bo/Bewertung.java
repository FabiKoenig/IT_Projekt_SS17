package de.hdm.itProjektSS17.shared.bo;

public class Bewertung extends BusinessObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stellungsnahme = "";
	
	private double bewerbungswert = 0.0;

	
	
	
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
	
	

	
	
}
