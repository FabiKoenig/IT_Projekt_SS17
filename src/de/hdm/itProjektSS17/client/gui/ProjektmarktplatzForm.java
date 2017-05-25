package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

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

		TextColumn<Projektmarktplatz> ProjektmarktplatzIdColumn = new TextColumn<Projektmarktplatz>() {

			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		dataGrid.addColumn(ProjektmarktplatzIdColumn, "Projektmarktplatz Nr.");
		
		
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn = new TextColumn<Projektmarktplatz>() {

			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		dataGrid.addColumn(ProjektmarktplatznameColumn, "Projektmarktplatz");

		
		
	}
}
