package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.appengine.api.capabilities.CapabilitiesPb.CapabilityConfig.Status;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class AusschreibungenaufProjektForm extends Showcase{

	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	HorizontalPanel buttonPanel = new HorizontalPanel();
	CellTable<Ausschreibung> dataGrid = new CellTable<Ausschreibung>();
	Vector<Ausschreibung> ausschreibung = new Vector<Ausschreibung>();
	
	Button btn_zurueck = new Button("Zurück");
	
	private Projekt p;
	
	@Override	
	protected String getHeadlineText() {
		return "Auschreibungen zum Projekt";

	}
	
	public  AusschreibungenaufProjektForm(Projekt p) {
		this.p = p;
		
		
		
	}
	
	


	
	private class getProjekte implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {


			dataGrid.setRowCount(result.size(), true);
			dataGrid.setRowData(0, result);
			
			}
		
		}
		
	private class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Organisationseinheit result) {		
			if (result != null) {
				projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new GetAusschreibungenByOrgaCallback());
			}			
		}
	
	}
	
	private class GetAusschreibungenByOrgaCallback implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Ausschreibungen konnten nicht geladen werden.");
			Window.alert("Fehler: "+ caught.toString());
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			
			if(result != null){
				//Anpassen der CellTable
				
				
				
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(0, result);
			
			}
		}	
	}


	


	@Override
	protected void run() {
		//projektmarktplatzVerwaltung.getOrganisationseinheitById(IdentityMarketChoice.getSelectedIdentityId(), new OrganisationseinheitCallback());
		
		
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		projektmarktplatzVerwaltung.getAusschreibungByForeignProjekt(p, new getProjekte());
		
		TextColumn<Ausschreibung> ausschreibungstextColumn = new TextColumn<Ausschreibung>(){
			
		public String getValue(Ausschreibung object){
			return object.getAusschreibungstext();
		}
	};
		
		TextColumn<Ausschreibung> bezeichnungColumn = new TextColumn<Ausschreibung>(){
		
		public String getValue(Ausschreibung object){
		return object.getBezeichnung();
		}
	};
		
		TextColumn<Ausschreibung> bewerbungsfristColumn = new TextColumn<Ausschreibung>(){
		
		public String getValue(Ausschreibung object){
		return object.getBewerbungsfrist().toString();
		
		}
	};
		
	TextColumn<Ausschreibung> ausschreibungsstatusColumn = new TextColumn<Ausschreibung>(){
		
		public String getValue(Ausschreibung object){
			
			//Michi Fragen wegen Enum
			return object.getStatus().toString();
		
		}
	};
	
		dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstext");
		dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
		dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		dataGrid.addColumn(ausschreibungsstatusColumn, "Ausschreibungsstatus");
	
		dataGrid.setWidth("100%");
		
		//was ist des?
		final SingleSelectionModel<Ausschreibung> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
	
		buttonPanel.add(btn_zurueck);
		
		btn_zurueck.setStylePrimaryName("navi-button");
	
		//auf welche Variable bezogen
		this.add(buttonPanel);
		this.add(dataGrid);
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new MeineProjektForm());				
			}
		});
		
	}
	
	
	
}
