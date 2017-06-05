package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.gargoylesoftware.htmlunit.protocol.data.Handler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;

public class IdentityMarketChoice extends FlexTable{
	
	private static int currentLogin = 7;
	private static IdentityMarketChoice navigation=null;
	private static ListBox ownOrgUnits = new ListBox();
	private static ListBox ownProjectMarkets = new ListBox();
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	private static ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static Person person;
	private static Team team;
	private static Unternehmen unternehmen;
	private static Projektmarktplatz projektmarktplatz;
	private static Vector<Projektmarktplatz> projektmarktplaetze;
	
	
	private IdentityMarketChoice (int id){
		
		this.setWidget(1, 0, new Label("Nutze Identität von: "));		
		this.setWidget(1, 1, ownOrgUnits);
		this.setStylePrimaryName("IdentityPanel");
		this.setWidget(2, 0, new Label("Projektmarktplatz: "));		
		this.setWidget(2, 1, ownProjectMarkets);
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		ownProjectMarkets.setWidth("250px");
		ownOrgUnits.setWidth("250px");
		projektmarktplatzVerwaltung.getPersonById(id, new getUser());
		
		ownOrgUnits.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				Navigation.reload();
			}
		});
		
		ownProjectMarkets.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				Navigation.reload();
			}
		});
		
	}
	
	public static IdentityMarketChoice getNavigation(){
		//Falls bis jetzt noch keine PMV Instanz bestand
		if (navigation == null){
			navigation = new IdentityMarketChoice(currentLogin);
		}
		return navigation;
	}
	
	public static int getSelectedIndex(){
		
		int selectedIdentity = ownOrgUnits.getSelectedIndex();
		
		return selectedIdentity;
	}
	
	//Gibt die Id einer Person, eines Teams oder eines Unternehmens zurück
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
	
	public static Organisationseinheit getSelectedIdentityAsObject(){
		int selectedIdentity = ownOrgUnits.getSelectedIndex();
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
	
	public static int getSelectedProjectMarketplaceId(){
		for(Projektmarktplatz p : projektmarktplaetze){
			if(p.getBezeichnung()==ownProjectMarkets.getSelectedItemText()){
				return p.getId();
			}
		}
		return 0;
	}
	
	public static Person getUser(){
		return person;
	}
	
	public static ListBox getOwnOrgUnits(){
		return ownOrgUnits;
	}
	
	public static void deactivateOrgUnits(){
		ownOrgUnits.setEnabled(false);
	}
	
	public static void deactivateProjectMarkets(){
		ownProjectMarkets.setEnabled(false);
	}
	
	public static void activateOrgUnits(){
		ownOrgUnits.setEnabled(true);
	}
	
	public static void activateProjectMarkets(){
		ownProjectMarkets.setEnabled(true);
	}
	
	public static void setOwnOrgUnitToZero(){
		ownOrgUnits.setSelectedIndex(0);
	}
	
	public void reinitialize(){
		projektmarktplatzVerwaltung.getPersonById(currentLogin, new getUser());
	}
	
	
	private class getUser implements AsyncCallback<Person>{
		

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("User konnte nicht für die Identitätsleiste geladen werden");
			
			
		}

		@Override
		public void onSuccess(Person result) {
			ownOrgUnits.clear();
			ownProjectMarkets.clear();
			person=result;
			Integer idOfPerson=result.getId();
			ownOrgUnits.addItem("Person: "+result.getVorname()+" "+result.getNachname(), idOfPerson.toString());
			
			if(person.getTeamId()!=null){
				projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new getTeam());
			}else if(person.getUnternehmenId()!=null){
				projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new getUnternehmen());
			}
			projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(result, new getProjektmarktplatz());
		}
		
	}
	
	private class getTeam implements AsyncCallback<Team>{
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Team des Users konnte nicht f�r die Identit�tsleiste geladen werden");
			
			
		}

		@Override
		public void onSuccess(Team result) {
			Integer idOfTeam=result.getId();
			ownOrgUnits.addItem("Team: "+result.getName(),idOfTeam.toString());	
			team=result;
			if(person.getUnternehmenId()!=null){
				projektmarktplatzVerwaltung.getUnternehmenById(person.getUnternehmenId(), new getUnternehmen());
			}

		}
		
	}
	
	private class getUnternehmen implements AsyncCallback<Unternehmen>{
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Unternehmen des Users konnte nicht für die Identitätsleiste geladen werden");
			
			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer idOfUnternehmen=result.getId();
			ownOrgUnits.addItem("Unternehmen: "+result.getName(),idOfUnternehmen.toString());
			unternehmen=result;
		}
		
	}
	
	private class getProjektmarktplatz implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projektmarktplätze des Users konnten nicht für die Identitätsleiste geladen werden");			
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			if(result!=null){
				for(Projektmarktplatz p : result){
					ownProjectMarkets.addItem(p.getBezeichnung());
				}
				projektmarktplaetze = result;
			}
		}
		
	}

}
