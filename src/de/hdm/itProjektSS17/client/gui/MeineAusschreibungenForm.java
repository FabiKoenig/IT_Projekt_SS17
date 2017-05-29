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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;

public class MeineAusschreibungenForm extends Showcase{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static Vector<Ausschreibung> ausschreibungen = new Vector<>();
	
	CellTable<Ausschreibung> dataGrid = new CellTable<Ausschreibung>();
	Button ausschreibungLoeschenButton = new Button("Ausschreibung löschen");
	Button ausschreibungBearbeitenButton = new Button("Ausschreibung bearbeiten");
	Button partnerprofilBearbeitenButton = new Button("Partnerprofil bearbeiten");
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	@Override
	protected String getHeadlineText() {
		return "Von mir erstellte Ausschreibungen";
	}

	@Override
	protected void run() {
		
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
			//Erstellen der TextColumns der CellTable
				TextColumn<Ausschreibung> bezeichnungColumn = new TextColumn<Ausschreibung>(){

					@Override
					public String getValue(Ausschreibung object) {
						return object.getBezeichnung();
					}
				};
				

				TextColumn<Ausschreibung> bewerbungsfristColumn = new TextColumn<Ausschreibung>(){

					@Override
					public String getValue(Ausschreibung object) {
						return object.getBewerbungsfrist().toString();
					}
				};
				
		
				TextColumn<Ausschreibung> ausschreibungstextColumn = new TextColumn<Ausschreibung>(){

					@Override
					public String getValue(Ausschreibung object) {
						return object.getAusschreibungstext();
					}
				};
				
//		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
//				
//		//Erstellen der TextColumns der CellTable
//				TextColumn<Ausschreibung> projektColumn = new TextColumn<Ausschreibung>(){
//
//					@Override
//					public String getValue(Ausschreibung object) {
//						return object.getAusschreibungstext();
//					}
//				};		
		
			// Hinzufügen der Spalten zu unserer CellTable
				dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
				dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
				dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstest");
				
				
			// Anlegen des SingleSeletion Models
				final SingleSelectionModel<Ausschreibung> selectionModel = new SingleSelectionModel<>();
				dataGrid.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						
						
					}
				});
				
					
			//Anpassen der CellTable
				dataGrid.setRowCount(ausschreibungen.size(), true);
				dataGrid.setRowData(0, ausschreibungen);
				dataGrid.setWidth("100%");
				
			//Hinzufügen der Buttons zum ButtonPanel
				buttonPanel.add(ausschreibungBearbeitenButton);
				buttonPanel.add(ausschreibungLoeschenButton);
				buttonPanel.add(partnerprofilBearbeitenButton);
				
			//Style der Buttons
				ausschreibungBearbeitenButton.setStylePrimaryName("navi-button");
				ausschreibungLoeschenButton.setStylePrimaryName("navi-button");
				partnerprofilBearbeitenButton.setStylePrimaryName("navi-button");
				
			//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
				this.setSpacing(8);
				this.add(buttonPanel);
				this.add(dataGrid);
				
				
			/**
			 * CLICK-HANDLER
			 */
				
				
				/**
				 * Click-Handler zum löschen einer Ausschreibung
				 */
			ausschreibungLoeschenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					Ausschreibung selectedAusschreibung = selectionModel.getSelectedObject();
					projektmarktplatzVerwaltung.deleteAusschreibung(selectedAusschreibung, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Die Ausschreibung wurde erfolgreich gelöscht.");
							
							Showcase showcase = new MeineAusschreibungenForm();
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(showcase);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Die Ausschreibung konnte nicht gelöscht werden.");
						}
					});
				}
			});
			
				/**
				 * Click-Handler zum bearbeiten einer Ausschreibung
				 */
			ausschreibungBearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					
				}
			});
			
			
			
	}

	
	
}
