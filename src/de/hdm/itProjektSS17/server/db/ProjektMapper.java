package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Projekt- Objekte
 */
public class ProjektMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektMapper projektMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected ProjektMapper(){
	}
	
	/**
	 * @return projektMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static ProjektMapper projektMapper() {
		    if (projektMapper == null) {
		      projektMapper = new ProjektMapper();
		    }

		    return projektMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Projekt entsprechend der übergebenen id zurueck.
	   */
	  public Projekt findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Projekt entsprechend des übergebenen Objekts zurueck.
	   */
	  public Projekt findByObject(Projekt p){
		return p;
		  
	  }
	  
	  /**
	   * 
	   * @param projektmarktplatzId
	   * @return Alle Projekte auf dem uebergebenen Projektmarktplatz werden zurueckgegeben.
	   */
	  public Vector<Projekt> findByForeignProjektmarktplatzId(int projektmarktplatzId){
		return null;
	  }
	  
	  /**
	   * 
	   * @param projektleiterId
	   * @return Liefert alle Projekt des uebergebenen Projektleiters zurueck.
	   */
	  public Vector<Projekt> findByForeignProjektleiterId(int projektleiterId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Projekt p){
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Projekt update(Projekt p){
		return p;
		
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Projekt insert(Projekt p){
		return p;
		  
	  }
}
