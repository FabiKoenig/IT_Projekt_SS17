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
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Beteiligungen";
	}

	@Override
	protected void run() {
		RootPanel.get("Details").setWidth("70%");
		ct_beteiligungen.setWidth("100%", true);
		beteiligungen.clear();
		hybrid.clear();
		Organisationseinheit oTemp = new Organisationseinheit();
		oTemp.setId(identityMarketChoice.getSelectedIdentityId());
		projektmarktplatzVerwaltung.getBeteiligungByForeignOrganisationseinheit(oTemp, new BeteiligungenByOrganisationseinheitCallBack());
		
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
		
		btn_beteiligungLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});

		btn_beteiligungLoeschen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Beteiligung beteiligungTemp = new Beteiligung();
				beteiligungTemp.setId(ssm_beteiligungen.getSelectedObject().getBeteiligungId());
				projektmarktplatzVerwaltung.deleteBeteiligung(beteiligungTemp, new BeteiligungLoeschenCallback());
				navigation.reload();
			}
		});

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
	
	private class BeteiligungenByOrganisationseinheitCallBack implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Beteiligungen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {
			
			beteiligungen.addAll(result);
			projektmarktplatzVerwaltung.getProjekteByBeteiligungen(result, new ProjektByBeteiligungCallBack());
		}
	}
	
	private class ProjektByBeteiligungCallBack implements AsyncCallback<Vector<Projekt>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projekte konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Projekt> result) {
			//Window.alert(Integer.toString(beteiligungen.size()));
			for (Beteiligung beteiligung : beteiligungen) {
				//Window.alert("Beteiligung: " + Integer.toString(beteiligung.getId()));
				for (Projekt projekt : result){
					//Window.alert("Projekt: " + Integer.toString(projekt.getId()));
					if(beteiligung.getProjektId()==projekt.getId() && projekt.getProjektmarktplatzId()!=identityMarketChoice.getSelectedProjectMarketplaceId()){
					//	Window.alert(Integer.toString(projekt.getProjektmarktplatzId()) + Integer.toString(IdentityMarketChoice.getSelectedProjectMarketplaceId()));
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
			
			final ListDataProvider dataProvider = new ListDataProvider();
			SimplePager pager;
			SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
			pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
			pager.setDisplay(ct_beteiligungen);
			dataProvider.addDataDisplay(ct_beteiligungen);
			dataProvider.setList(new ArrayList<BeteiligungProjektHybrid>(hybrid));
			pager.setPageSize(5);
			
			HorizontalPanel hp_pager = new HorizontalPanel();
			hp_pager.setWidth("100%");
			hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			hp_pager.add(pager);
			add(hp_pager);
			
			ct_beteiligungen.setRowData(hybrid);
			ct_beteiligungen.setRowCount(hybrid.size(), true);
		}
	}
	
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
	
	public static class BeteiligungProjektHybrid{
		
		private int beteiligungId;
		private int umfang;
		private Date startDatum;
		private Date endDatum;
		private String projektBezeichnung;
		private int beteiligungUmfang;
		private String beteiligter;
		
		public String getBeteiligter() {
			return beteiligter;
		}
		
		public void setBeteiligter(String beteiligter) {
			this.beteiligter = beteiligter;
		}
		
		public void setUmfang(int umfang) {
			this.umfang = umfang;
		}
		
		public int getUmfang() {
			return umfang;
		}
		
		public String getProjektBezeichnung() {
			return projektBezeichnung;
		}
		public void setProjektBezeichnung(String projektBezeichnung) {
			this.projektBezeichnung = projektBezeichnung;
		}
		public int getBeteiligungUmfang() {
			return beteiligungUmfang;
		}
		public void setBeteiligungUmfang(int beteiligungUmfang) {
			this.beteiligungUmfang = beteiligungUmfang;
		}
		public Date getStartDatum() {
			return startDatum;
		}
		public void setStartDatum(Date startDatum) {
			this.startDatum = startDatum;
		}
		public Date getEndDatum() {
			return endDatum;
		}
		public void setEndDatum(Date endDatum) {
			this.endDatum = endDatum;
		}
		public int getBeteiligungId() {
			return beteiligungId;
		}
		public void setBeteiligungId(int beteiligungId) {
			this.beteiligungId = beteiligungId;
		}
		
		
	}
	

}
