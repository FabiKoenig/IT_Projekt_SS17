package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.GetPersonalInformation;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Organisationseinheit;


public class OrganisationseinheitverwaltenForm extends Showcase {
	
	@Override
	protected String getHeadlineText() {
		return "Organisationseinheit verwalten";
	}


	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		HorizontalPanel panel_orgaverwalten = new HorizontalPanel();
		this.add(panel_orgaverwalten);
		
		
		Button btn_unternehmenerstellen = new Button("Unternehmen erstellen");
		btn_unternehmenerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				DialogBoxUnternehmenErstellen due = new DialogBoxUnternehmenErstellen();
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				due.setPopupPosition(left, top);
				due.show();
				
			}
		});
		this.add(btn_unternehmenerstellen);
	}
}