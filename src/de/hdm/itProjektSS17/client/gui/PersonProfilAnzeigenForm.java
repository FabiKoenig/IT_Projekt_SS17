package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.LoginInfo;
import de.hdm.itProjektSS17.client.Projektmarktplatz;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.LoginServiceAsync;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

/**
 * Klasse um das Profil einer Person anzuzeigen
 * @author Tim
 *
 */
public class PersonProfilAnzeigenForm extends Showcase{
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	private LoginInfo loginInfo = null;
	
	/**
	 * Konstruktor, dem ein Projekt und eine Instanz der navigation übergeben wird 
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public PersonProfilAnzeigenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		user= (Person) identityMarketChoice.getSelectedIdentityAsObject();
	}

	/**
	 * Anlegen der GUI-Elemente
	 */
	
	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable_form = new FlexTable();
	private FlexTable ftable_team = new FlexTable();
	private FlexTable ftable_unternehmen = new FlexTable();
	private FlexTable ft_buttonPanel = new FlexTable();
	private static DialogBox db_team = new DialogBox();
	private static DialogBox db_unternehmen = new DialogBox();
	private Button closeTeam = new Button("Schließen");
	private Button closeUnternehmen = new Button("Schließen");
	
	//Erstellen der Buttons
	private	Button bearbeitenButton = new Button("Bearbeiten");
	private Button speichernButton = new Button("Speichern");
	private Button abbrechenButton = new Button("Abbrechen");
	private Button teamButton = new Button("Team hinzufügen");
	private Button unternehmenButton = new Button("Unternehmen hinzufügen");
	private Button profilLoeschen = new Button("Profil löschen");
	
	private Button teamErstellenButton = new Button("Team Erstellen");
	private MultiWordSuggestOracle oracle_teamHinzufuegen= new MultiWordSuggestOracle();
	private SuggestBox sb_teamHinzufuegen = new SuggestBox(oracle_teamHinzufuegen);
	private Button teamHinzufuegenButton = new Button("OK");
	
	private Button unternehmenErstellenButton = new Button("Unternehmen Erstellen");
	private MultiWordSuggestOracle oracle_unternehmenHinzufuegen= new MultiWordSuggestOracle();
	private SuggestBox sb_unternehmenHinzufuegen = new SuggestBox(oracle_unternehmenHinzufuegen);
	private Button unternehmenHinzufuegenButton = new Button("OK");
	
	//Erstellen der Text- bzw. ListBoxen
	private ListBox anredeListBox = new ListBox();
	private TextBox anredeBox = new TextBox();
	private TextBox vnameBox = new TextBox();
	private TextBox nnameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	private TextBox emailBox = new TextBox();
	
	//Erstellen der Label
	private Label anredeLabel = new Label("Anrede");
	private Label vnameLabel = new Label("Vorname");
	private Label nnameLabel = new Label("Nachname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	private Label emailLabel = new Label("Google-Mail");
	
	/**
	 * Instanz der ProjektmarktplatzVerwaltungAsync abrufen.
	 */
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private Person user;
	
	/**
	 * Setzen des HeadLine Textes
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Personendaten";
	}

	/**
	 * Methode die startet, sobald diese Form aufgerufen wird
	 */
	@Override
	protected void run() {
		
		/**
		 * Aufruf der Methoden um alle Teams alle Unternehmen zu erhalten
		 */
		projektmarktplatzVerwaltung.getAllTeams(new getTeamCallback(user));
		projektmarktplatzVerwaltung.getAllUnternehmen(new getUnternehmenCallback(user));
		
		/**
		 * Werte in die verschiedenen TextBoxen. Diese werden aus dem übergebenen User gelesen.
		 */
		vnameBox.setText(user.getVorname());
		nnameBox.setText(user.getNachname());
		anredeBox.setText(user.getAnrede());
		strasseBox.setText(user.getStrasse());
		hausnrBox.setText(user.getHausnummer());
		plzBox.setText(Integer.toString(user.getPlz()));
		ortBox.setText(user.getOrt());
		emailBox.setText(user.getEmail());
		
		//Setzen der Boxen auf ReadOnly
		anredeBox.setReadOnly(true);
		vnameBox.setReadOnly(true);
		nnameBox.setReadOnly(true);
		strasseBox.setReadOnly(true);
		hausnrBox.setReadOnly(true);
		plzBox.setReadOnly(true);
		ortBox.setReadOnly(true);
		emailBox.setReadOnly(true);
		
		//Stylen der Buttons
		bearbeitenButton.setStylePrimaryName("cell-btn");
		speichernButton.setStylePrimaryName("cell-btn");
		abbrechenButton.setStylePrimaryName("cell-btn");
		teamButton.setStylePrimaryName("cell-btn");
		unternehmenButton.setStylePrimaryName("cell-btn");
		profilLoeschen.setStylePrimaryName("cell-btn");
		
		//Hinzufügen der Inhalte der anredeListBox
		anredeListBox.addItem("Herr");
		anredeListBox.addItem("Frau");
			
		// Befüllen der FlexTable
		ftable_form.setWidget(0, 1, emailBox);
		ftable_form.setWidget(0, 0, emailLabel);
		
		ftable_form.setWidget(1, 1, anredeBox);
		ftable_form.setWidget(1, 0, anredeLabel);

		ftable_form.setWidget(2, 1, vnameBox);
		ftable_form.setWidget(2, 0, vnameLabel);

		ftable_form.setWidget(3, 1, nnameBox);
		ftable_form.setWidget(3, 0, nnameLabel);

		ftable_form.setWidget(4, 1, strasseBox);
		ftable_form.setWidget(4, 0, strasseLabel);

		ftable_form.setWidget(5, 1, hausnrBox);
		ftable_form.setWidget(5, 0, hausnrLabel);
		
		ftable_form.setWidget(6, 1, plzBox);
		ftable_form.setWidget(6, 0, plzLabel);

		ftable_form.setWidget(7, 1, ortBox);
		ftable_form.setWidget(7, 0, ortLabel);


		/**
		 * Anfügen der FlexTable und des Buttons  an das Panel
		 */
		vpanel.setSpacing(8);
		ft_buttonPanel.setWidget(0, 0, bearbeitenButton);
		ft_buttonPanel.setWidget(0, 1, profilLoeschen);
		ft_buttonPanel.setWidget(0, 2, speichernButton);
		ft_buttonPanel.setWidget(0, 3, abbrechenButton);
		ft_buttonPanel.setWidget(0, 4, teamButton);
		ft_buttonPanel.setWidget(0, 5, unternehmenButton);
		
		
		speichernButton.setVisible(false);
		abbrechenButton.setVisible(false);
		teamButton.setVisible(false);
		unternehmenButton.setVisible(false);
		
		ftable_team.setWidget(1, 0, new Label("Bestehendes Team wählen:"));;
		ftable_team.setWidget(1, 1, sb_teamHinzufuegen);
		ftable_team.setWidget(1, 2, teamHinzufuegenButton);
		ftable_team.setWidget(2, 0, new Label("Oder: Neues Team erstellen:"));
		ftable_team.setWidget(2, 1, teamErstellenButton);
		ftable_team.setWidget(3, 1, closeTeam);
		
		ftable_unternehmen.setWidget(1, 0, new Label("Bestehendes Unternehmen wählen:"));;
		ftable_unternehmen.setWidget(1, 1, sb_unternehmenHinzufuegen);
		ftable_unternehmen.setWidget(1, 2, unternehmenHinzufuegenButton);
		ftable_unternehmen.setWidget(2, 0, new Label("Oder: Neues Unternehmen erstellen:"));
		ftable_unternehmen.setWidget(2, 1, unternehmenErstellenButton);
		ftable_unternehmen.setWidget(3, 1, closeUnternehmen);
		
		vpanel.add(ft_buttonPanel);
		vpanel.add(ftable_form);
		this.add(vpanel);
		this.setSpacing(8);
		
		profilLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				final DialogBox abfrageBox = new DialogBox();
				Label abfrageLabel = new Label("Möchten Sie wirklich Ihr Profil löschen?");
				Button jaButton = new Button("Ja");
				Button neinButton = new Button("Nein");
				HorizontalPanel buttonPanel = new HorizontalPanel();
				VerticalPanel boxPanel = new VerticalPanel();
				
				boxPanel.add(abfrageLabel);
				boxPanel.add(buttonPanel);
				
				buttonPanel.add(jaButton);
				buttonPanel.add(neinButton);
				jaButton.setStylePrimaryName("cell-btn");
				neinButton.setStylePrimaryName("cell-btn");
				boxPanel.setSpacing(8);
				abfrageBox.add(boxPanel);
				abfrageBox.setAnimationEnabled(false);
				abfrageBox.setGlassEnabled(true);
				abfrageBox.center();

				abfrageBox.show();
				
				
				
				neinButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						abfrageBox.hide();
					}
				});
				
				jaButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						projektmarktplatzVerwaltung.deletePerson(identityMarketChoice.getUser(), new AsyncCallback<Void>() {
							
							@Override
							public void onSuccess(Void result) {
								Window.alert("Das Profil wurde erfolgreich gelöscht!");
								
								//TODO User ausloggen
								
								abfrageBox.hide();
								
								LoginServiceAsync loginService = ClientsideSettings.getLoginService();
								loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
									
									@Override
									public void onSuccess(LoginInfo result) {
									
										loginInfo = result;										
										Window.open(loginInfo.getLogoutUrl(), "_self", "");
									}
									
									@Override
									public void onFailure(Throwable caught) {
										
										
									}
								});
								
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});
					}
				});
			}
		});
		
		//ClickHandler, der bei einem Klick auf den bearbeiten Button den ProfilBearbeitenCallback ausführt.
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				//Setzen der ListBox anstelle der TextBox
				ftable_form.setWidget(1, 1, anredeListBox);
				//Setzen auf NotReadOnly, um die Boxen wieder bearbeiten zu können.
				anredeBox.setReadOnly(false);
				vnameBox.setReadOnly(false);
				nnameBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				//Setzen des Buttons auf Visible
				bearbeitenButton.setVisible(false);
				profilLoeschen.setVisible(false);
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				
				
			}
		});
		
		/**
		 * Click-Handler für den abbrechen Button
		 */
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				teamButton.setVisible(false);
				unternehmenButton.setVisible(false);
				navigation.reload();	
			}
		});
		
		/**
		 * Click-Handler bei dem der ProilBearbeitenCallBack aufgerufen wird mit der übergebenen Person.
		 */
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getProjektmarktplatzVerwaltung().
				getPersonById(identityMarketChoice.getSelectedIdentityId(), new ProfilBearbeitenCallback());

			}
		});
		
		/**
		 * Click-Handler um ein Team zu erstellen.
		 * Hierzu wird eine neue DialogBox aufgerufen.
		 * @see de.hdm.itProjektSS17.client.gui.DialogBoxTeamErstellen
		 */
		teamErstellenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBoxTeamErstellen db_TeamErstellen = new DialogBoxTeamErstellen(identityMarketChoice, navigation);
				db_TeamErstellen.show();
				db_TeamErstellen.center();
			}
		});

		/**
		 * Click-Handler um ein Unternehmen zu erstellen.
		 * Hierzu wird eine neue DialogBox aufgerufen.
		 * @see de.hdm.itProjektSS17.client.gui.DialogBoxUnternehmenErstellen
		 */
		unternehmenErstellenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBoxUnternehmenErstellen db_unternehmenErstellen = new DialogBoxUnternehmenErstellen(identityMarketChoice, navigation);
				db_unternehmenErstellen.show();
				db_unternehmenErstellen.center();
			}
		});
		
		/**
		 * Click-Handler für den <code>teamButton</code>
		 * der die DialogBox <code>db_team</code> aufruft.
		 */
		teamButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				db_team.center();
			}
		});
		
		/**
		 * Click-Handler für den <code>unternehmenButton</code>
		 * der die DialogBox <code>db_unternehmen</code> aufruft.
		 */
		unternehmenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				db_unternehmen.center();
			}
		});
		
		/**
		 * Click-Handler der die DialogBox <code>db_team</code> wieder schließt.
		 */
		closeTeam.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				db_team.hide();
			}
		});
		
		/**
		 * Click-Handler der die DialogBox <code>db_unternehmen</code> wieder schließt.
		 */
		closeUnternehmen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				db_unternehmen.hide();
			}
		});
		
	}
	
	/**
	 * CallBack um das eigene Profil zu bearbeiten.
	 * Hierzu werden die Inhalte aus den verschiedenen Boxen gelesen und in das
	 * <code> result<code> gesetzt.
	 * Anschließend wird die save Methode aufgerufen um die Änderungen zu speichern.
	 * @author Tim
	 *
	 */
	private class ProfilBearbeitenCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
		}

		@Override
		public void onSuccess(Person result) {
			result.setAnrede(anredeListBox.getItemText(anredeListBox.getSelectedIndex()));
			result.setHausnummer(hausnrBox.getText());
			result.setNachname(nnameBox.getText());
			result.setOrt(ortBox.getText());
			result.setPlz(Integer.parseInt(plzBox.getText()));
			result.setStrasse(strasseBox.getText());
			result.setVorname(vnameBox.getText());
			
			ClientsideSettings.getProjektmarktplatzVerwaltung().savePerson(result, new PersonSpeichernCallback());


			
		}
		
	}
	
	/**
	 * CallBack der aufgerufen wird, wenn Änderungen am Profil vorgenommen wurden.
	 * @author Tim
	 *
	 */
	private class PersonSpeichernCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Profil konnte nicht gespeichert werden.");
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Profil wurde erfolgreich aktualisiert.");
			db_team.hide();
			db_unternehmen.hide();
			identityMarketChoice.reinitialize();
			navigation.reload();
		}
		
	}
	
	/**
	 * Callback welcher einen Vector mit Teams als <code> result</code> zurückgibt.
	 * Das  <code> result</code>  mit den Teams wird durchgegangen, der Name des jeweiligen Teams ausgelesen
	 * und dann zu <code> oracle_teamHinzufügen</code> hinzugefügt.
	 * @author Tim
	 *
	 */
	private class getTeamCallback implements AsyncCallback<Vector <Team>>{

		Person personTemp = new Person();
		
		public getTeamCallback(Person result){
			this.personTemp=result;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Teamauswahl konnte nicht geladen werden!");
		}

		@Override
		public void onSuccess(final Vector<Team> result) {
			
			for (Team team : result) {
				oracle_teamHinzufuegen.add(team.getName());
			}

			bearbeitenButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(user.getTeamId()==null){
							db_team.clear();
							db_team.add(ftable_team);
							db_team.setText("Team hinzufügen...");
							db_team.setAnimationEnabled(false);
							db_team.setGlassEnabled(true);
							teamButton.setVisible(true);	
					}
				}
			});
			
			/**
			 * Click-Handler um ein Team zu einem profil hinzuzufügen
			 */
			teamHinzufuegenButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					Team chosenTeam = null;
					for (Team team : result) {
						if(team.getName()==sb_teamHinzufuegen.getText()){
							personTemp.setTeamId(team.getId());
							chosenTeam=team;
						}
					}
					if(chosenTeam==null){
						Window.alert("Gewähltes Team exisitert nicht! Bitte validen Eintrag angeben.");
					}else{
						projektmarktplatzVerwaltung.savePerson(personTemp, new PersonSpeichernCallback());
						if(chosenTeam.getUnternehmenId()!=null){
							
							DialogBoxUnternehmenHinzufuegen db_unternehmenHinzufuegen = new DialogBoxUnternehmenHinzufuegen(personTemp, chosenTeam, identityMarketChoice, navigation);
							db_unternehmenHinzufuegen.show();
							
						}
					}
				}
				
			});

			
		}
		
	}
	
	/**
	 * Callback welcher einen Vector mit Unternehmen als <code> result</code> zurückgibt.
	 * Das  <code> result</code>  mit den Unternehmen wird durchgegangen, der Name des jeweiligen Unternehmen ausgelesen
	 * und dann zu <code> oracle_unternehmenHinzufügen</code> hinzugefügt.
	 * @author Tim
	 *
	 */
	private class getUnternehmenCallback implements AsyncCallback<Vector <Unternehmen>>{
		/**
		 * Personen Objekt wird gespeichert und das Person result abgespeichert. 
		 * Dies wird später benötigt, falls zu dieser Person ein Unternehmen oder Team hinzugefügt werden soll.
		 */
		Person personTemp = new Person();
		
		public getUnternehmenCallback(Person result){
			this.personTemp=result;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(final Vector<Unternehmen> result) {

			for (Unternehmen unternehmen : result) {
				oracle_unternehmenHinzufuegen.add(unternehmen.getName());
			}
			
			bearbeitenButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(user.getUnternehmenId()==null){
						db_unternehmen.clear();
						db_unternehmen.add(ftable_unternehmen);
						db_unternehmen.setText("Unternehmen hinzufügen...");
						db_unternehmen.setAnimationEnabled(false);
						db_unternehmen.setGlassEnabled(true);
						unternehmenButton.setVisible(true);
					}
				}
			});
			
			/**
			 * Click-Handler um ein Unternehmen zur Person hinzuzufügen
			 */
			unternehmenHinzufuegenButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Unternehmen chosenUnternehmen = null;
					for (Unternehmen unternehmen : result) {
						if(unternehmen.getName()==sb_unternehmenHinzufuegen.getText()){
							personTemp.setUnternehmenId(unternehmen.getId());
							chosenUnternehmen=unternehmen;
						}
					}
					if(chosenUnternehmen==null){
						Window.alert("Gewähltes Unternehmen exisitert nicht! Bitte validen Eintrag angeben.");
					}else{
					projektmarktplatzVerwaltung.savePerson(personTemp, new PersonSpeichernCallback());
					}
				}
			});
			

		}
		
	}
	
	public static DialogBox getDb_Team(){
		return db_team;
	}
	
	public static DialogBox getDb_Unternehmen(){
		return db_unternehmen;
	}
		
	
}
