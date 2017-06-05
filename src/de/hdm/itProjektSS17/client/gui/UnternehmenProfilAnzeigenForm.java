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
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class UnternehmenProfilAnzeigenForm extends Showcase{


	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	//Erstellen der Buttons
	Button bearbeitenButton = new Button("Bearbeiten");
	Button speichernButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	Button unternehmenVerlassenButton = new Button("Unternehmen verlassen");
	Button unternehmenLoeschen = new Button("Unternehmen löschen");
	
	//Erstellen der Text- bzw. ListBoxen
	private TextBox unternehmenNameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	
	
	//Erstellen der Label
	private Label unternehmenNameLabel = new Label("Firmenname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	
	private Person user = IdentityMarketChoice.getUser();
	private Unternehmen unternehmen = (Unternehmen) IdentityMarketChoice.getSelectedIdentityAsObject();
	
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Unternehmensdaten";
	}

	@Override
	protected void run() {
		
		try {
			
			ClientsideSettings.getProjektmarktplatzVerwaltung()
			.getUnternehmenById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilAnzeigenCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Setzen der Boxen auf ReadOnly
		unternehmenNameBox.setReadOnly(true);
		strasseBox.setReadOnly(true);
		hausnrBox.setReadOnly(true);
		plzBox.setReadOnly(true);
		ortBox.setReadOnly(true);
		
		//Stylen der Buttons
		bearbeitenButton.setStylePrimaryName("navi-button");
		speichernButton.setStylePrimaryName("navi-button");
		abbrechenButton.setStylePrimaryName("navi-button");
		unternehmenVerlassenButton.setStylePrimaryName("navi-button");
		unternehmenLoeschen.setStylePrimaryName("navi-button");

		
		//Setzen des SpeicherButtons
		speichernButton.setVisible(false);
		abbrechenButton.setVisible(false);
		unternehmenVerlassenButton.setVisible(false);
		unternehmenLoeschen.setVisible(false);
			
		// Befüllen der FlexTable
		ftable.setWidget(0, 1, unternehmenNameBox);
		ftable.setWidget(0, 0, unternehmenNameLabel);

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
		buttonPanel.add(unternehmenVerlassenButton);
		buttonPanel.add(unternehmenLoeschen);
		buttonPanel.add(abbrechenButton);

		vpanel.add(buttonPanel);
		vpanel.add(ftable);
		
		this.add(vpanel);
		
		
		//ClickHandler, der bei einem Klick auf den bearbeiten Button den ProfilBearbeitenCallback ausführt.
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				
				//Setzen auf NotReadOnly, um die Boxen wieder bearbeiten zu können.
				unternehmenNameBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				//Setzen des SpeicherButtons auf Visible
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				unternehmenVerlassenButton.setVisible(true);
				unternehmenLoeschen.setVisible(true);
				//Setzen des BearbeitenButtons auf NotVisible
				bearbeitenButton.setVisible(false);
			}
		});
		
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Navigation.reload();
			}
		});
		
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getProjektmarktplatzVerwaltung().
				getUnternehmenById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilBearbeitenCallback());
			}
		});
		
		unternehmenVerlassenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				user.setUnternehmenId(0);

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
		
		unternehmenLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {				
				user.setUnternehmenId(0);
				projektmarktplatzVerwaltung.savePerson(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beziehung konnte nicht aufgelöst werden");
					}

					@Override
					public void onSuccess(Void result) {
						projektmarktplatzVerwaltung.deleteUnternehmen(unternehmen, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unternehmen konnte nicht gelöscht werden!");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Unternehmen: "+unternehmen.getName()+" wurde gelöscht und die Beziehung wurde aufgelöst!");
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
	
	private class ProfilAnzeigenCallback implements AsyncCallback<Unternehmen> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			unternehmenNameBox.setText(result.getName());
			strasseBox.setText(result.getStrasse());
			hausnrBox.setText(result.getHausnummer());
			plzBox.setText(Integer.toString(result.getPlz()));
			ortBox.setText(result.getOrt());
			
			
		}
	}
	private class ProfilBearbeitenCallback implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
		}

		@Override
		public void onSuccess(Unternehmen result) {
			
			result.setName(unternehmenNameBox.getText());
			result.setHausnummer(hausnrBox.getText());
			result.setOrt(ortBox.getText());
			result.setPlz(Integer.parseInt(plzBox.getText()));
			result.setStrasse(strasseBox.getText());

			
			ClientsideSettings.getProjektmarktplatzVerwaltung().saveUnternehmen(result, new UnternehmenSpeichernCallback());
		
			Showcase showcase = new UnternehmenProfilAnzeigenForm();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	
	
	private class UnternehmenSpeichernCallback implements AsyncCallback<Void>{
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