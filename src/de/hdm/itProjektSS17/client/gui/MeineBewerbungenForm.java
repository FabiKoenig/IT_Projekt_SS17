package de.hdm.itProjektSS17.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.*;


/**
 * Diese Klasse erbt von Vertical Panel. Sie zeigt die Bewerbungen einer Person 
 * in tabellarischer Form
 * 
 * @author Fabian Koenig
 *
 */
public class MeineBewerbungenForm extends VerticalPanel{

	
	/**
	 * Instanz-Variablen werden deklariert
	 */
	
	// Vecotor, der später benutzt wird, um die Bewerbungen der Person auszulesen
	private Vector<Bewerbung> b = new Vector<Bewerbung>();
	
	//Panels für die GUI
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private FlexTable eigeneBewerbungenAnzeigenPanel = new FlexTable();
	
	
}
