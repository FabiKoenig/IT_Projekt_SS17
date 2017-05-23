package de.hdm.itProjektSS17.client.gui;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.bo.Person;

public class DialogBoxPersonProfilBearbeiten extends DialogBox{

	//Erstellen der Text- bzw. ListBoxen
	ListBox anredeBox = new ListBox();
	TextBox vnameBox = new TextBox();
	TextBox nnameBox = new TextBox();
	TextBox strasseBox = new TextBox();
	TextBox hausnrBox = new TextBox();
	TextBox plzBox = new TextBox();
	TextBox ortBox = new TextBox();
	
	
	//Erstellen der Label
	Label anredeLabel = new Label("Anrede");
	Label vnameLabel = new Label("Vorname");
	Label nnameLabel = new Label("Nachname");
	Label strasseLabel = new Label("Straße");
	Label hausnrLabel = new Label("Hausnummer");
	Label plzLabel = new Label("Postleitzahl");
	Label ortLabel = new Label("Ort");
	
	public DialogBoxPersonProfilBearbeiten(){
		
		
		//Anlegen des Panels und Tables
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		FlexTable ftable = new FlexTable();
		
		//Anlegen der Buttons
		Button abbrechenButton = new Button("Abbrechen");
		Button speichernButton = new Button("Speichern");
		
		//Hinzufügen der Inhalte der ListBox
		anredeBox.addItem("Herr");
		anredeBox.addItem("Frau");
		
		//Hinzufügen der Labels und Boxen zur FlexTable
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
		
		//ClickHandler für den Abbrechen Button, der die DialogBox schliesst
		abbrechenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();	
			}
		});
		
		speichernButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
					ClientsideSettings.getProjektmarktplatzVerwaltung().
					getPersonById(3, new ProfilBearbeitenCallback());
					hide();
			
			}
		});
		
		
		vPanel.add(ftable);
		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		vPanel.add(buttonPanel);
		this.add(vPanel);
	}
	
	
	
	private class ProfilBearbeitenCallback implements AsyncCallback<Person>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Profils ist fehlgeschlagen. Bitte versuche es erneut.");
		}

		@Override
		public void onSuccess(Person result) {
			
			result.setAnrede(anredeBox.getItemText(anredeBox.getSelectedIndex()));
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
