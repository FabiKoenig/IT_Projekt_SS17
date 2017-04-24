package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
		  // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Partnerprofil_Id, Erstellungsdatum, Aenderungsdatum FROM partnerprofil "
		          + "WHERE Partnerprofil_Id =" + id);

		      /*
		       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Partnerprofil p = new Partnerprofil();
		        p.setId(rs.getInt("Partnerprofil_Id"));
		        p.setErstellungsdatum(rs.getDate("Erstellungsdatum"));
		        p.setAenderungdatum(rs.getDate("Aenderungsdate"));
		        return p;
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
		  // DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
			  // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();
		      //Statement ausfüllen und als Update an die Datenbank schicken.
		      stmt.executeUpdate("DELETE FROM Partnerprofil " + "WHERE Partnerprofil_Id=" + p.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Partnerprofil update(Partnerprofil p){
		  //DB-Verbindung holen
		   Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen.
		      Statement stmt = con.createStatement();
		      //Statement mit Update-Befehl füllen.
		      stmt.executeUpdate("UPDATE partnerprofil " + "SET Erstellungsdatum=\""
		          + p.getErstellungsdatum() + "\", " + "Aenderungsdatum=\"" + p.getAenderungdatum() + "Bewerbung_Id=\"" + "\" "
		          + "WHERE Partnerprofil_Id=" + p.getId());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }  
		return p;
		
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Partnerprofil insert(Partnerprofil p){
		//DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      //ResultSet rs = stmt.executeQuery("SELECT MAX(Partnerprofil_Id) AS maxid "
		      //    + "FROM partnerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      //if (rs.next()) {
		        /*
		         * b erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        //p.setId(rs.getInt("maxid") + 1);
		        
		        //Leeres SQL-Statement für die Insert- Ausführung anlegen.
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO partnerprofil (Erstellungsdatum, Aenderungsdatum) VALUES ('"+p.getErstellungsdatum() + "','" + p.getAenderungdatum() + "')");
		      //}
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return p;
		  
	  }
}
