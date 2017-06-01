package de.hdm.itProjektSS17.client.gui;

import de.hdm.itProjektSS17.client.Showcase;

import java.util.Vector;

import com.google.gwt.aria.client.SelectedValue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

public class ProjektmarktplatzForm extends Showcase {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static  Vector<Projektmarktplatz> projektmarktplaetzeGefiltert = new Vector<>();
	
	CellTable<Projektmarktplatz> ct_fremdeProjektmarktplaetze = new CellTable();
	CellTable<Projektmarktplatz> ct_eigeneProjektmarktplaetze = new CellTable();
	
	
	HorizontalPanel panel_projektmarktplatz = new HorizontalPanel();
	
	//Deklarieren der Buttons
	Button btn_projektmarktplatzuebernehmen = new Button("An bestehendem Projektmarktplatz teilnehmen");
	Button btn_projektmarktplatzloeschen = new Button("Eigenen Projektmarktplatz löschen");
	Button btn_projektmarktplatzanlegen = new Button("Neuen Projektmarktplatz anlegen");
	Button btn_projektmarktplatzTeilnahmeentfernen = new Button("Teilnahme auflösen");
	
	Projektmarktplatz selectedObject_fremdeProjektmarktplaetze = new Projektmarktplatz();
	Projektmarktplatz selectedObject_eigeneProjektmarktplaetze = new Projektmarktplatz();
	
	SingleSelectionModel<Projektmarktplatz> ssm_fremdeProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	SingleSelectionModel<Projektmarktplatz> ssm_eigeneProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Alle Projektmarktplätze";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		
		this.add(panel_projektmarktplatz);
		
		//Stylen der Buttons
		btn_projektmarktplatzanlegen.setStylePrimaryName("navi-button");
		btn_projektmarktplatzloeschen.setStylePrimaryName("navi-button");
		
		//Hinzufügen der Buttons zum Panel
		panel_projektmarktplatz.add(btn_projektmarktplatzanlegen);
		panel_projektmarktplatz.add(btn_projektmarktplatzloeschen);
		panel_projektmarktplatz.add(btn_projektmarktplatzuebernehmen);
		panel_projektmarktplatz.add(btn_projektmarktplatzTeilnahmeentfernen);
		
		btn_projektmarktplatzanlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBoxProjektmarktplatzErstellen dbErstellen = new DialogBoxProjektmarktplatzErstellen();
				dbErstellen.center();
				dbErstellen.show();		
			}
		});
		
		btn_projektmarktplatzloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Projektmarktplatz selectedObject = ssm_eigeneProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					ClientsideSettings.getProjektmarktplatzVerwaltung().deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
							Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
							IdentityMarketChoice.getNavigation().reinitialize();
						}
						
						@Override
						public void onFailure(Throwable caught) {

							Window.alert("Fehler: Der Projektmarktplatz konnte nicht gel�scht werden. ");
						
						}
					});
					
				}else {
					Window.alert("Es wurde kein Projektmarktplatz ausgew�hlt");
				}
				
			}
		});
		
		btn_projektmarktplatzuebernehmen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Projektmarktplatz selectedObject = ssm_fremdeProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					ClientsideSettings.getProjektmarktplatzVerwaltung().createTeilnahme(IdentityMarketChoice.getSelectedIdentityId(), selectedObject.getId(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Der Projektmarktplatz wurde erfolgreich zum eigenen Profil hinzugefügt!");
							Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
							IdentityMarketChoice.getNavigation().reinitialize();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Der Projektmarktplatz konnte nicht zum eigenen Profil hinzugefügt werden.");
							
						}
					});
						
					
				}else {
					Window.alert("Es wurde kein fremder Projektmarktplatz ausgewählt");
				}
				
			}
		});
		
		btn_projektmarktplatzTeilnahmeentfernen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Projektmarktplatz selectedObject = ssm_eigeneProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					Person tempPerson = new Person();
					tempPerson.setId(IdentityMarketChoice.getSelectedIdentityId());
					ClientsideSettings.getProjektmarktplatzVerwaltung().deleteTeilnahme(tempPerson, selectedObject, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("Teilnahme wurde erfolgreich aufgelöst!");
							Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
							IdentityMarketChoice.getNavigation().reinitialize();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: Die Teilnahme konnte nicht gelöscht werden. ");
							
						}
					});

					
				}else {
					Window.alert("Es wurde kein eigener Projektmarktplatz ausgewählt");
				}
				
				
			}
		});
	
		
		projektmarktplatzVerwaltung.getAllProjektmarktplatz(new AsyncCallback<Vector<Projektmarktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail");
			}

			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				
				Person tempPerson = new Person();
				tempPerson.setId(IdentityMarketChoice.getSelectedIdentityId());
				projektmarktplaetzeGefiltert=result;
				projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(tempPerson, new AsyncCallback<Vector<Projektmarktplatz>>() {
				
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Projektmarktplatz> result) {
						for (Projektmarktplatz projektmarktplatz : result) {
							for(int i=0; i<projektmarktplaetzeGefiltert.size(); i++){
								if(projektmarktplaetzeGefiltert.get(i).getId()==projektmarktplatz.getId()){
									projektmarktplaetzeGefiltert.remove(i);
								}
							}
						}
						if(result != null && projektmarktplaetzeGefiltert != null){
							
							ct_fremdeProjektmarktplaetze.setRowData(0, projektmarktplaetzeGefiltert);
							ct_fremdeProjektmarktplaetze.setRowCount(projektmarktplaetzeGefiltert.size(), true);
							ct_eigeneProjektmarktplaetze.setRowData(0, result);
							ct_eigeneProjektmarktplaetze.setRowCount(result.size(), true);
							}

						
					}
				});
				

				}
		});

		
		
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn_fremd = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		ct_fremdeProjektmarktplaetze.addColumn(ProjektmarktplatznameColumn_fremd, "Fremde Projektmarktplätze");
	
		//SingleSelectionModel anlegen um verschiedene Zeilen auszuwählen
		
		ct_fremdeProjektmarktplaetze.setSelectionModel(ssm_fremdeProjektmarktplaetze);
		ssm_fremdeProjektmarktplaetze.addSelectionChangeHandler(new Handler() {
		    @Override
		    public void onSelectionChange(final SelectionChangeEvent event)
		    {
		        selectedObject_fremdeProjektmarktplaetze = ssm_fremdeProjektmarktplaetze.getSelectedObject();
		        // do what you want
		    }
		});
		
		
		
		ct_fremdeProjektmarktplaetze.setWidth("100%");
		
		this.add(ct_fremdeProjektmarktplaetze);		
		
		
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn_eigene = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		ct_eigeneProjektmarktplaetze.addColumn(ProjektmarktplatznameColumn_eigene, "Eigene Projektmarktplätze");
	
		//SingleSelectionModel anlegen um verschiedene Zeilen auszuwählen
		
		ct_eigeneProjektmarktplaetze.setSelectionModel(ssm_eigeneProjektmarktplaetze);
		ssm_eigeneProjektmarktplaetze.addSelectionChangeHandler(new Handler() {
		    @Override
		    public void onSelectionChange(final SelectionChangeEvent event)
		    {
		        selectedObject_eigeneProjektmarktplaetze = ssm_eigeneProjektmarktplaetze.getSelectedObject();
		    }
		});
		
		
		
		ct_eigeneProjektmarktplaetze.setWidth("100%");
		
		this.add(ct_eigeneProjektmarktplaetze);
		
		}		
		
	
	}


