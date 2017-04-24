package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;

/**
 * Mapper für Person- Objekte
 */
public class PersonMapper {

	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static PersonMapper personMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected PersonMapper(){
	}
	
	/**
	 * @return personMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static PersonMapper personMapper() {
		    if (personMapper == null) {
		      personMapper = new PersonMapper();
		    }

		    return personMapper;
		  }
	  
	/**
	 *   
	 * @param id
	 * @return Liefert eine Person entsprechend der übergebenen id zurueck.
	 */
	public Person findById(int id){
		return null;
		
	}
	
	/**
	 * 
	 * @param p
	 * @return Liefert eine Person entsprechend des übergebenen Objekts zurueck.
	 */
	public Person findByObject(Person p){
		return p;
		
	}
	
	/**
	 * 
	 * @param teamId
	 * @return Liefert alle Personen des uebergebenen Teams zurueck.
	 */
	public Vector<Person> findByForeignTeamId(int teamId){
		return null;
		
	}
	/**
	 * 
	 * @param unternehmenId
	 * @return Liefer alle Personen des uebergebenen Unternehmens zurueck.
	 */
	public Vector<Person> findByForeignUnternehmenId(int unternehmenId){
		return null;
	}
	
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Person p){
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Person update(Person p){
		return p;
		
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Person insert(Person p){
		return p;
		  
	  }
}
