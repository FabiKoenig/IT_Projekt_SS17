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
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.BeteiligungenForm.BeteiligungProjektHybrid;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;



public class BeteiligungaufProjektForm extends Showcase {

	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	HorizontalPanel buttonPanel = new HorizontalPanel();
	CellTable<BeteiligungProjektHybrid> dataGrid = new CellTable<BeteiligungProjektHybrid>();
	Vector<Beteiligung> ausschreibung = new Vector<Beteiligung>();
	public Vector<BeteiligungProjektHybrid> beteiligungen = new Vector<BeteiligungProjektHybrid>();

	Button btn_zurueck = new Button("Zurück");
	Navigation navigation = null;
	
	private Projekt p ;
	public BeteiligungaufProjektForm(Projekt P, Navigation navigation){
		this.p = P ;
		this.navigation=navigation;
	}
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Beteilgung des Projektes";
	}

	@Override
	protected void run() {
		
		

		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		btn_zurueck.setStylePrimaryName("cell-btn");
		projektmarktplatzVerwaltung.getBeteiligungByForeignProjekt(p, new getBeteiligung());
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_beteiligterBez = new TextColumn<BeteiligungenForm.BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getBeteiligter();
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_Umfang = new TextColumn<BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				Integer umfangTemp = object.getBeteiligungUmfang();
				return umfangTemp.toString();
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_startDatum = new TextColumn<BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getStartDatum().toString();
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_endDatum = new TextColumn<BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		};
		
		
		dataGrid.addColumn(tc_beteiligungen_beteiligterBez, "Beteiligter");
		dataGrid.addColumn(tc_beteiligungen_Umfang, "Umfang");
		dataGrid.addColumn(tc_beteiligungen_startDatum, "Startdatum");
		dataGrid.addColumn(tc_beteiligungen_endDatum, "Enddatum");
	
		dataGrid.setWidth("100%");
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		final SingleSelectionModel<BeteiligungProjektHybrid> selectionModel = new SingleSelectionModel<>();
		
		dataGrid.setSelectionModel(selectionModel);	
		
		buttonPanel.add(btn_zurueck);
		
	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		
		btn_zurueck.setStylePrimaryName("navi-button");
	
		this.add(buttonPanel);
		this.add(dataGrid);
		
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				navigation.reload();			
			}
		});
		
		
//		projektmarktplatzVerwaltung.getBeteiligungByForeignProjekt(p, new getBeteiligung());
		
	}
	
	
	
//	private class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Organisationseinheit result) {		
//			if (result != null) {
//				projektmarktplatzVerwaltung.getBeteiligungByForeignProjekt(result, new ););
//			}			
//		}
//	
//	}
	

	private class getBeteiligung implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
			
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {

			
			for (Beteiligung beteiligung : result) {
				final BeteiligungProjektHybrid localHybrid = new BeteiligungProjektHybrid();
				
				localHybrid.setBeteiligungId(beteiligung.getId());
				localHybrid.setBeteiligungUmfang(beteiligung.getUmfang());
				localHybrid.setStartDatum(beteiligung.getStartDatum());
				localHybrid.setEndDatum(beteiligung.getEndDatum());

				projektmarktplatzVerwaltung.getOrganisationseinheitById(beteiligung.getBeteiligterId(), new AsyncCallback<Organisationseinheit>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Anzeigen der Organisationseinheit ist fehlgeschlagen!");
						
					}

					@Override
					public void onSuccess(Organisationseinheit result) {
						if(result instanceof Person){							
							String beteiligtePerson = ((Person) result).getVorname() + " " + ((Person) result).getNachname();
							localHybrid.setBeteiligter(beteiligtePerson);
						} else if(result instanceof Team){
							String beteiligtesTeam = ((Team) result).getName();
							localHybrid.setBeteiligter(beteiligtesTeam);
						} else if(result instanceof Unternehmen){					
							String beteiligtesUnternehmen = ((Unternehmen) result).getName();
							localHybrid.setBeteiligter(beteiligtesUnternehmen);
						}	

						beteiligungen.add(localHybrid);
						
						
						final ListDataProvider dataProvider = new ListDataProvider();
						SimplePager pager;
						SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
						pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
						pager.setDisplay(dataGrid);
						dataProvider.addDataDisplay(dataGrid);
						dataProvider.setList(new ArrayList<BeteiligungProjektHybrid>(beteiligungen));
						pager.setPageSize(20);
						
						HorizontalPanel hp_pager = new HorizontalPanel();
						hp_pager.setWidth("100%");
						hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						hp_pager.add(pager);
						add(hp_pager);
						
						
						dataGrid.setRowCount(beteiligungen.size(), true);
						dataGrid.setRowData(0, beteiligungen);
					}
				});
							
				
				
			}
			
			

			
			
			}
		
		}
	
	
	
	
	
	
}
