package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;
import de.hdm.itProjektSS17.shared.bo.Bewertung;

public class BewerbungenAufAusschreibungForm extends VerticalPanel{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	
	CellTable<BewertungBewerbungHybrid> dataGrid = new CellTable<BewertungBewerbungHybrid>();
	Vector<BewertungBewerbungHybrid> hybrid = new Vector<BewertungBewerbungHybrid>();
	Vector<Bewerbung> bewerbungen = new Vector<Bewerbung>();
	Bewertung bewertung = null;
	Button bewerbungBewertenButton = new Button("Bewerbung bewerten");
	Button bewerberZusagenButton = new Button("Bewerber annehmen");
	Button zurueckButton = new Button("Zurück");
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	public BewerbungenAufAusschreibungForm(int ausschreibungId){
		
		
		projektmarktplatzVerwaltung.getBewerbungByForeignAusschreibungId(ausschreibungId, new GetBewerbungenCallback());
		
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Erstellen der TextColumns der CellTable
		TextColumn<BewertungBewerbungHybrid> bewerbungstextColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getBewerbungstext();
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> erstellungsdatumColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getErstellungsdatum().toString();
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> bewerbungsstatusColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getBewerbungsstatus().toString();
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> stellungsnahmeColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getStellungsnahme();
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> bewertungsWertColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				double wert = object.getBewertungWert();
				String stringWert = Double.toString(wert);
				return stringWert;
			}
		};
		
		dataGrid.addColumn(bewerbungstextColumn, "Bewerbungstext");
		dataGrid.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		dataGrid.addColumn(bewerbungsstatusColumn, "Bewerbungsstatus");
		dataGrid.addColumn(stellungsnahmeColumn, "Stellungsnahme");
		dataGrid.addColumn(bewertungsWertColumn, "Bewertung");
		dataGrid.setWidth("100%");
		
		final SingleSelectionModel<BewertungBewerbungHybrid> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		//Hinzufügen der Buttons zum ButtonPanel
		buttonPanel.add(zurueckButton);
		buttonPanel.add(bewerbungBewertenButton);
		buttonPanel.add(bewerberZusagenButton);
		
		
		//Stylen der Buttons
		zurueckButton.setStylePrimaryName("navi-button");
		bewerbungBewertenButton.setStylePrimaryName("navi-button");
		bewerberZusagenButton.setStylePrimaryName("navi-button");
		
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
		
		/**
		 * CLICK-HANDLER
		 */
		
		zurueckButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new MeineAusschreibungenForm());				
			}
		});
	}
	
	private class BewertungBewerbungHybrid{
		
		private String bewerbungstext;
		private Bewerbungsstatus bewerbungsstatus;
		private Date erstellungsdatum;
		private String stellungsnahme;
		private double bewertungWert;
		private int bewerbungId;
		
		public int getBewerbungId() {
			return bewerbungId;
		}
		
		public void setBewerbungId(int bewerbungId) {
			this.bewerbungId = bewerbungId;
		}
		
		public String getBewerbungstext() {
			return bewerbungstext;
		}
		
		public void setBewerbungstext(String bewerbungstext) {
			this.bewerbungstext = bewerbungstext;
		}
		
		public Bewerbungsstatus getBewerbungsstatus() {
			return bewerbungsstatus;
		}
		
		public void setBewerbungsstatus(Bewerbungsstatus bewerbungsstatus) {
			this.bewerbungsstatus = bewerbungsstatus;
		}
		
		public Date getErstellungsdatum() {
			return erstellungsdatum;
		}
		
		public void setErstellungsdatum(Date erstellungsdatum) {
			this.erstellungsdatum = erstellungsdatum;
		}
		
		public String getStellungsnahme() {
			return stellungsnahme;
		}
		
		public void setStellungsnahme(String stellungsnahme) {
			this.stellungsnahme = stellungsnahme;
		}
		
		public double getBewertungWert() {
			return bewertungWert;
		}
		
		public void setBewertungWert(double bewertungWert) {
			this.bewertungWert = bewertungWert;
		}
			
	}
	
	private class GetBewerbungenCallback implements AsyncCallback<Vector<Bewerbung>>{
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());	
		}
		public void onSuccess(Vector<Bewerbung> result) {
			
			bewerbungen = result;
			
			for(int i=0;i<bewerbungen.size();i++){
				projektmarktplatzVerwaltung.getBewertungByForeignBewerbung(bewerbungen.elementAt(i), new GetBewertungCallback());
				
				//Erstellen einer neuen Instanz unserer Hybrid-Klasse BewertungBewerbungHybrid
				BewertungBewerbungHybrid localHybrid = new BewertungBewerbungHybrid();
				localHybrid.setBewerbungstext(bewerbungen.elementAt(i).getBewerbungstext());
				localHybrid.setErstellungsdatum(bewerbungen.elementAt(i).getErstellungsdatum());
				localHybrid.setBewerbungsstatus(bewerbungen.elementAt(i).getStatus());
				localHybrid.setStellungsnahme(bewertung.getStellungnahme());
				localHybrid.setBewertungWert(bewertung.getWert());
				
				hybrid.add(localHybrid);
			}
			dataGrid.setRowCount(hybrid.size(), true);
			dataGrid.setRowData(0,hybrid);
		}
		
	}
	
	private class GetBewertungCallback implements AsyncCallback<Bewertung>{
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
		}
		public void onSuccess(Bewertung result) {
			bewertung = result;
			
		}
		
	}
	
	
	
	
}
