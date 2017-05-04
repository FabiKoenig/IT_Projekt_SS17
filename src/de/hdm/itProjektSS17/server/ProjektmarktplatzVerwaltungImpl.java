package de.hdm.itProjektSS17.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.server.db.*;
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


/**
 * Die Klasse <code>ProjektmarktplatzVerwaltungImpl</code> implementiert das Interface
 * ProjektmarktplatzVerwaltung.In der Klasse ist neben ReportGeneratorImpl sämtliche
 * Applikationslogik vorhanden.
 * 
 * 
 * 
 * @author Fabian Koenig
 *
 */

@SuppressWarnings("serial")
public class ProjektmarktplatzVerwaltungImpl extends RemoteServiceServlet 
implements ProjektmarktplatzVerwaltung {


	
	private Person person = null;
	/**
	 * Referenz auf den ProjektmarktplatzMapper, der Projektmarktplatz-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	/**
	 * Referenz auf den TeamMapper, der Team-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private TeamMapper teamMapper = null;
	
	/**
	 * Referenz auf den PersonMapper, der Person-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private PersonMapper personMapper = null;
	
	/**
	 * Referenz auf den UnternehmenMapper, der Unternehmen-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private UnternehmenMapper unternehmenMapper = null;
	
	/**
	 * Referenz auf den OrganisationseinheitMapper, der Organisationseinheit-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private OrganisationseinheitMapper orgaMapper = null;
	
	/**
	 * Referenz auf den BewerbungMapper, der Bewerbung-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private BewerbungMapper bewerbungMapper = null;
	
	/**
	 * Referenz auf den ProjektMapper, der Projekt-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private ProjektMapper projektMapper = null;
	
	/**
	 * Referenz auf den AusschreibungMapper, der Ausschreibung-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private AusschreibungMapper ausschreibungMapper = null;
	
	/**
	 * Referenz auf den PartnerprofilMapper, der Partnerprofil-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private PartnerprofilMapper partnerprofilMapper = null;
	
	/**
	 * Referenz auf den BewertungMapper, der Bewertung-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private BewertungMapper bewertungMapper = null;
	
	/**
	 * Referenz auf den BeteiligungMapper, der Beteiligung-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private BeteiligungMapper beteiligungMapper = null;
	
	/**
	 * Referenz auf den EigenschaftMapper, der Eigenschaft-Objekte
	 * mit der Datenbank abgleicht.
	 */
	private EigenschaftMapper eigenschaftMapper = null;
	
	/**
	 * Referenz auf den TeilnahmeMapper, der Teilnahmen zwischen Personen und Projektmarktpl�tzen realisiert
	 */
	private TeilnahmeMapper teilnahmeMapper = null;
	/**
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. 
	 * 
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * @see #init()
	 * @throws IllegalArgumentException
	 */
	
	public ProjektmarktplatzVerwaltungImpl() throws IllegalArgumentException {	
	}
	
	/**
	 * TODO
	 */
	
	public void init() throws IllegalArgumentException {
		
		/**
		 * Um mit der Datenbank kommunizieren zu koennen benoetigt die Klasse ProjektmarktplatzVerwaltung
		 * einen vollstaendigen Satz von Mappern.
		 */
		this.ausschreibungMapper = AusschreibungMapper.ausschreibungMapper();
		this.beteiligungMapper = BeteiligungMapper.beteiligungMapper();
		this.bewerbungMapper = BewerbungMapper.bewerbungMapper();
		this.bewertungMapper = BewertungMapper.bewertungMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		//this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper();
		this.partnerprofilMapper = PartnerprofilMapper.partnerprofilMapper();
		this.personMapper = PersonMapper.personMapper();
		this.projektMapper = ProjektMapper.projektMapper();
		this.projektmarktplatzMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
		this.teilnahmeMapper = TeilnahmeMapper.teilnahmeMapper();
		
	}
	/**
	 * Anlegen eines Eigenschaft-Objekts.
	 */
	@Override
	public Eigenschaft createEigenschaft(String name, String wert, int partnerprofilId)
			throws IllegalArgumentException {
		/* Setzen der Übegebenen Attribute*/
		Eigenschaft e = new Eigenschaft();
		e.setName(name);
		e.setWert(wert);
		e.setPartnerprofilId(partnerprofilId);
		
		/*
	     * Setzen einer vorläufigen EigenschaftId Der insert-Aufruf liefert dann ein
	     * Objekt, dessen Nummer mit der Datenbank konsistent ist.
	     */
	     e.setId(1);
		
	     // Objekt in der DB speichern.
	     return this.eigenschaftMapper.insert(e);
	}

	@Override
	public Ausschreibung createAusschreibung(String bezeichnung, Date bewerbungsfrist, String ausschreibungstext,
			int projektId, int ausschreibenderId, int partnerprofilId) throws IllegalArgumentException {
		
		Ausschreibung a = new Ausschreibung();
		a.setBezeichnung(bezeichnung);
		a.setBewerbungsfrist(bewerbungsfrist);
		a.setAusschreibungstext(ausschreibungstext);
		a.setProjektId(projektId);
		a.setAusschreibenderId(ausschreibenderId);
		a.setId(1);
		a.setPartnerprofilId(partnerprofilId);
		
		return this.ausschreibungMapper.insert(a); 
	}

	/**Erstellt ein Partnerprofil für eine Ausschreibung*/
	@Override
	public Partnerprofil createPartnerprofil_Ausschreibung(Date erstellungsdatum, Date aenderungsdatum,
			int ausschreibungId) throws IllegalArgumentException {
		
		Partnerprofil p = new Partnerprofil();
		p.setId(1);
		p.setErstellungsdatum(erstellungsdatum);
		p.setAenderungdatum(aenderungsdatum);
		
		//Das Partnerprofil wird in die Datenbank geschrieben. Bei der Insert Methode wird dann
		//die korrekte ID vergeben.
		Partnerprofil pa = partnerprofilMapper.insert(p);
		
		//AusschreibungMapper aufrufen um die passende Ausschreibung zu finden. Anschließend wird dann die 
		//korrekte PartnerprofilId an die Ausschreibung übergeben.
		ausschreibungMapper.findById(ausschreibungId).setPartnerprofilId(pa.getId());
		return null;
	}
	
	
	public Partnerprofil createPartnerprofil_Person(Date erstellungsdatum, Date aenderungsdatum,
			int orgaId) throws IllegalArgumentException {
		
		Partnerprofil p = new Partnerprofil();
		p.setId(1);
		p.setErstellungsdatum(erstellungsdatum);
		p.setAenderungdatum(aenderungsdatum);
		
		//Das Partnerprofil wird in die Datenbank geschrieben. Bei der Insert Methode wird dann
		//die korrekte ID vergeben.
		p = partnerprofilMapper.insert(p);
		
		
		Person pe = personMapper.findById(orgaId);
		pe.setPartnerprofilId(p.getId());
		personMapper.update(pe);
				return null;
	}
	
	
	
	public Partnerprofil createPartnerprofil_Team(Date erstellungsdatum, Date aenderungsdatum,
			int orgaId) throws IllegalArgumentException {
		
		Partnerprofil p = new Partnerprofil();
		p.setId(1);
		p.setErstellungsdatum(erstellungsdatum);
		p.setAenderungdatum(aenderungsdatum);
		
		//Das Partnerprofil wird in die Datenbank geschrieben. Bei der Insert Methode wird dann
		//die korrekte ID vergeben.
		p = partnerprofilMapper.insert(p);
		
		
		Team t = teamMapper.findById(orgaId);
		t.setPartnerprofilId(p.getId());
		teamMapper.update(t);
				return null;
	}
	
	
	
	public Partnerprofil createPartnerprofil_Unternehmen(Date erstellungsdatum, Date aenderungsdatum,
			int orgaId) throws IllegalArgumentException {
		
		Partnerprofil p = new Partnerprofil();
		p.setId(1);
		p.setErstellungsdatum(erstellungsdatum);
		p.setAenderungdatum(aenderungsdatum);
		
		//Das Partnerprofil wird in die Datenbank geschrieben. Bei der Insert Methode wird dann
		//die korrekte ID vergeben.
		p = partnerprofilMapper.insert(p);
		
		
		Unternehmen u = unternehmenMapper.findById(orgaId);
		u.setPartnerprofilId(p.getId());
		unternehmenMapper.update(u);
				return null;
	}
	
	public Bewerbung createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId) throws IllegalArgumentException{
		Bewerbung b = new Bewerbung();
		b.setBewerbungstext(bewerbungstext);
		b.setOrganisationseinheitId(orgaId);
		b.setAusschreibungId(ausschreibungId);
		return this.bewerbungMapper.insert(b);
	}

	@Override
	public Projekt createProjekt(Date startdatum, Date enddatum, String name, String beschreibung, int personId,
			int projektmarktplatzId) throws IllegalArgumentException {
		
		Projekt p = new Projekt();
		p.setBeschreibung(beschreibung);
		p.setEnddatum(enddatum);
		p.setId(1);
		p.setName(name);
		p.setProjektleiterId(personId);
		p.setProjektmarktplatzId(projektmarktplatzId);
		p.setStartdatum(startdatum);
		
		return this.projektMapper.insert(p);
	}

	@Override
	public Bewertung createBewertung(Date erstellungsdatum, String stellungnahme, double wert, int bewerbungId)
			throws IllegalArgumentException {
		Bewertung b = new Bewertung();
		b.setId(1);
		b.setErstellungsdatum(erstellungsdatum);
		b.setStellungnahme(stellungnahme);
		b.setWert(wert);
		b.setBewerbungId(bewerbungId);
		return this.bewertungMapper.insert(b);
	}

	
	@Override
	public Team createTeam(String name, int unternehmenId) throws IllegalArgumentException {
		Team a = new Team();
		a.setName(name);
		a.setUnternehmenId(unternehmenId);
		return this.teamMapper.insert(a);
	}
	/**
	 * Anlegen eines Unternehmen-Objekts.
	 */
	@Override

	public Unternehmen createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, int partnerprofilId) throws IllegalArgumentException {
		Unternehmen u = new Unternehmen();
		u.setName(name);
		u.setHausnummer(hausnummer);
		u.setOrt(ort);
		u.setPlz(plz);
		u.setStrasse(strasse);
		u.setPartnerprofilId(partnerprofilId);
		/*
	     * Setzen einer vorläufigen OrganisationsId. Der insert-Aufruf liefert dann ein
	     * Objekt, dessen Nummer mit der Datenbank konsistent ist.
	     */
	     u.setId(1);
		 // Objekt in der DB speichern.
	return this.unternehmenMapper.insert(u);
	}

	
	/**
	 * Anlegen eines Person-Objekts.
	 */
	@Override
	public Person createPerson(String vorname, String nachname, String anrede, 

		String strasse, String hausnr, int plz, String ort, int partnerprofilId, Integer teamId, Integer unternehmenId) throws IllegalArgumentException {
				
		Person p = new Person();
		p.setId(1);
		p.setVorname(vorname);
		p.setNachname(nachname);
		p.setAnrede(anrede);
		p.setStrasse(strasse);
		p.setHausnummer(hausnr);
		p.setOrt(ort);
		p.setPlz(plz);
		p.setPartnerprofilId(partnerprofilId);
		p.setTeamId(teamId);
		p.setUnternehmenId(unternehmenId);
		return this.personMapper.insert(p);
	}

	/**Anlegen eines Projektmarktplatzes und zuweisen der ersten Person zu diesem Marktplatz.
	 * Die zugewiesene, erste Person ist automatisch immer diejenige, die den Marktplatz erstellt hat.
	 */
	@Override
	public Projektmarktplatz createProjektmarktplatz(String bezeichnung) throws IllegalArgumentException {
		/*Anlegen des Projektmarktplatzes*/
		Projektmarktplatz pr = new Projektmarktplatz();
		pr.setId(1);
		pr.setBezeichnung(bezeichnung);
		
		return pr;
	}

	@Override
	public Beteiligung createBeteiligung(int umfang, Date startdatum, Date enddatum, int orgaId, int projektId, int bewertungId)
			throws IllegalArgumentException {
		
		Beteiligung b = new Beteiligung();
		b.setId(1);
		b.setUmfang(umfang);
		b.setStartDatum(startdatum);
		b.setEndDatum(enddatum);
		b.setBeteiligterId(orgaId);
		b.setProjektId(projektId);
		b.setBewertungId(bewertungId);
		
		
		return this.beteiligungMapper.insert(b);
	}

	@Override
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException {
		this.teilnahmeMapper.insert(this.getPersonById(orgaId), this.getProjektmarktplatzById(projmarktplatzId));
		
	}

	@Override
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException {
		//this.personMapper.findById(personId).setUnternehmenId(unternehmenId);
		//this.personMapper.update(personMapper.findById(personId).setUnternehmenId(unternehmenId));
		Person p = this.personMapper.findById(personId);
		p.setUnternehmenId(unternehmenId);
		
		//personMapper.findById(personId).setUnternehmenId(unternehmenId);
		
		
		this.personMapper.update(p);
	}

	@Override
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException {
		
		Team t = this.teamMapper.findById(teamId);
		t.setUnternehmenId(unternehmenId);
		
		this.teamMapper.update(t);	
	}

	@Override
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException {
		Person p = this.personMapper.findById(personId);
		p.setTeamId(teamId);
		
		this.personMapper.update(p);
	}

	@Override
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		

		Vector<Bewerbung> bewerbungen = this.getBewerbungByForeignAusschreibung(a);
		
		if (bewerbungen != null) {
			for (Bewerbung bewerbung : bewerbungen) {
				this.deleteBewerbung(bewerbung);
			}
		}
		

		
		this.ausschreibungMapper.delete(a);
		
	}

	public Partnerprofil getPartnerProfilByForeignAusschreibung(Ausschreibung a) {
		
		if (a != null && this.partnerprofilMapper != null) {
			return this.partnerprofilMapper.findById(a.getPartnerprofilId());
			
		}
		else {
			return null;
		}
	}
	/**
	 * Löschen eines Eigenschafts-Objekts.
	 */
	@Override
	public void deleteEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		/* Aufruf der MapperKlasse und löschung der übergebenen Eigenschaft*/
		 this.eigenschaftMapper.delete(e);
	}

	@Override
	public void deletePartnerprofil_Person(Partnerprofil p) throws IllegalArgumentException {
		
		this.partnerprofilMapper.delete(p);
	}

	
	@Override
	public void deletePartnerprofil_Team(Partnerprofil p) throws IllegalArgumentException {
		
		Organisationseinheit o = this.getOrganisationseinheitByForeignPartnerprofil(p);
		
		o.setPartnerprofilId(null);
		
		this.partnerprofilMapper.delete(p);
	}
	
	
	
	@Override
	public void deletePartnerprofil_Unternehmen(Partnerprofil p) throws IllegalArgumentException {
	
	}
	
	@Override
	public void deletePartnerprofil_Ausschreibung(Partnerprofil p) throws IllegalArgumentException {
		Vector<Eigenschaft> e = this.getEigenschaftByForeignPartnerprofil(p);
		
		Ausschreibung a = this.getAusschreibungByForeignPartnerprofil(p);
		
		if (a != null) {

			this.deleteAusschreibung(a);
		}
		
		if(e != null){
			for(Eigenschaft eigenschaft : e){
				this.eigenschaftMapper.delete(eigenschaft);
			}
		this.partnerprofilMapper.delete(p);
		}
	}

	@Override
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException {
		
		Bewertung be = this.getBewertungByForeignBewerbung(b);
		
		if (be != null) {
			this.deleteBewertung(be);
		}
		
		this.bewerbungMapper.delete(b);		
	}

	@Override
	public void deleteProjekt(Projekt p) throws IllegalArgumentException {
		
		Vector<Ausschreibung> ausschreibungen = this.getAusschreibungByForeignProjekt(p);
		
		
		for (Ausschreibung ausschreibung : ausschreibungen) {
			if (ausschreibung.getPartnerprofilId() == this.getPartnerProfilByForeignAusschreibung(ausschreibung).getId()) {
				
				Partnerprofil pp = this.getPartnerProfilByForeignAusschreibung(ausschreibung);
				this.deletePartnerprofil_Ausschreibung(pp);
			}
		}
		
		this.projektMapper.delete(p);
	}

	@Override
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException {
		this.bewertungMapper.delete(b);
	}

	@Override
	public void deleteTeam(Team t) throws IllegalArgumentException {
		Partnerprofil p = this.getPartnerprofilByForeignOrganisationseinheit(t);
		Vector <Beteiligung> b = this.getBeteiligungByForeignOrganisationseinheit(t);
	
		
		if(p!=null){
			this.partnerprofilMapper.delete(p);
		}
		if (b != null){
			for (Beteiligung beteiligung: b)
			{
				this.beteiligungMapper.delete(beteiligung);
			}		
		}
		this.teamMapper.delete(t);
		}
	
	/**
	 * Löschen eines Unternehmen-Objekts.
	 */
	@Override
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException {
		/*
		 * Auslesen des zu einem Unternehmen zugehörigen Partnerprofils und der Beteiligungen eines Unternehmens an Projekte.
		 */
		Partnerprofil p = this.getPartnerprofilByForeignOrganisationseinheit(u);

		Vector<Beteiligung> b = this.getBeteiligungByForeignOrganisationseinheit(u);
		/*
		 * Es wird geprüft, ob ein Partnerprofil zu dem zu löschenden Unternehmen besteht.
		 * Wenn eines besteht wird dieses gelöscht.
		 */
		if (p != null){
			this.partnerprofilMapper.delete(p);
		}
		/*
		 * Es wird geprüft, ob das zu löschende Unternehmen an Projekten beteiligt ist. 
		 * Falls ja, werden die Beteiligungen an den Projekten gelöscht. 
		 */
		if (b != null)
		{
			for (Beteiligung beteiligung: b)
			{
				this.beteiligungMapper.delete(beteiligung);
			}
		}
		// Account aus der DB entfernen
	    this.unternehmenMapper.delete(u);
	}

	@Override
	public void deletePerson(Person p) throws IllegalArgumentException {
		
		
		/*
		 * Auslesen des zu einer Person zugehörigen Partnerprofils, der Beteiligungen einer Person an Projekten, der Zugehörigkeit
		 * einer Person an einem Team und/ oder einem Unternehmen.
		 */
		Partnerprofil pp = this.getPartnerprofilByForeignOrganisationseinheit(p);
		Vector<Beteiligung> be = this.getBeteiligungByForeignOrganisationseinheit(p);
		Vector<Team> te = this.getTeamByForeignPerson(p);
		Unternehmen un = this.getUnternehmenByForeignOrganisationseinheit(p);
		
		
		/*
		 * Es wird geprüft, ob ein Partnerprofil zu der zu löschenden Person besteht.
		 * Wenn eines besteht wird dieses gelöscht.
		 */
		if (pp != null){
			this.partnerprofilMapper.delete(pp);
		}
		/*
		 * Es wird geprüft, ob die zu löschende Person an Projekten beteiligt ist. 
		 * Falls ja, werden die Beteiligungen an den Projekten gelöscht. 
		 */
		if (be != null){
			for (Beteiligung beteiligung: be){
				this.beteiligungMapper.delete(beteiligung);
			}	
		}
		/**
		 * Es wird geprüft, ob die zu löschende Person Mitglied in Teams ist.
		 * Falls ja, werden die Mitgliedschaften an Teams gelöscht.
		 */
		if (te != null){
				for(Team team: te){
					this.deleteMitgliedschaft(team, p);
			}
		}
		/**
		 * Es wird geprüft, ob die zu löschende Person in einem Unternehmen beschäftigt ist.
		 * Falls ja, wird das Arbeitsverhältnis zwischen Person und Unternehmen gelöscht. 
		 */
		if (un != null){
			this.deleteArbeitsverhaeltnis(un, p);
		}
		
		/**
		 * Die übergebene Person-Objekt wird gelöscht.
		 */
		this.personMapper.delete(p);
		
		
	}

	/**
	 * L�schen des �bergebenen Projektmarktplatzes
	 */
	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		this.projektmarktplatzMapper.delete(p);
	}

	@Override
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException {
		this.beteiligungMapper.delete(b);
		
	}

	@Override
	public void deleteTeilnahme(Person po, Projektmarktplatz p) throws IllegalArgumentException {
		this.teilnahmeMapper.delete(po, p);
		
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
		return this.ausschreibungMapper.findById(id);
	}

	@Override
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException {
		return this.unternehmenMapper.findById(id);
	}

	@Override
	public Team getTeamById(int id) throws IllegalArgumentException {
		return this.teamMapper.findById(id);
	}

	@Override
	public Person getPersonById(int id) throws IllegalArgumentException {
		return this.personMapper.findById(id);
	}

	@Override
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException {
		return this.projektmarktplatzMapper.findById(id);
	}

	@Override
	public Projekt getProjektById(int id) throws IllegalArgumentException {
		return this.projektMapper.findById(id);
	}

	@Override
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException {
		return this.beteiligungMapper.findById(id);
	}

	@Override
	public Bewertung getBewertungById(int id) throws IllegalArgumentException {
		return this.bewertungMapper.findById(id);
	}

	@Override
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException {
		return this.bewerbungMapper.findById(id);
	}

	@Override
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException {
		return this.partnerprofilMapper.findById(id);
	}

	@Override
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findById(id);
	}

	@Override
	public Ausschreibung getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p){
		
			Vector<Person> pe = personMapper.findAllPerson();
			Vector<Team> t = teamMapper.findAllTeam();
			Vector<Unternehmen> u = unternehmenMapper.findAllUnternehmen();
			
			
				
			for (Person per : pe) {
				if (per.getId() == personMapper.findById(per.getPartnerprofilId()).getId()) {
					return per;
				}
			}
			for (Team te : t) {
				if (te.getId() == personMapper.findById(te.getPartnerprofilId()).getId()) {
					return te;
				}
			}
			for (Unternehmen un : u) {
				if (un.getId() == personMapper.findById(un.getPartnerprofilId()).getId()) {
					return un;
				}
			}
	
		return null;
	}


	@Override
	public Vector<Person> getPersonByForeignTeam(Team t) throws IllegalArgumentException {
		return this.personMapper.findByForeignTeamId(t.getId());
	}

	@Override
	public Vector<Person> getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException {
		return this.personMapper.findByForeignUnternehmenId(u.getId());
	}

	@Override
	public Vector<Beteiligung> getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		//TODO
		return null;

	}

	@Override
	public Vector<Beteiligung> getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException {
		return this.beteiligungMapper.findByForeignProjektId(p.getId());
	}

	@Override
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException {
		return this.beteiligungMapper.findByForeignBewertung(b.getId());
	}

	@Override
	public Organisationseinheit getOrganisationseinheitByForeingProjektmarktplatz(Projektmarktplatz p)
			throws IllegalArgumentException {
		//TODO
		return null;
	}

	@Override
	public Vector<Projekt> getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		return this.projektMapper.findByForeignProjektmarktplatzId(p.getId());
	}

	@Override
	public Vector<Projekt> getProjektByForeignPerson(Person p) throws IllegalArgumentException {
		return this.projektMapper.findByForeignProjektleiterId(p.getId());		
	}

	@Override
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException {
		
		if (b != null && this.bewertungMapper != null) {
			Bewertung be = this.bewertungMapper.findById(b.getId());
			return be;
		}
	
		return null;
	}

	/**
	 * Liefert einen Vector mit Bewerbungen anhand des �bergebenen Organisationseinheit-Objekts.
	 */
	@Override
	public Vector<Bewerbung> getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException {
		return this.bewerbungMapper.findByForeignOrganisationseinheitId(o.getId());
	}

	/**
	 * Liefert einen Vector mit Bewerbungen anhand des �bergebenen Ausschreibung-Objekts.
	 */
	@Override
	public Vector<Bewerbung> getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		return this.bewerbungMapper.findByForeignAusschreibungId(a.getId());
	}

	@Override
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		return this.ausschreibungMapper.findByForeignPartnerprofilId(p.getId());
	}

	@Override
	public Vector<Eigenschaft> getEigenschaftByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		
		Vector <Eigenschaft> result = new Vector<Eigenschaft>();
		
		if(p != null && this.eigenschaftMapper != null ){
			Vector<Eigenschaft> eigenschaft = this.eigenschaftMapper.findByForeignPartnerprofilId(p.getId());
			
			if(eigenschaft != null){
				result.addAll(eigenschaft);
			}
		}
		return result;
	}
	

	@Override
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
		this.bewerbungMapper.update(b);
	}

	@Override
	public void saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		this.projektmarktplatzMapper.update(p);
	}

	@Override
	public void saveTeam(Team t) throws IllegalArgumentException {
		this.teamMapper.update(t);
		
	}

	@Override
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		this.eigenschaftMapper.update(e);
		
	}

	@Override
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		this.unternehmenMapper.update(u);
		
	}

	@Override
	public void savePerson(Person p) throws IllegalArgumentException {
		this.personMapper.update(p);
		
	}

	@Override
	public void saveBeteiligung(Beteiligung b) throws IllegalArgumentException {
		this.beteiligungMapper.update(b);
		
	}

	@Override
	public void saveBewertung(Bewertung b) throws IllegalArgumentException {
		this.bewertungMapper.update(b);
	}

	@Override
	public void savePartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		this.partnerprofilMapper.update(p);

	}

	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		this.projektMapper.update(p);
		
	}

	@Override
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		this.ausschreibungMapper.update(a);
	}

	@Override
	public Organisationseinheit getAllOrganisationseinheiten() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Ausschreibung> getAusschreibungByForeignProjekt(Projekt p) throws IllegalArgumentException {


		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		

		if (p != null && this.ausschreibungMapper != null) {
			Vector<Ausschreibung> ausschreibungen = this.ausschreibungMapper.findByForeignProjektId(p.getId());
			
			if (ausschreibungen != null) {
				result.addAll(ausschreibungen);
			}
		}
		
		return result;
			}



	/**
	 * Es wird überprüft, ob der erste Operand (das übergebene Objekt) zuweisungskompatibel zu einer Klasse ist,
	 * die im zweiten Operand angegeben wird.
	 * 
	 * In unserem Fall wird das übergebene Objekt mit der Klasse Person, Team und Unternehmen verglichen.
	 * Wenn der erste Operand zuweisungskompatibel mit dem zweiten Operand ist, wird aus dem Organisationseinheit-Objekt
	 * die Partnerprofil-Id ausgelesen, mit welcher das zugehörige Partnerprofil ausgelesen und zurückgegeben werden kann.
	 * 
	 * @return das Partnerprofil-Objekt, das dem übergebenen Organisationseinheit-Objekt zugeordnet ist.
	 */
	@Override
	public Partnerprofil getPartnerprofilByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		
		if (o instanceof Person){
			return this.partnerprofilMapper.findById(this.personMapper.findById(o.getId()).getPartnerprofilId());
		} else if(o instanceof Unternehmen){
			return this.partnerprofilMapper.findById(this.unternehmenMapper.findById(o.getId()).getPartnerprofilId());
		} else if (o instanceof Team){
			return this.partnerprofilMapper.findById(this.teamMapper.findById(o.getId()).getPartnerprofilId());
		}
		else{
			return null;
		}
		
	}

	@Override
	public Vector<Team> getTeamByForeignPerson(Organisationseinheit o) throws IllegalArgumentException {
		

		
		return null;
	}

	
	/**
	 * Auslesen des Unternehmens für ein übergebenes Organisationseinheit-Objekt.
	 * 
	 * @return ein Unternehmen-Objekt, das entweder einer Person oder einem Team zugeordnet ist. 
	 */
	@Override
	public Unternehmen getUnternehmenByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		
		/*
		 * Es wird überprüft, ob der erste Operand (das übergebene Objekt) zuweisungskompatibel zu einer Klasse ist,
		 * die im zweiten Operand angegeben wird.
		 * In unserem Fall wird das übergebene Objekt mit der Klasse Person und Team verglichen.
		 * In beiden Fällen wird über die Unternehmen_Id das zugehörige Unternehmen-Objekt zurückgegeben.
		 */
			if (o instanceof Person){
				return this.unternehmenMapper.findById(this.personMapper.findById(o.getId()).getUnternehmenId());
			}
			else if (o instanceof Team){
				return this.unternehmenMapper.findById(this.teamMapper.findById(o.getId()).getUnternehmenId());	
			}

			return null;
	}
	
	
	/**
	 * Setzen der Bank, für die dieser Projektmarktplatz tätig ist.
	 */
	
	@Override
	public void setPerson(Person p) throws IllegalArgumentException {
		this.person = p;
		
	}
	/**
	 * Auslesen der Person, für die dieser Projektmarktplatz tätig ist.
	 */

	@Override
	public Person getPerson() throws IllegalArgumentException {
		return this.person;
	}

}
