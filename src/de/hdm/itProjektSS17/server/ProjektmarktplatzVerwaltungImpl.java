package de.hdm.itProjektSS17.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.server.db.*;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung.Ausschreibungsstatus;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;
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

	/**
	 * Anlegen einer neuer Ausschreibung.
	 */
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
		a.setStatus(Ausschreibungsstatus.laufend);
		
		return this.ausschreibungMapper.insert(a); 
	}

	/**
	 * Erstellung eines neuen Partnerprofils
	 */
	public Partnerprofil createPartnerprofil(Date erstellungsdatum, Date aenderungsdatum) throws IllegalArgumentException {
		
		Partnerprofil p = new Partnerprofil();
		p.setId(1);
		p.setErstellungsdatum(erstellungsdatum);
		p.setAenderungdatum(aenderungsdatum);
		
		//Das Partnerprofil wird in die Datenbank geschrieben. Bei der Insert Methode wird dann
		//die korrekte ID vergeben.
		return partnerprofilMapper.insert(p);
	}
	
	/**
	 * Anlegen eines neuen Bewerbung-Objekts.
	 */
	public Bewerbung createBewerbung(String bewerbungstext, int orgaId, int ausschreibungId) throws IllegalArgumentException{
		Bewerbung b = new Bewerbung();
		b.setBewerbungstext(bewerbungstext);
		b.setOrganisationseinheitId(orgaId);
		b.setAusschreibungId(ausschreibungId);
		b.setStatus(Bewerbungsstatus.laufend);

		return this.bewerbungMapper.insert(b);
	}

	/**
	 * Anlegen eines neuen Projekt-Objekts.
	 */
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

	/**
	 * Anlegen einer neuen Bewertung.
	 */
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

	/**
	 * Erstellen eines neuen Team-Objekts.
	 */
	@Override
	public Team createTeam(String name, String strasse, String hausnr, int plz, 
			String ort, int partnerprofilId, Integer unternehmenId) throws IllegalArgumentException {
		Team a = new Team();
		a.setName(name);
		a.setStrasse(strasse);
		a.setHausnummer(hausnr);
		a.setPlz(plz);
		a.setOrt(ort);

		a.setPartnerprofilId(partnerprofilId);
		a.setUnternehmenId(unternehmenId);
		
		a.setId(1); 
		
		return this.teamMapper.insert(a);
	}
	
	
	
	/**
	 * Anlegen eines Unternehmen-Objekts.
	 */
	@Override

	public Unternehmen createUnternehmen(String name, String hausnummer, String ort, int plz, String strasse, Integer partnerprofilId) throws IllegalArgumentException {
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
		
		return this.projektmarktplatzMapper.insert(pr);
	}

	/**
	 * Erstellung einer neuen Beteiligung.
	 */
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

	/**
	 * Anlegen eines neuen Teilnahme-Objekts.
	 */
	@Override
	public void createTeilnahme(int orgaId, int projmarktplatzId) throws IllegalArgumentException {
		this.teilnahmeMapper.insert(this.getPersonById(orgaId), this.getProjektmarktplatzById(projmarktplatzId));
		
	}

	/**
	 * Erstellen eines neuen Arbeitsverhältnisses zwischen einer Person und einem Unternehmen.
	 */
	@Override
	public void createArbeitsverhaeltnis(int unternehmenId, int personId) throws IllegalArgumentException {
		//this.personMapper.findById(personId).setUnternehmenId(unternehmenId);
		//this.personMapper.update(personMapper.findById(personId).setUnternehmenId(unternehmenId));
		Person p = this.personMapper.findById(personId);
		p.setUnternehmenId(unternehmenId);
		
		//personMapper.findById(personId).setUnternehmenId(unternehmenId);
		
		
		this.personMapper.update(p);
	}

	
	/**
	 * Erstellen einer Zugehörigkeit zwischen einem Team und einem Unternehmen.
	 */
	@Override
	public void createZugehoerigkeit(int unternehmenId, int teamId) throws IllegalArgumentException {
		
		Team t = this.teamMapper.findById(teamId);
		t.setUnternehmenId(unternehmenId);
		
		this.teamMapper.update(t);	
	}
	
	
	/**
	 * Erstellung einer Mitgliedschaft von einer Person in einem Team.
	 */
	@Override
	public void createMitgliedschaft(int teamId, int personId) throws IllegalArgumentException {
		Person p = this.personMapper.findById(personId);
		p.setTeamId(teamId);
		
		this.personMapper.update(p);
	}

	
	/**
	 * Löscung eines Ausschreibung-Objekts inkl. den dazugehörigen Bewerbungen.
	 */
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

	
	/**
	 * 
	 * @param a
	 * @return Es wird ein Partnerprofil zu der übergebenen Ausschreibung zurückgegeben.
	 */
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

	
	/**
	 * Löschung eines Partnerprofils-Objekts inkl. der dazugehörigen Eigenschaften.
	 */
	public void deletePartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		Vector<Eigenschaft> e = this.getEigenschaftByForeignPartnerprofil(p);
		
		if(e != null){
			for(Eigenschaft eigenschaft : e){
				this.eigenschaftMapper.delete(eigenschaft);
			}
		this.partnerprofilMapper.delete(p);
		}
	}

	
	/**
	 * Löschung eines Bewerbung-Objekts inkl. der dazugehörigen Bewertung.
	 */
	@Override
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException {
		
		Bewertung be = this.getBewertungByForeignBewerbung(b);
		
		if (be != null) {
			this.deleteBewertung(be);
		}
		
		this.bewerbungMapper.delete(b);		
	}
	
	
	/**
	 * Löschung eines Projekt-Objekts inkl. der dazugehörigen Ausschreibungen und deren Partnerprofile.
	 */
	@Override
	public void deleteProjekt(Projekt p) throws IllegalArgumentException {
		
		Vector<Ausschreibung> ausschreibungen = this.getAusschreibungByForeignProjekt(p);
		
		
		for (Ausschreibung ausschreibung : ausschreibungen) {
			if (ausschreibung.getPartnerprofilId() == this.getPartnerProfilByForeignAusschreibung(ausschreibung).getId()) {
				
				Partnerprofil pp = this.getPartnerProfilByForeignAusschreibung(ausschreibung);
				this.deletePartnerprofil(pp);
			}
		}
		
		this.projektMapper.delete(p);
	}

	
	/**
	 * Löschung eines Bewertung-Objekts inkl. der dazugehörigen Beteiligung.
	 */
	@Override
	public void deleteBewertung(Bewertung b) throws IllegalArgumentException {
		
		Beteiligung be = this.getBeteiligungByForeignBewertung(b);
		
		if (be != null) {
			this.deleteBeteiligung(be);
		}
		
		this.bewertungMapper.delete(b);
	}

	
	/**
	 * Löschung eines Team-Objekts inkl. aller Abhängigkeiten.
	 */
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
	 * Löschen eines Unternehmen-Objekts inkl. aller Abhängigkeiten.
	 */
	@Override
	public void deleteUnternehmen(Unternehmen u) throws IllegalArgumentException {
		/*
		 * Auslesen des zu einem Unternehmen zugehörigen Partnerprofils und der Beteiligungen eines Unternehmens an Projekte.
		 */
		Partnerprofil p = this.getPartnerprofilByForeignOrganisationseinheit(u);

		Vector<Beteiligung> b = this.getBeteiligungByForeignOrganisationseinheit(u);
		Vector<Bewerbung> bw = this.getBewerbungByForeignOrganisationseinheit(u);
		/*
		 * Es wird geprüft, ob ein Partnerprofil zu dem zu löschenden Unternehmen besteht.
		 * Wenn eines besteht wird dieses gelöscht.
		 */
		if (p != null){
			this.deletePartnerprofil(p);
		}
		
		if (bw != null){
			for(Bewerbung bewerbung: bw){
				this.deleteBewerbung(bewerbung);;
			}
		}
		/*
		 * Es wird geprüft, ob das zu löschende Unternehmen an Projekten beteiligt ist. 
		 * Falls ja, werden die Beteiligungen an den sProjekten gelöscht. 
		 */
		if (b != null)
		{
			for (Beteiligung beteiligung: b)
			{
				this.deleteBeteiligung(beteiligung);
			}
		}
		// Account aus der DB entfernen
	    this.unternehmenMapper.delete(u);
	}

	
	
	/**
	 * Lösung einer Person inkl. aller Abhängigkeiten.
	 */
	@Override
	public void deletePerson(Person p) throws IllegalArgumentException {
		
		
		/*
		 * Auslesen des zu einer Person zugehörigen Partnerprofils, der Beteiligungen einer Person an Projekten, der Zugehörigkeit
		 * einer Person an einem Team und/ oder einem Unternehmen.
		 */
		Partnerprofil pp = this.getPartnerprofilByForeignOrganisationseinheit(p);
		Vector<Beteiligung> be = this.getBeteiligungByForeignOrganisationseinheit(p);
		Team te = this.getTeamByForeignPerson(p);
		Unternehmen un = this.getUnternehmenByForeignOrganisationseinheit(p);
		Vector<Projektmarktplatz> pm = this.getProjektmarktplaetzeByPerson(p);
		Vector<Bewerbung> bew = this.getBewerbungByForeignOrganisationseinheit(p);
		Vector<Projekt> proj = this.getProjektByForeignPerson(p);
		
		
	
		/**
		 * Es wird geprüft, ob die zu löschende Person in einem Unternehmen beschäftigt ist.
		 * Falls ja, wird das Arbeitsverhältnis zwischen Person und Unternehmen gelöscht. 
		 */
		if (un != null){
			this.deleteArbeitsverhaeltnis(p);
		}
		
		/**
		 * Es wird geprüft, ob die zu löschende Person Mitglied in Teams ist.
		 * Falls ja, werden die Mitgliedschaften an Teams gelöscht.
		 */
		if (te != null){
				
					this.deleteMitgliedschaft(p);
			
		}
		
		/**
		 * Es wird geprüft, ob die zu löschende Person Mitglied in einem Projektmarktplatz ist.
		 * Falls ja, werden die Mitgliedschaften an Projektmarktplätzen gelöscht.
		 */
		if(pm != null){
			for(Projektmarktplatz projm : pm){
				this.deleteTeilnahme(p, projm);
			}
		}
		
		
		/*
		 * Es wird geprüft, ob die zu löschende Person an Projekten beteiligt ist. 
		 * Falls ja, werden die Beteiligungen an den Projekten gelöscht. 
		 */
		if (be != null){
			for (Beteiligung beteiligung: be){
				this.deleteBeteiligung(beteiligung);
			}	
		}	
	
		
		if(bew != null){
			for(Bewerbung bewerbung : bew){
				this.deleteBewerbung(bewerbung);
			}
		}
		
		if(proj != null){
			for(Projekt projekt : proj){
				this.deleteProjekt(projekt);
			}
		}
		
		/**
		  * Die übergebene Person-Objekt wird gelöscht.
		  */
		 this.personMapper.delete(p);
		
		/*
		 * Es wird geprüft, ob ein Partnerprofil zu der zu löschenden Person besteht.
		 * Wenn eines besteht wird dieses gelöscht.
		 */
		if (pp != null){
			this.deletePartnerprofil(pp);;
		}
	
	}
	
	
	
	/**
	 * 
	 * @param p
	 * @return Gibt ein Team anhand der übergebenen Person zurück.
	 */
	private Team getTeamByForeignPerson(Person p) {
		
		if (p.getTeamId() != null && this.teamMapper != null) {
			
			return this.teamMapper.findById(p.getTeamId());
		}
		
		return null;
	}

	
	
	/**
	 * 
	 * @param p
	 * @return Gibt alle Projektmakrtplätze zurück, auf denen die übergebene Person teilnimmt.
	 * @throws IllegalArgumentException
	 */
	public Vector<Projektmarktplatz> getProjektmarktplaetzeByPerson(Person p) throws IllegalArgumentException {
		
		Vector<Projektmarktplatz> result = new Vector<>();
		
		if (p != null && this.projektmarktplatzMapper != null) {
			Vector<Projektmarktplatz> projektmarktplaetze = this.teilnahmeMapper.findRelatedProjektMarktplaetze(p); 
			
			if (projektmarktplaetze != null) {
				result.addAll(projektmarktplaetze);
			}
			
		}
		return result;
		
		
		
		
	}

	/**
	 * Löschen des übergebenen Projektmarktplatzes inkl. der sich daraufbefindenen Projekte und Teilnahmen von Personen.
	 */
	@Override
	public void deleteProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		
		Vector <Projekt> projekte = this.getProjektByForeignProjektmarktplatz(p);
		Vector <Person> personen = this.getRelatedPersonen(p);
		
		for (Projekt pro : projekte) {
			this.deleteProjekt(pro);
		}
		for (Person pers : personen) {
			this.deleteTeilnahme(pers, p);
		}
		this.projektmarktplatzMapper.delete(p);
	}
	

	
	/**
	 * 
	 * @param p
	 * @return Gibt alle Personen auf dem übergebenen Projektmarktplatz zurück.
	 */
	public Vector<Person> getRelatedPersonen(Projektmarktplatz p){
		return this.teilnahmeMapper.findRelatedPersonen(p);
	}
	
	
	
	/**
	 * Löscht das übergebene Beteiligungs-Objekt.
	 */
	@Override
	public void deleteBeteiligung(Beteiligung b) throws IllegalArgumentException {
		this.beteiligungMapper.delete(b);
		
	}

	
	/**
	 * Löscht die Teilnahme der übergebenen Person auf dem übergebenen Projektmarktplatz.
	 */
	@Override
	public void deleteTeilnahme(Person po, Projektmarktplatz p) throws IllegalArgumentException {
		this.teilnahmeMapper.delete(po, p);
		
	}

	
	/**
	 * Löscht das Arbeitsverhältnis zwischen einem Unternehmen und der übergebenen Person.
	 */
	@Override
	public void deleteArbeitsverhaeltnis(Person p) throws IllegalArgumentException {
		p.setUnternehmenId(0);
		this.personMapper.update(p);
		
	}

	
	/**
	 * Löscht die Zugehörigkeit zwischen einem Unternehmen und des übergebenen Team-Objekts.
	 */
	@Override
	public void deleteZugehoerigkeit(Team t) throws IllegalArgumentException {
		t.setUnternehmenId(0);
		this.teamMapper.update(t);
	}

	
	/**
	 * Löscht die Mitgliedschaft zwischen einem Team und der übergebenen Person.
	 */
	@Override
	public void deleteMitgliedschaft(Person p) throws IllegalArgumentException {
		p.setTeamId(0);
		this.personMapper.update(p);
		
	}

	
	/**
	 * Gibt eine Ausschreibung anhand der übergebenen Id zurück.
	 */
	@Override
	public Ausschreibung getAusschreibungById(int id) throws IllegalArgumentException {	
		return this.ausschreibungMapper.findById(id);
	}

	
	/**
	 * Gibt ein Unternehmen anhand der übergebenen Id zurück.
	 */
	@Override
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException {
		return this.unternehmenMapper.findById(id);
	}

	
	/**
	 * Gibt ein Team anhand der übergebenen Id zurück.
	 */
	@Override
	public Team getTeamById(int id) throws IllegalArgumentException {
		return this.teamMapper.findById(id);
	}

	
	/**
	 * Gibt ein Person anhand der übergebenen Id zurück.
	 */
	@Override
	public Person getPersonById(int id) throws IllegalArgumentException {
		return this.personMapper.findById(id);
	}

	
	/**
	 * Gibt einen Projektmarktplatz anhand der übergebenen Id zurück.
	 */
	@Override
	public Projektmarktplatz getProjektmarktplatzById(int id) throws IllegalArgumentException {
		return this.projektmarktplatzMapper.findById(id);
	}
	
	
	/**
	 * Gibt alle Projektmarktplätze zurück auf denen die übergebene Person eine Teilnahme besitzt.
	 */
	@Override
	public Vector<Projektmarktplatz> getProjektmarktplaetzeByForeignPerson(Person p) throws IllegalArgumentException {
		return this.teilnahmeMapper.findRelatedProjektMarktplaetze(p);
	}

	
	/**
	 * Gibt ein Projekt anhand der übergebenen Id zurück.
	 */
	@Override
	public Projekt getProjektById(int id) throws IllegalArgumentException {
		return this.projektMapper.findById(id);
	}
	
	
	/**
	 * Gibt eine Beteiligung anhand der übergebenen Id zurück.
	 */
	@Override
	public Beteiligung getBeteiligungById(int id) throws IllegalArgumentException {
		return this.beteiligungMapper.findById(id);
	}

	
	/**
	 * Gibt eine Bewertung anhand der übergebenen Id zurück.
	 */
	@Override
	public Bewertung getBewertungById(int id) throws IllegalArgumentException {
		return this.bewertungMapper.findById(id);
	}

	
	/**
	 * Gibt eine Bewerbung anhand der übergebenen Id zurück.
	 */
	@Override
	public Bewerbung getBewerbungById(int id) throws IllegalArgumentException {
		return this.bewerbungMapper.findById(id);
	}

	
	/**
	 * Gibt ein Partnerprofil anhand der übergebenen Id zurück.
	 */
	@Override
	public Partnerprofil getPartnerprofilById(int id) throws IllegalArgumentException {
		return this.partnerprofilMapper.findById(id);
	}

	
	/**
	 * Gibt ein Eigenschafts-Objekt anhand der übergebenen Id zurück.
	 */
	@Override
	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findById(id);
	}
	
	
	/**
	 * Gibt alle Ausschreibungen die von der übergebenen Organisationseinheit erstellt wurden zurück.
	 */
	@Override
	public Vector<Ausschreibung> getAusschreibungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		return this.ausschreibungMapper.findByForeignAusschreibenderId(o.getId());
	}

	
	/**
	 * Gibt die Organisationseinheit zu dem übergebenen Partnerprofil-Objekt zurück.
	 */
	@Override
	public Organisationseinheit getOrganisationseinheitByForeignPartnerprofil(Partnerprofil p){
		
			Vector<Person> pe = personMapper.findAllPerson();
			Vector<Team> t = teamMapper.findAllTeam();
			Vector<Unternehmen> u = unternehmenMapper.findAllUnternehmen();
			
			
			for (Person per : pe) {
				if (p.getId() == personMapper.findById(per.getId()).getPartnerprofilId()) {
					return per;
				}
			}
			for (Team te : t) {
				if (p.getId() == teamMapper.findById(te.getPartnerprofilId()).getId()) {
					return te;
				}
			}
			for (Unternehmen un : u) {
				if (un.getId() == unternehmenMapper.findById(un.getPartnerprofilId()).getId()) {
					return un;
				}
			}
	
		return null;
	}

	
	/**
	 * Gibt alle Personen in dem übergebenen Team zurück.
	 */
	@Override
	public Vector<Person> getPersonByForeignTeam(Team t) throws IllegalArgumentException {
		return this.personMapper.findByForeignTeamId(t.getId());
	}

	
	/**
	 * Gibt alle Personen in dem übergebenen Unternehmen zurück
	 */
	@Override
	public Vector<Person> getPersonByForeignUnternehmen(Unternehmen u) throws IllegalArgumentException {
		return this.personMapper.findByForeignUnternehmenId(u.getId());
	}

	
	/**
	 * Gibt alle Beteiligungen des übergebenen Organisationseinheit-Objekts zurück.
	 */
	@Override
	public Vector<Beteiligung> getBeteiligungByForeignOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {
		Vector<Beteiligung> result = new Vector<>();
		
		if (o != null && this.beteiligungMapper != null) {
			Vector<Beteiligung> beteiligungen = this.beteiligungMapper.findByForeignBeteiligterID(o.getId());
			
			if (beteiligungen != null) {
				result.addAll(beteiligungen);
			}
		}
		
		return result;
	}

	
	/**
	 * Gibt alle Beteiligungen von dem übergebenen Projekt zurück.
	 */
	@Override
	public Vector<Beteiligung> getBeteiligungByForeignProjekt(Projekt p) throws IllegalArgumentException {
		return this.beteiligungMapper.findByForeignProjektId(p.getId());
	}

	
	/**
	 * Gibt die Beteiligung zu dem übergebenen Bewertungs-Objekt zurück.
	 */
	@Override
	public Beteiligung getBeteiligungByForeignBewertung(Bewertung b) throws IllegalArgumentException {
		return this.beteiligungMapper.findByForeignBewertung(b.getId());
	}

	
	/**
	 * Gibt alle Personen die sich auf dem übergebenen Projektmarktplatz befinden zurück.
	 */
	@Override
	public Vector<Person> getPersonenByForeignProjektmarktplatz(Projektmarktplatz p){
		return TeilnahmeMapper.teilnahmeMapper().findRelatedPersonen(p);
	}

	
	/**
	 * Gibt alle Projekte die sich auf dem übergebenen Projektmarktplatz befinden zurück.
	 */
	@Override
	public Vector<Projekt> getProjektByForeignProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		return this.projektMapper.findByForeignProjektmarktplatzId(p.getId());
	}

	
	/**
	 * Gibt alle Projekte der übergebenen Person zurück.
	 */
	@Override
	public Vector<Projekt> getProjektByForeignPerson(Person p) throws IllegalArgumentException {
		
		Vector<Projekt> result = new Vector<>();
		
		if (p != null && this.projektMapper != null) {
			
			Vector<Projekt> projekte = this.projektMapper.findByForeignProjektleiterId(p.getId());
			
			if (projekte != null) {
				result.addAll(projekte);
			}
		}
		
		return result;
		
	}

	
	/**
	 * Gibt eine Bewertung zu der übergebenen Bewerbung zurück.
	 */
	@Override
	public Bewertung getBewertungByForeignBewerbung(Bewerbung b) throws IllegalArgumentException {
		
		if (b != null && this.bewertungMapper != null) {
			Bewertung be = this.bewertungMapper.findByForeignBewerbungId(b.getId());
			return be;
		}
	
		return null;
	}

	
	/**
	 * Liefert einen Vector mit Bewerbungen anhand des übergebenen Organisationseinheit-Objekts.
	 */
	@Override
	public Vector<Bewerbung> getBewerbungByForeignOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException {
		
		Vector<Bewerbung> result = new Vector<>();
		
		if (o != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> bewerbungen = this.bewerbungMapper.findByForeignOrganisationseinheitId(o.getId());
			
			if (bewerbungen != null) {
				result.addAll(bewerbungen);
			}
		}
		
		return result;
	}

	
	/**
	 * Liefert einen Vector mit Bewerbungen anhand des �bergebenen Ausschreibung-Objekts.
	 */
	@Override
	public Vector<Bewerbung> getBewerbungByForeignAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		
		Vector <Bewerbung> result = new Vector <Bewerbung>();
		if(a!=null && this.bewerbungMapper != null)
		{
			Vector <Bewerbung> bewerbungen = this.bewerbungMapper.findByForeignAusschreibungId(a.getId());
			
			if(bewerbungen != null){
				result.addAll(bewerbungen);
			}
		}
		
		return result;
	}

	
	/**
	 * Gibt eine Ausschreibung zu dem übergebenen Partnerprofil-Objekt zurück.
	 */
	@Override
	public Ausschreibung getAusschreibungByForeignPartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		return this.ausschreibungMapper.findByForeignPartnerprofilId(p.getId());
	}

	
	/**
	 * Gibt alle Eigenschaft zu dem übergebenen Partnerprofil-Objekt zurück.
	 */
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
	

	/**
	 * Schreibt Änderungen zu dem übergebenen Bewerbung-Objekts in die Datenbank.
	 */
	@Override
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
		this.bewerbungMapper.update(b);
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Projektmarktplatz-Objekts in die Datenbank.
	 */
	@Override
	public void saveProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException {
		this.projektmarktplatzMapper.update(p);
	}


	/**
	 * Schreibt Änderungen zu dem übergebenen Team-Objekts in die Datenbank.
	 */
	@Override
	public void saveTeam(Team t) throws IllegalArgumentException {
		this.teamMapper.update(t);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Eigenschaft-Objekts in die Datenbank.
	 */
	@Override
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		this.eigenschaftMapper.update(e);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Unternehmen-Objekts in die Datenbank.
	 */
	@Override
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		this.unternehmenMapper.update(u);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Personen-Objekts in die Datenbank.
	 */
	@Override
	public void savePerson(Person p) throws IllegalArgumentException {
		this.personMapper.update(p);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Beteiligung-Objekts in die Datenbank.
	 */
	@Override
	public void saveBeteiligung(Beteiligung b) throws IllegalArgumentException {
		this.beteiligungMapper.update(b);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Bewertung-Objekts in die Datenbank.
	 */
	@Override
	public void saveBewertung(Bewertung b) throws IllegalArgumentException {
		this.bewertungMapper.update(b);
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Partnerprofil-Objekts in die Datenbank.
	 */
	@Override
	public void savePartnerprofil(Partnerprofil p) throws IllegalArgumentException {
		this.partnerprofilMapper.update(p);

	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Projekt-Objekts in die Datenbank.
	 */
	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		this.projektMapper.update(p);
		
	}

	
	/**
	 * Schreibt Änderungen zu dem übergebenen Ausschreibung-Objekts in die Datenbank.
	 */
	@Override
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		this.ausschreibungMapper.update(a);
	}

	
	/**
	 * Gibt alle Organisationseinheiten zurück.
	 */
	@Override
	public Vector<Organisationseinheit> getAllOrganisationseinheiten() throws IllegalArgumentException {
		
		Vector<Organisationseinheit> organisationseinheiten = new Vector<Organisationseinheit>();
		
		Vector<Person> p = personMapper.findAllPerson();
		Vector<Team> t = teamMapper.findAllTeam();
		Vector<Unternehmen> u = unternehmenMapper.findAllUnternehmen();
		
		organisationseinheiten.addAll(p);
		organisationseinheiten.addAll(t);
		organisationseinheiten.addAll(u);
		
		return organisationseinheiten;
	}

	
	/**
	 * Gibt alle Ausschreibungen zu dem übergebenen Projekt-Objekt zurück.
	 */
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
				if (((Person) o).getUnternehmenId() != null) {
					
					return this.unternehmenMapper.findById(this.personMapper.findById(o.getId()).getUnternehmenId());
				}
			}
			else if (o instanceof Team){
				if (((Team) o).getUnternehmenId() != null) {
					
					return this.unternehmenMapper.findById(this.teamMapper.findById(o.getId()).getUnternehmenId());	
				}
			}

			return null;
	}
	
	/**
	 * Setzen der Person, für die dieser Projektmarktplatz tätig ist.
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
