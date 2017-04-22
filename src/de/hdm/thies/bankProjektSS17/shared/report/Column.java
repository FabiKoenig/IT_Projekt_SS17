package de.hdm.thies.bankProjektSS17.shared.report;

import java.io.Serializable;

public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value = "";

	
	public Column(){	
	}
	
	public Column(String v){
		this.value = v;
	}
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}

}
