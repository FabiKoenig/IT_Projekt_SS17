package de.hdm.itProjektSS17.client.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.search.query.QueryParser.item_return;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.BeteiligungenForm.BeteiligungProjektHybrid;
import de.hdm.itProjektSS17.client.gui.DialogBoxEigenschaftHinzufuegen.SetEigenschaftenCallback;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung.Ausschreibungsstatus;

/**
 * Die Klasse zeig die Ausschreibungen einer Person in tabellarischer Form.
 * @author Tim
 *
 */
public class MeineAusschreibungenForm extends Showcase{

	/**
	 * Anlegen globaler Variablen und Objekte
	 */
	SimplePager pager;
	HorizontalPanel hp_pager = new HorizontalPanel();
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	private static Vector<Ausschreibung> ausschreibungen = new Vector<>();
	private static int partnerprofilId = 0;
	private static ClickHandler clickhandler;
	private static ClickEvent clickevent;
	
	CellTable<AusschreibungProjektHybrid> dataGrid = new CellTable<AusschreibungProjektHybrid>();
	Vector <Projekt> projekt = new Vector <Projekt>();
	Vector<AusschreibungProjektHybrid> hybrid = new Vector<AusschreibungProjektHybrid>();
	
	/**
	 * Auslesen der ProjektmarktplatzAsync Instanz
	 */
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	
	/**
	 * Anlegen der Buttons
	 */
	Button ausschreibungLoeschenButton = new Button("Löschen");
	Button ausschreibungBearbeitenButton = new Button("Bearbeiten");
	Button partnerprofilBearbeitenButton = new Button("Gefordertes Partnerprofil anzeigen");
	Button bewerbungenAnzeigenButton = new Button("Bewerbungen anzeigen");
	Button ausschreibungstextButton = new Button("Ausschreibungstext anzeigen");
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityMarketChoice und der Navigation übergeben wird.
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public MeineAusschreibungenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}
	
	/**
	 * Anlegen des HeadlineTextes
	 */
	@Override
	protected String getHeadlineText() {
		return "Von mir erstellte Ausschreibungen";
	}

	/**
	 * Methode die aufgerufen wird, sobald diese Form aufgerufen wird.
	 */
	@Override
	protected void run() {
		
		hp_pager.setWidth("100%");
		hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		RootPanel.get("Details").setWidth("75%");
		dataGrid.setWidth("100%", true);
		
		/**
		 * CallBack um die Ausschreibungen der gewünschten Person zu laden
		 */
		projektmarktplatzVerwaltung.getOrganisationseinheitById(identityMarketChoice.getSelectedIdentityId(), new AsyncCallback<Organisationseinheit>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");				
			}

			@Override
			public void onSuccess(Organisationseinheit result) {
				/**
				 * Bei erfolgreichem Callback wird eine Organisationseinheit als result zurückgegeben,
				 * diese wird für den neuen Callback übergeben. Der neue Callback liefert dann ein Vector
				 * mit Ausschreibungen zu dieser Organisationseinheit zurück.
				 */
				if (result != null) {
					projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new AsyncCallback<Vector<Ausschreibung>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: "+caught.toString());
						}

						/**
						 * Es wird ein neues Objekt <code>localhybrid</code> erstelt. in dieses werden die jeweiligen
						 * Attribute der Ausschreibungen gesetzt.
						 */
						@Override
						public void onSuccess(Vector<Ausschreibung> result) {
							for (Ausschreibung ausschreibung : result) {
								final AusschreibungProjektHybrid localhybrid = new AusschreibungProjektHybrid();
								
								localhybrid.setBezeichnung(ausschreibung.getBezeichnung());
								localhybrid.setStatus(ausschreibung.getStatus());
								localhybrid.setBewerbungsfrist(ausschreibung.getBewerbungsfrist());
								localhybrid.setAusschreibenderid(ausschreibung.getAusschreibenderId());
								localhybrid.setAusschreibungid(ausschreibung.getId());
								localhybrid.setText(ausschreibung.getAusschreibungstext());
								localhybrid.setProjektid(ausschreibung.getProjektId());
								localhybrid.setPartnerprofilid(ausschreibung.getPartnerprofilId());
								dataGrid.setRowCount(hybrid.size(), true);
								dataGrid.setRowData(hybrid);
								/**
								 * Neuer Callback um das dazu gehörige Projekt zu der übergebenen Ausschreibung zu erhalten.
								 * Dabei wird in den <code>localhybrid</code> der Projektname gesetzt, anschließend wird das Objekt
								 * dem Vector <code>hybrid</code> hinzugefügt, welcher anschließend in der CelTable ausgegeben wird.
								 */
								projektmarktplatzVerwaltung.getProjektbyAusschreibung(ausschreibung, new AsyncCallback<Projekt>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Fehler: "+caught.toString());
										
									}

									@Override
									public void onSuccess(Projekt result) {
										localhybrid.setProjektname(result.getName());
										hybrid.add(localhybrid);
										
										
										dataGrid.setRowCount(hybrid.size(), true);
										dataGrid.setRowData(hybrid);	
										final ListDataProvider dataProvider = new ListDataProvider();
										
										
										
										pager.setDisplay(dataGrid);
										dataProvider.addDataDisplay(dataGrid);
										dataProvider.setList(new ArrayList<AusschreibungProjektHybrid>(hybrid));
										pager.setPageSize(10);
										
										
									
										
										
									}
								});

								}
							hp_pager.add(pager);
						}
					});
				}
			}
		});
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Erstellen der TextColumns der CellTable
				TextColumn<AusschreibungProjektHybrid> projektColumn = new TextColumn<AusschreibungProjektHybrid>(){

				@Override
					public String getValue(AusschreibungProjektHybrid object) {
						return object.getProjektname();
					}
				};
		
		
				TextColumn<AusschreibungProjektHybrid> bezeichnungColumn = new TextColumn<AusschreibungProjektHybrid>(){

					@Override
					public String getValue(AusschreibungProjektHybrid object) {
						return object.getBezeichnung();
					}
				};
				

				TextColumn<AusschreibungProjektHybrid> bewerbungsfristColumn = new TextColumn<AusschreibungProjektHybrid>(){

					@Override
					public String getValue(AusschreibungProjektHybrid object) {
						return object.getBewerbungsfrist().toString();
					}
				};
				
				TextColumn<AusschreibungProjektHybrid> statusColumn = new TextColumn<AusschreibungProjektHybrid>(){

					@Override
					public String getValue(AusschreibungProjektHybrid object) {
						return object.getStatus().toString();
					}
				};
				
		
		
			// Hinzufügen der Spalten zu unserer CellTable+
				dataGrid.addColumn(projektColumn, "Projektname");
				dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
				dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
				dataGrid.addColumn(statusColumn, "Status");			
//				dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstext");
				dataGrid.setEmptyTableWidget(new Label("Es sind keine eigenen Ausschreibungen vorhanden"));
				dataGrid.setLoadingIndicator(null);
				
			// Anlegen des SingleSeletion Models
				final SingleSelectionModel<AusschreibungProjektHybrid> selectionModel = new SingleSelectionModel<>();
				dataGrid.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						
						
					}
				});
				
					
			
				dataGrid.setWidth("100%");
				
			//Hinzufügen der Buttons zum ButtonPanel
				buttonPanel.add(ausschreibungBearbeitenButton);
				buttonPanel.add(ausschreibungLoeschenButton);
				buttonPanel.add(partnerprofilBearbeitenButton);
				buttonPanel.add(bewerbungenAnzeigenButton);
				buttonPanel.add(ausschreibungstextButton);
				
			//Style der Buttons
				ausschreibungBearbeitenButton.setStylePrimaryName("cell-btn");
				ausschreibungLoeschenButton.setStylePrimaryName("cell-btn");
				partnerprofilBearbeitenButton.setStylePrimaryName("cell-btn");
				bewerbungenAnzeigenButton.setStylePrimaryName("cell-btn");
				ausschreibungstextButton.setStylePrimaryName("cell-btn");
				
			//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
				this.setSpacing(8);
				this.add(buttonPanel);
				this.add(dataGrid);
				this.add(hp_pager);
				
				
			/**
			 * CLICK-HANDLER
			 */
				
				
				/**
				 * Click-Handler zum löschen einer Ausschreibung
				 */
			ausschreibungLoeschenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					/**
					 * Wenn eine Zeile ausgewählt ist wird in das <code>selectedAusschreibung</code> die benötigten
					 * Attribute gesetzt und anschließend gelöscht.
					 */
					if(selectionModel.getSelectedObject() != null){
						AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();
						Ausschreibung localAusschreibung = new Ausschreibung();
						localAusschreibung.setId(selectedAusschreibung.getAusschreibungid());
						localAusschreibung.setPartnerprofilId(selectedAusschreibung.getPartnerprofilid());
						localAusschreibung.setBezeichnung(selectedAusschreibung.getBezeichnung());
						projektmarktplatzVerwaltung.deleteAusschreibung(localAusschreibung, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Die Ausschreibung wurde erfolgreich gelöscht.");
							navigation.reload();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Die Ausschreibung konnte nicht gelöscht werden.");
						}
					});
				} else {
					Window.alert("Bitte wähle zuerst die zu löschende Ausschreibung aus.");
				}
			}
		});
			
				/**
				 * Click-Handler zum bearbeiten einer Ausschreibung
				 */
			ausschreibungBearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
							//Erstellen der GUI Elemente
								final DialogBox ausschreibungBearbeitenDialogBox = new DialogBox();
								FlexTable ausschreibungBearbeitenFlexTable = new FlexTable();
								HorizontalPanel buttonPanel = new HorizontalPanel();
								VerticalPanel dialogBoxPanel = new VerticalPanel();
								Label ausschreibungBezeichungLabel = new Label("Bezeichnung:");
								Label ausschreibungBewerbungsfristLabel = new Label("Bewerbungsfrist:");
								Label ausschreibungstextLabel = new Label("Ausschreibungstext:");
								Label ausschreibungsStatusLabel = new Label("Ausschreibungstatus:");
								final TextBox ausschreibungBezeichungBox = new TextBox();
								final DateBox ausschreibungBewerbungsfristBox = new DateBox();
								final TextArea ausschreibungstextBox = new TextArea();
								final ListBox ausschreibungsStatusBox = new ListBox();
								Button abbrechenButton = new Button("Abbrechen");
								Button speichernButton = new Button("Speichern");
								Button zurueckButton = new Button("Zurück");

								//Setzen des Formats und weiterer Standardwerte
								DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
								ausschreibungBewerbungsfristBox.setFormat(new DateBox.DefaultFormat(dateformat));
								ausschreibungstextBox.setVisibleLines(20);
								ausschreibungstextBox.setCharacterWidth(70);
								ausschreibungsStatusBox.addItem("laufend");
								ausschreibungsStatusBox.addItem("abgebrochen");
								ausschreibungsStatusBox.addItem("besetzt");
								ausschreibungBearbeitenDialogBox.setGlassEnabled(true);
								ausschreibungBearbeitenDialogBox.setAnimationEnabled(false);
								
								//Erstellen der FlexTable
								ausschreibungBearbeitenFlexTable.setWidget(0, 1, ausschreibungBezeichungBox);
								ausschreibungBearbeitenFlexTable.setWidget(0, 0, ausschreibungBezeichungLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(1, 1, ausschreibungBewerbungsfristBox);
								ausschreibungBearbeitenFlexTable.setWidget(1, 0, ausschreibungBewerbungsfristLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(2, 1, ausschreibungstextBox);
								ausschreibungBearbeitenFlexTable.setWidget(2, 0, ausschreibungstextLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(3, 1, ausschreibungsStatusBox);
								ausschreibungBearbeitenFlexTable.setWidget(3, 0, ausschreibungsStatusLabel);
								
							//Hinzufügen der Buttons zum Buttonpanel
								buttonPanel.add(speichernButton);
								buttonPanel.add(abbrechenButton);
								abbrechenButton.setStylePrimaryName("cell-btn");
								speichernButton.setStylePrimaryName("cell-btn");
								
							//Hinzufügen der FlexTable zur DialogBox
								dialogBoxPanel.setSpacing(8);
								dialogBoxPanel.add(buttonPanel);
								dialogBoxPanel.add(ausschreibungBearbeitenFlexTable);
								ausschreibungBearbeitenDialogBox.add(dialogBoxPanel);
								
								
								
							if(selectionModel.getSelectedObject() != null){	
							//	Anzeigen der DialogBox
								ausschreibungBezeichungBox.setValue(selectionModel.getSelectedObject().getBezeichnung());
								ausschreibungBewerbungsfristBox.setValue(selectionModel.getSelectedObject().getBewerbungsfrist());
								ausschreibungstextBox.setValue(selectionModel.getSelectedObject().getText());
								ausschreibungBearbeitenDialogBox.center();
								
								} else {
									Window.alert("Bitte wähle zuerst die Ausschreibung aus, die bearbeitet werden soll.");
								}
							
								/**
								 * CLICK-HANDLER für den <code>abbrechenButton</code>
								 */
									abbrechenButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											ausschreibungBearbeitenDialogBox.hide();
										}
									});
								
								/**
								 * CLICK-HANDLER für den <code>speichernButton</code>
								 * Um eine Ausschreibung zu bearbeiten werden die benötigten Id´s der ausgewählten Ausschreibung
								 * in das <code>bearbeiteteAusschreibung</code> Objekt gesetzt, dieses holt sich dann die neuen Attribute.
								 * Das <code>bearbeiteteAusschreibung</code> Objekt wird dann übergeben.
								 */
									speichernButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											
							
											AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();
											if(ausschreibungBezeichungBox.getText() != "" && ausschreibungstextBox.getText() != ""){
												Ausschreibung bearbeiteteAusschreibung = new Ausschreibung();
										
												bearbeiteteAusschreibung.setId(selectedAusschreibung.getAusschreibungid());
												bearbeiteteAusschreibung.setAusschreibenderId(selectedAusschreibung.getAusschreibenderid());
												
												bearbeiteteAusschreibung.setPartnerprofilId(selectedAusschreibung.getPartnerprofil());
												bearbeiteteAusschreibung.setProjektId(selectedAusschreibung.getProjektid());

												if(ausschreibungsStatusBox.getSelectedItemText().equals("laufend")){
													bearbeiteteAusschreibung.setStatus(Ausschreibungsstatus.laufend);
												}else if(ausschreibungsStatusBox.getSelectedItemText().equals("besetzt")){
													bearbeiteteAusschreibung.setStatus(Ausschreibungsstatus.besetzt);
												}else if(ausschreibungsStatusBox.getSelectedItemText().equals("abgebrochen")){
													bearbeiteteAusschreibung.setStatus(Ausschreibungsstatus.abgebrochen);
												}
												
												//bearbeiteteAusschreibung.setId(selectionModel.getSelectedObject().getId());
												//bearbeiteteAusschreibung.setAusschreibenderId(selectionModel.getSelectedObject().getAusschreibenderId());

												bearbeiteteAusschreibung.setAusschreibungstext(ausschreibungstextBox.getText());
												bearbeiteteAusschreibung.setBewerbungsfrist(ausschreibungBewerbungsfristBox.getValue());
												bearbeiteteAusschreibung.setBezeichnung(ausschreibungBezeichungBox.getText());
										
												
												projektmarktplatzVerwaltung.saveAusschreibung(bearbeiteteAusschreibung, new AsyncCallback<Void>() {
													
													
													
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Das bearbeiten der Ausschreibung ist fehlgeschlagen.");
														
													}

													@Override
													public void onSuccess(Void result) {
														Window.alert("Die Ausschreibung wurde erfolgreich bearbeitet.");
														
														ausschreibungBearbeitenDialogBox.hide();					
														navigation.reload();
													}
												});
											} else{
												Window.alert("Bitte fülle die Felder vollständig aus.");
											}	
										}
								});	
						}
				});
			
			
			/**
			 * Click-Handler für den "Partnerprofil anzeigen/bearbeiten" Button
			 * Weiterleitung auf PartnerprofilByAusschreibungForm mit der ausgewählten id.
			 * @see de.hdm.itProjektSS17.client.gui.PartnerprofilByAusschreibungForm
			 */
			
			partnerprofilBearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();

					if(selectionModel.getSelectedObject() != null){
						
						//geändert
						partnerprofilId = selectedAusschreibung.getPartnerprofilid();
					
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(partnerprofilId, false, false, identityMarketChoice, navigation));
					
						clickhandler = this;
						clickevent = event;

						
						} else {
							Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
				}
			});
			
			/**
			 * Click-Handler um die eingegangenen Bewerbungen auf eine Ausschreibung einzusehen
			 * Weiterleitung zur BewerbungenAufAusschreibungForm Klasse
			 * @see de.hdm.itProjektSS17.client.gui.BewerbungAufAusschreibungForm
			 */
			bewerbungenAnzeigenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();

					
					if(selectionModel.getSelectedObject() != null){

						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new BewerbungenAufAusschreibungForm(selectionModel.getSelectedObject().getAusschreibungid(), navigation, identityMarketChoice));
						navigation.setCurrentClickHandler(this);
						navigation.setCurrentClickEvent(event);

						
					} else {
						Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
					
				}
			});
			
			/**
			 * Click-Handler um den Ausschreibungstext der ausgewählten Ausschreibung
			 * in einer DialogBox anzuzeigen
			 */
			ausschreibungstextButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event) {
					AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();

					if (selectionModel.getSelectedObject() == null)
					{
						Window.alert("Bitte wählen Sie eine Stellenausschreibung aus");
					}
					
					DialogBoxAusschreibungstext text = new DialogBoxAusschreibungstext(selectedAusschreibung.getText());
					int left = Window.getClientWidth() / 3;
					int top = Window.getClientHeight() / 8;
					text.setPopupPosition(left, top);
					text.show();
					
			}
			});
		
			
			
	}
	


	
	public static int getPartnerprofilIdOfSelectedAusschreibung(){
		return partnerprofilId;
	}
	public static ClickHandler getClickHandlerForBewerbungen(){
		return clickhandler;
	}
	public static ClickEvent getClickEventForBewerbungen(){
		return clickevent;
	}
	

	/**
	 * Hybrid Klasse
	 * @author Tim
	 *
	 */
	public class AusschreibungProjektHybrid{
		
		/**
		 * Deklaration der Variablen
		 */
			private int projektid;
			private String projektname;
			private String bezeichnung;
			private Date bewerbungsfrist;
			private int ausschreibungid;
			private Ausschreibungsstatus status;
			
			private int ausschreibenderid;
			private String Text;
			private int partnerprofil;
			private int partnerprofilid;
			
		/**
		 * Anlegen der getter und setter Methoden
		 * 
		 * @return partnerprofilid
		 */
			public int getPartnerprofilid() {
				return partnerprofilid;
			}
			
		/**
		 * @param partnerprofilid
		 */
			public void setPartnerprofilid(int partnerprofilid) {
				this.partnerprofilid = partnerprofilid;
			}
		
			/**
			 * 
			 * @return partnerprofil
			 */
			public int getPartnerprofil() {
				return partnerprofil;
			}
			/**
			 * 
			 * @param partnerprofil
			 */
			public void setPartnerprofil(int partnerprofil) {
				this.partnerprofil = partnerprofil;
			}
			/**
			 * 
			 * @param text
			 */
			public void setText(String text) {
				Text = text;
			}
			/**
			 * 
			 * @return Text
			 */
			public String getText() {
				return Text;
			}
			/**
			 * 
			 * @param ausschreibenderid
			 */
			public void setAusschreibenderid(int ausschreibenderid) {
				this.ausschreibenderid = ausschreibenderid;
			}
			/**
			 * 
			 * @return ausschreibendid
			 */
			public int getAusschreibenderid() {
				return ausschreibenderid;
			}
			
			/**
			 * 
			 * @return ausschreibungid
			 */
			public int getAusschreibungid() {
				return ausschreibungid;
			}
			/**
			 * 
			 * @param ausschreibungid
			 */
			public void setAusschreibungid(int ausschreibungid) {
				this.ausschreibungid = ausschreibungid;
			}
			/**
			 * 
			 * @return bewerbungsfrist
			 */
			public Date getBewerbungsfrist() {
				return bewerbungsfrist;
			}
			/**
			 * 
			 * @param bewerbungsfrist
			 */
			public void setBewerbungsfrist(Date bewerbungsfrist) {
				this.bewerbungsfrist = bewerbungsfrist;
			}
			/**
			 * 
			 * @return bezeichnung
			 */
			public String getBezeichnung() {
				return bezeichnung;
			}
			/**
			 * 
			 * @param bezeichnung
			 */
			 public void setBezeichnung(String bezeichnung) {
				this.bezeichnung = bezeichnung;
			}
			/**
			 * 
			 * @returnprojektid
			 */
			public int getProjektid() {
				return projektid;
			}
			/**
			 * 
			 * @param projektid
			 */
			public void setProjektid(int projektid) {
				this.projektid = projektid;
			}
			/**
			 * 
			 * @return projektname
			 */
			public String getProjektname() {
				return projektname;
			}
			/**
			 * 
			 * @param projektname
			 */
			public void setProjektname(String projektname) {
				this.projektname = projektname;
			}
			public Ausschreibungsstatus getStatus() {
				return status;
			}
			public void setStatus(Ausschreibungsstatus status) {
				this.status = status;
			}
			
			
			
		}


}






