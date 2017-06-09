package de.hdm.itProjektSS17.client;


import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.Navigation;
import de.hdm.itProjektSS17.client.gui.report.IdentityChoiceReport;
import de.hdm.itProjektSS17.client.gui.report.NavigationReport;
import de.hdm.itProjektSS17.shared.LoginServiceAsync;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;



public class ReportEntry implements EntryPoint {

	
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung;
	private ReportGeneratorAsync reportGenerator;
	private LoginServiceAsync loginService;
	
	final Button Logout = new Button("Logout");
	private Button loginButton = new Button("Login");
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Bitte melde dich mit deinem Google Account an, um Zugang zu Projekto zu erhalten.");
	private Anchor signInLink= new Anchor("Login");
	private Anchor signOutLink = new Anchor("Logout");
	
	
	
	@Override
	public void onModuleLoad() {
		
		/*
		 * Zuerst instanzieren wir jeweils eine EditorService-Instanz & eine
		 * LoginService Instanz
		 */
		
		this.reportGenerator = ClientsideSettings.getReportGenerator();
		this.loginService = ClientsideSettings.getLoginService();
		this.projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
		//Überprüfen des Login-Status
		//LoginServiceAsync loginService = GWT.create(LoginService.class); 
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
				Window.alert(GWT.getHostPageBaseURL());
				
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()){
					projektmarktplatzVerwaltung.getAllPersonen(new AsyncCallback<Vector<Person>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Login fehlgeschlagen!");
						}

						@Override
						public void onSuccess(Vector<Person> result) {
							boolean isUserRegistered = false;
							for (Person person : result) {
								if(person.getEmail()==loginInfo.getEmailAddress()){
									isUserRegistered = true;
									loadReportGenerator(person.getId());
									break;
								}
							}
							if(isUserRegistered==false){
								RootPanel.get("Details").clear();
								RootPanel.get("Details").add(new RegistrierenForm());							
							}
						}
					});
					
				} else{
					loadLogin();
				}
				
			}
		});	
	
	}
	
	
	
		private void loadLogin(){
			
			loginPanel.setSpacing(20);
			loginPanel.add(loginLabel);
			loginPanel.add(loginButton);
			signInLink.setHref(loginInfo.getLoginUrl());
			RootPanel.get("DetailsReport").add(loginLabel);
			RootPanel.get("NavigatorReport").add(loginButton);
			
			
			loginButton.setWidth("150px");
			loginButton.setStylePrimaryName("projektmarktplatz-logout");
			loginButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Window.open(signInLink.getHref(), "_self",
							"");	
				}
			});
		}
	
	
		private void loadReportGenerator(int personId){
			
			//Erstellen des Logout-Links
			signOutLink.setHref(loginInfo.getLogoutUrl());
			
//			ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
			IdentityChoiceReport identityChoiceReport = new IdentityChoiceReport(personId);
			RootPanel.get("HeaderReport").clear();
			RootPanel.get("HeaderReport").add(identityChoiceReport);
//			Integer test = IdentityMarketChoice.getNavigation(3).getSelectedIdentityId();

		    RootPanel.get("NavigatorReport").add(Logout);
		    RootPanel.get("NavigatorReport").add(new NavigationReport(identityChoiceReport));
			
		    
		    //TopPanel für Logut
//		    VerticalPanel topPanel = new VerticalPanel();
//		    RootPanel.get("Header").add(topPanel);
		    //Erstellen Projektmarktzplatz Button
		    
		    Logout.setWidth("150px");
		    Logout.setStylePrimaryName("projektmarktplatz-logout");

		  
		   
		    Logout.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Window.open(signOutLink.getHref(), "_self",
							"");
				}
			});
		    
		    ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		}
					
	private class RegistrierenForm extends Showcase{

		
		private VerticalPanel vpanel = new VerticalPanel();
		private FlexTable ftable_form = new FlexTable();
		private FlexTable ft_buttonPanel = new FlexTable();
		
		//Erstellen der Buttons
		private Button speichernButton = new Button("Absenden");
		
		//Erstellen der Text- bzw. ListBoxen
		private ListBox anredeListBox = new ListBox();
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
		

		private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
		@Override
		protected String getHeadlineText() {
			// TODO Auto-generated method stub
			return "Bitte registrieren Sie sich!";
		}

		@Override
		protected void run() {
			emailBox.setText(loginInfo.getEmailAddress());
			emailBox.setReadOnly(true);
			
			//Stylen der Buttons
			speichernButton.setStylePrimaryName("navi-button");
			
			//Hinzufügen der Inhalte der anredeListBox
			anredeListBox.addItem("Herr");
			anredeListBox.addItem("Frau");
				
			// Befüllen der FlexTable
			ftable_form.setWidget(0, 1, emailBox);
			ftable_form.setWidget(0, 0, emailLabel);
			
			ftable_form.setWidget(1, 1, anredeListBox);
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
			ft_buttonPanel.setWidget(0, 1, speichernButton);
			
			vpanel.add(ftable_form);
			vpanel.add(ft_buttonPanel);
			this.add(vpanel);
			
			//ClickHandler, der bei einem Klick auf den Absenden Button den ProfilBearbeitenCallback ausführt.

			speichernButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					try {
						Integer.parseInt(plzBox.getText());
						projektmarktplatzVerwaltung.createPartnerprofil(new UserAnlegenCallback());
					} catch (Exception e) {
						Window.alert("PLZ muss eine Zahl sein!");
					}
				
				}
			});
			
		
		}
		
		private class UserAnlegenCallback implements AsyncCallback<Partnerprofil>{
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Partnerprofil result) {
				int plz = 0;
				if(plzBox.getText().isEmpty()==false){
					plz=Integer.parseInt(plzBox.getText());
				}
				projektmarktplatzVerwaltung.createPerson(emailBox.getText(), vnameBox.getText(), nnameBox.getText(), 
						anredeListBox.getSelectedItemText(), strasseBox.getText(), hausnrBox.getText(), plz, 
						ortBox.getText(), result.getId(), new Integer(0), new Integer(0), new AsyncCallback<Person>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Neuer User konnte nicht erstellt werden!");
							}

							@Override
							public void onSuccess(Person result) {
								Window.alert("Glückwunsch " + vnameBox.getText() +" "+ nnameBox.getText()+"! Sie sind jetzt Teilnehmer bei Prokeko!");
								loadReportGenerator(result.getId());
							}
						});
			}
		}
		
	}
	
	}
