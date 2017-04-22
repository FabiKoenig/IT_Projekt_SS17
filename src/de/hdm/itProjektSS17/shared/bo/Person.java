package de.hdm.itProjektSS17.shared.bo;

public class Person extends Organisationseinheit {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String anrede;
	
	private String vorname;
	
	private String nachname;
	
	
	public String getAnrede() {
		return anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
}
