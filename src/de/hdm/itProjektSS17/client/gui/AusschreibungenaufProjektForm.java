package de.hdm.itProjektSS17.client.gui;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Projekt;

/**
 * @see de.hdm.itProjektSS17.client.client.showcase;
 * @author Tim
 *
 */
public class AusschreibungenaufProjektForm extends Showcase{

	
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	/**
	 * Deklaration der globalen Variablen und Objekte
	 */
	HorizontalPanel buttonPanel = new HorizontalPanel();
	CellTable<Ausschreibung> dataGrid = new CellTable<Ausschreibung>();
	Button btn_zurueck = new Button("Zurück");
	
	Navigation navigation = null;
	Vector<Ausschreibung> ausschreibung = new Vector<Ausschreibung>();
	
	
	
	private Projekt p;
	
	/**
	 * HeadlineText returnen
	 * @return String
	 */
	@Override	
	protected String getHeadlineText() {
		return "Auschreibungen zum Projekt "+p.getName();

	}
	
	/**
	 * 
	 * @param p
	 * @param navigation
	 */
	public  AusschreibungenaufProjektForm(Projekt p, Navigation navigation) {
		this.p = p;
		this.navigation=navigation;	
	}
	
	


	/**
	 * Private Klasse welche einen neuen CallBack implementiert, 
	 * der einen Vector von Ausschreibung als result zurück gibt.
	 * @author Tim
	 *
	 */
	private class getProjekte implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
			
		}

		/**
		 * Vector mit Ausschreibung werden als result zurück gegeben
		 */
		@Override
		public void onSuccess(Vector<Ausschreibung> result) {

			/**
			 * Pager erstellen und zum Panel hinzufügen
			 */
			final ListDataProvider dataProvider = new ListDataProvider();
			SimplePager pager;
			SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
			pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
			pager.setDisplay(dataGrid);
			dataProvider.addDataDisplay(dataGrid);
			dataProvider.setList(new ArrayList<Ausschreibung>(result));
			pager.setPageSize(20);
			
			HorizontalPanel hp_pager = new HorizontalPanel();
			hp_pager.setWidth("100%");
			hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			hp_pager.add(pager);
			add(hp_pager);
			

			dataGrid.setRowCount(result.size(), true);
			dataGrid.setRowData(0, result);
			
			}
		
		}

	
	/**
	 * Nachdem alles vorbereitet ist wird die run-Methode gestartet. Diese ist eine abstrakte Methode
	 * in der die Subklassen implemntiert werden.
     */
	 
	@Override
	protected void run() {
	
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		projektmarktplatzVerwaltung.getAusschreibungByForeignProjekt(p, new getProjekte());
	
		/**
		 * Anlegen der TextColumns für die cellTable
		 */
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
	
	/**
	 * TextColumns der celltable hinzufügen
	 */
		dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstext");
		dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
		dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		dataGrid.addColumn(ausschreibungsstatusColumn, "Ausschreibungsstatus");
	
		dataGrid.setWidth("100%");
		
		/**
		 * SingleSelectionModel anlegen um Datensätze in der Tabelle zu makieren und der Celltable hinzufügen
		 */
		final SingleSelectionModel<Ausschreibung> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);	
		
		/**
		 * SelectionChangeEvent-Handler anlegen
		 */
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
	
		buttonPanel.add(btn_zurueck);
		
		btn_zurueck.setStylePrimaryName("cell-btn");
	
		this.add(buttonPanel);
		this.add(dataGrid);
		
		/**
		 * Click-Handler für zurück Button anlegen.
		 */
		btn_zurueck.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				navigation.reload();
			}
		});
		
	}
	
	
	
}
