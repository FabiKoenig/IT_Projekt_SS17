package de.hdm.itProjektSS17.client.gui;

import java.math.BigDecimal;
import java.util.Vector;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.gui.BewerbungenAufAusschreibungForm.BewertungBewerbungHybrid;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;

public class DialogBoxBewerbungBewerten extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	//Panels anlegen
	VerticalPanel vp_BewerbungBewertung = new VerticalPanel();
	HorizontalPanel hp_BewerbungBewertung = new HorizontalPanel();
	HorizontalPanel hp_Bewertung = new HorizontalPanel();
	
	//Anlegen der Flextables
	FlexTable ft_Bewerbung = new FlexTable();
	FlexTable ft_Bewertung = new FlexTable();

	//Anlegen der Buttons
	Button bewerberAnnehmenButton = new Button("Bewerber annehmen");
	Button speicherButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	
	//Lables anlegen
	Label lbl_Bewerber = new Label("Bewerber: ");
	Label lbl_Bewerbungstext = new Label("Bewerbungstext: ");
	Label lbl_Stellungnahme = new Label("Stellungnahme");
	Label lbl_Bewertung = new Label("Bewertung: ");
	
	//ListBox, TextBox + -areas anlegen
	TextBox txt_Bewerber = new TextBox();
	TextArea txta_Bewerbungstext = new TextArea();
	TextArea txta_Stellungnahme = new TextArea();
	ListBox lb_Bewertung = new ListBox();
	String[] bewertungen = { "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "1,0" };
	
	public DialogBoxBewerbungBewerten(BewertungBewerbungHybrid bbh) {
		this.setText("Bewerbung bewerten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		for (int i = 0; i < bewertungen.length; i++) {
			lb_Bewertung.addItem(bewertungen[i]);
		}
		
		
		BewertungBewerbungHybrid bewertungBewerbungHybrid = bbh;
		
	
		hp_Bewertung.add(speicherButton);
		hp_Bewertung.add(abbrechenButton);
		//Erstellen der FlexTable
				ft_Bewerbung.setWidget(1, 0, lbl_Bewerber);
				ft_Bewerbung.setWidget(1, 1, txt_Bewerber);
				ft_Bewerbung.setWidget(2, 0, lbl_Bewerbungstext);
				ft_Bewerbung.setWidget(2, 1, txta_Bewerbungstext);
				ft_Bewerbung.setWidget(3, 1, bewerberAnnehmenButton);
				
				ft_Bewertung.setWidget(1, 0, lbl_Bewertung);
				ft_Bewertung.setWidget(1, 1, lb_Bewertung);
				ft_Bewertung.setWidget(2, 0, lbl_Stellungnahme);
				ft_Bewertung.setWidget(2, 1, txta_Stellungnahme);
				ft_Bewertung.setWidget(3, 1, hp_Bewertung);
//				ft_Bewertung.setWidget(3, 1, abbrechenButton);
				
				txt_Bewerber.setEnabled(false);
				txta_Bewerbungstext.setEnabled(false);
				txta_Bewerbungstext.setPixelSize(200, 200);
				txta_Stellungnahme.setPixelSize(200, 200);
				
				txt_Bewerber.setText(bewertungBewerbungHybrid.getBewerber());
				txta_Bewerbungstext.setText(bewertungBewerbungHybrid.getBewerbungstext());
				txta_Stellungnahme.setText(bewertungBewerbungHybrid.getStellungsnahme());
				//lb_Bewertung.s(bewertungBewerbungHybrid.getBewertungWert());
				
		//Panels Widgets zuweisen
				hp_BewerbungBewertung.add(ft_Bewerbung);
				hp_BewerbungBewertung.add(ft_Bewertung);
				vp_BewerbungBewertung.add(hp_BewerbungBewertung);
				setWidget(vp_BewerbungBewertung);

		//ClickHandler anlegen		
				abbrechenButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						hide();
					}
				});
		}
}
