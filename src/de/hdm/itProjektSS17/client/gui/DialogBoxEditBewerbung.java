package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DialogBoxEditBewerbung extends DialogBox {
	
	public DialogBoxEditBewerbung() {
	
	VerticalPanel vp = new VerticalPanel();
	this.setText("Bewerbung bearbeiten...");
	this.setAnimationEnabled(false);
	this.setGlassEnabled(true);
	

	vp.setPixelSize(400, 600);
	vp.setSpacing(10);
	vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	
	ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	});
	
	abbrechen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			//TODO Auto-generated method stub
			hide();
		}
	});
	
	
	HorizontalPanel hp1 = new HorizontalPanel();
	Label lbl_projektname = new Label("Bewerbungstext: ");
	TextBox txt_projektname = new TextBox();
	txt_projektname.getElement().setPropertyString("placeholder", "Test");
	hp1.add(lbl_projektname);
	hp1.add(txt_projektname);
	vp.add(hp1);
	
	HorizontalPanel hp2 = new HorizontalPanel();
	Label lbl_beschreibung = new Label("Bewerbungstext: ");
	TextArea txta_beschreibung = new TextArea();
	txta_beschreibung.setPixelSize(600, 300);
	txta_beschreibung.getElement().setPropertyString("placeholder", "Geben Sie den neuen Bewerbungstext ein.");
	hp2.add(lbl_beschreibung);
	hp2.add(txta_beschreibung);
	vp.add(hp2);
	
	/*
	
	HorizontalPanel hp4 = new HorizontalPanel();
	Label lbl_enddatum = new Label("Enddatum: ");
	DateBox db_enddatum = new DateBox();
	db_enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
	hp4.add(lbl_enddatum);
	hp4.add(db_enddatum);
	vp.add(hp4);
	*/
	//datepicker.setValue(new Date(), true);
		
	ListBox projektmarktplatz = new ListBox();
	
	vp.add(ok);
	vp.add(abbrechen);
	setWidget(vp);
	
	}

}
