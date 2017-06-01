package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung.Ausschreibungsstatus;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;

public class StellenauschreibungForm extends Showcase {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static  Vector<Ausschreibung> ausschreibungen = new Vector<>();
	private static  Vector<Projekt> projekte = new Vector<>();
	private static Vector <Organisationseinheit> ausschreibender = new Vector();
	
	private static Vector <projektAusschreibungHybrid> proAusHybrid = new Vector();
	Projektmarktplatz p = new Projektmarktplatz();
	Button btn_bewerben = new Button("Bewerben");
	Button btn_Text = new Button("Ausschreibungstext anzeigen");
	CellTable cellTable= new CellTable();
	HorizontalPanel panel_Ausschreibung = new HorizontalPanel();
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Stellenausschreibung";
	}

	
	
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		RootPanel.get("Details").setWidth("70%");
		cellTable.setWidth("100%", true);
		
		btn_Text.setStylePrimaryName("navi-button");
		btn_bewerben.setStylePrimaryName("navi-button");
		
		this.add(panel_Ausschreibung);
		panel_Ausschreibung.add(btn_Text);
		panel_Ausschreibung.add(btn_bewerben);
		
		projektmarktplatzVerwaltung.getProjektmarktplatzById(IdentityMarketChoice.getSelectedProjectMarketplaceId(), new GetProjektmarktplatz());
//		{
//
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub					
//			}
//
//			@Override
//			public void onSuccess(Organisationseinheit result) {
//			ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
//		
//			projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new AusschreibungAnzeigenCallback());	
//					
//			}
//		});
	
		
	
	
		
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<projektAusschreibungHybrid> AusschreibenderColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getAusschreibungsbezeichnername();
			}
		};
		cellTable.addColumn(AusschreibenderColumn, "Ausschreibender");

		
		TextColumn<projektAusschreibungHybrid> ProjektColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getProjektbezeichnung();
			}
		};
		cellTable.addColumn(ProjektColumn, "Projekt");

		
		
		TextColumn<projektAusschreibungHybrid> BezeichnungColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		cellTable.addColumn(BezeichnungColumn, "Bezeichnung");

		
		
		
		TextColumn<projektAusschreibungHybrid> BewerbungsfristColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getBewerbungsfrist().toString();
			}
		};
		cellTable.addColumn(BewerbungsfristColumn, "Bewerbungsfrist");

		
		
			
		
		final SingleSelectionModel<projektAusschreibungHybrid> selectionModel = new SingleSelectionModel<>();
		cellTable.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
	
		
		cellTable.setWidth("100%");
		
		this.add(cellTable);
	}

	

	private class projektAusschreibungHybrid{
		
		private String bezeichnung;
		private String projektbezeichnung;
		private String ausschreibungsbezeichnername;
		private Date bewerbungsfrist;
		private Ausschreibungsstatus ausschreibungstatus;
		private int ausschreibungId;
		
		public int getAusschreibungId() {
			return ausschreibungId;
		}
		public void setAusschreibungId(int ausschreibungId) {
			this.ausschreibungId = ausschreibungId;
		}
		public String getBezeichnung() {
			return bezeichnung;
		}
		public void setBezeichnung(String bezeichnung) {
			this.bezeichnung = bezeichnung;
		}
		public String getProjektbezeichnung() {
			return projektbezeichnung;
		}
		public void setProjektbezeichnung(String projektbezeichnung) {
			this.projektbezeichnung = projektbezeichnung;
		}
		public String getAusschreibungsbezeichnername() {
			return ausschreibungsbezeichnername;
		}
		public void setAusschreibungsbezeichnername(String ausschreibungsbezeichnername) {
			this.ausschreibungsbezeichnername = ausschreibungsbezeichnername;
		}
		public Date getBewerbungsfrist() {
			return bewerbungsfrist;
		}
		public void setBewerbungsfrist(Date bewerbungsfrist) {
			this.bewerbungsfrist = bewerbungsfrist;
		}
		public Ausschreibungsstatus getAusschreibungstatus() {
			return ausschreibungstatus;
		}
		public void setAusschreibungstatus(Ausschreibungsstatus ausschreibungstatus) {
			this.ausschreibungstatus = ausschreibungstatus;
		}
	}
	
private class AusschreibungAnzeigenCallback implements AsyncCallback<Vector<Ausschreibung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			
			Vector<projektAusschreibungHybrid> hybrid = new Vector<>();

			ausschreibungen = result;
			
			for(int i=0;i<result.size();i++){
				projektmarktplatzVerwaltung.getProjektById(result.get(i).getProjektId(), new ProjekteAnzeigenCallback());
				projektmarktplatzVerwaltung.getOrganisationseinheitById(ausschreibungen.get(i).getAusschreibenderId(), new AusschreibenderAnzeigenCallback());
				
			
				projektAusschreibungHybrid localHybrid = new projektAusschreibungHybrid();
				localHybrid.setProjektbezeichnung(projekte.get(i).getName());
				if (ausschreibender.get(i) instanceof Person){
					Person localPerson = (Person) ausschreibender.get(i);
					localHybrid.setAusschreibungsbezeichnername(localPerson.getNachname());
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
				localHybrid.setAusschreibungId(result.get(i).getId());
				localHybrid.setBewerbungsfrist(result.get(i).getBewerbungsfrist());
				localHybrid.setBezeichnung(result.get(i).getBezeichnung());
				localHybrid.setAusschreibungstatus(result.get(i).getStatus());
				
				if(localHybrid.getAusschreibungstatus().equals(Ausschreibungsstatus.laufend))
				{
				hybrid.add(localHybrid);
				}
			}
			
			proAusHybrid=hybrid;
			cellTable.setRowCount(proAusHybrid.size(), true);
			cellTable.setRowData(0,proAusHybrid);
			};
		}
	
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
		
		private class ProjekteAnzeigenCallback implements AsyncCallback <Projekt>{
			
			
			@Override
			public void onFailure(Throwable caught) {
				
				Window.alert("Das Anzeigen der Ausschreibung ist fehlgeschlagen!");
				
			}
			@Override
			public void onSuccess(Projekt result) {
			projekte.add(result);		
			}
		
		};
		
		
		 
		 private class GetProjekte implements AsyncCallback<Vector<Projekt>>{
			
			 @Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			
				@Override
				public void onSuccess(Vector <Projekt> result) {
					
					for (Projekt projekt : result) {
						
						projektmarktplatzVerwaltung.getAusschreibungByForeignProjekt(projekt, new AusschreibungAnzeigenCallback());
					}
				}
		 }
		 private class GetProjektmarktplatz implements AsyncCallback<Projektmarktplatz>{
				
			 @Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			
					 
				@Override
				public void onSuccess(Projektmarktplatz result) {
					projektmarktplatzVerwaltung.getProjektByForeignProjektmarktplatz(result, new GetProjekte());
					
				}}
		 
		 
		 
			
		}
	

