package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;


public class MeineProjektForm extends Showcase{

	
	protected String getHeadlineText(){
		return "Meine Projekte";
	}
	
	
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Label lbl_valueProjektname = new Label();
		Label lbl_valueBeschreibung = new Label();
		Label lbl_valueStartdatum = new Label();
		Label lbl_valueEnddatum = new Label();
		Label lbl_valueProjektleiter = new Label();
		Label lbl_valueProjektmarktplatz = new Label();
		
		
		
		HorizontalPanel panel_projekte = new HorizontalPanel();
		this.add(panel_projekte);
		
		Button btn_eigeneProjekte = new Button("Eigene Projekte");
		//btn_eigeneProjekte.addClickHandler(new EigeneProjekteClickHandler());
		panel_projekte.add(btn_eigeneProjekte);
		
		Button btn_beteiligungen = new Button("Meine Beteiligungen");
	//	btn_beteiligungen.addClickHandler();
		panel_projekte.add(btn_beteiligungen);
		
		Grid projektGrid = new Grid(7 , 7);
		this.add(projektGrid);
		
		Label lbl_projektName = new Label("Projektname");
		projektGrid.setWidget(1, 0, lbl_projektName);
		projektGrid.setWidget(1, 1, lbl_valueProjektname);
		
		Label lbl_beschreibung = new Label("Beschriebung");
		projektGrid.setWidget(1, 1, lbl_beschreibung);
		projektGrid.setWidget(2, 1, lbl_valueBeschreibung);
		
		Label lbl_startdatum = new Label("Startdatum");
		projektGrid.setWidget(1, 2, lbl_startdatum);
		projektGrid.setWidget(3, 1, lbl_valueStartdatum);
		
		Label lbl_enddatum = new Label("Enddatum");
		projektGrid.setWidget(1, 3, lbl_enddatum);
		projektGrid.setWidget(4, 1, lbl_valueEnddatum);
		
		Label lbl_projektleiter = new Label("Projektleiter");
		projektGrid.setWidget(1, 4, lbl_projektleiter);
		projektGrid.setWidget(5, 1, lbl_valueProjektleiter);
		
		Label lbl_projektmarktplatz = new Label("Projektmarktplatz");
		projektGrid.setWidget(1, 5, lbl_projektmarktplatz);
		projektGrid.setWidget(6, 1, lbl_valueProjektmarktplatz);
		
		Button btn_projektAnlegen = new Button("Projekt anlegen");
		btn_projektAnlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				DialogBoxProjektAnlegen dpa = new DialogBoxProjektAnlegen();
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				dpa.setPopupPosition(left, top);
				dpa.show();
				
			}
		});
		this.add(btn_projektAnlegen);
		
	}
	
	
	
}
