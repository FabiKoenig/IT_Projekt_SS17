package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	Vector<Bewerbung> bewerbungen = new Vector<Bewerbung>();
	Button bewerbungBewertenButton = new Button("Bewerbung bewerten");
	Button bewerberZusagenButton = new Button("Bewerber annehmen");
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
		buttonPanel.add(bewerbungBewertenButton);
		buttonPanel.add(bewerberZusagenButton);
		
		//Stylen der Buttons
		bewerbungBewertenButton.setStylePrimaryName("navi-button");
		bewerberZusagenButton.setStylePrimaryName("navi-button");
		
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
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
			
			Vector<BewertungBewerbungHybrid> hybrid = new Vector();
			
			bewerbungen = result;
			
			for(int i=0;i<bewerbungen.size();i++){
				
			}
		}
		
	}
	
	private class GetBewertungCallback implements AsyncCallback<Bewertung>{
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
		}
		public void onSuccess(Bewertung result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	
}
