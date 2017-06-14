package de.hdm.itProjektSS17.server;

import java.util.logging.Logger;

import de.hdm.itProjektSS17.shared.CommonSettings;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Server-seitigen Klassen von 
 * Bedeutung sind.
 *
 */
public class ServersideSettings extends CommonSettings{

	private static final String LOGGER_NAME = "Projektmarktplatz Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/**
	 * 
	 * @return Server-Seitige Logger-Insanz 
	 */
	public static Logger getLogger(){
		return log;
	}
}
