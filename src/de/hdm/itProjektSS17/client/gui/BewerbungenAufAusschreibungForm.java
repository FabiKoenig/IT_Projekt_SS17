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
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;
import de.hdm.itProjektSS17.shared.bo.Bewertung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class BewerbungenAufAusschreibungForm extends VerticalPanel{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	Vector<BewertungBewerbungHybrid> hybrid = new Vector<BewertungBewerbungHybrid>();
	CellTable<BewertungBewerbungHybrid> dataGrid = new CellTable<BewertungBewerbungHybrid>();
	Vector<Bewerbung> bewerbungen = new Vector<Bewerbung>();
	Vector <String> bewerber = new Vector<String>();
	Vector<Bewertung> bewertungen = new Vector<Bewertung>();
	Bewertung bewertung = null;
	//String bewerber = null;

//	Button bewerberZusagenButton = new Button("Bewerber annehmen");
	Button bewerbungBewertenButton = new Button("Bewerbung bewerten");
	Button partnerprofilAnzeigen = new Button("Partnerprofil des Bewerbers");
	Button zurueckButton = new Button("Zurück");
	Navigation navigation = null;
	private IdentityMarketChoice identityMarketChoice=null;

	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	public BewerbungenAufAusschreibungForm(int ausschreibungId, final Navigation navigation, final IdentityMarketChoice identityMarketChoice){
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		projektmarktplatzVerwaltung.getBewerbungByForeignAusschreibungId(ausschreibungId, new GetBewerbungenCallback());
		
		
		//Erstellen der TextColumns der CellTable
//		TextColumn<BewertungBewerbungHybrid> bewerbungstextColumn = new TextColumn<BewertungBewerbungHybrid>(){
//
//			@Override
//			public String getValue(BewertungBewerbungHybrid object) {
//				return object.getBewerbungstext();
//			}
//		};
//		
		
		TextColumn<BewertungBewerbungHybrid> erstellungsdatumColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getErstellungsdatum().toString();
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> bewerbungsstatusColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getBewerbungsstatus().toString();
			}
		};
		
//		TextColumn<BewertungBewerbungHybrid> stellungsnahmeColumn = new TextColumn<BewertungBewerbungHybrid>(){
//
//			@Override
//			public String getValue(BewertungBewerbungHybrid object) {
//				return object.getStellungsnahme();
//			}
//		};
		
		TextColumn<BewertungBewerbungHybrid> bewertungsWertColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				double wert = object.getBewertungWert();
				String stringWert = Double.toString(wert);
				return stringWert;
			}
		};
		
		TextColumn<BewertungBewerbungHybrid> bewerberColumn = new TextColumn<BewertungBewerbungHybrid>(){

			@Override
			public String getValue(BewertungBewerbungHybrid object) {
				return object.getBewerber();
			}
		};
		
		dataGrid.addColumn(bewerberColumn, "Bewerber");
		//dataGrid.addColumn(bewerbungstextColumn, "Bewerbungstext");
		dataGrid.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		dataGrid.addColumn(bewerbungsstatusColumn, "Bewerbungsstatus");
		//dataGrid.addColumn(stellungsnahmeColumn, "Stellungsnahme");
		dataGrid.addColumn(bewertungsWertColumn, "Bewertung");
		
		dataGrid.setWidth("100%");
		
		final SingleSelectionModel<BewertungBewerbungHybrid> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Hinzufügen der Buttons zum ButtonPanel
		buttonPanel.add(zurueckButton);
//		buttonPanel.add(bewerbungBewertenButton);
//		buttonPanel.add(bewerberZusagenButton);
		buttonPanel.add(partnerprofilAnzeigen);
		buttonPanel.add(bewerbungBewertenButton);

		
		
		//Stylen der Buttons
		zurueckButton.setStylePrimaryName("cell-btn");

//		bewerbungBewertenButton.setStylePrimaryName("navi-button");
//		bewerberZusagenButton.setStylePrimaryName("navi-button");
		bewerbungBewertenButton.setStylePrimaryName("cell-btn");
		partnerprofilAnzeigen.setStylePrimaryName("cell-btn");
		//bewerberZusagenButton.setStylePrimaryName("navi-button");
		//texteAnzeigenButton.setStylePrimaryName("navi-button");

		
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
	
		
		/**
		 * CLICK-HANDLER
		 */
		bewerbungBewertenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(selectionModel.getSelectedObject() == null){
					Window.alert("Bitte wähle zuerst eine Bewerbung aus.");
				} else{
				DialogBoxBewerbungBewerten dbb = new DialogBoxBewerbungBewerten(selectionModel.getSelectedObject(), identityMarketChoice, navigation);
				dbb.center();
				dbb.show();
				}		
			}
		});
		
		
//		bewerbungstextButton.addClickHandler(new ClickHandler(){
//			public void onClick(ClickEvent event) {
//				if (selectionModel.getSelectedObject() == null)
//				{
//					Window.alert("Bitte wählen Sie eine Bewerbung aus");
//				}
//				DialogBoxBewerbungstext text = new DialogBoxBewerbungstext(selectionModel.getSelectedObject().getBewerbungstext());
//				int left = Window.getClientWidth() / 3;
//				int top = Window.getClientHeight() / 8;
//				text.setPopupPosition(left, top);
//				text.show();
//			}
//		});
		
		zurueckButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new MeineAusschreibungenForm(identityMarketChoice, navigation));
//				navigation.reload();				
			}
		});
		
		partnerprofilAnzeigen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(selectionModel.getSelectedObject() != null){
					int partnerprofilId = selectionModel.getSelectedObject().getPartnerprofilId();
				
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(partnerprofilId, true, identityMarketChoice, navigation));
					
					} else {
						Window.alert("Bitte wähle zuerst eine Bewerbung aus.");
				}
			}
		});
	}
	
	
	public class BewertungBewerbungHybrid{
		
		private String bewerbungstext;
		private Bewerbungsstatus bewerbungsstatus;
		private Date erstellungsdatum;
		private String stellungsnahme;
		private double bewertungWert;
		private String bewerber;
		private int bewerbungId;
		private int bewertungId;
		private int ausschreibungId;
		private int projektId;
		private int partnerprofilId;
		private int umfang;
		private int bewerberId;
		private Date startdatum;
		private Date enddatum;

		
		public int getBewertungId() {
			return bewertungId;
		}
		
		public void setBewertungId(int bewertungId) {
			this.bewertungId = bewertungId;
		}
		
		public String getBewerber() {
			return bewerber;
		}

		public void setBewerber(String bewerber) {
			this.bewerber = bewerber;
		}
		
		public String getBewerbungstext() {
			return bewerbungstext;
		}
		
		public void setBewerbungstext(String bewerbungstext) {
			this.bewerbungstext = bewerbungstext;
		}
		
		public Bewerbungsstatus getBewerbungsstatus() {
			return bewerbungsstatus;
		}
		
		public void setBewerbungsstatus(Bewerbungsstatus bewerbungsstatus) {
			this.bewerbungsstatus = bewerbungsstatus;
		}
		
		public Date getErstellungsdatum() {
			return erstellungsdatum;
		}
		
		public void setErstellungsdatum(Date erstellungsdatum) {
			this.erstellungsdatum = erstellungsdatum;
		}
		
		public String getStellungsnahme() {
			return stellungsnahme;
		}
		
		public void setStellungsnahme(String stellungsnahme) {
			this.stellungsnahme = stellungsnahme;
		}
		
		public double getBewertungWert() {
			return bewertungWert;
		}
		
		public void setBewertungWert(double bewertungWert) {
			this.bewertungWert = bewertungWert;
		}
		
		public int getBewerbungId() {
			return bewerbungId;
		}

		public void setBewerbungId(int bewerbungId) {
			this.bewerbungId = bewerbungId;
		}

		public int getAusschreibungId() {
			return ausschreibungId;
		}

		public void setAusschreibungId(int ausschreibungId) {
			this.ausschreibungId = ausschreibungId;
		}

		public int getProjektId() {
			return projektId;
		}

		public void setProjektId(int projektId) {
			this.projektId = projektId;
		}


		public int getPartnerprofilId() {
			return partnerprofilId;
		}

		public void setPartnerprofilId(int partnerprofilId) {
			this.partnerprofilId = partnerprofilId;
		}

		public int getBewerberId() {
			return bewerberId;
		}

		public void setBewerberId(int bewerberId) {
			this.bewerberId = bewerberId;
		}

		public int getUmfang() {
			return umfang;
		}

		public void setUmfang(int umfang) {
			this.umfang = umfang;
		}

		public Date getStartdatum() {
			return startdatum;
		}

		public void setStartdatum(Date startdatum) {
			this.startdatum = startdatum;
		}

		public Date getEnddatum() {
			return enddatum;
		}

		public void setEnddatum(Date enddatum) {
			this.enddatum = enddatum;
		}
		

		
	
	}

	
	private class GetBewerbungenCallback implements AsyncCallback<Vector<Bewerbung>>{
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.toString());	
		}
		public void onSuccess(Vector<Bewerbung> result) {
			
			bewerbungen = result;			
			
			for(int i=0;i<bewerbungen.size();i++){
				
				final BewertungBewerbungHybrid localHybrid = new BewertungBewerbungHybrid();
				localHybrid.setBewerberId(bewerbungen.get(i).getOrganisationseinheitId());
				
				projektmarktplatzVerwaltung.getBewertungByForeignBewerbung(bewerbungen.get(i), new AsyncCallback<Bewertung>() {
					public void onFailure(Throwable caught) {
						Window.alert("Fehler: " + caught.toString());
					}
					public void onSuccess(Bewertung result) {
						localHybrid.setStellungsnahme(result.getStellungnahme());
						localHybrid.setBewertungWert(result.getWert());
						localHybrid.setBewertungId(result.getId());
						
						dataGrid.setRowCount(hybrid.size(), true);
						dataGrid.setRowData(0,hybrid);
					}
				});
				projektmarktplatzVerwaltung.getOrganisationseinheitById(bewerbungen.get(i).getOrganisationseinheitId(), new AsyncCallback<Organisationseinheit>() {
					public void onFailure(Throwable caught) {
						Window.alert("Fehler: " + caught.toString());
					}
					public void onSuccess(Organisationseinheit result) {
						if(result instanceof Person){
//							bewerber.add(((Person) result).getVorname() + " " + ((Person) result).getNachname()); 
							String bewerber = ((Person) result).getVorname() + " " + ((Person) result).getNachname();
							localHybrid.setBewerber(bewerber);
						} else if(result instanceof Team){
//							bewerber.add(((Team) result).getName());
							String bewerber = ((Team) result).getName();
							localHybrid.setBewerber(bewerber);
						} else if(result instanceof Unternehmen){
//							bewerber.add(((Unternehmen) result).getName());
							String bewerber = ((Unternehmen) result).getName();
							localHybrid.setBewerber(bewerber);
						}	
						localHybrid.setPartnerprofilId(result.getPartnerprofilId());
						dataGrid.setRowCount(hybrid.size(), true);
						dataGrid.setRowData(0,hybrid);
					}
				});
				
				//Erstellen einer neuen Instanz unserer Hybrid-Klasse BewertungBewerbungHybrid
				
				localHybrid.setBewerbungId(bewerbungen.get(i).getId());
				localHybrid.setBewerbungstext(bewerbungen.get(i).getBewerbungstext());
				localHybrid.setAusschreibungId(bewerbungen.get(i).getAusschreibungId());
				localHybrid.setErstellungsdatum(bewerbungen.get(i).getErstellungsdatum());
				localHybrid.setBewerbungsstatus(bewerbungen.get(i).getStatus());
				
				hybrid.add(localHybrid);
				
				
			}
			
			
			final ListDataProvider dataProvider = new ListDataProvider();
			SimplePager pager;
			SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
			pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
			pager.setDisplay(dataGrid);
			dataProvider.addDataDisplay(dataGrid);
			dataProvider.setList(new ArrayList<BewertungBewerbungHybrid>(hybrid));
			pager.setPageSize(20);
			
			HorizontalPanel hp_pager = new HorizontalPanel();
			hp_pager.setWidth("100%");
			hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			hp_pager.add(pager);
			add(hp_pager);
						
		}
		
	}
	
//	private class GetBewertungCallback implements AsyncCallback<Bewertung>{
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: " + caught.toString());
//		}
//		public void onSuccess(Bewertung result) {
//			
//			bewertungen.add(result);
//			
//		}
//		
//	}
	
//	private class GetBewerberCallback implements AsyncCallback<Organisationseinheit>{
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: " + caught.toString());
//		}
//
//		public void onSuccess(Organisationseinheit result) {
//			
//			if(result instanceof Person){
//				bewerber.add(((Person) result).getVorname() + " " + ((Person) result).getNachname()); 
//			} else if(result instanceof Team){
//				bewerber.add(((Team) result).getName());
//			} else if(result instanceof Unternehmen){
//				bewerber.add(((Unternehmen) result).getName());
//			}
//		}		
//	}
	
}

