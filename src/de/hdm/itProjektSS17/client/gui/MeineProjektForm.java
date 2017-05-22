package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

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
		
		
		HorizontalPanel panel_projekte = new HorizontalPanel();
		this.add(panel_projekte);
		
		Button btn_eigeneProjekte = new Button("Eigene Projekte");
		//btn_eigeneProjekte.addClickHandler(new EigeneProjekteClickHandler());
		panel_projekte.add(btn_eigeneProjekte);
		
		Button btn_beteiligungen = new Button("Meine Beteiligungen");
	//	btn_beteiligungen.addClickHandler();
		panel_projekte.add(btn_beteiligungen);
		
		FlexTable ft_projekteAnzeigen = new FlexTable();
		FlexCellFormatter cellFormatter = ft_projekteAnzeigen.getFlexCellFormatter();
		ft_projekteAnzeigen.setWidth("32em");
		ft_projekteAnzeigen.setCellSpacing(5);
		ft_projekteAnzeigen.setCellPadding(3);
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		ft_projekteAnzeigen.setText(0, 0, "Projektname");
		ft_projekteAnzeigen.setText(0, 1, "Beschreibung");
		ft_projekteAnzeigen.setText(0, 2, "Startdatum");
		ft_projekteAnzeigen.setText(0, 3, "Enddatum");
		ft_projekteAnzeigen.setText(0, 4, "Projektleiter");
		ft_projekteAnzeigen.setText(0, 5, "Projektmarktplatz");
		
		this.add(ft_projekteAnzeigen); 
		
		
		
		
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
