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
	
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	public DialogBoxUnternehmenHinzufuegen(Person person, Team team){
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
		projektmarktplatzverwaltung.getUnternehmenByForeignOrganisationseinheit(team, new getUnternehmenByTeamCallback(person));
	}
	
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
			
			Button bt_ja = new Button("Ja");
			Button bt_nein = new Button("Nein");
			vp.add(new Label("Das hinzugefügte Team wurde dem Unternehmen: " + result.getName() + " zugeordnet."));
			vp.add(new Label("Wollen Sie zu diesem Unternehmen ebenfalls hinzugefügt werden?"));
			
			HorizontalPanel hp1 = new HorizontalPanel();
			hp1.add(bt_ja);
			hp1.add(bt_nein);
			
			vp.add(hp1);
			add(vp);
			
			bt_ja.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					person.setUnternehmenId(result.getId());
					projektmarktplatzverwaltung.savePerson(person, new PersonSpeichernCallback());
				}
			});
			
			bt_nein.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			
		}
		
	}
	
	private class PersonSpeichernCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Profil konnte nicht gespeichert werden.");
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Unternehmen wurde erfolgreich übernommen.");
			hide();
			IdentityMarketChoice.getNavigation().reinitialize();
			Navigation.reload();
		}
		
	}

}
