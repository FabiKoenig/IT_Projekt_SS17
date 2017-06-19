package de.hdm.itProjektSS17.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Beteiligung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class BeteiligungenForm extends Showcase{

	/**
	 * GUI-Elemente und globale Variablen und Objekte deklarieren.
	 */
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public BeteiligungenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}

	private Button btn_beteiligungLoeschen = new Button("Beteiligung löschen");
	private CellTable<BeteiligungProjektHybrid> ct_beteiligungen = new CellTable<>();
	private SingleSelectionModel<BeteiligungProjektHybrid> ssm_beteiligungen = new SingleSelectionModel<>();
	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private Vector<Beteiligung> beteiligungen = new Vector<Beteiligung>();
	private Vector<BeteiligungProjektHybrid> hybrid = new Vector<BeteiligungProjektHybrid>();
	private BeteiligungProjektHybrid selectedObject = new BeteiligungProjektHybrid();
	
	/**
	 * Headlinetext returnen
	 * @return String
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Beteiligungen";
	}

	/**
	 * Nachdem alles vorbereitet ist wird die run-Methode gestartet. Diese ist eine abstrakte Methode
	 * in der die Subklassen implemntiert werden.
     */
	@Override
	protected void run() {
		/**
		 * RootPanel wird auf 75% gesetzt, davon soll die Celltable 100% sein.
		 * Vector beteiligungen und hybrid werden gecleart.
		 * Organisationseinheit-Objekt wird erstellt und die ausgewählte Id gesetzt.
		 */
		RootPanel.get("Details").setWidth("75%");
		ct_beteiligungen.setWidth("100%", true);
		beteiligungen.clear();
		hybrid.clear();
		Organisationseinheit oTemp = new Organisationseinheit();
		oTemp.setId(identityMarketChoice.getSelectedIdentityId());
		
		/**
		 * Methode um die Beteiligungen der ausgewählten Organisationseinheit zu erhalten.
		 * Neuer CallBack wird hierzu aufgerufen.
		 */
		projektmarktplatzVerwaltung.getBeteiligungByForeignOrganisationseinheit(oTemp, new BeteiligungenByOrganisationseinheitCallBack());
		
		/**
		 * Erstellen der TextColumns
		 */
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_projektBez = new TextColumn<BeteiligungenForm.BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getProjektBezeichnung();
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_Umfang = new TextColumn<BeteiligungenForm.BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				Integer umfangTemp = object.getBeteiligungUmfang();
				return umfangTemp.toString() + " Tage";
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_startDatum = new TextColumn<BeteiligungenForm.BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getStartDatum().toString();
			}
		
		};
		
		TextColumn<BeteiligungProjektHybrid> tc_beteiligungen_endDatum = new TextColumn<BeteiligungenForm.BeteiligungProjektHybrid>() {

			@Override
			public String getValue(BeteiligungProjektHybrid object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		};
		
		/**
		 * Butten Click-Handler hinzugügen
		 */
		btn_beteiligungLoeschen.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});

		btn_beteiligungLoeschen.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				/**
				 * Neues Beteilung-Objekt erstellen und die Id des ausgewählten Objekts setzen.
				 * Dieses Objekt wird dann zum löschen übergeben.
				 * Neuer Callback wird erstellt.
				 */
				Beteiligung beteiligungTemp = new Beteiligung();
				beteiligungTemp.setId(ssm_beteiligungen.getSelectedObject().getBeteiligungId());
				projektmarktplatzVerwaltung.deleteBeteiligung(beteiligungTemp, new BeteiligungLoeschenCallback());
				navigation.reload();
			}
		});

		/**
		 * TextColumns zur Celltable hinzufügen
		 */
		ct_beteiligungen.setSelectionModel(ssm_beteiligungen);
		ct_beteiligungen.addColumn(tc_beteiligungen_projektBez, "Beteiligung bei");
		ct_beteiligungen.addColumn(tc_beteiligungen_Umfang, "Umfang");
		ct_beteiligungen.addColumn(tc_beteiligungen_startDatum, "Startdatum");
		ct_beteiligungen.addColumn(tc_beteiligungen_endDatum, "Enddatum");
		
		btn_beteiligungLoeschen.setStylePrimaryName("cell-btn");
		this.setSpacing(8);
		this.add(btn_beteiligungLoeschen);
		this.add(ct_beteiligungen);
	}
	
	/**
	 * Bei erfolgreichem Callback wird ein Vector mit Beteiligungen als result zurückgeliefert.
	 * @author Tim
	 *
	 */
	private class BeteiligungenByOrganisationseinheitCallBack implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Beteiligungen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {
			/**
			 * Das result mit Beteiligungen werden dem Vector <code>beteiligungen</code> hinzugefügt
			 * Zu den Beteiligungen sollen die Projekte übergeben werden, hierzu wird das result übergeben und ein
			 * neuer Callback erstellt.
			 */
			beteiligungen.addAll(result);
			projektmarktplatzVerwaltung.getProjekteByBeteiligungen(result, new ProjektByBeteiligungCallBack());
		}
	}
	
	/**
	 * Bei erfolgreichem Callback wird ein Vector mit Projekten als result zurückgeliefert.
	 * @author Tim
	 *
	 */
	private class ProjektByBeteiligungCallBack implements AsyncCallback<Vector<Projekt>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projekte konnten nicht geladen werden");
		}

		/**
		 * Der <code>beteiligungen</code> Vector und der Vector <code>result</code> werden durchgegangen.
		 * Dabei wird überprüft ob sich das Projekt auf dem ausgwählten Projektmarktplatz befindet. Falls dies
		 * diese nicht auf dem ausgewhählten Projektmarktplatz sind werden diese removed.
		 * Zu allen Beteiligungen die sich auf dem Projektmarktplatz befinden werden die jeweiligen Attribute gesetzt und
		 * dem <code>hybrid</code> Vector hinzugefügt.
		 */
		@Override
		public void onSuccess(Vector<Projekt> result) {
			for (Beteiligung beteiligung : beteiligungen) {
				for (Projekt projekt : result){
					if(beteiligung.getProjektId()==projekt.getId() && projekt.getProjektmarktplatzId()!=identityMarketChoice.getSelectedProjectMarketplaceId()){					//	Window.alert(Integer.toString(projekt.getProjektmarktplatzId()) + Integer.toString(IdentityMarketChoice.getSelectedProjectMarketplaceId()));
						beteiligungen.remove(beteiligung);
					}
				}
			}

			for(Beteiligung beteiligung : beteiligungen){
				BeteiligungProjektHybrid localHybrid = new BeteiligungProjektHybrid();
				for(Projekt projekt : result){
					if(projekt.getId()==beteiligung.getProjektId()){
						localHybrid.setProjektBezeichnung(projekt.getName());
					}
				}
				localHybrid.setBeteiligungId(beteiligung.getId());
				localHybrid.setBeteiligungUmfang(beteiligung.getUmfang());
				localHybrid.setStartDatum(beteiligung.getStartDatum());
				localHybrid.setEndDatum(beteiligung.getEndDatum());
				hybrid.add(localHybrid);
			}
			

			
			
			ct_beteiligungen.setRowData(hybrid);
			ct_beteiligungen.setRowCount(hybrid.size(), true);
			

			/**
			 * Konfiguration den Pager und hinzufügen zum Panel.
			 */

			final ListDataProvider dataProvider = new ListDataProvider();
			SimplePager pager;
			SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
			pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
			pager.setDisplay(ct_beteiligungen);
			dataProvider.addDataDisplay(ct_beteiligungen);
			dataProvider.setList(new ArrayList<BeteiligungProjektHybrid>(hybrid));
			pager.setPageSize(10);
			
			HorizontalPanel hp_pager = new HorizontalPanel();
			hp_pager.setWidth("100%");
			hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			hp_pager.add(pager);
			add(hp_pager);

		}
	}
	
	/**
	 * Bei erfolgreichem Callbacl wird eine Meldung ausgegeben, dass die Beteiligung gelöscht wurde.
	 * @author Tim
	 *
	 */
	private class BeteiligungLoeschenCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Beteiligung wurde gelöscht!");
		}
		
	}
	
	/**
	 * Anlegen einer Hybrid Klasse für Beteiligung und Projekt
	 * @author Tim
	 *
	 */
	public static class BeteiligungProjektHybrid{
		
		/**
		 * Anlegen der Variablen
		 */
		private int beteiligungId;
		private int umfang;
		private Date startDatum;
		private Date endDatum;
		private String projektBezeichnung;
		private int beteiligungUmfang;
		private String beteiligter;
		
		/**
		 * Getter und setter Methoden
		 * 
		 * @return beteiligter
		 */
		public String getBeteiligter() {
			return beteiligter;
		}
		
		/**
		 * 
		 * @param beteiligter
		 */
		public void setBeteiligter(String beteiligter) {
			this.beteiligter = beteiligter;
		}
		
		/**
		 * 
		 * @param umfang
		 */
		public void setUmfang(int umfang) {
			this.umfang = umfang;
		}
		
		/**
		 * 
		 * @return int umfang
		 */
		public int getUmfang() {
			return umfang;
		}
		
		/**
		 * 
		 * @return String projektBezeichnung
		 */
		public String getProjektBezeichnung() {
			return projektBezeichnung;
		}
		/**
		 * 
		 * @param projektBezeichnung
		 */
		public void setProjektBezeichnung(String projektBezeichnung) {
			this.projektBezeichnung = projektBezeichnung;
		}
		/**
		 * 
		 * @return int beteiligungUmfang
		 */
		public int getBeteiligungUmfang() {
			return beteiligungUmfang;
		}
		/**
		 * 
		 * @param beteiligungUmfang
		 */
		public void setBeteiligungUmfang(int beteiligungUmfang) {
			this.beteiligungUmfang = beteiligungUmfang;
		}
		/***
		 * 
		 * @return Date startDatum
		 */
		public Date getStartDatum() {
			return startDatum;
		}
		/**
		 * 
		 * @param startDatum
		 */
		public void setStartDatum(Date startDatum) {
			this.startDatum = startDatum;
		}
		/**
		 * 
		 * @return Date endDatum
		 */
		public Date getEndDatum() {
			return endDatum;
		}
		/**
		 * 
		 * @param endDatum
		 */
		public void setEndDatum(Date endDatum) {
			this.endDatum = endDatum;
		}
		/**
		 * 
		 * @return int beteiligungId
		 */
		public int getBeteiligungId() {
			return beteiligungId;
		}
		/**
		 * 
		 * @param beteiligungId
		 */
		public void setBeteiligungId(int beteiligungId) {
			this.beteiligungId = beteiligungId;
		}
		
		
	}
	

}
