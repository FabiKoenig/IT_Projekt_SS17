package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;

/*
 * Mapper für Eigenschaft-Objekte.
 */
public class EigenschaftMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static EigenschaftMapper eigenschaftMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected EigenschaftMapper(){
	}
	
	/**
	 * @return eigenschaftMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static EigenschaftMapper eigenschaftMapper() {
		    if (eigenschaftMapper == null) {
		      eigenschaftMapper = new EigenschaftMapper();
		    }

		    return eigenschaftMapper;
		  }
	  
	  /**
	     * @param id 
	     * @return Liefert eine Eigenschaft entsprechend der uebergebenen id zurueck
	     */
	    public Eigenschaft findById(int id) {
	        return null;
	    }
	    
	    /**
	     * @param e
	     * @return Liefert eine Eigenschaft entsprechend des uebergebenen Objekts zurueck.
	     */
	    public Bewerbung findByObject(Eigenschaft e) {
	        return null;
	    }
	    
	    /**
	     * 
	     * @param partnerprofilId
	     * @return Liefert alle Eigenschaften zu dem uebergenen Partnerprofil
	     */
	    public Vector<Eigenschaft> findByForeignPartnerprofilId(int partnerprofilId){
			return null;
	    }
	  
	  /**
	   * 
	   * @param e
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Eigenschaft e){
		  
	  }
	  
	  /**
	   * 
	   * @param e
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Eigenschaft update(Eigenschaft e){
		return e;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Eigenschaft insert(Eigenschaft e){
		return e;
		  
	  }
}

