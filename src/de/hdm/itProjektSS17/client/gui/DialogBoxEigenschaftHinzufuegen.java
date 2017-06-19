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
	
	/*
	 * Erstellen der GUI Elemente
	 */
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
	
	public DialogBoxEigenschaftHinzufuegen(final int partnerprofilId, boolean afterAnlegen, final Navigation navigation, final IdentityMarketChoice identityMarketChoice){
	this.navigation=navigation;
	this.identityMarketChoice=identityMarketChoice;
	this.partnerpId = partnerprofilId;	
		
	/*
	 * Erstellen der FlexTable
	 */
	eigenschaftHinzufuegenFlexTable.setWidget(0, 1, eigenschaftNameBox);
	eigenschaftHinzufuegenFlexTable.setWidget(0, 0, eigenschaftNameLabel);
	eigenschaftHinzufuegenFlexTable.setWidget(1, 1, eigenschaftWertBox);
	eigenschaftHinzufuegenFlexTable.setWidget(1, 0, eigenschaftWertLabel);
	
	/*
	 * Hinzufügen der Buttons zum Buttonpanel
	 */
	buttonPanel.add(speichernButton);
	buttonPanel.add(abbrechenButton);
	abbrechenButton.setStylePrimaryName("cell-btn");
	speichernButton.setStylePrimaryName("cell-btn");
	
	/*
	 * Hinzufügen der FlexTable zur DialogBox
	 */
	dialogBoxPanel.setSpacing(8);
	dialogBoxPanel.add(buttonPanel);
	dialogBoxPanel.add(eigenschaftHinzufuegenFlexTable);
	this.add(dialogBoxPanel);
	this.setGlassEnabled(true);
	this.setAnimationEnabled(false);
	this.center();
	
	
	
	if(afterAnlegen==true){
		speichernButton.setVisible(false);
		Button saveButton = new Button("Speichern");
		saveButton.setStylePrimaryName("cell-btn");
		buttonPanel.add(saveButton);
		/*
		 * Anlegen der ClickHandler des Save Buttons
		 */
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				projektmarktplatzVerwaltung.getPartnerprofilById(partnerprofilId, new AsyncCallback<Partnerprofil>() {

					public void onFailure(Throwable caught) {
						Window.alert("Die Eigenschaft konnte nicht gespeichert werden. Bitte versuchen Sie es erneut.");
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						final Partnerprofil partnerprofil = result;
						
						if(eigenschaftNameBox.getText()!= "" && eigenschaftWertBox.getText()!= ""){
							/**	
							 * Anlegen des Callbacks
							 * Bei erfolgreichem Callback wird eine Eigenschaft als result zurückgegeben
							 * @author Tom 
							 *
							 */
							projektmarktplatzVerwaltung.createEigenschaft(eigenschaftNameBox.getText(), eigenschaftWertBox.getText(), 
									result.getId(), new AsyncCallback<Eigenschaft>() {
										public void onFailure(Throwable caught) {
											Window.alert("Fehler: Das anlegen der Eigenschaft ist fehlgeschlagen.");
										}
										public void onSuccess(Eigenschaft result) {
											Window.alert("Die Eigenschaft wurde erfolgreich angelegt.");
											hide();
											RootPanel.get("Details").clear();
											RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(partnerprofilId, true, false, identityMarketChoice, navigation));
											
											partnerprofil.setAenderungdatum(new Date());
											projektmarktplatzVerwaltung.savePartnerprofil(partnerprofil, new AsyncCallback<Void>() {
												public void onFailure(Throwable caught) {
													Window.alert("Fehler: "+caught.getMessage());
												}

												@Override
												public void onSuccess(Void result) {
												}
											});
										}
									});
								}
							}
						});	
					}
				});
			}
	
	
	
	/**
	 * Anlegen der CLICK-HANDLER
	 */
	
	abbrechenButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			hide();
		}
	});
	
	speichernButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			projektmarktplatzVerwaltung.getPartnerprofilById(partnerprofilId, new SetEigenschaftenCallback());
			
		}
	});
	
	}
	
	/**	
	 * Anlegen der Callbacks
	 * Bei erfolgreichem Callback wird ein Partnerprofil als result zurückgegeben
	 * @author Tom 
	 *
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
							
							/*
							 * Nun überprüfen wir, ob das Partnerprofil einer Ausschreibung oder einer Organisationseinheit zugehörigt ist.
							 * Je nachdem wird die entsprechende Form geladen.
							 */
							projektmarktplatzVerwaltung.getAllOrganisationseinheiten(new AsyncCallback<Vector<Organisationseinheit>>() {
								public void onFailure(Throwable caught) {
								}

								public void onSuccess(Vector<Organisationseinheit> result) {
									
									for (Organisationseinheit organisationseinheit : result) {
										if(organisationseinheit.getPartnerprofilId() == partnerpId){
											RootPanel.get("Details").clear();
											RootPanel.get("Details").add(new MeinPartnerprofilForm(identityMarketChoice, navigation));
											break;		
										} else if (organisationseinheit.getPartnerprofilId() != partnerpId){
											RootPanel.get("Details").clear();
											RootPanel.get("Details").add(new PartnerprofilByAusschreibungForm(MeineAusschreibungenForm.getPartnerprofilIdOfSelectedAusschreibung(), false, false, identityMarketChoice, navigation));
											
										}
									}
									
								}
							});
					}
			});
		} else{
			Window.alert("Sie müssen einen Namen und einen Wert eintragen.");
		}	
	}
		
	}
	
	
}
