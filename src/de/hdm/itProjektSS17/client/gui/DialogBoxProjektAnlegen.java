package de.hdm.itProjektSS17.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DialogBoxProjektAnlegen extends DialogBox {
	
	public DialogBoxProjektAnlegen() {

	
	VerticalPanel vp = new VerticalPanel();
	this.setText("Projekt anlegen...");
	this.setAnimationEnabled(false);
	this.setGlassEnabled(true);
	

	vp.setPixelSize(400, 600);
	vp.setSpacing(10);
	vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	
	
	
	Button btn_ok = new Button("OK");
	btn_ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
			
		}
	});
	
	Button btn_abbrechen = new Button("Abbrechen");
	btn_abbrechen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
			
		}
	});
	
	HorizontalPanel hp1 = new HorizontalPanel();
	Label lbl_projektname = new Label("Projektname: ");
	TextBox txt_projektname = new TextBox();
	txt_projektname.getElement().setPropertyString("placeholder", "Projektname");
	hp1.add(lbl_projektname);
	hp1.add(txt_projektname);
	vp.add(hp1);
	
	HorizontalPanel hp2 = new HorizontalPanel();
	Label lbl_beschreibung = new Label("Beschreibung: ");
	TextArea txta_beschreibung = new TextArea();
	txta_beschreibung.setPixelSize(200, 200);
	txta_beschreibung.getElement().setPropertyString("placeholder", "Geben Sie die Beschreibung zum Projekt ein.");
	hp2.add(lbl_beschreibung);
	hp2.add(txta_beschreibung);
	vp.add(hp2);
	
	DatePicker datepicker = new DatePicker();
	DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	HorizontalPanel hp3 = new HorizontalPanel();
	Label lbl_startdatum = new Label("Startdatum: ");
	DateBox db_startdatum = new DateBox();
	db_startdatum.setFormat(new DateBox.DefaultFormat(dateformat));
	hp3.add(lbl_startdatum);
	hp3.add(db_startdatum);
	vp.add(hp3);
	
	HorizontalPanel hp4 = new HorizontalPanel();
	Label lbl_enddatum = new Label("Enddatum: ");
	DateBox db_enddatum = new DateBox();
	db_enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
	hp4.add(lbl_enddatum);
	hp4.add(db_enddatum);
	vp.add(hp4);
	
	//datepicker.setValue(new Date(), true);
	
	HorizontalPanel hp5 = new HorizontalPanel();
	Label lbl_projektmarktplatz = new Label("Projektmarktplatz: ");
	ListBox projektmarktplatz = new ListBox();
	hp5.add(lbl_projektmarktplatz);
	hp5.add(projektmarktplatz);
	vp.add(hp5);
	
	HorizontalPanel hp6 = new HorizontalPanel();
	hp6.add(btn_abbrechen);
	hp6.add(btn_ok);
	vp.add(hp6);
	setWidget(vp);
	
	}
}
