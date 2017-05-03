package de.hdm.itProjektSS17.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itProjektSS17.shared.bo.*;

public interface ProjektmarktplatzVerwaltung extends RemoteService{

	public void init() throws IllegalArgumentException;
	
	public void setPerson(Person p) throws IllegalArgumentException;
	
	public Person getPerson() throws IllegalArgumentException;
	
	public Eigenschaft createEigenschaft(String name, String wert, int partnerprofilId) throws IllegalArgumentException;
	
	public Ausschreibung createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext, int projektId) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil_Ausschreibung(Date erstellungsdatum, Date aenderungsdatum, int ausschreibungId) throws IllegalArgumentException;
	
	public Partnerprofil createPartnerprofil_Organisationseinheit(Date erstellungsdatum, Date aenderungsdatum, int orgaId) throws IllegalArgumentException;
	
	public Bewerbung createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId) throws IllegalArgumentException;
	
	public Projekt createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId, int projektmarktplatzId) throws IllegalArgumentException;
	
	public Bewertung createBewertung(Date erstellungsdatum, double wert, int bewerbungId) throws IllegalArgumentException;
	
	public Team createTeam(String name, int unternehmenId) throws IllegalArgumentException;
	
	public Unternehmen createUnternehmen(String name, int personId) throws IllegalArgumentException;
	
	public Person createPerson(String vorname, String nachname, String anrede) throws IllegalArgumentException;
	
	public Projektmarktplatz createProjektmarktplatz(String bezeichnung) throws IllegalArgumentException;
	
	public Beteiligung createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId) throws IllegalArgumentException;
	
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException;
	
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException;
	
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException;
	
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException;
	
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public void deletePartnerprofil_Organisationseinheit(Partnerprofil p) throws IllegalArgumentException;
	
	public void deletePartnerprofil_Ausschreibung(Partnerprofil p) throws IllegalArgumentException;
	
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public void deleteProjekt(Projekt p) throws IllegalArgumentException;
	
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException;
	
	public void deleteTeam(Team t) throws IllegalArgumentException;
	
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public void deletePerson(Person p) throws IllegalArgumentException;
	
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException;
	
	public void deleteTeilnahme(Organisationseinheit o, Projektmarktplatz p) throws IllegalArgumentException;
	
	public void deleteArbeitsverhaeltnis(Unternehmen u, Person p) throws IllegalArgumentException;
	
	public void deleteZugehoerigkeit(Unternehmen u, Team t) throws IllegalArgumentException;
	
	public void deleteMitgliedschaft(Team t, Person p) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungById(int id) throws IllegalArgumentException;
	
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException;
	
	public Team getTeamById(int id) throws IllegalArgumentException;
	
	public Person getPersonById(int id) throws IllegalArgumentException;
	
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException;
	
	public Projekt getProjektById(int id) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException;
	
	public Bewertung getBewertungById(int id) throws IllegalArgumentException;
	
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException;
	
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException;
	
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Team getTeamByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Person getPersonByForeignTeam(Team t) throws IllegalArgumentException;
	
	public Person getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException;
	
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException;
	
	public Organisationseinheit getOrganisationseinheitByForeingProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public Projekt getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public Projekt getProjektByForeignPerson(Person p) throws IllegalArgumentException;
	
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public Bewerbung getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
	
	public Bewerbung getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Eigenschaft getEigenschaftByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Bewerbung saveBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public Projektmarktplatz saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	
	public Team saveTeam(Team t) throws IllegalArgumentException;
	
	public Eigenschaft saveEigenschaft(Eigenschaft e) throws IllegalArgumentException;
	
	public Unternehmen saveUnternehmen(Unternehmen u) throws IllegalArgumentException;
	
	public Person savePerson(Person p) throws IllegalArgumentException;
	
	public Beteiligung saveBeteiligung(Beteiligung b) throws IllegalArgumentException;
	
	public Bewertung saveBewertung(Bewertung b) throws IllegalArgumentException;
	
	public Partnerprofil savePartnerprofil(Partnerprofil p) throws IllegalArgumentException;
	
	public Projekt saveProjekt(Projekt p) throws IllegalArgumentException;
	
	public Ausschreibung saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	
	/**
	 * TODO (ARRAY??)
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Organisationseinheit getAllOrganisationseinheiten() throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungByForeignProjekt(Projekt p) throws IllegalArgumentException;
	
	
	
	
}

