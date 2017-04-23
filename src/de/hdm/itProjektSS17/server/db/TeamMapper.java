package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;

/**
 * Mapper für Team- Objekte
 */
public class TeamMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static TeamMapper teamMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected TeamMapper(){
	}
	
	/**
	 * @return teamMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static TeamMapper teamMapper() {
		    if (teamMapper == null) {
		      teamMapper = new TeamMapper();
		    }

		    return teamMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Team entsprechend der übergebenen id zurueck.
	   */
	  public Team findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Liefert ein Team entsprechend des übergebenen Objekts zurueck.
	   */
	  public Team findByObject(Team t){
		return t;
		  
	  }
	  
	  /**
	   * 
	   * @param unternehmenId
	   * @return Liefert alle Team-Objekte des uebergebenen Unternehmens zurueck.
	   */
	  public Vector<Team> findByForeignUnternehmenId(int unternehmenId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Team t){
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Team update(Team t){
		return t;
		
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Team insert(Team t){
		return t;
		  
	  }
}
