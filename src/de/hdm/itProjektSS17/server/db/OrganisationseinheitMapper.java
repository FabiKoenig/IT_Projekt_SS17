package de.hdm.itProjektSS17.server.db;

import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;

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
	}
	
	
	/*Suche von Organisationseinheit-Objekten auf einem bestimmten Projektmarktplatz.
	 *Hierzu wird ein Projektmarktplatz-Objekt übergeben und Organisationseinheit-Objekt(e) zurückgegeben.
	 */
	public Organisationseinheit findByForeignProjektmarktplatz(Projektmarktplatz p){
	}
	
	
	/*Such von einer Organisationseinheit durch ein übergebendes Partnerprofil- Objekt.
	 *Ein Organisationseinheit-Objekt wird returnt.
	 */
	public Organisationseinheit findByForeignPartnerprofil(Partnerprofil p){
		
	}
	
	
	/*Durch die insert-Methode kann eine neue Organisationseinheit in die Datenbank geschrieben werden.
	 *Die id des Projekts wird überprüft und im Verlauf der Methode ggf. angepasst.
	 */
	public Organisationseinheit insert(Organisationseinheit o){
	}
	
	
	/*Durch die update-Methode kann ein Objekt wiederholt in die Datenbank geschrieben und verändert/ angepasst werden.
	 *Übergeben wird das Organisationseinheit-Objekt welches nochmals in die Datenbank geschrieben wird.
	 *Return wird das übergebene Objekt.
	 * 
	 */
	public Organisationseinheit update(Organisationseinheit o){
	}
	
	
	/*Durch die delete- Methode kann ein Organisationseinheit Objekt in der Datenbank gelöscht werden.
	 *Gelöscht wird das übergebene Objekt.
	 */
	public Organisationseinheit delete(Organisationseinheit o){
		
	}