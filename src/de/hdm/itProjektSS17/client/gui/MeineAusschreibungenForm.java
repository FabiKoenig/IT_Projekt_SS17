package de.hdm.itProjektSS17.client.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.BeteiligungenForm.BeteiligungProjektHybrid;
import de.hdm.itProjektSS17.client.gui.DialogBoxEigenschaftHinzufuegen.SetEigenschaftenCallback;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;

public class MeineAusschreibungenForm extends Showcase{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static Vector<Ausschreibung> ausschreibungen = new Vector<>();
	private static int partnerprofilId = 0;
	private static ClickHandler clickhandler;
	private static ClickEvent clickevent;
	
	CellTable<AusschreibungProjektHybrid> dataGrid = new CellTable<AusschreibungProjektHybrid>();
	Button ausschreibungLoeschenButton = new Button("Löschen");
	//Vector<Ausschreibung> ausschreibungen1 = new Vector<Ausschreibung>();
	Vector <Projekt> projekt = new Vector <Projekt>();
//	AusschreibungProjektHybrid localhybrid;

	Vector<AusschreibungProjektHybrid> hybrid = new Vector<AusschreibungProjektHybrid>();
	
	Button ausschreibungBearbeitenButton = new Button("Bearbeiten");
	Button partnerprofilBearbeitenButton = new Button("Gefordertes Partnerprofil anzeigen");
	Button bewerbungenAnzeigenButton = new Button("Bewerbungen anzeigen");
	Button ausschreibungstextButton = new Button("Ausschreibungstext anzeigen");
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	public MeineAusschreibungenForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
	}
	
	@Override
	protected String getHeadlineText() {
		return "Von mir erstellte Ausschreibungen";
	}

	@Override
	protected void run() {
		RootPanel.get("Details").setWidth("75%");
		dataGrid.setWidth("100%", true);
		
		//CallBack um die Ausschreibungen der gewünschten Person zu laden
		projektmarktplatzVerwaltung.getOrganisationseinheitById(identityMarketChoice.getSelectedIdentityId(), new AsyncCallback<Organisationseinheit>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");				
			}

			@Override
			public void onSuccess(Organisationseinheit result) {
				// TODO Auto-generated method stub
				if (result != null) {
					projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new AsyncCallback<Vector<Ausschreibung>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: "+caught.toString());
						}

						@Override
						public void onSuccess(Vector<Ausschreibung> result) {
							for (Ausschreibung ausschreibung : result) {
								final AusschreibungProjektHybrid localhybrid = new AusschreibungProjektHybrid();
								
								localhybrid.setBezeichnung(ausschreibung.getBezeichnung());
								localhybrid.setBewerbungsfrist(ausschreibung.getBewerbungsfrist());
								localhybrid.setAusschreibenderid(ausschreibung.getAusschreibenderId());
								localhybrid.setAusschreibungid(ausschreibung.getId());
								localhybrid.setText(ausschreibung.getAusschreibungstext());
								localhybrid.setProjektid(ausschreibung.getProjektId());
								localhybrid.setPartnerprofilid(ausschreibung.getPartnerprofilId());
								dataGrid.setRowCount(hybrid.size(), true);
								dataGrid.setRowData(hybrid);
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
									}
								});

								}
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
				
		
//				TextColumn<Ausschreibung> ausschreibungstextColumn = new TextColumn<Ausschreibung>(){
//
//					@Override
//					public String getValue(Ausschreibung object) {
//						return object.getAusschreibungstext();
//					}
//				};
				
//		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
//				
//		//Erstellen der TextColumns der CellTable
//				TextColumn<Ausschreibung> projektColumn = new TextColumn<Ausschreibung>(){
//
//					@Override
//					public String getValue(Ausschreibung object) {
//						return object.getAusschreibungstext();
//					}
//				};		
		
			// Hinzufügen der Spalten zu unserer CellTable+
				dataGrid.addColumn(projektColumn, "Projektname");
				dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
				dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
				dataGrid.setEmptyTableWidget(new Label("Es sind keine eigenen Ausschreibungen vorhanden"));
				dataGrid.setLoadingIndicator(null);
				
//				dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstext");
				
				
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
				
				
			/**
			 * CLICK-HANDLER
			 */
				
				
				/**
				 * Click-Handler zum löschen einer Ausschreibung
				 */
			ausschreibungLoeschenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					
					
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
								final TextBox ausschreibungBezeichungBox = new TextBox();
								final DateBox ausschreibungBewerbungsfristBox = new DateBox();
								final TextArea ausschreibungstextBox = new TextArea();
								Button abbrechenButton = new Button("Abbrechen");
								Button speichernButton = new Button("Speichern");
								Button zurueckButton = new Button("Zurück");
								
								//Setzen des Formats
								
								DateTimeFormat dateformat = DateTimeFormat.getFormat("dd.MM.yyyy");
								ausschreibungBewerbungsfristBox.setFormat(new DateBox.DefaultFormat(dateformat));
//								ausschreibungstextBox.setHeight("20px");
								ausschreibungstextBox.setVisibleLines(20);
								ausschreibungstextBox.setCharacterWidth(70);
								ausschreibungBearbeitenDialogBox.setGlassEnabled(true);
								ausschreibungBearbeitenDialogBox.setAnimationEnabled(false);
								
							//Erstellen der FlexTable
								ausschreibungBearbeitenFlexTable.setWidget(0, 1, ausschreibungBezeichungBox);
								ausschreibungBearbeitenFlexTable.setWidget(0, 0, ausschreibungBezeichungLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(1, 1, ausschreibungBewerbungsfristBox);
								ausschreibungBearbeitenFlexTable.setWidget(1, 0, ausschreibungBewerbungsfristLabel);
								
								ausschreibungBearbeitenFlexTable.setWidget(2, 1, ausschreibungstextBox);
								ausschreibungBearbeitenFlexTable.setWidget(2, 0, ausschreibungstextLabel);
								
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
								ausschreibungBearbeitenDialogBox.center();
								ausschreibungBearbeitenDialogBox.show();
								} else {
									Window.alert("Bitte wähle zuerst die Ausschreibung aus, die bearbeitet werden soll.");
								}
								/**
								 * CLICK-HANDLER
								 */
									
									abbrechenButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											ausschreibungBearbeitenDialogBox.hide();
										}
									});
									
									speichernButton.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
//											
//											
//											AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();
//											
//											if(selectionModel.getSelectedObject() != null){
//												Ausschreibung localAusschreibung = new Ausschreibung();
//												localAusschreibung.setId(selectedAusschreibung.getAusschreibungid());
//												projektmarktplatzVerwaltung.deleteAusschreibung(localAusschreibung, new AsyncCallback<Void>() {
//												
//												@Override
											
											AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();
											
											if(ausschreibungBezeichungBox.getText() != "" && ausschreibungstextBox.getText() != ""){
												Ausschreibung bearbeiteteAusschreibung = new Ausschreibung();
												
												bearbeiteteAusschreibung.setId(selectedAusschreibung.getAusschreibungid());
												bearbeiteteAusschreibung.setAusschreibenderId(selectedAusschreibung.getAusschreibenderid());
												//bearbeiteteAusschreibung.setAusschreibungstext(selectedAusschreibung.getText());
												//bearbeiteteAusschreibung.setBewerbungsfrist(selectedAusschreibung.getBewerbungsfrist());
												bearbeiteteAusschreibung.setPartnerprofilId(selectedAusschreibung.getPartnerprofil());
												bearbeiteteAusschreibung.setProjektId(selectedAusschreibung.getProjektid());
												
												//bearbeiteteAusschreibung.setId(selectionModel.getSelectedObject().getId());
												//bearbeiteteAusschreibung.setAusschreibenderId(selectionModel.getSelectedObject().getAusschreibenderId());
												bearbeiteteAusschreibung.setAusschreibungstext(ausschreibungstextBox.getText());
												bearbeiteteAusschreibung.setBewerbungsfrist(ausschreibungBewerbungsfristBox.getValue());
												bearbeiteteAusschreibung.setBezeichnung(ausschreibungBezeichungBox.getText());
											//	bearbeiteteAusschreibung.setPartnerprofilId(selectionModel.getSelectedObject().getPartnerprofilId());
												//bearbeiteteAusschreibung.setProjektId(selectionModel.getSelectedObject().getProjektId());
												
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
//						Navigation.setCurrentClickHandler(this);
//						Navigation.setCurrentClickEvent(event);
						
						} else {
							Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
				}
			});
			
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
			
			ausschreibungstextButton.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event) {
					AusschreibungProjektHybrid selectedAusschreibung = selectionModel.getSelectedObject();

					if (selectionModel.getSelectedObject() == null)
					{
						Window.alert("Bitte wählen Sie eine Stellenausschreibung aus");
					}
					
					DialogBoxAusschreibungstext text = new DialogBoxAusschreibungstext(selectedAusschreibung.getText());
					//DialogBoxAusschreibungstext text = new DialogBoxAusschreibungstext(selectionModel.getSelectedObject().getAusschreibungstext());
					int left = Window.getClientWidth() / 3;
					int top = Window.getClientHeight() / 8;
					text.setPopupPosition(left, top);
					text.show();
					
			}
			});
		
			
			
	}
	
//	//Geändert
//	public class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Organisationseinheit result) {		
//			if (result != null) {
//				projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new GetAusschreibungenByOrgaCallback());
//				
//			}			
//		}
//	
//	}
	//Geändert
//	public class GetAusschreibungenByOrgaCallback implements AsyncCallback<Vector<Ausschreibung>>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: Die Ausschreibungen konnten nicht geladen werden.");
//			Window.alert("Fehler: "+ caught.toString());
//			
//		}
//
//		public void onSuccess(Vector<Ausschreibung> result) {
//				
//			for (Ausschreibung ausschreibung : result) {
//				localhybrid = new AusschreibungProjektHybrid();
//				
//				localhybrid.setBezeichnung(ausschreibung.getBezeichnung());
//				localhybrid.setBewerbungsfrist(ausschreibung.getBewerbungsfrist());
//				hybrid.add(localhybrid);
//				dataGrid.setRowCount(hybrid.size(), true);
//				dataGrid.setRowData(hybrid);
//				projektmarktplatzVerwaltung.getProjektbyAusschreibung(ausschreibung, new getAusschreibungProjekte());
//
//				}	
//			}
//		}
//	
//	
//	
//	private class getAusschreibungProjekte implements AsyncCallback<Projekt>{
//
//	@Override
//	public void onFailure(Throwable caught) {
//			Window.alert("Fehler: " + caught.toString());				
//		
//	}
//
//	@Override
//	public void onSuccess(Projekt result) {
//			
//			localhybrid.setProjektname(result.getName());
//			hybrid.add(localhybrid);
//			dataGrid.setRowCount(hybrid.size(), true);
//			dataGrid.setRowData(hybrid);
//		}	
//	}
	
//	public class ProjektAusschreibungCallback implements AsyncCallback<Projekt>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler:");
//			
//		}
//
//		@Override
//		public void onSuccess(Projekt result) {
//			if(result!=null){
//				
//				projektmarktplatzVerwaltung.getProjektbyAusschreibung(result, callback);
//			}
//			
//		}
//		
//		
//	}

	
	public static int getPartnerprofilIdOfSelectedAusschreibung(){
		return partnerprofilId;
	}
	public static ClickHandler getClickHandlerForBewerbungen(){
		return clickhandler;
	}
	public static ClickEvent getClickEventForBewerbungen(){
		return clickevent;
	}
	
//	 private class getAusschreibungProjekte implements AsyncCallback<Vector<Ausschreibung>>{
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler: " + caught.toString());				
//			}
//
//			public void onSuccess(Ausschreibung result) {
//				
//				//Warum gehts das hier obwohl in BeteiligungForm die Forschleife als ProjektVektor deklariert ist.?
//				for(Ausschreibung ausschreibung : beteiligungen){
//
//					final AusschreibungProjektHybrid localHybrid = new AusschreibungProjektHybrid();
//					
//					if(result != null){
//				
//					localHybrid.setProjektname(.);
//					hybrid.add(localHybrid);
//											
//					}
//					dataGrid.setRowCount(result.size());
//					dataGrid.setRowData(hybrid);
//					
//					
//				}
							



	
	
	
			
		
		
		
	
					
				

					
				
			


		
	 
	 
		public class AusschreibungProjektHybrid{
			
			private int projektid;
			private String projektname;
			private String bezeichnung;
			private Date bewerbungsfrist;
			private int ausschreibungid;
			
			private int ausschreibenderid;
			private String Text;
			private int partnerprofil;
			private int partnerprofilid;
			
			
			
			
			
			public int getPartnerprofilid() {
				return partnerprofilid;
			}
			public void setPartnerprofilid(int partnerprofilid) {
				this.partnerprofilid = partnerprofilid;
			}
			
			public int getPartnerprofil() {
				return partnerprofil;
			}
			
			public void setPartnerprofil(int partnerprofil) {
				this.partnerprofil = partnerprofil;
			}
			
			public void setText(String text) {
				Text = text;
			}
			
			public String getText() {
				return Text;
			}
			
			public void setAusschreibenderid(int ausschreibenderid) {
				this.ausschreibenderid = ausschreibenderid;
			}
			
			public int getAusschreibenderid() {
				return ausschreibenderid;
			}
			
			
			public int getAusschreibungid() {
				return ausschreibungid;
			}
			
			public void setAusschreibungid(int ausschreibungid) {
				this.ausschreibungid = ausschreibungid;
			}
			
			public Date getBewerbungsfrist() {
				return bewerbungsfrist;
			}
			
			public void setBewerbungsfrist(Date bewerbungsfrist) {
				this.bewerbungsfrist = bewerbungsfrist;
			}
			
			public String getBezeichnung() {
				return bezeichnung;
			}
			
			 public void setBezeichnung(String bezeichnung) {
				this.bezeichnung = bezeichnung;
			}
			
			public int getProjektid() {
				return projektid;
			}
			public void setProjektid(int projektid) {
				this.projektid = projektid;
			}
			
			public String getProjektname() {
				return projektname;
			}
			
			public void setProjektname(String projektname) {
				this.projektname = projektname;
			}
			
			
			
		}


}






