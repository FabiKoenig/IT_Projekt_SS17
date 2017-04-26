package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.itProjektSS17.shared.bo.*;

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
	    	// DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft "
		          + "WHERE Eigenschaft_Id =" + id);

		      /*
		       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Eigenschaft e = new Eigenschaft();
		        e.setId(rs.getInt("Eigenschaft_Id"));
		        e.setName(rs.getString("Name"));
		        e.setWert(rs.getString("Wert"));
		        e.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
		        return e;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }
		return null;
		  
	  }
	  
	    /**
	     * @param e
	     * @return Liefert eine Eigenschaft entsprechend des uebergebenen Objekts zurueck.
	     */
	    public Eigenschaft findByObject(Eigenschaft e) {
	      
	    	this.findById(e.getId());
	    	return e;
	    }
	    
	    /**
	     * 
	     * @param partnerprofilId
	     * @return Liefert alle Eigenschaften zu dem uebergenen Partnerprofil
	     */
	    public Vector<Eigenschaft> findByForeignPartnerprofilId(int partnerprofilId){
	    	
	    	Vector<Eigenschaft> eObj = new Vector<Eigenschaft>();
	    	// DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();

		      // Statement ausfüllen und als Query an die DB schicken
		      ResultSet rs = stmt.executeQuery("SELECT Eigenschaft_Id, Name, Wert, Partnerprofil_Id FROM eigenschaft "
		          + "WHERE Partnerprofil_Id =" + partnerprofilId +"ORDER BY Eigenschaft_Id");

		      /*
		       * Da id Fremdschlüssel ist und Partnerprofile nur eine Eigenschaft erhalten kann, kann max. nur ein Tupel zurückgegeben
		       * werden. Prüfe, ob ein Ergebnis vorliegt.
		       */
		      while (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		    	  Eigenschaft e = new Eigenschaft();
			       e.setId(rs.getInt("Eigenschaft_Id"));
			       e.setName(rs.getString("Name"));
			       e.setWert(rs.getString("Wert"));
			       e.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
			       eObj.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }  
		return eObj;
	    }
	  
	  /**
	   * 
	   * @param e
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Eigenschaft e){
		  
		  Connection con = DBConnection.connection();

		    try {
			  // Leeres SQL-Statement (JDBC) anlegen
		      Statement stmt = con.createStatement();
		      //Statement ausfüllen und als Update an die Datenbank schicken.
		      stmt.executeUpdate("DELETE FROM eigenschaft " + "WHERE Eigenschaft_Id=" + e.getId());
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		  }

		  
	  
	  
	  /**
	   * 
	   * @param e
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Eigenschaft update(Eigenschaft e){
		
		  //DB-Verbindung holen
		   Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen.
		      Statement stmt = con.createStatement();
		      //Statement mit Update-Befehl füllen.
		      stmt.executeUpdate("UPDATE eigenschaft " + "SET Name=\""
		          + e.getName() + "\", " + "Wert=\"" + e.getWert() + "Partnerprofil_Id=\"" + e.getPartnerprofilId()+ "\" "
		          + "WHERE Eigenschaft_Id=" + e.getId());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
	
		  return e;
		  
	  }
	  
	  /**
	   * 
	   * @param b
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Eigenschaft insert(Eigenschaft e){
		
		  //DB-Verbindung holen
		  Connection con = DBConnection.connection();

		    try {
		    	//Leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Eigenschaft_Id) AS maxid "
		          + "FROM bewertung ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * b erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        e.setId(rs.getInt("maxid") + 1);
		        
		        //Leeres SQL-Statement für die Insert- Ausführung anlegen.
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO eigenschaft (Eigenschaft_Id, Name, Wert, Partnerprofil_Id) "
		            + "VALUES (" + e.getId() + ",'" + e.getName() + "','" + e.getWert() + "'.'" + 
		            + e.getPartnerprofilId() + "')");
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		return e;
		  
	  }
}

