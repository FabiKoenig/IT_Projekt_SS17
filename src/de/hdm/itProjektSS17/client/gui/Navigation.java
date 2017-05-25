package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Navigation extends StackPanel{
	
	
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
	
	Button ausschreibungenButton = new Button("Stellenausschreibungen");
	Button projektmarktplaetzeButton = new Button("Projektmarktplätze");
	
	Button meineprojekteButton = new Button("Meine Projekte");
	Button meineausschreibungenButton = new Button("Meine Ausschreibungen");
	Button meinebewerbungenButton = new Button("Meine Bewerbungen");
	Button meineteilnahmenButton = new Button("Meine Teilnahmen");
	
	Button personaldataButton = new Button("Persönliche Daten");
	Button eigenesprofilButton = new Button("Eigenes Partnerprofil");
	
	
	public Navigation(){
		
		//Zusammensetzen des startseitePanels
		startseitePanel.add(homeButton);
		homeButton.setWidth("190px");
		homeButton.setStylePrimaryName("navi-button");
		startseitePanel.add(impressumButton);
		impressumButton.setWidth("190px");
		impressumButton.setStylePrimaryName("navi-button");
		startseitePanel.add(agbButton);
		agbButton.setWidth("190px");
		agbButton.setStylePrimaryName("navi-button");
		startseitePanel.setSpacing(5);
		
		//Zusammensetzen des projektlocatorPanels
		projektlocatorPanel.add(ausschreibungenButton);
		ausschreibungenButton.setWidth("180px");
		projektlocatorPanel.add(projektmarktplaetzeButton);
		projektmarktplaetzeButton.setWidth("180px");
		projektlocatorPanel.setSpacing(5);
		
		//Zusammensetzung des meineaktivitaetenPanels
		meineaktivitaetenPanel.add(meineprojekteButton);
		meineprojekteButton.setWidth("180px");
		meineaktivitaetenPanel.add(meineausschreibungenButton);
		meineausschreibungenButton.setWidth("180px");
		meineaktivitaetenPanel.add(meinebewerbungenButton);
		meinebewerbungenButton.setWidth("180px");
		meineaktivitaetenPanel.add(meineteilnahmenButton);
		meineteilnahmenButton.setWidth("180px");
		meineaktivitaetenPanel.setSpacing(5);
		
		//Zusammensezuung des einstellungenPanels
		einstellungenPanel.add(personaldataButton);
		personaldataButton.setWidth("180px");
		einstellungenPanel.add(eigenesprofilButton);
		eigenesprofilButton.setWidth("180px");
		einstellungenPanel.setSpacing(5);
		
		this.setWidth("250px");
		this.add(startseitePanel, "Startseite");
		this.add(projektlocatorPanel, "Projekt Locator");
		this.add(meineaktivitaetenPanel, "Meine Aktivitäten");
		this.add(einstellungenPanel, "Einstellungen");
		
		
		
		

		
		
		
	}
	
	
	
	
}
