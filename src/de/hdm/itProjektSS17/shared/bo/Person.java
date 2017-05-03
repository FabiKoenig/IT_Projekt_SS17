package de.hdm.itProjektSS17.shared.bo;

public class Person extends Organisationseinheit {
	
	
	private static final long serialVersionUID = 1L;

	private String anrede;
	
	private String vorname;
	
	private String nachname;
	

	/**
	 * Realisierung der Beziehung zu einem Team durch einen Fremdschlüssel
	 */
	private Integer teamId = null;
	
	/**
	 * Realisierung der Beziehung zu einem Unternehmen durch einen Fremdschlüssel
	 */
	private Integer unternehmenId = null;


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
	
	/**
	 * @return Gibt den Fremdschlüssel teamId zurück.
	 */
	public Integer getTeamId() {
		return teamId;
	}
	/**
	 * @param Setzt den Fremdschlüssel teamId.
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	/**
	 * @return Gibt den Fremdschlüssel unternehmenId zurück.
	 */
	public Integer getUnternehmenId() {
		return unternehmenId;
	}
	
	/**
	 * @param Setzt den Fremdschlüssel unternehmenId.
	 */
	public void setUnternehmenId(Integer unternehmenId) {
		this.unternehmenId = unternehmenId;
	}
	
}
