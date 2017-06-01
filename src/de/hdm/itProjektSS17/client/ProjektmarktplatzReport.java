package de.hdm.itProjektSS17.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.Navigation;
import de.hdm.itProjektSS17.client.gui.report.NavigationReport;
import de.hdm.itProjektSS17.shared.LoginServiceAsync;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;



public class ProjektmarktplatzReport implements EntryPoint {

	
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
		
		reportGenerator = ClientsideSettings.getReportGenerator();
		loginService = ClientsideSettings.getLoginService();
		
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
		    RootPanel.get("Navigator").add(new NavigationReport());
			
		    
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
	}
