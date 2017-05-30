package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;

public class DialogBoxBewerbungstext extends DialogBox {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_bewerbungstext = new FlexTable();
	TextArea txta_bewerbungstext = new TextArea();
	Button btn_zurueck = new Button("Zurück");

	
	public DialogBoxBewerbungstext(String text){
		this.setText("Bewerbungstext anzeigen: ");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		txta_bewerbungstext.setReadOnly(true);
		txta_bewerbungstext.setWidth("900");
		txta_bewerbungstext.setHeight("900");
		txta_bewerbungstext.setText(text);
		ft_bewerbungstext.setWidget(0, 0, txta_bewerbungstext);
		ft_bewerbungstext.setWidget(1, 0, btn_zurueck);

		setWidget(ft_bewerbungstext);
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
	}

	
	
}
