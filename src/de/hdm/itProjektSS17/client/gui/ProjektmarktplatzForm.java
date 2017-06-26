package de.hdm.itProjektSS17.client.gui;

import de.hdm.itProjektSS17.client.Showcase;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.aria.client.SelectedValue;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

/**
 * Klasse für die verschiedenen Projektmarktplätze
 * @author Tim
 *
 */
public class ProjektmarktplatzForm extends Showcase {
	
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	/**
	 * Konstruktor, dem eine Instanz der IdentityMarketChoice und der Navigation übergeben wird.
	 * @param identityMarketChoice
	 * @param navigation
	 */
	public ProjektmarktplatzForm(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		identityMarketChoice.setOwnOrgUnitToZero();
		identityMarketChoice.deactivateProjectMarkets();
		identityMarketChoice.deactivateOrgUnits();
	}
	/**
	 * Auslesen der ProjektmarktplatzAsync Instanz
	 */
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	/**
	 * Anlegen globaler Vectoren und GUI-Elemente
	 */
	private static  Vector<Projektmarktplatz> projektmarktplaetzeGefiltert = new Vector<>();
	private static Vector<Projektmarktplatz> beigetreteneProjektmarktplaetze = new Vector<>();
	CellTable<Projektmarktplatz> ct_fremdeProjektmarktplaetze = new CellTable();
	CellTable<Projektmarktplatz> ct_eigeneProjektmarktplaetze = new CellTable();
	
	HorizontalPanel panel_projektmarktplatz = new HorizontalPanel();

	HorizontalPanel panel_beigetreteneProjektmarktplatz = new HorizontalPanel();

	HorizontalPanel panel_eigeneProjektmarktplaetze = new HorizontalPanel();

	HorizontalPanel hp_pager = new HorizontalPanel();

	//Deklarieren der Buttons

	Button btn_projektmarktplatzuebernehmen = new Button("Teilnehmen");
	Button btn_projektmarktplatzloeschen = new Button("Eigenen Projektmarktplatz löschen");
	Button btn_projektmarktplatzanlegen = new Button("Neuen Projektmarktplatz anlegen");

	Button btn_projektmarktplatzTeilnahmeentfernen = new Button("Teilnahme auflösen");
	
	Projektmarktplatz selectedObject_fremdeProjektmarktplaetze = new Projektmarktplatz();
	Projektmarktplatz selectedObject_eigeneProjektmarktplaetze = new Projektmarktplatz();
	
	SingleSelectionModel<Projektmarktplatz> ssm_fremdeProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	SingleSelectionModel<Projektmarktplatz> ssm_eigeneProjektmarktplaetze = new SingleSelectionModel<Projektmarktplatz>();
	
	/**
	 * Setzen des HeadLine Textes
	 */
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Projektmarktplätze";
	}

	/**
	 * Methode die starten wenn diese Form aufgerufen wird
	 */
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Details").setWidth("75%");
		ct_fremdeProjektmarktplaetze.setWidth("100%", true);
		ct_eigeneProjektmarktplaetze.setWidth("100%", true);
		
		this.setSpacing(8);
		this.add(panel_projektmarktplatz);
		
		//Stylen der Buttons
		btn_projektmarktplatzanlegen.setStylePrimaryName("cell-btn");
		btn_projektmarktplatzloeschen.setStylePrimaryName("cell-btn");
		btn_projektmarktplatzTeilnahmeentfernen.setStylePrimaryName("cell-btn");
		btn_projektmarktplatzuebernehmen.setStylePrimaryName("cell-btn");
		
		//Hinzufügen der Buttons zum Panel

		panel_eigeneProjektmarktplaetze.add(btn_projektmarktplatzanlegen);
		panel_eigeneProjektmarktplaetze.add(btn_projektmarktplatzloeschen);
		panel_eigeneProjektmarktplaetze.add(btn_projektmarktplatzTeilnahmeentfernen);	

		panel_projektmarktplatz.add(btn_projektmarktplatzuebernehmen);
		panel_beigetreteneProjektmarktplatz.add(btn_projektmarktplatzloeschen);
		
		/**
		 * Click-Handler um einen neuen Projektmarktplatz anzulegen.
		 * Hierzu wird eine neue DialogBox aufgerufen
		 * @see de.hdm.itProjektSS17.client.gui.DialogBoxProjektmarktplatzErstellen
		 */
		btn_projektmarktplatzanlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBoxProjektmarktplatzErstellen dbErstellen = new DialogBoxProjektmarktplatzErstellen(identityMarketChoice, navigation);
				dbErstellen.center();	
			}
		});
		
		/**
		 * Click-Handler zum löschen eines Projektparktplatzer.
		 * Hierzu wird der ausgewählte Projektmarktplatz der Methode zum löschen übergeben.
		 */
		btn_projektmarktplatzloeschen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				Projektmarktplatz selectedObject = ssm_eigeneProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					ClientsideSettings.getProjektmarktplatzVerwaltung().deleteProjektmarktplatz(selectedObject, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
							navigation.reload();
							identityMarketChoice.reinitialize();
						}
						
						@Override
						public void onFailure(Throwable caught) {

							Window.alert("Fehler: Der Projektmarktplatz konnte nicht gel�scht werden. ");
						
						}
					});
					
				}else {
					Window.alert("Es wurde kein Projektmarktplatz ausgewählt");
				}
				
			}
		});
		
		/**
		 * Click-Handler um einem Projektmarktplatz beizutreten.
		 * Hierzu wird eine Teilnahe erstellt zwischen dem eingeloggten User und dem ausgewählten Projektmarktplatz.
		 */
		btn_projektmarktplatzuebernehmen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Projektmarktplatz selectedObject = ssm_fremdeProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					ClientsideSettings.getProjektmarktplatzVerwaltung().createTeilnahme(identityMarketChoice.getSelectedIdentityId(), selectedObject.getId(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Der Projektmarktplatz wurde erfolgreich zum eigenen Profil hinzugefügt!");
							identityMarketChoice.reinitialize();
							navigation.reload();
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
		
		/**
		 * Click-Handler um eine Teilnahme an einem Projektmarktplatz aufzulösen.
		 * Hierzu wird die eingeloggte Person und der ausgewählte Projektmarktplatz der delete-Methode übergeben.
		 */
		btn_projektmarktplatzTeilnahmeentfernen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Projektmarktplatz selectedObject = ssm_eigeneProjektmarktplaetze.getSelectedObject();
				
				if (selectedObject != null) {
					Person tempPerson = new Person();
					tempPerson.setId(identityMarketChoice.getSelectedIdentityId());
					ClientsideSettings.getProjektmarktplatzVerwaltung().deleteTeilnahme(tempPerson, selectedObject, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("Teilnahme wurde erfolgreich aufgelöst!");
							navigation.reload();
							identityMarketChoice.reinitialize();
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
	
		/**
		 * CallBack um alle Projektmarktplätze zu erhalten.
		 * Diese werden in dem Vector <code>projektmarktplaetzeGefiltert</code> gespeichert.
		 */
		projektmarktplatzVerwaltung.getAllProjektmarktplatz(new AsyncCallback<Vector<Projektmarktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail");
			}

			/**
			 * Bei erfolgreichem CallBack wird ein neuer Callback aufgerufen, welcher
			 * einen Vector mit allen Projektmarktplätze auf denen sich der eingeloggte User bereits befindet 
			 * zurück gibt. 
			 */
			@Override
			public void onSuccess(Vector<Projektmarktplatz> result) {
				
				Person tempPerson = new Person();
				tempPerson.setId(identityMarketChoice.getSelectedIdentityId());
				projektmarktplaetzeGefiltert=result;
				projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(tempPerson, new AsyncCallback<Vector<Projektmarktplatz>>() {
				
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					/**
					 * Die Projektmarktplätze auf denen sich der User bereits befindet werden in dem Vector
					 * <code>beigetretenProjektmarktplaetze</code> gespeichert.
					 * Anschließend werden diese aus dem Vector <code>projektmarktplaetzeGefiltert</code> rausgelöscht,
					 * so dass sich in diesem Vector nur noch die Projektmarktplaetze befinden auf denen sich der
					 * User noch nicht befindet.
					 */
					@Override
					public void onSuccess(Vector<Projektmarktplatz> result) {
						beigetreteneProjektmarktplaetze = result;
						for (Projektmarktplatz projektmarktplatz : result) {
							for(int i=0; i<projektmarktplaetzeGefiltert.size(); i++){
								if(projektmarktplaetzeGefiltert.get(i).getId()==projektmarktplatz.getId()){
									projektmarktplaetzeGefiltert.remove(i);
								}
							}
						}
						/**
						 * Hinzügen zur cellTable
						 */
						if(result != null && projektmarktplaetzeGefiltert != null){
							
							
							ct_fremdeProjektmarktplaetze.setRowData(0, projektmarktplaetzeGefiltert);
							ct_fremdeProjektmarktplaetze.setRowCount(projektmarktplaetzeGefiltert.size(), true);
							
							/**
							 * Pager anlegen
							 */
							final ListDataProvider dataProvider = new ListDataProvider();
							SimplePager pager;
							SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
							pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
							pager.setDisplay(ct_fremdeProjektmarktplaetze);
							dataProvider.addDataDisplay(ct_fremdeProjektmarktplaetze);
							dataProvider.setList(new ArrayList<Projektmarktplatz>(projektmarktplaetzeGefiltert));
							pager.setPageSize(5);
							
						
							hp_pager.setWidth("100%");
							hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
							hp_pager.add(pager);
							
							

							
							ct_eigeneProjektmarktplaetze.setRowData(0, result);
							ct_eigeneProjektmarktplaetze.setRowCount(result.size(), true);
							
							
							final ListDataProvider dataProvider1 = new ListDataProvider();
							SimplePager pager1;
							SimplePager.Resources pagerResources1 = GWT.create(SimplePager.Resources.class);
							pager1 = new SimplePager(TextLocation.CENTER, pagerResources1, false, 0, true);
							pager1.setDisplay(ct_eigeneProjektmarktplaetze);
							dataProvider1.addDataDisplay(ct_eigeneProjektmarktplaetze);
							dataProvider1.setList(new ArrayList<Projektmarktplatz>(beigetreteneProjektmarktplaetze));
							pager1.setPageSize(5);
							
							HorizontalPanel hp_pager1 = new HorizontalPanel();
							hp_pager1.setWidth("100%");
							hp_pager1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
							hp_pager1.add(pager1);
							add(hp_pager1);
							
							}

						
					}
				});
				

				}
		});

		
		/**
		 * Anlegen der TextColumn für fremde Projektmarktplaetze und
		 * hinzufügen zur cellTable.
		 */
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn_fremd = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		ct_fremdeProjektmarktplaetze.addColumn(ProjektmarktplatznameColumn_fremd, "Nicht beigetretene Projektmarktplätze");
	
		
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
		
		this.add(hp_pager);
	
		
		/**
		 * Anlegen der Textcolumn für eigene Projektmarktplaetze und
		 * hinzufügen zur cellTable
		 */
		TextColumn<Projektmarktplatz> ProjektmarktplatznameColumn_eigene = new TextColumn<Projektmarktplatz>() {
			@Override
			public String getValue(Projektmarktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		};
		ct_eigeneProjektmarktplaetze.addColumn(ProjektmarktplatznameColumn_eigene, "Beigetretene Projektmarktplätze");
	
		//SingleSelectionModel anlegen um verschiedene Zeilen auszuwählen
		
		ct_eigeneProjektmarktplaetze.setSelectionModel(ssm_eigeneProjektmarktplaetze);
		ssm_eigeneProjektmarktplaetze.addSelectionChangeHandler(new Handler() {
		    @Override
		    public void onSelectionChange(final SelectionChangeEvent event)
		    {
		        selectedObject_eigeneProjektmarktplaetze = ssm_eigeneProjektmarktplaetze.getSelectedObject();
		    }
		});
		
		
		panel_beigetreteneProjektmarktplatz.add(btn_projektmarktplatzTeilnahmeentfernen);
		this.add(panel_beigetreteneProjektmarktplatz);
		
		
		ct_eigeneProjektmarktplaetze.setWidth("100%");
		this.add(panel_eigeneProjektmarktplaetze);
		this.add(ct_eigeneProjektmarktplaetze);
		
	
		
		}		
		
	
	}


