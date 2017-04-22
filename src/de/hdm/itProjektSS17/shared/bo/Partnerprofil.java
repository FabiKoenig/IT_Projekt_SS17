package de.hdm.itProjektSS17.shared.bo;

import java.util.Date;

public class Partnerprofil extends BusinessObject {
	
	
	private static final long serialVersionUID = 1L;
	
	private Date erstellungsdatum = null;
	private String bewerbungstext = "";
	//private int OragnisationsID = 0;
	//private int AusschreibungsID = 0;
	public Date getErstellungsdatum() {
		return erstellungsdatum;
	}
	public void setErstellungsdatum(Date erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}
	public String getBewerbungstext() {
		return bewerbungstext;
	}
	public void setBewerbungstext(String bewerbungstext) {
		this.bewerbungstext = bewerbungstext;
	}
	
	

}
