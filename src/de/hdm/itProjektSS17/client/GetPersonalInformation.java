package de.hdm.itProjektSS17.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.shared.ProjektmarktplatzVerwaltungAsync;
import de.hdm.itProjektSS17.shared.bo.Person;

public class GetPersonalInformation extends Showcase{

	@Override
	protected String getHeadlineText() {
		return "Pers�nliche Informationen";
	}

	@Override
	protected void run() {
		this.append("Persönliche Informationen Ihres Profils:");
		
		ProjektmarktplatzVerwaltungAsync pimpl = ClientsideSettings.getProjektmarktplatzVerwaltung();
		
		pimpl.getPersonById(3, new ProfilInformationenCallback(this));
	}
	
	class ProfilInformationenCallback implements AsyncCallback<Person> {
		
		private Showcase showcase = null;
		
		public ProfilInformationenCallback(Showcase c) {
			this.showcase = c;
			this.showcase.append("blub");
		}
		
		@Override
		public void onFailure(Throwable caught) {
			this.showcase.append("Informationen konnten nicht geladen werden");
			this.showcase.append(caught.toString());
			//aktuell
		}

		@Override
		public void onSuccess(Person result) {
		              // Kundennummer und Name ausgeben
		     this.showcase.append("Vorname :" + result.getVorname());
		     this.showcase.append("Nachname :" + result.getNachname());
		}
		
	}
	
	

}
