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
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Projektmarktplätze";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		HorizontalPanel panel_projektmarktplatz = new HorizontalPanel();
		this.add(panel_projektmarktplatz);
		
		Button btn_projektmarktplatzanlegen = new Button("Projektmarktplatz anlegen");
		panel_projektmarktplatz.add(btn_projektmarktplatzanlegen);
		
		final Button btn_projektmarktplatzlöschen = new Button("Projektmarktplatz löschen");
		panel_projektmarktplatz.add(btn_projektmarktplatzlöschen);
		
	
		
		projektmarktplatzVerwaltung.getAllProjektmarktplatz(new AsyncCallback<Vector<Projektmarktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				if(result != null){
						projektmarktplatz = result;
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
		final SingleSelectionModel<Projektmarktplatz> ssm = new SingleSelectionModel<Projektmarktplatz>();
		dataGrid.setSelectionModel(ssm);
		ssm.addSelectionChangeHandler(new Handler() {
		    @Override
		    public void onSelectionChange(final SelectionChangeEvent event)
		    {
		        final Projektmarktplatz selectedObject = ssm.getSelectedObject();
		        // do what you want
		    }
		});
		
		
		dataGrid.setRowCount(projektmarktplatz.size(), true);
		dataGrid.setRowData(0, projektmarktplatz);
		dataGrid.setWidth("100%");
		
		this.add(dataGrid);
		
		
		
	class ProjektmarktplatzLöschenCallback implements AsyncCallback<Projektmarktplatz>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Projektmarktplatz result) {
				// TODO Auto-generated method stub
				btn_projektmarktplatzlöschen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Projektmarktplatz selectedObject = ssm.getSelectedObject();
						if(selectedObject != null){
						projektmarktplatzVerwaltung.deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								
							}
						});
							
						}
					}
  			});
				
				}
			}
			
		}		
	}


