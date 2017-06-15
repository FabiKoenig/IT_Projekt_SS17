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

/**
 * Diese Klasse erbt von der Klasse Showcase. Sie stellt einen Form bereit, 
 * mit der das Profil eines Unternehmens angezeigt werden kann.
 * 
 * @author Fabian
 *
 */

public class UnternehmenProfilAnzeigenForm extends Showcase{


	/**
	 * Setzen der Navigation und der IdentityMarketChoice, die über den Konstruktor gesetzt werden.
	 */
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	/**
	 * Anlegen von globalen Variablen
	 */
	private Person user; 
	private Unternehmen unternehmen; 
	
	
	/**
	 * Konstruktor, dem eine Instanz der Klasse IdentityMarketChoice und der Navigation mitgegeben wird.
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public UnternehmenProfilAnzeigenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		user = identityMarketChoice.getUser();
		unternehmen = (Unternehmen) identityMarketChoice.getSelectedIdentityAsObject();
	}


	/**
	 * Anlegen der Panels und der FlexTable
	 */
	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	/**
	 * Anlegen der Buttons
	 */
	private Button bearbeitenButton = new Button("Bearbeiten");
	private Button speichernButton = new Button("Speichern");
	private Button abbrechenButton = new Button("Abbrechen");
	private Button unternehmenVerlassenButton = new Button("Unternehmen verlassen");
	private Button unternehmenLoeschen = new Button("Unternehmen löschen");
	
	/**
	 * Erstellen der Text- bzw. ListBoxen
	 */
	private TextBox unternehmenNameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	
	/**
	 * Erstellen der Label
	 */
	private Label unternehmenNameLabel = new Label("Firmenname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	

	
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
	/**
	 * Setzen der Headline.
	 * Diese Methode wird von der Superklasse vorgegeben und setzt für dieses GUI Element eine Überschrift.
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Unternehmensdaten";
	}

	/**
	 * Diese Methode wird ausgeführt, wenn die Form angezeigt wird.
	 */
	@Override
	protected void run() {
		
		try {
			/**
			 * Hier wird der Callback aufgerufen, um das Unternehmen-Objekt abzurufen.
			 */
			ClientsideSettings.getProjektmarktplatzVerwaltung()
			.getUnternehmenById(identityMarketChoice.getSelectedIdentityId(), new ProfilAnzeigenCallback());
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
		bearbeitenButton.setStylePrimaryName("cell-btn");
		speichernButton.setStylePrimaryName("cell-btn");
		abbrechenButton.setStylePrimaryName("cell-btn");
		unternehmenVerlassenButton.setStylePrimaryName("cell-btn");
		unternehmenLoeschen.setStylePrimaryName("cell-btn");

		
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

		/**
		 * Hinzufügen der GUI-Elemente zu den Panels
		 * und stylen der Elemente
		 */
		vpanel.add(buttonPanel);
		vpanel.add(ftable);		
		this.add(vpanel);
		this.setSpacing(8);
		
		
		/**
		 * ClickHandler, der bei einem Klick auf den bearbeiten Button den ProfilBearbeitenCallback ausführt.
		 */
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				
				/**
				 * Setzen auf NotReadOnly, um die Boxen wieder bearbeiten zu können.
				 */
				unternehmenNameBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				/**
				 * Setzen der Buttons auf Visible
				 */
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				unternehmenVerlassenButton.setVisible(true);
				unternehmenLoeschen.setVisible(true);
				bearbeitenButton.setVisible(false);
			}
		});
		
		/**
		 * ClickHandler zum Abbrechen. Die Navigation wird neu geladen.
		 */
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				navigation.reload();
			}
		});
		
		/**
		 * ClickHandler zum speichern, falls Änderungen gemacht wurden.
		 */
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getProjektmarktplatzVerwaltung().
				getUnternehmenById(identityMarketChoice.getSelectedIdentityId(), new ProfilBearbeitenCallback());
			}
		});
		
		/**
		 * ClickHandler um einem beigetretenen Unternehmen zu verlassen.
		 */
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
						identityMarketChoice.reinitialize();
						identityMarketChoice.getOwnOrgUnits().setSelectedIndex(0);
						navigation.reload();
					}
				
				});
			}
		});
		
		/**
		 * ClickHandler um ein beigetretenem Unternehmen zu löschen.
		 */
		unternehmenLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {	
				/**
				 * Die Änderung wird in dem Personen-Objekt gespeichert.
				 */
				user.setUnternehmenId(0);
				projektmarktplatzVerwaltung.savePerson(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Beziehung konnte nicht aufgelöst werden");
					}

					@Override
					public void onSuccess(Void result) {
						/**
						 * Das zu löschende Unternehmen wird gelöscht.
						 */
						projektmarktplatzVerwaltung.deleteUnternehmen(unternehmen, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unternehmen konnte nicht gelöscht werden!");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Unternehmen: "+unternehmen.getName()+" wurde gelöscht und die Beziehung wurde aufgelöst!");
								identityMarketChoice.reinitialize();
								identityMarketChoice.getOwnOrgUnits().setSelectedIndex(0);
								navigation.reload();
							}
							
						});
					}
				
				});
			}
		});
	}
	
	/**
	 * CallBack um die Daten aus einem Unternehmen-Objekt zu lesen und in die TextBoxen, die in einer FlexTable angeordnet sind
	 * zu schreiben.
	 * @author Fabian
	 *
	 */
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
	
	/**
	 * CallBack um ein Profil zu bearbeiten. Die Inhalte werden aus den TextBoxen gelesen, 
	 * das anschliessend zum speichern übergeben wird.
	 * @author Fabian
	 *
	 */
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

			
			ClientsideSettings.getProjektmarktplatzVerwaltung().saveUnternehmen(result, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Das Profil konnte nicht gespeichert werden.");
					
				}

				@Override
				public void onSuccess(Void result) {
					Window.alert("Das Profil wurde erfolgreich geändert.");
					navigation.reload();
					
				}
			});	
		}	
	}
}