package de.hdm.itProjektSS17.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class DialogBoxUnternehmenHinzufuegen extends DialogBox{
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	Button bt_ja = new Button("Ja");
	Button bt_nein = new Button("Nein");
	/**
	 * Instanz der ProjektmarktplatzVerwaltungsAsync abrufen
	 */
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	/**
	 * Anlegen des Konstruktors
	 * @param person
	 * @param team
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public DialogBoxUnternehmenHinzufuegen(Person person, Team team, IdentityMarketChoice identityMarketChoice, Navigation navigation){
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
		projektmarktplatzverwaltung.getUnternehmenByForeignOrganisationseinheit(team, new getUnternehmenByTeamCallback(person));
	}
	/**
	 * Private Klasse welche einen neuen CallBack implementiert, 
	 * der eine Person als result zurück gibt.
	 * @author Tom
	 *
	 */
	private class getUnternehmenByTeamCallback implements AsyncCallback<Unternehmen>{

		private Person person = new Person();
		
		public getUnternehmenByTeamCallback(Person person){
			this.person=person;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(final Unternehmen result) {
			setText("Unternehmen " +result.getName()+" hinzufügen?");
			
			VerticalPanel vp = new VerticalPanel();
			vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);	
			
			bt_ja.setStylePrimaryName("cell-btn");
			bt_nein.setStylePrimaryName("cell-btn");
			vp.add(new Label("Das hinzugefügte Team wurde dem Unternehmen: " + result.getName() + " zugeordnet."));
			vp.add(new Label("Wollen Sie zu diesem Unternehmen ebenfalls hinzugefügt werden?"));
			
			HorizontalPanel hp1 = new HorizontalPanel();
			hp1.setSpacing(5);
			hp1.add(bt_ja);
			hp1.add(bt_nein);
			
			vp.add(hp1);
			add(vp);
			center();
			/*
			 * Anlegen des ClickHandlers des Ja-Buttons
			 */
			bt_ja.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					person.setUnternehmenId(result.getId());
					projektmarktplatzverwaltung.savePerson(person, new PersonSpeichernCallback());
				}
			});
			/*
			 * Anlegen des ClickHandlers des Nein-Buttons
			 */
			bt_nein.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			
		}
		
	}
	/**
	 * Private Klasse welche einen neuen CallBack implementiert, 	 
	 * @author Tom
	 *
	 */
	private class PersonSpeichernCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Profil konnte nicht gespeichert werden.");
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Unternehmen wurde erfolgreich übernommen.");
			hide();
			identityMarketChoice.reinitialize();
			navigation.reload();
		}
		
	}

}
