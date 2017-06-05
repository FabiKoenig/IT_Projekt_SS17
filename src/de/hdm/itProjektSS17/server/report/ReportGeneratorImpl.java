package de.hdm.itProjektSS17.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ReportGenerator;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
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


public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator{

	
	/**
	 * Der ReportGenerator benötigt Zugriff auf die ProjektmarktplatzVerwaltung,
	 * da dort wichtige Methoden für die Koexistenz von Datenobjekten enthalten sind.
	 */
	private ProjektmarktplatzVerwaltung projektmarktplatzverwaltung = null;
	
	/**
	 * TODO
	 * @throws IllegalArgumentException
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}
	
	
	public void init() throws IllegalArgumentException{
		/**
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
	
	@Override
	public AlleAusschreibungenZuPartnerprofilReport createAlleAusschreibungeZuPartnerprofilReport(Partnerprofil p)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

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
		
		Vector<Ausschreibung> alleAusschreibungen = this.projektmarktplatzverwaltung.getAllAusschreibungen();
		
		for(Ausschreibung a : alleAusschreibungen){
			// Eine leere Zeile anlegen.
		      Row ausschreibungRow = new Row();
		      
		   /**
		    * prüfen, ob der Ausschreibene eine Person, Team oder Unternehmen ist.
		    * Erste Spalte: Je nachdem wird der Name des Ausschreibenden gesetzt
		    */
		      Organisationseinheit ausschreibender = projektmarktplatzverwaltung.getOrganisationseinheitById(a.getAusschreibenderId());
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
	
	

	
	
	public AlleBewerbungenAufEineAusschreibungDesUsers AlleBewerbungenAufEineAusschreibungDesUser(int ausschreibungId){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		Ausschreibung ausschreibung = projektmarktplatzverwaltung.getAusschreibungById(ausschreibungId);
		
		
		AlleBewerbungenAufEineAusschreibungDesUsers result = new AlleBewerbungenAufEineAusschreibungDesUsers();
		
		result.setTitel("Alle Bewerbungen auf die Ausschreibung " + ausschreibung.getBezeichnung() 
		+ ", ID: " + ausschreibung.getId());
		
		
		result.setErstellungsdatum(new Date());
		
		
		Row headline = new Row();
		headline.addColum(new Column("Bewerber"));
		headline.addColum(new Column("Erstellungsdatum"));
		headline.addColum(new Column("Bewerbungstext"));
		headline.addColum(new Column("Bewerbungsstatus"));
		
		result.addRow(headline);
		
		
		Vector<Bewerbung> bewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignAusschreibungId(ausschreibungId);
		
		for(Bewerbung b : bewerbungen){
			
			// Eine leere Zeile anlegen.
		      Row bewerbungRow = new Row();
		      
		   /**
		    * prüfen, ob der Ausschreibene eine Person, Team oder Unternehmen ist.
		    * Erste Spalte: Je nachdem wird der Name des Ausschreibenden gesetzt
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

		Vector<Bewerbung> alleEigeneBewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(o);
		
		for(Bewerbung b : alleEigeneBewerbungen){
			
			Ausschreibung ausschreibung = projektmarktplatzverwaltung.getAusschreibungById(b.getAusschreibungId());
			
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
		      bewerbungRow.addColum(new Column(projektmarktplatzverwaltung.
		    		  getPersonById(ausschreibung.getAusschreibenderId()).getVorname() + " " +
		    		  projektmarktplatzverwaltung.getPersonById(ausschreibung.getAusschreibenderId()).getNachname()));
		    // Sechste Zeile: Be ewerbungsfrist der Ausschreibung
		      bewerbungRow.addColum(new Column(ausschreibung.getBewerbungsfrist().toString()));
		      
		    // Siebte Zeile: Ausschreibungstext hinzufügen
		      bewerbungRow.addColum(new Column(ausschreibung.getAusschreibungstext()));
			
		      
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}

	public AlleBewerbungenAufEigeneAusschreibungenReport createAlleBewerbungenAufEigeneAusschreibungenReport(
			Organisationseinheit o) throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		AlleBewerbungenAufEigeneAusschreibungenReport result = new AlleBewerbungenAufEigeneAusschreibungenReport();
		
		result.setTitel("Alle Bewerbungen auf eigene Ausschreibungen");
		
		result.setErstellungsdatum(new Date());
		
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
	
	
	
	public AlleBeteiligungenEinesUsers alleBeteiligungenEinesUsers(Organisationseinheit o){
		
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

		Vector<Beteiligung> alleBeteiligungen = projektmarktplatzverwaltung.getBeteiligungByForeignOrganisationseinheit(o);
		
		for(Beteiligung b : alleBeteiligungen){
			
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
	
	
	public AlleBewerbungenEinesUsers alleBewerbungenEinesUsers(Organisationseinheit o){
		
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
		
		
		Vector<Bewerbung> bewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(o);
				
		for(Bewerbung b : bewerbungen){
			
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
	

	@Override
	public ProjektverflechtungenReport createProjektverflechtungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		ProjektverflechtungenReport result = new ProjektverflechtungenReport();
		
		result.setTitel("Projektverfplechtungen des Users");
		
		result.setErstellungsdatum(new Date());
		
		/*
	       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
	       */
	      result.addSubReport(this.alleBeteiligungenEinesUsers(o));
	      result.addSubReport(this.alleBewerbungenEinesUsers(o));
		
		
		return result;
	}
	
	

	public FanIn fanInAnalyse(Organisationseinheit o) throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		FanIn result = new FanIn();
		
		
		 if(o instanceof Person){
			 
			 result.setTitel("Anzahl der Bewerbungen von der Person " + ((Person) o).getVorname() + " " 
			 + ((Person) o).getNachname() + ", ID: " + o.getId());
			
			} else if(o instanceof Team){
				
			result.setTitel("Anzahl der Bewerbungen von dem Team " + ((Team) o).getName() + ", ID: "+ o.getId());

			} else if(o instanceof Unternehmen){
				
			result.setTitel("Anzahl der Bewerbungen von dem Unternehmen "+ ((Unternehmen) o).getName()+ ", ID: " +o.getId());	
			}
		
		
		result.setErstellungsdatum(new Date());
		
		Row headline = new Row();		
		headline.addColum(new Column("laufend"));
		headline.addColum(new Column("abgelehnt"));
		headline.addColum(new Column("angenommen"));
		
		result.addRow(headline);
		
		//Es werden alle Bewerbungen der gegebenen Organisationseinheit in einen Vector geschrieben
		Vector<Bewerbung> alleBewerbungen = projektmarktplatzverwaltung.getBewerbungByForeignOrganisationseinheit(o);
		
		
		Vector<Bewerbung> laufendeBewerbungen = new Vector<Bewerbung>();
		Vector<Bewerbung> abgelehnteBewerbungen = new Vector<Bewerbung>();
		Vector<Bewerbung> angenommeneBewerbungen = new Vector<Bewerbung>();
		
		for(Bewerbung b: alleBewerbungen){
			

			
			if(b.getStatus().equals("laufend")){
				laufendeBewerbungen.add(b);
			} else if(b.getStatus().equals("abgelehnt")){
				abgelehnteBewerbungen.add(b);
			} else if(b.getStatus().equals("angenommen")){
				angenommeneBewerbungen.add(b);
			}
		}
		
		Row anzahlRow = new Row();
		anzahlRow.addColum(new Column(String.valueOf(laufendeBewerbungen.size())));
		anzahlRow.addColum(new Column(String.valueOf(abgelehnteBewerbungen.size())));
		anzahlRow.addColum(new Column(String.valueOf(angenommeneBewerbungen.size())));
		
		return result;
		
	}
	
	public FanOut fanOutAnalyse(Organisationseinheit o){
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		
		FanOut result = new FanOut();
		
		
		 if(o instanceof Person){
	 
			 result.setTitel("Anzahl der Ausschreibungen von der Person " + ((Person) o).getVorname() + " " 
			 + ((Person) o).getNachname() + ", ID: " + o.getId());
			
			} else if(o instanceof Team){
				
			result.setTitel("Anzahl der Ausschreibungen von dem Team " + ((Team) o).getName() + ", ID: "+ o.getId());

			} else if(o instanceof Unternehmen){
				
			result.setTitel("Anzahl der Ausschreibungen von dem Unternehmen "+ ((Unternehmen) o).getName()+ ", ID: " +o.getId());	
			}
		
		
		
		result.setErstellungsdatum(new Date());
		
		Row headline = new Row();		
		headline.addColum(new Column("besetzt"));
		headline.addColum(new Column("abgebrochen"));
		headline.addColum(new Column("laufend"));
		
		result.addRow(headline);
		
		//Es werden alle Bewerbungen der gegebenen Organisationseinheit in einen Vector geschrieben
		Vector<Ausschreibung> alleAusschreibungen = projektmarktplatzverwaltung.getAusschreibungByForeignOrganisationseinheit(o);
		
		
		Vector<Ausschreibung> besetzteAusschreibungen = new Vector<Ausschreibung>();
		Vector<Ausschreibung> abgebrocheneAusschreibungen = new Vector<Ausschreibung>();
		Vector<Ausschreibung> laufendeAusschreibungen = new Vector<Ausschreibung>();
		
		for(Ausschreibung a: alleAusschreibungen){
			

			
			if(a.getStatus().equals("besetzt")){
				besetzteAusschreibungen.add(a);
			} else if(a.getStatus().equals("abgebrochen")){
				abgebrocheneAusschreibungen.add(a);
			} else if(a.getStatus().equals("laufend")){
				laufendeAusschreibungen.add(a);
			}
		}
		
		Row anzahlRow = new Row();
		anzahlRow.addColum(new Column(String.valueOf(besetzteAusschreibungen.size())));
		anzahlRow.addColum(new Column(String.valueOf(abgebrocheneAusschreibungen.size())));
		anzahlRow.addColum(new Column(String.valueOf(laufendeAusschreibungen.size())));		
		
		return result;		
	}
	
	
	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		
		if(this.getProjektmarktplatzVerwaltung()== null){
			return null;
		}
		
		FanInFanOutReport result = new FanInFanOutReport();
		
		result.setTitel("Fan-In/ Fan-Out-Analyse");
		
		result.setErstellungsdatum(new Date());
		
		Vector<Organisationseinheit> alleOrganisationseinheiten = projektmarktplatzverwaltung.getAllOrganisationseinheiten();
		
		for(Organisationseinheit organisationseinheit : alleOrganisationseinheiten){
			
			result.addSubReport(this.fanInAnalyse(organisationseinheit));
			result.addSubReport(this.fanOutAnalyse(organisationseinheit));
		}
		
		return result;
	}


	@Override
	public void setPerson() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
//	public class AlleBewerbungenAufEineAusschreibungDesUsers extends SimpleReport{
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}
	
//	public class AlleBeteiligungenEinesUsers extends SimpleReport{
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}

//	public class AlleBewerbungenEinesUsers extends SimpleReport{
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}
	
//	public class FanIn extends SimpleReport {
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}
//	
//	public class FanOut extends SimpleReport {
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}
	
	
}
