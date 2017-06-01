package de.hdm.itProjektSS17.client.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
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
import de.hdm.itProjektSS17.client.gui.DialogBoxEigenschaftHinzufuegen.SetEigenschaftenCallback;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;

public class MeineAusschreibungenForm extends Showcase{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static Vector<Ausschreibung> ausschreibungen = new Vector<>();
	private static int partnerprofilId = 0;
	private static ClickHandler clickhandler;
	private static ClickEvent clickevent;
	
	CellTable<Ausschreibung> dataGrid = new CellTable<Ausschreibung>();
	Button ausschreibungLoeschenButton = new Button("Löschen");
	Button ausschreibungBearbeitenButton = new Button("Bearbeiten");
	Button partnerprofilBearbeitenButton = new Button("Partnerprofil anzeigen");
	Button bewerbungenAnzeigenButton = new Button("Bewerbungen anzeigen");
	Button ausschreibungstextButton = new Button("Ausschreibungstext anzeigen");
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	//Formate der Datebox
	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	protected String getHeadlineText() {
		return "Von mir erstellte Ausschreibungen";
	}

	@Override
	protected void run() {
		RootPanel.get("Details").setWidth("70%");
		dataGrid.setWidth("100%", true);
		
		//CallBack um die Ausschreibungen der gewünschten Person zu laden
		projektmarktplatzVerwaltung.getOrganisationseinheitById(IdentityMarketChoice.getSelectedIdentityId(), new OrganisationseinheitCallback());
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
			//Erstellen der TextColumns der CellTable
				TextColumn<Ausschreibung> bezeichnungColumn = new TextColumn<Ausschreibung>(){

					@Override
					public String getValue(Ausschreibung object) {
						return object.getBezeichnung();
					}
				};
				

				TextColumn<Ausschreibung> bewerbungsfristColumn = new TextColumn<Ausschreibung>(){

					@Override
					public String getValue(Ausschreibung object) {
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
		
			// Hinzufügen der Spalten zu unserer CellTable
				dataGrid.addColumn(bezeichnungColumn, "Bezeichnung");
				dataGrid.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
//				dataGrid.addColumn(ausschreibungstextColumn, "Ausschreibungstext");
				
				
			// Anlegen des SingleSeletion Models
				final SingleSelectionModel<Ausschreibung> selectionModel = new SingleSelectionModel<>();
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
				ausschreibungBearbeitenButton.setStylePrimaryName("navi-button");
				ausschreibungLoeschenButton.setStylePrimaryName("navi-button");
				partnerprofilBearbeitenButton.setStylePrimaryName("navi-button");
				bewerbungenAnzeigenButton.setStylePrimaryName("navi-button");
				ausschreibungstextButton.setStylePrimaryName("navi-button");
				
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
					
					Ausschreibung selectedAusschreibung = selectionModel.getSelectedObject();
					
					if(selectionModel.getSelectedObject() != null){
						projektmarktplatzVerwaltung.deleteAusschreibung(selectedAusschreibung, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Die Ausschreibung wurde erfolgreich gelöscht.");
							
							Showcase showcase = new MeineAusschreibungenForm();
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(showcase);
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
								abbrechenButton.setStylePrimaryName("navi-button");
								speichernButton.setStylePrimaryName("navi-button");
								
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
											
											if(ausschreibungBezeichungBox.getText() != "" && ausschreibungstextBox.getText() != ""){
												Ausschreibung bearbeiteteAusschreibung = new Ausschreibung();
												bearbeiteteAusschreibung.setId(selectionModel.getSelectedObject().getId());
												bearbeiteteAusschreibung.setAusschreibenderId(selectionModel.getSelectedObject().getAusschreibenderId());
												bearbeiteteAusschreibung.setAusschreibungstext(ausschreibungstextBox.getText());
												bearbeiteteAusschreibung.setBewerbungsfrist(ausschreibungBewerbungsfristBox.getValue());
												bearbeiteteAusschreibung.setBezeichnung(ausschreibungBezeichungBox.getText());
												bearbeiteteAusschreibung.setPartnerprofilId(selectionModel.getSelectedObject().getPartnerprofilId());
												bearbeiteteAusschreibung.setProjektId(selectionModel.getSelectedObject().getProjektId());
												
												projektmarktplatzVerwaltung.saveAusschreibung(bearbeiteteAusschreibung, new AsyncCallback<Void>() {
													
													
													
													@Override
													public void onFailure(Throwable caught) {
														Window.alert("Das bearbeiten der Ausschreibung ist fehlgeschlagen.");
														
													}

													@Override
													public void onSuccess(Void result) {
														Window.alert("Die Ausschreibung wurde erfolgreich bearbeitet.");
														
														ausschreibungBearbeitenDialogBox.hide();					
														RootPanel.get("Details").clear();
														RootPanel.get("Details").add(new MeineAusschreibungenForm());
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
					
					if(selectionModel.getSelectedObject() != null){
						partnerprofilId = selectionModel.getSelectedObject().getPartnerprofilId();
					
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(partnerprofilId));
					
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
					
					if(selectionModel.getSelectedObject() != null){
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(new BewerbungenAufAusschreibungForm(selectionModel.getSelectedObject().getId()));
						
					} else {
						Window.alert("Bitte wähle zuerst eine Ausschreibung aus.");
					}
					
				}
			});
			
			ausschreibungstextButton.addClickHandler(new ClickHandler(){
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
		
			
			
	}
	
	
	private class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Organisationseinheit result) {		
			if (result != null) {
				projektmarktplatzVerwaltung.getAusschreibungByForeignOrganisationseinheit(result, new GetAusschreibungenByOrgaCallback());
			}			
		}
	
	}
	
	private class GetAusschreibungenByOrgaCallback implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Ausschreibungen konnten nicht geladen werden.");
			Window.alert("Fehler: "+ caught.toString());
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			
			if(result != null){
				//Anpassen der CellTable
				
				dataGrid.setRowCount(result.size(), true);
				dataGrid.setRowData(0, result);
			
			}
		}	
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
	
	
}
