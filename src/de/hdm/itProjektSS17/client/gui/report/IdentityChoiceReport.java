package de.hdm.itProjektSS17.client.gui.report;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class IdentityChoiceReport extends FlexTable{

	//Eine Listbox welche vom User genutzt wird um zwischen seinen Identitäten umzuschalten
	private static ListBox ownOrgUnits = new ListBox();
	
	//Formatierungsobjekt um die Listbox an die richtige STelle zu rücken
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	
	//Objekt zum Zugriff auf die Servermethoden
	private static ReportGeneratorAsync reportgenerator = ClientsideSettings.getReportGenerator();
	
	//Objekte zum Speichern der Identitätsinformationen einer Person
	private static Person person;
	private static Team team;
	private static Unternehmen unternehmen;
	
	//Speicherung des im Konstruktor übergebenen Navigation-Objekts um auf Member der Seitennavigation zugreifen zu können
	private NavigationReport navigationReport;
	
	/**
	 * Ein Objekt dieser Klasse stellt ein Menü zur Auswahl der Identitäten eines Users zur Verfügung
	 * @param id
	 * @param navigation
	 */
	public IdentityChoiceReport(int id, final NavigationReport navigationReport){
		this.navigationReport=navigationReport;
		this.setWidget(1, 0, new Label("Nutze Identität von: "));		
		this.setWidget(1, 1, ownOrgUnits);
		this.setStylePrimaryName("IdentityPanel");
		
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		ownOrgUnits.setWidth("250px");
		//Aufruf eines RPC um die Identitätsleiste mit den entsprechenden
		//Daten des eben eingeloggten Users zu befüllen.
		reportgenerator.getPersonById(id, new getUser());
		
		//ChangeHandler um die aktuelle Seite neu zu laden, sollte der Benutzer einen neuen Eintrag innerhalb der
		//Listboxen wählen
		
		ownOrgUnits.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				navigationReport.reload();
			}
		});
		
	}
	
	/**
	 * Gibt die ID der gewählten Identität (Person, Team oder Unternehmen) zurück
	 */
		public static int getSelectedIdentityId(){		
			if(person.getTeamId()!=null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person.getId();
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return team.getId();
				}else if(ownOrgUnits.getSelectedIndex()==2){
					return unternehmen.getId();
				}
			}else if(person.getTeamId()==null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person.getId();
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return unternehmen.getId();
				}

			}
			return 0;
		}
	
		/**
		 * Gibt die gewählte Identität als Objekt zurück
		 */
		public Organisationseinheit getSelectedIdentityAsObject(){
			if(person.getTeamId()!=null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person;
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return team;
				}else if(ownOrgUnits.getSelectedIndex()==2){
					return unternehmen;
				}
			}else if(person.getTeamId()==null){
				if(ownOrgUnits.getSelectedIndex()==0){
					return person;
				}else if(ownOrgUnits.getSelectedIndex()==1){
					return unternehmen;
				}

			}
			return null;
		}
		
		
	/**
	 * Private Klasse um den aktuell eingeloggten User aus der Datenbank zu erhalten und um 
	 * mit ihm verknüpfte Informationen (Team, Unternehmen oder Beteiligungen an Projektmarktplätzen)
	 * abzufragen.
	 */	
	private class getUser implements AsyncCallback<Person>{

		public void onFailure(Throwable caught) {
			Window.alert("User konnte nicht für die Identitätsleiste geladen werden");		
		}

		@Override
		public void onSuccess(Person result) {
			//Leeren alter Einträge
			ownOrgUnits.clear();
			//Speichern des aktuell eingeloggten Users
			person=result;
			Integer idOfPerson=result.getId();
			//Hinzufügen der Person-Identität zur Identitätsleiste
			ownOrgUnits.addItem("Person: "+result.getVorname()+" "+result.getNachname(), idOfPerson.toString());
			
			//Prüfen ob der User einem Team oder Unternehmen zugeordnet ist und Aufrufen entsprechender RPCs
			if(person.getTeamId()!=null){
				reportgenerator.getTeamById(result.getTeamId(), new getTeam());
			}else if(person.getUnternehmenId()!=null){
				reportgenerator.getUnternehmenById(result.getUnternehmenId(), new getUnternehmen());
			}
		}			
	}
		
	/**
	 * Private Klasse um das Team eines Users als Auswahl in die Identitätsleiste schreiben zu können
	 */
	private class getTeam implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Team des Users konnte nicht f�r die Identit�tsleiste geladen werden");			
		}

		@Override
		public void onSuccess(Team result) {
			//Hinzufügen des Teams als Auswahl zur Identitsleiste
			Integer idOfTeam=result.getId();
			ownOrgUnits.addItem("Team: "+result.getName(),idOfTeam.toString());	
			//Speichern des Teams
			team=result;
			//Wenn der Benutzer nun auch noch einem Unternehmen zugewiesen ist, wird der entsprehende RPC aufgerufen
			if(person.getUnternehmenId()!=null){
				reportgenerator.getUnternehmenById(person.getUnternehmenId(), new getUnternehmen());
			}	
		}	
	}
	
	/**
	 * Private Klasse um das Unternehmen eines Users als Auswahl in die Identitätsleiste schreiben zu können
	 */
	private class getUnternehmen implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Unternehmen des Users konnte nicht für die Identitätsleiste geladen werden");
			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			//Hinzufügen des Unternehmens als Auswahl zur Identitätsleiste

			Integer idOfUnternehmen=result.getId();
			ownOrgUnits.addItem("Unternehmen: "+result.getName(),idOfUnternehmen.toString());
			//Speichern des Unternehmens
			unternehmen=result;
			
		}
		
	}
		
	
	
}
