package de.hdm.itProjektSS17.client.gui.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.Impressum;
import de.hdm.itProjektSS17.client.gui.Navigation;
import de.hdm.itProjektSS17.client.gui.StartseiteForm;
import de.hdm.itProjektSS17.shared.report.AlleBewerbungenAufEigeneAusschreibungenReport;

public class NavigationReport extends StackPanel {

	private ClickHandler currentClickHandler = null;
	private ClickEvent currentClickEvent = null;
	
	//Anlegen der Panels
	private VerticalPanel startseitePanel = new VerticalPanel();
	private VerticalPanel reportPanel = new VerticalPanel();
	private VerticalPanel einstellungenPanel = new VerticalPanel();
	
	
	//Anlegen der Hyperlinks
	private Anchor prokekoLink = new Anchor();
	
	//Anlegen der Buttons
	private Button prokekoButton = new Button("Prokeko");
	private Button homeButton = new Button("Home");
	private Button impressumButton = new Button("Impressum");
	
	private Button showAusschreibungenButton = new Button("Alle Ausschreibungen");
	private Button showAusschreibungMatchsPartnerprofilButton = new Button("Ausschreibungen zu Partnerprofil");
	private Button showAllBewerbungenFromUserButton = new Button("Bewerbungen zu eigenen Ausschreibungen");
	private Button showBewerbungenAusschreibungenFromUserButton = new Button("Eigene Bewerbungen anzeigen");
	private Button showProjektverflechtungenButton = new Button("Projektverflechtungen anzeigen");
	private Button showFanInFanOutAnalyseButton = new Button("Fan-in/Fan-out Analyse");
	private IdentityChoiceReport identityChoiceReport = null;
	
	/**
	 * Ein Objekt dieser Klasse stellt ein Navigationsmenü für den Report-Generator zur Verfügung
	 */
	public NavigationReport(){
	//Zusammensetzen des startseitePanels
		startseitePanel.add(homeButton);
		homeButton.setWidth("200px");
		homeButton.setStylePrimaryName("menu-btn");
		startseitePanel.add(impressumButton);
		impressumButton.setWidth("200px");
		impressumButton.setStylePrimaryName("menu-btn");
		startseitePanel.setSpacing(5);
	
	//Zusammensetzen des reportPanels	
		reportPanel.add(showAusschreibungenButton);
		showAusschreibungenButton.setWidth("200px");
		showAusschreibungenButton.setStylePrimaryName("menu-btn");
		reportPanel.add(showAusschreibungMatchsPartnerprofilButton);
		showAusschreibungMatchsPartnerprofilButton.setWidth("200px");
		showAusschreibungMatchsPartnerprofilButton.setStylePrimaryName("menu-btn");
		reportPanel.add(showAllBewerbungenFromUserButton);
		showAllBewerbungenFromUserButton.setWidth("200px");
		showAllBewerbungenFromUserButton.setStylePrimaryName("menu-btn");
		reportPanel.add(showBewerbungenAusschreibungenFromUserButton);
		showBewerbungenAusschreibungenFromUserButton.setWidth("200px");
		showBewerbungenAusschreibungenFromUserButton.setStylePrimaryName("menu-btn");
		reportPanel.add(showProjektverflechtungenButton);
		showProjektverflechtungenButton.setWidth("200px");
		showProjektverflechtungenButton.setStylePrimaryName("menu-btn");
		reportPanel.add(showFanInFanOutAnalyseButton);
		showFanInFanOutAnalyseButton.setWidth("200px");
		showFanInFanOutAnalyseButton.setStylePrimaryName("menu-btn");
		reportPanel.setSpacing(5);
		
	//Zusammensetzung des EinstellungPanels
		einstellungenPanel.add(prokekoButton);
		prokekoButton.setWidth("200px");
		prokekoButton.setStylePrimaryName("menu-btn");
		einstellungenPanel.setSpacing(5);
		
		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(startseitePanel, "Startseite");
		this.add(reportPanel, "Report Locator");
		this.add(einstellungenPanel, "Prokeko");
		
		
		//ClickHandler welche den Showcase entsprechend zur Auswahl aufrufen
		//Einige der hier folgenden ClickHandler speichern zusätzlich mithilfe 
		//der Variablen currentCLickHandler und currentClickEvent
		//welche Auswahl der User zuletzt getroffen hat
		homeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(new StartseiteForm());
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		impressumButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(new Impressum());
			}
		});	
//		
		showAusschreibungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new AlleAusschreibungenShowcase();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showAusschreibungMatchsPartnerprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new AusschreibungenZuPartnerprofilShowcase(identityChoiceReport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showAllBewerbungenFromUserButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new AlleBewerungenAufEigeneAusschreibungenShowcase(identityChoiceReport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showBewerbungenAusschreibungenFromUserButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new MeineBewerbungenShowcase(identityChoiceReport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showProjektverflechtungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new ProjektverflechtungenShowcase(identityChoiceReport);
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showFanInFanOutAnalyseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcaseReport = new FanInFanOutShowcase();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		prokekoButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				prokekoLink.setHref(GWT.getHostPageBaseURL()+"IT_Projekt_SS17.html");
				Window.open(prokekoLink.getHref(), "_self", "");
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
	 * Liefert die Identitätsleiste und Projektmarktplatzleiste zurück
	 */
	public void setIdentityChoiceReport(IdentityChoiceReport identityChoiceReport){
		this.identityChoiceReport=identityChoiceReport;
	}
	
}