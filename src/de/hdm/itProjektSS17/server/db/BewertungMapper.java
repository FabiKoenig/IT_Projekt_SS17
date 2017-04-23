package de.hdm.itProjektSS17.server.db;

import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;

/*
 * Mapper für Bewertung-Objekte
 */
public class BewertungMapper {

	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static BewertungMapper bewertungMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected BewertungMapper(){
	}
	
	/**
	 * 
	 * @return bewertungMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static BewertungMapper bewertungMapper() {
		    if (bewertungMapper == null) {
		      bewertungMapper = new BewertungMapper();
		    }

		    return bewertungMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert eine Bewertung entsprechend der übergebenen id zurueck.
	   */
	  public Bewertung findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Liefert eine Bewertung entsprechend des uebergebenen Objekts zurueck.
	   */
	  public Bewertung findByObject(Bewertung b){
		return b;
		  
	  }
	  
	  /**
	   * 
	   * @param bewerbundId
	   * @return Liefert die Bewertung zu der uebergebenen Bewerbung zurück.
	   */
	  public Bewertung findByForeignBewerbungId(int bewerbungId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Bewertung b){
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Bewertung update(Bewertung b){
		return b;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Bewertung insert(Bewertung b){
		return b;
		  
	  }
	  
}
