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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;
import de.hdm.itProjektSS17.shared.bo.Bewerbung.Bewerbungsstatus;


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
	CellTable<ausschreibungBewerbungHybrid> cellTable = new CellTable<ausschreibungBewerbungHybrid>();
	Vector<ausschreibungBewerbungHybrid> hybrid = new Vector<ausschreibungBewerbungHybrid>();
		
	HorizontalPanel panel_Bewerbung = new HorizontalPanel();

	Button btn_bewerbungloeschen = new Button("Bewerbung zurückziehen");
	Button btn_bewerbungstext = new Button ("Bewerbungstext anzeigen");
	//Button btn_bewerbungzur�ckziehen = new Button("Projektmarktplatz anlegen");


	
	protected String getHeadlineText(){
	return "Meine Bewerbungen";
	}
	
	protected void run() {
		
		RootPanel.get("Details").setWidth("70%");
		cellTable.setWidth("100%", true);
		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(),true);
		cellTable.setLoadingIndicator(null);
		
		//Stylen des Buttons
		btn_bewerbungloeschen.setStylePrimaryName("navi-button");
		btn_bewerbungstext.setStylePrimaryName("navi-button");
		
		this.setSpacing(8);
		this.add(panel_Bewerbung);
		panel_Bewerbung.add(btn_bewerbungloeschen);

		panel_Bewerbung.add(btn_bewerbungstext);
		projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(IdentityMarketChoice.getSelectedIdentityAsObject(), new BewerbungAnzeigenCallback());
	
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
		TextColumn<ausschreibungBewerbungHybrid> AusschreibungNameColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
			
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
		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderTeamColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				// TODO Auto-generated method stub
				return object.getTeam();
			}
		};

		
		TextColumn<ausschreibungBewerbungHybrid> AusschreibenderUnternehmenColumn = new TextColumn<ausschreibungBewerbungHybrid>() {

			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				// TODO Auto-generated method stub
				return object.getUnternehmen();
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
		cellTable.addColumn(AusschreibenderTeamColumn, "Team");
		cellTable.addColumn(AusschreibenderUnternehmenColumn, "Unternehmen");
		cellTable.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		cellTable.addColumn(statusColumn, "Status");
	
		
		cellTable.setWidth("100%");
		
		final SingleSelectionModel<ausschreibungBewerbungHybrid> selectionModel = new SingleSelectionModel<>();
		cellTable.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		btn_bewerbungstext.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie eine Bewerbung aus");
				}
				DialogBoxBewerbungstext text = new DialogBoxBewerbungstext(selectionModel.getSelectedObject().getBewerbungstext());
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				text.setPopupPosition(left, top);
				text.show();
		}
		});
		btn_bewerbungloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie die zu löschende Bewerbung aus");
				}else{				
					/*for(ausschreibungBewerbungHybrid abH : hybrid){
						if (selectionModel.getSelectedObject().getBewerbungId()==abH.getBewerbungId())
						{
							hybrid.remove(selectionModel.getSelectedObject());
						}
					}*/
						Bewerbung tempBew = new Bewerbung();
						tempBew.setId(selectionModel.getSelectedObject().getBewerbungId());
						projektmarktplatzVerwaltung.deleteBewerbung(tempBew, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Bewerbung konnte nicht zurückgezogen werden");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Bewerbung wurde zurückgezogen!");
								Navigation.reload();
							}
						});
				}


				
			
			}
		});
		
		
		
		this.add(cellTable);
	}

	private class ausschreibungBewerbungHybrid{
		
		private int bewerbungId;
		private String bewerbungstext;

		private String ausschreibungsbezeichnung;
		private String ausschreibungsbezeichnername;
		private Date erstellungsdatum;
		private Bewerbungsstatus statusBewerbungsstatus;
		private String Team;
		private String Unternehmen;
		
		public String getBewerbungstext() {
			return bewerbungstext;
		}
		public void setBewerbungstext(String bewerbungstext){
			this.bewerbungstext=bewerbungstext;
		}
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
		public String getTeam() {
			return Team;
		}
		public void setTeam(String team) {
			Team = team;
		}
		public String getUnternehmen() {
			return Unternehmen;
		}
		public void setUnternehmen(String unternehmen) {
			Unternehmen = unternehmen;
		}
		
		
	}

	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
			for(int i=0;i<result.size();i++){

				final Bewerbung localBewerbung = result.get(i);
				projektmarktplatzVerwaltung.getAusschreibungById(result.get(i).getAusschreibungId(), new AsyncCallback<Ausschreibung>() {


					@Override
					public void onFailure(Throwable caught) {
						
						Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
						
					}
					@Override
					public void onSuccess(Ausschreibung result) {
					final ausschreibungBewerbungHybrid localHybrid = new ausschreibungBewerbungHybrid();
					final Ausschreibung a = result;
					localHybrid.setAusschreibungsbezeichnung(result.getBezeichnung());
					projektmarktplatzVerwaltung.getProjektById(result.getProjektId(), new AsyncCallback<Projekt>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Projekt result) {
							// TODO Auto-generated method stub
							if (IdentityMarketChoice.getSelectedProjectMarketplaceId()!=result.getProjektmarktplatzId()){
								
								projektmarktplatzVerwaltung.getOrganisationseinheitById(a.getAusschreibenderId(), new AsyncCallback<Organisationseinheit>() { 
									
														@Override
															public void onFailure(Throwable caught) {
																// TODO Auto-generated method stub
																
															}
									
															@Override
														public void onSuccess(Organisationseinheit result) {
									
																if (result instanceof Person){
																	Person localPerson = (Person) result;
																	localHybrid.setAusschreibungsbezeichnername(localPerson.getNachname());
															} else if(result instanceof Team){
																	Team localTeam = (Team) result;
																	localHybrid.setAusschreibungsbezeichnername(localTeam.getName());
																} else if (result instanceof Unternehmen){
																	Unternehmen localUnternehmen = (Unternehmen) result;
																	localHybrid.setAusschreibungsbezeichnername(localUnternehmen.getName());
																}
																else{
																	localHybrid.setAusschreibungsbezeichnername("Konnte nicht gesetzt werden");
																}
																
																localHybrid.setBewerbungId(localBewerbung.getId());
																localHybrid.setErstellungsdatum(localBewerbung.getErstellungsdatum());
																localHybrid.setStatusBewerbungsstatus(localBewerbung.getStatus());
																localHybrid.setBewerbungstext(localBewerbung.getBewerbungstext());
																
																hybrid.add(localHybrid);
									
																cellTable.setRowCount(hybrid.size(), true);
																cellTable.setRowData(0,hybrid);
															}
														});
													
													}
												}
											});
										}
						
									});
					
								}	
							}
	
					 };

	}