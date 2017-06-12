package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;

public class DialogBoxBewerbungstext extends DialogBox {

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	FlexTable ft_bewerbungstext = new FlexTable();
	TextArea txta_bewerbungstext = new TextArea();
	Button btn_zurueck = new Button("Zurück");
	
	public DialogBoxBewerbungstext(String text){
		btn_zurueck.setStylePrimaryName("cell-btn");
		this.setText("Ihr Bewerbungstext");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
			
		txta_bewerbungstext.setReadOnly(true);
		txta_bewerbungstext.setCharacterWidth(70);
		txta_bewerbungstext.setVisibleLines(25);		
		txta_bewerbungstext.setText(text);
		ft_bewerbungstext.setWidget(0, 0, txta_bewerbungstext);
		ft_bewerbungstext.setWidget(1, 0, btn_zurueck);

		
		vp.add(ft_bewerbungstext);
		vp.add(btn_zurueck);
		setWidget(vp);
		
		btn_zurueck.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
	}

	
	
}
