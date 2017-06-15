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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.BewerbungenAufAusschreibungForm.BewertungBewerbungHybrid;
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
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public PartnerprofilByAusschreibungForm(final int partnerprofilId, boolean afterAnlegen, boolean hideEigenschaftHinzufuegenButton, final IdentityMarketChoice identityMarketChoice, final Navigation navigation){
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		
		if(hideEigenschaftHinzufuegenButton==true){
			eigenschaftHinzufuegenButton.setVisible(false);
			loeschenButton.setVisible(false);
		}
		
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		
		
		
		
		
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
						
						final ListDataProvider dataProvider = new ListDataProvider();
						SimplePager pager;
						SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
						pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
						pager.setDisplay(dataGrid);
						dataProvider.addDataDisplay(dataGrid);
						dataProvider.setList(new ArrayList<Eigenschaft>(result));
						pager.setPageSize(1);
						
						HorizontalPanel hp_pager = new HorizontalPanel();
						hp_pager.setWidth("100%");
						hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						hp_pager.add(pager);
						add(hp_pager);
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
		zurueckButton.setStylePrimaryName("cell-btn");
		eigenschaftHinzufuegenButton.setStylePrimaryName("cell-btn");
		loeschenButton.setStylePrimaryName("cell-btn");
		
		//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
		
		/**
		 * Click-Handler
		 */
		zurueckButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				navigation.reload();
					
			}
		});
		
		
		eigenschaftHinzufuegenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBoxEigenschaftHinzufuegen gg = new DialogBoxEigenschaftHinzufuegen(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung(), false, navigation, identityMarketChoice);
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
						RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung(), false, false, identityMarketChoice, navigation));
						
					}
				});
				
			}
		});
		
		
		if(afterAnlegen == true){
			loeschenButton.setVisible(false);
			eigenschaftHinzufuegenButton.setVisible(false);
			zurueckButton.setVisible(false);
			Label helloLabel = new Label("Fügen Sie zu Ihrer Ausschreibung noch ein Partnerprofil mit Eigenschaften hinzu, die der Bewerber mit sich bringen soll.");
			Button deleteButton = new Button("Löschen");
			Button addEigenschaftButton = new Button("Eigenschaft hinzufügen");
			Button returnButton = new Button("Zurück");
			VerticalPanel vPanel = new VerticalPanel();
			deleteButton.setStylePrimaryName("cell-btn");
			addEigenschaftButton.setStylePrimaryName("cell-btn");
			returnButton.setStylePrimaryName("cell-btn");
			helloLabel.setStylePrimaryName("startseite_label");
			vPanel.setSpacing(10);
			vPanel.add(helloLabel);
			vPanel.add(buttonPanel);
			vPanel.add(dataGrid);
			this.add(vPanel);
			buttonPanel.add(returnButton);
			buttonPanel.add(addEigenschaftButton);
			buttonPanel.add(deleteButton);
			
			
			addEigenschaftButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					DialogBoxEigenschaftHinzufuegen dbox = new DialogBoxEigenschaftHinzufuegen(partnerprofilId, true, navigation, identityMarketChoice);
					dbox.center();
					dbox.show();
				}
			});
			
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Eigenschaft selectedEigenschaft = selectionModel.getSelectedObject();
					projektmarktplatzVerwaltung.deleteEigenschaft(selectedEigenschaft, new AsyncCallback<Void>() {
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Die Eigenschaft konnte nicht gelöscht werden.");
						}
						public void onSuccess(Void result) {
							Window.alert("Die Eigenschaft wurde erfolgreich gelöscht.");

							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(partnerprofilId, true, false, identityMarketChoice, navigation));
							
						}
					});	
				}
			});
			
			returnButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					navigation.reload();					
				}
			});
			
		}
		
	}
	
}
