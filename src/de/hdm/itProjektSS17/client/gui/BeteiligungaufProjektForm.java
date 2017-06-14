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


/**
 * @see de.hdm.itProjektSS17.client.client.showcase;
 * @author Tim
 *
 */
public class BeteiligungaufProjektForm extends Showcase {
	
	/**
	 * GUI-Elemente & globale Variablen/ Objekte anlegen
	 */
	SimplePager pager;
	
	HorizontalPanel hp_pager = new HorizontalPanel();
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
	
	/**
	 * HeadlineText returnen mit dem übergebenen Projekt
	 * @return String, Projekt
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Beteiligung des Projektes "+p.getName();
	}

	/**
	 * Nachdem alles vorbereitet ist wird die run-Methode gestartet. Diese ist eine abstrakte Methode
	 * in der die Subklassen implemntiert werden.
     */
	@Override
	protected void run() {
		
		/**
		 * Pager anlegen
		 */
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		
		btn_zurueck.setStylePrimaryName("cell-btn");
	
		/**
		 * Methode um Beteiligungen zu übergebnen Projekt zu erhalten, neuer CallBack wird aufgerufen.
		 * @param Projekt
		 */
		projektmarktplatzVerwaltung.getBeteiligungByForeignProjekt(p, new getBeteiligung());
		
		/**
		 * TextColumns anlegen und der Celltable hinzufügen
		 */
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
		
		
		/**
		 * SingleSelectionModel für die Klasse anlegen und der Celltable hinzufügen.
		 **/
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
		this.add(hp_pager);
		
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				navigation.reload();			
			}
		});
		
		
//		projektmarktplatzVerwaltung.getBeteiligungByForeignProjekt(p, new getBeteiligung());
		
	}
	
/**
 * Bei erfolgreichem Callback wird ein Vector mit Beteiligungen als result zurückgibt.
 * 
 * @author Tim
 *
 */
	private class getBeteiligung implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());
			
		}
		
		@Override
		public void onSuccess(Vector<Beteiligung> result) {

	/**
	 * Vector mit Beteiligungen wird durchgegangen und jeweils deren verschiedene Attribute in das Objekt localHybrid
	 * gesetzt.
	 */
			for (Beteiligung beteiligung : result) {
				final BeteiligungProjektHybrid localHybrid = new BeteiligungProjektHybrid();
				
				localHybrid.setBeteiligungId(beteiligung.getId());
				localHybrid.setBeteiligungUmfang(beteiligung.getUmfang());
				localHybrid.setStartDatum(beteiligung.getStartDatum());
				localHybrid.setEndDatum(beteiligung.getEndDatum());

				/**
				 * Bei erfolgreichem Callback wird die Organisationseinheit zu der übergebenen Beteiligung als
				 * result zurückgegeben
				 */
				projektmarktplatzVerwaltung.getOrganisationseinheitById(beteiligung.getBeteiligterId(), new AsyncCallback<Organisationseinheit>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Anzeigen der Organisationseinheit ist fehlgeschlagen!");
		
					}

					@Override
					public void onSuccess(Organisationseinheit result) {
						/**
						 * Prüfung ob die Organisationseinheit eine Person, ein Team oder ein Unternehmen ist.
						 * In Abhängingkeit, welche Art von Organisationseinheit das result ist werden die
						 * jeweiligen Attribute gesetzt.
						 */
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
						
						/**
						 * Das localHybrid-Objekt wird dem Vector hinzugefügt
						 */
						beteiligungen.add(localHybrid);
						
						
						final ListDataProvider dataProvider = new ListDataProvider();
						
					
						/**
						 * Pager konfigurieren und dem Panel hinzifügen
						 */
						pager.setDisplay(dataGrid);
						dataProvider.addDataDisplay(dataGrid);
						dataProvider.setList(new ArrayList<BeteiligungProjektHybrid>(beteiligungen));
						pager.setPageSize(1);
						
						
						hp_pager.setWidth("100%");
						hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						hp_pager.add(pager);
						
						
						/**
						 * Die Daten werden in der Celltable gesetzt.
						 */
						dataGrid.setRowCount(beteiligungen.size(), true);
						dataGrid.setRowData(0, beteiligungen);
					}
				});
							
				
				
			}
				
			}
		
		}
	
	
	
	
	
	
}
