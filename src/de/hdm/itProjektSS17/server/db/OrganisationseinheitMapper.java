package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Mapper für Organisationseinheit- Objekte
 */
public class OrganisationseinheitMapper {
		
	/*Konstruktor der Klasse Organisationseinheit. Durch protected wird verhindert,
	 *dass durch "new" neue Instanzen der Klasse erzeugt werden können.
	 */
	
	public static OrganisationseinheitMapper  organisationsEinheitsmapper = null;
	
	
	protected OrganisationseinheitMapper(){
	}
	
	 public static OrganisationseinheitMapper organisationsEinheitsmapper() {
		    if (organisationsEinheitsmapper == null) {
		    	organisationsEinheitsmapper = new OrganisationseinheitMapper();
		    }

		    return organisationsEinheitsmapper;
		  }
	
	
	/*Suche einer Organisationseinheit durch eine eindeutige ID(Primärschlüssel).
	 *Die Organisationseinheit mit der übergebenen ID wird zurückgegeben.
	 */
	protected Organisationseinheit findById(int id){

		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Organisationseinheit_Id, Strasse, Hausnummer, PLZ, Ort, Partnerprofil_Id"
					+ " FROM organisationseinheit " + "WHERE Organisationseinheit_Id=" + id);
			
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("Organisationseinheit_Id"));
				o.setStrasse(rs.getString("Strasse"));
				o.setHausnummer(rs.getString("Hausnummer"));
				o.setPlz(rs.getInt("PLZ"));
				o.setOrt(rs.getString("Ort"));
				o.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				
		return o;
			
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
	 * @param o
	 * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
	 */
	protected Organisationseinheit findByObject(Organisationseinheit o){
		return this.findById(o.getId()); 
	}
	
	/*Suceh von einer Organisationseinheit durch ein übergebendes Partnerprofil.
	 *Ein Organisationseinheit-Objekt wird zurueckgegeben.
	 */
	protected Organisationseinheit findByForeignPartnerprofilId(int partnerprofilId){
	Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Organisationseinheit_Id, Strasse, Hausnummer, PLZ, Ort, Partnerprofil_Id"
					+ " FROM organisationseinheit " + "WHERE Partnerprofil_Id=" + partnerprofilId);
			
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("Organisationseinheit_Id"));
				o.setStrasse(rs.getString("Strasse"));
				o.setHausnummer(rs.getString("Hausnummer"));
				o.setPlz(rs.getInt("PLZ"));
				o.setOrt(rs.getString("Ort"));
				o.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
		return o;
			
	}
	}
		catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
		return null;
	}
		
	
	
	
	/*Durch die insert-Methode kann eine neue Organisationseinheit in die Datenbank geschrieben werden.
	 *Die id des Projekts wird überprüft und im Verlauf der Methode ggf. angepasst.
	 */
	protected int insert(Organisationseinheit o){
		 
	    Connection con = DBConnection.connection();
	    int id=0;
	    try {
	    		
	      Statement stmt = con.createStatement();

	      /*
	       * Zunächst schauen wir nach, welches der momentan höchste
	       * Primärschlüsselwert ist.
	       */
	      ResultSet rs = stmt.executeQuery("SELECT MAX(Organisationseinheit_Id) AS maxid "
	          + "FROM organisationseinheit");

	      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
	      if (rs.next()) {
	        /*
	         * c erhält den bisher maximalen, nun um 1 inkrementierten
	         * Primärschlüssel.
	         */
	        o.setId(rs.getInt("maxid") + 1);
	        id=o.getId();
	        stmt = con.createStatement();

	        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
	        stmt.executeUpdate("INSERT INTO organisationseinheit (Organisationseinheit_Id, Strasse, Hausnummer, PLZ, Ort, Partnerprofil_Id ) "
	            + "VALUES ('" + o.getId() + "','" + o.getStrasse() + "','"
	            + o.getHausnummer() + "','" + o.getPlz() + "','" + o.getOrt() + "','" + o.getPartnerprofilId() +"')");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return id;
	  
  }

	
	
	/*Durch die update-Methode kann ein Objekt wiederholt in die Datenbank geschrieben und verändert/ angepasst werden.
	 *Übergeben wird das Organisationseinheit-Objekt welches nochmals in die Datenbank geschrieben wird.
	 *Return wird das übergebene Objekt.
	 * 
	 */
	protected int update(Organisationseinheit o){
		
		Connection con = DBConnection.connection();
		int id=0;

		    try {
		    id=o.getId();
		      Statement stmt = con.createStatement();


		      stmt.executeUpdate("UPDATE organisationseinheit SET strasse='"
			          + o.getStrasse() + "'," + "Hausnummer='" + o.getHausnummer() + "'," + "PLZ=" + o.getPlz() + ","
			          + "Ort='" + o.getOrt() +"'"+" WHERE Organisationseinheit_Id="+o.getId());

		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return id;
	  }
	
	
	
	/*Durch die delete- Methode kann ein Organisationseinheit Objekt in der Datenbank gelöscht werden.
	 *Gelöscht wird das übergebene Objekt.
	 */
	protected void delete(Organisationseinheit o){
		 Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM organisationseinheit" + " WHERE Organisationseinheit_Id=" + o.getId());
		      
		 
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	  }

	}
