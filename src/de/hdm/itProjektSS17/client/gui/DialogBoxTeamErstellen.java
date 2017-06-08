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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Partnerprofil;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Team;
import de.hdm.itProjektSS17.shared.bo.Unternehmen;

public class DialogBoxTeamErstellen extends DialogBox {

	private ProjektmarktplatzVerwaltungAsync projektmarktplatzverwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	private FlexTable ft = new FlexTable();
	private HorizontalPanel hp = new HorizontalPanel();
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");		

	private Label lbl_teamName = new Label("Team: ");
	private final TextBox txt_teamname = new TextBox();

	private Label lbl_strasse = new Label("Straße: ");
	private final TextBox txt_strasse = new TextBox();
	
	private Label lbl_hausnummer = new Label("Hausnummer: ");
	private final TextBox txt_hausnummer = new TextBox();
	
	private Label lbl_plz = new Label("Postleitzahl: ");
	private final TextBox txt_plz = new TextBox();
	
	private Label lbl_ort = new Label("Ort: ");
	private final TextBox txt_ort = new TextBox();
	
	private Label lbl_unternehmen = new Label("Unternehmen für Team erstellen: ");
	private Button unternehmenErstellenButton = new Button("Erstellen");
	
	private Label lbl_unternehmen2 = new Label("Oder Unternehmen wählen: ");
	private MultiWordSuggestOracle oracle_unternehmenHinzufuegen= new MultiWordSuggestOracle();
	private SuggestBox sb_unternehmenHinzufuegen = new SuggestBox(oracle_unternehmenHinzufuegen);
	private Button unternehmenHinzufuegenButton = new Button("Wählen");
	
	private Label lbl_unternehmenIsSet = new Label("Unternehmen gespeichert");
	private Button bt_unternehmenIsSet = new Button("Ändern");
	
	//Einige Variablen für das flexible Auswählen/Anlegen eines Unternehmens im Team-Anlegen-Dialog
	private final localDialogBoxUnternehmenErstellenFuerTeam db = new localDialogBoxUnternehmenErstellenFuerTeam();
	private Unternehmen chosenUnternehmen = null;
	private Unternehmen createdUnternehmen = null;
	private int wasUnternehmenCreated = 0;
	
	private Vector<Team> allTeams = new Vector();
	private Vector<Unternehmen> allUnternehmen = new Vector();
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	
	
	public DialogBoxTeamErstellen(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		projektmarktplatzverwaltung.getAllTeams(new getAllTeams());
		projektmarktplatzverwaltung.getAllUnternehmen(new getAllUnternehmen());

		this.setText("Team erstellen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		txt_teamname.getElement().setPropertyString("placeholder", "Teamname");

		txt_strasse.getElement().setPropertyString("placeholder", "Straße");
	
		txt_hausnummer.getElement().setPropertyString("placeholder", "Hausnummer");
	
		txt_plz.getElement().setPropertyString("placeholder", "PLZ");
		
		txt_ort.getElement().setPropertyString("placeholder", "Ort");
		
		hp.add(ok);
		hp.add(abbrechen);
		
		ft.setWidget(1, 0, lbl_teamName);
		ft.setWidget(1, 1, txt_teamname);
		ft.setWidget(2, 0, lbl_strasse);
		ft.setWidget(2, 1, txt_strasse);
		ft.setWidget(3, 0, lbl_hausnummer);
		ft.setWidget(3, 1, txt_hausnummer);
		ft.setWidget(4, 0, lbl_ort);
		ft.setWidget(4, 1, txt_ort);
		ft.setWidget(5, 0, lbl_plz);
		ft.setWidget(5, 1, txt_plz);		
		ft.setWidget(8, 1, hp);
		unternehmenIsNotSet();
		setWidget(ft);
		

		
		unternehmenErstellenButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {		
				db.center();
				db.show();
			}
		});
		
		projektmarktplatzverwaltung.getAllUnternehmen(new getUnternehmenCallback());
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Team testTeam = null;
				for(Team team : allTeams){
					if(txt_teamname.getText()==team.getName()){
						testTeam=team;
					}
				}
				
				if(txt_teamname.getText().isEmpty()){
					Window.alert("Teambezeichnung darf nicht leer sein!");
				}else if(testTeam!=null){
					Window.alert("Gewähltes Team exisitert bereits! Bitte einen anderen Namen wählen!");
				}else{
					if(txt_plz.getText().isEmpty()!=true){
						try {
							Integer.parseInt(txt_plz.getText());
							procede();
						} catch (Exception e) {
							Window.alert("PLZ muss eine Zahl sein!");
						}
					}else{
						procede();
					}
				}
				
			}
			
			public void procede(){
				if(wasUnternehmenCreated==0){
					Unternehmen tempUnternehmen = new Unternehmen();
					tempUnternehmen.setId(0);
					projektmarktplatzverwaltung.createPartnerprofil(new createTeamCallback(tempUnternehmen));
				
				}else if(wasUnternehmenCreated==1){
						projektmarktplatzverwaltung.createPartnerprofil(new AsyncCallback<Partnerprofil>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Partnerprofil für das neue Unternehmen konnte nicht erstellt werden");
							}
							
							@Override
							public void onSuccess(Partnerprofil result) {
								Partnerprofil partnerprofil_Unternehmen = result;
								projektmarktplatzverwaltung.createUnternehmen(createdUnternehmen.getName(), createdUnternehmen.getHausnummer(), createdUnternehmen.getOrt(), createdUnternehmen.getPlz(), createdUnternehmen.getStrasse(), partnerprofil_Unternehmen.getId(), new AsyncCallback<Unternehmen>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Neues Unternehmen konnte nicht erstellt werden");
									}

									@Override
									public void onSuccess(Unternehmen result) {
										createdUnternehmen.setId(result.getId());
										projektmarktplatzverwaltung.createPartnerprofil(new createTeamCallback(createdUnternehmen));
									}
								});
									
							}
						});
						
				}else if(wasUnternehmenCreated==2){
					projektmarktplatzverwaltung.createPartnerprofil(new createTeamCallback(chosenUnternehmen));		
				}	
			}
						
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		bt_unternehmenIsSet.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				unternehmenIsNotSet();
			}
		});
		
	}

	private class getUnternehmenCallback implements AsyncCallback<Vector <Unternehmen>>{
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(final Vector<Unternehmen> result) {

			for (Unternehmen unternehmen : result) {
				oracle_unternehmenHinzufuegen.add(unternehmen.getName());
			}
			
			unternehmenHinzufuegenButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					for (Unternehmen unternehmen : result) {
						if(unternehmen.getName()==sb_unternehmenHinzufuegen.getText()){
							chosenUnternehmen=unternehmen;
						}
					}
					if(chosenUnternehmen==null){
						Window.alert("Gewähltes Unternehmen exisitert nicht! Bitte validen Eintrag angeben.");
					}else{
						wasUnternehmenCreated=2;
						unternehmenIsSet();
					}

				}
			});
		}
		
	}
	
	public void unternehmenIsSet(){
		ft.setWidget(6, 0, lbl_unternehmenIsSet);
		ft.setWidget(6, 1, bt_unternehmenIsSet);
		ft.setWidget(7, 0, new Label(""));
		ft.setWidget(7, 1, new Label(""));
		ft.setWidget(7, 2, new Label(""));
	}
	
	public void unternehmenIsNotSet(){
		ft.setWidget(6, 0, lbl_unternehmen);
		ft.setWidget(6, 1, unternehmenErstellenButton);
		ft.setWidget(7, 0, lbl_unternehmen2);
		ft.setWidget(7, 1, sb_unternehmenHinzufuegen);
		ft.setWidget(7, 2, unternehmenHinzufuegenButton);
	}
	
	private class createTeamCallback implements AsyncCallback<Partnerprofil>{

		private Unternehmen unternehmen;
		
		public createTeamCallback(Unternehmen unternehmen){
			this.unternehmen=unternehmen;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Team konnte nicht erstellt werden!");
		}

		public void onSuccess(Partnerprofil result) {
			Partnerprofil partnerprofil_team = result;
			int i=0;
			if(txt_plz.getText().isEmpty()!=true){
				i=Integer.parseInt(txt_plz.getText());
			}
			projektmarktplatzverwaltung.createTeam(txt_teamname.getText(), txt_strasse.getText(), txt_hausnummer.getText(), i, txt_ort.getText(), partnerprofil_team.getId(), new Integer(unternehmen.getId()), new AsyncCallback<Team>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Team konnte nicht erstellt werden!");
				}

				@Override
				public void onSuccess(Team result) {
					final Team erstelltes_team = result;
					final Person user = (Person) identityMarketChoice.getSelectedIdentityAsObject();
					user.setTeamId(result.getId());
					projektmarktplatzverwaltung.savePerson(user, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Team wurde erstellt, konnte aber nicht zur Person hinzugefügt werden.");
						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Team " +erstelltes_team.getName() + " wurde erfolgreich erstellt!");
							PersonProfilAnzeigenForm.getDb_Team().hide();
							identityMarketChoice.reinitialize();
							navigation.reload();
							hide();
							
							if(erstelltes_team.getUnternehmenId()!=null){
							
								DialogBoxUnternehmenHinzufuegen db_unternehmenHinzufuegen = new DialogBoxUnternehmenHinzufuegen(user, erstelltes_team, identityMarketChoice, navigation);
								db_unternehmenHinzufuegen.show();
								
							}
						}
					});

				}
			});
		}

		
	}
	
	private class localDialogBoxUnternehmenErstellenFuerTeam extends DialogBox{
		
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
		
		public localDialogBoxUnternehmenErstellenFuerTeam(){
			
			this.setText("Unternehmen erstellen...");
			this.setAnimationEnabled(false);
			this.setGlassEnabled(true);

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
			
			unternehmen_ok.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					Unternehmen testUnternehmen = null;
					for(Unternehmen unternehmen : allUnternehmen){
						if(txt_unternehmenName.getText()==unternehmen.getName()){
							testUnternehmen=unternehmen;
						}
					}
					if(txt_unternehmenName.getText().isEmpty()){
						Window.alert("Unternehmensbezeichnung darf nicht leer sein!");
					}
					if(testUnternehmen!=null){				
						Window.alert("Gewähltes Unternehmen exisitert bereits! Bitte einen anderen Namen wählen!");
					}else{
						wasUnternehmenCreated=1;
						createdUnternehmen=new Unternehmen();
						createdUnternehmen.setName(txt_unternehmenName.getText());
						createdUnternehmen.setHausnummer(txt_uhausnummer.getText());
						createdUnternehmen.setOrt(txt_uort.getText());
						createdUnternehmen.setStrasse(txt_ustrasse.getText());
						if(txt_uplz.getText().isEmpty()){
							createdUnternehmen.setPlz(0);
							hide();
							unternehmenIsSet();
						}else{
							try {
								createdUnternehmen.setPlz(Integer.parseInt(txt_uplz.getText()));
								hide();
								unternehmenIsSet();
							} catch (Exception e) {
								Window.alert("PLZ muss eine Zahl sein!");
							}
						}
					}
				
				}
			});

			setWidget(unternehmen_ft);
			
		}
	}
	
	private class getAllTeams implements AsyncCallback<Vector<Team>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es konnten nicht alle bisher erstellen Teams geladen werden!");
		}

		@Override
		public void onSuccess(Vector<Team> result) {
			allTeams=result;
		}
		
	}
	
	private class getAllUnternehmen implements AsyncCallback<Vector<Unternehmen>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es konnten nicht alle bisher erstelten Unternehmen geladen werden!");
		}

		@Override
		public void onSuccess(Vector<Unternehmen> result) {
			allUnternehmen=result;
		}
		
	}
	

}
