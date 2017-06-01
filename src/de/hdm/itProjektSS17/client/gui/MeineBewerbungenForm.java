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
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;
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
	@SuppressWarnings("unchecked")
	private static Vector <Bewerbung> bewerbungen = new Vector();
	private static Vector <Ausschreibung> ausschreibung = new Vector();
	private static Vector <Organisationseinheit> ausschreibender = new Vector();
	private static Vector <ausschreibungBewerbungHybrid> ausBewHybrid = new Vector();
	CellTable cellTable = new CellTable();
	
	HorizontalPanel panel_Bewerbung = new HorizontalPanel();
	Button btn_bewerbungloeschen = new Button("Bewerbung zurückziehen");
	//Button btn_bewerbungzurückziehen = new Button("Projektmarktplatz anlegen");

	
	protected String getHeadlineText(){
	return "Meine Bewerbungen";
	}
	
	protected void run() {
		
		
		this.add(panel_Bewerbung);
		panel_Bewerbung.add(btn_bewerbungloeschen);
		projektmarktplatzVerwaltung.getPersonById(3, new AsyncCallback<Person>(){
		
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
	
		TextColumn<ausschreibungBewerbungHybrid> AusschreibungNameColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				// TODO Auto-generated method stub
				return object.getAusschreibungsbezeichnung();
			}


		
		};	
	
		TextColumn<ausschreibungBewerbungHybrid> erstellungsdatumColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
		
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			return object.getErstellungsdatum().toString();
			
			}
		};
		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			return object.getAusschreibungsbezeichnername();
			
			}
		};
		
		TextColumn<ausschreibungBewerbungHybrid> statusColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
			return object.getStatusBewerbungsstatus().toString();
			
			}
		};
		
		
		cellTable.addColumn(AusschreibungNameColumn, "Stelle");
		cellTable.addColumn(AusschreibenderColumn, "Ausschreibender");
		cellTable.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		cellTable.addColumn(statusColumn, "Status");
	
		cellTable.setRowCount(ausBewHybrid.size(), true);
		cellTable.setRowData(0,ausBewHybrid);
		cellTable.setWidth("100%");
		
		final SingleSelectionModel<Bewerbung> selectionModel = new SingleSelectionModel<>();
		cellTable.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		

		btn_bewerbungloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			
				Bewerbung selectedObject = selectionModel.getSelectedObject();
				ClientsideSettings.getProjektmarktplatzVerwaltung().deleteBewerbung(selectedObject, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						Window.alert("Die Bewerbung wurde erfolgreich zurück gezogen");
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler: " + caught.toString());
						
					}
				});
				
			}
		});
		
		this.add(cellTable);
	}

	
	private class ausschreibungBewerbungHybrid{
		
		private int bewerbungId;
		private String ausschreibungsbezeichnung;
		private String ausschreibungsbezeichnername;
		private Date erstellungsdatum;
		private Bewerbungsstatus statusBewerbungsstatus;
		
		public int getBewerbungId() {
			return bewerbungId;
		}
		public void setBewerbungId(int bewerbungId) {
			this.bewerbungId = bewerbungId;
		}
		public String getAusschreibungsbezeichnung() {
			return ausschreibungsbezeichnung;
		}
		public void setAusschreibungsbezeichnung(String ausschreibungsbezeichnung) {
			this.ausschreibungsbezeichnung = ausschreibungsbezeichnung;
		}
		public String getAusschreibungsbezeichnername() {
			return ausschreibungsbezeichnername;
		}
		public void setAusschreibungsbezeichnername(String ausschreibungsbezeichnername) {
			this.ausschreibungsbezeichnername = ausschreibungsbezeichnername;
		}
		public Date getErstellungsdatum() {
			return erstellungsdatum;
		}
		public void setErstellungsdatum(Date erstellungsdatum) {
			this.erstellungsdatum = erstellungsdatum;
		}
		public Bewerbungsstatus getStatusBewerbungsstatus() {
			return statusBewerbungsstatus;
		}
		public void setStatusBewerbungsstatus(Bewerbungsstatus statusBewerbungsstatus) {
			this.statusBewerbungsstatus = statusBewerbungsstatus;
		}
		
		
	}

	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Projekte der Person ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
			Vector<ausschreibungBewerbungHybrid> hybrid = new Vector();
			
			bewerbungen = result;
			
			for(int i=0;i<result.size();i++){
				
				projektmarktplatzVerwaltung.getAusschreibungById(result.get(i).getAusschreibungId(), new AusschreibungAnzeigenCallback());
				projektmarktplatzVerwaltung.getOrganisationseinheitById(ausschreibung.get(i).getAusschreibenderId(), new AusschreibenderAnzeigenCallback());
				ausschreibungBewerbungHybrid localHybrid = new ausschreibungBewerbungHybrid();
				localHybrid.setAusschreibungsbezeichnung(ausschreibung.get(i).getBezeichnung());
				if (ausschreibender.get(i) instanceof Person){
					Person localPerson = (Person) ausschreibender.get(i);
					localHybrid.setAusschreibungsbezeichnername(localPerson.getVorname());
				} else if(ausschreibender.get(i) instanceof Team){
					Team localTeam = (Team) ausschreibender.get(i);
					localHybrid.setAusschreibungsbezeichnername(localTeam.getName());
				} else if (ausschreibender.get(i) instanceof Unternehmen){
					Unternehmen localUnternehmen = (Unternehmen) ausschreibender.get(i);
					localHybrid.setAusschreibungsbezeichnername(localUnternehmen.getName());
				}
				else{
					localHybrid.setAusschreibungsbezeichnername("Konnte nicht gesetzt werden");
				}
				localHybrid.setBewerbungId(result.get(i).getId());
				localHybrid.setErstellungsdatum(result.get(i).getErstellungsdatum());
				localHybrid.setStatusBewerbungsstatus(result.get(i).getStatus());
				
				hybrid.add(localHybrid);
			}
			ausBewHybrid=hybrid;
			};
		}
	
	
	 private class AusschreibungAnzeigenCallback implements AsyncCallback <Ausschreibung>{
		
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Bewerbung ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Ausschreibung result) {
		ausschreibung.add(result);		
		}

	};
	 
	 private class AusschreibenderAnzeigenCallback implements AsyncCallback<Organisationseinheit>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Organisationseinheit result) {
			ausschreibender.add(result);
		}
		 
	 };
}
		
		
		
		
	

