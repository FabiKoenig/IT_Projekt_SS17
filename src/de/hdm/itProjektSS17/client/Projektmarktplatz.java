package de.hdm.itProjektSS17.client;


import de.hdm.itProjektSS17.client.gui.PersonProfilAnzeigenForm;
import de.hdm.itProjektSS17.client.gui.StartseiteForm;
import de.hdm.itProjektSS17.client.gui.StellenauschreibungForm;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.MeineBewerbungenForm;
import de.hdm.itProjektSS17.client.gui.MeineProjektForm;
import de.hdm.itProjektSS17.client.gui.Navigation;
import de.hdm.itProjektSS17.shared.FieldVerifier;
import de.hdm.itProjektSS17.shared.LoginServiceAsync;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * Entry point Klasse des Projektmarktplatzes. Dafür benötigen wir die Methode
 * <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {

	
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung;
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
		
		projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		loginService = ClientsideSettings.getLoginService();
		
		//Überprüfen des Login-Status
		//LoginServiceAsync loginService = GWT.create(LoginService.class); 
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
				
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()){
					loadProjektmarktplatz();
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
			RootPanel.get("Details").add(loginLabel);
			RootPanel.get("Navigator").add(loginButton);
			
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
	
		
	
		private void loadProjektmarktplatz(){
			
			//Erstellen des Logout-Links
			signOutLink.setHref(loginInfo.getLogoutUrl());
			
			//ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getBankVerwaltung();
			
			RootPanel.get("Header").add(IdentityMarketChoice.getNavigation());
			//Integer test = IdentityMarketChoice.getNavigation(3).getSelectedIdentityId();

		    RootPanel.get("Navigator").add(Logout);
		    RootPanel.get("Navigator").add(new Navigation());
		    RootPanel.get("Details").add(new StartseiteForm());
			
		    
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
		    
		    ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		}
		
		
	    
//	    final Button findNavButtonProjektmarktplatz = new Button("Projektmarktplatz");
//
//	    findNavButtonProjektmarktplatz.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonProjektmarktplatz);
//	   
//	    findNavButtonProjektmarktplatz.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new GetPersonalInformation();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    // Erstellen Startseite Button
//	    final Button findNavButtonStartseite = new Button("Startseite");
//
//	    findNavButtonStartseite.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonStartseite);
//	    
//	    findNavButtonStartseite.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new GetPersonalInformation();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    
//	    
//	    
//	    // Erstellen Stellenausschreibung Button
//	    final Button findNavButtonStellenausschreibung = new Button("Stellenausschreibung");
//
//	    findNavButtonStellenausschreibung.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonStellenausschreibung);
//	    
//	    findNavButtonStellenausschreibung.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new StellenauschreibungForm();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    
//	    // Erstellen Stellenausschreibung Button
//	    final Button findNavButtonMeineBewerbung = new Button("Meine Bewerbung");
//
//	    findNavButtonMeineBewerbung.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonMeineBewerbung);
//	    
//	    findNavButtonMeineBewerbung.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new MeineBewerbungenForm();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    
//	    
//	    // Erstellen Meine Projekte Button
//	    final Button findNavButtonMeineProjekte = new Button("Meine Projekte");
//
//	    findNavButtonMeineProjekte.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonMeineProjekte);
//	    
//	    findNavButtonMeineProjekte.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new MeineProjektForm();
//				
//				
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//				
//			}
//		});
//	    
//	    
//	    // Erstellen Organisationseinheit Button
//	    final Button findNavButtonOrganisationseinheit= new Button("Organisationseinheit");
//
//	    findNavButtonOrganisationseinheit.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonOrganisationseinheit);
//	    
//	    findNavButtonOrganisationseinheit.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new OrganisationseinheitverwaltenForm();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    
//	    // Erstellen Person Button
//	    final Button findNavButtonProfil= new Button("Profil");
//
//	    findNavButtonProfil.setStylePrimaryName("projektmarktplatz-menubutton");
//
//	    navPanel.add(findNavButtonProfil);
//	    
//	    findNavButtonProfil.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				Showcase showcase = new PersonProfilAnzeigenForm();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//				
//			}
//		});
//	    
//	    //Projekte dieses Marktplatzes
//	    final Button findNavButtonProjekte = new Button("Laufende Projekte");
//	    
//	    findNavButtonProjekte.setStylePrimaryName("projektmarktplatz-menubutton");
//	    
//	    navPanel.add(findNavButtonProjekte);
//
//	    
//	    
//		
//		
//	
	
//
//	public static Showcase getCurrentView() {
//		return currentView;
//	}
//
//	public static void setCurrentView(Showcase currentView) {
//		Projektmarktplatz.currentView = currentView;
//	}
	
	
	
	
}
