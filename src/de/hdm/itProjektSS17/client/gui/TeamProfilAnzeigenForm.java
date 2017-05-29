package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class TeamProfilAnzeigenForm extends Showcase{


	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	//Erstellen der Buttons
	Button bearbeitenButton = new Button("Bearbeiten");
	Button speichernButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	
	
	//Erstellen der Text- bzw. ListBoxen
	private TextBox teamNameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	
	
	//Erstellen der Label
	private Label teamNameLabel = new Label("Teamname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	
		
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Teamdaten";
	}

	@Override
	protected void run() {
		
		try {
			
			ClientsideSettings.getProjektmarktplatzVerwaltung()
			.getTeamById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilAnzeigenCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Setzen der Boxen auf ReadOnly
		teamNameBox.setReadOnly(true);
		strasseBox.setReadOnly(true);
		hausnrBox.setReadOnly(true);
		plzBox.setReadOnly(true);
		ortBox.setReadOnly(true);
		
		//Stylen der Buttons
		bearbeitenButton.setStylePrimaryName("navi-button");
		speichernButton.setStylePrimaryName("navi-button");
		abbrechenButton.setStylePrimaryName("navi-button");
		
		//Setzen des SpeicherButtons
		speichernButton.setVisible(false);
		abbrechenButton.setVisible(false);
			
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
		
		/**
		 * Anfügen der FlexTable und des Buttons  an das Panel
		 */
		vpanel.setSpacing(8);
		buttonPanel.add(bearbeitenButton);
		buttonPanel.add(speichernButton);
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
				
				//Setzen des SpeicherButtons auf Visible
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				//Setzen des BearbeitenButtons auf NotVisible
				bearbeitenButton.setVisible(false);
			}
		});
		
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new TeamProfilAnzeigenForm());
			}
		});
		
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getProjektmarktplatzVerwaltung().
				getTeamById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilBearbeitenCallback());
			}
		});
	}
	
	private class ProfilAnzeigenCallback implements AsyncCallback<Team> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Team result) {
			teamNameBox.setText(result.getName());
			strasseBox.setText(result.getStrasse());
			hausnrBox.setText(result.getHausnummer());
			plzBox.setText(Integer.toString(result.getPlz()));
			ortBox.setText(result.getOrt());
			
			
		}
	}
	private class ProfilBearbeitenCallback implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
		}

		@Override
		public void onSuccess(Team result) {
			
			result.setName(teamNameBox.getText());
			result.setHausnummer(hausnrBox.getText());
			result.setOrt(ortBox.getText());
			result.setPlz(Integer.parseInt(plzBox.getText()));
			result.setStrasse(strasseBox.getText());

			
			ClientsideSettings.getProjektmarktplatzVerwaltung().saveTeam(result, new TeamSpeichernCallback());
		
			Showcase showcase = new TeamProfilAnzeigenForm();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
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
	
}
