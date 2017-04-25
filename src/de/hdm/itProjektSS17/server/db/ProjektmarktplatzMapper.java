package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

/**
 * Mapper für Projektmarktplatz- Objekte
 */
public class ProjektmarktplatzMapper{

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	/**
	 * Geschuetzter Konstruktor um zu verhindern, dass Objekte dieser Klasse erstellt nicht au�erhalb
	 * der Vererbungshierarchie dieser Klasse erstellt werden.
	 */
	protected ProjektmarktplatzMapper(){
	}
	
	/**
	 * @return projektmarktplatzlMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static ProjektmarktplatzMapper projektmarktplatzMapper() {
		    if (projektmarktplatzMapper == null) {
		      projektmarktplatzMapper = new ProjektmarktplatzMapper();
		    }

		    return projektmarktplatzMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Projektmarktplatz entsprechend der übergebenen id zurueck.
	   */
	  public Projektmarktplatz findById(int id){
	        // DB-Verbindung holen
	        Connection con = DBConnection.connection();

	        try {
	          // Leeres SQL-Statement (JDBC) anlegen
	          Statement stmt = con.createStatement();

	          // Statement ausfüllen und als Query an die DB schicken
	          ResultSet rs = stmt.executeQuery("SELECT * FROM projektmarktplatz "
	              + "WHERE Projektmarktplatz_Id=" + id);

	          /*
	           * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	           * werden. Prüfe, ob ein Ergebnis vorliegt.
	           */
	          if (rs.next()) {
	            // Ergebnis-Tupel in Objekt umwandeln
	            Projektmarktplatz p = new Projektmarktplatz();
	            p.setId(rs.getInt("Projektmarktplatz_Id"));
	            p.setBezeichnung(rs.getString("Bezeichnung"));
	            return p;
	          }
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	          return null; 
	        }
	        
	        return null;
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Projektmarktplatz entsprechend des übergebenen Objekts zurueck.
	   */
	  public Projektmarktplatz findByObject(Projektmarktplatz p){
		  return this.findById(p.getId());	  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Projektmarktplatz p){
	        Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          stmt.executeUpdate("DELETE FROM projektmarktplatz " + "WHERE id=" + p.getId());

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
	  public Projektmarktplatz update(Projektmarktplatz p){
	        Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          stmt.executeUpdate("UPDATE Bewerbung SET Bezeichnung ='"+p.getBezeichnung()
	        		  +"WHERE CustomerID ="+ p.getId()+"; ");

	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }

	        // Um Analogie zu insert(Account a) zu wahren, geben wir a zurück
	        return p;
		
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Projektmarktplatz insert(Projektmarktplatz p){
	        Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          /*
	           * Zunächst schauen wir nach, welches der momentan höchste
	           * Primärschlüsselwert ist.
	           */
	          
	          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	                  + "FROM projektmarktplatz ");

	              // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	              if (rs.next()) {
	                /*
	                 * a erhält den bisher maximalen, nun um 1 inkrementierten
	                 * Primärschlüssel.
	                 */
	                p.setId(rs.getInt("maxid") + 1);
	              }

	           stmt.executeUpdate("INSERT INTO accounts (Projektmarktplatz_Id, Bezeichnung) " 
	           + "VALUES ('" + p.getId() + "','" + p.getBezeichnung()+"')");
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	        }

	        /*
	         * Rückgabe, des evtl. korrigierten Accounts.
	         * 
	         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	         * Objekte übergeben werden, wäre die Anpassung des Account-Objekts auch
	         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
	         * explizite Rückgabe von a ist eher ein Stilmittel, um zu signalisieren,
	         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	         */
	        return p;
		  
	  }
}
