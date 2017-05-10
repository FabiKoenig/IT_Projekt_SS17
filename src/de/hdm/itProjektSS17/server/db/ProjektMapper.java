package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;


import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Projekt-Objekte
 */
public class ProjektMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static ProjektMapper projektMapper = null;
	
	/**
	 * Geschützter Konstruktor um zu verhindern, dass Objekte der Klasse <code>AusschreibungsMapper</code nicht 
	 * außerhalb der Vererbungshierarchie dieser Klasse erstellt werden.
	 */
	protected ProjektMapper(){
	}
	

	/**
	 * Sicherstellung der Singleton-Eigenschaft der Mapperklasse, dass nur eine Instanz von 
	 * <code>AusschreibungMapper</code> existiert.
	 * @return <code>AusschreibungMapper</code>-Objekt
	 */
	  public static ProjektMapper projektMapper() {
		    if (projektMapper == null) {
		      projektMapper = new ProjektMapper();
		    }

		    return projektMapper;
		  }
	  
	  /**
	   * Suchen eines Projekts mit vorgegebener eindeutiger Projekt_Id.
	   * 
	   * @param id Primärschlüssel Projekt_Id der Tabelle projekt
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
				  p.setStartdatum(rs.getDate("Startdatum"));
				  p.setEnddatum(rs.getDate("Enddatum"));
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
	   * Suchen des Projekts durch das vorgebene Projekt-Objekt.
	   * @param p
	   * @return Liefert das Projekt entsprechend des übergebenen Objekts zurück.
	   */
	  public Projekt findByObject(Projekt p){
		  return this.findById(p.getId());		  
	  }
	  
	  /**
	   * Auslesen aller Projekte eines Projektmarktplatzes.
	   * 
	   * @param projektmarktplatzId Fremdschlüssel Projektmarktplatz_Id der Tabelle projekt
	   * @return alle Projekte des Projektmarktplatzes
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
			if(result.isEmpty()==true){
	        	  return null;
	          }else{
	              return result;
	          }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	  }
	  
	  /**
	   * Auslesen aller Projekte eines Projektleiters.
	   * 
	   * @param projektleiterId Fremdschlüssel Projektleiter_Id der Tabelle projekt
	   * @return alle Projekte des Projektleiters
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
			  e.printStackTrace();
		}
		  return result;
	  }
	  
	  /**
	   * Löschen des übergebenen Projekts.
	   * @param p das zu löschende Projekt-Objekt
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
	   * Wiederholtes Schreiben eines <code>Projekt</code>-Objekts in die Datenbank.
	   * 
	   * @param p
	   * @return das als Parameter übergebene, aktualisierte <code>Projekt</code>-Objekt
	   */
	  public Projekt update(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE projekt SET Startdatum='" + format.format(p.getStartdatum()) + "', "
			  		+ "Enddatum='" + format.format(p.getEnddatum()) + "', " + "Name='" + p.getName() + "', "
					+ "Beschreibung='" + p.getBeschreibung() + "' WHERE Projekt_Id=" + p.getId());
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		  return p;
		  
	  }
	  
	  /**
	   * 
	   * Einfügen eines <code>Projekt</code>-Objekts in die Datenbank. Dabei wird auch der Primärschlüssel des
	   * übergebenen Objekts geprüft und ggf. berichtigt.
	   * @param p das zu speichernde <code>Projekt</code>-Objekt, jedoch mit ggf. korrigiertem Primärschlüssel 
	   * <code>Projekt_Id</code>.
	   * @return .
	   */
	  public Projekt insert(Projekt p){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			  
			  Statement stmt = con.createStatement();			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(Projekt_Id) AS maxid FROM projekt");
			  
			  if (rs.next()) {
				
				  p.setId(rs.getInt("maxid") + 1);
				  
				  stmt.executeUpdate("INSERT INTO projekt (Projekt_Id ,`Startdatum`, `Enddatum`, `Name`, `Beschreibung`, "
				  		+ "Projektleiter_Id, Projektmarktplatz_Id) VALUES (" + p.getId() + ", '" + format.format(p.getStartdatum()) + "', '"
				  		+ format.format(p.getEnddatum()) + "', '" + p.getName() + "', '" + p.getBeschreibung() + "', " + 
				  		p.getProjektleiterId() + ", " + p.getProjektmarktplatzId() + ")");
			}
			  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		  return p;
	  }
}
