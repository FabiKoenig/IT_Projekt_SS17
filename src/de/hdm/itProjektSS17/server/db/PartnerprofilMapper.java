package de.hdm.itProjektSS17.server.db;

import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;

/**
 * Mapper für Partnerprofil- Objekte
 */
public class PartnerprofilMapper {

	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static PartnerprofilMapper partnerprofilMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected PartnerprofilMapper(){
	}
	
	/**
	 * @return partnerprofilMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static PartnerprofilMapper partnerprofilMapper() {
		    if (partnerprofilMapper == null) {
		      partnerprofilMapper = new PartnerprofilMapper();
		    }

		    return partnerprofilMapper;
		  }
	  
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Partnerprofil entsprechend der übergebenen id zurueck.
	   */
	  public Partnerprofil findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Partnerprofil entsprechend des übergebenen Objekts zurueck.
	   */
	  public Partnerprofil findByObject(Partnerprofil p){
		return p;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Partnerprofil p){
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Partnerprofil update(Partnerprofil p){
		return p;
		
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Partnerprofil insert(Partnerprofil p){
		return p;
		  
	  }
}
