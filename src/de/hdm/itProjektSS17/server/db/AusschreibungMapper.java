package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * Mapper für Ausschreibung-Objekte
 * 
 * @author Michael Geiselmann
 * @version 1.0
 */
public class AusschreibungMapper {

	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static AusschreibungMapper ausschreibungMapper = null;
	
	/**
	 * Geschuetzter Konstruktor
	 */
	protected AusschreibungMapper(){
	}
	
	/**
	 * @return ausschreibungMapper - Sicherstellung der Singleton-Eigenschaft der Mapperklasse 
	 */
	  public static AusschreibungMapper ausschreibungMapper() {
		    if (ausschreibungMapper == null) {
		      ausschreibungMapper = new AusschreibungMapper();
		    }

		    return ausschreibungMapper;
		  }
	
	  /**
	   * 
	   * @param id
	   * @return Liefert eine Ausschreibung entsprechend der übergebenen id zurueck.
	   */
	  public Ausschreibung findById(int id){
		
		  Connection con =  DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Ausschreibungs_Id=" + id);
			  
			  if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("Ausschreibung_Id"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
				a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
				a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
				a.setProjektId(rs.getInt("Projekt_Id"));
				
				return a;
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
	   * @param a
	   * @return Liefert eine Ausschreibung entsprechend des übergebenen Objekts zurueck.
	   */
	  public Ausschreibung findByObject(Ausschreibung a){
		return a;

	  }
	  /**
	   * 
	   * @param projektId
	   * @return Liefert alle Ausschreibungen zu dem uebergebenen Projekt Projekt zurueck.
	   */
	  public Vector<Ausschreibung> findByForeignProjektId(int projektId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Projekt_Id=" + projektId + " ORDER BY Ausschreibender_Id");
			  
			  while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("Ausschreibung_Id"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
				a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
				a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
				a.setProjektId(rs.getInt("Projekt_Id"));
				
				result.add(a);
			}
			  
			  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param partnerprofilId
	   * @return Liefert die Ausschreibung zu dem uebergebenen Partnerprofils zurueck.
	   */
	  public Ausschreibung findByForeignPartnerprofilId(int partnerprofilId){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE partnerprofil_id=" + partnerprofilId);
			
			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("Ausschreibung_Id"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
				a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
				a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
				a.setProjektId(rs.getInt("Projekt_Id"));
				
				return a;
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
	   * @param organisationseinheitId
	   * @return Liefert alle Ausschreibungen der uebergebenen Organisagtionseinheit zurueck. 
	   */
	  public Vector<Ausschreibung> findByForeignAusschreibenderId(int organisationseinheitId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Ausschreibender_id=" + organisationseinheitId + " ORDER BY Bezeichnung");
			
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("Ausschreibung_Id"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
				a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
				a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
				a.setProjektId(rs.getInt("Projekt_Id"));

				result.add(a);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentitaet aus der Datenbank, gemaess den Informationen des uebergebenen Objekts, loeschen.
	   */
	  public void delete(Ausschreibung a){
		  
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("DELETE FROM ausschreibung WHERE Ausschreibung_Id=" + a.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentiaet aus der Datenbank, gemaess den Informationen des uebergebenen Obejtks, aktualisieren.
	   */
	  public Ausschreibung update(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE ausschreibung SET Bezeichnung=" + a.getBezeichnung() + ", " 
			  		+ "Bewerbungsfrist=" + a.getBewerbungsfrist() + ", " + "Ausschreibungstext=" + 
					  a.getAusschreibungstext() + ", " + "Ausschreibender_Id="
					  + a.getAusschreibenderId() + ", " + "Partnerprofil_Id=" + a.getPartnerprofilId() + 
					  ", " + "Partnerprofil_Id=" + a.getPartnerprofilId());
			  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  return a;
		  
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Uebergebenes Objekt als neue Entitaet in die Datenbank schreiben.
	   */
	  public Ausschreibung insert(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement(); 
			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(Ausschreibender_Id) AS maxid FROM ausschreibung");
			  
			  if (rs.next()) {
				
				  a.setId(rs.getInt("maxid") + 1);
				  
				  stmt = con.createStatement();
				  stmt.executeUpdate("INSERT INTO ausschreibung (Ausschreibender_Id, Ausschreibungstext,"
				  		+ "Bewerbungsfrist, Bezeichnung, Partnerprofil_Id, Projekt_Id) VALUES ("
				  		+ a.getAusschreibenderId() + ", " + a.getAusschreibungstext() + ", " + a.getBewerbungsfrist()
				  		+ ", " + a.getBezeichnung() + ", " + a.getPartnerprofilId() + ", " + a.getProjektId());
				  
			}
			  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		  
		  return a;
	  }
}
