package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdm.itProjektSS17.shared.bo.Partnerprofil;

/**
 * Mapper für Partnerprofil- Objekte
 */
public class PartnerprofilMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static PartnerprofilMapper partnerprofilMapper = null;
	
	/**
	 *Geschuetzter Konstruktor. Durch protected wird verhindert,
	 * dass durch "new" neue Instanzen der Klasse erzeugt werden können.
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
		        p.setAenderungdatum(rs.getDate("Aenderungsdatum"));
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
	   * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
	   */
	  public Partnerprofil findByObject(Partnerprofil p){
		//findById Methode wird aufgerufen
		 return this.findById(p.getId());
		 
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
		      //Es wird nur das Aenderungsdatum aktualisiert
		      stmt.executeUpdate("UPDATE partnerprofil SET Aenderungsdatum='" + format.format(new Date()) + "' "
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
	  
	  //Die Methode ben�tigt kein �bergebenes Partnerprofil, da die Attribute eines neuen Partnerprofils ohnehin
	  //automatisch gesetzt werden.
	  public Partnerprofil insert(){
		//Anlegen eines neuen Partnerprofil-Objekts.
		  Partnerprofil p = new Partnerprofil();
		//DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Partnerprofil_Id) AS maxid "
		         + "FROM partnerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		        if (rs.next()) {
		        /*
		         * b erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        p.setId(rs.getInt("maxid") + 1);
		        
		        //Leeres SQL-Statement für die Insert- Ausführung anlegen.
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO partnerprofil (Partnerprofil_Id, `Erstellungsdatum`) VALUES (" + p.getId() + ",'"+format.format(p.getErstellungsdatum()) + "')");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		return p;
		  
	  }
}
