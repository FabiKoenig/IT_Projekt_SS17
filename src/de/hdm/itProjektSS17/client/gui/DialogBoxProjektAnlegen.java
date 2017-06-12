package de.hdm.itProjektSS17.client.gui;




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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class DialogBoxProjektAnlegen extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_projektErstellen = new FlexTable();
	Button btn_ok = new Button("OK");
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
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public DialogBoxProjektAnlegen(final IdentityMarketChoice identityMarketChoice, Navigation navigation) {
	this.navigation=navigation;
	this.identityMarketChoice=identityMarketChoice;
	
	this.setText("Projekt anlegen...");
	this.setAnimationEnabled(false);
	this.setGlassEnabled(true);
	
	btn_ok.setStylePrimaryName("cell-btn");
	btn_abbrechen.setStylePrimaryName("cell-btn");
	hp.add(btn_ok);
	hp.add(btn_abbrechen);
	
	
	
	
	
	
	btn_ok.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			if (db_enddatum.getValue().before(db_startdatum.getValue())) {
				Window.alert("Das Enddatum muss nach dem Startdatum erfolgen.");
			}
			else if (txt_projektname.getText().isEmpty()) {
				Window.alert("Bitte geben Sie einen Projektnamen für Ihr Projekt ein.");
			}
			else if (txta_beschreibung.getText().isEmpty()) {
				Window.alert("Bitte geben Sie eine Beschreibung für Ihr Projekt ein.");
			}
			else {
				projektmarktplatzVerwaltung.getPersonById(identityMarketChoice.getSelectedIdentityId(), new GetPersonCallback());
		
			}
			
		}
	});
	
	
	btn_abbrechen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
			
		}
	});
	
	
	

	
	
	txta_beschreibung.setPixelSize(200, 200);
	
	
	
	DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
	
	
	db_startdatum.setFormat(new DateBox.DefaultFormat(dateformat));
	
	
	
	db_enddatum.setFormat(new DateBox.DefaultFormat(dateformat));
	
	


	ft_projektErstellen.setWidget(1, 0, lbl_projektname);
	ft_projektErstellen.setWidget(1, 1, txt_projektname);
	ft_projektErstellen.setWidget(2, 0, lbl_beschreibung);
	ft_projektErstellen.setWidget(2, 1, txta_beschreibung);
	ft_projektErstellen.setWidget(3, 0, lbl_startdatum);
	ft_projektErstellen.setWidget(3, 1, db_startdatum);
	ft_projektErstellen.setWidget(4, 0, lbl_enddatum);
	ft_projektErstellen.setWidget(4, 1, db_enddatum);
	
	ft_projektErstellen.setWidget(7, 1, hp);
	
	setWidget(ft_projektErstellen);
	
	}
	
	

	
	
	
	private class GetPersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				
				projektmarktplatzVerwaltung.createProjekt(db_startdatum.getValue(), db_enddatum.getValue(), txt_projektname.getText(), txta_beschreibung.getText(), result.getId(), identityMarketChoice.getSelectedProjectMarketplaceId(), new CreateProjektCallback());		
			}
		}
		
		
	}
	
		
	private class CreateProjektCallback implements AsyncCallback<Projekt> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Hinzufügen des Projekts ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Projekt result) {
			final Projekt p = result;
			int umfang = (int)( (result.getEnddatum().getTime() - result.getStartdatum().getTime()) / (1000 * 60 * 60 * 24) ) + 1;
			projektmarktplatzVerwaltung.createBeteiligungProjektleiter(umfang, result.getStartdatum(), result.getEnddatum(), identityMarketChoice.getSelectedIdentityId(), result.getId(), new AsyncCallback<Beteiligung>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Die Beteiligung konnte nicht angelegt werden!");
				}

				@Override
				public void onSuccess(Beteiligung result) {
					Window.alert("Das Projekt " + "\"" +p.getName()+ "\"" + " Beteiligung wurde erfolgreich angelegt!");
					hide();
					navigation.reload();
				}
			});
		}
		
	}
	
	
	
}