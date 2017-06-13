package de.hdm.itProjektSS17.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;



import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung.Ausschreibungsstatus;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;


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
	 * Geschützter Konstruktor, um zu verhindern, dass Objekte der Klasse <code>AusschreibungsMapper</code nicht 
	 * außerhalb der Vererbungshierarchie dieser Klasse erstellt werden.
	 */
	protected AusschreibungMapper(){
	}
	
	/**
	 * Sicherstellung der Singleton-Eigenschaft der Mapperklasse, dass nur eine Instanz von 
	 * <code>AusschreibungMapper</code> existiert.
	 * @return <code>AusschreibungMapper</code>-Objekt
	 */
	  public static AusschreibungMapper ausschreibungMapper() {
		    if (ausschreibungMapper == null) {
		      ausschreibungMapper = new AusschreibungMapper();
		    }

		    return ausschreibungMapper;
		  }
	
	  /**
	   * Suchen einer Ausschreibung mit vorgegebener eindeutiger Ausschreibung_Id.
	   * 
	   * @param id Primärschlüssel Ausschreibung_Id der Tabelle ausschreibung
	   * @return Liefert eine Ausschreibung entsprechend der übergebenen id zurück.
	   */
	  public Ausschreibung findById(int id){
		
		  Connection con =  DBConnection.connection();
		  
		  try {

			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT * FROM `ausschreibung` WHERE Ausschreibung_Id=" + id);

			  if (rs.next()) {

				Ausschreibung a = new Ausschreibung();

				a.setId(rs.getInt("Ausschreibung_Id"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
				a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
				a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
				a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
				a.setProjektId(rs.getInt("Projekt_Id"));
				a.setStatus(Ausschreibungsstatus.valueOf(rs.getString("Ausschreibungsstatus")));
				
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		  return null;
	  }
	
	  /**
	   * Suchen der Ausschreibung durch das vorgebene Ausschreibung-Objekt.
	   * @param a
	   * @return Liefert die Ausschreibung entsprechend des übergebenen Objekts zurück.
	   */
	  public Ausschreibung findByObject(Ausschreibung a){
		  return this.findById(a.getId());
	  }
	  /**
	   * Auslesen aller Ausschreibungen eines Projekts.
	   * 
	   * @param projektId Fremdschlüssel Projekt_Id der Tabelle ausschreibung
	   * @return alle Ausschreiben des Projekts
	   */
	  public Vector<Ausschreibung> findByForeignProjektId(int projektId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist, Ausschreibungsstatus FROM ausschreibung "
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
				a.setStatus(Ausschreibungsstatus.valueOf(rs.getString("Ausschreibungsstatus")));
				
				result.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
	  }
	  
	  /**
	   * Auslesen der Ausschreibung des Partnerprofils.
	   * 
	   * @param partnerprofilId Fremdschlüssel Partnerprofil_Id der Tabelle ausschreibung 
	   * @return Liefert die Ausschreibung zu dem übergebenen Partnerprofils zurück.
	   */
	  public Ausschreibung findByForeignPartnerprofilId(int partnerprofilId){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist, Ausschreibungsstatus FROM ausschreibung "
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
				a.setStatus(Ausschreibungsstatus.valueOf(rs.getString("Ausschreibungsstatus")));
				
				return a;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return null;
		}
		  return null;
	  }
	  
	  /**
	   * Auslesen aller Ausschreibungen eines Ausschreibenden.
	   * 
	   * @param organisationseinheitId Fremdschlüssel der Tabelle ausschreibung
	   * @return Liefert alle Ausschreibungen der übergebenen Organisagtionseinheit zurück. 
	   */
	  public Vector<Ausschreibung> findByForeignAusschreibenderId(int organisationseinheitId){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT Ausschreibung_Id, Ausschreibender_Id, Bezeichnung, "
			  		+ "Ausschreibungstext, Partnerprofil_Id, Projekt_Id, Bewerbungsfrist, Ausschreibungsstatus FROM ausschreibung "
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
				a.setStatus(Ausschreibungsstatus.valueOf(rs.getString("Ausschreibungsstatus")));

				result.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
		
	  }
	  
	  /**
	   * Löschen der übergebenen Ausschreibung.
	   * @param a das zu löschende Ausschreibung-Objekt
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
	   * Wiederholtes Schreiben eines <code>Ausschreibung</code>-Objekts in die Datenbank.
	   * 
	   * @param a
	   * @return das als Parameter übergebene, aktualisierte <code>Ausschreibung</code>-Objekt
	   */
	  public Ausschreibung update(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  
			  stmt.executeUpdate("UPDATE ausschreibung SET Bezeichnung='" + a.getBezeichnung() + "', " 
			  		+ "Bewerbungsfrist='" + format.format(a.getBewerbungsfrist()) + "', " + "Ausschreibungstext='" + 
					  a.getAusschreibungstext() + "', Ausschreibungsstatus='" + 
							  a.getStatus() +"' WHERE Ausschreibung_Id = " + a.getId());
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return a;
		  
	  }
	  
	  /**
	   * 
	   * @return Alle Ausschreibung-Objekte
	   */
	  public Vector<Ausschreibung> findAllAusschreibungen(){
			Connection con = DBConnection.connection();
			
			Vector<Ausschreibung> result = new Vector<Ausschreibung>();
			
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * "
						+ "FROM ausschreibung");
				
				
				while (rs.next()){
					Ausschreibung a = new Ausschreibung();
					a.setId(rs.getInt("Ausschreibung_Id"));
					a.setBezeichnung(rs.getString("Bezeichnung"));
					a.setAusschreibenderId(rs.getInt("Ausschreibender_Id"));
					a.setAusschreibungstext(rs.getString("Ausschreibungstext"));
					a.setPartnerprofilId(rs.getInt("Partnerprofil_Id"));
					a.setBewerbungsfrist(rs.getDate("Bewerbungsfrist"));
					a.setProjektId(rs.getInt("Projekt_Id"));
					a.setStatus(Ausschreibungsstatus.valueOf(rs.getString("Ausschreibungsstatus")));
			
					
					result.add(a);
					} 
				}  
			catch (SQLException e) {
			e.printStackTrace();
			}
			return result;
			
		}
	  
	  /**
	   * 
	   * Einfügen eines <code>Ausschreibung</code>-Objekts in die Datenbank. Dabei wird auch der Primärschlüssel des
	   * übergebenen Objekts geprüft und ggf. berichtigt.
	   * @param a das zu speichernde <code>Ausschreibung</code>-Objekt, jedoch mit ggf. korrigiertem Primärschlüssel 
	   * <code>Ausschreibung_Id</code>.
	   * @return Ausschreibung Obejkt a wird in die DB geschrieben
	   */
	  public Ausschreibung insert(Ausschreibung a){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			
			  Statement stmt = con.createStatement(); 
			  
			  ResultSet rs = stmt.executeQuery("SELECT MAX(Ausschreibung_Id) AS maxid FROM ausschreibung");
			  
			  if (rs.next()) {
				
				  a.setId(rs.getInt("maxid") + 1);
			  }  
              
				  stmt = con.createStatement();
				  stmt.executeUpdate("INSERT INTO ausschreibung (Ausschreibung_Id, Ausschreibender_Id, `Ausschreibungstext`, "
				  		+ "`Bewerbungsfrist`, `Bezeichnung`, Ausschreibungsstatus, Partnerprofil_Id, Projekt_Id) VALUES ("
				  		+ a.getId()+ ", " + a.getAusschreibenderId() + ", '" + a.getAusschreibungstext() + "', '" + format.format(a.getBewerbungsfrist())
				  		+ "', '" + a.getBezeichnung() + "', '" + a.getStatus() + "', " + a.getPartnerprofilId() + ", " + a.getProjektId() + ")");
				  
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		  return a;
	  }
}
