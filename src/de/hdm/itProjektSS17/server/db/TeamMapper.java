package de.hdm.itProjektSS17.server.db;

import java.util.Vector;
import java.sql.*;

import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;

/**
 * Mapper für Team- Objekte
 */
public class TeamMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static TeamMapper teamMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected TeamMapper(){
	}
	
	/**
	 * @return teamMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static TeamMapper teamMapper() {
		    if (teamMapper == null) {
		      teamMapper = new TeamMapper();
		    }

		    return teamMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Team entsprechend der übergebenen id zurueck.
	   */
	  public Team findById(int id){
		  Connection con = DBConnection.connection();
		  
		  try{
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("Select Team_Id, Name, Organisationseinheit_Id, Unternehmen_Id FROM Team"
			  		+ "Where Team_Id ="+id);
			  
			 if(rs.next()){
				  Team a = new Team();
				  a.setId(rs.getInt("Team_Id"));
				  a.setName(rs.getString("Name"));
				  a.setUnternehmenId(rs.getInt("Unternehmen_Id"));
			  }
			  
		  }catch(SQLException e){
			  e.printStackTrace();
			  
			  
		  }
		  
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Liefert ein Team entsprechend des übergebenen Objekts zurueck.
	   */
	  public Team findByObject(Team t){
		return t;
		  
	  }
	  
	  /**
	   * 
	   * @param unternehmenId
	   * @return Liefert alle Team-Objekte des uebergebenen Unternehmens zurueck.
	   */
	  public Vector<Team> findByForeignUnternehmenId(int unternehmenId){
		// DB-Verbindung holen
	        Connection con = DBConnection.connection();

	        try {
	          // Leeres SQL-Statement (JDBC) anlegen
	          Statement stmt = con.createStatement();

	          // Statement ausfüllen und als Query an die DB schicken
	          ResultSet rs = stmt.executeQuery("SELECT * FROM Team "
	              + "WHERE Organisationseinheit_id=" + unternehmenId);

	          /*
	           * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	           * werden. Prüfe, ob ein Ergebnis vorliegt.
	           */
	          Vector <Team> t = new Vector();
	          while (rs.next()) {
	            // Ergebnis-Tupel in Objekt umwandeln
	        	Team te=new Team();
	            te.setId(rs.getInt("Team_Id"));
	            te.setName(rs.getString("Name"));
	            te.setO;  // Hier muss getOrganisationseinheit_Id hin
	            te.setUnternehmenId(rs.getInt("Unternehmen_Id"));
	            
	            t.add(te);
	          }
	          return t;
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	          return null;
	}
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Team t){
		  Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          stmt.executeUpdate("DELETE FROM Team " + "WHERE Team_Id=" + t.getId() );

	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	}
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Team update(Team t){
		  
		  Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          stmt.executeUpdate("UPDATE Team SET Name='"+t.getName()
	        		  +"', Organisationseinheit_Id="+t.getOr+"', Unternehmen_Id="+t.getUnternehmenId()
	        		  );						 // Hier muss getOrganisationseinheit_Id hin

	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	}
		  
		return t;
		
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Team insert(Team t){
		  
		  Connection con = DBConnection.connection();

	        try {
	          Statement stmt = con.createStatement();

	          /*
	           * Zunächst schauen wir nach, welches der momentan höchste
	           * Primärschlüsselwert ist.
	           */

	           stmt.executeUpdate("INSERT INTO Team (Name, Organisationseinheit_Id, Unternehmen_Id) " 
	           + "VALUES ('" + t.getName()+ "','" +t.get+"','"+t.getUnternehmenId()+"')");
	        }									   // Hier muss getOrganisationseinheit_Id hin
	        catch (SQLException e) {
	          e.printStackTrace();
	}
		return t;
		  
	  }
}
