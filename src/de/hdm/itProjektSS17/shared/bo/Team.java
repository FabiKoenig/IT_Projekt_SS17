package de.hdm.itProjektSS17.shared.bo;

public class Team extends Organisationseinheit{

	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * Realisierung der Beziehung zu einem Unternehmen durch einen Fremdschl�ssel
	 */
	private Integer unternehmenId = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Gibt den Fremdschl�ssel unternehmenId zur�ck.
	 */
	public Integer getUnternehmenId() {
		return unternehmenId;
	}
	
	/**
	 * @param Setzt den Fremdschl�ssel unternehmenId.
	 */
	public void setUnternehmenId(Integer unternehmenId) {
		if(unternehmenId==0){
			this.unternehmenId=null;
		}else{
			this.unternehmenId = unternehmenId;
		}
	}

}
