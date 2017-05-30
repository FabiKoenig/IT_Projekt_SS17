package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.bo.Person;

public class PersonProfilAnzeigenForm extends Showcase{

	
	private VerticalPanel vpanel = new VerticalPanel();
	private FlexTable ftable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	//Erstellen der Buttons
	Button bearbeitenButton = new Button("Bearbeiten");
	Button speichernButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	
	
	//Erstellen der Text- bzw. ListBoxen
	private ListBox anredeListBox = new ListBox();
	private TextBox anredeBox = new TextBox();
	private TextBox vnameBox = new TextBox();
	private TextBox nnameBox = new TextBox();
	private TextBox strasseBox = new TextBox();
	private TextBox hausnrBox = new TextBox();
	private TextBox plzBox = new TextBox();
	private TextBox ortBox = new TextBox();
	
	
	//Erstellen der Label
	private Label anredeLabel = new Label("Anrede");
	private Label vnameLabel = new Label("Vorname");
	private Label nnameLabel = new Label("Nachname");
	private Label strasseLabel = new Label("Straße");
	private Label hausnrLabel = new Label("Hausnummer");
	private Label plzLabel = new Label("Postleitzahl");
	private Label ortLabel = new Label("Ort");
	
		
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Personendaten";
	}

	@Override
	protected void run() {
		
		try {
			
			ClientsideSettings.getProjektmarktplatzVerwaltung()
			.getPersonById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilAnzeigenCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Setzen der Boxen auf ReadOnly
		anredeBox.setReadOnly(true);
		vnameBox.setReadOnly(true);
		nnameBox.setReadOnly(true);
		strasseBox.setReadOnly(true);
		hausnrBox.setReadOnly(true);
		plzBox.setReadOnly(true);
		ortBox.setReadOnly(true);
		
		//Stylen der Buttons
		bearbeitenButton.setStylePrimaryName("navi-button");
		speichernButton.setStylePrimaryName("navi-button");
		abbrechenButton.setStylePrimaryName("navi-button");
		
		//Setzen des SpeicherButtons
		speichernButton.setVisible(false);
		abbrechenButton.setVisible(false);
		
		//Hinzufügen der Inhalte der anredeListBox
		anredeListBox.addItem("Herr");
		anredeListBox.addItem("Frau");
			
		// Befüllen der FlexTable
		ftable.setWidget(0, 1, anredeBox);
		ftable.setWidget(0, 0, anredeLabel);

		ftable.setWidget(1, 1, vnameBox);
		ftable.setWidget(1, 0, vnameLabel);

		ftable.setWidget(2, 1, nnameBox);
		ftable.setWidget(2, 0, nnameLabel);

		ftable.setWidget(3, 1, strasseBox);
		ftable.setWidget(3, 0, strasseLabel);

		ftable.setWidget(4, 1, hausnrBox);
		ftable.setWidget(4, 0, hausnrLabel);
		
		ftable.setWidget(5, 1, plzBox);
		ftable.setWidget(5, 0, plzLabel);

		ftable.setWidget(6, 1, ortBox);
		ftable.setWidget(6, 0, ortLabel);


		/**
		 * Anfügen der FlexTable und des Buttons  an das Panel
		 */
		vpanel.setSpacing(8);
		buttonPanel.add(bearbeitenButton);
		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		vpanel.add(buttonPanel);
		vpanel.add(ftable);
		
		this.add(vpanel);
		
		
		//ClickHandler, der bei einem Klick auf den bearbeiten Button den ProfilBearbeitenCallback ausführt.
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				DialogBoxPersonProfilBearbeiten dbppb = new DialogBoxPersonProfilBearbeiten();
//				int left = Window.getClientWidth() / 3;
//				int top = Window.getClientHeight() / 8;
//				dbppb.setPopupPosition(left, top);
//				dbppb.show();
				
				
				//Setzen auf NotReadOnly, um die Boxen wieder bearbeiten zu können.
				anredeBox.setReadOnly(false);
				vnameBox.setReadOnly(false);
				nnameBox.setReadOnly(false);
				strasseBox.setReadOnly(false);
				hausnrBox.setReadOnly(false);
				plzBox.setReadOnly(false);
				ortBox.setReadOnly(false);
				
				//Setzen des SpeicherButtons auf Visible
				speichernButton.setVisible(true);
				abbrechenButton.setVisible(true);
				//Setzen des BearbeitenButtons auf NotVisible
				bearbeitenButton.setVisible(false);
				//Setzen der ListBox anstelle der TextBox
				ftable.setWidget(0, 1, anredeListBox);
			}
		});
		
		
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(new PersonProfilAnzeigenForm());
			}
		});
		
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getProjektmarktplatzVerwaltung().
				getPersonById(IdentityMarketChoice.getSelectedIdentityId(), new ProfilBearbeitenCallback());
			}
		});
	}
	
	private class ProfilAnzeigenCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Person result) {
			vnameBox.setText(result.getVorname());
			nnameBox.setText(result.getNachname());
			anredeBox.setText(result.getAnrede());
			strasseBox.setText(result.getStrasse());
			hausnrBox.setText(result.getHausnummer());
			plzBox.setText(Integer.toString(result.getPlz()));
			ortBox.setText(result.getOrt());
			
			
		}
	}
	private class ProfilBearbeitenCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
		}

		@Override
		public void onSuccess(Person result) {
			
			result.setAnrede(anredeListBox.getItemText(anredeListBox.getSelectedIndex()));
			result.setHausnummer(hausnrBox.getText());
			result.setNachname(nnameBox.getText());
			result.setOrt(ortBox.getText());
			result.setPlz(Integer.parseInt(plzBox.getText()));
			result.setStrasse(strasseBox.getText());
			result.setVorname(vnameBox.getText());
			
			ClientsideSettings.getProjektmarktplatzVerwaltung().savePerson(result, new PersonSpeichernCallback());
		
			Showcase showcase = new PersonProfilAnzeigenForm();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showcase);
			
		}
		
	}
	
	
	private class PersonSpeichernCallback implements AsyncCallback<Void>{
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Profil konnte nicht gespeichert werden.");
		}
		@Override
		public void onSuccess(Void result) {
			Window.alert("Das Profil wurde erfolgreich geändert.");
			
		}
		
	}
	
	
	
}
