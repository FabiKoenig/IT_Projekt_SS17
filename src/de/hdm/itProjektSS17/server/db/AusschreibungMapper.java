package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Ausschreibung- Objekte
 */
public class AusschreibungMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static AusschreibungMapper ausschreibungMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected AusschreibungMapper(){
	}
	
	/**
	 * @return ausschreibungMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static AusschreibungMapper ausschreibungMapper() {
		    if (ausschreibungMapper == null) {
		      ausschreibungMapper = new AusschreibungMapper();
		    }

		    return ausschreibungMapper;
		  }
	
	  /**
	   * 
	   * @param id
	   * @return Liefert eine Ausschreibung entsprechend der übergebenen id zurueck.
	   */
	  public Ausschreibung findById(int id){
		return null;
	  }
	
	  /**
	   * 
	   * @param a
	   * @return Liefert eine Ausschreibung entsprechend des übergebenen Objekts zurueck.
	   */
	  public Ausschreibung findByObject(Ausschreibung a){
		return a;

	  }
	  /**
	   * 
	   * @param projektId
	   * @return Liefert alle Ausschreibungen zu dem uebergebenen Projekt Projekt zurueck.
	   */
	  public Vector<Ausschreibung> findByForeignProjektId(int projektId){
		return null;
	  }
	  
	  /**
	   * 
	   * @param partnerprofilId
	   * @return Liefert die Ausschreibung zu dem uebergebenen Partnerprofils zurueck.
	   */
	  public Ausschreibung findByForeignPartnerprofilId(int partnerprofilId){
		return null; 
	  }
	  
	  /**
	   * 
	   * @param organisationseinheitId
	   * @return Liefert alle Ausschreibungen der uebergebenen Organisagtionseinheit zurueck. 
	   */
	  public Vector<Ausschreibung> findByForeignAusschreibenderId(int organisationseinheitId){
		return null;  
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Ausschreibung a){
		  
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Ausschreibung update(Ausschreibung a){
		return a;
		  
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Ausschreibung insert(Ausschreibung a){
		return a;
		  
	  }
}
