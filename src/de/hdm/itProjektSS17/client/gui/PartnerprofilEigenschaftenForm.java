package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.appengine.tools.admin.VerificationCodeReceiverRedirectUriException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;

public class PartnerprofilEigenschaftenForm extends VerticalPanel{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	// GUI Elemente
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addEigenschaftenButton = new Button("Eigenschaften hinzufügen");// Clickhandler hinzufuegen
	private Button speicherButton = new Button("Speichern"); //Speichern CklickHandler hinzufuegen
	private Button loeschenButton = new Button("Löschen"); //Löschen Clickhandler hinzufuegen
	private FlexTable eigenschaftenTable = new FlexTable();
	
	//Style der GUI Elemente
	

	
	
	public PartnerprofilEigenschaftenForm(){
		
		projektmarktplatzVerwaltung.getPersonById(3, new PersonCallback());
		this.setSpacing(8);
		this.add(eigenschaftenTable);
		buttonPanel.add(addEigenschaftenButton);
		this.add(buttonPanel);
	}
	
	public class GetPartnerProfilCallback implements AsyncCallback<Partnerprofil>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Das Partnerprofil konnte nicht ausgelesen werden");
		}

		@Override
		public void onSuccess(Partnerprofil result) {
			ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
			projektmarktplatzVerwaltung.getEigenschaftByForeignPartnerprofil(result, new GetEigenschaftenCallback());
		}
	}
	
	public class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Eigenschaften konnten nicht ausgelesen werden" );
		}

		@Override
		public void onSuccess(Vector<Eigenschaft> result) {

			
			
			if (result != null) {
				for (Eigenschaft eigenschaft: result){
					int row = eigenschaftenTable.getRowCount();
					TextBox textbox = new TextBox();
					textbox.setText(eigenschaft.getWert());
					textbox.setReadOnly(true);
					
					eigenschaftenTable.setWidget(row + 1, 1, textbox);
					eigenschaftenTable.setWidget(row + 1, 0, new Label(eigenschaft.getName()));
					//eigenschaftenTable.setWidget(row + 1, 1, new Label(eigenschaft.getWert()));
				}	
			}
			
		}
	}
	
	private class PersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
			
		}

		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
				projektmarktplatzVerwaltung.getPartnerprofilByForeignOrganisationseinheit(result, new GetPartnerProfilCallback());
			}			
		}
	}
	

	
}
