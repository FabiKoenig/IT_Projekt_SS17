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
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	final int ausschreibungsId;
	FlexTable ft_Bewerben = new FlexTable();
	Button btn_ok = new Button("Bewerbung einreichen");
	Button btn_abbrechen = new Button("Abbrechen");
	Label lbl_bewerbungstext = new Label("Bewerbungstext: ");
	TextArea txta_bewerbungstext = new TextArea();
	
	
	HorizontalPanel hp = new HorizontalPanel();
	public DialogBoxBewerben(final int ausschreibungsId)
	{
		this.ausschreibungsId=ausschreibungsId;
		this.setText("Bewerbung anlegen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		hp.add(btn_ok);
		hp.add(btn_abbrechen);
	
		
		btn_ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				if (txta_bewerbungstext.getText().isEmpty()) {
					Window.alert("Bitte geben Sie einen Bewerbungstextstext für die Stellenausschreibung an!");
				}
				projektmarktplatzVerwaltung.createBewerbung(txta_bewerbungstext.getText(), IdentityMarketChoice.getSelectedIdentityId(), ausschreibungsId, new BewerbungAnlegenCallback());
			}
			
		});
		
		btn_abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		txta_bewerbungstext.setPixelSize(200, 200);
		ft_Bewerben.setWidget(1, 0, lbl_bewerbungstext);
		ft_Bewerben.setWidget(1, 1, txta_bewerbungstext);
		ft_Bewerben.setWidget(6, 1, hp);
		setWidget(ft_Bewerben);
	}
	


	class BewerbungAnlegenCallback implements AsyncCallback<Bewerbung> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Bewerbung result) {
			
			Window.alert("Das Einreichen der Bewerbung war erfolgreich.");
			hide();
			Navigation.reload();

		}

	
	}
}


