package de.hdm.itProjektSS17.server.db;

import java.util.Vector;
import java.sql.*;

import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;


/**
 * Mapper für Team- Objekte
 */
public class TeamMapper extends OrganisationseinheitMapper{

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
			  
			  ResultSet rs = stmt.executeQuery("SELECT Team_Id, Name, Unternehmen_Id "
			  +"FROM team"+ " WHERE Team_Id=" + id);
			  
			 if(rs.next()){
				  Team a = new Team();
				  a.setId(rs.getInt("Team_Id"));
				  a.setName(rs.getString("Name"));
				  a.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				  a.setStrasse(super.findById(id).getStrasse());
				  a.setHausnummer(super.findById(id).getHausnummer());
				  a.setOrt(super.findById(id).getOrt());
				  a.setPlz(super.findById(id).getPlz());
				  a.setPartnerprofilId(super.findById(id).getPartnerprofilId());
				  a.setProjektmarktplatzId(super.findById(id).getProjektmarktplatzId());
				  
				  return a;
			  }
			  
		  }catch(SQLException e){
			  e.printStackTrace();
			  return null;
			  
		  }
		  
		return null;
		  
	  }
	  
	  /**
	   * 
	   * @param t
	   * @return Liefert ein Team entsprechend des übergebenen Objekts zurueck.
	   */
	  public Team findByObject(Team t){
		return this.findById(t.getId());
		  
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
	          ResultSet rs = stmt.executeQuery("SELECT * FROM team "
	              + "WHERE Unternehmen_Id=" + unternehmenId);

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
	          super.delete(t);
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
	        	t.setId(super.update(t));
	        	
	        	Statement stmt = con.createStatement();

	        	stmt.executeUpdate("UPDATE Team SET Name='"+t.getName()
	        		  +"',"+"', Unternehmen_Id="+t.getUnternehmenId()
	        		  );					

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
		  //DB-Verbindung holen
		  Connection con = DBConnection.connection();

	        try {
	        	//Leeres SQL-Statement anlegen
	          Statement stmt = con.createStatement();

	        
	          t.setId(super.insert(t));
	          
	          stmt.executeUpdate("INSERT INTO `team`(`Team_Id`, `Name`,`Unternehmen_Id`) "
	        		  + "VALUES ('" + t.getId() + "','" + t.getName() +"','"+ t.getUnternehmenId()+"')");
	           
	        } catch (SQLException e) {
	          e.printStackTrace();
	}
		return t;
		  
	  }


}
