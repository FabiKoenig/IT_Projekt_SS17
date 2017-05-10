package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper für Person- Objekte
 */
public class PersonMapper extends OrganisationseinheitMapper{

	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static PersonMapper personMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected PersonMapper(){
	}
	
	/**
	 * @return personMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static PersonMapper personMapper() {
		    if (personMapper == null) {
		      personMapper = new PersonMapper();
		    }

		    return personMapper;
		  }
	  
	/**
	 *   
	 * @param id
	 * @return Liefert eine Person entsprechend der übergebenen id zurueck.
	 */
	public Person findById(int id){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Person_Id, Anrede, Vorname, Nachname, Unternehmen_Id, Team_Id"
					+ " FROM person " + "WHERE Person_Id=" + 
			id + " ORDER BY Nachname");
			
			
			if(rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("Person_Id"));
				p.setAnrede(rs.getString("Anrede"));
				p.setVorname(rs.getString("Vorname"));
				p.setNachname(rs.getString("Nachname"));
				p.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				p.setTeamId(rs.getInt("Team_Id"));
				p.setStrasse(super.findById(id).getStrasse());
				p.setHausnummer(super.findById(id).getHausnummer());
				p.setPlz(super.findById(id).getPlz());
				p.setOrt(super.findById(id).getOrt());
				p.setPartnerprofilId(super.findById(id).getPartnerprofilId());
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
	 * @return Liefert die ID entsprechend des übergebenen Objekts zurück.
	 */
	public Person findByObject(Person p){
		
		return this.findById(p.getId());	
	}
	
	/**
	 * 
	 * @param teamId
	 * @return Liefert alle Personen des uebergebenen Teams zurueck.
	 */
	public Vector<Person> findByForeignTeamId(int teamId){
		
		Connection con = DBConnection.connection();
		
		// Vektor, in dem die Personen nach einem bestimmten Team gespeichert werden
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM person " + "WHERE Team_Id=" + 
			teamId + " ORDER BY Nachname");
			
			
			while (rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("Person_Id"));
				p.setAnrede(rs.getString("Anrede"));
				p.setVorname(rs.getString("Vorname"));
				p.setNachname(rs.getString("Nachname"));
				p.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				p.setTeamId(rs.getInt("Team_Id"));
				p.setStrasse(super.findById(teamId).getStrasse());
				p.setHausnummer(super.findById(teamId).getHausnummer());
				p.setPlz(super.findById(teamId).getPlz());
				p.setOrt(super.findById(teamId).getOrt());
				p.setPartnerprofilId(super.findById(teamId).getPartnerprofilId());	

			
				result.add(p);
				} 
				if(result.isEmpty()==true){
	        	  return null;
					}else{
	              return result;
	          }
			}   
		catch (SQLException e) {
		e.printStackTrace();
		return null;
		}
		
	}
	/**
	 * 
	 * @param unternehmenId
	 * @return Liefer alle Personen des uebergebenen Unternehmens zurueck.
	 */
	public Vector<Person> findByForeignUnternehmenId(int unternehmenId){
		
		Connection con = DBConnection.connection();
		
		// Vektor, in dem die Personen nach einem bestimmten Team gespeichert werden
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM person " + "WHERE Unternehmen_Id=" + 
			unternehmenId);
			
			
			while (rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("Person_Id"));
				p.setAnrede(rs.getString("Anrede"));
				p.setVorname(rs.getString("Vorname"));
				p.setNachname(rs.getString("Nachname"));
				p.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				p.setTeamId(rs.getInt("Team_Id"));
				p.setStrasse(super.findById(unternehmenId).getStrasse());
				p.setHausnummer(super.findById(unternehmenId).getHausnummer());
				p.setPlz(super.findById(unternehmenId).getPlz());
				p.setOrt(super.findById(unternehmenId).getOrt());
				p.setPartnerprofilId(super.findById(unternehmenId).getPartnerprofilId());
				

				result.add(p);
				} 
			}  
		catch (SQLException e) {
		e.printStackTrace();
		}
		
		return result;
		
	}
	
	public Vector<Person> findAllPerson(){
		Connection con = DBConnection.connection();
		
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * "
					+ " FROM person ORDER BY Person_Id");
			
			
			while (rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("Person_Id"));
				p.setAnrede(rs.getString("Anrede"));
				p.setVorname(rs.getString("Vorname"));
				p.setNachname(rs.getString("Nachname"));
				p.setUnternehmenId(rs.getInt("Unternehmen_Id"));
				p.setTeamId(rs.getInt("Team_Id"));
				p.setStrasse(super.findByObject(p).getStrasse());
				p.setHausnummer(super.findByObject(p).getHausnummer());
				p.setPlz(super.findByObject(p).getPlz());
				p.setOrt(super.findByObject(p).getOrt());
				p.setPartnerprofilId(super.findByObject(p).getPartnerprofilId());
				
				
				result.add(p);
				} 
			}  
		catch (SQLException e) {
		e.printStackTrace();
		}
		
		return result;
	}
	
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Person p){
		  Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM person " + "WHERE Person_Id=" + p.getId());
		      
		      super.delete(p);
		      
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
	  public Person update(Person p){
		  
		    Connection con = DBConnection.connection();
		    

		    try {
		    	 p.setId(super.update(p));
		     super.organisationsEinheitsmapper().update(p);
		    	
		      Statement stmt = con.createStatement();
		      
		        if(p.getTeamId()==null && p.getUnternehmenId()==null){
				      stmt.executeUpdate("UPDATE person " + "SET Vorname=\""
					          + p.getVorname() + "\", " + "Nachname=\"" + p.getNachname() + "\", " + "Anrede=\""+ p.getAnrede() +
					          "\", " + "Unternehmen_Id=NULL, Team_Id=NULL" + " WHERE Person_Id=" + p.getId());
		        }else if(p.getTeamId()!=null && p.getUnternehmenId()==null){
				      stmt.executeUpdate("UPDATE person " + "SET Vorname=\""
					          + p.getVorname() + "\", " + "Nachname=\"" + p.getNachname() + "\", " + "Anrede=\""+ p.getAnrede() +
					          "\", " + "Unternehmen_Id=NULL, Team_Id=\""+ p.getTeamId() + "\" "
					          + "WHERE Person_Id=" + p.getId());
		        }else if(p.getTeamId()==null && p.getUnternehmenId()!=null){
				      stmt.executeUpdate("UPDATE person " + "SET Vorname=\""
					          + p.getVorname() + "\", " + "Nachname=\"" + p.getNachname() + "\", " + "Anrede=\""+ p.getAnrede() +
					          "\", " + "Unternehmen_Id=\"" + p.getUnternehmenId() + "\", " + "Team_Id= NULL, WHERE Person_Id=" + p.getId());
		        }else if(p.getTeamId()!=null && p.getUnternehmenId()!=null){
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
				      stmt.executeUpdate("UPDATE person " + "SET Vorname=\""
					          + p.getVorname() + "\", " + "Nachname=\"" + p.getNachname() + "\", " + "Anrede=\""+ p.getAnrede() +
					          "\", " + "Unternehmen_Id=\"" + p.getUnternehmenId() + "\", " + "Team_Id=\""+ p.getTeamId() + "\" "
					          + "WHERE Person_Id=" + p.getId());
		        } 
		      
		   
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    // Um Analogie zu insert(Person p) zu wahren, geben wir p zurück
		    return p;
	  }
	  
	  /**
	   *  TODO
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Person insert(Person p){
		  
		  
		    Connection con = DBConnection.connection();

		    try {
		    	
		     // OrganisationseinheitMapper.organisationseinheitMapper().insert(p);
		    	
		      Statement stmt = con.createStatement();
		      p.setId(super.insert(p));
		      

		        stmt = con.createStatement();
		        
		        //Pr�fung ob im den �bergebenen Fremdschl�sseln Team_Id und Unternehmen_Id ein NULL-Wert vorhanden ist.
		        //Entsprechend muss das Statement angepast werden, um von der Datenbank akzeptiert zu werden.
		        if(p.getTeamId()==null && p.getUnternehmenId()==null){
			        stmt.executeUpdate("INSERT INTO person (Person_Id, Anrede, Vorname, Nachname) "
				            + "VALUES (" + p.getId() + ",'" + p.getAnrede() + "','"
				            + p.getVorname() + "','" + p.getNachname() +"')");
		        }else if(p.getTeamId()!=null && p.getUnternehmenId()==null){
			        stmt.executeUpdate("INSERT INTO person (Person_Id, Anrede, Vorname, Nachname, Team_Id) "
				            + "VALUES (" + p.getId() + ",'" + p.getAnrede() + "','"
				            + p.getVorname() + "','" + p.getNachname() + "','" + p.getTeamId() +"')");
		        }else if(p.getTeamId()==null && p.getUnternehmenId()!=null){
			        stmt.executeUpdate("INSERT INTO person (Person_Id, Anrede, Vorname, Nachname, Unternehmen_Id) "
				            + "VALUES (" + p.getId() + ",'" + p.getAnrede() + "','"
				            + p.getVorname() + "','" + p.getNachname() + "','" + p.getUnternehmenId() +"')");
		        }else if(p.getTeamId()!=null && p.getUnternehmenId()!=null){
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        stmt.executeUpdate("INSERT INTO person (Person_Id, Anrede, Vorname, Nachname, Unternehmen_Id, Team_Id) "
			            + "VALUES (" + p.getId() + ",'" + p.getAnrede() + "','"
			            + p.getVorname() + "','" + p.getNachname() + "','" + p.getUnternehmenId() + "','" + p.getTeamId() +"')");
		        }        
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return p;
		  
	  }

}
