
package de.hdm.itProjektSS17.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;


public class MeineProjektForm extends Showcase{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	HorizontalPanel buttonPanel = new HorizontalPanel();
	Button btn_projektBearbeiten = new Button("Projekt bearbeiten");
	
	CellTable<Projekt> dataGrid = new CellTable<Projekt>();
	
	
	

	Button btn_projektAnlegen = new Button("Projekt anlegen");
	Button btn_projektLoeschen = new Button("Projekt löschen");

	
	protected String getHeadlineText(){
		return "Meine Projekte";
	}
	
	
	
	
	
	@Override
	protected void run() {		
		
//		HorizontalPanel panel_projekte = new HorizontalPanel();
//		this.add(panel_projekte);
//		
//		
//		//btn_eigeneProjekte.addClickHandler(new EigeneProjekteClickHandler());
//		panel_projekte.add(btn_eigeneProjekte);
//		
//		
//	//	btn_beteiligungen.addClickHandler();
//		panel_projekte.add(btn_beteiligungen);
//		
//		
//		FlexCellFormatter cellFormatter = ft_projekteAnzeigen.getFlexCellFormatter();
//		ft_projekteAnzeigen.setWidth("32em");
//		ft_projekteAnzeigen.setCellSpacing(5);
//		ft_projekteAnzeigen.setCellPadding(3);
//		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
//		
//		ft_projekteAnzeigen.setText(0, 0, "Projektname");
//		ft_projekteAnzeigen.setText(0, 1, "Beschreibung");
//		ft_projekteAnzeigen.setText(0, 2, "Startdatum");
//		ft_projekteAnzeigen.setText(0, 3, "Enddatum");
//		ft_projekteAnzeigen.setText(0, 4, "Projektmarktplatz");
//		
//		this.add(ft_projekteAnzeigen); 
//		
//
//		btn_projektAnlegen.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				
//				DialogBoxProjektAnlegen dpa = new DialogBoxProjektAnlegen();
//				int left = Window.getClientWidth() / 3;
//				int top = Window.getClientHeight() / 8;
//				dpa.setPopupPosition(left, top);
//				dpa.show();
//				
//			}
//		});
//		this.add(btn_projektAnlegen);
//		
//		projektmarktplatzVerwaltung.getPersonById(8, new PersonCallback());
//		
//		
//	}
//	
//	private class PersonCallback implements AsyncCallback<Person> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			
//			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
//			
//		}
//
//		@Override
//		public void onSuccess(Person result) {
//			
//			if (result != null) {
//				ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
//				projektmarktplatzVerwaltung.getProjektByForeignPerson(result, new ProjekteAnzeigenCallback());
//			}
//			
//		}
//		
//	}
//	
//
//	private class ProjekteAnzeigenCallback implements AsyncCallback<Vector<Projekt>> {
//
//		
//		
//		@Override
//		public void onFailure(Throwable caught) {
//			
//			Window.alert("Das Anzeigen der Projekte der Person ist fehlgeschlagen!");
//			
//		}
//
//		@Override
//		public void onSuccess(Vector<Projekt> result) {
//				
//			for (Projekt projekt : result) {
//				
//				ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
//				
//				
//				projektmarktplatzVerwaltung.getProjektmarktplatzById(projekt.getProjektmarktplatzId(), new ProjektmarktplatzAnzeigenCallback(projekt));
//				
//				
//			}
//		}
//		
//	}
//	
//	
//	private class ProjektmarktplatzAnzeigenCallback implements AsyncCallback<Projektmarktplatz> {
//
//		private Projekt p = null;
//		
//		public ProjektmarktplatzAnzeigenCallback(Projekt p) {
//			this.p = p;
//		}
//		
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Anzeigen des Projektmarktplatzes ist fehlgeschlagen!");
//			
//		}
//
//		@Override
//		public void onSuccess(Projektmarktplatz result) {
//			int row = ft_projekteAnzeigen.getRowCount();
//			if (result != null) {
//				
//				ft_projekteAnzeigen.setWidget(row + 1, 0, new Label(p.getName()));
//				ft_projekteAnzeigen.setWidget(row + 1, 1, new Label(p.getBeschreibung()));
//				ft_projekteAnzeigen.setWidget(row + 1 , 2, new Label(p.getStartdatum().toString()));
//				ft_projekteAnzeigen.setWidget(row + 1 , 3, new Label(p.getEnddatum().toString()));
//				ft_projekteAnzeigen.setWidget(row + 1, 4, new Label(result.getBezeichnung()));
//				
//			}
			
//		}
	
		
//		projektmarktplatzVerwaltung.getPersonById(8, new AsyncCallback<Person>() {
//			
//			@Override
//			public void onSuccess(Person result) {
//				if (result != null) {
//					projektmarktplatzVerwaltung.getProjektByForeignPerson(result, new AsyncCallback<Vector<Projekt>>() {
//						
//						@Override
//						public void onSuccess(Vector<Projekt> result) {
//							if (result != null) {
//								for (Projekt projekt : result) {
//									
//									projekte.add(projekt);
//									}
//							}
//						}
//						
//						@Override
//						public void onFailure(Throwable caught) {
//							
//						}
//					});
//				}
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//			}
//		});
//		
//		
//		CellTable<Projekt> table = new CellTable<Projekt>();
//		
//		
//		
//		TextColumn<Projekt> nameColumn = new TextColumn<Projekt>() {
//			
//			@Override
//			public String getValue(Projekt object) {
//				return object.getName();
//			}
//		};
//		
//		table.addColumn(nameColumn, "Name");
//		
//		TextColumn<Projekt> beschreibungColumn = new TextColumn<Projekt>() {
//			
//			@Override
//			public String getValue(Projekt object) {
//				
//				return object.getBeschreibung();
//			}
//		};
//		
//		table.addColumn(beschreibungColumn, "Beschreibung");
//		// TODO alle spalten hinzuf�gen
//		
//		final SingleSelectionModel<Projekt> selectionModel = new SingleSelectionModel<Projekt>();
//		table.setSelectionModel(selectionModel);
//		
//		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//			
//			@Override
//			public void onSelectionChange(SelectionChangeEvent event) {
//			
//			}
//		});
//		
//		
//		table.setRowCount(projekte.size(), true);
//		table.setPageSize(projekte.size());
//		table.setRowData(0, projekte);
//		
//		this.add(table);
	
		
				
		projektmarktplatzVerwaltung.getPersonById(IdentityMarketChoice.getSelectedIdentityId(), new GetPersonCallback()); 
		
		
		btn_projektAnlegen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO weiterarbeiten
			DialogBoxProjektAnlegen dpa = new DialogBoxProjektAnlegen();
			int left = Window.getClientWidth() / 3;
			int top = Window.getClientHeight() / 8;
			dpa.setPopupPosition(left, top);
			dpa.show();
			
		}
	});
		
		
	//this.add(btn_projektAnlegen);
	
	projektmarktplatzVerwaltung.getPersonById(IdentityMarketChoice.getSelectedIdentityId(), new GetPersonCallback());
		
		
		
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
		
		//this.add(btn_projektLoeschen);

		
		btn_projektLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() != null) {
					projektmarktplatzVerwaltung.deleteProjekt(selectionModel.getSelectedObject(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							
							Window.alert("Das L�schen des Projektes war erfolgreich....");
							Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Das L�schen des Projektes schlug fehl");
							
						}
					});
				}
				
			}
		});
		
		
		
		dataGrid.setWidth("100%");
		btn_projektAnlegen.setStylePrimaryName("navi-button");
		btn_projektLoeschen.setStylePrimaryName("navi-button");
		btn_projektBearbeiten.setStylePrimaryName("navi-button");
	
		
		buttonPanel.add(btn_projektAnlegen);
		buttonPanel.add(btn_projektLoeschen);
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
	}
	
	private class GetPersonCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			projektmarktplatzVerwaltung.getProjektByForeignPerson(result, new GetProjektCallback());
			
		}
		
	}
	
	private class GetProjektCallback implements AsyncCallback<Vector<Projekt>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Projekt> result) {
			
			dataGrid.setRowCount(result.size(), true);
			dataGrid.setRowData(0, result);
			
		}
		
	}
}
