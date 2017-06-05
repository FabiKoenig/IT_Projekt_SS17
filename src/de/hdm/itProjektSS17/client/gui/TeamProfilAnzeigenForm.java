package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class TeamProfilAnzeigenForm extends Showcase{

	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	//Erstellen der Buttons
	Button bearbeitenButton = new Button("Bearbeiten");
	Button speichernButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	Button teamVerlassenButton = new Button("Team verlassen");
	Button teamLoeschen = new Button("Team löschen");
	
	//Erstellen der Text- bzw. ListBoxen
	private TextBox teamNameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	private TextBox tb_unternehmen = new TextBox();
	
	
	//Erstellen der Label
	private Label teamNameLabel = new Label("Teamname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	private Label unternehmenLabel = new Label("Unternehmen");
	
	//SuggestBox um ein neues Team zu wählen
	private MultiWordSuggestOracle oracle_unternehmen= new MultiWordSuggestOracle();
	private SuggestBox sb_unternehmen = new SuggestBox(oracle_unternehmen);
	
	private Person user = IdentityMarketChoice.getUser();
	private Team team = (Team) IdentityMarketChoice.getSelectedIdentityAsObject();
	private Unternehmen unternehmenOfTeam = null;
		
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Teamdaten";
	}

	@Override
	protected void run() {

		projektmarktplatzVerwaltung.getAllUnternehmen(new getUnternehmenOfTeamCallback());
		
		teamNameBox.setText(team.getName());
		strasseBox.setText(team.getStrasse());
		hausnrBox.setText(team.getHausnummer());
		plzBox.setText(Integer.toString(team.getPlz()));
		ortBox.setText(team.getOrt());
		
		//Setzen der Boxen auf ReadOnly
		teamNameBox.setReadOnly(true);	
		strasseBox.setReadOnly(true);
		hausnrBox.setReadOnly(true);
		plzBox.setReadOnly(true);
		ortBox.setReadOnly(true);
		tb_unternehmen.setReadOnly(true);
		
		//Stylen der Buttons
		bearbeitenButton.setStylePrimaryName("navi-button");
		speichernButton.setStylePrimaryName("navi-button");
		abbrechenButton.setStylePrimaryName("navi-button");
		teamVerlassenButton.setStylePrimaryName("navi-button");
		teamLoeschen.setStylePrimaryName("navi-button");
		
		//Setzen des SpeicherButtons
		speichernButton.setVisible(false);
		abbrechenButton.setVisible(false);
		teamVerlassenButton.setVisible(false);
		teamLoeschen.setVisible(false);
			
		// Befüllen der FlexTable
		ftable.setWidget(0, 1, teamNameBox);
		ftable.setWidget(0, 0, teamNameLabel);

		ftable.setWidget(1, 1, strasseBox);
		ftable.setWidget(1, 0, strasseLabel);

		ftable.setWidget(2, 1, hausnrBox);
		ftable.setWidget(2, 0, hausnrLabel);

		ftable.setWidget(3, 1, plzBox);
		ftable.setWidget(3, 0, plzLabel);

		ftable.setWidget(4, 1, ortBox);
		ftable.setWidget(4, 0, ortLabel);
		
		ftable.setWidget(5, 0, unternehmenLabel);
		ftable.setWidget(5, 1, tb_unternehmen);
		
		/**
		 * Anfügen der FlexTable und des Buttons  an das Panel
		 */
		vpanel.setSpacing(8);
		buttonPanel.add(bearbeitenButton);
		buttonPanel.add(speichernButton);
		buttonPanel.add(teamVerlassenButton);
		buttonPanel.add(teamLoeschen);
		buttonPanel.add(abbrechenButton);
		vpanel.add(buttonPanel);
		vpanel.add(ftable);
		
		
		
		
		this.add(vpanel);
		
		
		//ClickHandler, der bei einem Klick auf den bearbeiten Button den ProfilBearbeitenCallback ausführt.
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				
				//Setzen auf NotReadOnly, um die Boxen wieder bearbeiten zu können.
				teamNameBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				ftable.setWidget(5, 1, sb_unternehmen);
				
				//Setzen des SpeicherButtons auf Visible
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				teamVerlassenButton.setVisible(true);
				teamLoeschen.setVisible(true);
				//Setzen des BearbeitenButtons auf NotVisible
				bearbeitenButton.setVisible(false);

			}
		});
		
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Navigation.reload();
			}
		});
		
		teamVerlassenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				user.setTeamId(0);

				projektmarktplatzVerwaltung.savePerson(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beziehung konnte nicht aufgelöst werden");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Beziehung wurde aufgelöst!");
						IdentityMarketChoice.getNavigation().reinitialize();
						IdentityMarketChoice.getOwnOrgUnits().setSelectedIndex(0);
						Navigation.reload();
					}
				
				});
			}
		});
		
		teamLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				user.setTeamId(0);
				projektmarktplatzVerwaltung.savePerson(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beziehung konnte nicht aufgelöst werden");
					}

					@Override
					public void onSuccess(Void result) {
						projektmarktplatzVerwaltung.deleteTeam(team, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Team konnte nicht gelöscht werden!");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Team: "+team.getName()+" wurde gelöscht und die Beziehung wurde aufgelöst!");
								IdentityMarketChoice.getNavigation().reinitialize();
								IdentityMarketChoice.getOwnOrgUnits().setSelectedIndex(0);
								Navigation.reload();
							}
							
						});
					}
				
				});
			}
		});
	

	}
	
	private class TeamSpeichernCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Profil konnte nicht gespeichert werden.");
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Profil wurde erfolgreich geändert.");
			
		}
		
	}
	
	private class getUnternehmenOfTeamCallback implements AsyncCallback<Vector<Unternehmen>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Unternehmen für das Team konnte nicht geladen werden");
		}

		@Override
		public void onSuccess(final Vector<Unternehmen> result) {
			
			for (Unternehmen unternehmen : result) {
				oracle_unternehmen.add(unternehmen.getName());
				if(team.getUnternehmenId()!=null){
					if(unternehmen.getId()==team.getUnternehmenId()){
					unternehmenOfTeam=unternehmen;
					}
				}
			}
			
			if(team.getUnternehmenId()==null){

				sb_unternehmen.setText("Kein Unternehmen");
				tb_unternehmen.setText("Kein Unternehmen");
			}else{

				sb_unternehmen.setText(unternehmenOfTeam.getName());
				tb_unternehmen.setText(unternehmenOfTeam.getName());
			}
					
			speichernButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Unternehmen chosenUnternehmen = null;
					for (Unternehmen unternehmen : result) {
						if(unternehmen.getName()==sb_unternehmen.getText()){
							team.setUnternehmenId(unternehmen.getId());
							chosenUnternehmen=unternehmen;
						}
					}
					
					
					if(sb_unternehmen.getText().isEmpty()){
						Window.alert("Kein Unternehmen angegeben, Beziehung wird aufgelöst");
						team.setName(teamNameBox.getText());
						team.setHausnummer(hausnrBox.getText());
						team.setOrt(ortBox.getText());
						team.setPlz(Integer.parseInt(plzBox.getText()));
						team.setStrasse(strasseBox.getText());
						team.setUnternehmenId(0);
						ClientsideSettings.getProjektmarktplatzVerwaltung().saveTeam(team, new TeamSpeichernCallback());	
						Navigation.reload();
					}else if(chosenUnternehmen==null){
						Window.alert("Gewähltes Unternehmen exisitert nicht! Bitte validen Eintrag angeben.");
					}else{
						team.setName(teamNameBox.getText());
						team.setHausnummer(hausnrBox.getText());
						team.setOrt(ortBox.getText());
						team.setPlz(Integer.parseInt(plzBox.getText()));
						team.setStrasse(strasseBox.getText());
						team.setUnternehmenId(chosenUnternehmen.getId());
						ClientsideSettings.getProjektmarktplatzVerwaltung().saveTeam(team, new TeamSpeichernCallback());
						Navigation.reload();
					}
				}
			});
		}
		
	}
}
