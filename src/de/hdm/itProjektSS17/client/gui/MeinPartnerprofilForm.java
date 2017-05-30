package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.appengine.tools.admin.VerificationCodeReceiverRedirectUriException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;

public class MeinPartnerprofilForm extends Showcase{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private static int partnerprofilId = 0;
	
	
	//Elemente für die GUI
	CellTable<Eigenschaft> dataGrid = new CellTable<Eigenschaft>();
	Button loeschenButton = new Button("Löschen");
	Button eigenschaftHinzufuegenButton = new Button("Eigenschaft hinzufügen");
	HorizontalPanel buttonPanel = new HorizontalPanel();

	

	protected String getHeadlineText() {
		return "Meine Eigenschaften";
	}

	
	protected void run() {
		
		//CallBack um die Eigenschaften der gewünschten Person zu laden
		projektmarktplatzVerwaltung.getOrganisationseinheitById(IdentityMarketChoice.getSelectedIdentityId(), new OrganisationseinheitCallback());
		
		dataGrid.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		//Erstellen der TextColumns der CellTable
		TextColumn<Eigenschaft> nameColumn = new TextColumn<Eigenschaft>(){

			@Override
			public String getValue(Eigenschaft object) {
				return object.getName();
			}
		};
		
		TextColumn<Eigenschaft> wertColumn = new TextColumn<Eigenschaft>() {

			@Override
			public String getValue(Eigenschaft object) {
				return object.getWert();
			}
		};
		
		//Hinzufügen der TextColumns zur CellTable		
		dataGrid.addColumn(nameColumn, "Beschreibung");
		dataGrid.addColumn(wertColumn, "Wert");
		
		
		//
		final SingleSelectionModel<Eigenschaft> selectionModel = new SingleSelectionModel<>();
		dataGrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
				
			}
		});
		
		//Anpassen der CellTable
		dataGrid.setWidth("100%");
		
		//Hinzufügen der Buttons zum ButtonPanel
		buttonPanel.add(eigenschaftHinzufuegenButton);
		buttonPanel.add(loeschenButton);
		
		//Stylen der Buttons
		eigenschaftHinzufuegenButton.setStylePrimaryName("navi-button");
		loeschenButton.setStylePrimaryName("navi-button");
		
		//Hinzufügen der CellTable und des ButtonPanels zu unserem Showcase
		this.setSpacing(8);
		this.add(buttonPanel);
		this.add(dataGrid);
		
		
		/**
		 * Click-Handler
		 */
		
		eigenschaftHinzufuegenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogBoxEigenschaftHinzufuegen gg = new DialogBoxEigenschaftHinzufuegen(partnerprofilId);
				gg.center();
				gg.show();
		
			}
		});
		
		loeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Eigenschaft selectedEigenschaft = selectionModel.getSelectedObject();
				projektmarktplatzVerwaltung.deleteEigenschaft(selectedEigenschaft, new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						Window.alert("Fehler: Die Eigenschaft konnte nicht gelöscht werden.");
					}
					public void onSuccess(Void result) {
						Window.alert("Die Eigenschaft wurde erfolgreich gelöscht.");
						
						Showcase showcase = new MeinPartnerprofilForm();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
					}
				});
				
			}
		});
		
		

	}
	
//	// GUI Elemente
//	private HorizontalPanel buttonPanel = new HorizontalPanel();
//	private Button addEigenschaftenButton = new Button("Eigenschaften hinzufügen");// Clickhandler hinzufuegen
//	private Button speicherButton = new Button("Speichern"); //Speichern CklickHandler hinzufuegen
//	private Button loeschenButton = new Button("Löschen"); //Löschen Clickhandler hinzufuegen
//	private FlexTable eigenschaftenTable = new FlexTable();
//	
//	//Style der GUI Elemente
//	
//
//	
//	
//	public PartnerprofilEigenschaftenForm(){
//		
//		projektmarktplatzVerwaltung.getPersonById(3, new PersonCallback());
//		buttonPanel.add(addEigenschaftenButton);
//		this.add(buttonPanel);
//		this.setSpacing(8);
//		this.add(eigenschaftenTable);
//		
//		
//	}
//	

//	
//	public class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>>{
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler: Die Eigenschaften konnten nicht ausgelesen werden" );
//		}
//
//		@Override
//		public void onSuccess(Vector<Eigenschaft> result) {
//
//			
//			
//			if (result != null) {
//				for (Eigenschaft eigenschaft: result){
//					int row = eigenschaftenTable.getRowCount();
//					TextBox textbox = new TextBox();
//					
//					textbox.setText(eigenschaft.getWert());
//					textbox.setReadOnly(true);
//					
//					eigenschaftenTable.setWidget(row + 1, 1, textbox);
//					eigenschaftenTable.setWidget(row + 1, 0, new Label(eigenschaft.getName()));
//					//eigenschaftenTable.setWidget(row + 1, 1, new Label(eigenschaft.getWert()));
//				}	
//			}
//			
//		}
//	}
//	

//	}
	
	
	/**
	 * ASYNC-CALLBACKS
	 */
	
	public class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Eigenschaften konnten nicht ausgelesen werden" );
		}

		@Override
		public void onSuccess(Vector<Eigenschaft> result) {
			dataGrid.setRowCount(result.size(), true);
			dataGrid.setRowData(0, result);
		}	
	}
	
	public class GetPartnerProfilCallback implements AsyncCallback<Partnerprofil>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Das Partnerprofil konnte nicht ausgelesen werden");
		}

		@Override
		public void onSuccess(Partnerprofil result) {
			partnerprofilId = result.getId();
			projektmarktplatzVerwaltung.getEigenschaftByForeignPartnerprofil(result, new GetEigenschaftenCallback());
		}
	}
	
	
	private class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Organisationseinheit result) {		
			if (result != null) {
				projektmarktplatzVerwaltung.getPartnerprofilByForeignOrganisationseinheit(result, new GetPartnerProfilCallback());
			}			
		}
	
	}
	
}
