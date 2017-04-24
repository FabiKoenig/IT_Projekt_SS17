package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
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
		  // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung_Id, Umfang, Startdatum, Enddatum, Bewertung_Id, Beteiligter_Id, Projekt_Id FROM partnerprofil "
		          + "WHERE Beteiligung_Id =" + id);

		      /*
		       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Beteiligung b = new Beteiligung();
		        b.setId(rs.getInt("Beteiligung_Id"));
		        b.setUmfang(rs.getInt("Umfang"));
		        b.setStartDatum(rs.getDate("Startdatum"));
		        b.setEndDatum(rs.getDate("Enddatum"));
		        b.setBewertungId(rs.getInt("Bewertung_Id"));
		        b.setBeteiligterId(rs.getInt("Beteiligter_Id"));
		        b.setProjektId(rs.getInt("Projekt_Id"));
		        return b;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }
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
