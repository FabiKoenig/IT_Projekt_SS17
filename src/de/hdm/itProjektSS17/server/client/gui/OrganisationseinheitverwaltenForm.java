package de.hdm.itProjektSS17.server.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.GetPersonalInformation;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;


public class OrganisationseinheitverwaltenForm implements EntryPoint {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	Organisationseinheit orgaToDisplay = null;

			
	public void onModuleLoad() {
		
		
	VerticalPanel unPanel = new VerticalPanel();
	VerticalPanel tePanel = new VerticalPanel();
		
		
		//Button zum erstellen eines neuen Unternehmens
	 final Button unternehmenErstellenButton = new Button("Unternehmen erstellen");

	    unternehmenErstellenButton.setStylePrimaryName("organisationseinheitverwalten-button");
	    unPanel.add(unternehmenErstellenButton);
	    unternehmenErstellenButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//öffnet ein neues Popup Fenster in der selben seite
				 Window.open("http://google.com", "self", null);
	        }
	    });
	RootPanel.get().add(unternehmenErstellenButton);
	
	
	
	//Button um einem Unternehmen beizutreten
		final Button unternehmenBeitretenButton = new Button("Unternehmen beitreten");

	    unternehmenBeitretenButton.setStylePrimaryName("organisationseinheitverwalten-button");
	    unPanel.add(unternehmenBeitretenButton);
	    unternehmenBeitretenButton.addClickHandler(new ClickHandler() {
	    	
	    	public void onClick(ClickEvent event) {
	    		//öffnet ein neues Popup Fenster in der selben seite
	    		Window.open("http://google.com", "self", null);
	    		}
	    	});
	    RootPanel.get().add(unternehmenBeitretenButton);
	
	
	
	//Button um aus einem Unternehmen auszutreten
		final Button unternehmenAustretenButton = new Button("Unternehmen austreten");

		    unternehmenAustretenButton.setStylePrimaryName("organisationseinheitverwalten-button");
		    unPanel.add(unternehmenAustretenButton);
		    unternehmenAustretenButton.addClickHandler(new ClickHandler() {
		    	
		    	public void onClick(ClickEvent event) {
		    		//öffnet ein neues Popup Fenster in der selben seite
		    		Window.open("http://google.com", "self", null);
		    	}
		    });
		RootPanel.get().add(unternehmenAustretenButton);


	
	
	//Button um ein Team zu erstellen
		final Button teamErstellenButton = new Button("Team erstellen");

		    teamErstellenButton.setStylePrimaryName("organisationseinheitverwalten-button");
		    tePanel.add(teamErstellenButton);
		    teamErstellenButton.addClickHandler(new ClickHandler() {
		    	
		    	public void onClick(ClickEvent event) {
		    		//öffnet ein neues Popup Fenster in der selben seite
		    		Window.open("http://google.com", "self", null);
		    	}
		    });
		RootPanel.get().add(teamErstellenButton);
		
		
		
	//Button um einem Team beizutreten
		final Button teamBeitretenButton = new Button("Team beitreten");

		    teamBeitretenButton.setStylePrimaryName("organisationseinheitverwalten-button");
		    tePanel.add(teamBeitretenButton);
		    teamBeitretenButton.addClickHandler(new ClickHandler() {
		    	
		    	public void onClick(ClickEvent event) {
		    		//öffnet ein neues Popup Fenster in der selben seite
		    		Window.open("http://google.com", "self", null);
		    	}
		    });
		RootPanel.get().add(teamBeitretenButton);
		
		
		
		
	//Button um einem Team zu auszutreten
		final Button teamAustretenButton = new Button("Team austreten");

		    teamAustretenButton.setStylePrimaryName("organisationseinheitverwalten-button");
		    tePanel.add(teamAustretenButton);
		    teamAustretenButton.addClickHandler(new ClickHandler() {
		    	
		    	public void onClick(ClickEvent event) {
		    		//öffnet ein neues Popup Fenster in der selben seite
		    		Window.open("http://google.com", "self", null);
		    	}
		    });
		RootPanel.get().add(teamAustretenButton);
	}
}
