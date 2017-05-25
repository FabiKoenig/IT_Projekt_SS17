package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

public class StellenauschreibungForm extends Showcase {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static  Vector<Ausschreibung> ausschreibungen = new Vector<>();

	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Stellenausschreibung";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		projektmarktplatzVerwaltung.getProjektmarktplatzById(1, new AsyncCallback<Projektmarktplatz>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Projektmarktplatz result) {
				// TODO Auto-generated method stub
				if(result != null){
					projektmarktplatzVerwaltung.getProjektByForeignProjektmarktplatz(result, new AsyncCallback<Vector<Projekt>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
						
						
						@Override
						public void onSuccess(Vector<Projekt> result) {
							// TODO Auto-generated method stub
							if(result != null){
								for(Projekt projekt : result){
								
								projektmarktplatzVerwaltung.getAusschreibungByForeignProjekt(projekt, new AsyncCallback<Vector<Ausschreibung>>(){

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(Vector<Ausschreibung> result) {
										// TODO Auto-generated method stub
										if(result != null){
											for(Ausschreibung ausschreibung : result){
												ausschreibungen.add(ausschreibung);
											}
										}
									
									}
									
								});
							}
						}
						}	
					});
				}
			}
			
		});
		
		CellTable<Ausschreibung> dataGrid = new CellTable();
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		
		
		TextColumn<Ausschreibung> BezeichnungColumn = new TextColumn<Ausschreibung>() {

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		dataGrid.addColumn(BezeichnungColumn, "Bezeichnung");

		
		
		TextColumn<Ausschreibung> BewerbungsfristColumn = new TextColumn<Ausschreibung>() {

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBewerbungsfrist().toString();
			}
		};
		dataGrid.addColumn(BewerbungsfristColumn, "Bewerbungsfrist");

		
		
		TextColumn<Ausschreibung> nameColumn = new TextColumn<Ausschreibung>() {

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getAusschreibungstext();
			}
		};
		dataGrid.addColumn(nameColumn, "Ausschreibung");

		
		dataGrid.setRowCount(ausschreibungen.size(), true);
		dataGrid.setRowData(0, ausschreibungen);
		dataGrid.setWidth("100%");
		
		this.add(dataGrid);
	}
}

