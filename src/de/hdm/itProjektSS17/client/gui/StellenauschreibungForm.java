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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
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

	HorizontalPanel hp_pager = new HorizontalPanel();
	SimplePager pager;
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public StellenauschreibungForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
//	private static  Vector<Ausschreibung> ausschreibungen = new Vector<>();
//	private static  Vector<Projekt> projekte = new Vector<>();
//	private static Vector <Organisationseinheit> ausschreibender = new Vector();
	
	Projektmarktplatz p = new Projektmarktplatz();
	Button btn_bewerben = new Button("Bewerben");
	Button btn_Text = new Button("Ausschreibungstext anzeigen");
	
	Button btn_partnerprofilAnzeigen = new Button("Partnerprofil anzeigen");
	final Vector <projektAusschreibungHybrid> Hybrid = new Vector<projektAusschreibungHybrid>();
	CellTable <projektAusschreibungHybrid> cellTable= new CellTable<projektAusschreibungHybrid>();
	Ausschreibung localAusschreibung = new Ausschreibung();
	HorizontalPanel panel_Ausschreibung = new HorizontalPanel();
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Offene Stellenausschreibungen";
	}

	
	
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		hp_pager.setWidth("100%");
		hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		
		RootPanel.get("Details").setWidth("75%");
		cellTable.setWidth("100%", true);
		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(),true);
		cellTable.setLoadingIndicator(null);
		
		btn_Text.setStylePrimaryName("cell-btn");
		btn_bewerben.setStylePrimaryName("cell-btn");
		btn_partnerprofilAnzeigen.setStylePrimaryName("cell-btn");
		
		this.add(panel_Ausschreibung);
		panel_Ausschreibung.add(btn_Text);		
		panel_Ausschreibung.add(btn_partnerprofilAnzeigen);
		panel_Ausschreibung.add(btn_bewerben);
		
		projektmarktplatzVerwaltung.getProjektmarktplatzById(identityMarketChoice.getSelectedProjectMarketplaceId(), new AsyncCallback<Projektmarktplatz>() {

			 @Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			
					 
				@Override
				public void onSuccess(Projektmarktplatz result) {
					projektmarktplatzVerwaltung.getProjektByForeignProjektmarktplatz(result, new AsyncCallback<Vector<Projekt>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub	
						}
						
						@Override
						public void onSuccess(Vector <Projekt> result) {
					
							for (Projekt projekt : result) {
							
								projektmarktplatzVerwaltung.getAusschreibungByForeignProjekt(projekt, new AsyncCallback<Vector<Ausschreibung>>() {

									@Override
									public void onFailure(Throwable caught) {
										
										Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
										
									}
									@Override
									public void onSuccess(Vector<Ausschreibung> result) {
										
										for(int i=0;i<result.size();i++){
											
											final Ausschreibung localAusschreibung = result.get(i);
											final projektAusschreibungHybrid localHybrid = new projektAusschreibungHybrid();
											projektmarktplatzVerwaltung.getProjektById(result.get(i).getProjektId(), new AsyncCallback<Projekt>() {

												@Override
												public void onFailure(Throwable caught) {
													
													Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
													
												}
												@Override
												public void onSuccess(Projekt result) {
													
													
													localHybrid.setProjektbezeichnung(result.getName());
													localHybrid.setAusschreibungId(localAusschreibung.getId());
													localHybrid.setBewerbungsfrist(localAusschreibung.getBewerbungsfrist());
													localHybrid.setBezeichnung(localAusschreibung.getBezeichnung());
													localHybrid.setAusschreibungstatus(localAusschreibung.getStatus());
													localHybrid.setAusschreibungstext(localAusschreibung.getAusschreibungstext());
													localHybrid.setPartnerprofilId(localAusschreibung.getPartnerprofilId());
												
													projektmarktplatzVerwaltung.getPersonById(localAusschreibung.getAusschreibenderId(), new AsyncCallback<Person>() {

														public Person ausschreibender = null;
														@Override
														public void onFailure(Throwable caught) {
															// TODO Auto-generated method stub
															Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
															
														}
													
														@Override
														public void onSuccess(Person result) {
															ausschreibender = result;
															localHybrid.setAnrede(result.getAnrede());
															localHybrid.setAusschreibender(result.getNachname());
															
															Person p = result;
															if(p.getId()!=identityMarketChoice.getUser().getId()){
																
																if(p.getTeamId()==null && p.getUnternehmenId()==null){
																
																	localHybrid.setTeam("Kein Team");
																	localHybrid.setUnternehmen("Kein Unternehmen");
																	Hybrid.add(localHybrid);
																	cellTable.setRowCount(Hybrid.size(), true);
																	cellTable.setRowData(0,Hybrid);
																	
																}else if(p.getTeamId()!=null && p.getUnternehmenId()==null){
																	
																	projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new AsyncCallback<Team>() {
																		
																		@Override
																		public void onFailure(Throwable caught) {
																			Window.alert("Team zu Ausschreibendem konnte nicht geladen werden.");
																		}

																		@Override
																		public void onSuccess(Team result) {
																			localHybrid.setUnternehmen("Kein Unternehmen");
																			localHybrid.setTeam(result.getName());
																			Hybrid.add(localHybrid);
																			cellTable.setRowCount(Hybrid.size(), true);
																			cellTable.setRowData(0,Hybrid);
																		}
																	});
																	
																}else if(p.getTeamId()==null && p.getUnternehmenId()!=null){
																	
																	
																	projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

																		@Override
																		public void onFailure(Throwable caught) {
																			Window.alert("Unternehmen zu Ausschreibendem konnte nicht geladen werden.");
																		}

																		@Override
																		public void onSuccess(Unternehmen result) {
																			localHybrid.setTeam("Kein Team");
																			localHybrid.setUnternehmen(result.getName());
																			Hybrid.add(localHybrid);
																			cellTable.setRowCount(Hybrid.size(), true);
																			cellTable.setRowData(0,Hybrid);
																		}
																	});
																	
																}else if(p.getTeamId()!=null && p.getUnternehmenId()!=null){
																	
																	projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new AsyncCallback<Team>() {
																		
																		@Override
																		public void onFailure(Throwable caught) {
																			Window.alert("Team zu Ausschreibendem konnte nicht geladen werden.");
																		}

																		@Override
																		public void onSuccess(Team result) {
																			
																			localHybrid.setTeam(result.getName());
																			
//																			Hybrid.add(localHybrid);
																			cellTable.setRowCount(Hybrid.size(), true);
																			cellTable.setRowData(0,Hybrid);
																			
																			
																			projektmarktplatzVerwaltung.getUnternehmenById(ausschreibender.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

																				@Override
																				public void onFailure(Throwable caught) {
																					Window.alert("Unternehmen zu Ausschreibendem konnte nicht geladen werden.");
																				}

																				@Override
																				public void onSuccess(Unternehmen result) {					
																					
																					localHybrid.setUnternehmen(result.getName());
																					
																					Hybrid.add(localHybrid);
																					
																					
																					
																					cellTable.setRowCount(Hybrid.size(), true);
																					cellTable.setRowData(0,Hybrid);
																				}
																			});
																			
																			
																		}
																	});
																	
																	
																}
															}
															
															final ListDataProvider dataProvider = new ListDataProvider();
															
															pager.setDisplay(cellTable);
															dataProvider.addDataDisplay(cellTable);
															dataProvider.setList(new ArrayList<projektAusschreibungHybrid>(Hybrid));
															pager.setPageSize(20);
															
															
															
															
															
															cellTable.setRowCount(Hybrid.size(), true);
															cellTable.setRowData(0,Hybrid);
															
														}
														
														
														
													});
												} 
											});
											hp_pager.add(pager);
										}
									}
								});
							}
						}
					});
					
				
				}
				
		});
		
		

		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<projektAusschreibungHybrid> AusschreibenderColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				
				return object.getAnrede()+ " "+ object.getAusschreibender();
			}
		};
		cellTable.addColumn(AusschreibenderColumn, "Ausschreibender");
		
		TextColumn<projektAusschreibungHybrid> AusschreibenderTeamColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getTeam();
			}
		};
		cellTable.addColumn(AusschreibenderTeamColumn, "Team");
		
		TextColumn<projektAusschreibungHybrid> AusschreibenderUnternehmenColumn = new TextColumn<projektAusschreibungHybrid>() {

			@Override
			public String getValue(projektAusschreibungHybrid object) {
				// TODO Auto-generated method stub
				return object.getUnternehmen();
			}
		};
		cellTable.addColumn(AusschreibenderUnternehmenColumn, "Unternehmen");

		
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
		
		btn_Text.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie eine Stellenausschreibung aus");
				}
				DialogBoxAusschreibungstext text = new DialogBoxAusschreibungstext(selectionModel.getSelectedObject().getAusschreibungstext());
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				text.setPopupPosition(left, top);
				text.show();
				
		}
		});
		
		btn_bewerben.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie eine Stellenausschreibung aus auf die Sie sich bewerben möchten");
				}
				projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(identityMarketChoice.getSelectedIdentityAsObject(), new AsyncCallback<Vector<Bewerbung>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Bereits vorhandene Bewerbungen der Identität konnten nicht geladen werden!");
					}

					@Override
					public void onSuccess(Vector<Bewerbung> result) {
						boolean bereitsBeworben = false;
						for (Bewerbung bewerbung : result) {
							if(bewerbung.getAusschreibungId()==selectionModel.getSelectedObject().getAusschreibungId()){
								bereitsBeworben=true;
							}
						}
						if(bereitsBeworben==true){
							Window.alert("Sie haben sich bereits auf die Ausschreibung beworben! Bitte wählen Sie eine andere Ausschreibung!");
						}else{
							DialogBoxBewerben text = new DialogBoxBewerben(selectionModel.getSelectedObject().getAusschreibungId(), identityMarketChoice, navigation);
							int left = Window.getClientWidth() / 3;
							int top = Window.getClientHeight() / 8;
							text.setPopupPosition(left, top);
							text.show();
						}
					}
				});			
		}
		});
		
		
		btn_partnerprofilAnzeigen.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie eine Stellenausschreibung aus");
				}else{
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(selectionModel.getSelectedObject().getPartnerprofilId(), false, true,  identityMarketChoice, navigation));
				}}
		});
		
		
		
		cellTable.setWidth("100%");
		
		this.setSpacing(8);
		this.add(cellTable);
		
		this.add(hp_pager);	

	}

	

	private class projektAusschreibungHybrid{
		
		private String bezeichnung;
		private String Anrede;
		private String projektbezeichnung;
		private String ausschreibender;
		private String team;
		private String unternehmen;
		private String ausschreibungstext;
		private Date bewerbungsfrist;
		private Ausschreibungsstatus ausschreibungstatus;
		private int ausschreibungId;
		private int partnerprofilId;

		public int getPartnerprofilId() {
			return partnerprofilId;
		}
		public void setPartnerprofilId(int partnerprofilId) {
			this.partnerprofilId = partnerprofilId;
		}
		public String getAnrede() {
			return Anrede;
		}
		public void setAnrede(String anrede) {
			Anrede = anrede;
		}		
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
		public String getAusschreibender() {
			return ausschreibender;
		}
		public void setAusschreibender(String ausschreibungsbezeichnername) {
			this.ausschreibender = ausschreibungsbezeichnername;
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
		public String getAusschreibungstext() {
			return ausschreibungstext;
		}
		public void setAusschreibungstext(String ausschreibungstext) {
			this.ausschreibungstext = ausschreibungstext;
		}
		public String getTeam() {
			return team;
		}
		public void setTeam(String team) {
			this.team = team;
		}
		public String getUnternehmen() {
			return unternehmen;
		}
		public void setUnternehmen(String unternehmen) {
			this.unternehmen = unternehmen;
		}
		
	}
	 
}		 
		 

		

		 

	

