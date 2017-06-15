package de.hdm.itProjektSS17.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektSS17.client.gui.BewerbungenAufAusschreibungForm.BewertungBewerbungHybrid;
import de.hdm.itProjektSS17.shared.bo.*;

@RemoteServiceRelativePath("Verwaltung")
public interface ProjektmarktplatzVerwaltung extends RemoteService{
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#init();
	**/
	public void init() throws IllegalArgumentException;

	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBeteiligung(int, Date, Date, int, int, int);
	**/

	public Beteiligung createBeteiligungProjektleiter(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByBewerbung(Bewerbung);
	**/
	public Ausschreibung getAusschreibungByBewerbung(Bewerbung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#setPerson(Person);
	**/
	public void setPerson(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPerson();
	**/
	public Person getPerson() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllPersonen();
	**/
	public Vector<Person> getAllPersonen() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createEigenschaft(String, String, int);
	**/
	public Eigenschaft createEigenschaft(String name, String wert, int partnerprofilId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createAusschreibung(String, Date, String, int, int, int);
	**/
	public Ausschreibung createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext, int projektId, int ausschreibenderId, int partnerprofilId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createPartnerprofil();
	**/
	public Partnerprofil createPartnerprofil() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBewerbung(String, int, int);
	**/
	public Bewerbung createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createProjekt(Date, Date, String, String, int, int);
	**/
	public Projekt createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId, int projektmarktplatzId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBewertung(Date, String, double, int);
	**/
	public Bewertung createBewertung(Date erstellungsdatum, String stellungnahme, double wert, int bewerbungId)
			throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createTeam(String, String, String, int, String, int, Integer);
	**/
	public Team createTeam(String name, String strasse, String hausnr, int plz, 
			String ort, int partnerprofilId, Integer unternehmenId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createPerson(String, String, String, String, String, String, int, String, int, Integer, Integer);
	**/
	public Person createPerson(String email, String vorname, String nachname, String anrede, 
			String strasse, String hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createProjektmarktplatz(String);
	**/
	public Projektmarktplatz createProjektmarktplatz(String bezeichnung) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBeteiligung(int, Date, Date, int, int, int);
	**/
	public Beteiligung createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId, int bewertungId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createTeilnahme(int, int);
	**/
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createArbeitsverhaeltnis(int, int);
	**/
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createZugehoerigkeit(int, int);
	**/
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createMitgliedschaft(int, int);
	**/
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteAusschreibung(Ausschreibung);
	**/
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteEigenschaft(Eigenschaft);
	**/
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deletePartnerprofil(Partnerprofil);
	**/
	public void deletePartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBewerbung(Bewerbung);
	**/
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteProjekt(Projekt);
	**/
	public void deleteProjekt(Projekt p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBewertung(Bewertung);
	**/
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteTeam(Team);
	**/
	public void deleteTeam(Team t) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteTeam(Team);
	**/
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deletePerson(Person);
	**/
	public void deletePerson(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteProjektmarktplatz(Projektmarktplatz);
	**/
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBeteiligung(Beteiligung);
	**/
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteTeilnahme(Person, Projektmarktplatz);
	**/
	public void deleteTeilnahme(Person po, Projektmarktplatz p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteArbeitsverhaeltnis(Person);
	**/
	public void deleteArbeitsverhaeltnis(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteZugehoerigkeit(Team);
	**/
	public void deleteZugehoerigkeit(Team t) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteMitgliedschaft(Person);
	**/
	public void deleteMitgliedschaft(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungById(int);
	**/
	public Ausschreibung getAusschreibungById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getUnternehmenById(int);
	**/
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getTeamById(int);
	**/
	public Team getTeamById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonById(int);
	**/
	public Person getPersonById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektmarktplatzById(int);
	**/
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektmarktplaetzeByForeignPerson(Person);
	**/
	public Vector<Projektmarktplatz> getProjektmarktplaetzeByForeignPerson(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektById(int);
	**/
	public Projekt getProjektById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungById(int);
	**/
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewertungById(int);
	**/
	public Bewertung getBewertungById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungById(int);
	**/
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerprofilById(int);
	**/
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftById(int);
	**/
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignOrganisationseinheit(Organisationseinheit);
	**/
	public Vector<Ausschreibung> getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getOrganisationseinheitByForeignPartnerprofil(Partnerprofil);
	**/
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonByForeignTeam(Team);
	**/
	public Vector<Person> getPersonByForeignTeam(Team t) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonByForeignUnternehmen(Unternehmen);
	**/
	public Vector<Person> getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getTeamByForeignUnternehmen(Unternehmen);
	**/
	public Vector<Team> getTeamByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignOrganisationseinheit(Organisationseinheit);
	**/
	public Vector<Beteiligung> getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignProjekt(Projekt);
	**/
	public Vector<Beteiligung> getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignBewertung(Bewertung);
	**/
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonByForeignUnternehmen(Unternehmen);
	**/
	public Vector<Person> getPersonenByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektByForeignProjektmarktplatz(Projektmarktplatz);
	**/
	public Vector<Projekt> getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektByForeignPerson(Person);
	**/
	public Vector<Projekt> getProjektByForeignPerson(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewertungByForeignBewerbung(Bewerbung);
	**/
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignOrganisationseinheit(Organisationseinheit);
	**/
	public Vector<Bewerbung> getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignAusschreibung(Ausschreibung);
	**/
	public Vector<Bewerbung> getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignPartnerprofil(Partnerprofil);
	**/
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftByForeignPartnerprofil(Partnerprofil);
	**/
	public Vector<Eigenschaft> getEigenschaftByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getUnternehmenByForeignOrganisationseinheit(Organisationseinheit);
	**/
	Unternehmen getUnternehmenByForeignOrganisationseinheit(Organisationseinheit o);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBewerbung(Bewerbung);
	**/
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveProjektmarktplatz(Projektmarktplatz);
	**/
	public void saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveTeam(Team);
	**/
	public void saveTeam(Team t) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveEigenschaft(Eigenschaft);
	**/
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveUnternehmen(Unternehmen);
	**/
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#savePerson(Person);
	**/
	public void savePerson(Person p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBeteiligung(Beteiligung);
	**/
	public void saveBeteiligung(Beteiligung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBewertung(Bewertung);
	**/
	public void saveBewertung(Bewertung b) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#savePartnerprofil(Partnerprofil);
	**/
	public void savePartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveProjekt(Projekt);
	**/
	public void saveProjekt(Projekt p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveAusschreibung(Ausschreibung);
	**/
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllOrganisationseinheiten();
	**/
	public Vector<Organisationseinheit> getAllOrganisationseinheiten() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignProjekt(Projekt);
	**/
	public Vector<Ausschreibung> getAusschreibungByForeignProjekt(Projekt p) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createUnternehmen(String, String, String, int, String, Integer);
	**/
	public Unternehmen createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, Integer partnerprofilId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit);
	**/
	public Partnerprofil getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getOrganisationseinheitById(int);
	**/
	public Organisationseinheit getOrganisationseinheitById(int orgaId) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllProjektmarktplatz();
	**/
	public Vector<Projektmarktplatz> getAllProjektmarktplatz() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignAusschreibungId(int);
	**/
	public Vector<Bewerbung> getBewerbungByForeignAusschreibungId(int id)throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllAusschreibungen();
	**/
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjekteByBeteiligungen(Vector);
	**/
	public Vector<Projekt> getProjekteByBeteiligungen(Vector<Beteiligung> bt) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllTeams();
	**/
	public Vector<Team> getAllTeams() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllUnternehmen();
	**/
	public Vector<Unternehmen> getAllUnternehmen() throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektbyAusschreibung(Ausschreibung);
	**/
	public Projekt getProjektbyAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftenByForeignPartnerprofilId(int);
	**/
	public Vector<Eigenschaft> getEigenschaftenByForeignPartnerprofilId(int id) throws IllegalArgumentException;
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerProfilByForeignAusschreibung(Ausschreibung);
	**/
	public Partnerprofil getPartnerProfilByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException;

}

