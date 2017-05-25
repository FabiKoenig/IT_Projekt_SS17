package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.client.Showcase;
import de.hdm.itProjektSS17.server.db.BewerbungMapper;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;


/**
 * Diese Klasse erbt von Vertical Panel. Sie zeigt die Bewerbungen einer Person 
 * in tabellarischer Form
 * 
 * @author Tom Alender
 *
 */
public class MeineBewerbungenForm extends Showcase{
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	FlexTable ft_Bewerbung = new FlexTable();
	
	protected String getHeadlineText(){
		return "Meine Bewerbungen";
		

	}
	
	
	
	protected void run() {
		// TODO Auto-generated method stub
	/*	Label lbl_valueProjektname = new Label();
		Label lbl_valueBeschreibung = new Label();
		*/
		
		
		HorizontalPanel panel_bewerbung = new HorizontalPanel();
		this.add(panel_bewerbung);
		
		
		FlexCellFormatter cellFormatterBewerbung = ft_Bewerbung .getFlexCellFormatter();
		ft_Bewerbung.setWidth("32em");
		ft_Bewerbung.setCellSpacing(5);
		ft_Bewerbung.setCellPadding(3);
		cellFormatterBewerbung.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		// Tables have no explicit size -- they resize automatically on demand.
	    FlexTable ft_Bewerbung = new FlexTable();
	    
	    // Put some text at the table's extremes.  This forces the table to be
	    // 3 by 3.
	    ft_Bewerbung.setText(0, 0, "Ausschreibung");
	    ft_Bewerbung.setText(0, 1, "Bewerbungstext");
	    ft_Bewerbung.setText(0, 2, "Erstellungsdatum");
	    ft_Bewerbung.setText(0, 3, "Bewerbungsstatus");
	    ft_Bewerbung.setText(0, 4, "Löschen");
	    ft_Bewerbung.setText(0, 5, "Bearbeiten");
	    panel_bewerbung.add(ft_Bewerbung);
	    // Add a button to remove this stock from the table.
	  Button removeBewerbungButton = new Button("x");
	    removeBewerbungButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	       /* int removedIndex =Bewerbung.indexOf(symbol);
	        Bewerbung.deleteBewerbung(removedIndex);
	        p.removeRow(removedIndex + 1);*/
	      }
	    });
	    ft_Bewerbung.setWidget(1, 4, removeBewerbungButton);
	
	 Button editBewerbungButton = new Button("Bearbeiten");
	    editBewerbungButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	       /* int removedIndex =Bewerbung.indexOf(symbol);
	        Bewerbung.deleteBewerbung(removedIndex);
	        p.removeRow(removedIndex + 1);*/
	    	 DialogBoxEditBewerbung dpa = new DialogBoxEditBewerbung();
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				dpa.setPopupPosition(left, top);
				dpa.show();
	      }
	    });
	    ft_Bewerbung.setWidget(1, 5, editBewerbungButton);
		projektmarktplatzVerwaltung.getPersonById(3, new PersonCallback());
		
	}
	
	private class PersonCallback implements AsyncCallback<Person> {	   
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen Person ist fehlgeschlagen!");
			
		}
		
		@Override
		public void onSuccess(Person result) {
			
			if (result != null) {
				ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
				projektmarktplatzVerwaltung.getBewerbungByForeignOrganisationseinheit(result, new BewerbungAnzeigenCallback());
			}
			
		}
	}
	
	private class BewerbungAnzeigenCallback implements AsyncCallback<Vector<Bewerbung>>	{
		
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Projekte der Person ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			
		for (Bewerbung bewerbung : result) {
		ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
		projektmarktplatzVerwaltung.getAusschreibungById(bewerbung.getAusschreibungId(), new AusschreibungAnzeigenCallback(bewerbung));
		}
	}
	
	private class AusschreibungAnzeigenCallback implements AsyncCallback <Ausschreibung>{
		
		private Bewerbung  b = null;
		
		public AusschreibungAnzeigenCallback(Bewerbung b) {
			this.b= b;
		}
		@Override
		public void onFailure(Throwable caught) {
			
			Window.alert("Das Anzeigen der Projekte der Person ist fehlgeschlagen!");
			
		}
		@Override
		public void onSuccess(Ausschreibung result) {
			int row = ft_Bewerbung.getRowCount();
			if (result != null) {
				ft_Bewerbung.setWidget(row + 1, 0, new Label(result.getBezeichnung()));
				ft_Bewerbung.setWidget(row + 1, 1, new Label(b.getBewerbungstext()));
				ft_Bewerbung.setWidget(row + 1, 2, new Label(b.getErstellungsdatum().toString()));
				ft_Bewerbung.setWidget(row + 1, 3, new Label(b.getStatus().toString()));
			}
		}
		
	}
		
		
		
		
		/*			
			int row = ft_Bewerbung.getRowCount();
			if (result != null) {
			ft_Bewerbung.setWidget(row + 1, 0, new Label(bewerbung.getAusschreibungId());
			ft_Bewerbung.setWidget(row + 1, 1, new Label(bewerbung.getBewerbungstext()));
			ft_Bewerbung.setWidget(row + 1, 2, new Label(bewerbung.getOrganisationseinheitId());
			ft_Bewerbung.setWidget(row + 1, 3, new Label(bewerbung.getErstellungsdatum().toString());
		}*/
		
	}
			
  
		/* Button btn_bewerbungAnlegen = new Button();
	}
	    btn_bewerbungAnlegen.setText("Neue Bewerbung anlegen");
	    btn_bewerbungAnlegen.addClickHandler(new ClickHandler() {
		
			@Override
			public void onClick(ClickEvent event) {
				
				DialogBoxBewerbungAnlegen dpa = new DialogBoxBewerbungAnlegen();
				int left = Window.getClientWidth() / 3;
				int top = Window.getClientHeight() / 8;
				dpa.setPopupPosition(left, top);
				dpa.show();
				
			}
		});
	    // Let's put a button in the middle...
	    p.setWidget(1, 0, btn_bewerbungAnlegen);

	    // ...and set it's column span so that it takes up the whole row.
	    p.getFlexCellFormatter().setColSpan(1, 0, 3);

	    //RootPanel.get().add(p);
	  
		
		HorizontalPanel panel_bewerbung = new HorizontalPanel();
		this.add(panel_bewerbung);
		
		
		
				
		Grid projektGrid = new Grid(7 , 7);
		this.add(projektGrid);
		
		Label lbl_projektName = new Label("Projektname");
		projektGrid.setWidget(1, 0, lbl_projektName);
		projektGrid.setWidget(1, 1, lbl_valueProjektname);
		
		Label lbl_beschreibung = new Label("Beschriebung");
		projektGrid.setWidget(1, 1, lbl_beschreibung);
		projektGrid.setWidget(2, 1, lbl_valueBeschreibung);
		
		Label lbl_startdatum = new Label("Startdatum");
		projektGrid.setWidget(1, 2, lbl_startdatum);
		projektGrid.setWidget(3, 1, lbl_valueStartdatum);
		
		Label lbl_enddatum = new Label("Enddatum");
		projektGrid.setWidget(1, 3, lbl_enddatum);
		projektGrid.setWidget(4, 1, lbl_valueEnddatum);
		
		Label lbl_projektleiter = new Label("Projektleiter");
		projektGrid.setWidget(1, 4, lbl_projektleiter);
		projektGrid.setWidget(5, 1, lbl_valueProjektleiter);
		
		Label lbl_projektmarktplatz = new Label("Projektmarktplatz");
		projektGrid.setWidget(1, 5, lbl_projektmarktplatz);
		projektGrid.setWidget(6, 1, lbl_valueProjektmarktplatz);
		*/
	
		
		
	}
	
	

