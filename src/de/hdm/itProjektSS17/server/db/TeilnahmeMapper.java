package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.*;

/**
 * Mapper f�r Teilnahme-Objekte um die Teilnahme von Personen auf Marktpl�tzen darzustellen.
 */
public class TeilnahmeMapper{

	
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static TeilnahmeMapper teilnahmeMapper = null;
	
	/**
	 * Geschuetzter Konstruktor um zu verhindern, dass unkontrolliert Instanzen dieser Klasse erstellt werden
	 */
	protected TeilnahmeMapper(){
	}
	
	/**
	 * @return teilnahmeMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static TeilnahmeMapper teilnahmeMapper() {
		    if (teilnahmeMapper == null) {
		      teilnahmeMapper = new TeilnahmeMapper();
		    }

		    return teilnahmeMapper;
		  }
	  
	  public void insert(Person pe, Projektmarktplatz pr){
		  
		  
		    Connection con = DBConnection.connection();

		    try {
		    	
		      Statement stmt = con.createStatement();
		      
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO `teilnahme` (`Person_Id`, `Projektmarktplatz_Id`) VALUES ("+pe.getId()+", "+pr.getId()+")"); 
		        
		        
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return;
	  }
	  
	  public void delete(Person pe, Projektmarktplatz pr){
		  
		    Connection con = DBConnection.connection();

		    try {
		    	
		      Statement stmt = con.createStatement();
		      
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("DELETE FROM `teilnahme` WHERE `teilnahme`.`Person_Id` = "+pe.getId()+" AND `teilnahme`.`Projektmarktplatz_Id` = "+pr.getId()); 
		        
		        
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return;
	  }
	  
	  public Vector<Projektmarktplatz> findRelatedProjektMarktplaetze(Person pe){
	        // DB-Verbindung holen
	        Connection con = DBConnection.connection();

	        try {
	          // Leeres SQL-Statement (JDBC) anlegen
	          Statement stmt = con.createStatement();

	          // Statement ausfüllen und als Query an die DB schicken
	          ResultSet rs = stmt.executeQuery("SELECT * FROM `teilnahme` WHERE Person_Id="+pe.getId());
	          Vector <Integer> idsOfProjektmarktplaetze = new Vector();
	     
	          
	          while(rs.next()){
	        	  idsOfProjektmarktplaetze.add(rs.getInt("Projektmarktplatz_Id"));
	          }
	          
	          Vector <Projektmarktplatz> pr = new Vector();
	          for (Integer ids : idsOfProjektmarktplaetze) {
	        	  pr.add(ProjektmarktplatzMapper.projektmarktplatzMapper().findById(ids));
			}
	          return pr;
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	          return null;
	        }
	  }
	  
	  public Vector<Person> findRelatedPersonen(Projektmarktplatz pr){
	        // DB-Verbindung holen
	        Connection con = DBConnection.connection();

	        try {
	          // Leeres SQL-Statement (JDBC) anlegen
	          Statement stmt = con.createStatement();

	          // Statement ausfüllen und als Query an die DB schicken
	          ResultSet rs = stmt.executeQuery("SELECT * FROM `teilnahme` WHERE Projektmarktplatz_Id="+pr.getId());
	          Vector <Integer> idsOfPersonen = new Vector();
	     
	          
	          while(rs.next()){
	        	  idsOfPersonen.add(rs.getInt("Person_Id"));
	          }
	          
	          Vector <Person> pe = new Vector();
	          for (Integer ids : idsOfPersonen) {
	        	  pe.add(PersonMapper.personMapper().findById(ids));
			}
	          return pe;
	        }
	        catch (SQLException e) {
	          e.printStackTrace();
	          return null;
	        }
	  }

}
