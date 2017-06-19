
package de.hdm.itProjektSS17.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;


public class MeineProjektForm extends Showcase{
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public MeineProjektForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	HorizontalPanel buttonPanel = new HorizontalPanel();
	CellTable<Projekt> dataGrid = new CellTable<Projekt>();

	
	Button btn_projektBearbeiten = new Button("Projekt bearbeiten");
	Button btn_projektAnlegen = new Button("Projekt anlegen");
	Button btn_projektLoeschen = new Button("Projekt löschen");
	Button btn_ausschreibungAnlegen = new Button("Ausschreibung anlegen");
	Button btn_ausschreibungenAnzeigen = new Button("Ausschreibungen anzeigen");
	Button btn_beteiligungAnzeigen = new Button("Beteiligung anzeigen");	

	
	protected String getHeadlineText(){
		return "Meine Projekte";
	}
	
	
	@Override
	protected void run() {		
			
		projektmarktplatzVerwaltung.getProjektmarktplatzById(identityMarketChoice.getSelectedProjectMarketplaceId(), new AsyncCallback<Projektmarktplatz>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Projektmarktplatz result) {
			
					
				projektmarktplatzVerwaltung.getProjektByForeignProjektmarktplatz(result, new GetProjektCallback());
				
				
			}
		});
		
		
		btn_projektAnlegen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			DialogBoxProjektAnlegen dpa = new DialogBoxProjektAnlegen(identityMarketChoice, navigation);
			int left = Window.getClientWidth() / 3;
			int top = Window.getClientHeight() / 8;
			dpa.setPopupPosition(left, top);
			dpa.show();
			
		}
	});
	


		
		RootPanel.get("Details").setWidth("75%");
		dataGrid.setWidth("100%", true);
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		TextColumn<Projekt> nameColumn = new TextColumn<Projekt>() {
			
			@Override
			public String getValue(Projekt object) {
			
				return object.getName();
			}
		};
		
		dataGrid.addColumn(nameColumn, "Name");
		
		TextColumn<Projekt> beschreibungColumn = new TextColumn<Projekt>() {
			
			@Override
			public String getValue(Projekt object) {
				
				return object.getBeschreibung();
				
			}
		};
		
		dataGrid.addColumn(beschreibungColumn, "Beschreibung");
		
		TextColumn<Projekt> startDatumColumn = new TextColumn<Projekt>() {
			
			@Override
			public String getValue(Projekt object) {
				
				return object.getStartdatum().toString();
			}
		}; 
		
		dataGrid.addColumn(startDatumColumn, "Startdatum");
		
		TextColumn<Projekt> endDatumColumn = new TextColumn<Projekt>() {
			
			@Override
			public String getValue(Projekt object) {
				return object.getEnddatum().toString();
			}
		};

		dataGrid.addColumn(endDatumColumn, "Enddatum");
		
		
		
		final SingleSelectionModel<Projekt> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
			}
		});
		
		
		btn_ausschreibungAnlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				if (selectionModel.getSelectedObject() != null) {
					DialogBoxAusschreibungAnlegen daa = new DialogBoxAusschreibungAnlegen(selectionModel.getSelectedObject(), identityMarketChoice, navigation);
					daa.show();
					daa.center();
				}
				else {
					Window.alert("Bitte wählen Sie ein Projekt zuerst aus, bevor Sie eine Ausschreibung anlegen möchten!");
				}
			}
		});
		
		btn_ausschreibungenAnzeigen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(selectionModel.getSelectedObject() != null){
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(new AusschreibungenaufProjektForm(selectionModel.getSelectedObject(), navigation));
					
				} else {
					Window.alert("Bitte wähle zuerst eine Projekt aus.");
				}
				
				
				
			}
		});
		
		
		
		btn_projektBearbeiten.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						if (selectionModel.getSelectedObject() == null) {
							Window.alert("Bitte wählen Sie ein Projekt aus!");
						}
						else {
						
							DialogBoxProjektBearbeiten dpb = new DialogBoxProjektBearbeiten(selectionModel.getSelectedObject(), navigation);
							int left = Window.getClientWidth() / 3;
							int top = Window.getClientHeight() / 8;
							dpb.setPopupPosition(left, top);
							
							dpb.show();
						}
					
					}
				});
		
		btn_projektLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() != null) {
					projektmarktplatzVerwaltung.deleteProjekt(selectionModel.getSelectedObject(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							
							Window.alert("Das Löschen des Projektes war erfolgreich");
							navigation.reload();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Das Löschen des Projektes schlug fehl");
							
						}
					});
				}
				else {
					Window.alert("Bitte wählen Sie zuerst das zu löschende Projekte aus!");
				}
				
			}
		});
		
	btn_beteiligungAnzeigen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(selectionModel.getSelectedObject() != null){
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(new BeteiligungaufProjektForm(selectionModel.getSelectedObject(), navigation, identityMarketChoice));
					navigation.setCurrentClickEvent(event);
					navigation.setCurrentClickHandler(this);
				} else {
					Window.alert("Bitte wähle zuerst eine Projekt aus.");
				}
				
				
				
			}
		});
		
		
		
		
		dataGrid.setWidth("100%");
		btn_projektAnlegen.setStylePrimaryName("cell-btn");
		btn_projektLoeschen.setStylePrimaryName("cell-btn");
		btn_projektBearbeiten.setStylePrimaryName("cell-btn");
		
		btn_ausschreibungAnlegen.setStylePrimaryName("cell-btn");
		btn_ausschreibungenAnzeigen.setStylePrimaryName("cell-btn");
		btn_beteiligungAnzeigen.setStylePrimaryName("cell-btn");

		
		buttonPanel.add(btn_projektAnlegen);
		buttonPanel.add(btn_projektBearbeiten);
		buttonPanel.add(btn_projektLoeschen);
		buttonPanel.add(btn_ausschreibungAnlegen);
		buttonPanel.add(btn_ausschreibungenAnzeigen);
		buttonPanel.add(btn_beteiligungAnzeigen);
		this.setSpacing(8);
		this.add(buttonPanel);
		
		
		this.add(dataGrid);
		
	}
	

//	private class GetPersonCallback implements AsyncCallback<Person>{
//
//		@Override
//		public void onFailure(Throwable caught) {			
//		}
//
//		@Override
//		public void onSuccess(Person result) {
//			projektmarktplatzVerwaltung.getProjektByForeignPerson(result, new GetProjektCallback());
//			
//		}
//		
//	}
	 

	private class GetProjektCallback implements AsyncCallback<Vector<Projekt>>{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Vector<Projekt> result) {
			
			Vector<Projekt> projekte = new Vector<>();
			
			for(Projekt projekt : result){
				if(projekt.getProjektleiterId() == identityMarketChoice.getSelectedIdentityId()){
					projekte.add(projekt);
				}
			}

			
			final ListDataProvider dataProvider = new ListDataProvider();
			SimplePager pager;
			SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
			pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
			pager.setDisplay(dataGrid);
			dataProvider.addDataDisplay(dataGrid);
			dataProvider.setList(new ArrayList<Projekt>(projekte));
			pager.setPageSize(10);
			
			HorizontalPanel hp_pager = new HorizontalPanel();
			hp_pager.setWidth("100%");
			hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			hp_pager.add(pager);
			add(hp_pager);
			dataGrid.setRowCount(projekte.size(), true);
			dataGrid.setRowData(0, projekte);
			
		}
		
	}
}
