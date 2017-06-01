package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class DialogBoxProjektBearbeiten extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();

	FlexTable ft_projektBearbeiten = new FlexTable();
	Button btn_speichern = new Button("Speichern");
	Button btn_abbrechen = new Button("Abbrechen");
	Label lbl_projektname = new Label("Projektname: ");
	TextBox txt_projektname = new TextBox();
	Label lbl_beschreibung = new Label("Beschreibung: ");
	TextArea txta_beschreibung = new TextArea();
	Label lbl_startdatum = new Label("Startdatum: ");
	DateBox db_startdatum = new DateBox();
	Label lbl_enddatum = new Label("Enddatum: ");
	DateBox db_enddatum = new DateBox();
	DatePicker datepicker = new DatePicker();
	HorizontalPanel hp = new HorizontalPanel();
	
	public DialogBoxProjektBearbeiten(Projekt p) {
		this.setText("Projekt bearbeiten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		

		final Projekt projekt = p; 
		
		
		
		
		hp.add(btn_speichern);
		hp.add(btn_abbrechen);
		
		txta_beschreibung.setPixelSize(200, 200);
		DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
		
		db_startdatum.setFormat(new DateBox.DefaultFormat(dateformat));
		db_enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
		
		ft_projektBearbeiten.setWidget(1, 0, lbl_projektname);
		ft_projektBearbeiten.setWidget(1, 1, txt_projektname);
		ft_projektBearbeiten.setWidget(2, 0, lbl_beschreibung);
		ft_projektBearbeiten.setWidget(2, 1, txta_beschreibung);
		ft_projektBearbeiten.setWidget(3, 0, lbl_startdatum);
		ft_projektBearbeiten.setWidget(3, 1, db_startdatum);
		ft_projektBearbeiten.setWidget(4, 0, lbl_enddatum);
		ft_projektBearbeiten.setWidget(4, 1, db_enddatum);
		ft_projektBearbeiten.setWidget(7, 1, hp);
	
		txt_projektname.setText(p.getName());
		txta_beschreibung.setText(p.getBeschreibung());
		db_startdatum.setValue(p.getStartdatum());
		db_enddatum.setValue(p.getEnddatum());
		
		
		
		
		btn_speichern.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
			projekt.setName(txt_projektname.getText());
			projekt.setBeschreibung(txta_beschreibung.getText());
			projekt.setStartdatum(db_startdatum.getValue());
			projekt.setEnddatum(db_enddatum.getValue());
				
			// TODO
			if (db_enddatum.getValue().before(db_startdatum.getValue())) {
				Window.alert("Das Enddatum muss nach dem Startdatum erfolgen!");
			}else if (txt_projektname.getText().isEmpty()) {
				Window.alert("Bitte geben Sie einen Projektnamen für Ihr Projekt ein.");
			}
			else if (txta_beschreibung.getText().isEmpty()) {
				Window.alert("Bitte geben Sie eine Beschreibung für Ihr Projekt ein.");
			}
			else {
				projektmarktplatzVerwaltung.saveProjekt(projekt, new SaveProjektCallback());	
				
			}
				
				
			}
		});
		
		btn_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		setWidget(ft_projektBearbeiten);
	}
	

	private class SaveProjektCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Speichern des Projektes schlug fehl. Versuchen Sie es erneut!");
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Projekt wurde erfolgreich gespeichert.");
			hide();
			Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
			
		}
		
	}
	
}


