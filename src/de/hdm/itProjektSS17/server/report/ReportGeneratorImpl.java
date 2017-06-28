package de.hdm.itProjektSS17.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ReportGenerator;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.AlleAusschreibungenZuPartnerprofilReport;
import de.hdm.itProjektSS17.shared.report.AlleBeteiligungenEinesUsers;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEineAusschreibungDesUsers;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenEinesUsers;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenMitAusschreibungenReport;
import de.hdm.itProjektSS17.shared.report.Column;
import de.hdm.itProjektSS17.shared.report.CompositeParagraph;
import de.hdm.itProjektSS17.shared.report.FanIn;
import de.hdm.itProjektSS17.shared.report.FanInFanOutReport;
import de.hdm.itProjektSS17.shared.report.FanOut;
import de.hdm.itProjektSS17.shared.report.ProjektverflechtungenReport;
import de.hdm.itProjektSS17.shared.report.Row;
import de.hdm.itProjektSS17.shared.report.SimpleParagraph;
import de.hdm.itProjektSS17.shared.report.SimpleReport;


@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator{

	
	/**
	 * Der ReportGenerator benötigt Zugriff auf die ProjektmarktplatzVerwaltung,
	 * da dort wichtige Methoden für die Koexistenz von Datenobjekten enthalten sind.
	 */
	private ProjektmarktplatzVerwaltung projektmarktplatzverwaltung = null;
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}
	
	/**
	 * Initialisierung der Impl
	 */
	public void init() throws IllegalArgumentException{
		/**
		 * 
		 * Hier wird eine ProjektmarktplatzVerwaltungImpl-Instanz instantiiert
		 * um auf dessen Methoden zugreifen zu können.
		 */
		
		ProjektmarktplatzVerwaltungImpl pm = new ProjektmarktplatzVerwaltungImpl();
		pm.init();
		this.projektmarktplatzverwaltung = pm;
	}
	
	/**
	 * Auslesen der zugehörigen ProjektmarktplatzVerwaltung für den internen Gebrauch.
	 * @return das ProjektmarktplatzVerwaltung-Objekt.
	 */
	protected ProjektmarktplatzVerwaltung getProjektmarktplatzVerwaltung(){
		return this.projektmarktplatzverwaltung;
	}
	
	/**
	 * @param Organisationseinheit o
	 * @return Alle Ausschreibungen zum Partnerprofil der übergebenen Organisationseinheit
	 */
	@Override
	public AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Organisationseinheit o)
			throws IllegalArgumentException {
		
		/**
		 * @return Vector mit allen Ausschreibungen
		 */
		Vector<Ausschreibung> alleAusschreibungen = projektmarktplatzverwaltung.getAllAusschreibungen();
		/**
		 * @param o
		 * @return Partnerprofil zur übergebenen Organisationseinheit o
		 */
		Partnerprofil referenzPartnerprofil = projektmarktplatzverwaltung.getPartnerprofilByForeignOrganisationseinheit(o);
		/**
		 * @param referenzPartnerprofil
		 * @return Vector mit allen Eigenschaft zu dem übergebenen Partnerprofil
		 */
		Vector<Eigenschaft> referenzEigenschaften = projektmarktplatzverwaltung.getEigenschaftByForeignPartnerprofil(referenzPartnerprofil);	
	
		
		
		for (Ausschreibung ausschreibung : alleAusschreibungen) {
			/**
			 * @param id des partnerprofil, welches aus dem Ausschreibungs-Objekt gelesen wird
			 * @return Vector mit allen Eigenschaften zu dem übergenen Partneprofil
			 */
			Vector<Eigenschaft> eigenschaftenDerAusschreibung = projektmarktplatzverwaltung.getEigenschaftenByForeignPartnerprofilId(ausschreibung.getPartnerprofilId());
		}
		
		for (Eigenschaft eigenschaft : referenzEigenschaften) {
			
		}
		
		
		return null;
	}

	/**
	 * @param id 
	 * @return Person-Objekt zur übergebenen id
	 */
	public Person getPersonById(int id) throws IllegalArgumentException {
		return projektmarktplatzverwaltung.getPersonById(id);
	}
	
	/**
	 * @param id
	 * @return Team zur übergebnen id
	 */
	public Team getTeamById(int id) throws IllegalArgumentException{
		return projektmarktplatzverwaltung.getTeamById(id);
	}
	
	/**
	 * @param id
	 * @return Unternehmen zur übergebenen Id
	 */
	public Unternehmen getUnternehmenById(int id) throws IllegalArgumentException{
		return projektmarktplatzverwaltung.getUnternehmenById(id);
	}
	
	/**
	 * @return Vector mit allen Personen
	 */
	public Vector<Person> getAllPersonen() throws IllegalArgumentException{		
		return projektmarktplatzverwaltung.getAllPersonen();	
	}
	
	/**
	 * @param Organisationseinheit o
	 * @return Vector mit allen Organisationseinheiten(Bewerber) die sich auf die Ausschreibungen der übergebnen Organisationseinheit o beworben haben
	 */
	public Vector<Organisationseinheit> getBewerberAufEigeneAusschreibungen(Organisationseinheit o) throws IllegalArgumentException{
		
		Vector<Organisationseinheit> bewerber = new Vector<Organisationseinheit>();
		
		/**
		 * @param Organisationseinheit o
		 * @return Vector mit allen Ausschreibung zu dem übergebenen Organisationseinheit-Objekt
		 */
		Vector<Ausschreibung> meineAusschreibungen = projektmarktplatzverwaltung.getAusschreibungByForeignOrganisationseinheit(o);
		
		for (Ausschreibung ausschreibung : meineAusschreibungen) {
			
			/**
			 * @param id der Ausschreibung, welche aus dem Ausschreibung-Objekt gelesen wird
			 * @return Vector mit allen Bewerbungen zur übergebenen Ausschreibung 
			 */
			Vector<Bewerbung> bewerbungen =  projektmarktplatzverwaltung.getBewerbungByForeignAusschreibungId(ausschreibung.getId());
			
				for (Bewerbung bewerbung : bewerbungen) {
					/**
					 * 
					 * @param id der Organisationseinheit, welche aus der jeweiligen Bewerbung gelesen wird
					 * @return Organisationseinheit-Objekt
					 */
					if(bewerber.contains(projektmarktplatzverwaltung.getOrganisationseinheitById(bewerbung.getOrganisationseinheitId()))){
						
					}else{
						/**
						 * 
						 * @param id der Organisationseinheit, welche aus der jeweiligen Bewerbung gelesen wird
						 * @return Organisationseinheit-Objekt
						 */
						bewerber.add(projektmarktplatzverwaltung.getOrganisationseinheitById(bewerbung.getOrganisationseinheitId()));
					}
					
					
				}
			}
		return bewerber;
	}
	
	/**
	 * Erstellt einen Report mit allen Ausschreibung. Hierzu werden alle Column wie z.B. Ausschreibender, Bezeichnung etc.
	 * gesetzt und befüllt 
	 *
	 * @return AusschreibungReport  
	 */
	@Override
	public AlleAusschreibungenReport createAlleAusschreibungenReport() throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
		return null;
		
		}
		
		AlleAusschreibungenReport result = new AlleAusschreibungenReport();
		
		result.setTitel("Alle Ausschreibungen");
		result.setErstellungsdatum(new Date());
		
		
		Row headline = new Row();
		headline.addColum(new Column("Ausschreibender"));
		headline.addColum(new Column("Zugehöriges Projekt"));
		headline.addColum(new Column("Bezeichnung"));
		headline.addColum(new Column("Bewerbungsfrist"));
		headline.addColum(new Column("Ausschreibungstext"));
		headline.addColum(new Column("Ausschreibungsstatus"));
		
		result.addRow(headline);
		
		
		/**
		 * @return Vector mit allen Ausschreibungen
		 */
		Vector<Ausschreibung> alleAusschreibungen = this.projektmarktplatzverwaltung.getAllAusschreibungen();
	
		for(Ausschreibung a : alleAusschreibungen){
			// Eine leere Zeile anlegen.
		      Row ausschreibungRow = new Row();
		            
		      
		   /**
		    * prüfen, ob der Ausschreibene eine Person, Team oder Unternehmen ist.
		    * Erste Spalte: Je nachdem wird der Name des Ausschreibenden gesetzt
		    * @param Id der Organisationseinheit, die aus dem Ausschreibungsobjekt gelesen wird
		    * @return Organisationseinheit-Objekt anhand der übergebenen Id
		    */
		      Organisationseinheit ausschreibender = projektmarktplatzverwaltung.getOrganisationseinheitById(a.getAusschreibenderId());
		      
		      /**
		       * @param Id der Projekts, die aus dem Ausschreibungsobjekt gelesen wird.
		       * @return Projekt-Objekt anhand der übergebenen Id
		       */
		      Projekt zugehoerigesProjekt = projektmarktplatzverwaltung.getProjektById(a.getProjektId());
		      
		      
		      
		      if(ausschreibender instanceof Person){
					ausschreibungRow.addColum(new Column(((Person) ausschreibender).getVorname() 
							+ " " + ((Person) ausschreibender).getNachname()));
				
				} else if(ausschreibender instanceof Team){
					ausschreibungRow.addColum(new Column(((Team) ausschreibender).getName()));
				
				} else if(ausschreibender instanceof Unternehmen){

					ausschreibungRow.addColum(new Column(((Unternehmen) ausschreibender).getName()));
				}	

		      
		   // Zweite Spalte: Zugehöriges Projekt hinzufügen   
		      ausschreibungRow.addColum(new Column(zugehoerigesProjekt.getName()));
		      
		   // Dritte Spalte: Bezeichnung hinzufügen
		      ausschreibungRow.addColum(new Column(a.getBezeichnung()));
		      
		   // Vierte Spalte: Bewerbungsfrist hinzufügen
		      ausschreibungRow.addColum(new Column(a.getBewerbungsfrist().toString()));
		      
		   // Fünfte Spalte: Ausschreibungstext hinzufügen
		      ausschreibungRow.addColum(new Column(a.getAusschreibungstext()));
		      
		   //Sechste Spalte: Ausschreibungsstatus hinzufügen
		      ausschreibungRow.addColum(new Column(a.getStatus().toString()));		
		      
		    //Hinzufügen der Row
		      result.addRow(ausschreibungRow);
		}
		
		return result;	
	}
	
	

	
	/**
	 * 
	 * @param ausschreibungId
	 * @return Report mit allen Bewerbungen auf die übergebene Ausschreibung
	 */
	public AlleBewerbungenAufEineAusschreibungDesUsers AlleBewerbungenAufEineAusschreibungDesUser(int ausschreibungId){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		/**
		 * @param ausschreibungID
		 * @return Ausschreibung anhand der übergebenen ausschreibundId
		 */
		Ausschreibung ausschreibung = projektmarktplatzverwaltung.getAusschreibungById(ausschreibungId);
		
		
		AlleBewerbungenAufEineAusschreibungDesUsers result = new AlleBewerbungenAufEineAusschreibungDesUsers();
		
		result.setTitel("Alle Bewerbungen auf die Ausschreibung: " + ausschreibung.getBezeichnung() 
		+ ", ID: " + ausschreibung.getId());
		
		
		result.setErstellungsdatum(new Date());
		
		
		Row headline = new Row();
		headline.addColum(new Column("Bewerber"));
		headline.addColum(new Column("Erstellungsdatum"));
		headline.addColum(new Column("Bewerbungstext"));
		headline.addColum(new Column("Bewerbungsstatus"));
		
		result.addRow(headline);
		
		/**
		 * @param ausschreibungId
		 * @return Bewerbung-Objekt anhand der übergebenen ausschreibungID
		 */
		Vector<Bewerbung> bewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignAusschreibungId(ausschreibungId);
		
		for(Bewerbung b : bewerbungen){
			
			// Eine leere Zeile anlegen.
		      Row bewerbungRow = new Row();
		      
		   /**
		    * prüfen, ob der Ausschreibene eine Person, Team oder Unternehmen ist.
		    * Erste Spalte: Je nachdem wird der Name des Ausschreibenden gesetzt
		    * @param organisationseinheitId zum jeweiligen Bewerbungs-Objekt
		    * @return Organisationseinheit-Objekt anhand der übergebenen Id
		    */
		      
		      Organisationseinheit bewerber = projektmarktplatzverwaltung.getOrganisationseinheitById(b.getOrganisationseinheitId());
		     
		      
		      if(bewerber instanceof Person){
		    	  bewerbungRow.addColum(new Column(((Person) bewerber).getVorname() 
							+ " " + ((Person) bewerber).getNachname()));
				
				} else if(bewerber instanceof Team){
					bewerbungRow.addColum(new Column(((Team) bewerber).getName()));
				
				} else if(bewerber instanceof Unternehmen){
					bewerbungRow.addColum(new Column(((Unternehmen) bewerber).getName()));
				}
		      
		      //Zweite Spalte: Erstellungsdatum
		      	bewerbungRow.addColum(new Column(b.getErstellungsdatum().toString()));
		      	
		      // Dritte Spalte: Bewerbungstext hinzufügen
		      	bewerbungRow.addColum(new Column(b.getBewerbungstext()));
		      	
		      // Vierte Spalte: Bewerbungsstatus
		      	bewerbungRow.addColum(new Column(b.getStatus().toString()));
		   
		      	
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}
	
	/**
	 * @param Organisationseinheit o
	 * @return Alle Bewerbungen mit den dazugehörigen Auscchreibungen zu der übergebenen Organisationseinheit o
	 */
	@Override
	public AlleBewerbungenMitAusschreibungenReport createAlleBewerbungenMitAusschreibungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		
		
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		AlleBewerbungenMitAusschreibungenReport result = new AlleBewerbungenMitAusschreibungenReport();
		
		result.setTitel("Alle eigenen Bewerbungen mit den zugehörigen Ausschreibungen");
		
		result.setErstellungsdatum(new Date());
		

		
		Row headline = new Row();
		
		headline.addColum(new Column("Bewerbungstext"));
		headline.addColum(new Column("Erstellungsdatum"));
		headline.addColum(new Column("Bewerbungsstatus"));
		headline.addColum(new Column("Zugehörige Ausschreibung"));
		headline.addColum(new Column("Ausschreibender"));
		headline.addColum(new Column("Bewerbungsfrist"));
		headline.addColum(new Column("Ausschreibungstext"));
		
		result.addRow(headline);

		/**
		 * @param Organisationseinheit o
		 * @return Vector mit allen Bewerbungen zu der übergebenen Organisationseinheit
		 */
		Vector<Bewerbung> alleEigeneBewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(o);
		
		for(Bewerbung b : alleEigeneBewerbungen){
			
			/**
			 * @param id zu Ausschreibung-Objekt, welche aus dem jeweiligen Bewerbungs-Objekt gelesen wird
			 * @return Ausschreibung-Objekt
			 */
			Ausschreibung ausschreibung = projektmarktplatzverwaltung.getAusschreibungById(b.getAusschreibungId());
			
			/**
			 * @param id einer Person, die aus dem Ausschreibungs-Objekt gelesen wird
			 * @return Person-Objekt anhand der übergebenen id
			 */
			Person ausschreibender = projektmarktplatzverwaltung.getPersonById(ausschreibung.getAusschreibenderId());
			
			// Eine leere Zeile anlegen.
		      Row bewerbungRow = new Row();
		      
		    //Erste Zeile: Bewerbungstext hinzufügen  
		      bewerbungRow.addColum(new Column(b.getBewerbungstext()));
		      
		    //Zweite Zeile: Erstellungsdatum hinzufügen  
		      bewerbungRow.addColum(new Column(b.getErstellungsdatum().toString()));
		      
		    //Dritte Zeile: Bewerbungsstatus hinzufügen  
		      bewerbungRow.addColum(new Column(b.getStatus().toString()));
		      
		    // Vierte Zeile: Bezeichnung der Ausschreibung hinzufügen  
		      bewerbungRow.addColum(new Column(ausschreibung.getBezeichnung()));
		      
		    // Fünfte Zeile: Ausschreibender hinzufügen
		      bewerbungRow.addColum(new Column(ausschreibender.getVorname() + " " + ausschreibender.getNachname()));
		    // Sechste Zeile: Be ewerbungsfrist der Ausschreibung
		      bewerbungRow.addColum(new Column(ausschreibung.getBewerbungsfrist().toString()));
		      
		    // Siebte Zeile: Ausschreibungstext hinzufügen
		      bewerbungRow.addColum(new Column(ausschreibung.getAusschreibungstext()));
			
		      
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}

	/**
	 * @param Organisationseinheit o
	 * @return Report mit allen Bewerbungen auf eigene Ausschreibungen der übergebenen Organisationseinheit o
	 */
	public AlleBewerbungenAufEigeneAusschreibungenReport createAlleBewerbungenAufEigeneAusschreibungenReport(
			Organisationseinheit o) throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		AlleBewerbungenAufEigeneAusschreibungenReport result = new AlleBewerbungenAufEigeneAusschreibungenReport();
		
		result.setTitel("Alle Bewerbungen auf eigene Ausschreibungen");
		
		result.setErstellungsdatum(new Date());
		
		/**
		 * @return Vector mit allen Ausschreibungen
		 */
		Vector<Ausschreibung> alleAusschreibungen = projektmarktplatzverwaltung.getAllAusschreibungen();
		
		for(Ausschreibung a: alleAusschreibungen){
			
			if(a.getAusschreibenderId() == o.getId()){
		    /*
		       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
		       */
		      result.addSubReport(this.AlleBewerbungenAufEineAusschreibungDesUser(a.getId()));
			}
		}
		
		return result;
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return Alle Beteiligungen eines Users
	 */
	public AlleBeteiligungenEinesUsers alleBeteiligungenEinesUsers(int id){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		AlleBeteiligungenEinesUsers result = new AlleBeteiligungenEinesUsers();
		
		result.setTitel("Alle Beteiligungen des Users");
		
		result.setErstellungsdatum(new Date());
		
		Row headline = new Row();
		
		headline.addColum(new Column("Projekt"));
		headline.addColum(new Column("Startdatum"));
		headline.addColum(new Column("Enddatum"));
		headline.addColum(new Column("Umfang"));
		
		result.addRow(headline);

		/**
		 * @param Organisationseinheit-Objekt, welches man aus der übergebnen id erhählt
		 * @return Vector mit allen Beteiligungen der übergebenen Organisationseinheit
		 */
		Vector<Beteiligung> alleBeteiligungen = projektmarktplatzverwaltung.getBeteiligungByForeignOrganisationseinheit(projektmarktplatzverwaltung.getOrganisationseinheitById(id));
		
		for(Beteiligung b : alleBeteiligungen){
			
			/**
			 * @param id eines Projekt-Objekts, welches aus dem jeweiligen Beteiligungs-Objekt gelesen wird
			 * @return Projekt-Objekt, anhand der übergebnen id
			 */
			Projekt projekt = projektmarktplatzverwaltung.getProjektById(b.getProjektId());
			
			// Eine leere Zeile anlegen.
		      Row beteiligungRow = new Row();
		      
		    //Erste Zeile: Projektname hinzufügen  
		      beteiligungRow.addColum(new Column(projekt.getName()));
		      
		    //Zweite Zeile: Startdatum hinzufügen  
		      beteiligungRow.addColum(new Column(b.getStartDatum().toString()));
		      
		    //Dritte Zeile: Enddatum hinzufügen  
		      beteiligungRow.addColum(new Column(b.getEndDatum().toString()));
		      
		    // Vierte Zeile: Umfang hinzufügen  
		      beteiligungRow.addColum(new Column(Integer.toString(b.getUmfang())));
		     
 
		      result.addRow(beteiligungRow);
		}
		
		
		
		return result;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return Alle Bewerbungen eines Users
	 */
	public AlleBewerbungenEinesUsers alleBewerbungenEinesUsers(int id){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		AlleBewerbungenEinesUsers result = new AlleBewerbungenEinesUsers();
		
		result.setTitel("Alle Bewerbungen des Users");
		
		
		result.setErstellungsdatum(new Date());
		
		
		Row headline = new Row();		
		headline.addColum(new Column("Erstellungsdatum der Bewerbung"));
		headline.addColum(new Column("Bewerbungstext"));
		headline.addColum(new Column("Bewerbungsstatus"));
		headline.addColum(new Column("Auf Ausschreibung"));
		
		result.addRow(headline);
		
		/**
		 * @param Organisationseinheit-Objekt, welches man über die übergebene id erhählt
		 * @return Vector mit allen Bewerbungen der übergebenen Organisationseinheit
		 */
		Vector<Bewerbung> bewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(projektmarktplatzverwaltung.getOrganisationseinheitById(id));
				
		for(Bewerbung b : bewerbungen){
			
			/**
			 * @param id einer Ausschreibung, welches man anhand der jeweiligen Bewerung b ausliest
			 * @return Ausschreibung-Objekt, zu der übergebnen id
			 */
			Ausschreibung empfangendeAusschreibung = projektmarktplatzverwaltung.getAusschreibungById(b.getAusschreibungId());
			
			
			// Eine leere Zeile anlegen.
		      Row bewerbungRow = new Row();
		      		      
		      //Erste Spalte: Erstellungsdatum
		      	bewerbungRow.addColum(new Column(b.getErstellungsdatum().toString()));
		      	
		      // Zweite Spalte: Bewerbungstext hinzufügen
		      	bewerbungRow.addColum(new Column(b.getBewerbungstext()));
		      	
		      // Dritte Spalte: Bewerbungsstatus
		      	bewerbungRow.addColum(new Column(b.getStatus().toString()));
		      	
		      // Vierte Spalte: Auf Ausschreibung hinzufügen
		      	bewerbungRow.addColum(new Column(empfangendeAusschreibung.getBezeichnung())); 	
		   
		      	
		      result.addRow(bewerbungRow);
		}
		
		
		
		return result;		
	}
	
	/**
	 * @param id
	 * @return Report mit allen Projektverflectungen
	 */
	@Override
	public ProjektverflechtungenReport createProjektverflechtungenReport(int id)
			throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		ProjektverflechtungenReport result = new ProjektverflechtungenReport();
		
		result.setTitel("Projektverflechtungen des Users");
		
		result.setErstellungsdatum(new Date());
		
		/*
	       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
	       */
	      result.addSubReport(this.alleBeteiligungenEinesUsers(id));
	      result.addSubReport(this.alleBewerbungenEinesUsers(id));
		
		
		return result;
	}
	
	

	/**
	 * @return Alle Bewerbungen mit deren jeweiligen Status
	 */
	public FanIn fanInAnalyse() throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		FanIn result = new FanIn();
		
		result.setTitel("Anzahl der Bewerbungen");
		
		
		result.setErstellungsdatum(new Date());
		
		Row headline = new Row();
		headline.addColum(new Column("ID"));
		headline.addColum(new Column("Organisationseinheit"));
		headline.addColum(new Column("laufend"));
		headline.addColum(new Column("abgelehnt"));
		headline.addColum(new Column("angenommen"));
		
		result.addRow(headline);
		
		
		/**
		 * @return Vector mit allen Organisationseinheiten
		 */
		Vector<Organisationseinheit> alleOrganisationseinheiten = projektmarktplatzverwaltung.getAllOrganisationseinheiten();
		
		for (Organisationseinheit orga : alleOrganisationseinheiten) {
			
			Vector<Bewerbung> laufendeBewerbungen = new Vector<Bewerbung>();
			Vector<Bewerbung> abgelehnteBewerbungen = new Vector<Bewerbung>();
			Vector<Bewerbung> angenommeneBewerbungen = new Vector<Bewerbung>();
			
			/**
			 * Es werden alle Bewerbungen der gegebenen Organisationseinheit in einen Vector geschrieben
			 * @param Organisationseinheit-Objekt orga
			 * @return Vector mit allen Bewerbungen zu dem jeweils übergebenen Organisationseinheit-Objekts
			 */
			Vector<Bewerbung> alleBewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(orga);
			
			for(Bewerbung b: alleBewerbungen){
				

				
				if(b.getStatus().toString().equals("laufend")){
					laufendeBewerbungen.add(b);
				} else if(b.getStatus().toString().equals("abgelehnt")){
					abgelehnteBewerbungen.add(b);
				} else if(b.getStatus().toString().equals("angenommen")){
					angenommeneBewerbungen.add(b);
				}
			}
			
			Row anzahlRow = new Row();
			
			anzahlRow.addColum(new Column(String.valueOf(orga.getId())));
			
			if(orga instanceof Person){
				anzahlRow.addColum(new Column(((Person)orga).getVorname() + " "+((Person)orga).getNachname()));
			} else if(orga instanceof Team){
				anzahlRow.addColum(new Column(((Team)orga).getName()));
			} else if(orga instanceof Unternehmen){
				anzahlRow.addColum(new Column(((Unternehmen)orga).getName()));		
			}
			
			anzahlRow.addColum(new Column(String.valueOf(laufendeBewerbungen.size())));
			anzahlRow.addColum(new Column(String.valueOf(abgelehnteBewerbungen.size())));
			anzahlRow.addColum(new Column(String.valueOf(angenommeneBewerbungen.size())));
			
			result.addRow(anzahlRow);
			
		}
		
		return result;	
	}
	
	
	/**
	 * 
	 * @return Alle Ausschreibungen mit deren jeweiligen Status
	 */
	public FanOut fanOutAnalyse(){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		FanOut result = new FanOut();
		
		result.setTitel("Anzahl der Ausschreibungen");
		
		
		
		result.setErstellungsdatum(new Date());
		
		Row headline = new Row();
		headline.addColum(new Column("ID"));
		headline.addColum(new Column("Organisationseinheit"));
		headline.addColum(new Column("besetzt"));
		headline.addColum(new Column("abgebrochen"));
		headline.addColum(new Column("laufend"));
		
		result.addRow(headline);
		
		/**
		 * @return Vector mit allen Organisationseinheiten
		 */
		Vector<Organisationseinheit> alleOrganisationseinheiten = projektmarktplatzverwaltung.getAllOrganisationseinheiten();
		
	
		for(Organisationseinheit orga : alleOrganisationseinheiten) {
			
			Vector<Ausschreibung> besetzteAusschreibungen = new Vector<Ausschreibung>();
			Vector<Ausschreibung> abgebrocheneAusschreibungen = new Vector<Ausschreibung>();
			Vector<Ausschreibung> laufendeAusschreibungen = new Vector<Ausschreibung>();
			
			/**
			 * @param Organisationseinheit-Objekt orga
			 * @return Ausschreibung-Objekt zu dem jeweils übergebenen Organisationseinheit-Objekt
			 */
			Vector<Ausschreibung> alleAusschreibungen = projektmarktplatzverwaltung.getAusschreibungByForeignOrganisationseinheit(orga);
				

			for(Ausschreibung a: alleAusschreibungen){
				
				if(a.getStatus().toString().equals("besetzt")){
					besetzteAusschreibungen.add(a);
				} else if(a.getStatus().toString().equals("abgebrochen")){
					abgebrocheneAusschreibungen.add(a);
				} else if(a.getStatus().toString().equals("laufend")){
					laufendeAusschreibungen.add(a);
				}
				
			}
			
			Row anzahlRow = new Row();
			
			anzahlRow.addColum(new Column(String.valueOf(orga.getId())));
			
			if(orga instanceof Person){
				anzahlRow.addColum(new Column(((Person)orga).getVorname() + " "+((Person)orga).getNachname()));
			} else if(orga instanceof Team){
				anzahlRow.addColum(new Column(((Team)orga).getName()));
			} else if(orga instanceof Unternehmen){
				anzahlRow.addColum(new Column(((Unternehmen)orga).getName()));		
			}
			
			anzahlRow.addColum(new Column(String.valueOf(besetzteAusschreibungen.size())));
			anzahlRow.addColum(new Column(String.valueOf(abgebrocheneAusschreibungen.size())));
			anzahlRow.addColum(new Column(String.valueOf(laufendeAusschreibungen.size())));		
			
			result.addRow(anzahlRow);
			
		}	
		return result;		
	}
	
	
	/**
	 * @return FanInFanOut Report
	 */
	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		FanInFanOutReport result = new FanInFanOutReport();
		
		result.setTitel("Fan-In/ Fan-Out-Analyse");
		
		result.setErstellungsdatum(new Date());
		
			
			result.addSubReport(this.fanInAnalyse());
			result.addSubReport(this.fanOutAnalyse());
		
		return result;
	}

	
	@Override
	public void setPerson() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * @param Organisationseinheit o
	 * @return Vector/Report mit allen Ausschreibungen deren Partnerprofil zu dem der übergebenen Organisationseinheit passen.
	 */
	@Override
	public AlleAusschreibungenZuPartnerprofilReport getAusschreibungByMatchingPartnerprofilOfOrganisationseinheitReport(Organisationseinheit o){
		
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
			
			}
			
			AlleAusschreibungenZuPartnerprofilReport result = new AlleAusschreibungenZuPartnerprofilReport();
			
			result.setTitel("");
			result.setErstellungsdatum(new Date());
			
			
			Row headline = new Row();
			headline.addColum(new Column("Bezeichnung"));
			headline.addColum(new Column("Ausschreibender"));
			headline.addColum(new Column("Ausschreibungstext"));
			headline.addColum(new Column("Bewerbungsfrist"));
			
			
			result.addRow(headline);
			

		Vector<Ausschreibung> matchingAusschreibungen = new Vector<Ausschreibung>();
		/**
		 * @param Organisationseinheit o
		 * @return Partnerprofil, zu der übergebnen Organisationseinheit
		 */
		Partnerprofil partnerprofil = projektmarktplatzverwaltung.getPartnerprofilByForeignOrganisationseinheit(o);
		/**
		 * @param partnerprofil
		 * @return Vector mit allen Eigenschaften zum übergebenen Partnerprofil
		 */
		Vector<Eigenschaft> eigenschaften = projektmarktplatzverwaltung.getEigenschaftByForeignPartnerprofil(partnerprofil);
		/**
		 * @return Vector mit allen Ausschreibungen
		 */
		Vector<Ausschreibung> allAusschreibungen = projektmarktplatzverwaltung.getAllAusschreibungen();
		System.out.println("Menge aller Ausschreibungen: "+allAusschreibungen.size());
		
		for(Ausschreibung ausschreibung : allAusschreibungen){
			System.out.println("");
			/**
			 * @param Ausschreibung-Objekt ausschreibung
			 * @return Partnerprofil zu der jeweils übergebnen Ausschreibung
			 */
			Partnerprofil partnerprofilOfAusschreibung = projektmarktplatzverwaltung.getPartnerProfilByForeignAusschreibung(ausschreibung);
			System.out.println("Hole Partnerprofil mit der Id: "+partnerprofilOfAusschreibung.getId());
			/**
			 * @param id, welche aus dem partnerprofilOfAusschreibung-Objekt gelesen wird
			 * @return Vector mit allen Eigenschaften zu dem übergebnen Partnerprofil
			 */
			Vector<Eigenschaft> eigenschaftenOfAusschreibung = projektmarktplatzverwaltung.getEigenschaftenByForeignPartnerprofilId(partnerprofilOfAusschreibung.getId());
			System.out.println("Menge der Eigenschaften zu diesem Partnerprofil: "+eigenschaftenOfAusschreibung.size());
			if(eigenschaften.size()==eigenschaftenOfAusschreibung.size()){
				System.out.println("Menge der Eigenschaften passt");
				int matchCounter = 0;
				for(int i=0;i<eigenschaften.size();i++){
					for(Eigenschaft fremdeEigenschaft : eigenschaftenOfAusschreibung){
						if(eigenschaften.get(i).getName().equals(fremdeEigenschaft.getName()) && eigenschaften.get(i).getWert().equals(fremdeEigenschaft.getWert())){
							matchCounter++;
						}
					}
				}
				/**
				 * @param id eines Projekts, welches aus dem jeweiligen auschreibungs-Objekt gelesen wird
				 * @return Projekt-Objekt, anhand de rübergebenen id
				 */
				Projekt projektOfAusschreibung = projektmarktplatzverwaltung.getProjektById(ausschreibung.getProjektId());
				
				/**
				 * @param id eines Person-Objekts, welches aus dem übergebenen Projekt-Objekt gelesen wird
				 * @return Person-Objekt
				 */
				Person projektleiterOfProjekt = this.getPersonById(projektOfAusschreibung.getProjektleiterId());
				if(matchCounter==eigenschaften.size()){
					if(projektleiterOfProjekt.getPartnerprofilId()!=partnerprofil.getId()){
						System.out.println("Partnerprofil passt, füge zur Menge hinzu!");
						matchingAusschreibungen.add(ausschreibung);
					}
				}
			}
		}
	
		for (Ausschreibung ausschreibung : matchingAusschreibungen) {
			
			 Row ausschreibungRow = new Row();
			 
			 ausschreibungRow.addColum(new Column(ausschreibung.getBezeichnung()));
			 ausschreibungRow.addColum(new Column(projektmarktplatzverwaltung.getPersonById(ausschreibung.getAusschreibenderId()).getVorname() + " " 
			 + projektmarktplatzverwaltung.getPersonById(ausschreibung.getAusschreibenderId()).getNachname()));
			 ausschreibungRow.addColum(new Column(ausschreibung.getAusschreibungstext()));
			 ausschreibungRow.addColum(new Column(ausschreibung.getBewerbungsfrist().toString()));
			 
			 result.addRow(ausschreibungRow);
			 
		}
		
		
		return result;
	}
	
}
