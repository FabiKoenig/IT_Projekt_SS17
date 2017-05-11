package de.hdm.itProjektSS17.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itProjektSS17.shared.bo.*;

public interface ProjektmarktplatzVerwaltung extends RemoteService{

	public void init() throws IllegalArgumentException;
	
	public void setPerson(Person p) throws IllegalArgumentException;
	
	public Person getPerson() throws IllegalArgumentException;
	
	public Eigenschaft createEigenschaft(String name, String wert, int partnerprofilId) throws IllegalArgumentException;
	
	public Ausschreibung createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext, int projektId, int ausschreibenderId, int partnerprofilId) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil_Ausschreibung(Date erstellungsdatum, Date aenderungsdatum) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil_Person(Date erstellungsdatum, Date aenderungsdatum, int orgaId) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil_Team(Date erstellungsdatum, Date aenderungsdatum, int orgaId) throws IllegalArgumentException;

	public Partnerprofil createPartnerprofil_Unternehmen(Date erstellungsdatum, Date aenderungsdatum, int orgaId) throws IllegalArgumentException;

	public Bewerbung createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId) throws IllegalArgumentException;
	
	public Projekt createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId, int projektmarktplatzId) throws IllegalArgumentException;
	
	public Bewertung createBewertung(Date erstellungsdatum, String stellungnahme, double wert, int bewerbungId)
			throws IllegalArgumentException;

	public Team createTeam(String name, String strasse, String hausnr, int plz, 
			String ort, int partnerprofilId, Integer unternehmenId) throws IllegalArgumentException;

	
	public Person createPerson(String vorname, String nachname, String anrede, 
			String strasse, String hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) throws IllegalArgumentException;
	
	public Projektmarktplatz createProjektmarktplatz(String bezeichnung) throws IllegalArgumentException;
	
	public Beteiligung createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId, int bewertungId) throws IllegalArgumentException;
	
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException;
	
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException;
	
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException;
	
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException;
	
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public void deletePartnerprofil_Person(Partnerprofil p) throws IllegalArgumentException;
	
	public void deletePartnerprofil_Team(Partnerprofil p) throws IllegalArgumentException;

	public void deletePartnerprofil_Unternehmen(Partnerprofil p) throws IllegalArgumentException;
	
	public void deletePartnerprofil_Ausschreibung(Partnerprofil p) throws IllegalArgumentException;
	
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public void deleteProjekt(Projekt p) throws IllegalArgumentException;
	
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException;
	
	public void deleteTeam(Team t) throws IllegalArgumentException;
	
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public void deletePerson(Person p) throws IllegalArgumentException;
	
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException;
	
	public void deleteTeilnahme(Person po, Projektmarktplatz p) throws IllegalArgumentException;
	
	public void deleteArbeitsverhaeltnis(Person p) throws IllegalArgumentException;
	
	public void deleteZugehoerigkeit(Team t) throws IllegalArgumentException;
	
	public void deleteMitgliedschaft(Person p) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungById(int id) throws IllegalArgumentException;
	
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException;
	
	public Team getTeamById(int id) throws IllegalArgumentException;
	
	public Person getPersonById(int id) throws IllegalArgumentException;
	
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException;
	
	public Vector<Projektmarktplatz> getProjektmarktplaetzeByForeignPerson(Person p) throws IllegalArgumentException;
	
	public Projekt getProjektById(int id) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException;
	
	public Bewertung getBewertungById(int id) throws IllegalArgumentException;
	
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException;
	
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException;
	
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Vector<Person> getPersonByForeignTeam(Team t) throws IllegalArgumentException;
	
	public Vector<Person> getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Vector<Beteiligung> getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Vector<Beteiligung> getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException;
	
	public Vector<Person> getPersonenByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public Vector<Projekt> getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public Vector<Projekt> getProjektByForeignPerson(Person p) throws IllegalArgumentException;
	
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public Vector<Bewerbung> getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Vector<Bewerbung> getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Vector<Eigenschaft> getEigenschaftByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	Unternehmen getUnternehmenByForeignOrganisationseinheit(Organisationseinheit o);
	
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public void saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public void saveTeam(Team t) throws IllegalArgumentException;
	
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public void savePerson(Person p) throws IllegalArgumentException;
	
	public void saveBeteiligung(Beteiligung b) throws IllegalArgumentException;
	
	public void saveBewertung(Bewertung b) throws IllegalArgumentException;
	
	public void savePartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public void saveProjekt(Projekt p) throws IllegalArgumentException;
	
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public Vector<Organisationseinheit> getAllOrganisationseinheiten() throws IllegalArgumentException;
	
	public Vector<Ausschreibung> getAusschreibungByForeignProjekt(Projekt p) throws IllegalArgumentException;

	public Unternehmen createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, Integer partnerprofilId) throws IllegalArgumentException;

	public Partnerprofil getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	

	
	
}

