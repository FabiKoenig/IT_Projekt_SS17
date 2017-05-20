package de.hdm.itProjektSS17.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.shared.*;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle
 * Clientseitigen Klassen relevant sind.
 * 
 * @author Fabian Koenig
 *
 */

public class ClientsideSettings extends CommonSettings{

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem serverseitigen
	 * Dienst <code>ProjektmarktplatzVerwaltung</code>
	 */
	private static ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = null;
	
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem serverseitigen
	 * Dienst <code>RemoteGenerator</code>
	 */	
	private static ReportGeneratorAsync reportGenerator = null;
	
	/**
	 * Name des Client-seitigen Loggers.
	 */
	private static final String LOGGER_NAME = "Projektmarktplatz Web Client";
	
	/**
	 * Instanz des Client-seitigen Loggers.
	 */
	
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/**
	 * Auslesen des applikationsweiten (Client-seitig) zentralen Loggers.
	 * @return die Logger-Instanz für die Server-Seite.
	 */
	
	public static Logger getLogger(){
		return log;
	}
	
	/**
	 * Durch die Methode wird die ProjektmarktplatzVerwaltung erstellt, sofern diese noch nicht besteht.
	 * Bei erneutem Aufruf der Methode wird das bereits angelegte Objekt zurückgegeben.
	 * 
	 * @return eindeutige Instanz des Typs <code>ProjektmarktplatzVerwaltungAsync</code>
	 */
	public static ProjektmarktplatzVerwaltungAsync getProjektmarktplatzVerwaltung(){
		//Falls bis jetzt noch keine PMV Instanz bestand
		if (projektmarktplatzVerwaltung == null){
			projektmarktplatzVerwaltung = GWT.create(ProjektmarktplatzVerwaltung.class);
		}
		
		return projektmarktplatzVerwaltung;
	}
	
	/**
	 * Durch die Methode wird eine Instanz des ReportGenerators erstellt, sofern diese noch nicht besteht.
	 * Bei erneutem Aufruf der Methode wird das bereits angelegte Objekt zurückgegeben.
	 * 
	 * @return eindeutige Instanz des Typs <code>ReportGeneratorAsync</code>
	 */	
	
	public static ReportGeneratorAsync getReportGenerator(){
		//Falls bis jetzt noch keine ReportGenerator Instanz bestand
		if(reportGenerator == null){
			reportGenerator = GWT.create(ReportGenerator.class);
			
			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>(){

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden");
					
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info("Der ReportGenerator wurde initialisiert.");
					
				}
				
			};
			reportGenerator.init(initReportGeneratorCallback);
		}
		
		return reportGenerator;
	}
}
