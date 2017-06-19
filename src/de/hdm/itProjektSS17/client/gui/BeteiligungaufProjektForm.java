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
	

	
	/**
	 * Anlegen von GUI-Elementen und globalen Variablen 
	 */
	private SimplePager pager;	
	private Navigation navigation = null;
	private HorizontalPanel hp_pager = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button btn_zurueck = new Button("Zurück");
	private CellTable<BeteiligungProjektHybrid> dataGrid = new CellTable<BeteiligungProjektHybrid>();
	private Vector<Beteiligung> ausschreibung = new Vector<Beteiligung>();
	private Projekt p ;
	public Vector<BeteiligungProjektHybrid> beteiligungen = new Vector<BeteiligungProjektHybrid>();
	
	/**
	 * Instanz der ProjektmarktplatzVerwaltungAsync abrufen.
	 */
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	Button btn_loeschen = new Button("Löschen");
	IdentityMarketChoice identityMarketChoice = null;
	public BeteiligungaufProjektForm(Projekt P, Navigation navigation, IdentityMarketChoice identityMarketChoice){

	/**
	 * Konstruktor, dem ein Projekt und eine Instanz der navigation übergeben wird 
	 * @param Projekt-Objekt
	 * @param navigation, Instanz der Navigation
	 * @param identitiyMarketChoice, Instanz der IdentityMarketChoice
	 */

		this.p = P ;
		this.navigation=navigation;
		this.identityMarketChoice=identityMarketChoice;
	}
	/**
	 * Setzen des Headline Textes
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Beteiligung des Projektes "+p.getName();
	}

	/**
	 * Methode die startet, sobald diese Form aufgerufen wird.
	 */
	@Override
	protected void run() {
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		btn_zurueck.setStylePrimaryName("cell-btn");
		btn_loeschen.setStylePrimaryName("cell-btn");
	
		/**
		 * Methode um Beteiligungen zu übergebnen Projekt zu erhalten, neuer CallBack wird aufgerufen.
		 * @param Projekt
		 */
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
				return umfangTemp.toString() + " Tage";
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
		buttonPanel.add(btn_loeschen);
	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
	
		this.add(buttonPanel);
		this.add(dataGrid);
		this.add(hp_pager);
		
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcase = new MeineProjektForm(identityMarketChoice, navigation);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);			
			}
		});
		
		btn_loeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Beteiligung tempBeteiligung = new Beteiligung();
				tempBeteiligung.setId(selectionModel.getSelectedObject().getBeteiligungId());
				projektmarktplatzVerwaltung.deleteBeteiligung(tempBeteiligung, new BeteiligungLoeschen());
			}
		});
		
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
						
					
						
						pager.setDisplay(dataGrid);
						dataProvider.addDataDisplay(dataGrid);
						dataProvider.setList(new ArrayList<BeteiligungProjektHybrid>(beteiligungen));
						pager.setPageSize(20);
						
						
						hp_pager.setWidth("100%");
						hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						hp_pager.add(pager);
						
						
						
						dataGrid.setRowCount(beteiligungen.size(), true);
						dataGrid.setRowData(0, beteiligungen);
					}
				});
							
				
				
			}
				
			}
		
		}
	
	
	private class BeteiligungLoeschen implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Beteiligung konnte nicht gelöscht werden");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Beteiligung wurde gelöscht.");
			navigation.reload();
		}
		
	}
	
	
}
