package de.hdm.itProjektSS17.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
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

@SuppressWarnings("serial")
public class ProjektmarktplatzVerwaltungImpl extends RemoteServiceServlet 
implements ProjektmarktplatzVerwaltung {


	public ProjektmarktplatzVerwaltungImpl() throws IllegalArgumentException {	
	}
	
	public void init() throws IllegalArgumentException {
		/**
		 * MAPPER TODO
		 */
	}

	@Override
	public Eigenschaft createEigenschaft(String name, String wert, int partnerprofilId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ausschreibung createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext,
			int projektId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partnerprofil createPartnerprofil_Ausschreibung(Date erstellungsdatum, Date aenderungsdatum,
			int ausschreibungId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partnerprofil createPartnerprofil_Organisationseinheit(Date erstellungsdatum, Date aenderungsdatum,
			int orgaId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewerbung createBewerbung(Date erstellungsdatum, String bewerbungstext, int orgaId, int ausschreibungId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekt createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId,
			int projektmarktplatzId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewertung createBewertung(Date erstellungsdatum, double wert, int bewerbungId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team createTeam(String name, int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unternehmen createUnternehmen(String name, int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person createPerson(String vorname, String nachname, String anrede) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projektmarktplatz createProjektmarktplatz(String bezeichnung, int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePartnerprofil_Organisationseinheit(Partnerprofil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePartnerprofil_Ausschreibung(Partnerprofil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProjekt(Projekt p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeam(Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeilnahme(Organisationseinheit o, Projektmarktplatz p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArbeitsverhaeltnis(Unternehmen u, Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteZugehoerigkeit(Unternehmen u, Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMitgliedschaft(Team t, Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ausschreibung getAusschreibungById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekt getProjektById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewertung getBewertungById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ausschreibung getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByForeignTeam(Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisationseinheit getOrganisationseinheitByForeingProjektmarktplatz(Projektmarktplatz p)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekt getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekt getProjektByForeignPerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewerbung getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewerbung getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Eigenschaft getEigenschaftByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewerbung saveBewerbung(Bewerbung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projektmarktplatz saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team saveTeam(Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Eigenschaft saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unternehmen saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person savePerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beteiligung saveBeteiligung(Beteiligung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bewertung saveBewertung(Bewertung b) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partnerprofil savePartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Projekt saveProjekt(Projekt p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ausschreibung saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisationseinheit getAllOrganisationseinheiten() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ausschreibung getAusschreibungByForeignProjekt(Projekt p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
