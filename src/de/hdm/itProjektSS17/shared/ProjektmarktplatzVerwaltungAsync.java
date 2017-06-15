package de.hdm.itProjektSS17.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.client.gui.BewerbungenAufAusschreibungForm.BewertungBewerbungHybrid;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.BusinessObject;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public interface ProjektmarktplatzVerwaltungAsync {

	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#init();
	**/
	void init(AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createEigenschaft(String, String, int);
	**/
	void createEigenschaft(String name, String wert, int partnerprofilId, AsyncCallback<Eigenschaft> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createAusschreibung(String, Date, String, int, int, int);
	**/
	void createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext, int projektId,
			int ausschreibenderId, int partnerprofilId, AsyncCallback<Ausschreibung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createPartnerprofil();
	**/
	void createPartnerprofil(AsyncCallback<Partnerprofil> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBewerbung(String, int, int);
	**/
	void createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId,
			AsyncCallback<Bewerbung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createProjekt(Date, Date, String, String, int, int);
	**/
	void createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId,
			int projektmarktplatzId, AsyncCallback<Projekt> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBewertung(Date, String, double, int);
	**/
	void createBewertung(Date erstellungsdatum, String stellungnahme, double wert, int bewerbungId,
			AsyncCallback<Bewertung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createTeam(String, String, String, int, String, int, Integer);
	**/
	void createTeam(String name, String strasse, String hausnr, int plz, String ort,
			int partnerprofilId, Integer unternehmenId, AsyncCallback<Team> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createUnternehmen(String, String, String, int, String, Integer);
	**/
	void createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, Integer partnerprofilId, AsyncCallback<Unternehmen> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createPerson(String, String, String, String, String, String, int, String, int, Integer, Integer);
	**/
	void createPerson(String email, String vorname, String nachname, String anrede, String strasse, String hausnr,
			int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId,
			AsyncCallback<Person> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createProjektmarktplatz(String);
	**/
	void createProjektmarktplatz(String bezeichnung, AsyncCallback<Projektmarktplatz> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBeteiligung(int, Date, Date, int, int, int);
	**/
	void createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId, int bewertungId,
			AsyncCallback<Beteiligung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createTeilnahme(int, int);
	**/
	void createTeilnahme(int orgaId, int projmarktplatzId, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createArbeitsverhaeltnis(int, int);
	**/
	void createArbeitsverhaeltnis(int unternehmenId, int personId, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createMitgliedschaft(int, int);
	**/
	void createMitgliedschaft(int teamId, int personId, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createZugehoerigkeit(int, int);
	**/
	void createZugehoerigkeit(int unternehmenId, int teamId, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteArbeitsverhaeltnis(Person);
	**/
	void deleteArbeitsverhaeltnis(Person p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteAusschreibung(Ausschreibung);
	**/
	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBeteiligung(Beteiligung);
	**/
	void deleteBeteiligung(Beteiligung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBewerbung(Bewerbung);
	**/
	void deleteBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteBewertung(Bewertung):
	**/
	void deleteBewertung(Bewertung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteEigenschaft(Eigenschaft);
	**/
	void deleteEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteMitgliedschaft(Person);
	**/
	void deleteMitgliedschaft(Person p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deletePartnerprofil(Partnerprofil):
	**/
	void deletePartnerprofil(Partnerprofil p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deletePerson(Person)
	**/
	void deletePerson(Person p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteProjekt(Projekt);
	**/
	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteProjektmarktplatz(Projektmarktplatz);
	**/
	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteTeam(Team);
	**/
	void deleteTeam(Team t, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteTeilnahme(Person, Projektmarktplatz);
	**/
	void deleteTeilnahme(Person po, Projektmarktplatz p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteUnternehmen(Unternehmen);
	**/
	void deleteUnternehmen(Unternehmen u, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#deleteZugehoerigkeit(Team);
	**/
	void deleteZugehoerigkeit(Team t, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllOrganisationseinheiten();
	**/
	void getAllOrganisationseinheiten(AsyncCallback<Vector<Organisationseinheit>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignOrganisationseinheit(Organisationseinheit);
	**/
	void getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o,
			AsyncCallback<Vector<Ausschreibung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignPartnerprofil(Partnerprofil);
	**/
	void getAusschreibungByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Ausschreibung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungByForeignProjekt(Projekt)
	**/
	void getAusschreibungByForeignProjekt(Projekt p, AsyncCallback<Vector<Ausschreibung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAusschreibungById(int);
	**/
	void getAusschreibungById(int id, AsyncCallback<Ausschreibung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignBewertung(Bewertung);
	**/
	void getBeteiligungByForeignBewertung(Bewertung b, AsyncCallback<Beteiligung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignOrganisationseinheit(Organisationseinheit);
	**/
	void getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o,
			AsyncCallback<Vector<Beteiligung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungByForeignProjekt(Projekt);
	**/
	void getBeteiligungByForeignProjekt(Projekt p, AsyncCallback<Vector<Beteiligung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBeteiligungById(int);
	**/
	void getBeteiligungById(int id, AsyncCallback<Beteiligung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignAusschreibung(Ausschreibung);
	**/
	void getBewerbungByForeignAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignOrganisationseinheit(Organisationseinheit);
	**/
    void getBewerbungByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);
    /**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungById(int);
	**/
	void getBewerbungById(int id, AsyncCallback<Bewerbung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewertungByForeignBewerbung(Bewerbung)
	**/
	void getBewertungByForeignBewerbung(Bewerbung b, AsyncCallback<Bewertung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewertungById(int);
	**/
	void getBewertungById(int id, AsyncCallback<Bewertung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftenByForeignPartnerprofilId(int);
	**/
	void getEigenschaftByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Vector<Eigenschaft>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftById(int);
	**/
	void getEigenschaftById(int id, AsyncCallback<Eigenschaft> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getOrganisationseinheitByForeignPartnerprofil(Partnerprofil);
	**/
	void getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Organisationseinheit> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonenByForeignProjektmarktplatz(Projektmarktplatz);
	**/
	void getPersonenByForeignProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Vector<Person>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerprofilById(int);
	**/
	void getPartnerprofilById(int id, AsyncCallback<Partnerprofil> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonByForeignTeam(Team);
	**/
	void getPersonByForeignTeam(Team t, AsyncCallback<Vector<Person>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonByForeignUnternehmen(Unternehmen);
	**/
	void getPersonByForeignUnternehmen(Unternehmen u, AsyncCallback<Vector<Person>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPersonById(int);
	**/
	void getPersonById(int id, AsyncCallback<Person> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektByForeignPerson(Person);
	**/
	void getProjektByForeignPerson(Person p, AsyncCallback<Vector<Projekt>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektByForeignProjektmarktplatz(Projektmarktplatz);
	**/
	void getProjektByForeignProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Vector<Projekt>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektById(int);
	**/
	void getProjektById(int id, AsyncCallback<Projekt> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektmarktplatzById(int);
	**/
	void getProjektmarktplatzById(int id, AsyncCallback<Projektmarktplatz> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getTeamById(int);
	**/
	void getTeamById(int id, AsyncCallback<Team> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getUnternehmenById(int);
	**/
	void getUnternehmenById(int id, AsyncCallback<Unternehmen> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveAusschreibung(Ausschreibung);
	**/
	void saveAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBeteiligung(Beteiligung);
	**/
	void saveBeteiligung(Beteiligung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBewerbung(Bewerbung);
	**/
	void saveBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveBewertung(Bewertung);
	**/
	void saveBewertung(Bewertung b, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveEigenschaft(Eigenschaft);
	**/
	void saveEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#savePartnerprofil(Partnerprofil);
	**/
	void savePartnerprofil(Partnerprofil p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#savePerson(Person);
	**/
	void savePerson(Person p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveProjekt(Projekt);
	**/
	void saveProjekt(Projekt p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveProjektmarktplatz(Projektmarktplatz);
	**/
	void saveProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveTeam(Team);
	**/
	void saveTeam(Team t, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#saveUnternehmen(Unternehmen);
	**/
	void saveUnternehmen(Unternehmen u, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPerson();
	**/
	void getPerson(AsyncCallback<Person> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#setPerson(Person);
	**/
	void setPerson(Person p, AsyncCallback<Void> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit);
	**/
	void getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Partnerprofil> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getUnternehmenByForeignOrganisationseinheit(Organisationseinheit);
	**/
	void getUnternehmenByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Unternehmen> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjektmarktplaetzeByForeignPerson(Person);
	**/
	void getProjektmarktplaetzeByForeignPerson(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getTeamByForeignUnternehmen(Unternehmen);
	**/
	void getTeamByForeignUnternehmen(Unternehmen u, AsyncCallback<Vector<Team>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getOrganisationseinheitById(int);
	**/
	void getOrganisationseinheitById(int orgaId, AsyncCallback<Organisationseinheit> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllProjektmarktplatz();
	**/
	void getAllProjektmarktplatz(AsyncCallback<Vector<Projektmarktplatz>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllAusschreibungen();
	**/
	void getAllAusschreibungen(AsyncCallback<Vector<Ausschreibung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getBewerbungByForeignAusschreibungId(int);
	**/
	void getBewerbungByForeignAusschreibungId(int id, AsyncCallback<Vector<Bewerbung>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getProjekteByBeteiligungen(Vector);
	**/
	void getProjekteByBeteiligungen(Vector<Beteiligung> bt, AsyncCallback<Vector<Projekt>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllTeams();
	**/
	void getAllTeams(AsyncCallback<Vector<Team>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllUnternehmen();
	**/
	void getAllUnternehmen(AsyncCallback<Vector<Unternehmen>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBeteiligungProjektleiter(int, Date, Date, int, int);
	**/
	void getAusschreibungByBewerbung(Bewerbung b, AsyncCallback<Ausschreibung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#createBeteiligungProjektleiter(int, Date, Date, int, int);
	**/
	void createBeteiligungProjektleiter(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId,
			AsyncCallback<Beteiligung> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getAllPersonen();
	**/
	void getAllPersonen(AsyncCallback<Vector<Person>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl
	**/
	void getProjektbyAusschreibung(Ausschreibung a, AsyncCallback<Projekt> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getEigenschaftenByForeignPartnerprofilId(int);
	**/
	void getEigenschaftenByForeignPartnerprofilId(int id, AsyncCallback<Vector<Eigenschaft>> callback);
	/**
 	 *@see de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl#getPartnerProfilByForeignAusschreibung(Ausschreibung);
	**/
	void getPartnerProfilByForeignAusschreibung(Ausschreibung a, AsyncCallback<Partnerprofil> callback);


}
