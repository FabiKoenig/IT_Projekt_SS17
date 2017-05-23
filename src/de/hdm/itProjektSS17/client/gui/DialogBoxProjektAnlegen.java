package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

public class DialogBoxProjektAnlegen extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_projektErstellen = new FlexTable();
	VerticalPanel vp = new VerticalPanel();
	Button btn_ok = new Button("OK");
	Button btn_abbrechen = new Button("Abbrechen");
	HorizontalPanel hp1 = new HorizontalPanel();
	Label lbl_projektname = new Label("Projektname: ");
	TextBox txt_projektname = new TextBox();
	HorizontalPanel hp2 = new HorizontalPanel();
	Label lbl_beschreibung = new Label("Beschreibung: ");
	TextArea txta_beschreibung = new TextArea();
	HorizontalPanel hp3 = new HorizontalPanel();
	Label lbl_startdatum = new Label("Startdatum: ");
	DateBox db_startdatum = new DateBox();
	HorizontalPanel hp4 = new HorizontalPanel();
	Label lbl_enddatum = new Label("Enddatum: ");
	DateBox db_enddatum = new DateBox();
	DatePicker datepicker = new DatePicker();
	HorizontalPanel hp5 = new HorizontalPanel();
	Label lbl_projektmarktplatz = new Label("Projektmarktplatz: ");
	ListBox lb_projektmarktplatz = new ListBox();
	HorizontalPanel hp6 = new HorizontalPanel();
	
	public DialogBoxProjektAnlegen() {

	
	this.setText("Projekt anlegen...");
	this.setAnimationEnabled(false);
	this.setGlassEnabled(true);
	

	vp.setPixelSize(400, 600);
	vp.setSpacing(10);
	vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	
	
	
	
	btn_ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			projektmarktplatzVerwaltung.getPersonById(8, new GetPersonCallback2());
			hide();
			
			Showcase showcase = new MeineProjektForm();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
		}
	});
	
	
	btn_abbrechen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
			
		}
	});
	
	
	txt_projektname.getElement().setPropertyString("placeholder", "Projektname");
	hp1.add(lbl_projektname);
	hp1.add(txt_projektname);
	vp.add(hp1);
	
	
	txta_beschreibung.setPixelSize(200, 200);
	txta_beschreibung.getElement().setPropertyString("placeholder", "Geben Sie die Beschreibung zum Projekt ein.");
	hp2.add(lbl_beschreibung);
	hp2.add(txta_beschreibung);
	vp.add(hp2);
	
	
	DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	
	db_startdatum.setFormat(new DateBox.DefaultFormat(dateformat));
	hp3.add(lbl_startdatum);
	hp3.add(db_startdatum);
	vp.add(hp3);
	
	
	db_enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
	hp4.add(lbl_enddatum);
	hp4.add(db_enddatum);
	vp.add(hp4);
	
	//datepicker.setValue(new Date(), true);
	
	
	hp5.add(lbl_projektmarktplatz);
	hp5.add(lb_projektmarktplatz);
	vp.add(hp5);
	
	
	hp6.add(btn_abbrechen);
	hp6.add(btn_ok);
	vp.add(hp6);
	
	projektmarktplatzVerwaltung.getPersonById(8, new GetPersonCallback());
	
	
	
	setWidget(vp);
	
	}
	
	private class GetProjektmarktplaetzeCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Projektmarktplätze ist fehlgeschlagen!");
			
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			if (result != null) {
				
				for (Projektmarktplatz projektmarktplatz : result) {
					lb_projektmarktplatz.addItem(projektmarktplatz.getBezeichnung());
					
				}
			}
			
		}
		
	}

	private class GetPersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				
				projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(result, new GetProjektmarktplaetzeCallback());

				
				
			}
		}
		
	}
	
	
	private class GetPersonCallback2 implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				
				projektmarktplatzVerwaltung.createProjekt(db_startdatum.getValue(), db_enddatum.getValue(), txt_projektname.getText(), txta_beschreibung.getText(), result.getId(), lb_projektmarktplatz.getSelectedIndex() + 1, new CreateProjektCallback());


				
				
			}
		}
		
	}
	
		
	private class CreateProjektCallback implements AsyncCallback<Projekt> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Das Hinzufügen des Projekts ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Projekt result) {
			

			Window.alert("Projekt erfolgreich hinzugefügt!");
		}
		
	}
	
	
	
}

