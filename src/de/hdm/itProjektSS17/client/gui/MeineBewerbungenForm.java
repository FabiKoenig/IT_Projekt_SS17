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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.server.db.BewerbungMapper;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;
import java_cup.action_part;


/**
 * Diese Klasse erbt von Vertical Panel. Sie zeigt die Bewerbungen einer Person 
 * in tabellarischer Form
 * 
 * @author Tom Alender
 *
 */
public class MeineBewerbungenForm extends Showcase{
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static  Vector<Bewerbung> bewerbung = new Vector<>();
	CellTable<Bewerbung> cellTable = new CellTable<Bewerbung>();
	 Ausschreibung a = new Ausschreibung();

	FlexTable ft_Bewerbung = new FlexTable();
	
	protected String getHeadlineText(){
		return "Meine Bewerbungen";
		

	}
	
	
	
	protected void run() {
		// TODO Auto-generated method stub
	/*	Label lbl_valueProjektname = new Label();
		Label lbl_valueBeschreibung = new Label();
		*/
		
		
		
		projektmarktplatzVerwaltung.getPersonById(6, new AsyncCallback<Person>(){
		
		
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Person result) {
					
					ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
					
					projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(result, new BewerbungAnzeigenCallback());	
					
				}
			});
	
		
		
	
	
	
	cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
	TextColumn<Bewerbung> AusschreibungNameColumn = new TextColumn<Bewerbung>() {

		@Override
		public String getValue(Bewerbung object) {
			// TODO Auto-generated method stub
			return a.getAusschreibungstext();
		}
		

	};
	
	cellTable.addColumn(AusschreibungNameColumn, "Ausschreibung");
	
	
	
	TextColumn<Bewerbung> erstellungsdatumColumn = new TextColumn<Bewerbung>() {
		
		@Override
		public String getValue(Bewerbung object) {
			
			return object.getErstellungsdatum().toString();
			
		}
	};
	
	cellTable.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
	
	
	final SingleSelectionModel<Bewerbung> selectionModel = new SingleSelectionModel<>();
	cellTable.setSelectionModel(selectionModel);
	selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			
			
		}
	});
	
	cellTable.setRowCount(bewerbung.size(), true);
	cellTable.setRowData(0, bewerbung);
	
	
	cellTable.setWidth("100%");
		
	
	this.add(cellTable);
}



	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Projekte der Person ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
			bewerbung = result;
		for (Bewerbung bewerbung : result) {
		
			ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
			
			projektmarktplatzVerwaltung.getAusschreibungById(bewerbung.getAusschreibungId(), new AusschreibungAnzeigenCallback());
		
					
				}
			};
		}
	
	
	 private class AusschreibungAnzeigenCallback implements AsyncCallback <Ausschreibung>{
		
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Bewerbung ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Ausschreibung result) {
			
			a = result;
			
			};
			
			
	 
		
		
	}
}
		
		
		
		
	

