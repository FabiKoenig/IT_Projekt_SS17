package de.hdm.itProjektSS17.client.gui;

import org.cyberneko.html.HTMLScanner.CurrentEntity;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class Navigation extends StackPanel{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	//Anlegen der Panels
	VerticalPanel startseitePanel = new VerticalPanel();
	VerticalPanel projektlocatorPanel = new VerticalPanel();
	VerticalPanel meineaktivitaetenPanel = new VerticalPanel();
	VerticalPanel einstellungenPanel = new VerticalPanel();
	
	//Anlegen der Hyperlinks
	Hyperlink home = new Hyperlink();
	
	//Anlegen der Buttons
	Button homeButton = new Button("Home");
	Button agbButton = new Button("AGB");
	Button impressumButton = new Button("Impressum");
	Button testButton = new Button("TEST");
	
	Button ausschreibungenButton = new Button("Stellenausschreibungen");
	Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	
	Button meineprojekteButton = new Button("Meine Projekte");
	Button meineausschreibungenButton = new Button("Meine Ausschreibungen");
	Button meinebewerbungenButton = new Button("Meine Bewerbungen");
	Button meineBeteiligungenButton = new Button("Meine Beteiligungen");
	
	Button personaldataButton = new Button("Persönliche Daten");
	Button eigenesprofilButton = new Button("Eigenes Partnerprofil");
	
	
	public Navigation(){
		
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
		
		//Zusammensetzen des projektlocatorPanels
		projektlocatorPanel.add(ausschreibungenButton);
		ausschreibungenButton.setWidth("200px");
		ausschreibungenButton.setStylePrimaryName("navi-button");
		projektlocatorPanel.add(projektmarktplaetzeButton);
		projektmarktplaetzeButton.setWidth("200px");
		projektmarktplaetzeButton.setStylePrimaryName("navi-button");
		projektlocatorPanel.setSpacing(5);
		
		//Zusammensetzung des meineaktivitaetenPanels
		meineaktivitaetenPanel.add(meineprojekteButton);
		meineprojekteButton.setWidth("200px");
		meineprojekteButton.setStylePrimaryName("navi-button");
		meineaktivitaetenPanel.add(meineausschreibungenButton);
		meineausschreibungenButton.setWidth("200px");
		meineausschreibungenButton.setStylePrimaryName("navi-button");
		meineaktivitaetenPanel.add(meinebewerbungenButton);
		meinebewerbungenButton.setWidth("200px");
		meinebewerbungenButton.setStylePrimaryName("navi-button");
		meineaktivitaetenPanel.add(meineBeteiligungenButton);
		meineBeteiligungenButton.setWidth("200px");
		meineBeteiligungenButton.setStylePrimaryName("navi-button");
		meineaktivitaetenPanel.setSpacing(5);
		
		//Zusammensezuung des einstellungenPanels
		einstellungenPanel.add(personaldataButton);
		personaldataButton.setWidth("200px");
		personaldataButton.setStylePrimaryName("navi-button");
		einstellungenPanel.add(eigenesprofilButton);
		eigenesprofilButton.setWidth("200px");
		eigenesprofilButton.setStylePrimaryName("navi-button");
		einstellungenPanel.setSpacing(5);
		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(startseitePanel, "Startseite");
		this.add(projektlocatorPanel, "Projekt Locator");
		this.add(meineaktivitaetenPanel, "Meine Aktivitäten");
		this.add(einstellungenPanel, "Einstellungen");
		
		
		//Clickhandler für den Impressum-Button
		impressumButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new Impressum());
			}
		});		
		
		personaldataButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				currentClickHandler=this;
				currentClickEvent=event;
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				//Auslesen des Index, der in der ListBox der agierenden Organisationseinheit ausgewählt ist
				Organisationseinheit selectedIdentity = IdentityMarketChoice.getSelectedIdentityAsObject();
				//Window.alert(selectedIdentity.toString());
				//Falls der Index 0 ist, dann ist es eine Person und es wird die PersonProfilAnzeigenForm geladen
				if(selectedIdentity instanceof Person){
					Showcase showcase = new PersonProfilAnzeigenForm();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				//Falls der Index 1 ist, dann ist ein Team aktiv und es wird die TeamProfilAnzeigenForm geladen.	
				}else if(selectedIdentity instanceof Team){
					Showcase showcase = new TeamProfilAnzeigenForm();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				//Falls der Index 2 ist, dann ist ein Unternehmen aktiv und es wird die UnternehmenProfilAnzeigenForm geladen.
				}else if(selectedIdentity instanceof Unternehmen){
					Showcase showcase = new UnternehmenProfilAnzeigenForm();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
				}
			}
		});
		
		eigenesprofilButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				Showcase showcase = new MeinPartnerprofilForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meineprojekteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.activateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				Showcase showcase = new MeineProjektForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meinebewerbungenButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				IdentityMarketChoice.activateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				Showcase showcase = new MeineBewerbungenForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		ausschreibungenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.activateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				Showcase showcase = new StellenauschreibungForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		projektmarktplaetzeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.setOwnOrgUnitToZero();
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new ProjektmarktplatzForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		homeButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				IdentityMarketChoice.deactivateProjectMarkets();
				IdentityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new StartseiteForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}
		});
		
		meineBeteiligungenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				IdentityMarketChoice.activateProjectMarkets();
				IdentityMarketChoice.activateOrgUnits();
				Showcase showcase = new BeteiligungenForm();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
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
