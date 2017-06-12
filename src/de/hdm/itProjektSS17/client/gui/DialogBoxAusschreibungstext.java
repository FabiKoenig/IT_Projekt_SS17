package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;

public class DialogBoxAusschreibungstext extends DialogBox{

	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	VerticalPanel vp = new VerticalPanel();
	FlexTable ft_ausschreibungstext = new FlexTable();
	TextArea txta_ausschreibungsstext = new TextArea();
	Button btn_zurueck = new Button("Zurück");
	
	public DialogBoxAusschreibungstext(String text){
		btn_zurueck.setStylePrimaryName("navi-button");
		this.setText("Ausschreibungstext anzeigen: ");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
		
		txta_ausschreibungsstext.setReadOnly(true);
		txta_ausschreibungsstext.setCharacterWidth(70);
		txta_ausschreibungsstext.setVisibleLines(25);		
		txta_ausschreibungsstext.setText(text);
		ft_ausschreibungstext.setWidget(0, 0, txta_ausschreibungsstext);
		ft_ausschreibungstext.setWidget(1, 0, btn_zurueck);

		btn_zurueck.setStylePrimaryName("cell-btn");
		vp.add(ft_ausschreibungstext);
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

	
	


