package de.hdm.itProjektSS17.server.db;

import java.util.Vector;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;
import java.sql.*;

/**
 * Mapper für Organisationseinheit- Objekte
 */
public class OrganisationseinheitMapper {
	
	/*Die Klasse OrganisationseinheitMapper soll durch einen sogenannten Singlelton
	 *nur einmal instantiiert werden.
	 *Durch static ist die Variable nur einmal für alle möglichen Instanzen der Klasse vorhanden
	 *und speichert die einzige Instanz der Klasse.
	 */
	private static OrganisationseinheitMapper organisationseinheitMapper = null;
	
	
	/*Konstruktor der Klasse Organisationseinheit. Durch protected wird verhindert,
	 *dass durch "new" neue Instanzen der Klasse erzeugt werden können.
	 */
	protected OrganisationseinheitMapper(){
	}
	
	/*Durch die static Methode kann durch OrganisationseinheitMapper.OrganisationseinheitMapper() aufgerufen werden.
	 *(OrganisationseinheitMapper sollte nie über new instantiiert werden, sondern stets über die static Methode!)
	 *Dank dieser Methode wird die Singletoneigenschaft sicher gestellt, sodass immer nur eine einzige Instanz von 
	 *Organisationseinheit existiert.
	 *Returnt wird das OrganisationseinheitMapper-Objekt.
	 */
	public static OrganisationseinheitMapper organisationseinheitMapper(){
		if(organisationseinheitMapper == null){
			organisationseinheitMapper = new OrganisationseinheitMapper();
		}
		return organisationseinheitMapper;
	}
	
	
	/*Suche einer Organisationseinheit durch eine eindeutige ID(Primärschlüssel).
	 *Die Organisationseinheit mit der übergebenen ID wird zurückgegeben.
	 */
	public Organisationseinheit findById(int id){
		
		Connection con  = DBConnection.connection();
		try{
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select Id,Straße, Organisationseinheit FROM Organisationseinheit"
					+ "Where id="+id);
			
			if(rs.next()){
				Organisationseinheit a = new Organisationseinheit();
				a.setId(rs.getInt("id"));
				a.setStraße(rs.getString("Strasse"));
				a.setHausnummer(rs.getString("Hausnummer"));
				a.setPlz(rs.getInt("PLZ"));
				a.setOrt(rs.getString("PLZ"));
				
				return a;
			}
			
		}catch(SQLException e2){
			
			e2.printStackTrace();
			return null;
		}

		return null;
		
		
		
	}
	
	/**
	 * 
	 * @param o
	 * @return Liefert eine Organisationseinheit entsprechend des uebergebenen Objekts zurueck
	 */
	public Organisationseinheit findByObject(Organisationseinheit o){
		
		return o;
		
	}
	
	/*Suche von Organisationseinheit-Objekten auf einem bestimmten Projektmarktplatz.
	 *Hierzu wird ein Projektmarktplatz übergeben und Organisationseinheit-Objekt(e) zurückgegeben.
	 */
	public Vector<Organisationseinheit> findByForeignProjektmarktplatzId(int projektmarktplatzId){
		return null;
	}
	
	
	/*Suceh von einer Organisationseinheit durch ein übergebendes Partnerprofil.
	 *Ein Organisationseinheit-Objekt wird zurueckgegeben.
	 */
	public Organisationseinheit findByForeignPartnerprofilId(int partnerprofilId){
		return null;
		
	}
	
	
	/*Durch die insert-Methode kann eine neue Organisationseinheit in die Datenbank geschrieben werden.
	 *Die id des Projekts wird überprüft und im Verlauf der Methode ggf. angepasst.
	 */
	public Organisationseinheit insert(Organisationseinheit o){
		Connection con  = DBConnection.connection();
		try{
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) "
          + "FROM Organisationseinheit");
			
			if(rs.next()){
			o.setId(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSER INTO Organisationseinheit(id,straße,hausnummer plz, ort)"+"VALUES("+
			o.getId()+","
					+o.getStraße()
					+","
					+o.getHausnummer()
					+","+o.getPlz()
					+","
					+o.getOrt());
			}
			
			
		}catch(SQLException e2){
			
			e2.printStackTrace();
			
		}

		
		return o;
	}
	
	
	/*Durch die update-Methode kann ein Objekt wiederholt in die Datenbank geschrieben und verändert/ angepasst werden.
	 *Übergeben wird das Organisationseinheit-Objekt welches nochmals in die Datenbank geschrieben wird.
	 *Return wird das übergebene Objekt.
	 * 
	 */
	public Organisationseinheit update(Organisationseinheit o){
		return o;
	}
	
	
	/*Durch die delete- Methode kann ein Organisationseinheit Objekt in der Datenbank gelöscht werden.
	 *Gelöscht wird das übergebene Objekt.
	 */
	public void delete(Organisationseinheit o){
		
	}
}