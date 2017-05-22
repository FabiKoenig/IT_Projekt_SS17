package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		
		VerticalPanel panel_orgaverwaltenV = new VerticalPanel();
		this.add(panel_orgaverwaltenV);
		
		Button btn_unternehmenerstellen = new Button("Unternehmen erstellen");
		panel_orgaverwalten.add(btn_unternehmenerstellen);
		
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
		
		
		
		Button btn_teamerstellen = new Button("Team erstellen");
		panel_orgaverwalten.add(btn_teamerstellen);
		
		btn_teamerstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				DialogBoxTeamErstellen dte = new DialogBoxTeamErstellen();
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				dte.setPopupPosition(left, top);
				dte.show();
			}
		});
		
		
		Button btn_unternehmenaustreten = new Button("Unternehmen austreten");
		panel_orgaverwaltenV.add(btn_unternehmenaustreten);
		
		btn_unternehmenaustreten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btn_teamaustreten = new Button("Team austreten");
		panel_orgaverwaltenV.add(btn_teamaustreten);
		
		btn_teamaustreten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
		
			}
		});
	}

}