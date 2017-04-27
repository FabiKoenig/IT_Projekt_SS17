package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.Ausschreibung;


/**
 * Mapper für Ausschreibung-Objekte
 * 
 * @author Michael Geiselmann
 * @version 1.0
 */
public class AusschreibungMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Speicherung der einzigen Instanz dieser Mapperklasse.
	 */
	private static AusschreibungMapper ausschreibungMapper = null;
	
	/**
	 * Geschützter Konstruktor
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
	   * @return Liefert eine Ausschreibung entsprechend der übergebenen id zurück.
	   */
	  public Ausschreibung findById(int id){
		
		  Connection con =  DBConnection.connection();
		  
		  try {
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Ausschreibung_Id=" + id);
			  
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
			e.printStackTrace();
			return null;
		}
		  
		  return null;
	  }
	
	  /**
	   * 
	   * @param a
	   * @return Liefert eine Ausschreibung entsprechend des übergebenen Objekts zurück.
	   */
	  public Ausschreibung findByObject(Ausschreibung a){
		  return this.findById(a.getId());
	  }
	  /**
	   * 
	   * @param projektId
	   * @return Liefert alle Ausschreibungen zu dem übergebenen Projekt zurück.
	   */
	  public Vector<Ausschreibung> findByForeignProjektId(int projektId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Projekt_Id=" + projektId + " ORDER BY Bezeichnung");
			  
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
			e.printStackTrace();
			
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param partnerprofilId
	   * @return Liefert die Ausschreibung zu dem übergebenen Partnerprofils zurück.
	   */
	  public Ausschreibung findByForeignPartnerprofilId(int partnerprofilId){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Partnerprofil_Id=" + partnerprofilId);
			
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
			
			e.printStackTrace();
			
			return null;
		}
		  return null;
	  }
	  
	  /**
	   * 
	   * @param organisationseinheitId
	   * @return Liefert alle Ausschreibungen der übergebenen Organisagtionseinheit zurück. 
	   */
	  public Vector<Ausschreibung> findByForeignAusschreibenderId(int organisationseinheitId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist FROM ausschreibung "
			  		+ "WHERE Ausschreibender_Id=" + organisationseinheitId + " ORDER BY Bezeichnung");
			
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
			e.printStackTrace();
		}
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentität aus der Datenbank, gemäß den Informationen des übergebenen Objekts, löschen.
	   */
	  public void delete(Ausschreibung a){
		  
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("DELETE FROM ausschreibung WHERE Ausschreibung_Id=" + a.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Zielentität aus der Datenbank, gemäß den Informationen des übergebenen Objekts, aktualisieren.
	   */
	  public Ausschreibung update(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE ausschreibung SET Bezeichnung='" + a.getBezeichnung() + "', " 
			  		+ "Bewerbungsfrist='" + format.format(a.getBewerbungsfrist()) + "', " + "Ausschreibungstext='" + 
					  a.getAusschreibungstext() + "', " + "Ausschreibender_Id="
					  + a.getAusschreibenderId() + ", " + "Partnerprofil_Id=" + a.getPartnerprofilId() + 
					  ", " + "Projekt_Id=" + a.getProjektId() + " WHERE Ausschreibung_Id = " + a.getId());
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return a;
		  
	  }
	  
	  /**
	   * 
	   * @param a
	   * @return Übergebenes Objekt als neue Entität in die Datenbank schreiben.
	   */
	  public Ausschreibung insert(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement(); 
			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(Ausschreibung_Id) AS maxid FROM ausschreibung");
			  
			  if (rs.next()) {
				
				  a.setId(rs.getInt("maxid") + 1);
				  
				  stmt = con.createStatement();
				  stmt.executeUpdate("INSERT INTO ausschreibung (Ausschreibung_Id, Ausschreibender_Id, `Ausschreibungstext`, "
				  		+ "`Bewerbungsfrist`, `Bezeichnung`, Partnerprofil_Id, Projekt_Id) VALUES ("
				  		+ a.getId()+ ", " + a.getAusschreibenderId() + ", '" + a.getAusschreibungstext() + "', '" + format.format(a.getBewerbungsfrist())
				  		+ "', '" + a.getBezeichnung() + "', " + a.getPartnerprofilId() + ", " + a.getProjektId() + ")");
				  
			}
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		  return a;
	  }
}
