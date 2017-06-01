package de.hdm.itProjektSS17.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.ShowcaseReport;
import de.hdm.itProjektSS17.client.gui.IdentityMarketChoice;
import de.hdm.itProjektSS17.client.gui.Impressum;
import de.hdm.itProjektSS17.client.gui.StartseiteForm;

public class NavigationReport extends StackPanel {

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	//Anlegen der Panels
	VerticalPanel startseitePanel = new VerticalPanel();
	VerticalPanel reportPanel = new VerticalPanel();
	
	
	//Anlegen der Hyperlinks
	Hyperlink home = new Hyperlink();
	
	//Anlegen der Buttons
	Button homeButton = new Button("Home");
	Button agbButton = new Button("AGB");
	Button impressumButton = new Button("Impressum");
	
	Button showAusschreibungenButton = new Button("Alle Ausschreibungen");
	Button showAusschreibungMatchsPartnerprofilButton = new Button("Ausschreibungen zu Partnerprofil");
	Button showAllBewerbungenFromUserButton = new Button("Bewerbungen zu eigenen Ausschreibungen");
	Button showBewerbungenAusschreibungenFromUserButton = new Button("Eigene Bewerbungen anzeigen");
	Button showProjektverflechtungenButton = new Button("Projektverflechtungen anzeigen");
	Button showFanInFanOutAnalyseButton = new Button("Fan-in/Fan-out Analyse");
	
	public NavigationReport(){
		
	//Zusammensetzen des startseitePanels
		startseitePanel.add(homeButton);
		homeButton.setWidth("200px");
		homeButton.setStylePrimaryName("navi-button");
		startseitePanel.add(impressumButton);
		impressumButton.setWidth("200px");
		impressumButton.setStylePrimaryName("navi-button");
		startseitePanel.add(agbButton);
		agbButton.setWidth("200px");
		agbButton.setStylePrimaryName("navi-button");
		startseitePanel.setSpacing(5);
	
	//Zusammensetzen des reportPanels	
		reportPanel.add(showAusschreibungenButton);
		showAusschreibungenButton.setWidth("200px");
		showAusschreibungenButton.setStylePrimaryName("navi-Button");
		reportPanel.add(showAusschreibungMatchsPartnerprofilButton);
		showAusschreibungMatchsPartnerprofilButton.setWidth("200px");
		showAusschreibungMatchsPartnerprofilButton.setStylePrimaryName("navi-Button");
		reportPanel.add(showAllBewerbungenFromUserButton);
		showAllBewerbungenFromUserButton.setWidth("200px");
		showAllBewerbungenFromUserButton.setStylePrimaryName("navi-Button");
		reportPanel.add(showBewerbungenAusschreibungenFromUserButton);
		showBewerbungenAusschreibungenFromUserButton.setWidth("200px");
		showBewerbungenAusschreibungenFromUserButton.setStylePrimaryName("navi-Button");
		reportPanel.add(showProjektverflechtungenButton);
		showProjektverflechtungenButton.setWidth("200px");
		showProjektverflechtungenButton.setStylePrimaryName("navi-Button");
		reportPanel.add(showFanInFanOutAnalyseButton);
		showFanInFanOutAnalyseButton.setWidth("200px");
		showFanInFanOutAnalyseButton.setStylePrimaryName("navi-Button");
		reportPanel.setSpacing(5);
		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(startseitePanel, "Startseite");
		this.add(reportPanel, "Report Locator");
		
		
	//Anlegen der ClickHandler
		homeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new StartseiteFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		impressumButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(new Impressum());
			}
		});	
		
		showAusschreibungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new AllAusschreibungFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showAusschreibungMatchsPartnerprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new AusschreibungMatchsPartnerprofilFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showAllBewerbungenFromUserButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new AllBewerbungFromUserFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showBewerbungenAusschreibungenFromUserButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new BewerbungAusschreibungFromUserFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showProjektverflechtungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new ProjektverflechtungFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		showFanInFanOutAnalyseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				ShowcaseReport showcaseReport = new FanInFanOutFormReport();
				RootPanel.get("DetailsReport").clear();
				RootPanel.get("DetailsReport").add(showcaseReport);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
	}
	
	public static ClickHandler getCurrentClickHandler() {
		return currentClickHandler;
	}

	public static ClickEvent getCurrentClickEvent() {
		return currentClickEvent;
	}

	public static void setCurrentClickHandler(ClickHandler c){
		currentClickHandler = c;
	}
	public static void setCurrentClickEvent(ClickEvent e){
		currentClickEvent = e;
	}

	public static void reload(){
		currentClickHandler.onClick(currentClickEvent);
	}
	
}
