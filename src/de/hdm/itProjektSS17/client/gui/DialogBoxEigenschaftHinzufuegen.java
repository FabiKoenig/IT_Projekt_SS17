package de.hdm.itProjektSS17.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.client.gui.MeinPartnerprofilForm.GetEigenschaftenCallback;
import de.hdm.itProjektSS17.client.gui.MeinPartnerprofilForm.GetPartnerProfilCallback;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Eigenschaft;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;

public class DialogBoxEigenschaftHinzufuegen extends DialogBox{
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();

	private int partnerpId = 0;
	
	//Erstellen der GUI Elemente
	FlexTable eigenschaftHinzufuegenFlexTable = new FlexTable();
	HorizontalPanel buttonPanel = new HorizontalPanel();
	VerticalPanel dialogBoxPanel = new VerticalPanel();
	Label eigenschaftNameLabel = new Label("Name:");
	Label eigenschaftWertLabel = new Label("Wert:");
	TextBox eigenschaftNameBox = new TextBox();
	TextBox eigenschaftWertBox = new TextBox();
	Button abbrechenButton = new Button("Abbrechen");
	Button speichernButton = new Button("Speichern");
	private Navigation navigation=null;
	private IdentityMarketChoice identityMarketChoice=null;
	private Boolean showFormafter = null;
	
	public DialogBoxEigenschaftHinzufuegen(final int partnerprofilId, final DialogBox dbox, boolean showFormAfter, Navigation navigation, IdentityMarketChoice identityMarketChoice){
	this.navigation=navigation;
	this.identityMarketChoice=identityMarketChoice;
	this.partnerpId = partnerprofilId;	
	this.showFormafter = showFormAfter;
		
	//Erstellen der FlexTable
	eigenschaftHinzufuegenFlexTable.setWidget(0, 1, eigenschaftNameBox);
	eigenschaftHinzufuegenFlexTable.setWidget(0, 0, eigenschaftNameLabel);
	eigenschaftHinzufuegenFlexTable.setWidget(1, 1, eigenschaftWertBox);
	eigenschaftHinzufuegenFlexTable.setWidget(1, 0, eigenschaftWertLabel);
	
	//Hinzufügen der Buttons zum Buttonpanel
	buttonPanel.add(speichernButton);
	buttonPanel.add(abbrechenButton);
	abbrechenButton.setStylePrimaryName("cell-btn");
	speichernButton.setStylePrimaryName("cell-btn");
	
	//Hinzufügen der FlexTable zur DialogBox
	dialogBoxPanel.setSpacing(8);
	dialogBoxPanel.add(buttonPanel);
	dialogBoxPanel.add(eigenschaftHinzufuegenFlexTable);
	this.add(dialogBoxPanel);
	this.setGlassEnabled(true);
	this.setAnimationEnabled(false);
	this.center();
	
	/**
	 * CLICK-HANDLER
	 */
	
	abbrechenButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			hide();
		}
	});
	
	speichernButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			if(showFormafter == true){
			projektmarktplatzVerwaltung.getPartnerprofilById(partnerprofilId, new SetEigenschaftenCallback());
			} else if(showFormafter == false){
				projektmarktplatzVerwaltung.getPartnerprofilById(partnerprofilId, new SetEigenschaftenCallback());
				dbox.hide();
				dbox.center();
				dbox.show();
			}
			
		}
	});
	
	}
	
	/**
	 * ASYNC-CALLBACKS
	 */
	
	public class SetEigenschaftenCallback implements AsyncCallback<Partnerprofil>{
		
	public void onFailure(Throwable caught) {
			Window.alert("Fehler: Die Eigenschaft konnte nicht hinzugefügt werden.");
	}
	public void onSuccess(Partnerprofil result) {
			
			if(eigenschaftNameBox.getText()!= "" && eigenschaftWertBox.getText()!= ""){
		
			projektmarktplatzVerwaltung.createEigenschaft(eigenschaftNameBox.getText(), eigenschaftWertBox.getText(), 
					result.getId(), new AsyncCallback<Eigenschaft>() {
					
				public void onFailure(Throwable caught) {
							Window.alert("Fehler: Das anlegen der Eigenschaft ist fehlgeschlagen.");
						}
						public void onSuccess(Eigenschaft result) {
							
							Window.alert("Die Eigenschaft wurde erfolgreich angelegt.");
							
							hide();
							
							
							if(showFormafter == true){
							// Nun überprüfen wir, ob das Partnerprofil einer Ausschreibung oder einer Organisationseinheit zugehörigt ist.
							// Je nachdem wird die entsprechende Form geladen.
							projektmarktplatzVerwaltung.getAllOrganisationseinheiten(new AsyncCallback<Vector<Organisationseinheit>>() {
								public void onFailure(Throwable caught) {
								}

								public void onSuccess(Vector<Organisationseinheit> result) {
									
									for (Organisationseinheit organisationseinheit : result) {
										
										if(organisationseinheit.getPartnerprofilId() == partnerpId){
											navigation.reload();
											break;		
										} else if (organisationseinheit.getPartnerprofilId() != partnerpId){
											RootPanel.get("Details").clear();
											RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung(), null, true, false, identityMarketChoice, navigation));
											
										}
									}
									
								}
							});
						}
//							RootPanel.get("Details").clear();
//							RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung()));
//							
//							Navigation.getCurrentClickHandler().onClick(Navigation.getCurrentClickEvent());
						}
			});
			result.setAenderungdatum(new Date());
		} else{
			Window.alert("Sie müssen einen Namen und einen Wert eintragen.");
		}	
	}
		
	}
	
	
//	private class OrganisationseinheitCallback implements AsyncCallback<Organisationseinheit> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Organisationseinheit result) {		
//			if (result != null) {
//				projektmarktplatzVerwaltung.getPartnerprofilByForeignOrganisationseinheit(result, new SetEigenschaftenCallback());
//			}			
//		}
//	
//	}
}
