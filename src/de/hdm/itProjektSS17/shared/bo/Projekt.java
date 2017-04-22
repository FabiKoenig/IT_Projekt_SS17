package de.hdm.itProjektSS17.shared.bo;

import java.sql.Date;

public class Projekt {

	private Date startdatum; 
	private Date enddatum;
	private String name;
	private String beschreibung;
	
	
	public Date getStartdatum(){
		return startdatum;
	}
	public void setStartdatum(Date startdatum){
		this.startdatum = startdatum;
	}
	public Date getEnddatum() {
		return enddatum;
	}
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
}
