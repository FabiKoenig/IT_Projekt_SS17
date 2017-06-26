package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import org.cyberneko.html.HTMLScanner.CurrentEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.report.AlleAusschreibungenShowcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;


public class Navigation extends StackPanel{
	
	//ClickHandler um zu speichern, welche Auswahl der User zuletzt getroffen hat
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	//Anlegen der Panels
	VerticalPanel startseitePanel = new VerticalPanel();
	VerticalPanel projektlocatorPanel = new VerticalPanel();
	VerticalPanel meineaktivitaetenPanel = new VerticalPanel();
	VerticalPanel einstellungenPanel = new VerticalPanel();
	VerticalPanel reportPanel = new VerticalPanel();

	
	//Anlegen der Hyperlinks
	private Hyperlink home = new Hyperlink();
	private Anchor reportLink = new Anchor("ReportGenerator");
	
	//Anlegen der Buttons
	private Button homeButton = new Button("Home");
	private Button agbButton = new Button("AGB");
	private Button impressumButton = new Button("Impressum");

	private Button ausschreibungenButton = new Button("Offene Ausschreibungen");
	private Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	
	private Button meineprojekteButton = new Button("Meine Projekte");
	private Button meineausschreibungenButton = new Button("Meine Ausschreibungen");
	private Button meinebewerbungenButton = new Button("Meine Bewerbungen");
	private Button meineBeteiligungenButton = new Button("Meine Beteiligungen");
	
	private Button personaldataButton = new Button("Persönliche Daten");
	private Button eigenesprofilButton = new Button("Eigenes Partnerprofil");
	
	private Button reportButton = new Button("Report Generator");
	private IdentityMarketChoice identityMarketChoice = null;
	
	/**
	 * Ein Objekt dieser Klasse stellt ein Navigationsmenü für die Hauptseite zur Verfügung
	 */
	public Navigation(){
		
		//Zusammensetzen des startseitePanels
		startseitePanel.add(homeButton);
		homeButton.setWidth("200px");
		homeButton.setStylePrimaryName("menu-btn");
		startseitePanel.add(impressumButton);
		impressumButton.setWidth("200px");
		impressumButton.setStylePrimaryName("menu-btn");
		//startseitePanel.add(agbButton);
		agbButton.setWidth("200px");
		agbButton.setStylePrimaryName("menu-btn");
		startseitePanel.setSpacing(5);
		
		//Zusammensetzen des projektlocatorPanels
		projektlocatorPanel.add(ausschreibungenButton);
		ausschreibungenButton.setWidth("200px");
		ausschreibungenButton.setStylePrimaryName("menu-btn");
		projektlocatorPanel.add(projektmarktplaetzeButton);
		projektmarktplaetzeButton.setWidth("200px");
		projektmarktplaetzeButton.setStylePrimaryName("menu-btn");
		projektlocatorPanel.setSpacing(5);
		
		//Zusammensetzung des meineaktivitaetenPanels
		meineaktivitaetenPanel.add(meineprojekteButton);
		meineprojekteButton.setWidth("200px");
		meineprojekteButton.setStylePrimaryName("menu-btn");
		meineaktivitaetenPanel.add(meineausschreibungenButton);
		meineausschreibungenButton.setWidth("200px");
		meineausschreibungenButton.setStylePrimaryName("menu-btn");
		meineaktivitaetenPanel.add(meinebewerbungenButton);
		meinebewerbungenButton.setWidth("200px");
		meinebewerbungenButton.setStylePrimaryName("menu-btn");
		meineaktivitaetenPanel.add(meineBeteiligungenButton);
		meineBeteiligungenButton.setWidth("200px");
		meineBeteiligungenButton.setStylePrimaryName("menu-btn");
		meineaktivitaetenPanel.setSpacing(5);
		
		//Zusammensezuung des einstellungenPanels
		einstellungenPanel.add(personaldataButton);
		personaldataButton.setWidth("200px");
		personaldataButton.setStylePrimaryName("menu-btn");
		einstellungenPanel.add(eigenesprofilButton);
		eigenesprofilButton.setWidth("200px");
		eigenesprofilButton.setStylePrimaryName("menu-btn");
		//einstellungenPanel.add(reportButton);
		
		einstellungenPanel.setSpacing(5);
		
		
		//Zusammensetzung des ReportGeneartorPanels
		reportPanel.add(reportButton);
		reportButton.setStylePrimaryName("menu-btn");
		reportButton.setWidth("200px");
		reportPanel.setSpacing(5);
		
		
		//Setzen der Hauptmenüs
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(startseitePanel, "Startseite");
		this.add(projektlocatorPanel, "Projekt Locator");
		this.add(meineaktivitaetenPanel, "Meine Aktivitäten");
		this.add(einstellungenPanel, "Einstellungen");
		this.add(reportPanel, "ReportGenerator");
		
		
		//Einige ClickHandler welche den Showcase entsprechend zur Auswahl aufrufen
		//Zusätzlich wird die Identitätsleiste je nach Usecase aktiviert/deaktibiert.
		impressumButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.deactivateOrgUnits();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new Impressum());
			}
		});	
		
		agbButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new AGB();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}
		});	
		
		//Spezieller ClickHandler welcher je nach gewählter Identität den passenden Showcase aufruft,
		//um die Eigenschaften der entsprechenden Identität bearbeiten zu können
		personaldataButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				currentClickHandler=this;
				currentClickEvent=event;
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.activateOrgUnits();
				//Auslesen des Index, der in der ListBox der agierenden Organisationseinheit ausgewählt ist
				Organisationseinheit selectedIdentity = identityMarketChoice.getSelectedIdentityAsObject();
				//Falls der Index eine Person ist, dann wird die PersonProfilAnzeigenForm geladen
				if(selectedIdentity instanceof Person){
					Showcase showcase = new PersonProfilAnzeigenForm(identityMarketChoice, getNavigation());
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				//Falls der Index ein Team ist, wird die TeamProfilAnzeigenForm geladen.	
				}else if(selectedIdentity instanceof Team){
					Showcase showcase = new TeamProfilAnzeigenForm(identityMarketChoice, getNavigation());
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				//Falls der Index ein Unternehmen ist, wird die UnternehmenProfilAnzeigenForm geladen.
				}else if(selectedIdentity instanceof Unternehmen){
					Showcase showcase = new UnternehmenProfilAnzeigenForm(identityMarketChoice, getNavigation());
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
				}
			} 
		});
		
		//Die hier folgenden ClickHandler speichern zusätzlich mithilfe 
		//der Variablen currentCLickHandler und currentClickEvent
		//welche Auswahl der User zuletzt getroffen hat
		eigenesprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.activateOrgUnits();
				Showcase showcase = new MeinPartnerprofilForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meineprojekteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.activateProjectMarkets();
				identityMarketChoice.setOwnOrgUnitToZero();
				identityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new MeineProjektForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meinebewerbungenButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				identityMarketChoice.activateProjectMarkets();
				identityMarketChoice.activateOrgUnits();
				Showcase showcase = new MeineBewerbungenForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		ausschreibungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.activateProjectMarkets();
				identityMarketChoice.activateOrgUnits();
				Showcase showcase = new StellenauschreibungForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		projektmarktplaetzeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				identityMarketChoice.setOwnOrgUnitToZero();
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new ProjektmarktplatzForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		homeButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				identityMarketChoice.deactivateProjectMarkets();
				identityMarketChoice.deactivateOrgUnits();				
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new StartseiteForm());
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meineBeteiligungenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				identityMarketChoice.activateProjectMarkets();
				identityMarketChoice.activateOrgUnits();
				Showcase showcase = new BeteiligungenForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meineausschreibungenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				identityMarketChoice.setOwnOrgUnitToZero();
				identityMarketChoice.deactivateOrgUnits();
				identityMarketChoice.deactivateProjectMarkets();
				Showcase showcase = new MeineAusschreibungenForm(identityMarketChoice, getNavigation());
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
				
			}
		});
		
		reportButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				reportLink.setHref(GWT.getHostPageBaseURL()+"ProjektmarktplatzReports.html");
				Window.open(reportLink.getHref(), "_self", "");
				
			}
		});

	}
	

	/**
	 * Liefert das ClickHandler Objekt anhand der letzten Menüauswahl zurück
	 */
	public ClickHandler getCurrentClickHandler() {
		return currentClickHandler;
	}

	/**
	 * Liefert das ClickEvent Objekt anhand der letzten Menüauswahl zurück
	 */
	public ClickEvent getCurrentClickEvent() {
		return currentClickEvent;
	}

	/**
	 * Überschreibt den zuletzt gespeicherten ClickHandler
	 * @param c
	 */
	public void setCurrentClickHandler(ClickHandler c){
		currentClickHandler = c;
	}
	/**
	 * Überschreibt das zuletzt gespeicherte ClickEvent
	 * @param c
	 */
	public void setCurrentClickEvent(ClickEvent e){
		currentClickEvent = e;
	}

	/**
	 * Aktualisiert den letzten Showcase anhand des zuletzt geklickten Buttons
	 */
	public void reload(){
		currentClickHandler.onClick(currentClickEvent);
	}
	
	/**
	 * Übergibt das eigene Navigation-Objekt
	 */
	public Navigation getNavigation(){
		return this;
	}
	
	/**
	 * Liefert die Identitätsleiste und Projektmarktplatzleiste zurück
	 */
	public IdentityMarketChoice getIdentityMarketChoice(){
		return identityMarketChoice;
	}
	
	/**
	 * Überschreibt das IdentityMarketChoice Objekt anhand des übergebenen Wertes
	 * @param identityMarketChoice
	 */
	public void setIdentityMarketChoice(IdentityMarketChoice identityMarketChoice){
		this.identityMarketChoice=identityMarketChoice;
	}

	/**
	 * Gibt den Projektmarktplätze Button zurück
	 */
	public Button getProjektmarktplaetzeButton() {
		return projektmarktplaetzeButton;
	}
	
	
	
}
