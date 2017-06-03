package de.hdm.itProjektSS17.client.gui;

import com.google.gwt.user.client.ui.DialogBox;

import de.hdm.itProjektSS17.client.ClientsideSettings;
import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;

public class DialogBoxBewerben extends DialogBox {
	
	ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = ClientsideSettings.getProjektmarktplatzVerwaltung();
	
	int ausschreibungsId;
	
	public DialogBoxBewerben(int ausschreibungsId)
	{
		this.ausschreibungsId = ausschreibungsId;
	}

}
