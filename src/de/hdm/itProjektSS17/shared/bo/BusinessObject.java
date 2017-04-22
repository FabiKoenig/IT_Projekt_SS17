package de.hdm.itProjektSS17.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	
	
	public String toString(){
		
		return this.getClass().getName() + " #" + this.id;
	}
	
	public boolean equals(Object o){
		
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			
			try {
				
				if (bo.getId() == this.id) {
					return true;
				}
			} catch (IllegalArgumentException e) {
				
				return false;
			}
		}
		
		return false;
	}
	
	public int hashCode(){
		
		return this.id;
	}
}
