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
//	private static  Vector<Ausschreibung> ausschreibungen = new Vector<>();
//	private static  Vector<Projekt> projekte = new Vector<>();
//	private static Vector <Organisationseinheit> ausschreibender = new Vector();
	
	Projektmarktplatz p = new Projektmarktplatz();
	Button btn_bewerben = new Button("Bewerben");
	Button btn_Text = new Button("Ausschreibungstext anzeigen");
	CellTable <projektAusschreibungHybrid> cellTable= new CellTable<projektAusschreibungHybrid>();
	Ausschreibung localAusschreibung = new Ausschreibung();
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
		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(),true);
		cellTable.setLoadingIndicator(null);
		
		btn_Text.setStylePrimaryName("navi-button");
		btn_bewerben.setStylePrimaryName("navi-button");
		
		this.add(panel_Ausschreibung);
		panel_Ausschreibung.add(btn_Text);
		panel_Ausschreibung.add(btn_bewerben);
		
		projektmarktplatzVerwaltung.getProjektmarktplatzById(IdentityMarketChoice.getSelectedProjectMarketplaceId(), new AsyncCallback<Projektmarktplatz>() {

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
										final Vector <projektAusschreibungHybrid> Hybrid = new Vector<projektAusschreibungHybrid>();
										for(int i=0;i<result.size();i++){
											final Ausschreibung localAusschreibung = result.get(i);
											projektmarktplatzVerwaltung.getProjektById(result.get(i).getProjektId(), new AsyncCallback<Projekt>() {

												@Override
												public void onFailure(Throwable caught) {
													
													Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
													
												}
												@Override
												public void onSuccess(Projekt result) {
													
													final projektAusschreibungHybrid localHybrid = new projektAusschreibungHybrid();
													localHybrid.setProjektbezeichnung(result.getName());
													localHybrid.setAusschreibungId(localAusschreibung.getId());
													localHybrid.setBewerbungsfrist(localAusschreibung.getBewerbungsfrist());
													localHybrid.setBezeichnung(localAusschreibung.getBezeichnung());
													localHybrid.setAusschreibungstatus(localAusschreibung.getStatus());
													localHybrid.setAusschreibungstext(localAusschreibung.getAusschreibungstext());
												
													projektmarktplatzVerwaltung.getPersonById(localAusschreibung.getAusschreibenderId(), new AsyncCallback<Person>() {

														
														@Override
														public void onFailure(Throwable caught) {
															// TODO Auto-generated method stub
															Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen!");
															
														}
													
														@Override
														public void onSuccess(Person result) {
															localHybrid.setAnrede(result.getAnrede());
															localHybrid.setAusschreibender(result.getNachname());
															
															Person p = result;
															if(p.getId()!=IdentityMarketChoice.getUser().getId()){
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
																			projektmarktplatzVerwaltung.getUnternehmenById(result.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

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
														}
													});
												}
											});
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
				projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(IdentityMarketChoice.getSelectedIdentityAsObject(), new AsyncCallback<Vector<Bewerbung>>() {

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
							DialogBoxBewerben text = new DialogBoxBewerben(selectionModel.getSelectedObject().getAusschreibungId());
							int left = Window.getClientWidth() / 3;
							int top = Window.getClientHeight() / 8;
							text.setPopupPosition(left, top);
							text.show();
						}
					}
				});			
		}
		});
		cellTable.setWidth("100%");
		
		this.setSpacing(8);
		this.add(cellTable);
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
		 

		

		 

	

