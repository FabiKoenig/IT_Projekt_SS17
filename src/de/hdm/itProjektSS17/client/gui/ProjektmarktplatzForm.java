package de.hdm.itProjektSS17.client.gui;

import de.hdm.itProjektSS17.client.Showcase;

import java.util.Vector;

import com.google.gwt.aria.client.SelectedValue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

public class ProjektmarktplatzForm extends Showcase {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static  Vector<Projektmarktplatz> projektmarktplatz = new Vector<>();
	CellTable<Projektmarktplatz> dataGrid = new CellTable();
	
	
	HorizontalPanel panel_projektmarktplatz = new HorizontalPanel();
	
	//Deklarieren der Buttons
	Button btn_projektmarktplatzloeschen = new Button("Projektmarktplatz löschen");
	Button btn_projektmarktplatzanlegen = new Button("Projektmarktplatz anlegen");
	
	Projektmarktplatz selectedObject = new Projektmarktplatz();
	SingleSelectionModel<Projektmarktplatz> ssm = new SingleSelectionModel<Projektmarktplatz>();
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Projektmarktplätze";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		
		this.add(panel_projektmarktplatz);
		
		//Stylen der Buttons
		btn_projektmarktplatzanlegen.setStylePrimaryName("navi-button");
		btn_projektmarktplatzloeschen.setStylePrimaryName("navi-button");
		
		//Hinzufügen der Buttons zum Panel
		panel_projektmarktplatz.add(btn_projektmarktplatzanlegen);
		panel_projektmarktplatz.add(btn_projektmarktplatzloeschen);
		
		
		
		btn_projektmarktplatzloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Projektmarktplatz selectedObject = ssm.getSelectedObject();
				
				if (selectedObject != null) {
					ClientsideSettings.getProjektmarktplatzVerwaltung().deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Der Projektmarktplatz konnte nicht gel�scht werden. ");
							
						}
					});
					
				}else {
					Window.alert("Es wurde kein Projektmarktplatz ausgew�hlt");
				}
				
			}
		});
	
		
		projektmarktplatzVerwaltung.getAllProjektmarktplatz(new AsyncCallback<Vector<Projektmarktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail");
			}

			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				if(result != null){
					
					dataGrid.setRowData(0, result);
					dataGrid.setRowCount(result.size(), true);
					}
				}
		});

		
		
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		dataGrid.addColumn(ProjektmarktplatznameColumn, "Projektmarktplatz");
	
		//SingleSelectionModel anlegen um verschiedene Zeilen auszuwählen
		
		dataGrid.setSelectionModel(ssm);
		ssm.addSelectionChangeHandler(new Handler() {
		    @Override
		    public void onSelectionChange(final SelectionChangeEvent event)
		    {
		        selectedObject = ssm.getSelectedObject();
		        // do what you want
		    }
		});
		
		
		
		dataGrid.setWidth("100%");
		
		this.add(dataGrid);		
		}		
	}


