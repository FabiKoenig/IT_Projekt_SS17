package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.*;
import de.hdm.itProjektSS17.shared.FieldVerifier;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.ReportGeneratorAsync;
import de.hdm.itProjektSS17.shared.bo.Person;
import de.hdm.itProjektSS17.shared.bo.Projekt;
import de.hdm.itProjektSS17.shared.bo.Projektmarktplatz;
import de.hdm.itProjektSS17.shared.bo.Ausschreibung;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;

public class StellenauschreibungForm extends Showcase {

	ListBox lib = new ListBox();
	FlexTable ft_Ausschreibungen = new FlexTable();
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();

	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Stellenausschreibungen";
	}

	@Override
	protected void run() {

		HorizontalPanel panel_stellenausschreibung = new HorizontalPanel();
		this.add(panel_stellenausschreibung);
		
		this.add(lib);
		projektmarktplatzVerwaltung.getPersonById(3, new PersonCallback());
		
		FlexCellFormatter cellFormatter = ft_Ausschreibungen.getFlexCellFormatter();
		ft_Ausschreibungen.setWidth("32em");
		ft_Ausschreibungen.setCellSpacing(5);
		ft_Ausschreibungen.setCellPadding(3);
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		ft_Ausschreibungen.setText(0, 0, "Bezeichnung");
		ft_Ausschreibungen.setText(0, 1, "Bewerbungsfrist");
		ft_Ausschreibungen.setText(0, 2, "Ausschreibungstest");
		ft_Ausschreibungen.setText(0, 3, "Status");
		ft_Ausschreibungen.setText(0, 4, "Projektmarktplatz");
		
		this.add(ft_Ausschreibungen); 
		
	}
	
	
	
	
	
	
	private class PersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Person ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
				projektmarktplatzVerwaltung.getProjektmarktplaetzeByForeignPerson(result, new TeilnahmeProjektmarktplatzAnzeigenCallback());		
		}
		
	}
	
		
		
		
		
	
	private class TeilnahmeProjektmarktplatzAnzeigenCallback implements AsyncCallback<Vector<Projektmarktplatz>>{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der teilnehmenden Projektmarklpätzen ist fehlgeschlagen");	
		}
		public void onSuccess(Vector<Projektmarktplatz> result) {
			
			if (result != null) {
				for (Projektmarktplatz proj : result) {
					lib.addItem(proj.getBezeichnung());

				}
			}
			
			
			
			
			
	class AusschreibungenAnzeigenCallback implements AsyncCallback<Vector<Ausschreibung>>{
		
		private Ausschreibung a;
		
		public AusschreibungenAnzeigenCallback(Ausschreibung a){
			this.a = a;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Das Anzeigen der Ausschreibungen ist fehlgeschlagen");
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			int row = ft_Ausschreibungen.getRowCount();
			if (result != null) {
				for (Ausschreibung ausschreibung : result) {
					ft_Ausschreibungen.setWidget(row +1, 0, new Label(a.getBezeichnung()));
					ft_Ausschreibungen.setWidget(row +1, 1, new Label(a.getBewerbungsfrist().toString()));
					ft_Ausschreibungen.setWidget(row +1, 2, new Label(a.getAusschreibungstext()));
					ft_Ausschreibungen.setWidget(row +1, 3, new Label(a.getStatus().toString()));
					
				}
			}
		}
				
			}
		}
	}
}
}
