package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.security.auth.login.AccountException;

import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Projekt- Objekte
 */
public class ProjektMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektMapper projektMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected ProjektMapper(){
	}
	
	/**
	 * @return projektMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static ProjektMapper projektMapper() {
		    if (projektMapper == null) {
		      projektMapper = new ProjektMapper();
		    }

		    return projektMapper;
		  }
	  
	  /**
	   * 
	   * @param id
	   * @return Liefert ein Projekt entsprechend der übergebenen id zurueck.
	   */
	  public Projekt findById(int id){

		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT Projekt_Id, Startdatum, Enddatum, Name, Beschreibung, "
			  		+ "Projektleiter_Id, Projektmarktplatz_Id FROM projekt WHERE Projekt_Id=" + id);
			  
			  if (rs.next()) {
				
				  Projekt p = new Projekt();
				  p.setId(rs.getInt("Projekt_Id"));
				  p.setStartdatum(rs.getDate("Startdatum"));
				  p.setEnddatum(rs.getDate("Enddatum"));
				  p.setName(rs.getString("Name"));
				  p.setBeschreibung(rs.getString("Beschreibung"));
				  p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				  p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
			
				  return p;
			}
			  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		  return null;
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Projekt entsprechend des übergebenen Objekts zurueck.
	   */
	  public Projekt findByObject(Projekt p){
		return p;
		  
	  }
	  
	  /**
	   * 
	   * @param projektmarktplatzId
	   * @return Alle Projekte auf dem uebergebenen Projektmarktplatz werden zurueckgegeben.
	   */
	  public Vector<Projekt> findByForeignProjektmarktplatzId(int projektmarktplatzId){
		
		  Connection con = DBConnection.connection();
		  Vector<Projekt> result = new Vector<Projekt>();
		  
		  try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Projekt_Id, Startdatum, Enddatum, Name, Beschreibung, "
			  		+ "Projektleiter_Id, Projektmarktplatz_Id FROM projekt WHERE Projektmarktplatz_Id=" + projektmarktplatzId);
			
			while (rs.next()) {
				Projekt p = new Projekt();
				
				p.setId(rs.getInt("Projekt_Id"));
				p.setStartdatum(rs.getDate("Startdatum"));
				p.setEnddatum(rs.getDate("Enddatum"));
				p.setName(rs.getString("Name"));
				p.setBeschreibung(rs.getString("Beschreibung"));
				p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
				
				result.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param projektleiterId
	   * @return Liefert alle Projekt des uebergebenen Projektleiters zurueck.
	   */
	  public Vector<Projekt> findByForeignProjektleiterId(int projektleiterId){
		
		  Connection con = DBConnection.connection();
		  Vector<Projekt> result = new Vector<Projekt>();
		  
		  try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Projekt_Id, Startdatum, Enddatum, Name, Beschreibung, "
			  		+ "Projektleiter_Id, Projektmarktplatz_Id FROM projekt WHERE Projektleiter_Id=" + projektleiterId);
			
			while (rs.next()) {
				Projekt p = new Projekt();
				
				p.setId(rs.getInt("Projekt_Id"));
				p.setStartdatum(rs.getDate("Startdatum"));
				p.setEnddatum(rs.getDate("Enddatum"));
				p.setName(rs.getString("Name"));
				p.setBeschreibung(rs.getString("Beschreibung"));
				p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
				
				result.add(p);
			}
		  }	catch (SQLException e) {
			// TODO: handle exception
			  e.printStackTrace();
		}
		  return result;
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Projekt p){
		  
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("DELETE FROM projekt WHERE projekt_Id=" + p.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Projekt update(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE projekt SET Startdatum=" + p.getStartdatum() + ", "
			  		+ "Enddatum=" + p.getEnddatum() + ", " + "Name=" + p.getName() + ", "
					+ "Beschreibung=" + p.getBeschreibung() + ", " + "Projektleiter_Id=" + p.getProjektleiterId()
					+ ", " + "Projektmarktplatz_Id=" + p.getProjektmarktplatzId());
			  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		  return p;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Projekt insert(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			  
			  Statement stmt = con.createStatement();			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(projekt_Id) AS maxid FROM projekt");
			  
			  if (rs.next()) {
				
				  p.setId(rs.getInt("maxid" + 1));
				  
				  stmt.executeUpdate("INSERT INTO projekt (Startdatum, Enddatum, Name, Beschreibung, "
				  		+ "Projektleiter_Id, Projektmarktplatz_Id) VALUES (" + p.getStartdatum() + ", "
				  		+ p.getEnddatum() + ", " + p.getName() + ", " + p.getBeschreibung() + ", " + 
				  		p.getProjektleiterId() + ", " + p.getProjektmarktplatzId());
			}
			  
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  return p;
	  }
}
