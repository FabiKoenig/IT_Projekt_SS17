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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class DialogBoxUnternehmenErstellen extends DialogBox{
	
		private ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
		private Button unternehmen_ok = new Button("OK");
		private FlexTable unternehmen_ft = new FlexTable();
		
		private Label lbl_unternehmenName = new Label("Unternehmen: ");
		private TextBox txt_unternehmenName = new TextBox();

		private Label lbl_ustrasse = new Label("Straße: ");
		private TextBox txt_ustrasse = new TextBox();

		private Label lbl_uhausnummer = new Label("Hausnummer: ");
		private TextBox txt_uhausnummer = new TextBox();

		private Label lbl_uplz = new Label("Postleitzahl: ");
		private TextBox txt_uplz = new TextBox();

		private Label lbl_uort = new Label("Ort: ");
		private TextBox txt_uort = new TextBox();
		
		private Vector<Unternehmen> allUnternehmen = new Vector();
		
		private IdentityMarketChoice identityMarketChoice=null;
		private Navigation navigation=null;
		
		public DialogBoxUnternehmenErstellen(IdentityMarketChoice identityMarketChoice, Navigation navigation){
			this.identityMarketChoice=identityMarketChoice;
			this.navigation=navigation;
			this.setText("Unternehmen erstellen...");
			this.setAnimationEnabled(false);
			this.setGlassEnabled(true);
			unternehmen_ok.setStylePrimaryName("cell-btn");

			txt_unternehmenName.getElement().setPropertyString("placeholder", "Name des Unternehmens");
			txt_ustrasse.getElement().setPropertyString("placeholder", "Straße");
			txt_uhausnummer.getElement().setPropertyString("placeholder", "Hausnummer");
			txt_uplz.getElement().setPropertyString("placeholder", "PLZ");
			txt_uort.getElement().setPropertyString("placeholder", "Ort");

			unternehmen_ft.setWidget(1, 0, lbl_unternehmenName);
			unternehmen_ft.setWidget(1, 1, txt_unternehmenName);
			unternehmen_ft.setWidget(2, 0, lbl_ustrasse);
			unternehmen_ft.setWidget(2, 1, txt_ustrasse);
			unternehmen_ft.setWidget(3, 0, lbl_uhausnummer);
			unternehmen_ft.setWidget(3, 1, txt_uhausnummer);
			unternehmen_ft.setWidget(4, 0, lbl_uort);
			unternehmen_ft.setWidget(4, 1, txt_uort);
			unternehmen_ft.setWidget(5, 0, lbl_uplz);
			unternehmen_ft.setWidget(5, 1, txt_uplz);		
			unternehmen_ft.setWidget(9, 0, new Label(" "));
			unternehmen_ft.setWidget(10, 1, unternehmen_ok);
			
			unternehmen_ok.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					
					Unternehmen testUnternehmen = null;
					for(Unternehmen unternehmen : allUnternehmen){
						if(txt_unternehmenName.getText()==unternehmen.getName()){
							testUnternehmen=unternehmen;
						}
					}
					if(txt_unternehmenName.getText().isEmpty()){
						Window.alert("Teambezeichnung darf nicht leer sein!");
					}else if(testUnternehmen!=null){
						Window.alert("Gewähltes Team exisitert bereits! Bitte einen anderen Namen wählen!");
					}else{
						int plz;
						if(txt_uplz.getText().isEmpty()){
							plz = 0;
							procede(plz);
						}else{
							try {
								plz=Integer.parseInt(txt_uplz.getText());
								procede(plz);
							} catch (Exception e) {
								Window.alert("PLZ muss eine Zahl sein!");
							}
						}
					}	
				}

			});

			setWidget(unternehmen_ft);
			
		}
		
		void procede(final int plz) {
			projektmarktplatzverwaltung.createPartnerprofil(new AsyncCallback<Partnerprofil>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Partnerprofil für neues Unternehmen konnte nicht erstellt werden");
				}

				@Override
				public void onSuccess(Partnerprofil result) {

					projektmarktplatzverwaltung.createUnternehmen(txt_unternehmenName.getText(), txt_uhausnummer.getText(), 
							txt_uort.getText(), plz, txt_ustrasse.getText(), result.getId(), new AsyncCallback<Unternehmen>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Unternehmen konnte nicht erstellt werden!");
								}

								@Override
								public void onSuccess(Unternehmen result) {
									final Unternehmen erstelltesUnternehmen = result;
									Person user = (Person) identityMarketChoice.getSelectedIdentityAsObject();
									user.setUnternehmenId(result.getId());
									
									projektmarktplatzverwaltung.savePerson(user, new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Unternehmen wurde erstellt, konnte aber nicht zur Person hinzugefügt werden.");
										}

										@Override
										public void onSuccess(Void result) {
											Window.alert("Unternehmen " +erstelltesUnternehmen.getName() + " wurde erfolgreich erstellt!");
											PersonProfilAnzeigenForm.getDb_Unternehmen().hide();
											identityMarketChoice.reinitialize();
											navigation.reload();
											hide();
										}
									});
								}
							});
				}
			});
		}
		
	}