package de.hdm.itProjektSS17.client.gui;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;

public class PartnerprofilByAusschreibungForm extends VerticalPanel{
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	
	
	//Elemente für die GUI
	CellTable<Eigenschaft> dataGrid = new CellTable<Eigenschaft>();
	Button loeschenButton = new Button("Löschen");
	Button eigenschaftHinzufuegenButton = new Button("Eigenschaft hinzufügen");
	Button zurueckButton = new Button("Zurück");
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	public PartnerprofilByAusschreibungForm(int partnerprofilId){
		
		
		//CallBack um die Eigenschaften der gewünschten Person zu laden
		projektmarktplatzVerwaltung.getPartnerprofilById(partnerprofilId, new AsyncCallback<Partnerprofil>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Partnerprofil result) {
				projektmarktplatzVerwaltung.getEigenschaftByForeignPartnerprofil(result, new AsyncCallback<Vector<Eigenschaft>>() {

					public void onFailure(Throwable caught) {
						Window.alert("Das Laden des Partnerprofils ist fehlgeschlagen.");
					}

					public void onSuccess(Vector<Eigenschaft> result) {
						dataGrid.setRowCount(result.size(), true);
						dataGrid.setRowData(0, result);
						
					}
				});
				
			}
		});
				
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Erstellen der TextColumns der CellTable
		TextColumn<Eigenschaft> nameColumn = new TextColumn<Eigenschaft>(){

			@Override
			public String getValue(Eigenschaft object) {
				return object.getName();
			}
		};
		
		TextColumn<Eigenschaft> wertColumn = new TextColumn<Eigenschaft>() {

			@Override
			public String getValue(Eigenschaft object) {
				return object.getWert();
			}
		};
		
		//Hinzufügen der TextColumns zur CellTable		
		dataGrid.addColumn(nameColumn, "Beschreibung");
		dataGrid.addColumn(wertColumn, "Wert");
		
		
		//
		final SingleSelectionModel<Eigenschaft> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
				
			}
		});
		
		//Anpassen der CellTable
		dataGrid.setWidth("100%");
		
		//Hinzufügen der Buttons zum ButtonPanel
		buttonPanel.add(zurueckButton);
		buttonPanel.add(eigenschaftHinzufuegenButton);
		buttonPanel.add(loeschenButton);
		
		//Stylen der Buttons
		zurueckButton.setStylePrimaryName("navi-button");
		eigenschaftHinzufuegenButton.setStylePrimaryName("navi-button");
		loeschenButton.setStylePrimaryName("navi-button");
		
		//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
		
		/**
		 * Click-Handler
		 */
		zurueckButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new MeineAusschreibungenForm());
				
			}
		});
		
		
		eigenschaftHinzufuegenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBoxEigenschaftHinzufuegen gg = new DialogBoxEigenschaftHinzufuegen(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung());
				gg.center();
				gg.show();
		
			}
		});
		
		loeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Eigenschaft selectedEigenschaft = selectionModel.getSelectedObject();
				projektmarktplatzVerwaltung.deleteEigenschaft(selectedEigenschaft, new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						Window.alert("Fehler: Die Eigenschaft konnte nicht gelöscht werden.");
					}
					public void onSuccess(Void result) {
						Window.alert("Die Eigenschaft wurde erfolgreich gelöscht.");

						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung()));
						
					}
				});
				
			}
		});
		
	}
	
}
