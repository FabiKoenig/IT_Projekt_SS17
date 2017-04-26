package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Projekt-Objekte
 */
public class ProjektMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektMapper projektMapper = null;
	
	/**
	 * Geschützter Konstruktor
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
	   * @return Liefert ein Projekt entsprechend der übergebenen id zurück.
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
				  p.setStartdatum(rs.getString("Startdatum"));
				  p.setEnddatum(rs.getString("Enddatum"));
				  p.setName(rs.getString("Name"));
				  p.setBeschreibung(rs.getString("Beschreibung"));
				  p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				  p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
			
				  return p;
			}
			  
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		  return null;
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Liefert ein Projekt entsprechend des übergebenen Objekts zurück.
	   */
	  public Projekt findByObject(Projekt p){
		this.findById(p.getId());
		  
		return p;		  
	  }
	  
	  /**
	   * 
	   * @param projektmarktplatzId
	   * @return Alle Projekte auf dem übergebenen Projektmarktplatz werden zurückgegeben.
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
				p.setStartdatum(rs.getString("Startdatum"));
				p.setEnddatum(rs.getString("Enddatum"));
				p.setName(rs.getString("Name"));
				p.setBeschreibung(rs.getString("Beschreibung"));
				p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
				
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param projektleiterId
	   * @return Liefert alle Projekt des übergebenen Projektleiters zurück.
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
				p.setStartdatum(rs.getString("Startdatum"));
				p.setEnddatum(rs.getString("Enddatum"));
				p.setName(rs.getString("Name"));
				p.setBeschreibung(rs.getString("Beschreibung"));
				p.setProjektleiterId(rs.getInt("Projektleiter_Id"));
				p.setProjektmarktplatzId(rs.getInt("Projektmarktplatz_Id"));
				
				result.add(p);
			}
		  }	catch (SQLException e) {
			  e.printStackTrace();
		}
		  return result;
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentität aus der Datenbank, gemäß den Informationen des übergebenen Objekts, löschen.
	   */
	  public void delete(Projekt p){
		  
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("DELETE FROM projekt WHERE Projekt_Id=" + p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Zielentität aus der Datenbank, gemäß den Informationen des übergebenen Objekts, aktualisieren.
	   */
	  public Projekt update(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE projekt SET Startdatum='" + p.getStartdatum() + "', "
			  		+ "Enddatum='" + p.getEnddatum() + "', " + "Name='" + p.getName() + "', "
					+ "Beschreibung='" + p.getBeschreibung() + "', " + "Projektleiter_Id=" + p.getProjektleiterId()
					+ ", " + "Projektmarktplatz_Id=" + p.getProjektmarktplatzId() + " WHERE Projekt_Id=" + p.getId());
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		  return p;
		  
	  }
	  
	  /**
	   * 
	   * @param p
	   * @return Übergebenes Objekt als neue Entität in die Datenbank schreiben.
	   */
	  public Projekt insert(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			  
			  Statement stmt = con.createStatement();			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(Projekt_Id) AS maxid FROM projekt");
			  
			  if (rs.next()) {
				
				  p.setId(rs.getInt("maxid") + 1);
				  
				  stmt.executeUpdate("INSERT INTO projekt (Projekt_Id ,`Startdatum`, `Enddatum`, `Name`, `Beschreibung`, "
				  		+ "Projektleiter_Id, Projektmarktplatz_Id) VALUES (" + p.getId() + ", '" + p.getStartdatum() + "', '"
				  		+ p.getEnddatum() + "', '" + p.getName() + "', '" + p.getBeschreibung() + "', " + 
				  		p.getProjektleiterId() + ", " + p.getProjektmarktplatzId() + ")");
			}
			  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		  return p;
	  }
}
