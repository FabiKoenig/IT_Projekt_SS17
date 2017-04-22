package de.hdm.itProjektSS17.shared.bo;

public class Team extends Organisationseinheit{

	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * Realisierung der Beziehung zu einem Unternehmen durch einen Fremdschlüssel
	 */
	private int unternehmenId = 0;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Gibt den Fremdschlüssel unternehmenId zurück.
	 */
	public int getUnternehmenId() {
		return unternehmenId;
	}
	
	/**
	 * @param Setzt den Fremdschlüssel unternehmenId.
	 */
	public void setUnternehmenId(int unternehmenId) {
		this.unternehmenId = unternehmenId;
	}

}
