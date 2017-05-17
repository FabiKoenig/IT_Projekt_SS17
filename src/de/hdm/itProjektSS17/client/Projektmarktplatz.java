package de.hdm.itProjektSS17.client;

import de.hdm.itProjektSS17.shared.FieldVerifier;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point Klasse des Projektmarktplatzes. Dafür benötigen wir die Methode
 * <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getBankVerwaltung();
		
	    VerticalPanel navPanel = new VerticalPanel();

	    RootPanel.get("Navigator").add(navPanel);
		
	    final Button findCustomerButton = new Button("Finde Kunde");

	    findCustomerButton.setStylePrimaryName("projektmarktplatz-menubutton");

	    navPanel.add(findCustomerButton);
	    
	    
	    findCustomerButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new GetPersonalInformation();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
				
			}
		});
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
	
	}
	
	
}
