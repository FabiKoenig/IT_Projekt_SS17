package de.hdm.itProjektSS17.client.gui;

import java.util.Date;

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
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class DialogBoxAusschreibungAnlegen extends DialogBox {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_ausschreibungAnlegen = new FlexTable();
	Button btn_ok = new Button("Ok");
	Button btn_abbrechen = new Button("Abbrechen");
	Label lbl_bezeichnung = new Label("Bezeichnung: ");
	TextBox txt_bezeichnung = new TextBox();
	Label lbl_bewerbungsfrist = new Label("Bewerbungsfrist: ");
	DateBox db_bewerbungsfrist = new DateBox();
	Label lbl_ausschreibungstext = new Label("Ausschreibungstext: ");
	TextArea txta_ausschreibungstext = new TextArea();
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	DatePicker datepicker = new DatePicker();
	HorizontalPanel hp = new HorizontalPanel();
	
	public DialogBoxAusschreibungAnlegen(Projekt p, IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		final Projekt projekt = p;
		this.setText("Ausschreibung anlegen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		hp.add(btn_ok);
		hp.add(btn_abbrechen);
		
		btn_ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (db_bewerbungsfrist.getValue().before(new Date())) {
					Window.alert("Bewerbungsfrist muss ein in der Zukunft liegendes Datum sein!");
				}
				else if (txt_bezeichnung.getText().isEmpty()) {
					Window.alert("Bitte geben Sie eine Bezeichnung für die Stellenausschreibung an!");
				}
				else if (txta_ausschreibungstext.getText().isEmpty()) {
					Window.alert("Bitte geben Sie einen Ausschreibungstext für die Stellenausschreibung an!");
				}
				else if (db_bewerbungsfrist.getValue() == null) {
					Window.alert("Bitte geben Sie eine Bewerbungsfrist für die Stellenausschreibung an!");
				}
				else {
					projektmarktplatzVerwaltung.createPartnerprofil(new PartnerprofilAnlegenCallback(projekt));
				}
			}
		});
		
		btn_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		txta_ausschreibungstext.setPixelSize(200, 200);
		DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
		db_bewerbungsfrist.setFormat(new DateBox.DefaultFormat(dateformat));
		
		ft_ausschreibungAnlegen.setWidget(1, 0, lbl_bezeichnung);
		ft_ausschreibungAnlegen.setWidget(1, 1, txt_bezeichnung);
		ft_ausschreibungAnlegen.setWidget(2, 0, lbl_ausschreibungstext);
		ft_ausschreibungAnlegen.setWidget(2, 1, txta_ausschreibungstext);
		ft_ausschreibungAnlegen.setWidget(3, 0, lbl_bewerbungsfrist);
		ft_ausschreibungAnlegen.setWidget(3, 1, db_bewerbungsfrist);
		ft_ausschreibungAnlegen.setWidget(6, 1, hp);
		
		setWidget(ft_ausschreibungAnlegen);
		
		
		
	}
	
	
	private class PartnerprofilAnlegenCallback implements AsyncCallback<Partnerprofil> {

		private Projekt pro;
		
		public PartnerprofilAnlegenCallback(Projekt p) {
			pro = p;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Partnerprofil result) {
			
			projektmarktplatzVerwaltung.createAusschreibung(txt_bezeichnung.getText(), db_bewerbungsfrist.getValue(), txta_ausschreibungstext.getText(), pro.getId(), identityMarketChoice.getSelectedIdentityId(), result.getId(), new AusschreibungAnlegenCallback());

		}
		
	}
	
	private class AusschreibungAnlegenCallback implements AsyncCallback<Ausschreibung> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Ausschreibung result) {
		
			Window.alert("Das Anlegen der Anschreibung war erfolgreich.");
			hide();
			navigation.reload();
			
		}
		
	}
}
