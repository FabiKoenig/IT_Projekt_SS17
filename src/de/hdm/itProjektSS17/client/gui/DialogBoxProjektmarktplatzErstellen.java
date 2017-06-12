package de.hdm.itProjektSS17.client.gui;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;

public class DialogBoxProjektmarktplatzErstellen extends DialogBox{

	private ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	private FlexTable ft_pmName = new FlexTable();
	private Label lbl_pmName = new Label("Name des Marktplatzes: ");
	private TextBox txt_pmName = new TextBox();
	private Projektmarktplatz pm = null;
	private IdentityMarketChoice identityMarketChoice=null;
	private Navigation navigation=null;
	private Vector<Projektmarktplatz> projektmarktplaetze = new Vector<Projektmarktplatz>();
	
	public DialogBoxProjektmarktplatzErstellen(IdentityMarketChoice identityMarketChoice, Navigation navigation) {
		projektmarktplatzVerwaltung.getAllProjektmarktplatz(new getAllProjektmarktplaetze());
		this.identityMarketChoice=identityMarketChoice;
		this.navigation=navigation;
		
		this.setText("Projektmarktplatz erstellen...");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		Button ok = new Button("OK");
		Button abbrechen = new Button("Abbrechen");
		ok.setStylePrimaryName("cell-btn");
		abbrechen.setStylePrimaryName("cell-btn");
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				boolean choosable = true;
				for(Projektmarktplatz projektmarktplatz : projektmarktplaetze){
					if(projektmarktplatz.getBezeichnung().equals(txt_pmName.getText())){
						choosable=false;
					}
				}
				if(choosable==true){
					projektmarktplatzVerwaltung.createProjektmarktplatz(txt_pmName.getText(), new ProjektmarktplatzErstellenCallback());
				}else if(choosable==false){
					Window.alert("Projektmarktplatz existiert bereits! Bitte einen anderen Namen wählen!");
				}
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//TODO Auto-generated method stub
				hide();
			}
		});
		
		txt_pmName.getElement().setPropertyString("placeholder", "Marktplatzname");
		ft_pmName.setWidget(0, 0, lbl_pmName);
		ft_pmName.setWidget(0, 1, txt_pmName);
		ft_pmName.setWidget(1, 0, ok);
		ft_pmName.setWidget(1, 1, abbrechen);
		setWidget(ft_pmName);
		
	}
	
	private class ProjektmarktplatzErstellenCallback implements AsyncCallback<Projektmarktplatz>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Projektmarktplatz konnte nicht angelegt werden");
		}

		@Override
		public void onSuccess(Projektmarktplatz result) {
			projektmarktplatzVerwaltung.createTeilnahme(identityMarketChoice.getSelectedIdentityId(), result.getId(), new pmZuPersonHinzufuegenCallback());
		}
		
	}
	
	private class pmZuPersonHinzufuegenCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Neuer Projektmarktplatz konnte nicht zu Person hinzugefügt werden!");
		}

		@Override
		public void onSuccess(Void result) {
			identityMarketChoice.reinitialize();
			navigation.reload();
			Window.alert("Neuer Projektmarktplatz wurde hinzugefügt!");
			hide();
		}		
	}
	
	private class getAllProjektmarktplaetze implements AsyncCallback<Vector<Projektmarktplatz>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Bereits vorhandene Projektmarktplaetze konnte nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			projektmarktplaetze=result;
		}
		
	}
		
}
