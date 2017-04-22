package de.hdm.itProjektSS17.shared.bo;

public class Eigenschaft extends BusinessObject {
	
	private String name = "";
	private String wert = "";
	private int partnerprofilId = 0;
	
	private static final long serialVersionUID = 1L;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWert() {
		return wert;
	}
	
	public void setWert(String wert) {
		this.wert = wert;
	}
	
	public int getPartnerprofilId() {
		return partnerprofilId;
	}
	
	public void setPartnerprofilId(int partnerprofilId) {
		this.partnerprofilId = partnerprofilId;
	}
	
	public String toString(){
		return super.toString() + " " + this.name + ": " + this.wert;
	}
}
