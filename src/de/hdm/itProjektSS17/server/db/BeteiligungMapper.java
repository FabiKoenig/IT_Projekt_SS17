package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung_Id, Umfang, Startdatum, Enddatum, Bewertung_Id, Beteiligter_Id, Projekt_Id FROM beteiligung "
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
	   * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
	   */
	  public Beteiligung findByObject(Beteiligung b){
		  
			return this.findById(b.getId());
	  }
	  
	  /**
	   * 
	   * @param projektId
	   * @return Liefert alle Beteiligungen zu dem uebergebenen Projekt Projekt zurueck.
	   */
	  public Vector<Beteiligung> findByForeignProjektId(int projektId){
		  
		  // DB-Verbindung holen
		    Connection con = DBConnection.connection();
		  // Ergebnisvektor in dem die Beteiligungen zu dem Projekt abgelegt werden
		    Vector<Beteiligung> result = new Vector<Beteiligung>();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung_Id, Umfang, Startdatum, Enddatum, Bewertung_Id, Beteiligter_Id, Projekt_Id FROM beteiligung "
		          + "WHERE Projekt_Id =" + projektId);

		     
		      while (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Beteiligung b = new Beteiligung();
		        b.setId(rs.getInt("Beteiligung_Id"));
		        b.setUmfang(rs.getInt("Umfang"));
		        b.setStartDatum(rs.getDate("Startdatum"));
		        b.setEndDatum(rs.getDate("Enddatum"));
		        b.setBewertungId(rs.getInt("Bewertung_Id"));
		        b.setBeteiligterId(rs.getInt("Beteiligter_Id"));
		        b.setProjektId(rs.getInt("Projekt_Id"));
		        
		        result.add(b);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    } 
		  return result;
	  }
	  
	  /**
	   * 
	   * @param bewertungId
	   * @return Liefert eine Beteiligung entsprechend der uebergebenen Bewertung zurueck.
	   */
	  public Beteiligung findByForeignBewertung(int bewertungId){
		  
		  // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung_Id, Umfang, Startdatum, Enddatum, Bewertung_Id, Beteiligter_Id, Projekt_Id FROM beteiligung "
		          + "WHERE Bewertung_Id =" + bewertungId);

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
	   * @param organisationseinheitId
	   * @return Liefert alle Beteiligungen entsprechend der uebergebenen Organisationseinheit zurueck.
	   */
	  public Vector<Beteiligung> findByForeignBeteiligterID(int organisationseinheitId){
		  
		// DB-Verbindung holen
		    Connection con = DBConnection.connection();
		  // Ergebnisvektor in dem die Beteiligungen nach einem Beteiligten gespeichert werden
		    Vector<Beteiligung> result = new Vector<Beteiligung>();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Beteiligung_Id, Umfang, Startdatum, Enddatum, Bewertung_Id, Beteiligter_Id, Projekt_Id FROM beteiligung "
		          + "WHERE Beteiligter_Id =" + organisationseinheitId);

		     
		      while (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Beteiligung b = new Beteiligung();
		        b.setId(rs.getInt("Beteiligung_Id"));
		        b.setUmfang(rs.getInt("Umfang"));
		        b.setStartDatum(rs.getDate("Startdatum"));
		        b.setEndDatum(rs.getDate("Enddatum"));
		        b.setBewertungId(rs.getInt("Bewertung_Id"));
		        b.setBeteiligterId(rs.getInt("Beteiligter_Id"));
		        b.setProjektId(rs.getInt("Projekt_Id"));
		        
		        result.add(b);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    } 
		    	return result;
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Beteiligung b){
		  
		  // DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
			  // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();
		      //Statement ausfüllen und als Update an die Datenbank schicken.
		      stmt.executeUpdate("DELETE FROM beteiligung " + "WHERE Beteiligung_Id=" + b.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Beteiligung update(Beteiligung b){
		  //DB-Verbindung holen
		   Connection con = DBConnection.connection();
		   	
		    try {
		    	//Leeres SQL-Statement anlegen.
		      Statement stmt = con.createStatement();
		      //Statement mit Update-Befehl füllen.
		      stmt.executeUpdate("UPDATE beteiligung " + "SET Umfang="

		          + b.getUmfang() + ", " + "Startdatum='" + sdf.format(b.getStartDatum()) +"', "+ "Enddatum='" 
		          + sdf.format(b.getEndDatum())+ "' WHERE Beteiligung_Id=" + b.getId());


		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return b;
		  
	  }
	  		
	  /**
	   * 
	   * @param b
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Beteiligung insert(Beteiligung b){
		  
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Beteiligung_Id) AS maxid "
		          + "FROM beteiligung ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        b.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO beteiligung (Beteiligung_Id, Umfang, `Startdatum`, `Enddatum`, Bewertung_Id, Beteiligter_Id, Projekt_Id) "
		            + "VALUES ("
		        	+ b.getId() 
		        	+ "," 
		        	+ b.getUmfang() 
		        	+ ",'" 
		        	+ sdf.format(b.getStartDatum())
		        	+ "','" 
		        	+ sdf.format(b.getEndDatum())
		        	+ "',"
		            + b.getBewertungId() 
		            + "," 
		            + b.getBeteiligterId() 
		            + "," 
		            + b.getProjektId()
		            + ")");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return b;
		  
	  }
}
