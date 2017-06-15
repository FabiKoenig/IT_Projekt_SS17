package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.server.ProjektmarktplatzVerwaltungImpl;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltung;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Bewerbung;

public class DialogBoxBewerben extends DialogBox {
	
	/**
	 * GUI-Elemente & globale Variablen/ Objekte anlegen
	 * Instanz der ProjektmarktplatzVerwaltungsAsync abrufen
	 */
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_Bewerben = new FlexTable();
		
	Button btn_ok = new Button("Bewerbung einreichen");
	Button btn_abbrechen = new Button("Abbrechen");
	
	Label lbl_bewerbungstext = new Label("Bewerbungstext: ");
	TextArea txta_bewerbungstext = new TextArea();
	
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	final int ausschreibungsId;
	
	HorizontalPanel hp = new HorizontalPanel();
	
	/**
	 * Anlegen des Konstruktors
	 * @param ausschreibungsId
	 * @param identityMarketChoice
	 * @param navigation
	 */
	
	public DialogBoxBewerben(final int ausschreibungsId, final IdentityMarketChoice identityMarketChoice, Navigation navigation)
	{
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		this.ausschreibungsId=ausschreibungsId;
		this.setText("Bewerbung anlegen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		
		/**
		 * Hinzufügen der Buttons zum Horizontal Panel
		 */
		hp.add(btn_ok);
		hp.add(btn_abbrechen);
	
		/**
		 * Anlegen der Clickhandler des Ok-Button
		 */
		btn_ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				if (txta_bewerbungstext.getText().isEmpty()) {
					Window.alert("Bitte geben Sie einen Bewerbungstextstext für die Stellenausschreibung an!");
				}
				projektmarktplatzVerwaltung.createBewerbung(txta_bewerbungstext.getText(), identityMarketChoice.getSelectedIdentityId(), ausschreibungsId, new BewerbungAnlegenCallback());
			}
			
		});
		
		btn_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		/**
		 * Stylen der Buttons, Textareas sowie Hinzufügen zur FelxTable
		 */
		btn_ok.setStylePrimaryName("cell-btn");
		btn_abbrechen.setStylePrimaryName("cell-btn");
		txta_bewerbungstext.setPixelSize(200, 200);
		ft_Bewerben.setWidget(1, 0, lbl_bewerbungstext);
		ft_Bewerben.setWidget(1, 1, txta_bewerbungstext);
		ft_Bewerben.setWidget(6, 1, hp);
		setWidget(ft_Bewerben);
	}
	/**
	 * Anlegen der Callbacks
	 * Bei erfolgreichem Callback wird eine Bewerbung als result zurückgegeben.
	 * 
	 * @author Tom
	 *
	 */
	class BewerbungAnlegenCallback implements AsyncCallback<Bewerbung> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Bewerbung result) {
			
			Window.alert("Das Einreichen der Bewerbung war erfolgreich.");
			hide();
			navigation.reload();

		}

	
	}
}


