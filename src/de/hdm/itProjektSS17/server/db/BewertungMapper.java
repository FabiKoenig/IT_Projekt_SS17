package de.hdm.itProjektSS17.server.db;

import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;


import java.sql.*;

/*
 * Mapper für Bewertung-Objekte
 */
public class BewertungMapper {

	/*
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static BewertungMapper bewertungMapper = null;
	
	/**
	 * Geschuetzter Konstruktor. Durch protected wird verhindert,
	 * dass durch "new" neue Instanzen der Klasse erzeugt werden können.
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
		    // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Bewertung_Id, Stellungnahme, Wert, Bewerbung_Id FROM bewertung "
		          + "WHERE Bewertung_Id =" + id);

		      /*
		       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Bewertung b = new Bewertung();
		        b.setId(rs.getInt("Bewertung_Id"));
		        b.setStellungsnahme(rs.getString("Stellungnahme"));
		        b.setWert(rs.getDouble("Wert"));
		        b.setBewerbungId(rs.getInt("Bewerbung_Id"));
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
	   * @return Liefert eine Bewertung entsprechend des uebergebenen Objekts zurueck.
	   */
	  public Bewertung findByObject(Bewertung b){
		//findById Methode wird aufgerufen
		  this.findById(b.getId());
		 /**
		  * @return b
		  */
		  return b;
	  }
	  
	  /**
	   * 
	   * @param bewerbundId
	   * @return Liefert die Bewertung zu der uebergebenen Bewerbung zurück.
	   */
	  public Bewertung findByForeignBewerbungId(int bewerbungId){
		    // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Bewertung_Id, Stellungnahme, Wert, Bewerbung_Id FROM bewertung "
		          + "WHERE Bewerbung_Id =" + bewerbungId);

		      /*
		       * Da id Fremdschlüssel ist und Bewerbung nur eine Bewertung erhalten kann, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Bewertung b = new Bewertung();
		        b.setId(rs.getInt("Bewertung_Id"));
		        b.setStellungsnahme(rs.getString("Stellungnahme"));
		        b.setWert(rs.getDouble("Wert"));
		        b.setBewerbungId(rs.getInt("Bewerbung_Id"));
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
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Bewertung b){
		  // DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
			  // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();
		      //Statement ausfüllen und als Update an die Datenbank schicken.
		      stmt.executeUpdate("DELETE FROM bewertung " + "WHERE Bewertung_Id=" + b.getId());
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
	  public Bewertung update(Bewertung b){
		  //DB-Verbindung holen
		   Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen.
		      Statement stmt = con.createStatement();
		      //Statement mit Update-Befehl füllen.
		      stmt.executeUpdate("UPDATE bewertung " + "SET Stellungnahme=\""

		          + b.getStellungsnahme() + "\", " + "Wert=\"" + b.getWert() + "\", " + "Bewerbung_Id=\"" + b.getBewerbungId()+ "\" "
		          + "WHERE Bewertung_Id=" + b.getId());

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
	  public Bewertung insert(Bewertung b){
		//DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Bewertung_Id) AS maxid "
		          + "FROM bewertung ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * b erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        b.setId(rs.getInt("maxid") + 1);
		        
		        System.out.println(b.getId());
		        
		        //Leeres SQL-Statement für die Insert- Ausführung anlegen.
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO bewertung (Bewertung_Id, `Stellungnahme`, Wert, Bewerbung_Id) "
		            + "VALUES (" + b.getId() + ", '" + b.getStellungsnahme() + "', " + b.getWert() + ", " 
		            + b.getBewerbungId() + ")");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return b;
		  
	  }
	  
}
