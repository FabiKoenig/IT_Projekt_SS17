package de.hdm.itProjektSS17.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.TextArea;
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

	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityMarketChoice und der Navigation übergeben wird.
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public MeineBewerbungenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	@SuppressWarnings("unchecked")
	
	/**
	 * Anlegen der GUI-Elemente und der globalen Variablen
	 */
	CellTable<ausschreibungBewerbungHybrid> cellTable = new CellTable<ausschreibungBewerbungHybrid>();
	Vector<ausschreibungBewerbungHybrid> hybrid = new Vector<ausschreibungBewerbungHybrid>();
	HorizontalPanel hp_pager = new HorizontalPanel();
	SimplePager pager;	
	HorizontalPanel panel_Bewerbung = new HorizontalPanel();

	Button btn_bewerbungloeschen = new Button("Bewerbung zurückziehen");
	Button btn_bewerbungstext = new Button ("Bewerbungstext anzeigen");
	Button btn_stellungname = new Button ("Stellungnahme anzeigen");


	/**
	 * Setzen der Headline.
	 * Diese Methode wird von der Superklasse vorgegeben und setzt für dieses GUI Element eine Überschrift.
	 */
	protected String getHeadlineText(){
	return "Meine Bewerbungen";
	}
	
	/**
	 * Methode die aufgerufen wird, sobald diese Form aufgerufen wird.
	 */
	protected void run() {
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		
		RootPanel.get("Details").setWidth("75%");
		cellTable.setWidth("100%", true);
		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(),true);
		cellTable.setLoadingIndicator(null);
		
		//Stylen des Buttons
		btn_bewerbungloeschen.setStylePrimaryName("cell-btn");
		btn_bewerbungstext.setStylePrimaryName("cell-btn");
		btn_stellungname.setStylePrimaryName("cell-btn");
		
		this.setSpacing(8);
		this.add(panel_Bewerbung);
		panel_Bewerbung.add(btn_bewerbungloeschen);

		panel_Bewerbung.add(btn_bewerbungstext);
		panel_Bewerbung.add(btn_stellungname);
		projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(identityMarketChoice.getSelectedIdentityAsObject(), new BewerbungAnzeigenCallback());
	
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
		/**
		 * Anlegen der TextColumns für die cellTable
		 */
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
			
			
			return object.getAnrede()+ " " +object.getAusschreibungsbezeichnername();
			
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
		
		TextColumn<ausschreibungBewerbungHybrid> BewertungColumn = new TextColumn<ausschreibungBewerbungHybrid>() {
			
			@Override
			public String getValue(ausschreibungBewerbungHybrid object) {
				double wert = object.getBewertungWert();
				if(wert==0.0){
					return "Noch nicht bewertet";
				}
				String stringWert = Double.toString(wert);
				return stringWert;
			}
		};

		/**
		 * Hinzufügen der Columns zu der CellTable
		 */
		cellTable.addColumn(AusschreibungNameColumn, "Stelle");
		cellTable.addColumn(AusschreibenderColumn, "Ausschreibender");
		cellTable.addColumn(AusschreibenderTeamColumn, "Team");
		cellTable.addColumn(AusschreibenderUnternehmenColumn, "Unternehmen");
		cellTable.addColumn(erstellungsdatumColumn, "Erstellungsdatum");
		cellTable.addColumn(statusColumn, "Status");
		cellTable.setColumnWidth(statusColumn, 80, Unit.PT);
		cellTable.addColumn(BewertungColumn, "Bewertung");

		/**
		 * Anlegen des SingleSelectionModels und hinzufügen zur cellTable
		 */
		final SingleSelectionModel<ausschreibungBewerbungHybrid> selectionModel = new SingleSelectionModel<>();
		cellTable.setSelectionModel(selectionModel);	
		
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
	
			}
		});
		
		/**
		 * Anlegen des Click-Handlers mit Hilfe desse eine DialogBox aufgerufen wird, welche den 
		 * Bewerbungstext zur ausgwählten Bewerbung anzeigt.
		 */
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
		
		
		/**
		 * Click-Handler um Bewerbungen zu löschen. Hierzu wird ein lokales Bewerbungsobjekt erstellt, welches
		 * die id der ausgewählten Bewerbung gesetzt bekommt. Dieses wird dann anschließend zum löschen übergeben.
		 */
		btn_bewerbungloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if (selectionModel.getSelectedObject() == null)
				{
					Window.alert("Bitte wählen Sie die zu löschende Bewerbung aus");
				}else{				
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
								navigation.reload();
							}
						});
				}


				
			
			}
		});
		
		
		/**
		 * Click-Handler um sich die, zur Bewerbungen abgegebene, Stellungnagme anzeigen zulassen
		 */
		btn_stellungname.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final DialogBox db_Stellungnahme = new DialogBox();
				TextArea txta_Stellungnahme = new TextArea();
				FlexTable ft_Stellungname = new FlexTable();
				Button ok_Stellungnahme = new Button("Zurück");
				
				txta_Stellungnahme.setText(selectionModel.getSelectedObject().getStellungnahme());
				txta_Stellungnahme.setReadOnly(true);
				txta_Stellungnahme.setCharacterWidth(70);
				txta_Stellungnahme.setVisibleLines(25);	
				
				ft_Stellungname.setWidget(0, 0, txta_Stellungnahme);
				ft_Stellungname.setWidget(1, 0, ok_Stellungnahme);
				
				/**
				 * Click-Handler um DialogBox wieder zu schließen
				 */
				ok_Stellungnahme.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						db_Stellungnahme.hide();
					}
				});
				
				db_Stellungnahme.setWidget(ft_Stellungname);
				db_Stellungnahme.setText("Stellungnahme");
				db_Stellungnahme.setAnimationEnabled(false);
				db_Stellungnahme.setGlassEnabled(true);
				db_Stellungnahme.center();
				
				
			}
		});
		
		this.add(cellTable);
		hp_pager.setWidth("100%");
		hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.add(hp_pager);
		
	}

	/**
	 * Anlegen der Hybrid-Klasse 
	 * @author Tim
	 *
	 */
	private class ausschreibungBewerbungHybrid{
		
		/**
		 * Deklarieren der Variablen
		 * mit den jeweiligen Gettern und Settern.
		 */
		private int bewerbungId;
		private String bewerbungstext;
		private String anrede;
		
		private String ausschreibungsbezeichnung;
		private String ausschreibungsbezeichnername;
		private Date erstellungsdatum;
		private Bewerbungsstatus statusBewerbungsstatus;
		private String Team;
		private String Unternehmen;
		private String stellungnahme;
		
		private Double bewertungWert;
		
		public String getAnrede() {
			return anrede;
		}
		public void setAnrede(String anrede) {
			this.anrede = anrede;
		}
		
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
		public Double getBewertungWert() {
			return bewertungWert;
		}
		public void setBewertungWert(Double bewertungWert) {
			this.bewertungWert = bewertungWert;
		}
		public String getStellungnahme() {
			return stellungnahme;
		}
		public void setStellungnahme(String stellungnahme) {
			this.stellungnahme = stellungnahme;
		}
		
		
		
	}

	/**
	 * CallBack um die Bewerbungen in der Celltable auszugeben.
	 * Bei erfolgreichem Callback wird ein Vector mit Bewerbungs-Objekten als <code>result</code> zurückgegeben.
	 * @author Tim
	 *
	 */
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			/**
			 * Das result wird in ein neues Bewerbungs-Objekt <code>localHybrid</code> gespeichert.
			 * Dieses wird übergeben um in einem neuen Callback die passenden Ausschreibungen zu den jeweiligen 
			 * Bewerbungen zu erhalten.
			 */
			for(int i=0;i<result.size();i++){
				final Bewerbung localBewerbung = result.get(i);
				final ausschreibungBewerbungHybrid localHybrid = new ausschreibungBewerbungHybrid();
			
				
				projektmarktplatzVerwaltung.getAusschreibungById(result.get(i).getAusschreibungId(), new AsyncCallback<Ausschreibung>() {
					
					@Override
					public void onFailure(Throwable caught) {
						
						Window.alert("Das Anzeigen der Bewerbungen ist fehlgeschlagen!");
						
					}
					/**
					 * Bei erfolgreichem Callback wird in das <code>localHybrid</code> Obejkt die Ausschreibungsbezeichnung gesetzt.
					 * Anschließend aus dem <code>result</code> die ProjektId gelesen und übergeben um in einem neuen Callback
					 * das passende Projekt zu erhalten.
					 */
					@Override
					public void onSuccess(Ausschreibung result) {
					final Ausschreibung a = result;
					localHybrid.setAusschreibungsbezeichnung(result.getBezeichnung());
					projektmarktplatzVerwaltung.getProjektById(result.getProjektId(), new AsyncCallback<Projekt>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						/**
						 * Bei erfolgreichem Callback wird geprüft ob sich das das Projekt auf dem ausgewähltem Projektmarktplatz befindet.
						 * Falls ja wird über das die <code> AusschreibenderId</code> die Person gesucht. Hierzu wird ein neuer
						 * Callback aufgerufen. Das result wird ein neuen Person-Objekt <code> ausschreibender</code> gespeichert.
						 */
						@Override
						public void onSuccess(Projekt result) {
							if (identityMarketChoice.getSelectedProjectMarketplaceId()==result.getProjektmarktplatzId()){
								
								projektmarktplatzVerwaltung.getPersonById(a.getAusschreibenderId(), new AsyncCallback<Person>() { 
									
														public Person ausschreibender = null;
														@Override
															public void onFailure(Throwable caught) {
																// TODO Auto-generated method stub
																
															}
									
															@Override
														public void onSuccess(Person result) {
																ausschreibender = result;
																
																/**
																 * Neuer Callback um die Bewertung zu einer Bewerbung zu erhalten. Hierzu wird das Objekt 
																 * <code>localBewerbung</code> übergeben. 
																 */
																projektmarktplatzVerwaltung.getBewertungByForeignBewerbung(localBewerbung, new AsyncCallback<Bewertung>() {
																	
																	@Override
																	public void onFailure(Throwable caught) {
																		Window.alert("Fehler: "+caught.toString());					
																	}
																	/**
																	 * Bei erfolgreichem Callback wird die Bewertung in das <code>localHybrid</code> Objekt gespeichert
																	 * und der <code>cellTable</code> hinzugefügt.
																	 */
																	@Override
																	public void onSuccess(Bewertung result) {	
																		if(result != null){	
																		localHybrid.setBewertungWert(result.getWert());	
																		localHybrid.setStellungnahme(result.getStellungnahme());;
																		cellTable.setRowCount(hybrid.size(), true);
																		cellTable.setRowData(0,hybrid);				
																		}
																		else{
																			localHybrid.setStellungnahme("Keine Stellungnahme vorhanden");
																			localHybrid.setBewertungWert(0.0);
																			cellTable.setRowCount(hybrid.size(), true);
																			cellTable.setRowData(0,hybrid);	
																		}
																	}
																});
																
																/**
																 * Setzen der Attribute in das <code>localHybrid</code> Objekt.
																 */
																localHybrid.setAnrede(result.getAnrede());
																localHybrid.setAusschreibungsbezeichnername(result.getNachname());
																localHybrid.setBewerbungId(localBewerbung.getId());
																localHybrid.setErstellungsdatum(localBewerbung.getErstellungsdatum());
																localHybrid.setStatusBewerbungsstatus(localBewerbung.getStatus());
																if(localBewerbung.getBewerbungstext()=="null"){
																	localHybrid.setBewerbungstext("Kein Text vorhanden");
																}else if (localBewerbung.getBewerbungstext()==""){
																	localHybrid.setBewerbungstext("Kein Text vorhanden");
																}else{
																	localHybrid.setBewerbungstext(localBewerbung.getBewerbungstext());
																}
																
																/**
																 * Prüfen ob zu der Ausschreibung ein Unternehmen oder Team gehört.
																 * Falls nicht wird in den <code>localHybrid</code> gesetzt, dass kein Unternehmen
																 * und Team zum Ausschreibenden gehören.
																 */
																Person p = result;
																if(p.getId()!=identityMarketChoice.getUser().getId()){
																	if(p.getTeamId()==null && p.getUnternehmenId()==null){
																		
																		localHybrid.setTeam("Kein Team");
																		localHybrid.setUnternehmen("Kein Unternehmen");
																		hybrid.add(localHybrid);
																		cellTable.setRowCount(hybrid.size(), true);
																		cellTable.setRowData(0,hybrid);
																	
																		/**
																		 * Wenn ein Team zum Ausschreibenden gehört wird dieses mit in den <code>localHybrid</code> gesetzt.
																		 */
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
																				hybrid.add(localHybrid);
																				cellTable.setRowCount(hybrid.size(), true);
																				cellTable.setRowData(0,hybrid);
																			}
																		});
																		/**
																		 * Falls ein Unternehmen zum Ausschreibenden gehört wird das Unternehmen wird dieses mit in den
																		 * <code>localHybrid</code> gesetzt.
																		 */
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
																				hybrid.add(localHybrid);
																				
																				final ListDataProvider dataProvider = new ListDataProvider();
																				
																																						pager.setDisplay(cellTable);
																				dataProvider.addDataDisplay(cellTable);
																				dataProvider.setList(new ArrayList<ausschreibungBewerbungHybrid>(hybrid));
																				pager.setPageSize(20);
																				
																				
																				
																				
																				
																				
																				
																				cellTable.setRowCount(hybrid.size(), true);
																				cellTable.setRowData(0,hybrid);
																				
																				

																			}
																			
																		});
																		
																		/**
																		 * Wenn zum Ausschreibenden ein Team und ein Unternehmen gehört, werden diese beide in den
																		 * <code>localHybrid</code> gesetzt.
																		 */
																	}else if(p.getTeamId()!=null && p.getUnternehmenId()!=null){
																		
																		projektmarktplatzVerwaltung.getTeamById(result.getTeamId(), new AsyncCallback<Team>() {
																			
																			@Override
																			public void onFailure(Throwable caught) {
																				Window.alert("Team zu Ausschreibendem konnte nicht geladen werden.");
																			}

																			@Override
																			public void onSuccess(Team result) {
																				localHybrid.setTeam(result.getName());
																				
																				
																				cellTable.setRowCount(hybrid.size(), true);
																				cellTable.setRowData(0,hybrid);
																				projektmarktplatzVerwaltung.getUnternehmenById(ausschreibender.getUnternehmenId(), new AsyncCallback<Unternehmen>() {

																					@Override
																					public void onFailure(Throwable caught) {
																						Window.alert("Unternehmen zu Ausschreibendem konnte nicht geladen werden.");
																					}

																					@Override
																					public void onSuccess(Unternehmen result) {
																						localHybrid.setUnternehmen(result.getName());
																						hybrid.add(localHybrid);
																						
																					
																		
																						
																						
																						cellTable.setRowCount(hybrid.size(), true);
																						cellTable.setRowData(0,hybrid);

																						
																					}
																				});
																				
																				

																			}
																		});
																		

																	}
																	
																}
																hp_pager.add(pager);
															}
															
														});
													
								
														
													}
												}
											});
					
										}
									});

				
								}
		

			
			cellTable.setRowCount(hybrid.size(), true);
			cellTable.setRowData(0,hybrid);
							}
	
							
					 };
					 
					 
					 
	}