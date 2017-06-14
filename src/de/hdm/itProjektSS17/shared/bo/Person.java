package de.hdm.itProjektSS17.shared.bo;

public class Person extends Organisationseinheit {
	
	
	private static final long serialVersionUID = 1L;
	
	private String email;

	private String anrede;
	
	private String vorname;
	
	private String nachname;
	

	/**
	 * Realisierung der Beziehung zu einem Team durch einen Fremdschl�ssel
	 */
	private Integer teamId = null;
	
	/**
	 * Realisierung der Beziehung zu einem Unternehmen durch einen Fremdschl�ssel
	 */
	private Integer unternehmenId = null;
	
	/**
	 * 
	 * @return anrede
	 */
	public String getAnrede() {
		return anrede;
	}
	
	/**
	 * 
	 * @param anrede
	 */
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	/**
	 * 
	 * @return vorname
	 */
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNachname() {
		return nachname;
	}
	
	/**
	 * 
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	/**
	 * @return Gibt den Fremdschl�ssel teamId zur�ck.
	 */
	public Integer getTeamId() {
		return teamId;
	}
	/**
	 * @param Setzt den Fremdschl�ssel teamId.
	 */
	public void setTeamId(Integer teamId) {
		if(teamId==0){
			this.teamId=null;
		}else{
			this.teamId = teamId;	
		}
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
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
