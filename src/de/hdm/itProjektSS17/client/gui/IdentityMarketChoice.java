package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;

public class IdentityMarketChoice extends FlexTable{
	

	//Zwei Listboxen zur Auswahl von Identität und Marktplatz und ein Formatierungs-Widget
	private ListBox ownOrgUnits = new ListBox();
	private ListBox ownProjectMarkets = new ListBox();
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	
	//Objekt zum Zugriff auf die Servermethoden
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	//Objekte zum Speichern der Identitätsinformationen einer Person und zur Speicherung der Projektmarktplätze,
	//welcher eine Person beigetreten ist.	
	private Person person;
	private Team team;
	private Unternehmen unternehmen;
	private Vector<Projektmarktplatz> projektmarktplaetze;
	
	//Speicherung des im Konstruktor übergebenen Navigation-Objekts um auf Member der Seitennavigation zugreifen zu können
	private Navigation navigation;
	
	//Prüfvariable, ob der Benutzer bereits Projektmarktplätze gewählt hat
	private boolean isMarktplatzSet = false;
	
	/**
	 * Ein Objekt dieser Klasse stellt ein Menü zur Auswahl von Identität und Projektmarktplatz zur Verfügung
	 * @param id
	 * @param navigation
	 */
	//
	public IdentityMarketChoice (int id, final Navigation navigation){
		this.navigation=navigation;
		this.setWidget(1, 0, new Label("Nutze Identität von: "));		
		this.setWidget(1, 1, ownOrgUnits);
		this.setStylePrimaryName("IdentityPanel");
		this.setWidget(2, 0, new Label("Projektmarktplatz: "));		
		this.setWidget(2, 1, ownProjectMarkets);
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		ownProjectMarkets.setWidth("250px");
		ownOrgUnits.setWidth("250px");
		
		//Aufruf eines RPC um die Identitätsleiste und Projektmarktplatzleiste mit den entsprechenden
		//Daten des eben eingeloggten Users zu befüllen.
		projektmarktplatzVerwaltung.getPersonById(id, new getUser());
		
		//Zwei ChangeHandler um die aktuelle Seite neu zu laden, sollte der Benutzer einen neuen Eintrag innerhalb der
		//Listboxen wählen
		ownOrgUnits.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				navigation.reload();
			}
		});
		
		ownProjectMarkets.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				navigation.reload();
			}
		});
		
	}
	
	/**
	 * Gibt den gewählten Index der Identitäts-Listbox zurück
	 */
	public int getSelectedIndex(){
		
		int selectedIdentity = ownOrgUnits.getSelectedIndex();
		
		return selectedIdentity;
	}
	
	/**
	 * Gibt die ID der gewählten Identität (Person, Team oder Unternehmen) zurück
	 */
	public int getSelectedIdentityId(){		
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
	 * Gibt die ID des ausgewählten Projektmarktplatzes zurück.
	 */
	public int getSelectedProjectMarketplaceId(){
		for(Projektmarktplatz p : projektmarktplaetze){
			if(p.getBezeichnung()==ownProjectMarkets.getSelectedItemText()){
				return p.getId();
			}
		}
		return 0;
	}
	
	/**
	 * Gibt den eingeloggten User zurück
	 */
	public Person getUser(){
		return person;
	}
	
	/**
	 * Gibt das Team des eingeloggten Users zurück
	 */
	public Team getTeamOfUser(){
		return team;
	}
	
	/**
	 * Gibt das Unternehmen des eingeloggten Users zurück
	 */
	public Unternehmen getUnternehmenOfUser(){
		return unternehmen;
	}
	
	/**
	 * Gibt die eigene Projektmarktplätze-ListBox zurück
	 */
	public ListBox getOwnProjectMarkets(){
		return ownProjectMarkets;
	}
	
	/**
	 * Gibt die eigene Identitäts-ListBox zurück
	 */
	public ListBox getOwnOrgUnits(){
		return ownOrgUnits;
	}
	
	/**
	 * Deaktiviert die Identitätslistbox
	 */
	public void deactivateOrgUnits(){
		ownOrgUnits.setEnabled(false);
	}
	
	/**
	 * Deaktiviert die Projektmarktplätze Listbox
	 */
	public void deactivateProjectMarkets(){
		ownProjectMarkets.setEnabled(false);
	}
	
	/**
	 * Aktiviert die Identitätslistbox
	 */
	public void activateOrgUnits(){
		ownOrgUnits.setEnabled(true);
	}
	
	/**
	 * Aktiviert die Projektmarktplätze Listbox
	 */
	public void activateProjectMarkets(){
		ownProjectMarkets.setEnabled(true);
	}
	
	/**
	 * Setzt den Index der Identitätslistbox auf 0 um 
	 * zu erzwingen, dass der eingeloggte User gewählt ist
	 */
	public void setOwnOrgUnitToZero(){
		ownOrgUnits.setSelectedIndex(0);
	}
	
	/**
	 * Lädt die Inhalte der beiden Listboxen neu
	 */
	public void reinitialize(){
		projektmarktplatzVerwaltung.getPersonById(person.getId(), new getUser());
	}
	
	/**
	 * Gibt true zurück, wenn mindestens eine Beteiligung des Users an einem Projektmarktplatz besteht.
	 * Gibt false zurück, wenn keine Beteiligungen des Users bestehen.
	 */
	public boolean getisMarktplatzSet(){
		return isMarktplatzSet;
	}
	
	
	/**
	 * Private Klasse um den aktuell eingeloggten User aus der Datenbank zu erhalten und um 
	 * mit ihm verknüpfte Informationen (Team, Unternehmen oder Beteiligungen an Projektmarktplätzen)
	 * abzufragen.
	 */
	private class getUser implements AsyncCallback<Person>{
		

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("User konnte nicht für die Identitätsleiste geladen werden");
			Window.alert("Fehler ist: " + caught.toString());
			
			
		}

		@Override
		public void onSuccess(Person result) {
			//Leeren alter Einträge der beiden Listboxen
			ownOrgUnits.clear();
			ownProjectMarkets.clear();
			//Speichern des aktuell eingeloggten Users
			person=result;
			Integer idOfPerson=result.getId();
			
			//Hinzufügen der Person-Identität zur Identitätsleiste
			ownOrgUnits.addItem("Person: "+result.getVorname()+" "+result.getNachname(), idOfPerson.toString());
			
			//Prüfen ob der User einem Team oder Unternehmen zugeordnet ist und Aufrufen entsprechender RPCs
			if(person.getTeamId()!=null){
				projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new getTeam());
			}else if(person.getUnternehmenId()!=null){
				projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new getUnternehmen());
			}
			//RPC um die Projektmarktplätze eines Users zur Identitätsleiste hinzufügen zu können.
			projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(result, new getProjektmarktplatz());
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
				projektmarktplatzVerwaltung.getUnternehmenById(person.getUnternehmenId(), new getUnternehmen());
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
	
	/**
	 * Private Klasse um die Projektmarktplatz-Beteiligungen eines Users als Auswahl in die Projektmarktplatz-Listbox schreiben zu können
	 */
	private class getProjektmarktplatz implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projektmarktplätze des Users konnten nicht für die Identitätsleiste geladen werden");			
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			if(result!=null){
				//Wenn das Resultset leer ist, so wird der User gezwungen mindestens einen Projektmarktplatz auswzuwählen
				if(result.isEmpty()){
					ownProjectMarkets.addItem("Bitte einen Marktplatz hinzufügen!");
					navigation.getProjektmarktplaetzeButton().click();
					RootPanel.get("Navigator").clear();
				//Wenn das Resultset Einträge enthält, werden diese zur Projektmarktplatz-Listbox hinzugefügt
				//und in den Projektmarktplatz Vector des Users geschrieben
				}else{		
					isMarktplatzSet=true;
					for(Projektmarktplatz p : result){
					ownProjectMarkets.addItem(p.getBezeichnung());
					}
					projektmarktplaetze = result;
					RootPanel.get("Navigator").add(navigation);
				}
			}
		}
	}
}
