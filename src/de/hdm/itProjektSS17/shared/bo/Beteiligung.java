package de.hdm.itProjektSS17.shared.bo;

import java.sql.Date;

public class Beteiligung extends BusinessObject {

	private int umfang = 0;
	private Date startDatum = null;
	private Date endDatum = null;
	private int projektId = 0;
	private int beteiligterId = 0;
	private int bewertungId = 0;
	
	private static final long serialVersionUID = 1L;	
	
	
	public int getUmfang() {
		return umfang;
	}
	
	public void setUmfang(int umfang) {
		this.umfang = umfang;
	}
	
	public Date getStartDatum() {
		return startDatum;
	}
	
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}
	
	public Date getEndDatum() {
		return endDatum;
	}
	
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}
	
	public int getProjektId() {
		return projektId;
	}
	
	public void setProjekt(int projektId) {
		this.projektId = projektId;
	}
	
	public int getBeteiligterId() {
		return beteiligterId;
	}
	
	public void setBeteiligter(int beteiligterId) {
		this.beteiligterId = beteiligterId;
	}
	
	public int getBewertungId() {
		return bewertungId;
	}
	
	public void setBewertung(int bewertung) {
		this.bewertungId = bewertungId;
	}
	
	public String toString(){
		
		return super.toString() + "Beteiligung zwischen " + beteiligterId + " und " + projektId; 
	}
}
