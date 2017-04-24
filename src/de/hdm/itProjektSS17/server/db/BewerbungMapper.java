package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper fuer ein Bewerbung-Objekt
 */
public class BewerbungMapper {

    /**
     * Speicherung der einzigen Instanz dieser Mapperklasse.
     */
    private static BewerbungMapper bewerbungMapper = null;

    /**
     * Geschuetzter Konstruktor
     */
    protected BewerbungMapper() {
        // TODO implement here
    }

    /**
     * @return Sicherstellung der Singleton-Eigenschaft der Mapperklasse.
     */
    public static BewerbungMapper bewerbungMapper() {
        if(bewerbungMapper == null){
        	bewerbungMapper = new BewerbungMapper();
        }
        return bewerbungMapper;
    }

    /**
     * @param id 
     * @return Liefert eine Bewerbung entsprechend der uebergebenen id zurueck
     */
    public Bewerbung findById(int id) {
        // DB-Verbindung holen
        Connection con = DBConnection.connection();

        try {
          // Leeres SQL-Statement (JDBC) anlegen
          Statement stmt = con.createStatement();

          // Statement ausfüllen und als Query an die DB schicken
          ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung "
              + "WHERE id=" + id);

          /*
           * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
           * werden. Prüfe, ob ein Ergebnis vorliegt.
           */
          if (rs.next()) {
            // Ergebnis-Tupel in Objekt umwandeln
            Bewerbung b = new Bewerbung();
            b.setId(rs.getInt("Bewerbung_Id"));
            b.setBewerbungstext(rs.getString("Bewerbungstext"));
            b.setAusschreibungId(rs.getInt("Ausschreibung_Id"));
            b.setErstellungsdatum(rs.getDate("Erstellungsdatum"));
            b.setOrganisationseinheitId(rs.getInt("Organisationseinheit_Id"));
            return b;
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
          return null;
        }

        return null;
    }
    
    /**
     * @param b 
     * @return Liefert eine Bewerbung entsprechend des uebergebenen Objekts zurueck
     */
    public Bewerbung findByObject(Bewerbung b) {
        // TODO implement here
    	return this.findById(b.getId());
    }

    /**
     * @param b 
     * @return Zielentitaet aus der Datenbank gemaess des uebergebenen Objekts loeschen.
     */
    public void delete(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM accounts " + "WHERE id=" + b.getId());

        }
        catch (SQLException e) {
          e.printStackTrace();
        }
    }

    /**
     * @param b 
     * @return Zielentitaet aus der Datenbank gemaess den Informationen des uebergebenen Objekts aktualisieren.
     */
    public Bewerbung update(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE Bewerbung SET Bewerbungstext ='"+b.getBewerbungstext()
        		  +"', Ausschreibung_Id="+b.getAusschreibungId()+"', Erstellungsdatum="+b.getErstellungsdatum()
        		  +"WHERE CustomerID ="+ b.getId()+"; ");

        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Account a) zu wahren, geben wir a zurück
        return b;
    }

    /**
     * @param b
     * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
     */
    public Bewerbung insert(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */

           stmt.executeUpdate("INSERT INTO accounts (Bewerbungstext, Erstellungsdatum, Organisationseinheit_Id, Ausschreibung_Id) " 
           + "VALUES ('" + b.getBewerbungstext() + "','" + b.getErstellungsdatum() +"','" + b.getOrganisationseinheitId() +"','"+b.getAusschreibungId()+"')");
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
        return b;
    }
    
    /**
     * @param ausschreibungId
     * @return Liefert alle Bewerbung-Objekte anhand des uebergebenen Beziehungs-Objektes aus der Datenbank zurueck.
     */
    public Vector<Bewerbung> findByForeignAusschreibungId(int ausschreibungId){
        // DB-Verbindung holen
        Connection con = DBConnection.connection();

        try {
          // Leeres SQL-Statement (JDBC) anlegen
          Statement stmt = con.createStatement();

          // Statement ausfüllen und als Query an die DB schicken
          ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung "
              + "WHERE Organisationseinheit_id=" + ausschreibungId);

          /*
           * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
           * werden. Prüfe, ob ein Ergebnis vorliegt.
           */
          Vector <Bewerbung> b = new Vector();
          while (rs.next()) {
            // Ergebnis-Tupel in Objekt umwandeln
        	Bewerbung bObj=new Bewerbung();
            bObj.setId(rs.getInt("Bewerbung_Id"));
            bObj.setBewerbungstext(rs.getString("Bewerbungstext"));
            bObj.setAusschreibungId(rs.getInt("Ausschreibung_Id"));
            bObj.setErstellungsdatum(rs.getDate("Erstellungsdatum"));
            bObj.setOrganisationseinheitId(rs.getInt("Organisationseinheit_Id"));
            b.add(bObj);
          }
          return b;
        }
        catch (SQLException e) {
          e.printStackTrace();
          return null;
        }
    }
    
    /**
     * @param organisationseinheitId
     * @return Liefert alle Bewerbung-Objekte anhand des uebergebenen Beziehungs-Objektes aus der Datenbank zurueck.
     */
    public Vector<Bewerbung> findByForeignOrganisationseinheitId(int organisationseinheitId){
        // DB-Verbindung holen
        Connection con = DBConnection.connection();

        try {
          // Leeres SQL-Statement (JDBC) anlegen
          Statement stmt = con.createStatement();

          // Statement ausfüllen und als Query an die DB schicken
          ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung "
              + "WHERE Organisationseinheit_id=" + organisationseinheitId);

          /*
           * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
           * werden. Prüfe, ob ein Ergebnis vorliegt.
           */
          Vector <Bewerbung> b = new Vector();
          while (rs.next()) {
            // Ergebnis-Tupel in Objekt umwandeln
        	Bewerbung bObj=new Bewerbung();
            bObj.setId(rs.getInt("Bewerbung_Id"));
            bObj.setBewerbungstext(rs.getString("Bewerbungstext"));
            bObj.setAusschreibungId(rs.getInt("Ausschreibung_Id"));
            bObj.setErstellungsdatum(rs.getDate("Erstellungsdatum"));
            bObj.setOrganisationseinheitId(rs.getInt("Organisationseinheit_Id"));
            b.add(bObj);
          }
          return b;
        }
        catch (SQLException e) {
          e.printStackTrace();
          return null;
        }
    }
}