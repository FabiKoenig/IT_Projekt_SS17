package de.hdm.itProjektSS17.client.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class DialogBoxProjektAnzeigen extends DialogBox {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	FlexTable ft_projektAnzeigen = new FlexTable();
	Button btn_speichern = new Button("Speichern");
	Button btn_abbrechen = new Button("Abbrechen");
	Button btn_bearbeiten = new Button("Bearbeiten");
	Button btn_loeschen = new Button("Löschen");
	
	Label lbl_projektname = new Label("Projektname:");
	Label lbl_beschreibung = new Label("Beschreibung:");
	Label lbl_startdatum = new Label("Startdatum:");
	Label lbl_enddatum = new Label("Enddatum:");
	Label lbl_projektmarktplatz = new Label("Projektmarktplatz:");
	Label lbl_projektleiter = new Label("Projektleiter:");
	
	TextBox txt_projektname = new TextBox();
	TextArea txta_beschreibung = new TextArea();
	TextBox txt_startdatum = new TextBox();
	TextBox txt_enddatum = new TextBox();
	TextBox txt_projektleiter = new TextBox();
	TextBox txt_projektmarktplatz = new TextBox();
	DateBox db_startdatum = new DateBox();
	DateBox db_enddatum = new DateBox();
	
//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	HorizontalPanel hp = new HorizontalPanel();
	HorizontalPanel hp_bearbeiten = new HorizontalPanel();
	
	public DialogBoxProjektAnzeigen(){
		
		this.setText("Projekt anzeigen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		txt_projektname.setReadOnly(true);
		txta_beschreibung.setReadOnly(true);
		txt_startdatum.setReadOnly(true);
		txt_enddatum.setReadOnly(true);
		txt_projektleiter.setReadOnly(true);
		txt_projektmarktplatz.setReadOnly(true);
		
		hp.add(btn_bearbeiten);
		hp.add(btn_loeschen);
		hp.add(btn_abbrechen);
		
		
		ft_projektAnzeigen.setWidget(1, 0, lbl_projektname);
		ft_projektAnzeigen.setWidget(1, 1, txt_projektname);
		ft_projektAnzeigen.setWidget(2, 0, lbl_beschreibung);
		ft_projektAnzeigen.setWidget(2, 1, txta_beschreibung);
		ft_projektAnzeigen.setWidget(3, 0, lbl_startdatum);
		ft_projektAnzeigen.setWidget(3, 1, txt_startdatum);
		ft_projektAnzeigen.setWidget(4, 0, lbl_enddatum);
		ft_projektAnzeigen.setWidget(4, 1, txt_enddatum);
		ft_projektAnzeigen.setWidget(5, 0, lbl_projektmarktplatz);
		ft_projektAnzeigen.setWidget(5, 1, txt_projektmarktplatz);
		ft_projektAnzeigen.setWidget(6, 0, lbl_projektleiter);
		ft_projektAnzeigen.setWidget(6, 1, txt_projektleiter);
		
		ft_projektAnzeigen.setWidget(10, 1, hp);
		
		setWidget(ft_projektAnzeigen);
		
		btn_bearbeiten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				setText("Projekt bearbeiten...");
				txt_projektname.setReadOnly(false);
				txta_beschreibung.setReadOnly(false);
				
				ft_projektAnzeigen.setWidget(1, 0, lbl_projektname);
				ft_projektAnzeigen.setWidget(1, 1, txt_projektname);
				ft_projektAnzeigen.setWidget(2, 0, lbl_beschreibung);
				ft_projektAnzeigen.setWidget(2, 1, txta_beschreibung);
				ft_projektAnzeigen.setWidget(3, 0, lbl_startdatum);
				ft_projektAnzeigen.setWidget(3, 1, db_startdatum);
				ft_projektAnzeigen.setWidget(4, 0, lbl_enddatum);
				ft_projektAnzeigen.setWidget(4, 1, db_enddatum);
				ft_projektAnzeigen.setWidget(5, 0, lbl_projektmarktplatz);
				ft_projektAnzeigen.setWidget(5, 1, txt_projektmarktplatz);
				ft_projektAnzeigen.setWidget(6, 0, lbl_projektleiter);
				ft_projektAnzeigen.setWidget(6, 1, txt_projektleiter);
				
				
				hp_bearbeiten.add(btn_speichern);
				hp_bearbeiten.add(btn_abbrechen);
				
				ft_projektAnzeigen.setWidget(10, 1, hp_bearbeiten);
//				try {
//					Projekt projekt = new Projekt();
//					projekt.setName(txt_projektname.getText());
//					projekt.setBeschreibung(txta_beschreibung.getText());
//					projekt.setStartdatum(format.parse(txt_startdatum.getText()));
//					projekt.setEnddatum(format.parse(txt_enddatum.getText()));
//					
//					projektmarktplatzVerwaltung.saveProjekt(projekt, new AsyncCallback<Void>() {
//						
//						@Override
//						public void onSuccess(Void result) {
//							Window.alert("Projekt erfolgreich gespeichert.");
//						}
//						
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							
//						}
//					});
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		
		
	}
	
	
}
