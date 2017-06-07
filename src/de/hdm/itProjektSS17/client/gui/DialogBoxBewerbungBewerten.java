package de.hdm.itProjektSS17.client.gui;

import java.awt.event.WindowAdapter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.BewerbungenAufAusschreibungForm.BewertungBewerbungHybrid;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class DialogBoxBewerbungBewerten extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	final BewertungBewerbungHybrid bewertungBewerbungHybrid;
	
	//Panels anlegen
	VerticalPanel vp_BewerbungBewertung = new VerticalPanel();
	HorizontalPanel hp_Button = new HorizontalPanel();
	HorizontalPanel hp_BewerbungBewertung = new HorizontalPanel();
	HorizontalPanel hp_Bewertung = new HorizontalPanel();
	
	//Anlegen der Flextables
	FlexTable ft_Bewerbung = new FlexTable();
	FlexTable ft_Bewertung = new FlexTable();

	//Anlegen der Buttons
	Button bewerberAnnehmenButton = new Button("Annehmen");
	Button bewerberAblehnenButton = new Button("Ablehnen");
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
	
	//Konstruktor erstellen
	public DialogBoxBewerbungBewerten(BewertungBewerbungHybrid bbh) {
		this.setText("Bewerbung bewerten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
		

		bewertungBewerbungHybrid = bbh;
		
		//Array begüllen
		for (int i = 0; i < bewertungen.length; i++) {
			lb_Bewertung.addItem(bewertungen[i]);
		}

		//Buttons dem HorizontalPanel hinzufügen
		hp_Bewertung.add(speicherButton);
		hp_Bewertung.add(abbrechenButton);
		hp_Button.add(bewerberAnnehmenButton);
		hp_Button.add(bewerberAblehnenButton);
		
		//Buttons stylen
		speicherButton.setStylePrimaryName("navi-button");
		abbrechenButton.setStylePrimaryName("navi-button");
		bewerberAnnehmenButton.setStylePrimaryName("navi-button");
		bewerberAblehnenButton.setStylePrimaryName("navi-button");
		
		//Erstellen der FlexTable
				ft_Bewerbung.setWidget(1, 0, lbl_Bewerber);
				ft_Bewerbung.setWidget(1, 1, txt_Bewerber);
				ft_Bewerbung.setWidget(2, 0, lbl_Bewerbungstext);
				ft_Bewerbung.setWidget(2, 1, txta_Bewerbungstext);
				ft_Bewerbung.setWidget(3, 1, hp_Button);
				
				ft_Bewertung.setWidget(1, 0, lbl_Bewertung);
				ft_Bewertung.setWidget(1, 1, lb_Bewertung);
				ft_Bewertung.setWidget(2, 0, lbl_Stellungnahme);
				ft_Bewertung.setWidget(2, 1, txta_Stellungnahme);
				ft_Bewertung.setWidget(3, 1, hp_Bewertung);
				
				txt_Bewerber.setEnabled(false);
				txta_Bewerbungstext.setEnabled(false);
				txta_Bewerbungstext.setPixelSize(200, 200);
				txta_Stellungnahme.setPixelSize(200, 200);
				
				txt_Bewerber.setText(bewertungBewerbungHybrid.getBewerber());
				txta_Bewerbungstext.setText(bewertungBewerbungHybrid.getBewerbungstext());
				txta_Stellungnahme.setText(bewertungBewerbungHybrid.getStellungsnahme());
				
								
		//Panels Widgets zuweisen
				hp_BewerbungBewertung.add(ft_Bewerbung);
				hp_BewerbungBewertung.add(ft_Bewertung);
				vp_BewerbungBewertung.add(hp_BewerbungBewertung);
				setWidget(vp_BewerbungBewertung);

		//ClickHandler für AbbrechButton anlegen		
				abbrechenButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						hide();
					}
				});
				
		//ClickHandler für SpeicherButton anlegen		
				speicherButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Bewertung bewertung = new Bewertung();
					//Bewertung aus Listbox ziehen und in double umwandeln
						try {
							String numberString = lb_Bewertung.getSelectedItemText();
							Double number = NumberFormat.getDecimalFormat().parse(numberString);
							double number2 = number /10;
							
							bewertung.setWert(number2);
							bewertungBewerbungHybrid.setBewertungWert(number2);
							
						} catch (Exception e) {
							e.getStackTrace();
						}
					//Werte setzen
						bewertung.setId(bewertungBewerbungHybrid.getBewertungId());
						bewertungBewerbungHybrid.setStellungsnahme(txta_Stellungnahme.getText());
						bewertung.setBewerbungId(bewertungBewerbungHybrid.getBewerbungId());
						bewertung.setStellungnahme(bewertungBewerbungHybrid.getStellungsnahme());
						
					//Prüfung ob Bewertung überschrieben oder neu erstellt wird
						if(bewertungBewerbungHybrid.getBewertungId() == 0){
							projektmarktplatzverwaltung.createBewertung(new Date(), bewertung.getStellungnahme(), bewertung.getWert(), bewertung.getBewerbungId(), new CreateBewertungCallback());
						}
						else{
							projektmarktplatzverwaltung.saveBewertung(bewertung, new SaveBewertungCallback());
						}
					}				
				});
				
		//ClickHandler für BewerberAnnehmenButton anlegen		
				bewerberAnnehmenButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Bewerbung b = new Bewerbung();
						b.setId(bewertungBewerbungHybrid.getBewerbungId());
						b.setAusschreibungId(bewertungBewerbungHybrid.getAusschreibungId());
						projektmarktplatzverwaltung.getAusschreibungByBewerbung(b, new GetAusschreibungFromBewerbungCallback() {
						});
					}
				});
				
		//ClickHandler für BewerberAblehnenButton anlegen		
				bewerberAblehnenButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if(bewertungBewerbungHybrid.getBewerbungsstatus() == Bewerbungsstatus.abgelehnt){
							Window.alert("Bewerber wurde bereits abgelehnt!");
							hide();
							Navigation.reload();
						}
						else if(bewertungBewerbungHybrid.getBewerbungsstatus() == Bewerbungsstatus.angenommen){
							Window.alert("Der Bewerber wurde bereits angenommen!");
							hide();
							Navigation.reload();
						}
						else{
						Bewerbung b = new Bewerbung();
						b.setId(bewertungBewerbungHybrid.getBewerbungId());
						b.setStatus(Bewerbungsstatus.abgelehnt);
						projektmarktplatzverwaltung.saveBewerbung(b, new SaveBewerbungCallback());
						Window.alert("Der Bewerber wurde abgelehnt!");
						hide();
						Navigation.reload();
						}
					}
				});
	}			
	

	//Callbacks
	private class SaveBewertungCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Speichern der Bewertung schlug fehl. Versuchen Sie es erneut!");	
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Die Bewertung wurde erfolgreich gespeichert.");
			hide();		
			Navigation.reload();
		}
	}
	
	
	private class SaveBewerbungCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Speichern der Bewertung schlug fehl. Versuchen Sie es erneut!");	
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Die Bewertung wurde erfolgreich gespeichert.");
			hide();
			Navigation.reload();
		}
	}
	
	
	private class CreateBewertungCallback implements AsyncCallback<Bewertung>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Speichern der Bewertung schlug fehl. Versuchen Sie es erneut!");	
		}
		@Override
		public void onSuccess(Bewertung result) {
			Window.alert("Die Bewertung wurde erfolgreich angelegt.");
			hide();
			Navigation.reload();
		}
	}
	
	
	private class GetAusschreibungFromBewerbungCallback implements AsyncCallback<Ausschreibung>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler. Versuchen Sie es erneut.");
		}
		@Override
		public void onSuccess(Ausschreibung result) {
			bewertungBewerbungHybrid.setProjektId(result.getProjektId());
			projektmarktplatzverwaltung.getProjektById(result.getProjektId(),new GetProjektCallback());
			}
		}	
	
	private class GetProjektCallback implements AsyncCallback<Projekt>{
				@Override
				public void onFailure(Throwable caught) {
				}
				@Override
				public void onSuccess(Projekt result) {	
					Bewerbung bewerbung = new Bewerbung();
					if(bewertungBewerbungHybrid.getBewerbungsstatus() == Bewerbungsstatus.angenommen){
						Window.alert("Der Bewerber wurde bereits angenommen!");
					}
					else if(bewertungBewerbungHybrid.getBewerbungsstatus() == Bewerbungsstatus.abgelehnt){
						Window.alert("Der Bewerber wurde bereits abgelehnt!");
					}
					else{
					bewerbung.setId(bewertungBewerbungHybrid.getBewerbungId());
					bewerbung.setStatus(Bewerbungsstatus.angenommen);
					bewerbung.setBewerbungstext(bewertungBewerbungHybrid.getBewerbungstext());
					projektmarktplatzverwaltung.saveBewerbung(bewerbung, new SaveBewerbungCallback());
					try{
						Date date1 = result.getStartdatum();
						Date date2 = result.getEnddatum();
						int umfang = date2.compareTo(date1);
						projektmarktplatzverwaltung.createBeteiligung(umfang, result.getStartdatum(), result.getEnddatum(), bewertungBewerbungHybrid.getBewertungId(), result.getId(), bewertungBewerbungHybrid.getBewertungId(), new BeteiligungErstelltCallback());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	
	class BeteiligungErstelltCallback implements AsyncCallback<Beteiligung>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Projektbeteiligung konnte nicht erstellt werden. Versuchen Sie es erneut!");
		}

		@Override
		public void onSuccess(Beteiligung result) {
			Window.alert("Der Bewerber wurde angenommen!");
			hide();
			Navigation.reload();
		}
	}
}
	
	

