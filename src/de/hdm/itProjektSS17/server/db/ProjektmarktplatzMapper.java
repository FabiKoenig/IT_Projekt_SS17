package de.hdm.itProjektSS17.server.db;

import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

/**
 * Mapper für Projektmarktplatz- Objekte
 */
public class ProjektmarktplatzMapper{

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected ProjektmarktplatzMapper(){
	}
	
	/**
	 * @return projektmarktplatzlMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static ProjektmarktplatzMapper projektmarktplatzMapper() {
		    if (projektmarktplatzMapper == null) {
		      projektmarktplatzMapper = new ProjektmarktplatzMapper();
		    }

		    return projektmarktplatzMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Projektmarktplatz entsprechend der übergebenen id zurueck.
	   */
	  public Projektmarktplatz findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Projektmarktplatz entsprechend des übergebenen Objekts zurueck.
	   */
	  public Projektmarktplatz findByObject(Projektmarktplatz p){
		return p;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Projektmarktplatz p){
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Projektmarktplatz update(Projektmarktplatz p){
		return p;
		
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Projektmarktplatz insert(Projektmarktplatz p){
		return p;
		  
	  }
}
