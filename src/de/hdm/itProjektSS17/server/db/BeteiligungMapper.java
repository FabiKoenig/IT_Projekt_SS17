package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/*
 * Mapper für Beteiligung-Objekte.
 */
public class BeteiligungMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static BeteiligungMapper beteiligungMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected BeteiligungMapper(){
	}
	
	/**
	 * @return beteiligungMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static BeteiligungMapper beteiligungMapper() {
		    if (beteiligungMapper == null) {
		      beteiligungMapper = new BeteiligungMapper();
		    }

		    return beteiligungMapper;
		  }
	
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert eine Beteiligung entsprechend der übergebenen id zurueck.
	   */
	  public Beteiligung findById(int id){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Liefert eine Beteiligung entsprechend des uebergebenen Objekts zurueck.
	   */
	  public Beteiligung findByObject(Beteiligung b){
		return b;
	  }
	  
	  /**
	   * 
	   * @param projektId
	   * @return Liefert alle Beteiligungen zu dem uebergebenen Projekt Projekt zurueck.
	   */
	  public Vector<Beteiligung> findByForeignProjektId(int projektId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param bewertungId
	   * @return Liefert eine Beteiligung entsprechend der uebergebenen Bewertung zurueck.
	   */
	  public Beteiligung findByForeignBewertung(int bewertungId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param organisationseinheitId
	   * @return Liefert alle Beteiligungen entsprechend der uebergebenen Organisationseinheit zurueck.
	   */
	  public Vector<Beteiligung> findByForeignBeteiligterID(int organisationseinheitId){
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Beteiligung b){
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Beteiligung update(Beteiligung b){
		return b;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Beteiligung insert(Beteiligung b){
		return b;
		  
	  }
}
