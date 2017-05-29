package de.hdm.itProjektSS17.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public interface ProjektmarktplatzVerwaltungAsync {

	void init(AsyncCallback<Void> callback);

	void createEigenschaft(String name, String wert, int partnerprofilId, AsyncCallback<Eigenschaft> callback);

	void createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext, int projektId,
			int ausschreibenderId, int partnerprofilId, AsyncCallback<Ausschreibung> callback);

	void createPartnerprofil(Date erstellungsdatum, Date aenderungsdatum,
			AsyncCallback<Partnerprofil> callback);

	void createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId,
			AsyncCallback<Bewerbung> callback);

	void createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId,
			int projektmarktplatzId, AsyncCallback<Projekt> callback);

	void createBewertung(Date erstellungsdatum, String stellungnahme, double wert, int bewerbungId,
			AsyncCallback<Bewertung> callback);


	void createTeam(String name, String strasse, String hausnr, int plz, String ort,
			int partnerprofilId, Integer unternehmenId, AsyncCallback<Team> callback);


	void createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, Integer partnerprofilId, AsyncCallback<Unternehmen> callback);

	void createPerson(String vorname, String nachname, String anrede, String strasse, String hausnr, int plz,
			String ort, int partnerprofilId, Integer teamId, Integer unternehmenId,
			AsyncCallback<Person> callback);

	void createProjektmarktplatz(String bezeichnung, AsyncCallback<Projektmarktplatz> callback);

	void createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId, int bewertungId,
			AsyncCallback<Beteiligung> callback);

	void createTeilnahme(int orgaId, int projmarktplatzId, AsyncCallback<Void> callback);

	void createArbeitsverhaeltnis(int unternehmenId, int personId, AsyncCallback<Void> callback);

	void createMitgliedschaft(int teamId, int personId, AsyncCallback<Void> callback);

	void createZugehoerigkeit(int unternehmenId, int teamId, AsyncCallback<Void> callback);

	void deleteArbeitsverhaeltnis(Person p, AsyncCallback<Void> callback);

	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void deleteBeteiligung(Beteiligung b, AsyncCallback<Void> callback);

	void deleteBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void deleteBewertung(Bewertung b, AsyncCallback<Void> callback);

	void deleteEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	void deleteMitgliedschaft(Person p, AsyncCallback<Void> callback);

	void deletePartnerprofil(Partnerprofil p, AsyncCallback<Void> callback);

	void deletePerson(Person p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Void> callback);

	void deleteTeam(Team t, AsyncCallback<Void> callback);

	void deleteTeilnahme(Person po, Projektmarktplatz p, AsyncCallback<Void> callback);

	void deleteUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	void deleteZugehoerigkeit(Team t, AsyncCallback<Void> callback);

	void getAllOrganisationseinheiten(AsyncCallback<Vector<Organisationseinheit>> callback);

	void getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o,
			AsyncCallback<Vector<Ausschreibung>> callback);

	void getAusschreibungByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Ausschreibung> callback);

	void getAusschreibungByForeignProjekt(Projekt p, AsyncCallback<Vector<Ausschreibung>> callback);

	void getAusschreibungById(int id, AsyncCallback<Ausschreibung> callback);

	void getBeteiligungByForeignBewertung(Bewertung b, AsyncCallback<Beteiligung> callback);

	void getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o,
			AsyncCallback<Vector<Beteiligung>> callback);

	void getBeteiligungByForeignProjekt(Projekt p, AsyncCallback<Vector<Beteiligung>> callback);

	void getBeteiligungById(int id, AsyncCallback<Beteiligung> callback);

	void getBewerbungByForeignAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);

    void getBewerbungByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);

	void getBewerbungById(int id, AsyncCallback<Bewerbung> callback);

	void getBewertungByForeignBewerbung(Bewerbung b, AsyncCallback<Bewertung> callback);

	void getBewertungById(int id, AsyncCallback<Bewertung> callback);

	void getEigenschaftByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Vector<Eigenschaft>> callback);

	void getEigenschaftById(int id, AsyncCallback<Eigenschaft> callback);

	void getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p, AsyncCallback<Organisationseinheit> callback);

	void getPersonenByForeignProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Vector<Person>> callback);

	void getPartnerprofilById(int id, AsyncCallback<Partnerprofil> callback);

	void getPersonByForeignTeam(Team t, AsyncCallback<Vector<Person>> callback);

	void getPersonByForeignUnternehmen(Unternehmen u, AsyncCallback<Vector<Person>> callback);

	void getPersonById(int id, AsyncCallback<Person> callback);

	void getProjektByForeignPerson(Person p, AsyncCallback<Vector<Projekt>> callback);

	void getProjektByForeignProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Vector<Projekt>> callback);

	void getProjektById(int id, AsyncCallback<Projekt> callback);

	void getProjektmarktplatzById(int id, AsyncCallback<Projektmarktplatz> callback);

	void getTeamById(int id, AsyncCallback<Team> callback);

	void getUnternehmenById(int id, AsyncCallback<Unternehmen> callback);

	void saveAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void saveBeteiligung(Beteiligung b, AsyncCallback<Void> callback);

	void saveBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void saveBewertung(Bewertung b, AsyncCallback<Void> callback);

	void saveEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	void savePartnerprofil(Partnerprofil p, AsyncCallback<Void> callback);

	void savePerson(Person p, AsyncCallback<Void> callback);

	void saveProjekt(Projekt p, AsyncCallback<Void> callback);

	void saveProjektmarktplatz(Projektmarktplatz p, AsyncCallback<Void> callback);

	void saveTeam(Team t, AsyncCallback<Void> callback);

	void saveUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	void getPerson(AsyncCallback<Person> callback);

	void setPerson(Person p, AsyncCallback<Void> callback);

	void getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Partnerprofil> callback);

	void getUnternehmenByForeignOrganisationseinheit(Organisationseinheit o, AsyncCallback<Unternehmen> callback);

	void getProjektmarktplaetzeByForeignPerson(Person p, AsyncCallback<Vector<Projektmarktplatz>> callback);

	void getTeamByForeignUnternehmen(Unternehmen u, AsyncCallback<Vector<Team>> callback);

	void getPersonByIdTest(int id, AsyncCallback<Person> callback);

	void getOrganisationseinheitById(int orgaId, AsyncCallback<Organisationseinheit> callback);

	void getAllProjektmarktplatz(AsyncCallback<Vector<Projektmarktplatz>> callback);


}
